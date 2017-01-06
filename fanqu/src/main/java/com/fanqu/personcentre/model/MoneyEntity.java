package com.fanqu.personcentre.model;

/**
 * 充值选择金额
 */

public class MoneyEntity {
    private String money;
    private boolean isSelected;

    public MoneyEntity(String money, boolean isSelected) {
        this.money = money;
        this.isSelected = isSelected;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
