package me.hgj.jetpackmvvm.demo.ui.fragment.me

import android.os.Bundle
import me.hgj.jetpackmvvm.core.data.obs
import me.hgj.jetpackmvvm.demo.R
import me.hgj.jetpackmvvm.demo.app.core.base.BaseFragment
import me.hgj.jetpackmvvm.demo.app.core.ext.onRefresh
import me.hgj.jetpackmvvm.demo.app.core.util.LocalDataUtil
import me.hgj.jetpackmvvm.demo.app.core.util.UserManager
import me.hgj.jetpackmvvm.demo.data.model.entity.UserInfo
import me.hgj.jetpackmvvm.demo.data.vm.IntegralViewModel
import me.hgj.jetpackmvvm.demo.data.vm.UserViewModel
import me.hgj.jetpackmvvm.demo.databinding.FragmentMeBinding
import me.hgj.jetpackmvvm.demo.ui.activity.SettingActivity
import me.hgj.jetpackmvvm.demo.ui.activity.WebActivity
import me.hgj.jetpackmvvm.ext.lifecycle.getViewModel
import me.hgj.jetpackmvvm.ext.util.clickNoRepeat
import me.hgj.jetpackmvvm.ext.util.intent.openActivity
import me.hgj.jetpackmvvm.ext.util.loadCircle
import me.hgj.jetpackmvvm.ext.util.statusPadding

/**
 * 描述　: 我的
 */

class MeFragment : BaseFragment<UserViewModel, FragmentMeBinding>() {

    companion object {
        fun newInstance(): MeFragment {
            val args = Bundle()
            val fragment = MeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    val integralVm by getViewModel<IntegralViewModel>()

    override fun initView(savedInstanceState: Bundle?) {
        mBind.topLayout.statusPadding()
        mBind.meSwipe.onRefresh {
            refreshIntegral()
        }
        refreshIntegral()
    }

    override fun onBindViewClick() {
        super.onBindViewClick()
        mBind.topLayout.clickNoRepeat {

        }


        mBind.settingLayout.clickNoRepeat {
            //设置
            openActivity<SettingActivity>()
        }
    }

    override fun createObserver() {
        super.createObserver()
        UserManager.observeUser().observe(viewLifecycleOwner) {
            updateUser(it)
        }
    }

    private fun updateUser(info: UserInfo?) {
        if (info != null) {
            mBind.userName.text = info.nickname.ifEmpty { info.username }
            mBind.headImg.loadCircle(LocalDataUtil.randomImage(), error = R.mipmap.ic_launcher)
            refreshIntegral()
        } else {
            mBind.userName.text = "请先登录~"
            mBind.userInfo.text = "id："
        }
    }

    private fun refreshIntegral() {
        if (!UserManager.isLoggedIn) {
            mBind.meSwipe.isRefreshing = false
            return
        }
        mBind.meSwipe.isRefreshing = true
        integralVm.getIntegralData().obs(viewLifecycleOwner) {
            onSuccess {
                mBind.meSwipe.isRefreshing = false
                mBind.userInfo.text = "id：${it.userId}"
            }
            onError {
                mBind.meSwipe.isRefreshing = false
            }
        }
    }

}