package com.nh.server.entity;

/**
 * @Classname Entity
 * @Description TODO
 * @Date 2020/5/13 11:34 AM
 * @Created by nihui
 */
public class Entity {
    private String name;
    private String clz;


    public Entity() {
    }

    public Entity(String name, String clz) {
        this.name = name;
        this.clz = clz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClz() {
        return clz;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }
}
