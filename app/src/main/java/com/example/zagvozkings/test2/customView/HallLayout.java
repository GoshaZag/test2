package com.example.zagvozkings.test2.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zagvozkings.test2.storage.Table;

public class HallLayout extends RelativeLayout{

    private final int DEFAULT_HALL_WIDTH = 680;
    private final int DEFAULT_HALL_HEIGHT = 680;

    private Integer WIDTH = null;
    private Integer HEIGHT = null;

    private double scale = 1;
    private OnClickListener onClickListener;
    private OnLongClickListener onLongClickListener;

    public HallLayout(Context context) {
        super(context);
        init();
    }

    public HallLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        post(new Runnable() {
            @Override
            public void run() {
                //измеряем зал, если темно и нет рулетки, то используем размеры по умолчанию
                if (WIDTH == null) WIDTH = DEFAULT_HALL_WIDTH;
                if (HEIGHT == null) HEIGHT = DEFAULT_HALL_HEIGHT;

                double tempW = WIDTH / (double) getWidth();
                double tempH = HEIGHT / (double) getHeight();
                scale = Math.max(tempW, tempH);
                //откорректируем зал
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        (int) (WIDTH / scale),
                        (int) (HEIGHT / scale));
                setLayoutParams(lp);
            }
        });
    }

    public void setSize(Integer WIDTH, Integer HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void setTable(Table table) {
        final CustomTableLayout tableView = new CustomTableLayout(getContext());
        tableView.setTable(table);
        addView(tableView);
    }
    public void changeTable(Table table) {
        if (table == null) return;
        View view = findViewById(table.getId());
        if (view == null) return;

        removeView(view);
        setTable(table);
    }

    public void setTableOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public void setTableOnLongClickListener(OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }



    //собственно сам стол
    private class CustomTableLayout extends RelativeLayout{

        private int height;
        private int width;

        public CustomTableLayout(Context context) {
            super(context);
        }

        public void setTable(final Table table) {
            this.height = table.getHeight();
            this.width = table.getWidth();
            setId(table.getId());
            //поворот
            setRotation(table.getRotate());
            //название
            //к верх ногами и по диагонали читать не удобно
            TextView tableText = new TextView(getContext());
            tableText.setGravity(Gravity.CENTER);
            tableText.setText(table.getName());
            tableText.setRotation(-table.getRotate());
            addView(tableText);
            //фон стола
            //хочешь круглы, хочешь квадратный, хочешь восмерки
            //можно красивый стол, а можно и сращный, весь покарябаный и закленый жвачками
            setBackgroundResource(table.getResId());
            setGravity(Gravity.CENTER);
            //Нужно тыкать? тыкай!
            setOnLongClickListener(onLongClickListener);
            setOnClickListener(onClickListener);

            //длина и ширина, отступы
            post(new Runnable() {
                @Override
                public void run() {
                    RelativeLayout.LayoutParams lpv = new RelativeLayout.LayoutParams(
                            (int) (width / scale),
                            (int) (height / scale));
                    lpv.setMargins(
                            (int) (table.getX() / scale),
                            (int) (table.getY() / scale), 0, 0);
                    measure((int) (width / scale), (int) (height / scale));
                    setLayoutParams(lpv);
                }
            });
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension((int) (width / scale), (int) (height / scale));
        }
    }
}
