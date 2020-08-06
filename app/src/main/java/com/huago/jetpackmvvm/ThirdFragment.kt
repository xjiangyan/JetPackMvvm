package com.huago.jetpackmvvm

import androidx.lifecycle.ViewModelProvider
import com.fmt.github.base.fragment.BaseDataBindVMFragment
import com.fmt.github.base.viewmodel.BaseViewModel
import com.huago.jetpackmvvm.databinding.BaseFragmentThirdBinding
import com.huago.jetpackmvvm.viewmodel.ThirdFragmentViewModel

/**
 * @author xjiang
 * @updateDes 2020/8/5
 */
class ThirdFragment : BaseDataBindVMFragment<BaseFragmentThirdBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.base_fragment_third
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProvider(this).get(ThirdFragmentViewModel::class.java)
    }

    override fun initView() {

    }

}