package Cuenta;

public class CuentaMain {
    public static void main(String[] args) {
        MonitorCuenta mon = new MonitorCuenta(10000);

        for (int i = 0; i < 10; i++) {
            HiloPersona h = new HiloPersona(mon);
            h.start();
        }
    }
}