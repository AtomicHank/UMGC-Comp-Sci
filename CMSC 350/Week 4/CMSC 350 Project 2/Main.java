import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

  private static List<Polynomial> polyList = new ArrayList<>();
    //main here
    public static void main(String[] args) {
      processPolyList();
    }
  //===============================================================================================
  // method: fromFile / returns: Arraylist
  // description: Opens a file takes a String and tokenizes it into an ArrayList of of expressions
  //===============================================================================================
  public static ArrayList<String> fromFile() {
    //Create ArrayList and JFileChooser
    ArrayList<String> expressionList = new ArrayList<>();
    JFileChooser chooser = new JFileChooser();
    //Show both directories and files
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    //use current directory for ease
    chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    int response = chooser.showOpenDialog(null);
      if (response == JFileChooser.APPROVE_OPTION){
      File file = chooser.getSelectedFile();
        try {
          Scanner fileIn = new Scanner(file);
            if (file.isFile()){
              while (fileIn.hasNextLine()) {
                String expression = fileIn.nextLine();
                expressionList.add(expression);
              }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error in input/output");
        } 
        finally {
        scanner.close();
        }
      }
  }
}