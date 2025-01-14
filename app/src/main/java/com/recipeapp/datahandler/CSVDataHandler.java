package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath; //レシピデータ格納

    //コンストラクタ
    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    //メソッド
    @Override
    public String getMode() {
        return "CSV";
    }

    /*1. 料理情報リストの初期化
    1. 空の料理情報リストを作成する
    2. ファイルの読み込み
        1. `filePath`で指定されたファイルを開く
        2. ファイルの各行に対して以下の処理を行う
            1. 最初のカンマを基準に行をカンマで分割する
            2. 分割した最初の要素を料理名とする
            3. 残りの要素を材料リストとする
            4. 料理名と材料リストを使用して新しい料理情報を作成する
            5. 作成した料理情報を料理情報リストに追加する
    3. 結果の返却
        1. 料理情報リストを返す
         */
    @Override
    public ArrayList<Recipe> readData() throws IOException {
        //recipe型のArrayListを作成
        ArrayList<Recipe> recipes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) { //ファイルの読み込み
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",",2);
                String recipeName = parts[0];
                ArrayList<Ingredient> recipeIngredients = new ArrayList<>();
                String[] parts2 = parts[1].split(",");
                for (String part2 : parts2) {
                    Ingredient recipIngredient = new Ingredient(part2);
                    recipeIngredients.add(recipIngredient);
                }
                Recipe recipe = new Recipe(recipeName, recipeIngredients);
                recipes.add(recipe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipes;
    }

     //指定されたRecipeオブジェクトを追加します。

     //新しいレシピをrecipes.csvに追加します。
    /*レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。

    手順
    1. ファイルを読み込む
    2. recipeの名前を書き込む
    3. recipeより材料情報(以下、ingredients)を取得する
    4. ingredientsのデータ件数分繰り返し、次の処理を行う
        4-1. カンマを書き込む
        4-2. 要素(以下、Ingredientオブジェクト)を1件取得する
        4-3. Ingredientオブジェクトより材料名を取得する
        4-4. 材料名を書き込む
    5. 改行を書き込む
     */
    
    @Override
    public void writeData(Recipe recipe) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            writer.write(recipe.getName());
            // 3. recipeより材料情報(以下、ingredients)を取得する
            ArrayList<Ingredient> zairyos = recipe.getIngredients();
            // 4. ingredientsのデータ件数分繰り返し、次の処理を行う
            for (Ingredient zairyo : zairyos) { //4-2. 要素(以下、Ingredientオブジェクト)を1件取得する
                //4-1. カンマを書き込む
                writer.write(",");
                //4-3. Ingredientオブジェクトより材料名を取得する
                String ingredientName = zairyo.getName();
                //4-4. 材料名を書き込む
                writer.write(ingredientName);
            }
            writer.newLine(); //5. 改行を書き込む
        }
    }

    //指定されたキーワードでレシピを検索し、一致するRecipeオブジェクトのリストを返します。
    @Override
    public ArrayList<Recipe> searchData(String keyWord) throws IOException {
        return null;
    }
}
