import java.util.ArrayList;
import java.util.Scanner;

public class MemorySimulator
{
   ArrayList<Integer> rfString; 
   int[] rmPages; 
   int[] pageCalled; 
   boolean[] pageFaults; 
   int rsLen; 
   int numOfPhyFrames;
   int numOfVirPages;

   int[][] phyMemory;
   PhysicalFrame[] virtualFrames;
   String typeAlg;

   MemorySimulator(ArrayList<Integer> refs, int p, int v)
   {
       rfString = refs;
       rsLen = rfString.size();
       rmPages = new int[rsLen];
       pageCalled = new int[rsLen];
       numOfPhyFrames = p;
       numOfVirPages = v;
       phyMemory = new int[rfString.size()][p];
       virtualFrames = new PhysicalFrame[v];
       pageFaults = new boolean[rsLen];
   }

   void generate(String alg) {
       initialize();
       typeAlg = alg;
       int currentFrame = 0;
       int insertFrame;
       int empty;
       int replaceFrame;
       int[] listOfFrames;
       int inMemory;
       while (currentFrame < rsLen) {
           insertFrame = rfString.get(currentFrame);
           if (alg == "LRU") {
               virtualFrames[insertFrame].setLastUse(currentFrame);
           } else if (alg == "LFU") {
               virtualFrames[insertFrame].incrementTimesUsed();
           }
           empty = findIndex(phyMemory[currentFrame], -1);
           inMemory = findIndex(phyMemory[currentFrame], insertFrame);
           if (inMemory != -1) {
               pageCalled[currentFrame] = inMemory;
               // no page fault!
               pageFaults[currentFrame] = false;
           }
           else if (empty >= 0) {
               pageCalled[currentFrame] = empty;
               phyMemory[currentFrame][empty] = insertFrame;
               virtualFrames[insertFrame].setInserted(currentFrame);
           }
           else {
               switch (alg) {
                   case "FIFO":
                   replaceFrame = findOldest(phyMemory[currentFrame]);
                   virtualFrames[insertFrame].setInserted(currentFrame);
                   break;
                   case "OPT":
                   calculateNextUses(currentFrame);
                   replaceFrame = findLeastOptimal(phyMemory[currentFrame]);
                   break;
                   case "LFU":
                   replaceFrame = findLfu(phyMemory[currentFrame]);
                   break;
                   case "LRU":
                   replaceFrame = findLru(phyMemory[currentFrame]);
                   break;
                   default:
                   System.out.println("Error: algorithm not recognized!");
                   return;
               }
               rmPages[currentFrame] = phyMemory[currentFrame][replaceFrame];
               pageCalled[currentFrame] = replaceFrame;
               phyMemory[currentFrame][replaceFrame] = insertFrame;
           }
           if ((currentFrame + 1) < rsLen) {
               for (int i = 0; i < numOfPhyFrames; i ++) {
                   phyMemory[currentFrame +1][i] = phyMemory[currentFrame][i];
               }
           }
           currentFrame += 1;
       }
   }

   int findOldest(int[] a) {
       int oldest = virtualFrames[a[0]].getInserted();
       int oldestIndex = 0;
       int checking;
       for (int i = 1; i < a.length; i++) {
           checking = virtualFrames[a[i]].getInserted();
           if (checking < oldest) {
               oldest = checking;
               oldestIndex = i;
           }
       }
       return oldestIndex;
   }

   int findLfu(int[] a) {
       int lfuIndex = 0;
       int lfuTimesUsed = virtualFrames[a[lfuIndex]].getTimesUsed();

       for (int i = 1; i < a.length; i++) {
           int temp = a[i];
           int tempTimesUsed = virtualFrames[a[i]].getTimesUsed();

           if (tempTimesUsed < lfuTimesUsed) {
               lfuIndex = i;
               lfuTimesUsed = tempTimesUsed;
           }
       }
       return lfuIndex;
   }
   int findLru(int[] a) {
       int lruIndex = 0;
       int lruLastUse = virtualFrames[a[lruIndex]].getLastUse();

       for (int i = 1; i < a.length; i++) {
           int temp = a[i];
           int tempLastUse = virtualFrames[a[i]].getLastUse();

           if (tempLastUse < lruLastUse) {
               lruIndex = i;
               lruLastUse = tempLastUse;
           }
       }
       return lruIndex;
   }

