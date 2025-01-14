package com.recipeapp.ui;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                    addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /*
     * 問題
     * 1.DataHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     * 2.IOExceptionを受け取った場合はError reading file: 例外のメッセージとコンソールに表示します
     * 
     * 手順
     * 1.料理情報の取得
     * 1.DataHandlerのreadData()メソッドを呼び出す
     * 2.返り値として、料理情報のリストを受け取る
     * 2. 料理情報の存在確認
     * 1. 取得したリストの要素数を確認する
     * 3. 料理情報が存在しない場合（リストの要素数が0の場合）
     * 1. 空行を出力する
     * 2. `No recipes available.`と出力する
     * 4. 料理情報が存在する場合（リストの要素数が1以上の場合）
     * 1. 空行を出力する
     * 2. `Recipes:`と出力する
     * 3. 取得した各料理情報に対して以下の処理を行う:
     * 1. `----------------------------------`を出力する
     * 2. `Recipe Name: に続けて料理名を出力する
     * 3. `Main Ingredients: と出力する
     * 4. 料理の材料それぞれに対して以下の処理を行う:
     * 1. 材料名を出力する
     * 2. 最後の材料でない場合、`,` を出力する
     * 5. `----------------------------------`を出力する
     */
    private void displayRecipes() {
        try {
            ArrayList<Recipe> recipes = dataHandler.readData();
            if (recipes.isEmpty()) {
                System.out.println();
                System.out.println("No recipes available");
            } else {
                System.out.println();
                System.out.println("Recipes:");

                for (Recipe recipe : recipes) {
                    System.out.println("-----------------------------------");
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.print("Main Ingredients: ");
                    for (Ingredient ingredient : recipe.getIngredients()) {
                        System.out.print(ingredient.getName() + ",");
                    }
                    System.out.println();
                }
                System.out.println("-----------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
    }

    private void addNewRecipe() {
        try {
            // 1. `Adding a new recipe.`と画面に出力する（改行アリ）
            System.out.println("Adding a new recipe.");
            // 2. `Enter recipe name: `と画面に出力する（改行ナシ）
            System.out.print("Enter recipe name:");
            // 3. 文字入力処理を実行し、変数（以下、recipeName）に代入する
            String recipeName = reader.readLine();
            // 4. 後続処理で利用する変数の初期データを用意する
            // 4-1. 材料情報入力用の変数を用意する（以下、ingredientInput）
            String ingredientInput = "";
            // 4-2. 材料オブジェクトのリストを生成する（以下、ingredients）
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            // 5. `Enter ingredients (type 'done' when finished):`と画面に出力する（改行アリ）
            System.out.println("Enter ingredients (type 'done' when finished):");
            // 6. 以下の処理を繰り返す
            while (true) {
                // 6-1. `Ingredient: `と画面に出力する（改行ナシ）
                System.out.print("Ingredient: ");
                // 6-2. 文字入力処理を 実行し、ingredientInputに代入する
                ingredientInput = reader.readLine();
                // 6-3. ingredientInputが `done`であった場合、次の処理をする
                if (ingredientInput.equals("done")) {
                    // 6-3-1. 繰り返しを修了する
                    break;
                }
                // 6-4. Ingredientオブジェクト（以下、ingredient）を生成する
                // 6-4-1. 材料名にはingredientInputを指定する
                Ingredient ingredient = new Ingredient(ingredientInput);
                // 6-5. ingredientをingredientsに追加する
                ingredients.add(ingredient);
            }
            // 7. Recipeオブジェクト(以下、recipe)を生成する
            // 7-1. レシピ名は recipeName を指定する
            // 7-2. 材料リストは ingredientsを指定する
            Recipe recipe = new Recipe(recipeName, ingredients);
            // 8. writeDataに recipeを渡す
            dataHandler.writeData(recipe);
            // 9. `Recipe added successfully.`を画面に出力する（改行アリ）
            System.out.println("Recipe added successfully.");
            // 10. 上記処理のいずれかの中でIOExceptionが発生した場合は次の処理を行う
        } catch (IOException e) {
            // 10-1. `Failed to add new recipe: `を画面に出力する（改行ナシ）
            System.out.print("Failed to add new recipe: ");
            // 10-2. 例外メッセージを出力する（改行アリ）
            System.out.println(e.getMessage());
        }
    }
}