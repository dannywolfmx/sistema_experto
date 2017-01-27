/*
 * MainClass
 * 
 * Esta clase es el orquestador principal del desempeño
 * de este sistema experto.
 * 
 * Desde este punto se crean las instancias concretas
 * de los elementos principales del sistema experto: 
 * Motor de Inferencia, Módulo de Conocimiento y
 * Memoria de Trabajo.
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
 * Fecha: 29/10/2006
 * Hora: 06:34 a.m.
 * 
 * Migracion a Scala: Daniel Menchaca Luna
 */

import scala.collection.mutable

object Main{
    var mt = new MemoriaTrabajo()
    var mc = new ModuloConocimiento("Animales","/home/daniel/zoo.bc.txt")
    var mi = new MotorInferencia()
  
    def main(args:Array[String]){
        var res:mutable.ArrayBuffer[Any] = null
        println("Sistema experto Scala")
        println(this.mc);
        
        res = this.mi.encadenarAtras(this.mc, this.mt);
        
        if (res != null){
          println("Exito: ")
          for(a <- res){
            println(a.asInstanceOf[Atomo] + " ")
          }
          println(this.mt)
        }
        else {
          println("Fracaso...");
        }
        println(mc.muestraReglasDisparadas);
        
        
        
    }
}
