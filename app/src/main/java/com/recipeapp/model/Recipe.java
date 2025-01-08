package com.recipeapp.model;
import java.util.ArrayList;

import main.java.com.recipeapp.model.Ingredient;

public class Recipe {
    private String name; //レシピ名前
    private ArrayList<Ingredient> ingredients; //材料リスト

    //コンストラクタ
    public Recipe(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    //メソッド
    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
