package com.hzl.administrator.test1;

import android.util.Log;

/**
 * @author hzl
 * @time 2016/5/26 19:08
 * 单例模式的测试
 */
public class SingleMode {
    int i = 0;

    private static class SingleTonBuilder {
        private static SingleMode singleTon = new SingleMode();
    }


    private static SingleMode singleTon2 = new SingleMode();


    private SingleMode() {
        Log.e("s", "getInstance1");
    }

    public static SingleMode getInstance() {
        Log.e("s", "getInstance");
        return singleTon2;
    }

    protected int method(int i) {
        return i;
    }
}
