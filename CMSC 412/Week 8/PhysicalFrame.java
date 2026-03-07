public class PhysicalFrame
{
   int id;
   int inserted;
   int next;
   int last;
   int timesUsed;

   PhysicalFrame(int n) {
       id = n;
       inserted = -1;
       next = -1;
       last = -1;
       timesUsed = 0;
   }
   void setNum(int n) {
       id = n;
   }
   int getNum() {
       return id;
   }
   void setInserted(int n) {
       inserted = n;
   }
   int getInserted() {
       return inserted;
   }
   void setNextUse(int n) {
       next = n;
   }
   int getNextUse() {
       return next;
   }
   void setLastUse(int n) {
       last = n;
   }
   int getLastUse() {
       return last;
   }
   void incrementTimesUsed() {
       timesUsed += 1;
   }
   int getTimesUsed() {
       return timesUsed;
   }
}