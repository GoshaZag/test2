package com.example.zagvozkings.test2.ui.activity.main;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.zagvozkings.test2.R;
import com.example.zagvozkings.test2.customView.CustomTableView;
import com.example.zagvozkings.test2.storage.TableData;
import com.example.zagvozkings.test2.ui.activity.BaseActivity;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.CreateHallPresenter;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.HallSizeModel;
import com.example.zagvozkings.test2.ui.activity.main.interfaces.MainView;
import com.example.zagvozkings.test2.utility.Table;
import com.example.zagvozkings.test2.utility.TypeTable;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EBean
public class CreateHallPresenterImp implements CreateHallPresenter {

    private static final int DEFAULT_HALL_WIDTH = 680;
    private static final int DEFAULT_HALL_HEIGHT = 680;

    @RootContext
    protected Context context;
    @Bean
    protected TableData tableData;
    @RootContext
    protected BaseActivity base;

    @Bean(HallSizeModelImp.class)
    protected HallSizeModel hallSizeModel;
    @ViewById
    protected LinearLayout Main;

    protected RelativeLayout MainHall;

    @Override
    public void init(MainView mainView) {

        //измеряем зал, если темно и нет рулетки, то используем размеры по умолчанию
        Integer width = hallSizeModel.getWidth();
        Integer height = hallSizeModel.getHeight();
        if (width == null)  width  = DEFAULT_HALL_WIDTH;
        if (height == null) height = DEFAULT_HALL_HEIGHT;

        //создаем коэффициент для зала и столов
        float tempW = width / (float) Main.getWidth();
        float tempH = height /(float) Main.getHeight();
        float scale = (tempW > tempH) ? tempW : tempH;
        tableData.setScale(scale);

        int layoutWidth = (int) (width / scale);
        int layoutHeight = (int) (height / scale);

        //зал известен,
        MainHall = new RelativeLayout(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(layoutWidth, layoutHeight);
        MainHall.setLayoutParams(lp);
        MainHall.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        mainView.addHall(MainHall);

        //если в зале нет столов, добавим столы по умолчанию
        List<Table> listTable = tableData.getListTable();
        if (listTable == null) {
            tableData.setTableView(new Table(0, "Стол 1", TypeTable.rectangle, 340, 340, 170, 170, 0f));
            tableData.setTableView(new Table(1, "Стол 1", TypeTable.rectangle, 340, 340, 510, 510, 0f));
            listTable = tableData.getListTable();
        }

        //если есть столы, то после небольшого дебоша (переворот экрана) выставляем все столы правильно
        for (Table table : listTable){
            CustomTableView customTableView = new CustomTableView(context, table);
            if (tableData.getChangeTable() != null) {
                if (table.getId().equals(tableData.getChangeTable().getIdTable())){
                    tableData.setChangeTable(customTableView);
                }
            }
            mainView.addView(customTableView);
        }
    }

}
