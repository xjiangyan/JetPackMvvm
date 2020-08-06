package com.huago.jetpackmvvm

import androidx.lifecycle.ViewModelProvider
import com.fmt.github.base.fragment.BaseVMFragment
import com.fmt.github.base.viewmodel.BaseViewModel
import com.huago.jetpackmvvm.viewmodel.SecFragmentViewModel

/**
 * @author xjiang
 * @updateDes 2020/8/5
 */
class SecFragment : BaseVMFragment() {
    override fun getLayoutRes(): Int {
        return R.layout.base_fragment_sec
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProvider(this).get(SecFragmentViewModel::class.java)
    }

    override fun initView() {
    }
}