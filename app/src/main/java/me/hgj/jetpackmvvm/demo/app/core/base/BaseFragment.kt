package me.hgj.jetpackmvvm.demo.app.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import me.hgj.jetpackmvvm.base.ui.BaseVbFragment
import me.hgj.jetpackmvvm.base.vm.BaseViewModel
import me.hgj.jetpackmvvm.demo.app.core.ext.initClose
import me.hgj.jetpackmvvm.demo.databinding.FragmentBaseWithToolbarBinding
import me.hgj.jetpackmvvm.ext.util.statusPadding

/**
 * 描述　: 根据业务定义自己的基类Fragment ，注意：仅供参考 这里是demo ，随便你怎么造都行，请根据自己的业务来
 */
abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> (): BaseVbFragment<VM, VB>() {

    open val showTitle: Boolean = false

    open val title = ""

    /**
     * 因为用了navigation，所以封装了一下父布局，包含了toolBar头部信息
     */
    private var _baseBinding: FragmentBaseWithToolbarBinding? = null
    protected val baseBinding get() = _baseBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (showTitle) {
            // 外层布局
            _baseBinding = FragmentBaseWithToolbarBinding.inflate(inflater, container, false)
            baseBinding.includeToolbar.toolbar.statusPadding()
            // 调用父类的创建流程，获取子布局
            val childView = super.onCreateView(inflater, _baseBinding!!.fragmentContent, savedInstanceState)
            // 添加子布局到容器
            if (childView != null) {
                _baseBinding!!.fragmentContent.addView(childView)
            }
            // 初始化 Toolbar（默认返回按钮）
            _baseBinding!!.includeToolbar.toolbar.initClose(title) {
               //todo返回页面
            }
            _baseBinding!!.root
        } else {
            // 没有标题，直接走普通流程
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }


    /**
     * 这个基类定义了一个头部布局，那么状态布局就让它只加载 除头部的布局了，不然会默认将整个页面都包裹
     */
    override fun getLoadingView() = if(showTitle) baseBinding.fragmentContent as View else null

    override fun onDestroyView() {
        super.onDestroyView()
        _baseBinding = null
    }


}