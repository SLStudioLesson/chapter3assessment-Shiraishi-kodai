import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.model.Recipe;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        String choice = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            choice = reader.readLine();
            
            if(choice.equals("2")) {
                JSONDataHandler json = new JSONDataHandler();
                RecipeUI recipeUi = new RecipeUI(json);
                recipeUi.displayMenu();
            } else {
                CSVDataHandler csv = new CSVDataHandler();
                RecipeUI recipeUi = new RecipeUI(csv);
                recipeUi.displayMenu();
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}