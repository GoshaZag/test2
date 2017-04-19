package com.example.zagvozkings.test2.ui.activity.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.zagvozkings.test2.R;
import com.example.zagvozkings.test2.customView.CustomTableView;
import com.example.zagvozkings.test2.storage.TableData;
import com.example.zagvozkings.test2.ui.activity.BaseActivity;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.CreateHallPresenter;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.MainView;
import com.example.zagvozkings.test2.ui.fragment.FragmentAddTable_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainView{

    @ViewById
    protected LinearLayout Main;

    @Bean
    protected TableData tableData;
    @Bean(CreateHallPresenterImp.class)
    protected CreateHallPresenter createHallPresenter;

    protected RelativeLayout MainHall;

    @AfterViews
    protected void createHall(){
        final MainView mainView = this;
        Main.post(new Runnable() {
            @Override
            public void run() {
                createHallPresenter.init(mainView);
            }
        });
    }

    @Click(R.id.MaimADD)
    protected void showSelected(){
        tableData.setChangeTable(null);
        openDialog(new FragmentAddTable_());
    }

    @Override
    public void addView(final CustomTableView view){
        if (MainHall != null && view != null) {
            ViewGroup parentView = (ViewGroup) view.getParent();
            if (parentView != null) parentView.removeView(view);
            MainHall.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentAddTable_ dialog = new FragmentAddTable_();
                    tableData.setChangeTable(view);
                    openDialog(dialog);
                }
            });
        }
    }

    @Override
    public void addHall(RelativeLayout mainHall) {
        if (Main != null && mainHall != null){
            MainHall = mainHall;
            Main.addView(mainHall);
        }
    }

}
