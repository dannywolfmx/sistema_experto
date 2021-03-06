/*
 * PilaBooleana
 * 
 * Clase auxiliar, responsable de administrar una pila
 * con una representación de datos específica para el 
 * enfoque de este entorno genérico para sistemas expertos.
 * 
 * En particular, es utilizada para realizar la evaluación
 * lógica de las condiciones compuestas, contenidas en las
 * representaciones LM-Regla; que se encuentran expresadas
 * en notación postfija libre de complejidad en paréntesis
 * y presedencias, y directamente procesable por la maquinaria
 * computacional.
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
 * Fecha: 28/10/2006
 * Hora: 06:04 p.m.
 * 
 * Migracion a Scala: Daniel Menchaca Luna
 */

import scala.collection.mutable

class Regla(){
    var partesCond = mutable.ArrayBuffer.empty[Any];
    var partesConc = mutable.ArrayBuffer.empty[Any];
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
            retorno += (elemCond.asInstanceOf[ParteRegla] + " ")
            
        }

        retorno += "ENTONCES "

        for ( elemConc <- partesConc){
            retorno += (elemConc.asInstanceOf[ParteRegla] + " ")
        }

        retorno
    }

    def probarCondicion(mt:MemoriaTrabajo):Boolean = {
        var pb = new PilaBooleana()
        var verdad1 = false
        var verdad2 = false
        var aTmp:Atomo = null
        var aMT:Atomo = null
        var nTmp:Negacion = null
        var bTmp:Binario = null

        for ( elemCond <- partesCond ){

            if ( elemCond.isInstanceOf[Atomo] ){
                aTmp = elemCond.asInstanceOf[Atomo]
                aMT = mt.recupera(aTmp);
                verdad1=aTmp.verVerdad(aMT);

                pb.push(verdad1);
            }
            else if (elemCond.isInstanceOf[Negacion]){
                nTmp = elemCond.asInstanceOf[Negacion]
                verdad1 = pb.pop
                verdad1 = !verdad1
                pb.push(verdad1)
            }
            else if (elemCond.isInstanceOf[Binario]){
                bTmp = elemCond.asInstanceOf[Binario]
                verdad1 = pb.pop
                verdad2 = pb.pop
                pb.push(
                    if (bTmp.conj) verdad1 && verdad2 else verdad1||verdad2
                )
            }
        }
        return pb.pop
    }

    def dispara(mt:MemoriaTrabajo) : Boolean = {
        var aTmp:Atomo = null;
        var llegoObj:Boolean = false
        disparo = true;
        var atomos = mutable.ArrayBuffer.empty[Any]

        for(elemConc <- partesConc){
            // El nivel de certidumbre que se reciba
            // se asigna a los �tomos conclusi�n
            // que se ingresar�n a la MT.
            if ( elemConc.asInstanceOf[ParteRegla].isInstanceOf[Atomo]){
                aTmp = new Atomo ( elemConc.asInstanceOf[Atomo] )
                atomos += aTmp
                if ( aTmp.Objetivo ) {
                  llegoObj = true; 
                }
            }
            else if ( elemConc.asInstanceOf[ParteRegla].isInstanceOf[Negacion]){
                aTmp.Estado = !aTmp.Estado
            }
        }

        for (aa <- atomos){
            try{
                mt.guardaAtomo(aa.asInstanceOf[Atomo])
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
        var partes = r.split(" ")
        var regla:Boolean = false
        var cond:Boolean = false
        var conc:Boolean = false
        var atomo:Boolean = false
        var obj:Boolean = false
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
                        obj = true
                        objetivo = true;
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
                        var pr = new Negacion()
                        if ( cond && !conc ){
                            partesCond += pr
                        }
                        if ( conc && !cond ){
                            partesConc += pr
                        }
                    }

                    case "<conjuncion/>" =>{
                        var pr = new Binario(true)
                        if ( cond && !conc ){
                            partesCond += pr
                        }
                        if ( conc && !cond ){
                            partesConc += pr
                        }
                    }
                    case "<disyuncion/>" =>{
                        var pr=new Binario(false)
                        if ( cond && !conc) partesCond += pr
                    }
                    case _ =>{
                        if(atomo){

                            var pr = new Atomo(parte,true,obj)
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
