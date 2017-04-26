package com.example.zagvozkings.test2.ui.activity.main;

import android.view.View;

import com.example.zagvozkings.test2.customView.HallLayout;
import com.example.zagvozkings.test2.storage.TableData;
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

    @Bean(CreateTableListModelImp.class)
    protected CreateTableListModel createTableListModel;

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
    }
}
