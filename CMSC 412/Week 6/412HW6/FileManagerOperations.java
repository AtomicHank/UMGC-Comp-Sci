import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManagerOperations{

  private String currentWorkingDirectory;
  private File currentWorkingFile;
  private boolean exit;

  public FileManagerOperations(){
         currentWorkingDirectory = "";
         exit = false;
  }
  
  public void menuSelection(){
  System.out.println("\t\tMAIN MENU");
  System.out.println("\t0) Exit");
  System.out.println("\t1) Select Directory");
  System.out.println("\t2) List Directory Content (First Level)");
  System.out.println("\t3) List Directory Content (All Levels)");
  System.out.println("\t4) Delete File");
  System.out.println("\t5) Display Files (Hexadecimal View)");
  System.out.println("\t6) Encrypt Files (XOR with Password)");
  System.out.println("\t7) Decrypt Files (XOR with Password)\n\n\n");
  }
  
  public void exit(){
    this.exit = true;
  }
  
  private void selectDirectory(){
  Scanner sc = new Scanner(System.in);
  System.out.print("Enter the absolute path of the directory:\n");
    this.currentWorkingDirectory = sc.next();
    currentWorkingFile = new File(currentWorkingDirectory);
      while(!(currentWorkingFile.exists() && currentWorkingFile.isDirectory())){
      System.out.print("\n\""+currentWorkingDirectory+"\"Does not exist:\n");
      System.out.print("Enter the absolute path of the directory:\n");
      sc = new Scanner(System.in);
        this.currentWorkingDirectory = sc.next();
        currentWorkingFile = new File(currentWorkingDirectory);
      }
      System.out.println("Your Current Working Directory is: \'"+ currentWorkingDirectory+"\'");
  }
  
  private String[] listFirstLevelOneFiles(){
  String[] fileList;
    int fileIndex = 0;
    File nextLevelFile;
    fileList = currentWorkingFile.list();
    System.out.println("List of files in "+ currentWorkingDirectory + "\n");
      while(fileIndex < fileList.length){
      nextLevelFile = new File (currentWorkingFile+"/"+fileList[fileIndex]);
        if(nextLevelFile.isDirectory()){
        System.out.println((fileIndex+1)+") "+fileList[fileIndex]+" | Dir ");
        }
        else{
        System.out.println((fileIndex+1)+") "+fileList[fileIndex]);
        }
        fileIndex++;
      }
    return fileList;
  }
  
  private void listAllFilesRecursively(File file){
  String[] fileList;
    int fileIndex = 0;
    File nextLevelFile;
    fileList = file.list();
      while(fileIndex < fileList.length){
      nextLevelFile = new File(file.getAbsoluteFile().toString() +"/"+fileList[fileIndex]);
      if(nextLevelFile.isDirectory()){
      System.out.println((fileIndex+1)+") "+fileList[fileIndex]+" | Dir |Parent "+ nextLevelFile.getParentFile());
      }
      else{
      System.out.println((fileIndex+1)+") "+fileList[fileIndex]+" | Dir |Parent "+ nextLevelFile.getParentFile());
      }
      if(nextLevelFile.isDirectory()){
      listAllFilesRecursively(nextLevelFile);
      }
      fileIndex++;
      }
      System.out.println("");
  }
  
  private void deleteFile(){
  System.out.print("\nEnter the name of the file you want to delete: ");
  @SuppressWarnings("resource")
  String fileName = new Scanner(System.in).next();
  File fileToDelete = new File
  (currentWorkingFile.getAbsoluteFile()+"/"+fileName);
    if(!fileToDelete.exists()){
    System.out.println("***[ERROR: The file you entered "+ "does not exist]***"); return;
    }
    fileToDelete.delete();
    System.out.println("You have successfully deleted the following file: \'"+fileName);
  }
  
  private void displayFileInHexFormat() throws IOException{
  System.out.print("\nEnter the name of the file you want to display: ");
  @SuppressWarnings("resource")
  String fileName = new Scanner(System.in).next();
  BufferedReader bf = null;
  File fileToDisplay = new File(currentWorkingFile.getAbsoluteFile()+"/"+fileName);
  if(!fileToDisplay.exists()){
  System.out.println("***[ERROR: The file you entered "+ "does not exist]***"); 
  }
  if(currentWorkingFile.isDirectory()){
  System.out.println("The file you selected is a Directory \n");
  }
  try{
  System.out.println("Hexadecimal display of: \'"+fileName+"\'\n");
  bf = new BufferedReader(new FileReader(fileToDisplay));
  int count= 0;
  int charCode;
    while(bf.ready()){
    charCode = bf.read();
    System.out.print(" "+String.format("%x", charCode));
    if(count==30){
    System.out.print("\n");
    count=0;
    }
    count++;
    }
    bf.close();
  }
  catch (FileNotFoundException ex){
  Logger.getLogger(FileManagerOperations.
    class.getName()).log(Level.SEVERE, null, ex);
  }
  catch (IOException ex){
  Logger.getLogger(FileManagerOperations.
    class.getName()).log(Level.SEVERE, null, ex);
  }
  finally{
  bf.close();
  }
  }
  
  private void encryptFile() throws InputMismatchException, IOException{
  String nameOfFile = "";
  System.out.print("\nEnter the name of the file you want to Encrypt: ");
  String fileName = new Scanner(System.in).next();
  File fileToEncrypt = new File(this.currentWorkingDirectory+"/"+fileName);
    if(!fileToEncrypt.exists()){
    System.out.println("***[ERROR: The file you entered "+ "does not exist]***");
    }
    System.out.print("\nEnter Password: ");
    @SuppressWarnings("resource")
    String password = new Scanner(System.in).next();
    BufferedReader bf = null;
    BufferedWriter bw = null;
    File encryptedFile = new File(this.currentWorkingDirectory+"/"+fileName+"_encrypted.txt");
      if(fileToEncrypt.isDirectory()){
      System.out.println("The file you selected is a Directory");
        return;
      }
      try{
      bf = new BufferedReader(new FileReader(fileToEncrypt));
      bw = new BufferedWriter(new FileWriter(encryptedFile));
      int passwordIndex = 0;
      char charCode;
      char encryptChar;
        while(bf.ready()){
          if(passwordIndex==password.length()){
          passwordIndex = 0;
          }
        charCode = (char)bf.read();
        encryptChar=(char)(password.charAt(passwordIndex)
        ^(char)charCode);
        bw.append(encryptChar);
        passwordIndex++;
        }
        bf.close();
      }
      catch (FileNotFoundException ex){
      Logger.getLogger(FileManagerOperations.
        class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (IOException ex){
      Logger.getLogger(FileManagerOperations.
        class.getName()).log(Level.SEVERE, null, ex);
      }
      finally{
      bw.flush();
      bf.close();
      bw.close();
      fileToEncrypt.delete();
      }
  }
  private void decrypteFile() throws IOException{
  String pathOfFile;
  BufferedReader bf = null;
  BufferedWriter bw = null;
  System.out.print("\nEnter the name of the file you want to decrypt: ");
  String fileName = new Scanner(System.in).next();
  pathOfFile = this.currentWorkingDirectory+"/"+fileName;
  File fileTDeEncrypt = new File(pathOfFile);
    if(!fileTDeEncrypt.exists()){
    System.out.println("***[ERROR: The file you entered "+ "does not exist]***");
    return;
    }
    System.out.print("\nEnter Password: ");
    String password = new Scanner(System.in).next();
    pathOfFile = pathOfFile.replaceAll("(_encrypted.txt)", "");
    File encryptedFile = new File(pathOfFile);
    try{
    bf = new BufferedReader(new FileReader(fileTDeEncrypt));
    bw = new BufferedWriter(new FileWriter(encryptedFile));
    int passwordIndex = 0;
    char charCode;
    char encryptChar;
      while(bf.ready()){
        if(passwordIndex==password.length()){
        passwordIndex = 0;
        }
        charCode = (char)bf.read();
        encryptChar = (char) (password.charAt(passwordIndex)
        ^ (char)charCode);
        bw.append(encryptChar);
        passwordIndex++;
      }
      bf.close();
    }
    catch (FileNotFoundException ex){
    Logger.getLogger(FileManagerOperations.class.getName())
    .log(Level.SEVERE, null, ex);
    }
    catch (IOException ex){
    Logger.getLogger(FileManagerOperations.class.getName())
    .log(Level.SEVERE, null, ex);
    }
    finally{
    bw.flush();
    bf.close();
    bw.close();
    fileTDeEncrypt.delete();
    }
  }
  
  public void runFileManager() throws IOException{
    while(exit==false){
    menuSelection();
    System.out.print("Make a selection from the menu: ");
    String menuSelection = new Scanner(System.in).next();
      if(this.currentWorkingFile != null ||
      menuSelection.equals("1") || menuSelection.equals("0")){
        switch(menuSelection){
        case "0":
        this.exit();
        break;
        case "1":
        this.selectDirectory();
        break;
        case "2":
          try{
          this.listFirstLevelOneFiles();
          }
          catch(NullPointerException ex){
          }
         break;
         case "3":
           try{
           this.listAllFilesRecursively(currentWorkingFile);
           }
           catch(NullPointerException ex){
           }
        break;
        case "4":
          try{
          this.deleteFile();
          }
          catch (InputMismatchException | ArrayIndexOutOfBoundsException ex ){
          System.out.println("ERROR: You Entered an Invalid "+ "Index. Try again");
            this.deleteFile();
          }
        break;
        case "5":
          try{
          this.displayFileInHexFormat();
          }
          catch (InputMismatchException | FileNotFoundException| ArrayIndexOutOfBoundsException ex ){
          System.out.println("ERROR: You Entered an Invalid " + "Index. Try again");
            this.displayFileInHexFormat();
          }
        break;
        case "6":
          try{
          this.encryptFile();
          }
          catch (InputMismatchException|FileNotFoundException| ArrayIndexOutOfBoundsException ex ){
          System.out.println("ERROR: You Entered an Invalid " + "Index. Try again");
          this.encryptFile();
          }
        break;
        case "7":
          try {
          this.decrypteFile();
          }
          catch (InputMismatchException | FileNotFoundException|ArrayIndexOutOfBoundsException ex ){
          System.out.println("ERROR: You Entered an Invalid " + "Index. Try again");
          this.decrypteFile();
          }
        break;
        default:
        System.out.println("***[Error: Invalid menu "+ "selection]***"); 
        break;
        }
        if(!menuSelection.equalsIgnoreCase("0")){
        System.out.print("\n\nPress 'Enter' To Continue: ");
        new Scanner(System.in).nextLine();
        }
      }
      else{
      System.out.println("Choose a current working directory ");
      }
    }
    System.out.println("Ending Program");
  }
  public static void main(String[] args) throws IOException{
  FileManagerOperations fileObject = new FileManagerOperations();
  fileObject.runFileManager();
  }
}