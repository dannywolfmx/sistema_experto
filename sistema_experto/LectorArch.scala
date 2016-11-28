package experto
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