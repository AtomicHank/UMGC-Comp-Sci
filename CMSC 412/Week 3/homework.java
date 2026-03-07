public class Notification{

    int status=1;
    public static void main(String[] args) {

        Notification notifyAllExample = new Notification();

        A a=new A(notifyAllExample);
        B b=new B(notifyAllExample);
        C c=new C(notifyAllExample);

        a.start();
        b.start();
        c.start();
    }
}

class A extends Thread{
    Notification notifyAll;

    A(Notification notifyAllExample){
        this.notifyAll = notifyAllExample;
    }

    @Override
    public void run() {

        try{
            synchronized (notifyAll) {

                for (int i = 0; i < 5; i++) {

                    while(notifyAll.status!=1){
                        notifyAll.wait();
                    }
                    System.out.println("Thread 1 - iteration no. "+(i+1));
                    notifyAll.status = 2;
                    notifyAll.notifyAll();
                }

            }
        }catch (Exception e) {
            System.out.println("Exception 1 :"+e.getMessage());
        }

    }

}

class B extends Thread{

    Notification notifyAll;

    B(Notification notifyAllExample){
        this.notifyAll = notifyAllExample;
    }

    @Override
    public void run() {

        try{
            synchronized (notifyAll) {
               int i;
                for (i = 0; i < 5; i++) {

                    while(notifyAll.status!=2){
                        notifyAll.wait();
                    }
                    System.out.println("Thread 2 - iteration no. "+(i+1));
                    notifyAll.status = 3;
                    notifyAll.notifyAll();
                }

            }
        }catch (Exception e) {
            System.out.println("Exception 2 :"+e.getMessage());
        }

    }
}


class C extends Thread{

    Notification notifyAll;

    C(Notification notifyAllExample){
        this.notifyAll = notifyAllExample;
    }

    @Override
    public void run() {

        try{
            synchronized (notifyAll) {
                int i;
                for (i = 0; i < 5; i++) {

                    while(notifyAll.status!=3){
                        notifyAll.wait();
                    }

                    System.out.println("Thread 3 - iteration no. "+(i+1));
                    notifyAll.status = 1;
                    notifyAll.notifyAll();
                }

            }
        }catch (Exception e) {
            System.out.println("Exception 3 :"+e.getMessage());
        }
    }
}