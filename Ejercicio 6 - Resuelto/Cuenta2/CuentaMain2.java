package Cuenta2;

public class CuentaMain2 {
    public static void main(String[] args) {
        MonitorCuenta2 mon = new MonitorCuenta2(100);

        for (int i = 0; i < 10; i++) {
            HiloPersona h = new HiloPersona(mon);
            h.start();
        }
    }
}