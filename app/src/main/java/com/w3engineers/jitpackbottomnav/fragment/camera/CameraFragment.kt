package com.w3engineers.jitpackbottomnav.fragment.camera

import android.Manifest
import android.graphics.Bitmap
import android.os.Environment
import android.os.SystemClock
import androidx.fragment.app.Fragment
import com.cjt2325.cameralibrary.JCameraView
import com.w3engineers.jitpackbottomnav.MainActivity
import com.w3engineers.jitpackbottomnav.R
import com.w3engineers.jitpackbottomnav.databinding.FragmentCameraBinding
import com.w3engineers.jitpackbottomnav.util.FileUtils
import com.w3engineers.jitpackbottomnav.util.Permission
import org.workfort.base.ui.base.BaseFragment
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 12/28/2018 at 12:39 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 12/28/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class CameraFragment : BaseFragment() {

    private lateinit var binding: FragmentCameraBinding

    override val getLayoutId: Int
        get() = R.layout.fragment_camera

    override val getMenuId: Int
        get() = 0

    override fun startView() {
        binding = getViewBinding() as FragmentCameraBinding


            binding.jcCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().path)
            binding.jcCameraView.setAutoFoucs(true)
            val file = File(FileUtils.getDir("video"))
            if (!file.exists())
                file.mkdirs()
            binding.jcCameraView.setSaveVideoPath(FileUtils.getDir("photo"))
            initListener()
    }

    private fun initListener() {
        binding.jcCameraView.setCameraViewListener(object : JCameraView.CameraViewListener {
            override fun captureSuccess(bitmap: Bitmap?) {
                val path = saveBitmap(bitmap!!, FileUtils.getDir("photo"))
                (activity as MainActivity).getMainViewModel().capturedImagePath(path)
                activity!!.getSupportFragmentManager().popBackStack();
            }

            override fun quit() {
                activity!!.getSupportFragmentManager().popBackStack();
            }

            override fun recordSuccess(url: String?) {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        binding.jcCameraView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.jcCameraView.onPause()
    }


    fun saveBitmap(bm: Bitmap, dir: String): String {
        var path = ""
        val f = File(dir, "CSDN_LQR_" + SystemClock.currentThreadTimeMillis() + ".png")
        if (f.exists()) {
            f.delete()
        }
        try {
            val out = FileOutputStream(f)
            bm.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()
            path = f.absolutePath
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return path
    }

    override fun stopView() {}
    override fun currentFragment(): Fragment = this
}