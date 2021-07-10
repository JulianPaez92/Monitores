import threading
import logging
import random
import time

logging.basicConfig(format='%(asctime)s.%(msecs)03d [%(threadName)s] - %(message)s', datefmt='%H:%M:%S', level=logging.INFO)

class MonitorProductorConsumidor():
    entero = 0  #Variable a producir/consumir
    consumos = 1 #Cantidad de lecturas por parte del consumidor
    lock = threading.RLock()
    leido = threading.Condition(lock)  # informa el estado del entero para ser leido


    def productor(self):
        self.lock.acquire()
        try:
            while(self.consumos == 0): # mientras el entero no sea consumido espera para producir
                self.leido.wait()
            self.entero = random.randint(0, 100)
            logging.info(f'{threading.current_thread().name} asignó el valor {self.entero} a la variable')
            self.consumos = 0  # torna el valor a cero para que pueda ser consumido
        finally:
            self.lock.release()


    def consumidor(self):
        self.lock.acquire()
        try:
            while(self.consumos == 3): #despues de 3 lecturas el entero se deja de consumir
                self.leido.wait()
            logging.info(f'{threading.current_thread().name} consumió el valor {self.entero}')
            self.consumos += 1  #el entero es consumido 1 vez
            self.leido.notifyAll()
        finally:
            self.lock.release()

class HiloProductor(threading.Thread):
    def __init__(self, monitorPC):
        super().__init__()
        self.monitor = monitorPC

    def run(self):
        while True:
            self.monitor.productor()
            time.sleep(random.randint(0,5)) #tiempo aleatorio para ejecutar el hilo


class HiloConsumidor(threading.Thread):
    def __init__(self, monitorPC):
        super().__init__()
        self.monitor = monitorPC

    def run(self):
        while True:
            self.monitor.consumidor()
            time.sleep(random.randint(0,5)) #tiempo aleatorio para ejecutar el hilo


def ProductorConsumidorMain():
    monitor = MonitorProductorConsumidor()
    hilos = []

    for i in range(10): #Se crean 10 hilos para Productores y 10 para Consumidores
        productor = HiloProductor(monitor)
        consumidor = HiloConsumidor(monitor)
        productor.start()
        consumidor.start()
        hilos.append(productor)
        hilos.append(consumidor)

    for i in hilos:
        i.join()

if __name__ == "__main__": #Arranca programa
    ProductorConsumidorMain()
