package com.fanqu.dinner.modle;

/**
 * 菜品集合实体类
 */

public class DishesEntity {
    private String dish_name;//菜名
    private boolean isspecialty;//是否是招牌菜

    public DishesEntity() {
    }

    public DishesEntity(String dish_name, boolean isspecialty) {
        this.dish_name = dish_name;
        this.isspecialty = isspecialty;
    }


    @Override
    public String toString() {
        return "DishesEntity{" +
                "dish_name='" + dish_name + '\'' +
                ", isspecialty=" + isspecialty +
                '}';
    }

    public boolean isspecialty() {
        return isspecialty;
    }

    public void setIsspecialty(boolean isspecialty) {
        this.isspecialty = isspecialty;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }
}