   int findLeastOptimal(int[] a) {
       int leastOptimal = a[0];
       int index = 0;
       int leastOptNextUse = virtualFrames[leastOptimal].getNextUse();
       for (int i = 1; i < a.length; i++) {
           int temp = a[i];
           int tempNextUse = virtualFrames[temp].getNextUse();
           if (tempNextUse > leastOptNextUse)
           {
               leastOptimal = temp;
               leastOptNextUse = virtualFrames[leastOptimal].getNextUse();
               index = i;
           }
       }
       return index;
   }

  
   void calculateNextUses(int n)
   {      
       for (int i = 0; i < numOfVirPages; i++) {
           virtualFrames[i].setNextUse(rsLen + 1);
       }
       for (int i = rsLen - 1; i >= n; i--) {
           int called = rfString.get(i);
           virtualFrames[called].setNextUse(i);
       }
   }

   void initialize() {
       for (int i = 0; i < pageFaults.length; i++)
           pageFaults[i] = true;
       for (int i = 0; i < rmPages.length; i++)
           rmPages[i] = -1;
       for (int i = 0; i < pageCalled.length; i++)
           pageCalled[i] = -1;  
       for (int i = 0; i < numOfVirPages; i++)
           virtualFrames[i] = new PhysicalFrame(i);
       for (int i = 0; i < rsLen; i++)
           for (int j = 0; j < numOfPhyFrames; j ++)
               phyMemory[i][j] = -1;
      
       typeAlg = "";
   }

   void printFrameInfo() {
       System.out.println("Memory Information: ");
       System.out.println("Algorithm Type: " + typeAlg);
       System.out.println("Length of Refernce String: " + rsLen);
       System.out.println("Number of Virtual Pages: " + numOfVirPages);
       System.out.println("Number of Physical Pages: " + numOfPhyFrames);
       System.out.println("---");
       System.out.println("Press enter to step through snapshots of physical memory");
       System.out.println("Or, enter \"q\" at any time to return to main menu.");

       Scanner sc = new Scanner(System.in);
       int steppingSlice = 0;
       String prompt;
       int frameNum;
       int removedInt;
       while (steppingSlice < rsLen) {
           prompt = sc.nextLine();
           if (prompt.equals("q")) {
               System.out.println("Exitting printout.");
               break;
           }
           System.out.println("Snapshot at step " + (steppingSlice + 1) + ":");
           System.out.println("Program called virtual frame # "
                           + rfString.get(steppingSlice));
           for (int i = 0; i < numOfPhyFrames; i ++) {
               System.out.print("Physical frame " + i + ":");
               frameNum = phyMemory[steppingSlice][i];
               if (frameNum >= 0) {
                   if (i == pageCalled[steppingSlice]) {
                       System.out.println("[" + frameNum + "]");
                   } else {
                       System.out.println(" " + frameNum);
                   }
               } else {
                   System.out.println("x");
               }
           }
           removedInt = rmPages[steppingSlice];
           System.out.println("Page faults: " + (pageFaults[steppingSlice] ? "Yes." : "No."));
           System.out.println("Victim frames: " + (removedInt == -1 ? "None." : removedInt));
           steppingSlice += 1;
       }
       System.out.print("Simluation finished. Press enter to continue.");
       sc.nextLine();
   }

   int findIndex(int[] a, int n) {
       for (int i = 0; i < a.length; i++) {
           if (a[i] == n) {
               return i;
           }
       }
       return -1;
   }
}