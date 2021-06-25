package buffer;

import java.util.Random;

public class HiloProductor implements Runnable{
    public HiloProductor(BufferMonitor bufferMon){
        buffer = bufferMon;
        r = new Random();
    }

    public void run(){
        while(true){
            int valor = r.nextInt(100);
            try {
                buffer.insertar(valor);
                System.out.println(Thread.currentThread().getName() + " produjo dato: " + valor);
                Thread.sleep(r.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private final Random r;
    private final BufferMonitor buffer;
}
