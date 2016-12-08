package com.qbgg.cenglaicengqu.homepage.model;

/**
 * 附近的厨房实体类
 */

public class KitchenBean {
    private String kitchenTitle;
    private String number_people;
    private String kitchenername;
    private String kitchenerlocation;
    private String distance;//距离
    private String dinnertime;//饭局时间 早餐中餐 晚餐
    private String kitchenstlye;//饭局特色
    private String kitchenimage;
    private boolean KitchenBeanlike;//喜欢的饭局放大图片

    public KitchenBean() {
    }

    public boolean isKitchenBeanlike() {
        return KitchenBeanlike;
    }

    public void setKitchenBeanlike(boolean kitchenBeanlike) {
        KitchenBeanlike = kitchenBeanlike;
    }

    public String getKitchenTitle() {
        return kitchenTitle;
    }

    public void setKitchenTitle(String kitchenTitle) {
        this.kitchenTitle = kitchenTitle;
    }

    public String getKitchenimage() {
        return kitchenimage;
    }

    public void setKitchenimage(String kitchenimage) {
        this.kitchenimage = kitchenimage;
    }

    public String getKitchenstlye() {
        return kitchenstlye;
    }

    public void setKitchenstlye(String kitchenstlye) {
        this.kitchenstlye = kitchenstlye;
    }

    public String getDinnertime() {
        return dinnertime;
    }

    public void setDinnertime(String dinnertime) {
        this.dinnertime = dinnertime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getKitchenerlocation() {
        return kitchenerlocation;
    }

    public void setKitchenerlocation(String kitchenerlocation) {
        this.kitchenerlocation = kitchenerlocation;
    }

    public String getKitchenername() {
        return kitchenername;
    }

    public void setKitchenername(String kitchenername) {
        this.kitchenername = kitchenername;
    }

    public String getNumber_people() {
        return number_people;
    }

    public void setNumber_people(String number_people) {
        this.number_people = number_people;
    }
}
