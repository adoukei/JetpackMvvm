package me.hgj.jetpackmvvm.widget.loadsir.core;


import me.hgj.jetpackmvvm.widget.loadsir.callback.Callback;


public interface Convertor<T> {
   Class<?extends Callback> map(T t);
}
