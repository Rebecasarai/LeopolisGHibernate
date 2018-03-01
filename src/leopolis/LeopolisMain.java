/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leopolis;

import java.io.File;

/**
 *
 * @author Fran
 * 
 */
public class LeopolisMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creamos la manejadora para usar sus diferentes métodos
        ManejadoraFichero manejadora = new ManejadoraFichero();
        //Xml de entrada
        File xmlEntrada = new File("src\\xml\\Registro.xml");
        //xml de salida
        File xmlDestino=new File("src\\xml\\Incidencias.xml");
        //leemos el fichero y lo procesamos
        manejadora.leerFichero(xmlEntrada);
        //imprimmos los errores y creamos un xml con las incidencia correspondiente
        manejadora.imprimirErrores(xmlDestino);     
    }
}
