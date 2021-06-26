import threading
import logging

logging.basicConfig(format='%(asctime)s.%(msecs)03d [%(threadName)s] - %(message)s', datefmt='%H:%M:%S', level=logging.INFO)


class MonitorContador():
    i = 0
    lock = threading.RLock()

    def incrementar(self):
        self.lock.acquire()
        try:
            self.i += 1
        finally:
            self.lock.release()

    def getValor(self):
        self.lock.acquire()
        try:
            return self.i
        finally:
            self.lock.release()

class HiloContador(threading.Thread):
    def __init__(self, contador):
        super().__init__()
        self.contador = contador

    def run(self):
        for i in range(0,1000000):
            self.contador.incrementar()

def ContadorMain():
    contador = MonitorContador()
    hilos = []

    for i in range(0,5):
        t = HiloContador(contador)
        t.start()
        logging.info(f'Arrancando hilo {i}')
        hilos.append(t)

    for i in hilos:
        i.join()

    logging.info(f'Valor finale = {contador.getValor()}')

if __name__ == "__main__":
    ContadorMain()


