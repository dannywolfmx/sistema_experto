/*
 * MemoriaTrabajo
 * 
 * Clase esencial de este entorno generico para sistemas
 * expertos. Responsable de contener los datos concretos
 * del caso de la realidad que actualmente procesa este
 * sistema experto.
 * 
 * En particular, cuando el sistema experto est� verificando
 * diferentes hechos de la realidad; almacena en esta 
 * estructura los �tomos que se han cotejado por medio de un
 * simple interrogatorio, o bien cuando una regla se dispara
 * y los �tomos contenidos en su conclusi�n se almacenan.
 * 
 * Es capaz de controlar los �tomos que se han afirmado y 
 * negado en el proceso, con el fin de lograr un tratamiento
 * preciso de la l�gica modelada en la ontolog�a del sistema
 * experto.
 * 
 * Desarrollador: Luis Alberto Casillas Santill�n
 * Fecha: 28/10/2006
 * Hora: 05:39 p.m.
 * 
 * Migracion a Scala: Daniel Menchaca Luna
 */

 //Importar Paquetes de coleccion mutable
import scala.collection.mutable

class MemoriaTrabajo(){
    var afirmados = mutable.ArrayBuffer.empty[Any] 
    var negados = mutable.ArrayBuffer.empty[Any]
    
    //Guardar los atomos
    def guardaAtomo( aa:Atomo  ) : Unit = {
        if ( !afirmados.contains( aa ) && !negados.contains( aa ) ){
            if ( aa.Estado ) {
              afirmados += aa

            } else {
              negados += aa
            }
        } 
        else {
            throw new AtomoDuplicado( aa.Desc );
        }
    }

    def presente( aa:Atomo  ):Boolean ={
        var aTmp:Atomo  = new Atomo( aa );
        aTmp.Estado = !aTmp.Estado;

        return ( afirmados.contains( aa ) || negados.contains( aa ) ||
			        afirmados.contains( aTmp ) || negados.contains( aTmp ) )   
    }

    def fueAfirmado( aa:Atomo  ) : Boolean = afirmados.contains(aa) 

    def fueNegado( aa:Atomo  ) : Boolean =  negados.contains(aa) 

    def recupera( aa:Atomo  ) : Atomo ={ 
        
        var pa = afirmados.indexOf( aa );
        var pn = negados.indexOf( aa );

        if ( pa > -1 ) return afirmados(pa).asInstanceOf[ Atomo ];
        
        if ( pn > -1 ) return negados( pn ).asInstanceOf[ Atomo ];

        return null;
    }

    override def toString() : String = {
        var retorno:String = "\nMemoria de Trabajo\nAfirmados: [ ";
       
        for( a <- afirmados ) { retorno += (a+" ")  }
        retorno += "]\nNegados: [ "

        for( a <- negados ) { retorno += (a+" ")  }
        retorno += "]"
        
        retorno
    }

}
