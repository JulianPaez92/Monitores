package Contador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.*;


public class ContadorMonitor2 {
    private final Lock lock = new ReentrantLock();
    private final Condition menosDos = lock.newCondition();
    private int cuenta;
    private final List<Thread> hilosActivos = new ArrayList<Thread>();

    public void solicitarContador() {
        lock.lock();
        try {
            while (cuenta == 2) {
                menosDos.await();
            }
            System.out.println(currentThread().getName() + " esta contando");
            hilosActivos.add(currentThread());
            cuenta++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void liberarContador() {
        lock.lock();
        try {
            if(hilosActivos.contains(currentThread())){
                hilosActivos.remove(currentThread());
                cuenta--;
                System.out.println(currentThread().getName() + " dejó de contar");
                menosDos.signalAll();
            } else {
                System.out.println("Error: hilo no solicito contador");
            }
        } finally {
                lock.unlock();
            }
    }

    public void incrementar() {
        lock.lock();
        try {
            i += 1;
        }
        finally {
            lock.unlock();
        }
    }

    public double getValor() {
        lock.lock();
        try {
            return i;
        } finally {
            lock.unlock();
        }
    }
    private static double i; // Recurso
}
