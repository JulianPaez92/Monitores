package ContadorMonitor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorContador {
    private final Lock lock = new ReentrantLock();

    public void incrementar() {
        lock.lock();
        try {
            i += 1;
        } finally {
            lock.unlock();
        }
    }

    public int getValor() {
        lock.lock();
        try {
            return i;
        } finally {
            lock.unlock();
        }
    }
    private static int i;
}
