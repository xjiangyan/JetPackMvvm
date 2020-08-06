package com.huago.jetpackmvvm

import androidx.lifecycle.ViewModelProvider
import com.fmt.github.base.viewmodel.BaseViewModel
import com.huago.baselibrary.fragment.BaseListMVFragment
import com.huago.jetpackmvvm.databinding.BaseFragmentFirstBinding
import com.huago.jetpackmvvm.viewmodel.FirstFragmentViewModel

/**
 * @author xjiang
 * @updateDes 2020/8/5
 */
class FirstFragment : BaseListMVFragment<BaseFragmentFirstBinding>() {
    override fun initRecyclerView() {


    }

    override fun getListData() {

    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProvider(this).get(FirstFragmentViewModel::class.java)
    }
}