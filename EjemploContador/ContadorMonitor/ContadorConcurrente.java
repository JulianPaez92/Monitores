package ContadorMonitor;

public class ContadorConcurrente {
    public static void main(String[] args) {
        MonitorContador countMon = new MonitorContador();
        Thread[] hilos;
        hilos= new Thread[10];

        for(int i=0; i<5;i++){
            Runnable r = new HiloContador(countMon);
            Thread t = new Thread(r);
            hilos[i] = t;
            t.start();
            System.out.println("Arranca hilo " + i);
        }
        try {
            for(int i=0;i<5;i++)
                hilos[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor Final =" + countMon.getValor());

    }
}
