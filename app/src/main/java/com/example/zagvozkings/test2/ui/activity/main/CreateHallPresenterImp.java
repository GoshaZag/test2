package com.example.zagvozkings.test2.ui.activity.main;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.zagvozkings.test2.R;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.CreateHallPresenter;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.HallSizeModel;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.MainView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

@EBean
public class CreateHallPresenterImp implements CreateHallPresenter {

    @RootContext
    protected Context context;

    @Bean
    protected HallSizeModel hallSizeModel;
    @ViewById
    protected LinearLayout Main;

    protected RelativeLayout MainHall;

    @Override
    public void init(MainView mainView) {

        Integer width = hallSizeModel.getWidth();
        Integer height = hallSizeModel.getHeight();
        if (width == null)  width  = 680;
        if (height == null) height = 680;

        float tempW = width / (float) Main.getWidth();
        float tempH = height /(float) Main.getHeight();
        float temp = (tempW > tempH) ? tempW : tempH;

        int layoutWidth = (int) (width / temp);
        int layoutHeight = (int) (height / temp);

        MainHall = new RelativeLayout(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(layoutWidth, layoutHeight);
        MainHall.setLayoutParams(lp);
        MainHall.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));

        mainView.addHall(MainHall);
    }
}
