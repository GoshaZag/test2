package com.example.zagvozkings.test2.utility;

public class Table {

    private Integer id;
    private String name;
    private TypeTable type;
    private Integer width;
    private Integer height;
    private Integer x;
    private Integer y;
    private Float rotate;

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

    public Integer   getId()    { return id;    }
    public String    getName()  { return name;  }
    public TypeTable getType()  { return type;  }
    public Integer   getWidth() { return width; }
    public Integer   getHeight(){ return height;}
    public Float     getRotate(){ return rotate;}
    public Integer   getX()     { return x;     }
    public Integer   getY()     { return y;     }
}
