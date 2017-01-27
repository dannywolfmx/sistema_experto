/*
 * LectorArch
 * 
 * Clase auxiliar responsable de recuperar información
 * contenida en un archivo específico. 
 * 
 * En particular, el módulo de conocimiento de este
 * gestor genérico para sistema experto se apoya en los
 * servicios de esta clase para recuperar la base de
 * conocimiento del sistema de archivos.
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
 * Fecha: 29/10/2006
 * Hora: 07:01 a.m.
 *
 * Migracion a Scala: Daniel Menchaca Luna
 *
 */

import scala.io.{Source,BufferedSource}

class LectorArch{
    var sr:BufferedSource = _ 
    def this(nomArch:String){
        this()
        sr = Source.fromFile(nomArch)
    }

    def leeCad {
       // sr.
    }

    def cierra:Unit={
        //Implementar un close
    }

}
