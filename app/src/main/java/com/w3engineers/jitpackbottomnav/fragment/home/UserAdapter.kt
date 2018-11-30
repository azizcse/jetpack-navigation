package com.w3engineers.jitpackbottomnav.fragment.home


import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.core.kbasekit.ui.base.BaseAdapter
import com.core.kbasekit.ui.base.BaseViewHolder
import com.w3engineers.jitpackbottomnav.R
import com.w3engineers.jitpackbottomnav.data.model.User
import com.w3engineers.jitpackbottomnav.databinding.ItemUserBinding

class UserAdapter : BaseAdapter<User>() {

    override fun isEqual(leftItem: User, rightItem: User): Boolean {
        return leftItem.userId.equals(rightItem.userId)
    }

    override fun newViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<User> {
        return UserHolder(inflate(parent!!, R.layout.item_user))
    }

    private inner class UserHolder(viewDataBinding: ViewDataBinding) : BaseViewHolder<User>(viewDataBinding){
        override fun bind(item: User) {
            val viewBinding = viewDataBinding as ItemUserBinding
            viewBinding.userName.text = item.userName
            viewBinding.userId.text = item.userId
            setOnclick(viewBinding.userName, viewBinding.userId)
        }
        override fun onClick(v: View?) {
            mListener?.onItemClick(v!!, getItem(adapterPosition)!!)
        }

    }
}