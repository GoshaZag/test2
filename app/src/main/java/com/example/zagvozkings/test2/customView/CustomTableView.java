package com.example.zagvozkings.test2.customView;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.zagvozkings.test2.R;
import com.example.zagvozkings.test2.utility.TypeTable;

public class CustomTableView extends AppCompatTextView {
    public CustomTableView(Context context) {
        super(context);
        //по умолчанию прямоугольник
        setGravity(Gravity.CENTER);
        setBackgroundResource(R.drawable.table_rectangle);
    }

    //по умолчанию
    private int height = 100;
    private int width = 100;
    public TypeTable type = TypeTable.rectangle;

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
        this.width = width;
        this.height = height;
    }

    public void setMargin(Integer x, Integer y) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(
                (x - width / 2),
                (y - height / 2),0,0);
        setLayoutParams(lp);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension(width, height);
    }
}
