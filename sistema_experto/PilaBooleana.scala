
import scala.collection.mutable
class PilaBooleand{
    var datos:mutable.ArrayList[Any] = null
    def this(){
        datos = mutable.ArrayList.empty[Any]
    }

    def anula : Unit = {
        datos.clear
    }

    def vacia:Boolean ={
        datos.count == 0
    }

    def top:Boolean = {
        if ( vacia ) datos(datos.count-1).asInstanceOf(Boolean) else false
    }

    def pop:Boolean = {
        var tmp:Boolean

        if (vacia){
            return false
        }else{
            tmp = datos(datos.count -1).asInstanceOf(Boolean)
            datos.remove(datos.count - 1)
            return tmp;
        }
    }

    def push(valor:Boolean):Unit = {
        datos += valor
    }
}