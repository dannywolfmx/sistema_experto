
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