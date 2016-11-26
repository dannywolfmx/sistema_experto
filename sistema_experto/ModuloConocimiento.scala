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
 */

import scala.collection.mutable

 class ModuloConocimiento(){
     var desc:String
     var bs:mutable.ArrayBuffer[Any];

     def this(desc:String){
         this()
         this.desc = desc
     }

     def this(desc:String,archBC :String) : Unit = {
         this()
         this.desc = desc;
         bc = new mutable.ArrayBuffer.empty[Any];
     }

     def cargarBC(nomArch:String){
         //Pendiente
     }

     override def toString() : String = {
         var retorno = "Modulo de Conocimiento: "+desc+"\n";
         for( elemento <- bs ){
             retorno += ( elemento + "\n" )
         }
         retorno;
     }

     def filtrarObjs() : mutable.ArrayBuffer[Any] = {
         var objs = new mutable.ArrayBuffer.empty[Any]
         var r:Regla = null;

         for( elemento <- bc ){
             r = elemento.asInstanceOf[Regla]
             if ( r.esObjetivo ) objs.Add(r);
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
                if (rActual.disparo) retorno += (rActual+"\n") 
         }
         
         retorno
     }
 }