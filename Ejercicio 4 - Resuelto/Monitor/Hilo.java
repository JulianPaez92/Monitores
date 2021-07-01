package Monitor;

public class Hilo extends Thread{
    public Hilo(Monitor mon){
        monitor = mon;
    }
    public void run(){
        monitor.entrada();
    }
    private final Monitor monitor;
}
