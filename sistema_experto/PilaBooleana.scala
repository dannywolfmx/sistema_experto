/*
 * PilaBooleana
 * 
 * Clase auxiliar, responsable de administrar una pila
 * con una representación de datos específica para el 
 * enfoque de este entorno genérico para sistemas expertos.
 * 
 * En particular, es utilizada para realizar la evaluación
 * lógica de las condiciones compuestas, contenidas en las
 * representaciones LM-Regla; que se encuentran expresadas
 * en notación postfija libre de complejidad en paréntesis
 * y presedencias, y directamente procesable por la maquinaria
 * computacional.
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
 * Fecha: 28/10/2006
 * Hora: 06:04 p.m.
 * Migracion a Scala: Daniel Menchaca Luna
 * 
 */


import scala.collection.mutable

class PilaBooleana(){
  
    var datos = mutable.ArrayBuffer.empty[Any]


    def anula : Unit = {
        datos.clear
    }

    def vacia : Boolean = {
        datos.size == 0
    }

    def top:Boolean = {
        if ( !vacia ) {
          datos(datos.size - 1).asInstanceOf[Boolean]
        } else {
          false
        }
    }

    def pop:Boolean = {
        var tmp:Boolean = false

        if (!vacia){
          tmp = datos(datos.size -1).asInstanceOf[Boolean]
            datos.remove(datos.size - 1)
            return tmp;
            
        }else{
            return false
        }
    }

    def push(valor:Boolean):Unit = {
        datos += valor
    }
}
