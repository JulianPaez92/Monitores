package Monitor;

public class MainClass {
    public static void main(String[] args) {
        Monitor mon = new Monitor();

        for (int i = 0; i < 10; i++) {
            Hilo h = new Hilo(mon);
            h.start();
        }
    }
}
