import threading
import random
import time
import logging

logging.basicConfig(format='%(asctime)s.%(msecs)03d [%(threadName)s] - %(message)s', datefmt='%H:%M:%S', level=logging.INFO)

class BufferMonitor():
    buffer =[]
    capacidad=0
    primero = 0
    ultimo = 0
    cuenta = 0

    lock = threading.RLock()

    noLleno = threading.Condition(lock)
    noVacio = threading.Condition(lock)

    def __init__(self, capacidad):
        self.capacidad = capacidad
        # inicializa el buffer en 0
        for i in range(capacidad):
            self.buffer.append(0)

    def insertar(self, dato):
        self.lock.acquire()
        try:
            while(self.cuenta==self.capacidad):
                self.noLleno.wait()
            self.buffer[self.ultimo] = dato
            self.ultimo = (self.ultimo+1) % self.capacidad
            self.cuenta +=1
            self.noVacio.notifyAll()
        finally:
            self.lock.release()

    def extraer(self):
        self.lock.acquire()
        try:
            while(self.cuenta==0):
                self.noVacio.wait()

            resultado = self.buffer[self.primero]
            self.primero = (self.primero+1) % self.capacidad
            self.cuenta -=1
            self.noLleno.notifyAll()
            return resultado

        finally:
            self.lock.release()


class HiloConsumidor(threading.Thread):
    def __init__(self, bufferMon):
        super().__init__()
        self.buffer = bufferMon

    def run(self):
        while True:
            valor = self.buffer.extraer()
            logging.info(f'{threading.current_thread().name} consumio valor = {valor}')
            time.sleep(random.randint(0,3))


class HiloProductor(threading.Thread):
    def __init__(self, bufferMon):
        super().__init__()
        self.buffer = bufferMon

    def run(self):
        while True:
            valor = random.randint(0,100)
            self.buffer.insertar(valor)
            logging.info(f'{threading.current_thread().name} produjo dato: {valor}')
            time.sleep(random.randint(0,2))

def ProductorConsumidorMain():
    bufferMon = BufferMonitor(10)
    hilos = []

    for i in range(5):
        c = HiloConsumidor(bufferMon)
        p = HiloProductor(bufferMon)
        c.start()
        p.start()
        hilos.append(c)
        hilos.append(p)

    for i in hilos:
        i.join()

if __name__ == "__main__":
    ProductorConsumidorMain()

