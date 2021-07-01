package Cuenta2;


import java.util.Random;

public class HiloPersona extends Thread {
    Random r = new Random();
    private final MonitorCuenta2 monitorCuenta;

    public HiloPersona(MonitorCuenta2 monitor) {
        monitorCuenta = monitor;
    }

    public void run() {
        while(true) {
            if (r.nextBoolean()) {
                monitorCuenta.depositar(r.nextDouble() * 1000);
            } else {
                monitorCuenta.extraer(r.nextDouble() * 1000);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
