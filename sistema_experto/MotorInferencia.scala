/*
 * MotorInferencia
 * 
 * El motor de inferencia es una clase esencial para este gestor
 * gen�rico de sistemas expertos. Eje de la inferencia y del 
 * proceso de confrontaci�n de datos (almacenados en la memoria
 * de trabajo) con el conocimiento ontol�gico almacenado en la base 
 * de conocimiento, para producir el resultado meta.
 * 
 * Espec�ficamente este motor de inferencia cuenta con los
 * operadores responsables de realizar encadenamiento hacia 
 * adelante (modus ponens) y hacia atr�s (modus ponens invertido), 
 * as� como los operadores auxiliares para cumplir estas tareas:
 * concatenar y esElegible.
 * 
 * 
 * Desarrollador: Luis Alberto Casillas Santill�n
 * Fecha: 29/10/2006
 * Hora: 07:17 a.m.
 * 
 */

import scala.collection.mutable

 class MotorInferencia{
     var backward:Boolean = false

     
 }