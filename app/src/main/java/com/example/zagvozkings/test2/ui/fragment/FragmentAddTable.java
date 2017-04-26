package com.example.zagvozkings.test2.ui.fragment;

import android.app.DialogFragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.zagvozkings.test2.R;
import com.example.zagvozkings.test2.storage.TableData_;
import com.example.zagvozkings.test2.ui.fragment.interfaces.AddTableView;
import com.example.zagvozkings.test2.ui.fragment.interfaces.CreateViewPresenter;
import com.example.zagvozkings.test2.storage.Table;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.dialog_add_table)
public class FragmentAddTable extends DialogFragment implements AddTableView{

    @Bean(CreateViewPresenterImp.class)
    protected CreateViewPresenter createViewPre;

    @ViewById
    protected CheckBox TableCicle;
    @ViewById
    protected CheckBox TableRectangle;
    @ViewById
    protected LinearLayout Cicle;
    @ViewById
    protected LinearLayout Rectangle;
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

    private String name;
    private Integer width, height, left, top, id;
    private Float rotation;
    private Boolean cycle, rectangle;

    @AfterViews
    void init() {
        //поставим слушатели
        TableCicle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
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
                if (!isChecked) {
                    Rectangle.setVisibility(View.GONE);
                } else {
                    TableCicle.setChecked(false);
                    Cicle.setVisibility(View.GONE);
                    Rectangle.setVisibility(View.VISIBLE);
                }
            }
        });

        //заполним поля
        if (name != null) TableName.setText(String.valueOf(name));
        if (width != null) FRRadius.setText(String.valueOf(width / 2));
        if (width != null) FRWidth.setText(String.valueOf(width));
        if (height != null) FRHeight.setText(String.valueOf(height));
        if (left != null) FRX.setText(String.valueOf(left));
        if (top != null) FRY.setText(String.valueOf(top));
        if (rotation != null) FRTurn.setText(String.valueOf(rotation));
        if (cycle != null) TableCicle.setChecked(cycle);
        if (rectangle != null) TableRectangle.setChecked(rectangle);
    }

    @Click(R.id.FragmentAddTable)
    protected void add(){
        createViewPre.init(this, id);
        deleteData();
        dismiss();
    }

    public void deleteData() {
        setDataTable(null, null, null, null, null, null, null, 0);
    }

    public void setIDTable(int idTable) {
        Table table = TableData_.getInstance_(getActivity()).getTableForId(idTable);
        if (table == null) return;

        setDataTable(
                table.getName(),
                table.getWidth(),
                table.getHeight(),
                table.getRotate(),
                table.getX(),
                table.getY(),
                table.getId(),
                table.getResId());
    }

    public void setDataTable(String name, Integer width, Integer height, Float rotation, Integer left, Integer top, Integer id, Integer resId) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.left = left;
        this.top = top;
        this.id = id;
        switch (resId){
            case R.drawable.table_cicle:
                cycle = true;
                rectangle = false;
                break;
            case R.drawable.table_rectangle:
                cycle = false;
                rectangle = true;
                break;
            default:
                cycle = null;
                rectangle = null;
                break;
        }
    }
}
