# Monitores

## Ejemplo 1 - Contador Concurrente
[EjemploContador/ContadorMonitor](https://github.com/unahur-progra-concu-1-2021/Monitores/tree/master/EjemploContador/ContadorMonitor "EjemploContador/ContadorMonitor")

Este ejemplo implementa un monitor que controla un contador concurrente que es incrementado por varios hilos.

El monitor está implementado en la clase [MonitorContador](https://github.com/unahur-progra-concu-1-2021/Monitores/blob/master/EjemploContador/ContadorMonitor/MonitorContador.javap:// "MonitorContador")


Es un monitor simple, que solo controla el acceso en exclusión mútua al recurso.



## Ejemplo 2 - Buffer Cíclico (Productor/Consumidor)

[Ejemplo/Buffer](https://github.com/unahur-progra-concu-1-2021/Monitores/tree/master/EjemploBuffer/buffer "Ejemplo/Buffer")

En este ejemplo hilos productores y consumidores acceden concurrentemente a un buffer cíclico controlado por un monitor.

El monitor está implementado en la clase [BufferMonitor](https://github.com/unahur-progra-concu-1-2021/Monitores/blob/master/EjemploBuffer/buffer/BufferMonitor.java "BufferMonitor")


Este monitor controla el acceso con exclusión mútua al recurso e implementa las condiciones para sincronizar el acceso al buffer (lleno/vacío)




## Ejercicios

1. Implementar los monitores de los ejemplos 1 y 2 (Contador y Buffer) codificando en **python** (se puede implementar utilizando clases y las primitivas threading.Rlock y threading.Condition).

2. Modificar el **monitor** del ejemplo 1 (contador) de modo que solo dos hilos puedan incrementar el contador a la vez, debiendo los demás hilos esperar a que uno de estos termine para tomar su lugar.

3. Modificar el el ejercicio anterior de modo que solo dos hilos incrementen el contador y los demas deberán esperar a que **ambos hilos** terminen para tomar sus lugares.
4. Diseñar un monitor con un único procedimiento llamado **entrada**, que provoque que los dos primeros procesos que llamen al procedimiento sean suspendidos y el tercero los despierte y así cíclicamente.
5. Una cuenta de ahorros es compartida entre distintas personas (procesos). Cada persona puede sacar y depositar dinero en la cuenta. El saldo actual de la cuenta es la suma de los depósitos menos la suma de las extracciones. El saldo nunca pude ser negativo. Construir un monitor que resuelva este problema con las operaciones **depositar** y **extraer**.
6. Modificar la solución anterior suponiendo que las extracciones son resueltas por orden de llegada. Es decir si hay 200 y alguien quiere sacar 300 y luego llega alguien que quiere sacar 200, este último tendrá que esperar hasta que el que pidió 300 consiga esa cantidad.
7. Implementar utilizando un monitor una relación de productor/consumidor entre tres procesos: generador, visualizador e impresor:
  - El proceso **generador** generará caracteres en forma aleatoria (simula un usuario utilizando el teclado de un terminal) y almacena esos caracteres en un buffer cíclico cuyo tamaño se corresponde con una línea en pantalla.
  - El proceso **visualizador** recogerá los caracteres producidos por el generador y los introducirá en otro recurso de almacenamiento simulando una **memoria de pantalla** con un tamaño preestablecido de varias líneas. Nota: la memoria de imagen puede ser un array bi-dimensional de **n** arrays con el mismo tamaño que el buffer cíclico (linea).
  - El proceso **impresor** enviará a la impresora (simulada por la pantalla) el contendio de la **memoria de pantalla**.
  - Para resolver el problema además de las condiciones típicas del esquema productor/consumidor, se deben tener en cuenta las siguientes restricciones:
    - Cada vez que se llena la **memoria de pantalla**, para volver a utilizarla el proceso **visualizador** debe esperar a que el proceso **impresor** haya realizado la copia.
    - Una vez que se visualizaron todos los caracteres generados, el procesos impresor realizará la copia de la última pantalla aunque esta no este completa (llena).ç
    - Se puede suponer que la terminación de los proceos ocurrirá cuando se detecte un caracter especial.  

8. Supongamos que **n** procesos comparten tres impresoras. Antes de usar una impresora se ejecuta **pedir_impresora** que devuelve el código de la impresora asignada. Una vez utilizada la impresora, ésta es liberada con otro procedimiento **liberar_impresora**, que solo puede ser llamado por el proceso que previamente le fue asignada. Desarrollar un monitor que implemente dichas operaciones e implementar un programa que genere **n** hilos solicitando continuamente impresoras cada un tiempo aleatorio.

9. Coches que vienen del Norte y del Sur llegan a un puente de una sola vía. Los coches en el mismo sentido pueden cruzar el puente a la vez, pero los coches en sentidos opuestos no. Desarrollar una solución a este problema modelando los coches como hilos y usar un monitor para la sincronización.

10. Modificar la solución anterior para asegurar equitatividad. Por ejemplo, permitir que como mucho C coches pasen en una dirección si hay coches esperando en la otra dirección.

11. Supongamos que un centro de computadores tiene dos impresoras, A y B, que son parecidas pero no idénticas. Tres clases de procesos usan las impresoras: los que usan la impresora A, los que usan la impresora B y los que usan ambas impresoras.
Desarrollar el código que cada clase de proceso ejecuta para acceder y liberar una impresora. Construir un monitor que asigne las impresoras y que permita usarlas con el máximo rendimiento.

