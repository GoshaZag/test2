package com.example.zagvozkings.test2.customView;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.zagvozkings.test2.R;
import com.example.zagvozkings.test2.storage.TableData_;
import com.example.zagvozkings.test2.utility.Table;
import com.example.zagvozkings.test2.utility.TypeTable;

public class CustomTableView extends AppCompatTextView{

    //по умолчанию
    private Integer idTable;
    private int height = 100;
    private int width = 100;
    public TypeTable type = TypeTable.rectangle;

    public CustomTableView(Context context) {
        super(context);
        init();
    }

    public CustomTableView(Context context, Table table) {
        super(context);
        init();
        setTable(table);
    }
    private void init() {
        //по умолчанию прямоугольник
        setGravity(Gravity.CENTER);
        setBackgroundResource(R.drawable.table_rectangle);
    }

    public void setType(TypeTable type) {
        this.type = type;
        //берем готовый drawable, чтоб проще было заменить на другой
        if (type == TypeTable.cicle){
            setBackgroundResource(R.drawable.table_cicle);
        } else {
            setBackgroundResource(R.drawable.table_rectangle);
        }
    }

    public void setSize(Integer width, Integer height) {
        float scale = TableData_.getInstance_(getContext()).getScale();
        this.width = (int) (width / scale);
        this.height = (int) (height / scale);
    }

    public void setMargin(Integer x, Integer y) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        float scale = TableData_.getInstance_(getContext()).getScale();
        lp.setMargins(
                ((int) (x / scale) - width / 2),
                ((int) (y/ scale) - height / 2),0,0);
        setLayoutParams(lp);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension(width, height);
    }

    public void setTable(Table table) {
        idTable = table.id;
        setText(table.name);
        setType(table.type);
        setSize(table.width, table.height);
        setRotation(table.rotate);
        setMargin(table.x, table.y);
    }

    public Integer getIdTable() {
        return idTable;
    }
}
