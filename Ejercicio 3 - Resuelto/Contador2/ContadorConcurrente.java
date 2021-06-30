package Contador2;


public class ContadorConcurrente {
    public static void main(String[] args) {
        ContadorMonitor3 countMon = new ContadorMonitor3();
        Thread[] hilos;
        hilos= new Thread[20];

        for(int i=0; i<10;i++){
            Runnable r = new HiloContador(countMon);
            Thread t = new Thread(r);
            hilos[i] = t;
            t.start();
            //      System.out.println("Arranca hilo " + i);
        }
        try {
            for(int i=0;i<10;i++)
                hilos[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor Final =" + countMon.getValor());

    }
}
