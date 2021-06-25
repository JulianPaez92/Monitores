package buffer;

import java.util.Random;

public class HiloConsumidor implements Runnable{
    public HiloConsumidor(BufferMonitor bufferMon){
        buffer = bufferMon;
        r = new Random();
    }

    public void run(){
        while(true){
            try {
                int valor = buffer.extraer();
                System.out.println(Thread.currentThread().getName() + " consumio valor = " + valor);
                Thread.sleep(r.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private Random r;
    private final BufferMonitor buffer;
}
