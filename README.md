#Monitores

##Ejemplo 1 - Contador Concurrente
[EjemploContador/ContadorMonitor](https://github.com/unahur-progra-concu-1-2021/Monitores/tree/master/EjemploContador/ContadorMonitor "EjemploContador/ContadorMonitor")

Este ejemplo implementa un monitor que controla un contador concurrente que es incrementado por varios hilos.

El monitor está implementado en la clase [MonitorContador](https://github.com/unahur-progra-concu-1-2021/Monitores/blob/master/EjemploContador/ContadorMonitor/MonitorContador.javap:// "MonitorContador")


Es un monitor simple, que solo controla el acceso en exclusión mútua al recurso.



##Ejemplo 2 - Buffer Cíclico (Productor/Consumidor)

[Ejemplo/Buffer](https://github.com/unahur-progra-concu-1-2021/Monitores/tree/master/EjemploBuffer/buffer "Ejemplo/Buffer")

En este ejemplo hilos productores y consumidores acceden concurrentemente a un buffer cíclico controlado por un monitor.

El monitor está implementado en la clase [BufferMonitor](https://github.com/unahur-progra-concu-1-2021/Monitores/blob/master/EjemploBuffer/buffer/BufferMonitor.java "BufferMonitor")


Este monitor controla el acceso con exclusión mútua al recurso e implementa las condiciones para sincronizar el acceso al buffer (lleno/vacío)




## Ejercicios

1. Implementar los ejemplos anteriores utilizando python (se puede implementar utlizando clases y las primitivas threading.Rlock y threading.Condition).

2. Modificar el **monitor** del ejemplo 1 (contador) de modo que solo dos hilos puedan incrementar el contador a la vez, debiendo los demás hilos esperar a que uno de estos termine para tomar su lugar.

3. Modificar el el ejercicio anterior de modo que solo dos hilos incrementen el contador y los demas deberán esperar a que **ambos hilos** terminen para tomar sus lugares.

4. Supongamos que **n** procesos comparten tres impresoras. Antes de usar una impresora se ejecuta **pedir_impresora** que devuelve el código de la impresora asignada. Una vez utilizada la impresora, ésta es liberada con otro procedimiento **liberar_impresora**, que solo puede ser llamado por el proceso que previamente le fue asignada. Desarrollar un monitor que implemente dichas operaciones e implementar un programa que genere **n** hilos solicitando continuamente impresoras cada un tiempo aleatorio.

5. Coches que vienen del Norte y del Sur llegan a un puente de una sola vía. Los coches en el mismo sentido pueden cruzar el puente a la vez, pero los coches en sentidos opuestos no. Desarrollar una solución a este problema modelando los coches como hilos y usar un monitor para la sincronización.

6. Modificar la solución anterior para asegurar equitatividad. Por ejemplo, permitir que como mucho C coches pasen en una dirección si hay coches esperando en la otra dirección.

8. Supongamos que un centro de computadores tiene dos impresoras, A y B, que son parecidas pero no idénticas. Tres clases de procesos usan las impresoras: los que usan la impresora A, los que usan la impresora B y los que usan ambas impresoras.
Desarrollar el código que cada clase de proceso ejecuta para acceder y liberar una impresora. Construir un monitor que asigne las impresoras y que permita usarlas con el máximo rendimiento.

