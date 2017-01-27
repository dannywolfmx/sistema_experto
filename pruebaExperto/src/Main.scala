import scala.collection.mutable

object Main{
    var mt = new MemoriaTrabajo()
    var mc = new ModuloConocimiento("Computadoras","/home/daniel/compu.bc.txt")
    var mi = new MotorInferencia()
  
    def main(args:Array[String]){
        var res:mutable.ArrayBuffer[Any] = null
        println("Sistema experto Scala")
        
       // var obd = Array("PELO", "DA_LECHE", "MAMIFERO", "DIENTES_AGUDOS" ,"OJOS_FRENTE" ,"CARNIVORO" ,"COME_CARNE" ,"GARRAS" ,"VISTA_NOCTURNA" ,"FELINO" ,"COLOR_LEONADO" ,"MELENA")
        
   /*    for(i <- obd ){
    		  this.mt.guardaAtomo(new Atomo(i,true,false))
    		}*/
        
    	/*	var miArray = Array("BAHIA_3.5","PUERTO_AUDIO","USB_2.0","GABINETE","IDE","SATA_2.0","SATA_3.0","USB_3.0","VGA","TARJETA_MADRE","GABINETE_SIN_FUENTE","SOCKET_FM2+","TARJETA_MADRE_FM2+","VELOCIDAD_MEMORIA_1600MHZ","TARJETA_MADRE_DDR3","4_NUCLEOS VELOCIDAD_3.80GHZ","VENTILADOR","PROCESADOR_A8-7650K","GIGABYTE","BAHIA_5.25","BAHIA_2.5","GABINETE_GZ-ZGS4B5","KINGSTON","DISIPADOR 8_GB","MEMORIA_HYPERX_FURY_8GB","TARJETA_MADRE_GA-F2A68HM-DS2H","DISCO_DURO_1_GB","TOSHIBA","DISCO_DURO_(DT01ACA100)","DVD");
    		for(i <- miArray ){
    		  this.mt.guardaAtomo(new Atomo(i,true,false))
    		}
    		miArray = Array("FUENTE","VELOCIDAD_MEMORIA_1066MHZ","VELOCIDAD_MEMORIA_1333MHZ")
    		for(i <- miArray){
    		     this.mt.guardaAtomo(new Atomo(i,false,false))

    		}*/
    		
    		println(this.mt);

        res = this.mi.encadenarAtras(this.mc, this.mt);
        
        if (res != null){
          println("Exito: ")
          for(a <- res){
            println(a.asInstanceOf[Atomo] + " ")
          }
//          println(this.mt)
        }
        else {
          println("Fracaso...");
        }
        println(mc.muestraReglasDisparadas);
        
    }
}