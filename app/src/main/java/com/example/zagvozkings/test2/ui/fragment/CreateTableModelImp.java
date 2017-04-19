package com.example.zagvozkings.test2.ui.fragment;

import android.widget.CheckBox;
import android.widget.EditText;

import com.example.zagvozkings.test2.utility.TypeTable;
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
        String rotate = FRTurn.getText().toString();
        if (rotate.equals("")) return 0f;
        return Float.parseFloat(rotate);
    }

    @Override
    public TypeTable getType() {
        if (TableCicle.isChecked()) return TypeTable.cicle;
        if (TableRectangle.isChecked()) return TypeTable.rectangle;
        return null;
    }
}
