package com.recipeapp.model;

public class Ingredient {
    private String name; //材料の名前

    //コンストラクタ
    public Ingredient(String name) {
        this.name = name;
    }

    //メソッド
    public String getName() {
        return name;
    }
}
