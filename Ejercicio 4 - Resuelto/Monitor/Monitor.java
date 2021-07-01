package Monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private final Lock lock = new ReentrantLock();
    private final Condition menos2Hilos = lock.newCondition();
    private int cuenta=0;

    public void entrada(){
        lock.lock();
        try{
            while(cuenta<2){
                cuenta++;
                System.out.println("hilo " + cuenta + Thread.currentThread().getName() + " se duerme");
                menos2Hilos.await();
            }
            if(cuenta==0) System.out.println("hilo " + Thread.currentThread().getName() + " se despierta");
            if(cuenta==2) {
                System.out.println("hilo " + Thread.currentThread().getName() + " despierta a los hilos" );
                cuenta=0;
                menos2Hilos.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
