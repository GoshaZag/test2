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

    private float scale = 1;
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

                float tempW = WIDTH / (float) getWidth();
                float tempH = HEIGHT / (float) getHeight();
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
        setTable(
                table.getId(),
                table.getName(),
                table.getResId(),
                table.getWidth(),
                table.getHeight(),
                table.getX(),
                table.getY(),
                table.getRotate()
        );
    }
    public void changeTable(Table table) {
        if (table == null) return;
        View view = findViewById(table.getId());
        if (view == null) return;

        removeView(view);
        setTable(table);
    }

    public void setTable(Integer id, String name, Integer resId, final int width, final int height, final int x, final int y, Float rotate){
        final RelativeLayout table = new RelativeLayout(getContext());
        //зададим id
        table.setId(id);
        //поворот
        table.setRotation(rotate);
        //название
        //к верх ногами и по диагонали читать не удобно
        TextView tableText = new TextView(getContext());
        tableText.setGravity(Gravity.CENTER);
        tableText.setText(name);
        tableText.setRotation(-rotate);
        table.addView(tableText);
        //фон стола
        //хочешь круглы, хочешь квадратный, хочешь восмерки
        //можно красивый стол, а можно и сращный, весь покарябаный и закленый жвачками
        table.setBackgroundResource(resId);
        table.setGravity(Gravity.CENTER);
        //Нужно тыкать? тыкай!
        table.setOnLongClickListener(onLongClickListener);
        table.setOnClickListener(onClickListener);
        addView(table);

        //длина и ширина, отступы
        table.post(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams lpv = new RelativeLayout.LayoutParams(
                        (int) (width / scale),
                        (int) (height / scale));
                lpv.setMargins(
                        (int) (x / scale),
                        (int) (y / scale), 0, 0);
                table.setLayoutParams(lpv);
            }
        });
    }

    public void setTableOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public void setTableOnLongClickListener(OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }

}
