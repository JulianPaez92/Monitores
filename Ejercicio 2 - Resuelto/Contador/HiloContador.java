package Contador;

public class HiloContador implements Runnable{

    public HiloContador(ContadorMonitor2 contadorMon){
        contador=contadorMon;
    }
    public void run(){
        // System.out.println("Aqui hilo");
        contador.solicitarContador();
        for(double i=0;i<1000000;i++){
            contador.incrementar();
        }
        contador.liberarContador();
    }
    private final ContadorMonitor2 contador;
}
