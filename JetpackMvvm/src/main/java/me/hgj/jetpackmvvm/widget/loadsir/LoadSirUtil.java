package me.hgj.jetpackmvvm.widget.loadsir;

import android.os.Looper;

import java.util.List;

import me.hgj.jetpackmvvm.widget.loadsir.target.ITarget;


public class LoadSirUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static ITarget getTargetContext(Object target, List<ITarget> targetContextList) {
        for (ITarget targetContext : targetContextList) {
            if (targetContext.equals(target)) {
                return targetContext;
            }

        }
        throw new IllegalArgumentException("No TargetContext fit it");
    }
}
