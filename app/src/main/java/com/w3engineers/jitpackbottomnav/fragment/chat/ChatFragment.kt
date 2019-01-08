package com.w3engineers.jitpackbottomnav.fragment.chat

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.w3engineers.jitpackbottomnav.R
import com.w3engineers.jitpackbottomnav.data.model.Message
import com.w3engineers.jitpackbottomnav.data.model.User
import com.w3engineers.jitpackbottomnav.databinding.FragmentChatBinding
import com.w3engineers.jitpackbottomnav.util.FileUtils
import com.w3engineers.jitpackbottomnav.util.Permission
import org.workfort.base.ui.base.BaseFragment


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 11/26/2018 at 3:52 PM.
*  * Email : azizul@w3engineers.com
*  *
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 11/26/2018.
*  *
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
*  ****************************************************************************
*/

class ChatFragment : BaseFragment() {

    lateinit var binding: FragmentChatBinding
    lateinit var chatAdapter: ChatAdapter
    lateinit var user: User
    lateinit var chatViewModel: ChatViewModel
    lateinit var capturedImagePath: String
    override val getLayoutId: Int
        get() = R.layout.fragment_chat
    override val getMenuId: Int
        get() = R.menu.menu_profile

    override fun currentFragment(): Fragment = this

    override fun startView() {
        binding = getViewBinding() as FragmentChatBinding
        chatAdapter = ChatAdapter(activity)
        binding.imageButtonSend.setOnClickListener(this)
        binding.imageButtonCamera.setOnClickListener(this)
        binding.recyclerViewMessage.adapter = chatAdapter
        binding.recyclerViewMessage.layoutManager = LinearLayoutManager(activity)
        chatViewModel = getViewModel()
        user = arguments?.get("user") as User
        loadData()
    }

    fun getViewModel(): ChatViewModel {
        return ViewModelProviders.of(this).get(ChatViewModel::class.java)
    }

    private fun loadData() {
        chatViewModel.getMessagePagedLiveData(user).observe(this,
            Observer<PagedList<Message>> {
                Log.e("Item_list", "Chat List size =" + it.size)
                chatAdapter.submitList(it)
                //recyclerView.scrollToPosition(chatAdapter.itemCount-1)
            })
    }


    override fun onClick(view: View?) {
        //Navigation.findNavController(view!!).navigate(R.id.open_fragment_example_second)
        if (view!!.id == R.id.image_button_camera) {
            if (Permission.on(activity!!).request(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                //findNavController().navigate(ChatFragmentDirections.openCameraFragment())
                openCamera()
            }
        } else {
            val value = binding.edittextMessageInput.text.toString().trim()
            if (value.isEmpty()) return
            chatViewModel.saveMessage(value, user)
            binding.edittextMessageInput.setText("")
        }
    }


    fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(activity!!.packageManager) != null) {
            val file = FileUtils.createImageFile()
            var mImageFileUri: String
            if (file != null) {
                capturedImagePath = FileUtils.FILE_PREFIX + file.getAbsolutePath()
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                    mImageFileUri =
                            FileProvider.getUriForFile(activity!!, getString(R.string.file_provider_authority), file)
                                .toString()
                } else {
                    mImageFileUri = Uri.parse(FileUtils.FILE_PREFIX + file.getAbsolutePath()).toString()
                }
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageFileUri)
                startActivityForResult(cameraIntent, 100)
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_menu -> {
                Toast.makeText(activity, "Message deleted", Toast.LENGTH_SHORT).show()
                chatViewModel.deleteAllMessage(user)
                return true
            }
            R.id.profile_menu -> {
                findNavController().navigate(ChatFragmentDirections.openFragmentProfile())
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun stopView() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (!TextUtils.isEmpty(capturedImagePath)) {
            val uri = Uri.parse(capturedImagePath)
            Toast.makeText(activity, uri.toString(), Toast.LENGTH_SHORT).show()
            capturedImagePath = ""
        }
    }


}