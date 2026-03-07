import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;

public class BankerAlgorithm{
   
  public static void main(String[]args)throws IOException,FileNotFoundException{

  int num_process = 0,num_resources = 0,lineCount = 0;  
  int [] sumColumn, sumRow,resourceVector,availableVector;
  int [] current_available,process_order;
  int index = 0;
  boolean finish[];
  int [][] max_resource_matrix,allocationMatrix, needMatrix;
  boolean isSafe = false;
  String line,filename;
  StringTokenizer lines;
  @SuppressWarnings("resource")
  Scanner input = new Scanner(System.in);
  System.out.print("Input File Name: ");
  filename = input.nextLine();
  FileReader fr = new FileReader(filename);
  BufferedReader br = new BufferedReader(fr);
  line = br.readLine();
  num_process = Integer.parseInt(line);
  line = br.readLine();
  num_resources = Integer.parseInt(line);
        
  max_resource_matrix = new int[num_process][num_resources];
  allocationMatrix = new int[num_process][num_resources];
  needMatrix = new int [num_process][num_resources];
         
  resourceVector = new int[num_resources];
         
  availableVector = new int[num_resources];
      
  current_available = new int[num_resources];
  finish = new boolean[num_process];

  process_order = new int[num_process];
  sumColumn = new int[num_resources];
  sumRow = new int[num_process];
  line = br.readLine();
          
  while(line != null && lineCount < num_process){
  lines = new StringTokenizer(line);
	  if(lines.hasMoreTokens()){
		for(int j = 0; j < num_resources; j++){
		max_resource_matrix[lineCount][j]=Integer.parseInt(lines.nextToken());  
        }  
      }  
       line = br.readLine();
       lineCount++;
  }
  lineCount = 0;
  while(line != null && lineCount < num_process){
	lines = new StringTokenizer(line);
	  if(lines.hasMoreTokens()){
		for(int j = 0; j < num_resources; j++){
		allocationMatrix[lineCount][j] =
		Integer.parseInt(lines.nextToken());
		}
      }
	  line = br.readLine();
	  lineCount++;
  }  
  lines = new StringTokenizer(line);
  while(lines.hasMoreTokens()){
	for(int i = 0; i < num_resources; i++){
	resourceVector[i] = Integer.parseInt(lines.nextToken());
    }
  }  
  br.close();
  fr.close();
  for(int i = 0; i < num_process; i++){
    for(int j = 0; j < num_resources; j++){
    needMatrix[i][j] = max_resource_matrix[i][j];
    }
  }  
  for(int i = 0; i < num_process; i++){
    for(int j = 0; j < num_resources; j++){
    sumColumn[j] += allocationMatrix[i][j];
    sumRow[i] += needMatrix[i][j];
    }
  }
  for(int j = 0; j < num_resources; j++){
  availableVector[j] = resourceVector[j] - sumColumn[j];
  }
  for(int j = 0; j < num_resources; j++){
  current_available[j]=availableVector[j];
  }
  for(int i = 0; i < num_process; i++){
  finish[i] = false;
  }
  boolean unsafe = false;
  do{
    unsafe = false;
    int i = 0;
    for(; i < num_process; i++){
      if ((!finish[i])){
      boolean good = true;  
        for (int j = 0; j < num_resources; j++){  
          if(needMatrix[i][j] > current_available[j]){
          good = false;
          break;
          }
        }
          if (!good)
          continue;
          unsafe = true;
          break;  
       }
     }
     if(unsafe)  {
     finish[i] = true;
       for (int j = 0; j < num_resources; j++){  
       current_available[j] += allocationMatrix[i][j];
       }
       process_order[index++] = i;
     }
   }
   while (unsafe);
     for(int i = 0; i < num_process; i++){
       if(!finish[i]){
       isSafe = false;
       }
       else{
       isSafe = true;
       }
     }
     // Display output
     System.out.println("Process Count: " + num_process);
     System.out.println("Resource Count: " + num_resources + "\n");
    
     //Max need
     System.out.println("Max Need: ");
       for(int i = 0; i < num_process; i++){
         for(int j = 0; j < num_resources; j++){
         System.out.print(max_resource_matrix[i][j] + " ");
         }
         System.out.println();
       }
     //Current alloc
       System.out.println("\nCurrently Allocated: ");
       for(int i = 0; i < num_process; i++){
         for(int j = 0; j < num_resources; j++){
         System.out.print(allocationMatrix[i][j] + " ");
         }
         System.out.println();
       }
     //Resources avail
     System.out.println("\nResources Available: ");
     for(int i = 0; i < num_resources; i++){
     System.out.print(resourceVector[i] + " ");
     }
     System.out.println();
     //Need 
     System.out.println("\nNeed: ");
     for(int i = 0; i < num_process; i++){
       for(int j = 0; j < num_resources; j++){
       System.out.print(needMatrix[i][j] + " ");
       }
       System.out.println();
     }
     //Available
     System.out.println();
     System.out.println("Initial Resources Available: ");
     for(int j = 0; j < num_resources; j++){
       System.out.print(availableVector[j] +" ");
     }
     System.out.println("\n");

     if(isSafe){
       System.out.print("Process Order: ");
       for(int i = 0; i < process_order.length; i++){
         System.out.print((process_order[i]+1) + " ");
       }
       System.out.println();
       System.out.println("System State: Safe");
     }
   else{
   System.out.println("System State: Not Safe ");
   }
  }  
}