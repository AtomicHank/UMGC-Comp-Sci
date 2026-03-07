//Joe Merrill
//CMSC 350 Project 2
//5Feb21
//Main class

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

  private static List<Polynomial> polyList = new ArrayList<>();
  public static void main(String[] args) {
  processPolyList();
  }
  public static ArrayList<String> fromFile() {
  ArrayList<String> expressionList = new ArrayList<>();
  JFileChooser chooser = new JFileChooser();
  chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
  chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    int response = chooser.showOpenDialog(null);
      if (response == JFileChooser.APPROVE_OPTION){
      File file = chooser.getSelectedFile();
        try {
        Scanner fileIn = new Scanner(file);
          if (file.isFile()){
            while (fileIn.hasNextLine()){
            String expression = fileIn.nextLine();
            expressionList.add(expression);
            }
          }
        }
        catch (NoSuchElementException nse){
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"File has no content");
        }
        catch(FileNotFoundException fne){
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"File not found");
        }
      }
      return expressionList;
  }
  
  public static boolean checkWeakOrder( List<Polynomial> polyList){
    boolean isWeakOrder = true;
    Polynomial previous = polyList.get(polyList.size()-1);
      for(int i = polyList.size()-2; i > 0; i--){
        if (previous.compareExponents(polyList.get(i)) < 0){
        isWeakOrder = false;
        }
      }
      return isWeakOrder;
  }
  public static void processPolyList(){
    try {
    ArrayList<String> a = fromFile();
      for (String element : a) {
      Polynomial p = new Polynomial(element);
      System.out.println(p);
      polyList.add(p);
      }
    }
    catch (InvalidPolynomialSyntax ex){
    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getMessage());
    }
    System.out.println("Strong Order: " + OrderedList.checkSorted(polyList));
    System.out.println("Weak Order: " + checkWeakOrder(polyList));
  }
}