package Cuenta;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorCuenta {
    private final Lock lock = new ReentrantLock();
    private final Condition saldoSuficiente = lock.newCondition();
    private static double saldo;

    public MonitorCuenta(double saldoInicial){
        saldo = saldoInicial;
    }

    public void extraer(double valor){
        lock.lock();
        try{
            while(saldo<=valor){
                saldoSuficiente.await();
            }
            saldo = saldo - valor;
            System.out.println(Thread.currentThread().getName() + " extrajo " + String.format("%.2f",valor) + " nuevo saldo = " + String.format("%.2f",saldo));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void depositar(double valor){
        lock.lock();
        try{
            saldo = saldo + valor;
            System.out.println(Thread.currentThread().getName() + " despositó " + String.format("%.2f", valor) + " nuevo saldo = " + String.format("%.2f",saldo));
            saldoSuficiente.signalAll();
        }
        finally {
            lock.unlock();
        }

    }
}
