import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.InputMismatchException;

public class DemandPagingSimulator {
   static final int MAX_VP = 10;
   static final int MAX_PP = 7;

   public static void main(String[] args) {
       int numOfPhyFrames = readCmdLineArg(args);
       System.out.println("Number of page frames set to "
               + numOfPhyFrames + ".");

       Scanner stdIn = new Scanner(System.in);
       String line; // input from user
       ArrayList<Integer> refString = null;
       MemorySimulator simulator;

       while (true)
       {
           System.out.println();
           System.out.println("Please choose from the following options:");
           System.out.println("0 - Exit");
           System.out.println("1 - Read reference string");
           System.out.println("2 - Generate reference string");
           System.out.println("3 - Display current reference string");
           System.out.println("4 - Simulate FIFO Algorithm");
           System.out.println("5 - Simulate OPT Algorithm");
           System.out.println("6 - Simulate LRU Algorithm");
           System.out.println("7 - Simulate LFU Algorithm");
           System.out.println();

           line = stdIn.next();
           stdIn.nextLine();
           switch (line) {
               case "0":
               System.out.println("Thank you for using the program");
               System.exit(0);
               break;

               case "1":
               refString = readRefString(stdIn);
               stringConfirm(refString);
               break;

               case "2":
               System.out.println("Reference string length?");
               int stringSize = getStringSize(stdIn);
               refString = generateString(stringSize, MAX_VP);
               stringConfirm(refString);
               break;

               case "3":
               if (refString != null) {
                   System.out.print("Current reference string: ");
                   int i;
                   for (i = 0; i < refString.size() - 1; i++) {
                       System.out.print(refString.get(i) + ", ");
                   }
                   System.out.print(refString.get(i));
                   System.out.print(".");
               } else {
                   System.out.println("Error: No reference string entered.");
               }
               break;

               case "4":
               if (rsIsSet(refString)) {
                   // create simulation conditions, run it, and print
                   simulator = new MemorySimulator(refString, numOfPhyFrames, MAX_VP);
                   simulator.generate("FIFO");
                   simulator.printFrameInfo();
               }
               break;

               case "5":
               if (rsIsSet(refString)) {
                   // create simulation conditions, run it, and print
                   simulator = new MemorySimulator(refString, numOfPhyFrames, MAX_VP);
                   simulator.generate("OPT");
                   simulator.printFrameInfo();
               }
               break;

               case "6":
               if (rsIsSet(refString)) {
                   // create simulation conditions, run it, and print
                   simulator = new MemorySimulator(refString, numOfPhyFrames, MAX_VP);
                   simulator.generate("LRU");
                   simulator.printFrameInfo();
               }
               break;

               case "7":
               if (rsIsSet(refString)) {
                   // create simulation conditions, run it, and print
                   simulator = new MemorySimulator(refString, numOfPhyFrames, MAX_VP);
                   simulator.generate("LFU");
                   simulator.printFrameInfo();
               }
               break;

               default:
               break;
           } 
       } 
   } 

   private static int readCmdLineArg(String[] args) {
       if (args.length < 1) {
           System.out.println("Error: Need to pass exactly 1"
                   +"command line argument for number of physical frames.");
           System.exit(-1);
       }
       if (args.length > 1) {
           System.out.println("Warning: Too many command line arguments."
                   +"Every argument after the 1st will be ignored.");
       }
       int n = -1;

       try {
           n = Integer.parseInt(args[0]);
       } catch(NumberFormatException e) {
           System.out.println("Error: Command line argument must be an integer.");
           System.exit(-1);
       }

       if (n < 1 || n > MAX_PP) {
           System.out.println("Error: Must be between 1 and "
                   + (MAX_PP) + " physical frames.");
           System.exit(-1);
       }

       return n;
   }

   static ArrayList<Integer> readRefString(Scanner in) {
       System.out.println("Enter a series of numbers: ");
       ArrayList<Integer> list = new ArrayList<Integer>();
       do {
           String line = in.nextLine();
           Scanner stdInput = new Scanner(line);
           String temp;
           int tempInt = -1;
           boolean isInt;
           while (stdInput.hasNext()) {
               temp = stdInput.next();
               isInt = false;
               try {
                   tempInt = Integer.parseInt(temp);
                   isInt = true;
               } catch (NumberFormatException e) {
                   System.out.println("Warning: Non-integer entered; \""
                           + temp + "\" ignored.");
               }
               if (isInt && (tempInt < 0 || tempInt >= MAX_VP))
               {
                   System.out.println("Warning: Numbers must be between 0 and "
                           + (MAX_VP - 1) + "; \"" + temp + "\" ignored.");
               } else if (isInt) {
                   list.add(tempInt);
               }
           }
           if (list.size() < 1) {
               System.out.println("Error: Reference string must be at least one"
           +"integer (0 to 9). Please try again.");
           }
       } while (list.size() < 1);
       return list;//return reference string
   }

   static int getStringSize(Scanner in) {
       int stringSize = 0;
       while (stringSize < 1) {
           try {
               stringSize = in.nextInt();
           }
           catch (InputMismatchException e) {
               System.out.println("You must enter an integer.");
           }
           in.nextLine();
           if (stringSize < 1) {
               System.out.println("You must enter a positive integer.");
           }
       }
       return stringSize;
   }

   static ArrayList<Integer> generateString(int n, int max) {
       if (n < 1) {
           System.out.println("Error: Cannot create a reference string shorter than 1.");
           return null;
       }
       Random rand = new Random();

       ArrayList<Integer> ar = new ArrayList<Integer>();
       for (int i = 0; i < n; i++) {
           ar.add(rand.nextInt(max));
       }

       ArrayList<Integer> rs = ar;
       return rs;
   }

   static void stringConfirm(ArrayList<Integer> rs) {
       if (rs != null) {
           System.out.print("Valid ref.string: ");
           int i;
           for (i = 0; i < rs.size() - 1; i++) {
               System.out.print(rs.get(i) + ", ");
           }
           System.out.print(rs.get(i));
           System.out.print(".");
       } else {
           System.out.println("Invalid reference string. Please try again.");
       }
   }

   static boolean rsIsSet(ArrayList<Integer> rs) {
       if (rs != null) {
           return true;
       }
       System.out.println("Error: Reference string not yet entered or generated.");
       return false;
   }
}