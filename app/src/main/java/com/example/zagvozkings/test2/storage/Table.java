package com.example.zagvozkings.test2.storage;

public class Table {

    private Integer id, resId, width, height, x, y;
    private String name;
    private Float rotate;

    public Table(Integer id, String name, Integer resId, int width, int height, int x, int y, Float rotate) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotate = rotate;
        this.resId = resId;
    }

    public Integer   getId()    { return id;    }
    public String    getName()  { return name;  }
    public Integer   getResId() { return resId; }
    public Integer   getWidth() { return width; }
    public Integer   getHeight(){ return height;}
    public Float     getRotate(){ return rotate;}
    public Integer   getX()     { return x;     }
    public Integer   getY()     { return y;     }
}
