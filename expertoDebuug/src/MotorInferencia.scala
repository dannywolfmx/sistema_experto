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

 class MotorInferencia(){
     var backward:Boolean = false

     def encadenarAdelante(mc:ModuloConocimiento,mt:MemoriaTrabajo) : mutable.ArrayBuffer[Any] = {
         var ra:Regla = null
         var aa:Atomo = null
         var resConsulta:Boolean = false
         var resCondicion:Boolean = false
         
         for ( elemento <- mc.bc ){
             ra = elemento.asInstanceOf[ Regla ]

             for ( elemCond <- ra.partesCond ){

                 if ( elemCond.isInstanceOf[ Atomo ] ){

                    aa = elemCond.asInstanceOf[ Atomo ]
                    aa = new Atomo( aa.Desc , aa.Estado, aa.Objetivo)

                    if( !mt.presente( aa ) ){

                        resConsulta = Consultar.porAtomo(aa,ra);
                        // Verificacion de certidumbre: [0,1] elem R
                        aa.Estado = resConsulta;
                        try{

                        }catch{
                            case as:AtomoDuplicado => println("Se duplico el atomo: {0}",aa)
                            // Hacer nada...
                        }
                    }
                 }
                    
             }

             resCondicion = ra.probarCondicion( mt )

             if ( resCondicion ){
                 println( "Se disparo: " + ra )
                // Antes de llamar a ra.dispara(mt)
				// calcular certidumbre del resultado.
				// Deberia enviarse como otro parimetro.
                ra.dispara( mt )
                //println( mt )
                resCondicion = false
                if ( ra.esObjetivo() ) return ra.partesConc
             }
             else{
                // Este es uno de los interruptores del SE
				// Si se comenta, el experto consulta toda
				// la BC' aunque una regla falle.
                if ( backward ) return null
                println( "No se disparo..." )
                //Agregar comentarios complementarios de c#
             }
         }

         return null
     }

     def concatena(destino:mutable.ArrayBuffer[Any],fuente:mutable.ArrayBuffer[Any]) : Unit = {
         var aTmp:Atomo = null

         for ( aAgregar <- fuente ){
             if ( aAgregar.isInstanceOf[Atomo] ){
                 aTmp = new Atomo( aAgregar.asInstanceOf[Atomo] )
                 destino += aTmp
             }
            // Ojo, aqu� es donde se niega la BC. Debes arreglarlo
            // Parece que ya...
            if ( aAgregar.isInstanceOf[Negacion] ){
                aTmp.Estado = ! aTmp.Estado
            }
         }
     }

     def esElegible(r:Regla,porSatisfacer:mutable.ArrayBuffer[Any]):Boolean = {
         var atomosConc = mutable.ArrayBuffer.empty[Any]
         var aTmp:Atomo = null;

         for ( aa <- r.partesConc){
             
             if ( aa.isInstanceOf[Atomo] ){
                 aTmp = new Atomo (aa.asInstanceOf[Atomo])
                 atomosConc += aTmp
             }

             if ( aa.isInstanceOf[Atomo] ) aTmp.Estado = !aTmp.Estado;
         }

         for ( aa <- atomosConc ){
             if ( porSatisfacer.contains( aa ) ) return true;
         }
         return false;
     }

     def encadenarAtras( mc : ModuloConocimiento , mt : MemoriaTrabajo ) : mutable.ArrayBuffer[Any] = {
         var reglasObj = mc.filtrarObjs
         var aSatisfacer = mutable.ArrayBuffer.empty[Any]
         var bcPrima = mutable.ArrayBuffer.empty[Any]
         var resultado:mutable.ArrayBuffer[Any] = null
         
         var nomBCPrima:String = null

         var usadas = new Array[Boolean](reglasObj.size);
         var salir = false;

         var pos = -1
         var veces = 0
         var total  = reglasObj.size

         var r = util.Random

         backward = true;
         
         var mcTmp:ModuloConocimiento = null;
         do{
             pos = r.nextInt(total)
             if ( !usadas(pos) ){
                 veces += 1
                 usadas(pos) = true
                 //Eliminar los elementos del array
                 aSatisfacer.clear
                 bcPrima.clear

                 mc.desmarcar
                 mc.quitarDisparos
                 
                 for( pConc <- ( reglasObj(pos).asInstanceOf[ Regla ] ).partesConc ){
                     if ( pConc.isInstanceOf[Atomo] ){
                         var aObj = pConc.asInstanceOf[Atomo];
                         
                         if( aObj.Objetivo ){
                           
                             nomBCPrima = aObj.Desc.toUpperCase + "";
                         }
                     }
                 }

                 mcTmp = new ModuloConocimiento(nomBCPrima)
                 //Bootstrap!!!
                 concatena(aSatisfacer,( reglasObj(pos).asInstanceOf[ Regla ] ).partesCond);
                     do{
                         salir = true
                         for (ra <- mc.bc){
                             if ( !ra.asInstanceOf[Regla].marca && esElegible( ra.asInstanceOf[Regla],aSatisfacer ) ){
                                 salir = false;
                                 println( "Elegida: " + ra )
                                 ra.asInstanceOf[Regla].marca = true;
                                 concatena( aSatisfacer , ra.asInstanceOf[Regla].partesCond )
                                 bcPrima.prepend(ra)
                                
                             }
                         }
                     }while( !salir )

                     mcTmp.bc = bcPrima
                     println( "Intentando con: \n" + mcTmp )
                     resultado = encadenarAdelante(mcTmp,mt)
                     if ( resultado != null ){
                         backward = false
                         println("Entregue resultado")
                         return resultado
                     }
                 }
         }while( veces < total )
         backward =  false
         return null

     }
 }