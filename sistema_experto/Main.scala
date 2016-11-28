package experto
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