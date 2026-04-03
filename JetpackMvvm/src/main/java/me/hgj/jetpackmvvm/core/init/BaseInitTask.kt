package me.hgj.jetpackmvvm.core.init

/**
 *
 * 说明　：
 */
abstract class BaseInitTask : InitTask {
    @Volatile
    var isFinished = false
}
