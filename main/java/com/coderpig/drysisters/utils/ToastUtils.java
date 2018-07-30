package com.coderpig.drysisters.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.coderpig.drysisters.DrySisterApp;

public class ToastUtils {

    public static void shortToast(String msg) {
        /**
         * 分别对应Toast.LENGTH_LONG（3.5秒）和Toast.LENGTH_SHORT（2秒）的值
         */
        Toast toast = Toast.makeText(DrySisterApp.getContext(),msg,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM,0,40);
        toast.show();
    }

    public static void longToast(String msg) {
        Toast toast = Toast.makeText(DrySisterApp.getContext(),msg,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM,0,40);
        toast.show();
    }
}
