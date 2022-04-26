package com.example.fight1;

import java.util.ArrayList;

public class Root{
    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Coord coord;
    public ArrayList<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public int dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;
}

class Coord{
    public double lon;
    public double lat;
}

class Weather{
    public int id;
    public String main;
    public String description;
    public String icon;
}

class Main{
    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public int pressure;
    public int humidity;
}

class Wind{
    public double speed;
    public int deg;
}

class Clouds{
    public int all;
}

class Sys{
    public int type;
    public int id;
    public String country;
    public int sunrise;
    public int sunset;
}


