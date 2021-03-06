package com.example.zagvozkings.test2.ui.fragment;

import android.widget.CheckBox;
import android.widget.EditText;

import com.example.zagvozkings.test2.R;
import com.example.zagvozkings.test2.ui.fragment.interfaces.CreateTableModel;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

@EBean
public class CreateTableModelImp implements CreateTableModel{

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

    @Override
    public String getName()     {
        return TableName.getText().toString();
    }

    @Override
    public Integer getX()        {
        String rotate = FRX.getText().toString();
        if (rotate.equals("")) return 0;
        return Integer.parseInt(rotate);
    }

    @Override
    public Integer getY()        {
        String rotate = FRY.getText().toString();
        if (rotate.equals("")) return 0;
        return Integer.parseInt(rotate);
    }

    @Override
    public Integer getWidth()    {
        //может быть ничего не введено или непонятно что введено
        try {
            if (TableCicle.isChecked()) {
                return Integer.parseInt(FRRadius.getText().toString()) * 2;
            }
            if (TableRectangle.isChecked()) {
                return Integer.parseInt(FRWidth.getText().toString());
            }
        } catch (Exception e){
            return null;
        }
        return null;
    }

    @Override
    public Integer getHeight()   {
        //может быть ничего не введено или непонятно что введено
        try {
            if (TableCicle.isChecked()) {
                return Integer.parseInt(FRRadius.getText().toString()) * 2;
            }
            if (TableRectangle.isChecked()) {
                return Integer.parseInt(FRHeight.getText().toString());
            }
        } catch (Exception e){
            return null;
        }
        return null;
    }

    @Override
    public Float getRotate()   {
        //ну если определили ширину и высоту, то с остальным не должно быть проблем
        String rotate = FRTurn.getText().toString();
        if (rotate.equals("")) return 0f;
        return Float.parseFloat(rotate);
    }

    @Override
    public Integer getResId() {
        //ну если определили ширину и высоту, то с остальным не должно быть проблем
        if (TableCicle.isChecked()) return R.drawable.table_cicle;
        if (TableRectangle.isChecked()) return R.drawable.table_rectangle;
        return null;
    }
}
