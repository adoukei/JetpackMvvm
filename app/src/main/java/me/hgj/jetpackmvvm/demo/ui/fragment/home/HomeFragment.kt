package me.hgj.jetpackmvvm.demo.ui.fragment.home

import android.os.Bundle
import me.hgj.jetpackmvvm.base.ui.BaseVbFragment
import me.hgj.jetpackmvvm.demo.data.vm.HomeViewModel
import me.hgj.jetpackmvvm.demo.databinding.IncludeRecyclerviewBinding

/**
 * 描述　:
 */
class HomeFragment : BaseVbFragment<HomeViewModel, IncludeRecyclerviewBinding>() {

    override fun initView(savedInstanceState: Bundle?) {

    }


    companion object {
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

}