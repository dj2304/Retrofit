package com.example.guoxinyu20181229.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class GreenBean {
@Id(autoincrement = true)
    long id;
    private int pscid;
    private double price;
    private String title;
    private String images;
}
