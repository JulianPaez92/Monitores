package Cuenta2;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorCuenta2 {
    private final Lock lock = new ReentrantLock();
    private final Condition saldoSuficiente = lock.newCondition();
    private final Condition nadieEnEspera = lock.newCondition();
    private static double saldo;
    private static Thread hiloEsperando = null;

    public MonitorCuenta2(double saldoInicial){
        saldo = saldoInicial;
    }

    public void extraer(double valor){
        lock.lock();
        try{
            while(hiloEsperando!=Thread.currentThread()&&hiloEsperando!=null){
                System.out.println(Thread.currentThread().getName() + " se duerme esperando a " + hiloEsperando.getName());
                nadieEnEspera.await();
            }
            while(saldo<=valor) {
                hiloEsperando = Thread.currentThread();
                System.out.println(Thread.currentThread().getName() + " saldo insuficiente para " + String.format("%.2f", valor) + " Saldo = " + String.format("%.2f", saldo));
                saldoSuficiente.await();
            }
            saldo = saldo - valor;
            hiloEsperando = null;
            System.out.println(Thread.currentThread().getName() + " extrajo " + String.format("%.2f", valor) + " nuevo saldo = " + String.format("%.2f", saldo));
            nadieEnEspera.signalAll();
        } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
    }
        finally {
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
