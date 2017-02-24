package com.fanqu.main.model;

/**
 * 我的资产类
 */

public class AssetBean {
    /**
     * balance : 6788.850
     * buy_balance : 0.000
     * commission_balance : 43.000
     * total_score : 90.34
     * expend_srcoe : 0.00
     */

    private String balance;
    private String buy_balance;
    private String commission_balance;
    private String total_score;
    private String expend_srcoe;

    public AssetBean() {
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBuy_balance() {
        return buy_balance;
    }

    public void setBuy_balance(String buy_balance) {
        this.buy_balance = buy_balance;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getExpend_srcoe() {
        return expend_srcoe;
    }

    public void setExpend_srcoe(String expend_srcoe) {
        this.expend_srcoe = expend_srcoe;
    }

    public String getCommission_balance() {
        return commission_balance;
    }

    public void setCommission_balance(String commission_balance) {
        this.commission_balance = commission_balance;
    }
}
