/*
 * Consultor
 * 
 * Clase auxiliar responsable de consultar al usuario del
 * entorno gen�rico de sistema experto acerca del 
 * cumplimiento de diferentes hechos que podr�an darse en
 * el caso de la realidad que se verifica.
 * 
 * De acuerdo al funcionamiento convencional de un sistema
 * experto, se deben tomar hechos de la realidad y luego 
 * confrontarlos con el conocimiento almacenado en la ontolog�a
 * o base de conocimientos; el resultado es informaci�n �til,
 * normalmente conocida como la meta del sistema experto.
 * 
 * Datos ---> Sistema Experto---> Informaci�n
 *                  ^
 *                  |
 *             Conocimiento
 * 
 * Desarrollador: Luis Alberto Casillas Santill�n
 * Fecha: 29/10/2006
 * Hora: 06:42 a.m.
 * 
 */

 object Consultor{
     def porAtomo(aa:Atomo,ra:Regla):Boolean = {
         var resp:String
         do{

             println("Se cumple " + aa.Desc + "? (S/N/P)"  );
             resp = scala.io.StdIn.readLine();
             resp = resp.ToUpperCase

             if (resp.equals("P")){
                 println("Se intenta probar que: "+ra)
             }
        } while ( !resp.equals("S") && !resp.equals("N") )
        resp.equals("S")
     }
 }