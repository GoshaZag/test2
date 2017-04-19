package com.example.zagvozkings.test2.utility;

public class Table {

    public Integer id;
    public String name;
    public TypeTable type;
    public Integer width;
    public Integer height;
    public Integer x;
    public Integer y;
    public Float rotate;

    public Table(Integer id, String name, TypeTable type, int width, int height, int x, int y, Float rotate) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotate = rotate;
        this.type = type;
    }
}
