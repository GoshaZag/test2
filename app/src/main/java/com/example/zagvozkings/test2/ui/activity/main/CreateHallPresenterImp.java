package com.example.zagvozkings.test2.ui.activity.main;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.LinearLayout;

import com.example.zagvozkings.test2.customView.HallLayout;
import com.example.zagvozkings.test2.ui.activity.BaseActivity;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.CreateHallPresenter;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.CreateTableListModel;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.MainView;
import com.example.zagvozkings.test2.ui.fragment.FragmentAddTable_;
import com.example.zagvozkings.test2.storage.Table;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EBean
public class CreateHallPresenterImp implements CreateHallPresenter {

    @RootContext
    protected BaseActivity baseActivity;
    @RootContext
    protected Context context;

    @Bean(CreateTableListModelImp.class)
    protected CreateTableListModel createTableListModel;

    @ViewById
    protected LinearLayout Main;

    @ViewById
    protected HallLayout MainHall;

    @Override
    public void init(MainView mainView) {
        //хочешь тыкать, скажи что тыкать!
        MainHall.setTableOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentAddTable_ dialog = new FragmentAddTable_();
                baseActivity.openDialog(dialog);
                dialog.setIDTable(v.getId());
            }
        });

        //хочешь долгий тап, скажи что тыкать!
        MainHall.setTableOnLongClickListener(null);

        //модели---------------------------------

        //бери с инета...
        MainHall.setSize(null, null);
        //бери с инета, базы....
        List<Table> tableList = createTableListModel.getList();
        for (Table table : tableList){
            mainView.addTable(table);
        }

        //Для приближения---------------------------

        Main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ScaleGestureDetector SGD = new ScaleGestureDetector(context,new ScaleListener());
                SGD.onTouchEvent(event);
                return true;
            }
        });
    }

    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    (int)(MainHall.getWidth() * detector.getScaleFactor()),
                    (int)(MainHall.getHeight() * detector.getScaleFactor()));
            MainHall.setLayoutParams(lp);
            MainHall.update();
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
        }
    }
}
