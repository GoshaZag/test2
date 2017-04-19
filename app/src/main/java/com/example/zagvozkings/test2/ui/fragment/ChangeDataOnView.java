package com.example.zagvozkings.test2.ui.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.zagvozkings.test2.customView.CustomTableView;
import com.example.zagvozkings.test2.storage.TableData;
import com.example.zagvozkings.test2.utility.TypeTable;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

@EBean
class ChangeDataOnView {

    @Bean
    protected TableData tableData;

    @ViewById
    protected CheckBox TableCicle;
    @ViewById
    protected CheckBox TableRectangle;

    @ViewById
    protected EditText TableName;

    @ViewById
    protected EditText FRX;
    @ViewById
    protected EditText FRY;

    @ViewById
    protected EditText FRRadius;

    @ViewById
    protected EditText FRWidth;
    @ViewById
    protected EditText FRHeight;
    @ViewById
    protected EditText FRTurn;

    @ViewById
    protected LinearLayout Cicle;
    @ViewById
    protected LinearLayout Rectangle;


    public void init() {

        //поставим слушатели
        TableCicle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    Cicle.setVisibility(View.GONE);
                } else {
                    TableRectangle.setChecked(false);
                    Rectangle.setVisibility(View.GONE);
                    Cicle.setVisibility(View.VISIBLE);
                }
            }
        });
        TableRectangle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    Rectangle.setVisibility(View.GONE);
                } else {
                    TableCicle.setChecked(false);
                    Cicle.setVisibility(View.GONE);
                    Rectangle.setVisibility(View.VISIBLE);
                }
            }
        });

        //при повороте проверим
        if (TableCicle.isChecked()) Cicle.setVisibility(View.VISIBLE);
        if (TableRectangle.isChecked()) Rectangle.setVisibility(View.VISIBLE);

        //проверим, создаем новое или редактируем старое
        final CustomTableView oldTableView = tableData.getChangeTable();
        if (oldTableView == null) return;

        if (oldTableView.isInLayout()){
            zapoln(oldTableView);
        } else {
            //при повороте во время редактирования, нового стола еще нет в зале
            //в зале остался старый стол
            //подождем грузчиков, когда закончат заносить столы
            oldTableView.post(new Runnable() {
                @Override
                public void run() {
                    zapoln(oldTableView);
                }
            });
        }
    }

    private void zapoln(CustomTableView oldTableView) {
        TableName.setText(oldTableView.getText());


        float scale = tableData.getScale();
        FRX.setText(String.valueOf((int)((oldTableView.getLeft() + oldTableView.getWidth() / 2) * scale)));
        FRY.setText(String.valueOf((int)((oldTableView.getTop() + oldTableView.getHeight() / 2) * scale)));

        TypeTable typeTable = oldTableView.type;
        switch (typeTable){
            case cycle:
                TableCicle.setChecked(true);
                FRRadius.setText(String.valueOf((int) (oldTableView.getWidth() * scale) / 2));
                break;
            case rectangle:
                TableRectangle.setChecked(true);
                FRWidth.setText(String.valueOf((int) (oldTableView.getWidth() * scale)));
                FRHeight.setText(String.valueOf((int) (oldTableView.getHeight() * scale)));
                FRTurn.setText(String.valueOf(oldTableView.getRotation()));
                break;
        }
    }
}
