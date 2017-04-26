package com.example.zagvozkings.test2.ui.activity.main;

import com.example.zagvozkings.test2.R;
import com.example.zagvozkings.test2.customView.HallLayout;
import com.example.zagvozkings.test2.ui.activity.BaseActivity;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.CreateHallPresenter;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.MainView;
import com.example.zagvozkings.test2.ui.fragment.FragmentAddTable_;
import com.example.zagvozkings.test2.storage.Table;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainView{

    @Bean(CreateHallPresenterImp.class)
    protected CreateHallPresenter createHallPresenter;

    @ViewById
    protected HallLayout MainHall;

    @AfterViews
    protected void createHall(){
        final MainView view = this;
        MainHall.post(new Runnable() {
            @Override
            public void run() {
                createHallPresenter.init(view);
            }
        });
    }

    @Click(R.id.MaimADD)
    protected void showSelected(){
        openDialog(new FragmentAddTable_());
    }

    @Override
    public void addTable(Table table) {
        if (MainHall != null && table != null)
            MainHall.setTable(table);
    }

    @Override
    public void ChangeTable(Table table) {
        if (MainHall != null && table != null)
            MainHall.changeTable(table);
    }
}
