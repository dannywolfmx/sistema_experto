/*
 * ModuloConocimiento
 * 
 * Clase esencial de este entorno gen�rico para sistemas
 * expertos. Es la responsable de contener y gestionar
 * todo el conocimiento que administra el sistema experto.
 * 
 * Espec�ficamente contiene la ontolog�a que el experto
 * artificial debe procesar, por medio de una base de
 * conocimiento que contiene todas las reglas expresadas
 * originalmente en LM-Regla y luego en una codificaci�n
 * interna congruente al resto de este modelo.
 * 
 * Responsable de cargar la base de conocimiento, apoy�ndose
 * en el comportamiento implementado en la clase Regla. 
 * Filtra las reglas que concluyen objetivos, elimina las 
 * marcas de disparo y las marcas para encadenamiento hacia
 * atr�s hechas durante este tipo de inferencia.
 * 
 * Desarrollador: Luis Alberto Casillas Santill�n
 * Fecha: 29/10/2006
 * Hora: 06:10 a.m.
 *
 * Migracion a Scala: Daniel Menchaca Luna
 * 
 */

import scala.collection.mutable
import scala.io.Source

 class ModuloConocimiento(){
     var desc:String = null;
     var bc:mutable.ArrayBuffer[Any] = null;

     def this(desc:String){
         this()
         this.desc = desc
     }

     def this(desc:String,archBC : String){
         this()
         this.desc = desc;
         bc = mutable.ArrayBuffer.empty[Any];
         cargarBC(archBC)
     }

     def cargarBC(nomArch:String){
         for(line <- Source.fromFile(nomArch).getLines()){
           bc += new Regla(line)
         }
         
     }

     override def toString() : String = {
         var retorno = "Modulo de Conocimiento: "+desc+"\n";
         for( elemento <- bc ){
             retorno += ( elemento + "\n" )
         }
         retorno;
     }

     def filtrarObjs() : mutable.ArrayBuffer[Any] = {
         var objs = mutable.ArrayBuffer.empty[Any]
         var r:Regla = null;

         for( elemento <- bc ){
             r = elemento.asInstanceOf[Regla]
             if ( r.esObjetivo ){
               objs += r ;
              
             }
         }

         objs
     }

     def desmarcar() : Unit = {
         var r:Regla = null

         for( elemento <- bc){
             r = elemento.asInstanceOf[Regla]
             r.marca = false
         }
     }

     def quitarDisparos() : Unit = {
         var r:Regla = null

         for( elemento <- bc){
             r = elemento.asInstanceOf[Regla]
             r.disparo = false
         }
     }

     def muestraReglasDisparadas : String  = {
         var retorno = ""

         for( rActual <- bc ){
                if (rActual.asInstanceOf[Regla].disparo) retorno += (rActual.asInstanceOf[Regla]+"\n") 
         }
         
         retorno
     }
 }
