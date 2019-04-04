package com.chatRobot.model;

/**
 * Created by wanjiayuan on 2018/3/6.
 */
public class User {
    private int id;
    private int name;
    private int city;

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public int getOthers2() {
        return others2;
    }

    public void setOthers2(int others2) {
        this.others2 = others2;
    }

    private int others;

    private int others2;

    public User() {
    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }
}
