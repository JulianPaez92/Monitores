package Contador2;

public class HiloContador implements Runnable{

    public HiloContador(ContadorMonitor3 contadorMon){
        contador=contadorMon;
    }
    public void run(){
        contador.solicitarContador();
        for(double i=0;i<1000000;i++){
            contador.incrementar();
        }
        contador.liberarContador();
    }
    private final ContadorMonitor3 contador;
}
