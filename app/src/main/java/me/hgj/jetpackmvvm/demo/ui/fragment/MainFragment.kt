package me.hgj.jetpackmvvm.demo.ui.fragment

import android.os.Bundle
import me.hgj.jetpackmvvm.base.vm.BaseViewModel
import me.hgj.jetpackmvvm.demo.R
import me.hgj.jetpackmvvm.demo.app.core.base.BaseFragment
import me.hgj.jetpackmvvm.demo.databinding.FragmentMainBinding
import me.hgj.jetpackmvvm.demo.ui.adapter.MainAdapter
import me.hgj.jetpackmvvm.demo.ui.adapter.MainAdapter.Companion.PAGE_HOME
import me.hgj.jetpackmvvm.demo.ui.adapter.MainAdapter.Companion.PAGE_USER

/**
 * 描述　: 项目主页Fragment
 */
class MainFragment : BaseFragment<BaseViewModel, FragmentMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        //设置适配器
        mBind.mainViewPager.adapter = MainAdapter(this)
        //设置缓存页面数量
        mBind.mainViewPager.offscreenPageLimit = mBind.mainViewPager.adapter!!.itemCount
        //禁止滑动
        mBind.mainViewPager.isUserInputEnabled = false
        //设置底部导航栏选择事件
        mBind.mainNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main -> mBind.mainViewPager.setCurrentItem(PAGE_HOME, false)
                R.id.menu_me -> mBind.mainViewPager.setCurrentItem(PAGE_USER, false)
            }
            true
        }
    }
}