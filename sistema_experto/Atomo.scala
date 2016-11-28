/*
 * Atomo
 * 
 * La clase �tomo es esencial para este entorno gen�rico 
 * para sistemas expertos. Representa elementos concretos
 * de la realidad que busca ser procesada a trav�s de un
 * experto artificial modelado al interior del presente
 * entorno.
 * 
 * Esta clase incluye diversos atributos y operadores, a 
 * partir de los cuales logra el tratamiento adecuado de la
 * porci�n m�nima de realidad a tratar, un �tomo. Elemento
 * simb�lico de la programaci�n declarativa, �til en la
 * confecci�n de una ontolog�a.
 * 
 * Desarrollador en C#: Luis Alberto Casillas Santill�n
 * Fecha: 26/10/2006
 * Hora: 07:14 a.m.
 * 
 * Migracion a Scala: Daniel Menchaca Luna
 *
 */



class Atomo extends ParteRegla {
    var desc:String = null;
    var estado:Boolean = false;
    var objetivo:Boolean = false;
    
    def this(desc:String, estado:Boolean, objetivo:Boolean){
      this()
      this.desc = desc.toUpperCase()
      this.estado = (estado && true);
      this.objetivo = objetivo && true;
    }
   
    def this(otro:Atomo){
        this()
        this.desc = new String(otro.Desc)
        this.estado = otro.Estado&&true
        this.objetivo = otro.Objetivo&&true
    }   
    

    //Getter para Desc
    def Desc = desc
    //Setter para Desc
    def Desc_=(value:String):Unit ={ this.desc = value}

    //Getter para Estado
    def Estado = estado
    //Setter para Estado
    def Estado_=(value:Boolean):Unit = this.estado = value

    //Getter para Estado
    def Objetivo:Boolean = {
      return objetivo
    }
    //Setter para Estado
    def Objetivo_=(value:Boolean) : Unit = {
      this.objetivo = value
    }

    def verIgualdad(aTmp:Atomo) : Boolean = {
        desc.equals( aTmp.Desc ) /*&&(estado==aTmp.Estado)*/ ; 
    } 

    def verVerdad ( aTmp:Atomo ) : Boolean = {
        
        if ( aTmp == null ) {
          return false;
        }else{
          return (estado && aTmp.Estado)
        }
        
    }


    //Implementar toString para esta clase
    override def toString() : String = {
        ( if (estado) "" else "!" ) + ( if (objetivo) "*" else "" ) + desc
    }

    //Implementar HashCode 
    //En el codigo original en C# este metodo es GetHashCode
    override def hashCode : Int = {
     	desc.hashCode() ^ ( if (estado) 1 else 0 ) ^ ( if (objetivo) 1 else 0 )
    }

    //Implemenetacion de Equals
    override def equals(obj:Any):Boolean ={
        var aTmp:Atomo  = obj.asInstanceOf[Atomo]
        desc.equals( aTmp.Desc ) && ( estado == aTmp.Estado );
    } 




}