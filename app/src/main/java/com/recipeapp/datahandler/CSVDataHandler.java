//package com.recipeapp.datahandler;

import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath; //レシピデータ格納

    //コンストラクタ
    public CSVDataHandler() {
        this.filePath = app/src/main/resources/recipes.csv;
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    //メソッド
    @Override
    public String getMode() {
        return "CSV";
    }

    //レシピデータの読み込み
    @Override
    public ArrayList<Recipe> readData() throws IOException {
        return null;
    }

     //指定されたRecipeオブジェクトを追加します。
    @Override
    public void writeData(Recipe recipe) throws IOException {
        //後で定義
    }

    //指定されたキーワードでレシピを検索し、一致するRecipeオブジェクトのリストを返します。
    @Override
    public ArrayList<Recipe> searchData(String keyWord) throws IOException {
        return null;
    }
}
