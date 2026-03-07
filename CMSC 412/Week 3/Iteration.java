public class Iteration{

  int status=1;
  public static void main(String[] args) {

    Iteration iterateEachIteration = new Iteration();

    X x=new X(iterateEachIteration);
    Y y=new Y(iterateEachIteration);
    Z z=new Z(iterateEachIteration);

    x.start();
    y.start();
    z.start();
    }
}

class X extends Thread{
  Iteration iterateEach;

  X(Iteration iterateEachIteration){
    this.iterateEach = iterateEachIteration;
  }

  @Override
  public void run() {

    try{
      synchronized (iterateEach) {

        for (int i = 0; i < 5; i++) {

          while(iterateEach.status!=1){
               	iterateEach.wait();
          }
          System.out.println("Thread 1 - iteration no. "+(i+1));
          iterateEach.status = 2;
          iterateEach.notifyAll();
        }
      }
    }
    catch (Exception e) {
            System.out.println("Exception 1 :"+e.getMessage());
    }
  }
}

class Y extends Thread{
  Iteration iterateEach;

  Y(Iteration iterateEachIteration){
        this.iterateEach = iterateEachIteration;
  }

  @Override
  public void run() {

    try{
      synchronized (iterateEach) {
        int i;
          for (i = 0; i < 5; i++) {

            while(iterateEach.status!=2){
                  	iterateEach.wait();
            }
            System.out.println("Thread 2 - iteration no. "+(i+1));
            iterateEach.status = 3;
            iterateEach.notifyAll();
            }
          }
        }
    catch (Exception e) {
      System.out.println("Exception 2 :"+e.getMessage());
      }
    }
  }


class Z extends Thread{
  Iteration iterateEach;

  Z(Iteration iterateEachIteration){
    this.iterateEach = iterateEachIteration;
  }

  @Override
  public void run() {

    try{
      synchronized (iterateEach) {
        int i;
          for (i = 0; i < 5; i++) {

            while(iterateEach.status!=3){
                   	iterateEach.wait();
            }
            System.out.println("Thread 3 - iteration no. "+(i+1));
            iterateEach.status = 1;
            iterateEach.notifyAll();
          }
      }
    }
  catch (Exception e) {
    System.out.println("Exception 3 :"+e.getMessage());
    }
  }
}