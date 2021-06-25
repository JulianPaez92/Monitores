package ContadorMonitor;

public class HiloContador implements Runnable{

    public HiloContador(MonitorContador contadorMon){
        contador=contadorMon;
    }
    public void run(){
       // System.out.println("Aqui hilo");
        for(int i=0;i<1000000;i++){
            contador.incrementar();
        }
    }
    private final MonitorContador contador;
}
