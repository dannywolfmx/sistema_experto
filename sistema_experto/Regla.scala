import scala.collection.mutable

class Regla{
    var parteCond = new mutable.ArrayBuffer.empty[Any];
    var parteConc = new mutable.ArrayBuffer.empty[Any];
    var marca = false
    var disparo = false
    var objetivo = false

    def this(reglaCad:String){
        this()
        analiza(reglaCad)
    }

    override def toString:String ={
        var retorno = "Si "

        for ( elemCond <- partesCond){
            retorno += (elemCond + " ")
        }

        retorno += "ENTONCES "

        for ( elemCond <- partesConc){
            retorno += (elemCond + " ")
        }

        retorno
    }

    def probarCondicion(mt:MemoriaTrabajo):Boolean = {
        var pb = new PilaBooleana()
        var verdad1 = false
        var verdad2 = false
        aTmp:Atomo = null
        aMt:Atomo = null
        nTmp:Negacion = null
        bTmp:Binario = null

        for ( elemCond <- parteCond ){

            if ( elemCond.instanceOf(Atomo) ){
                aTmp = elemCond.asInstanceOf(Atomo)
                aMT = mt.recupera(aTmp);

                verdad1=aTmp.verVerdad(aMT);
                pb.push(verdad1);
            }
            else if (elemCond.instanceOf(Negacion)){
                nTmp = elemCond.asInstanceOf(Negacion)
                verdad1 = pb.pop
                verdad1 = !verdad1
                pb.push(verdad1)
            }
            else if (elemCond.instanceOf(Binario)){
                bTmp = elemCond.asInstanceOf(Binario)
                verdad1 = pb.pop
                verdad2 = pb.pop
                pb.push(
                    if (bTmp.conj) verdad1 && verdad2 else verdad1||verdad2
                )
            }
        }
        return pb.pop
    }

    def disparo(mt:MemoriaTrabajo) : Boolean = {
        var aTmp:Atomo = null;
        var llegoObj:Boolean = false
        disparo = true;
        var atomos = new mutable.ArrayBuffer.empty[Any]

        for(elemCond <- partesConc){
            // El nivel de certidumbre que se reciba
            // se asigna a los �tomos conclusi�n
            // que se ingresar�n a la MT.
            if ( elemConc.instanceOf(Atomo)){
                aTmp = new Atomo ( elemConc.asInstanceOf(Atomo) )
                atomo += aTmp
                if ( aTmp.Objetivo ) llegoObj = true; 
            }
            else if ( elemconc.instanceOf(Negacion)){
                aTmp.Estado = !aTmp.Estado
            }
        }

        for (aa <- atomos){
            try{
                mt.guardaAtomo(aa)
            }catch{
                case ad:AtomoDuplicado => println("Se duplico el atomo: "+aa)
            }
        }
        return llegoObj
    } 

    def esObjetivo():Boolean = {
        return objetivo
    }

    def analiza(r:String):Unit = {
        var partes = r.split("")
        var regla:Boolean = _
        var cond:Boolean = _
        var conc:Boolean = _
        var atomo:Boolean = _
        var obj:Boolean = _

        for( parte <- partes){
                parte match{
                    case "<atomo>" =>{
                        atomo = true
                        obj = false
                    }
                    case "</atomo>" =>{
                        atomo = false
                        obj = false
                    }
                    case "<atomoObj>" =>{
                        atomo = true
                        obj = false
                    }
                    case "</atomoObj>" =>{
                        atomo = false
                        obj = false
                    }
                    case "<condicion>" =>{
                        cond = true
                    }
                    case "</condicion>" =>{
                        cond = false
                    }
                    case "<conclusion>" =>{
                        conc = true
                    }
                    case "</conclusion>" =>{
                        conc = false
                    }
                    // Etiquetas sencillas
                    case "<negacion/>" =>{
                        pr = new Negacion()
                        if ( cond && !conc ){
                            partesCond += pr
                        }
                        if ( conc && !cond ){
                            partesConc += pr
                        }
                    }

                    case "<conjuncion/>" =>{
                        pr = new Binario(true)
                        if ( cond && !conc ){
                            partesCond += pr
                        }
                        if ( conc && !cond ){
                            partesConc += pr
                        }
                    }
                    case "<disyuncion/>" =>{
                        pr=new Binario(false)
                        if ( cond && !conc) partesCond += pr
                    }
                    case _ =>{
                        if(atomo){
                            pr = new Atomo(parte,true,obj)
                            if (cond && !conc && !obj){
                                partesCond += pr
                            }
                            if (conc && !cond){
                                partesConc += pr
                            }
                        }
                    }

                }
        }
        
    }
}