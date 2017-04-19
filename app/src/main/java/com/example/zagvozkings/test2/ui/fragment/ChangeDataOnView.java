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
    protected LinearLayout Cicle1;
    @ViewById
    protected LinearLayout Rectangle1;
    @ViewById
    protected LinearLayout Rectangle2;


    public void init() {

        //поставим слушатели
        TableCicle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    Cicle1.setVisibility(View.GONE);
                } else {
                    TableRectangle.setChecked(false);
                    Rectangle1.setVisibility(View.GONE);
                    Rectangle2.setVisibility(View.GONE);
                    Cicle1.setVisibility(View.VISIBLE);
                }
            }
        });
        TableRectangle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    Rectangle1.setVisibility(View.GONE);
                    Rectangle2.setVisibility(View.GONE);
                } else {
                    TableCicle.setChecked(false);
                    Cicle1.setVisibility(View.GONE);
                    Rectangle1.setVisibility(View.VISIBLE);
                    Rectangle2.setVisibility(View.VISIBLE);
                }
            }
        });

        //проверим, создаем новое или редактируем старое
        CustomTableView oldTableView = tableData.getChangeTable();
        if (oldTableView == null) return;
        TableName.setText(oldTableView.getText());

        FRX.setText(String.valueOf(oldTableView.getLeft() + oldTableView.getWidth() / 2));
        FRY.setText(String.valueOf(oldTableView.getTop() + oldTableView.getHeight() / 2));

        TypeTable typeTable = oldTableView.type;
        switch (typeTable){
            case cicle:
                TableCicle.setChecked(true);
                FRRadius.setText(String.valueOf(oldTableView.getWidth() / 2));
                break;
            case rectangle:
                TableRectangle.setChecked(true);
                FRWidth.setText(String.valueOf(oldTableView.getWidth()));
                FRHeight.setText(String.valueOf(oldTableView.getHeight()));
                FRTurn.setText(String.valueOf(oldTableView.getRotation()));
                break;
        }
    }
}
