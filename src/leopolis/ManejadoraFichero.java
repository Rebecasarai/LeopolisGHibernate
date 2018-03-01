/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leopolis;

import claseIncidencias.Incidencia;
import claseIncidencias.Incidencias;
import clasesJaxb.Anotaciones;
import clasesJaxb.Asiento;
import java.io.File;
import java.io.StringWriter;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author rsgonzalez
 */
public class ManejadoraFichero {
    Anotaciones anotaciones;//Listado de asientos
    SessionFactory instancia;//para abrir la instancia de hibernate
    Session session; //para abrir, consultary  cerrar la BBDD
    Incidencias incidencias; //Listado de incidencia
    int numIncidencias =0; //Para el id que sea autoincrementable
    ManejadoraBBDD manejadoraBBDD= new ManejadoraBBDD();
    /*
    Leemos el ficheron XML, para luego procesar los asientos
    */
     public void leerFichero(File xmlPrimero){
        try {
            JAXBContext mContexto;
            mContexto = JAXBContext.newInstance(Anotaciones.class);
            Unmarshaller u = mContexto.createUnmarshaller();
          
            //Guardamos la Lista de anotaciones del documento en el array
            anotaciones = (Anotaciones) u.unmarshal(xmlPrimero);
           
            ///Procesar antotaciones
            ProcesarAnotaciones();

        } catch (JAXBException ex) {
            Logger.getLogger(ManejadoraFichero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      /*
    En este método recorremos la lista de Anotaciones creada previavemnte en la llamada de leerFichero
    */
     public void ProcesarAnotaciones(){
         //Hibernate
        instancia = HibernateUtil.buildSessionFactory();
        //Abrir la BBDD
        session = instancia.openSession();
        incidencias= new Incidencias();
        
        //Recorremos la lista 
        for(Asiento unAsiento:anotaciones.getAsiento()){
            ValidarAnotaciones(unAsiento);
        }
       //Cerramos Conexiones
        session.close();
        instancia.close();
        
    }
      /*
    Imprimimos por xml y por pantalla un listado de incidencias
    */
      public void imprimirErrores(File archivoXML){
            JAXBContext contexto; 
        try {
            //Miramos si contiene alguna incidecia para mostrarlo
            if(incidencias.getIncidencia().size()!=0){
            contexto = JAXBContext.newInstance(Incidencias.class);
            Marshaller marshalero = contexto.createMarshaller();
            marshalero.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter escribiente = new StringWriter();
            marshalero.marshal(incidencias, archivoXML);
            // ahora lo marshaleamos a un stream para visualizarlo
            marshalero.marshal(incidencias, escribiente);
            System.out.println("-----------------");
            System.out.println(escribiente.toString());
            System.out.println("-----------------");
            }
            else{
                System.out.println("Ninguna Incidencia");
            }
        } catch (JAXBException ex) {
           Logger.getLogger(ManejadoraFichero.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      }
     
        //Me queda aun esto:
      //Mirar la validacion del madre  y de la padre
      //Un divorcio con una fecha anterior al matrimonio 
      //Un divorcio para un matrimonio que ya estaba disuelto (divorcio anterior).
      
      //Esto se tiene que mejorar el codigo es muy largo y se podra acotar
      // y estructuarlo mucho mejor

      
    /**
    Aqui vamos mirando un Asiento si es valido se procesará en la BBDD 
    si no se insertara en un listado de incidencias para mostrarlo posteriomente
    */
    private void ValidarAnotaciones(Asiento unAsiento) {
        Boolean insertar =true;
        String query;
        Long count;
        Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSistema=formateador.format(fechaActual);
        String error="";
        
        
        
        
         switch (unAsiento.getTipo()) {
            case "Nacimiento": 
                /////Ciudadane con un ID inexistente
                    /*if(manejadoraBBDD.comprobarIdInexistente(unAsiento.getCiudadane().get(0).getID(), session)>0){
                        insertar=false;
                        error=insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                    }
                 /////Ciudadane con un tipo de pasta favorita inexistente      
                    if(manejadoraBBDD.comprobarPasta(unAsiento.getCiudadane().get(0).getPastaFavorita(), session)==0){
                        error="Ciudadane con un tipo de pasta favorita inexistente";
                        insertar=false;
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                    }*/
                    /////Ciudadane con una fecha de nacimiento futura.
                    if(manejadoraBBDD.compararFechasConDate(unAsiento.getCiudadane().get(0).getFechaNacimiento(),fechaSistema)>=0){
                        insertar=false;
                        error="Ciudadane con una fecha de nacimiento futura";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());

                    }
                    
                    /////Ciudadane con un ID de padre o madre que no existe 
                    /**
                     * Hacer la conuslta bien en una sola aparte. 
                     * Consulta del id del padre y la madre
                     */
                    /*if(manejadoraBBDD.comprobarIdInexistente(unAsiento.getCiudadane().get(0).getMadre().getID(), session)==0){
                        error="ID madre que no existe ";
                        insertar=false;
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                    }
                    
                    if(manejadoraBBDD.comprobarIdInexistente(unAsiento.getCiudadane().get(0).getPadre().getID(), session)==0){
                        error="ID Padre que no existe ";
                        insertar=false;
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                    }
                    
                    if(manejadoraBBDD.comprobarIdInexistente(unAsiento.getCiudadane().get(0).getMadre().getID(), session)==0
                            ||manejadoraBBDD.comprobarIdInexistente(unAsiento.getCiudadane().get(0).getPadre().getID(), session)==0){
                        error="ID de uno de sus padres que no existe ";
                        insertar=false;
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                    }*/
                     
                    if(insertar){
                       manejadoraBBDD.insertCiudadane(session,unAsiento);
                       System.out.println("Insertar Nacimiento");     
                    }
                     
                break;
            case "Matrimonio": 
                ///Un matrimonio que incluya un id de Ciudadane que no existe 
                    //query = "select count(ID) from Ciudadanes where ID="+unAsiento.getCiudadane().get(0).getID();
                    //count = (Long) session.createQuery(query).getSingleResult();
                    if(manejadoraBBDD.comprobarIdInexistente(unAsiento.getCiudadane().get(0).getID(), session)==0){
                        insertar=false;
                        error="Conyuje1 con un ID inexistente ";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                    }
                    
                    //query = "select count(ID) from Ciudadanes where ID="+unAsiento.getCiudadane().get(1).getID();
                    //count = (Long) session.createQuery(query).getSingleResult();
                    if(manejadoraBBDD.comprobarIdInexistente(unAsiento.getCiudadane().get(1).getID(), session)==0){
                        insertar=false;
                        error="Conyuje2 con un ID inexistente ";                      
                    }
                    /// Un matrimonio que incluya un ciudadane que ya esté casade 
                    //query = "select count(ID) from Matrimonios where IDConyuge1="+unAsiento.getCiudadane().get(0).getID()+" and Fecha_fin is not null";
                    //count = (Long) session.createQuery(query).getSingleResult();
                    if(manejadoraBBDD.comprobarCasado(unAsiento.getCiudadane().get(0).getID(), session)>=1){
                        insertar=false;
                        error="Conyuje1 ya esta casado";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                        
                    }
                    
                    
                    //query = "select count(ID) from Matrimonios where IDConyuge2="+unAsiento.getCiudadane().get(1).getID()+" and Fecha_fin is not null";
                    //count = (Long) session.createQuery(query).getSingleResult();
                    if(manejadoraBBDD.comprobarCasado(unAsiento.getCiudadane().get(1).getID(), session)>=1){
                        insertar=false;
                        error="Conyuje2 ya esta casado";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                    }
                    
                   // Un matrimonio en el que uno de los contrayentes hay fallecido 
                    //query = "select count(ID) from Ciudadanes where Fecha_muerte is  null and ID="+unAsiento.getCiudadane().get(0).getID();
                    //count = (Long) session.createQuery(query).getSingleResult();
                    if(manejadoraBBDD.comprobarFeDeVida(unAsiento.getCiudadane().get(0).getID(), session)==0){
                        insertar=false;
                        error="Conyuje1 había fallecido";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                    }
                    
                    //query = "select count(ID) from Ciudadanes where Fecha_muerte is  null and ID="+unAsiento.getCiudadane().get(1).getID();
                    //count = (Long) session.createQuery(query).getSingleResult();
                    if(manejadoraBBDD.comprobarFeDeVida(unAsiento.getCiudadane().get(1).getID(), session)==0){
                        insertar=false;
                        error="Conyuje2 había fallecido";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                    }
                    
                     /////Un matrimonio con una fecha futura 
                    if(manejadoraBBDD.compararFechasConDate(unAsiento.getFecha(),fechaSistema)>=0){
                        insertar=false;
                        error="Un matrimonio con una fecha futura ";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                    }
                    if(insertar==true){
                       manejadoraBBDD.insertMatrimonio(session,unAsiento);
                       System.out.println("Insertar Matrimonio");    
                    }
                      
         
                break;
            case "Divorcio": 
                //Un divorcio para un matrimonio que no existe 
                    //query = "select count(ID) from Matrimonios where  ID="+unAsiento.getMatrimonio();
                    //count = (Long) session.createQuery(query).getSingleResult();

                    /*if(manejadoraBBDD.comprobarExistenciaDEMatrimonio(unAsiento.getMatrimonio(), session)==0){
>>>>>>> Matrimonios, divorcios, decesos, nacimientos no chinos
                        insertar=false;
                        error="Matrimonio que no existe ";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());      
                    }
                    //ME falta lo que hay que hacer es traer la fecha cambiar de formato y
                    //compararla con el metodo que ya tengo abajo
                   //Un divorcio con una fecha anterior al matrimonio 
                    
                    if(manejadoraBBDD.divorcioAntesQueMatrimonio(unAsiento.getMatrimonio(), session, unAsiento.getFecha())){
                        insertar=false;
                        error="Fecha de divorcio anterior al matrimonio";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());      
                        
                    }
                    
                    //Un divorcio para un matrimonio que ya estaba disuelto (divorcio anterior).
                    
                    if(manejadoraBBDD.matrimonioYaDisuelto(unAsiento.getMatrimonio(), session)){
                        insertar=false;
                        error="Matrimonio ya disuelto";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());   
                    }
                    
<<<<<<< HEAD
                    
                    if(insertar){
                        manejadoraBBDD.updateMatrimonio(session, unAsiento);
                        System.out.println("Actualizar Matrimonio"); 
                    }
                    */
                    //if(insertar){
                        manejadoraBBDD.updateMatrimonio(session, unAsiento);
                        System.out.println("Actualizar Matrimonio"); 
                   //}
                     
                 break;
            case "Deceso": 
                //La muerte de un ciudadane que no existe
                //query = "select count(ID) from Ciudadanes where ID="+unAsiento.getCiudadane().get(0).getID();
                //count = (Long) session.createQuery(query).getSingleResult();
                /*if(manejadoraBBDD.comprobarIdInexistente(unAsiento.getCiudadane().get(0).getID(), session)==0){
>>>>>>> Matrimonios, divorcios, decesos, nacimientos no chinos
                    insertar=false;
                    error="Ciudadane con un ID inexistente ";
                    insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());
                
                }else{
                    //La muerte de un ciudadane que ya estaba muerto
                    //query = "select count(ID) from Ciudadanes where ID="+unAsiento.getCiudadane().get(0).getID()+" and Fecha_muerte is null";
                    //count = (Long) session.createQuery(query).getSingleResult();
                    if(manejadoraBBDD.comprobarFeDeVida(unAsiento.getCiudadane().get(0).getID(), session)==0){
                        insertar=false;
                        error="Ciudadane  ya estaba muerto ";
                        insertarIncidencia(unAsiento.getID(),unAsiento.getTipo(),error,fechaActual.toString());

                    }
                }
<<<<<<< HEAD
                if(insertar){
                    // manejadoraBBDD.updateCiudadane(session, unAsiento);
                     System.out.println("Actualizar Ciiudadane"); 
                }
                if(insertar){*/
                     manejadoraBBDD.updateCiudadane(session, unAsiento);
                     System.out.println("Actualizar Ciiudadane"); 
                //}
                break;
         }   
            
    }

     
     public int obtenerAnno(Date date){

        if (null == date){
            return 0;
        }
        else{

            String formato="yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));
        }
     }
     public int obtenerMes(Date date){

        if (null == date){
            return 0;
        }
        else{
            String formato="MM";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));
        }
     }
     public int obtenerDia(Date date){

        if (null == date){
            return 0;
        }
        else{
            String formato="dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));
        }
}

    private String insertarIncidencia(BigInteger id, String tipo, String error, String fecha) {
       Incidencia incidencia = new Incidencia();
       incidencia.setID(BigInteger.valueOf(numIncidencias));
       incidencia.setIDAsiento(id);
       incidencia.setTipoAsiento(tipo);
       incidencia.setError(error);
       incidencia.setFechaError(fecha);
       incidencias.getIncidencia().add(incidencia);
       numIncidencias++;
       
       return "yava";
    }
}
