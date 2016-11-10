package com.bw.zyj.recyclerviewdemo;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by PigGhost on 2016/11/10.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);

        ImageLoader.getInstance().init(configuration);
    }
}
