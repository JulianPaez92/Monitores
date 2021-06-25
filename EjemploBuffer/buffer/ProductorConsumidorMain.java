package buffer;

import java.util.concurrent.RunnableFuture;

public class ProductorConsumidorMain {
    public static void main(String[] args) {
        BufferMonitor bufferMon = new BufferMonitor(10);
        Thread[] hilos;
        hilos= new Thread[10];

        for(int i=0; i<5;i++){
            Runnable c = new HiloConsumidor(bufferMon);
            Runnable p = new HiloProductor(bufferMon);
            Thread tc = new Thread(c);
            Thread tp = new Thread(p);
       //     hilos[2*i]=tc;
       //     hilos[2*i+1]=tp;
            tc.start();
            tp.start();

        }
       /* try {
            for(int i=0;i<10;i++)
                hilos[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } */

    }
}
