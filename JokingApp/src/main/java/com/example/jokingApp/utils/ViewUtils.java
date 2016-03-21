package com.example.jokingApp.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class ViewUtils {
	public static void removeParent(View v){
		// 先找到控件的父亲
		ViewParent parent = v.getParent();
		//所有的控件 都有父亲  父亲一般情况下 就是ViewGoup
		if(parent instanceof ViewGroup){
			ViewGroup group=(ViewGroup) parent;
			group.removeView(v);
		}
	}
}
