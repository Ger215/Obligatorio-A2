# Obligatorio-Algoritmos2

# 1. Base de Datos de Estudiantes Nombre de archivo: ejercicio1.cpp/Ejercicio1.java

Letra

Se tiene una base de datos de estudiantes de la Universidad ORT. En esta instancia, cada estudiante está representado por una palabra conformada por las iniciales de su nombre y sus dos apellidos completos. Se conoce al conjunto de notas de exoneraciones y exámenes en diferentes asignaturas. Se desea implementar una tabla de hash cerrado que permita buscar los estudiantes por número de identificación y obtener el promedio de sus notas.
Se pide implementar una función en que reciba como entrada los códigos de identificación de los estudiantes y sus respectivas notas de exoneración o aprobación de examen. La función deberá imprimir por pantalla el código de identificación de los alumnos y el PAC (Promedio Acumulado de Calificaciones) de cada uno de ellos. El listado deberá respetar el orden en el que fueron introducidos los alumnos y sus notas. La implementación debe utilizar una tabla de hash cerrado con resolución de colisiones distinta al método de exploración lineal.

Input
La primera línea de la entrada contiene un número entero positivo A, el número de estudiantes de la universidad. Le siguen A líneas distintas de formato N K K1 K2 ... Kk siendo N las iniciales y apellidos del estudiante, K la cantidad de aprobaciones y exoneraciones y los siguientes valores las notas obtenidas.

Salida
A líneas de formato N P, siendo N las iniciales y apellidos del estudiante y P el PAC del alumno.

Restricciones
● Utilizar una tabla de hash cerrado.
● Resolver en orden temporal: O(A) promedio, siendo A la cantidad de
usuarios.

Ejemplos de entrada y salida
Entrada
3
CHBuenoSuarez 4 10 30 50 70
DMForlanCorazo 3 100 100 100
FSValverdeDipetta 5 100 90 80 70 60

Salida
CHBuenoSuarez 40
DMForlanCorazo 100
FSValverdeDipetta 80

# 2. Libro Nombre de archivo: ejercicio2.cpp/Ejercicio2.java

Letra
Se tiene un libro, el cual tiene una cantidad N de palabras, siendo en total P palabras distintas. Se desea implementar una función en que permita mostrar las distintas P palabras ordenadas y su cantidad de apariciones en el libro.
La función deberá recibir como entrada el número P de palabras, seguido por una palabra por línea, totalizando P+1 líneas. Debe mostrar por pantalla las P palabras distintas ordenadas alfabéticamente de forma descendente, con cada línea conteniendo la palabra y sus apariciones en el texto.

Input
La primera línea de la entrada contiene un número entero positivo N, seguido por N líneas con una palabra cada una.

Salida
Imprime P líneas conteniendo una palabra y su cantidad de apariciones cada una. Las P líneas deben estar ordenadas alfabéticamente de forma descendente por palabra.

Restricciones
● La operación debe tener orden O(P \* Log2 P) peor caso, siendo P la cantidad de palabras distintas en el texto.

Ejemplos de entrada y salida
Entrada
10
sos
mi
locura
y
mi
pasion
tuya
es
mi
vida

Salida
y 1
vida 1
tuya 1
sos 1
pasion 1
mi 3
locura 1
es 1

# 3. Aerolínea Nombre de archivo: ejercicio3.cpp/Ejercicio3.java

Letra
Una aerolínea desea optimizar su sistema de check-in para favorecer a los pasajeros más frecuentes de la misma. Se recibirá un número R de reservas distintas, compuestas por P personas indicadas en la reserva. Cada persona tiene un pasaporte y cantidad de vuelos realizados con la aerolínea. Las personas pertenecientes a la misma reserva ingresan juntos, favoreciendo inicialmente a aquellas reservas que tienen un más alto promedio de vuelos realizados con la aerolínea.

Input
La función deberá recibir como entrada el número R de reservas, seguido por R grupos conformados por un número P de personas en la reserva para seguir con P líneas, cada una conteniendo al número de pasaporte del viajero y la cantidad de vuelos realizados.

Salida
Debe mostrar por pantalla R líneas ordenadas descendientemente por promedio de vuelos de los integrantes de la reserva. Las líneas deben contener el promedio de vuelos de los integrantes seguido por sus pasaportes, todos separados por un espacio y en el mismo orden original en el que fueron ingresados.

Restricciones
● El orden de ejecución de la carga de datos no puede superar O(P + R) caso promedio y O(P + R _ Log2 R) peor caso, mientras que la impresión no debe llevar más de O(R _ Log2 R) peor caso.

Ejemplos de entrada y salida
Entrada
3
2
A123456 10
B234567 11
1
C345678 40
3
D456789 50
E567890 10
F678901 30

Salida
40 C345678
30 D456789 E567890 F678901
10.5 A123456 B234567
