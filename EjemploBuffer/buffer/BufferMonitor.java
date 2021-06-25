package buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferMonitor {
    private final int[] buffer;
    private final int capacidad;

    private int primero;
    private int ultimo;
    private int cuenta;

    private final Lock lock = new ReentrantLock();

    private final Condition noLleno = lock.newCondition();
    private final Condition noVacio = lock.newCondition();

    public BufferMonitor(int capacidad){
        super();
        this.capacidad = capacidad;
        buffer = new int[capacidad];

    }

    public void insertar(int dato) throws InterruptedException {
        lock.lock();
        try{
            while(cuenta==capacidad){
                noLleno.await();
            }
            buffer[ultimo]=dato;
            ultimo = (ultimo+1) % capacidad;
            cuenta++;
            noVacio.signalAll();
        }finally{
            lock.unlock();
        }
    }

    public int extraer() throws InterruptedException {
        lock.lock();
        try{
            while (cuenta==0){
                noVacio.await();
            }
            int resultado  = buffer[primero];
            primero=(primero+1) % capacidad;
            cuenta--;
            noLleno.signalAll();
            return resultado;
        }finally{
            lock.unlock();
        }
    }
}
