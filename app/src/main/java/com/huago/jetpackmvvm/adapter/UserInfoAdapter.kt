package com.example.base_mvvm.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.huago.jetpackmvvm.R
import com.huago.jetpackmvvm.dao.UserInfo
import com.huago.jetpackmvvm.databinding.ItemUserinfoBinding


/**
 * @author Administrator
 * @updateDes 2020/6/16
 */
class UserInfoAdapter :
    BaseQuickAdapter<UserInfo, BaseViewHolder>(R.layout.item_userinfo) {
    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)

        DataBindingUtil.bind<ItemUserinfoBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: UserInfo?) {
        if (item == null) {
            return
        }
        val binding = helper.getBinding<ItemUserinfoBinding>()
        if (binding != null) {
            binding.viewModel = item
            binding.executePendingBindings()
        }

    }

}