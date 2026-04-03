package me.hgj.jetpackmvvm.demo.ui.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowInsetsControllerCompat
import me.hgj.jetpackmvvm.base.vm.BaseViewModel
import me.hgj.jetpackmvvm.demo.R
import me.hgj.jetpackmvvm.demo.app.core.base.BaseActivity
import me.hgj.jetpackmvvm.demo.databinding.ActivityMainBinding
import me.hgj.jetpackmvvm.ext.util.toast

/**
 *
 * 描述　：项目主页Activity
 */
class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {

    override val showTitle = false

    var exitTime = 0L

    override fun initView(savedInstanceState: Bundle?) {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    "再按一次退出程序".toast()
                    exitTime = System.currentTimeMillis()
                } else {
                    finish()
                }
            }
        })
    }
}
