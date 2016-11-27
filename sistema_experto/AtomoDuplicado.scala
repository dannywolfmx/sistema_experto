/*
 * AtomoDuplicado
 * 
 * Es una clase auxiliar que representa la excepci�n 
 * espec�fica de que un �tomo ha sido duplicado al 
 * interior de una regla: en su condici�n compuesta o 
 * conclusi�n m�ltiple, o bien en la memoria de trabajo
 * del propio sistema experto.
 * 
 * Al ser lanzada esta excepci�n, acciona los mecanismos
 * de respuesta al suceso de duplicaci�n de �tomo en los
 * recept�culos dise�ados para ello.
 * 
 * Desarrollador: Luis Alberto Casillas Santill�n
 * Fecha: 28/10/2006
 * Hora: 05:44 p.m.
 * 
 */
case class AtomoDuplicado(s:String) extends Exception(s){

}