
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leopolis;

import clasesJaxb.Asiento;
import clasesJaxb.Madre;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import objetos.Ciudadanes;
import objetos.Matrimonios;
import objetos.Pastas;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.query.Query;

/**
 *
 * @author fran9
 */
public class ManejadoraBBDD {
    private String query;


    public void insertCiudadane(Session session, Asiento unAsiento) {
       try{
        Transaction tran;
       // Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = session.beginTransaction();
        Ciudadanes ciudadano = new Ciudadanes();
        
        ciudadano.setNombre(unAsiento.getCiudadane().get(0).getNombre());
        ciudadano.setId(unAsiento.getCiudadane().get(0).getID().intValueExact());
        
        Pastas p = new Pastas( unAsiento.getCiudadane().get(0).getPastaFavorita());
        ciudadano.setPastas( p);
        ciudadano.setApellidos(unAsiento.getCiudadane().get(0).getApellidos());
        ciudadano.setSexo(unAsiento.getCiudadane().get(0).getSexo().charAt(0));
        
        
        System.out.println(""+unAsiento.getCiudadane().get(0).getMadre().getID());
        ciudadano.setCiudadanesByIdMadre(recuperarCiudadane(unAsiento.getCiudadane().get(0).getMadre().getID().intValue(), session));
        ciudadano.setCiudadanesByIdPadre(recuperarCiudadane(unAsiento.getCiudadane().get(0).getPadre().getID().intValue(), session));
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = unAsiento.getCiudadane().get(0).getFechaNacimiento();

        Date date = formatter.parse(dateInString);
        System.out.println(date);
        System.out.println(formatter.format(date)); 
        ciudadano.setFechaNacimiento(date);
        
        System.out.println("Nombre "+  ciudadano.getNombre()+"y apellidos: "+ciudadano.getApellidos());

        session.save(ciudadano);
        tran.commit();
        } catch (ParseException e) {
            System.out.println("error insertando");
            System.out.println(e.getMessage());
        } catch(JDBCException e) {
            SQLException cause = (SQLException) e.getCause();
            //evaluate cause and find out what was the problem
            System.out.println(cause.getMessage());
        }
        
    }
    
    /**
     * String a Fecha.
     * @param fecha
     * @return 
     */
    public Date convertirAFecha(String fecha){
        Date date = null;
        try {
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = fecha;

            date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date)); 

        } catch (ParseException e) {
            System.out.println("error insertando");
            e.printStackTrace();
        }
        return date;
    }
    
    
    
    /**
     * Inserta un matrimonio nuevo.
     * @param session
     * @param unAsiento 
     */
    public void insertMatrimonio(Session session, Asiento unAsiento) {
        Transaction tran;
        //Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = session.beginTransaction();
        Matrimonios matrimonios = new Matrimonios();
        
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = unAsiento.getFecha();

            Date date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date)); 
            matrimonios.setFechaMatrimonio(date);

        } catch (ParseException e) {
            System.out.println("error insertando");
            e.printStackTrace();
        }
        //matrimonios.setCiudadanesByIdconyuge1(id);
	// Al ejecutar el método save el objeto se convierte en persistente
        System.out.println(unAsiento.getCiudadane().get(0).getID());
        
        
        Ciudadanes ciudaden1 = new Ciudadanes();
        ciudaden1.setId(unAsiento.getCiudadane().get(0).getID().intValue());
        
        
        Ciudadanes ciudaden2 = new Ciudadanes();
        ciudaden2.setId(unAsiento.getCiudadane().get(1).getID().intValue());
        
        matrimonios.setCiudadanesByIdconyuge1(ciudaden1);
        matrimonios.setCiudadanesByIdconyuge1(ciudaden2);
        
        //No puede deserializarse
        session.save(matrimonios);
        tran.commit();
        
    }
    

    /**
     * Actualiza el matrimonio para realizarse el divorcio
     * @param session
     * @param unAsiento 
     */
    public void updateMatrimonio(Session session, Asiento unAsiento) {
        try{
        Transaction tran; 
       // Session ses = HibernateUtil.buildSessionFactory().openSession();
       
        tran = session.beginTransaction();
        Matrimonios matrimonios = recuperarMatrimonio(unAsiento.getMatrimonio().intValue(), session);
        Date date = convertirAFecha(unAsiento.getFecha());
        matrimonios.setFechaFin(date);
        System.out.println("id del matrimonio: "+matrimonios.getId()+" Fecha:"+matrimonios.getFechaMatrimonio()+" Fecha fin: "+matrimonios.getFechaMatrimonio());
        
        session.update(matrimonios);
        tran.commit();
        
            } catch(ConstraintViolationException e) {
               System.out.println(""+e);
            } catch(JDBCConnectionException e) {
                
               System.out.println(""+e);
        }
    }
    
    /**
     * Recupera el ciudadane de la sesión.
     * @param id
     * @param session
     * @return 
     */
    public Ciudadanes recuperarCiudadane(int id, Session session){
       Ciudadanes ciudadane;
        //int i = id.intValueExact() ;
        //Session ses = HibernateUtil.buildSessionFactory().openSession();
        ciudadane = (Ciudadanes)session.get(Ciudadanes.class, id);
        return ciudadane;
    }
    
    /**
     * Recupera de la sesión hibernate el objeto matrimonio segun su id
     * @param id
     * @param session
     * @return 
     */
    public Matrimonios recuperarMatrimonio(int id, Session session){
        Matrimonios matrimonio;
        //int i = id.intValueExact() ;
        //Session ses = HibernateUtil.buildSessionFactory().openSession();
        matrimonio = (Matrimonios)session.get(Matrimonios.class, id);
        return matrimonio;
    }
    
    
    
    public void updateCiudadane (Session session, Asiento unAsiento){
        /*Transaction tran; 
        tran = session.beginTransaction();
        session.update(unAsiento.getCiudadane());
        tran.commit();  */
        Transaction tran; 
       // Session ses = HibernateUtil.buildSessionFactory().openSession();
       
        tran = session.beginTransaction();
        Ciudadanes ciudadane = recuperarCiudadane(unAsiento.getCiudadane().get(0).getID().intValue(), session);
        Date date = convertirAFecha(unAsiento.getFecha());
        ciudadane.setFechaMuerte(date);
        System.out.println("id del ciudadeane: "+ciudadane.getId()+" Fecha de deceso:"+ciudadane.getFechaMuerte());
        
        session.update(ciudadane);
        tran.commit();
    }
    
   public Long comprobarIdInexistente(BigInteger id, Session session) {
        query = "select count(ID) from Ciudadanes where ID="+id;
        return (Long) session.createQuery(query).getSingleResult();
   }
   
   /**
    * asfafa
    * @param nombrePasta
    * @param session
    * @return 
    */
   public Long comprobarPasta(String nombrePasta, Session session) {
        query = "select count(NombrePasta) from Pastas where NombrePasta='"+nombrePasta+"'";
        return (Long) session.createQuery(query).getSingleResult();
   }
    
    /**
     * Para comparar fechas
     * 
     */
     public int compararFechasConDate(String fecha1, String fechaActual) {  
        int resultado=0;
        try {
            /**Obtenemos las fechas enviadas en el formato a comparar*/
             SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); 
             Date fechaDate1 = formateador.parse(fecha1);
             Date fechaDate2 = formateador.parse(fechaActual);
             if ( fechaDate1.before(fechaDate2) ){
                  resultado= -1;//La Fecha 1 es menor
             }else{
              if ( fechaDate2.before(fechaDate1) ){
                  resultado= 1;//La Fecha 1 es mayor
              }else{
                 resultado= 0;
              } 
             }
        } catch (ParseException e) {
               System.out.println("Se Produjo un Error!!!  "+e.getMessage());
        }  
        return resultado;
    }
     
     
     
     /**
     * Para comparar fechas
     * 
     */
     public boolean fechaMayorQue(String fecha1, String fechaActual) {  
        boolean resultado=false;
        try {
            /**Obtenemos las fechas enviadas en el formato a comparar*/
             SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); 
             Date fechaDate1 = formateador.parse(fecha1);
             Date fechaDate2 = formateador.parse(fechaActual);
             if ( fechaDate1.before(fechaDate2) ){
                  resultado= false;//La Fecha 1 es menor
             }else{
              if ( fechaDate2.before(fechaDate1) ){
                  resultado= true;//La Fecha 1 es mayor
              }else{
                 resultado= false;
              } 
             }
        } catch (ParseException e) {
               System.out.println("Se Produjo un Error!!!  "+e.getMessage());
        }  
        return resultado;
    }
     
     
     /**
      * 
      * @param id
      * @param session
      * @return 
      */
     public Long comprobarCasado(BigInteger id, Session session){
          query = "select count(ID) from Matrimonios where IDConyuge1="+id+" and Fecha_fin is not null";
          return (Long) session.createQuery(query).getSingleResult();
     }
     
     
     /**
      * 
      * @param id
      * @param session
      * @return 
      */
     public Long comprobarFeDeVida(BigInteger id, Session session){
         query = "select count(ID) from Ciudadanes where Fecha_muerte is  null and ID="+id;
         return (Long) session.createQuery(query).getSingleResult();
     }
     
     
     public Long comprobarExistenciaDEMatrimonio(BigInteger id, Session session){
         query = "select count(ID) from Matrimonios where  ID="+id;
         return (Long) session.createQuery(query).getSingleResult();
     }
     
     public Long comprobarHuerfano(BigInteger id, BigInteger padre,Session session){
        query = "select count(ID) from Ciudadanes where ID_madre ="+ id + "or ID_padre="+id;
        return (Long) session.createQuery(query).getSingleResult();
     }
     
     public Boolean divorcioAntesQueMatrimonio(BigInteger id, Session session, String fechaDivorcio){
        //query = "select Fecha_matrimonio from Matrimonios where id=1";
        TypedQuery consulta;
        //Session ses = HibernateUtil.buildSessionFactory().openSession();
        String ordenConsulta = "from Matrimonios where id ="+id;
        consulta = session.createQuery(ordenConsulta);
        Matrimonios matrimonio = (Matrimonios) consulta.getSingleResult();
        System.out.println("Fecha matrimonio "+matrimonio.getFechaMatrimonio()+ " y Divorcio"+ fechaDivorcio);
       
        Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSistema=formateador.format(matrimonio.getFechaMatrimonio());
        
        return fechaMayorQue(fechaSistema, fechaDivorcio );
         
     }
     
     
     /**
      * Metodo que consulta si el matrimonio ha sido ya disuelto.
      * Si el matrimonio tiene fecha fin, entonces ya estaban divorciados
      * @param id
      * @param session
      * @return 
      */
     public Boolean matrimonioYaDisuelto(BigInteger id, Session session){
         Boolean divorciados=false;
        TypedQuery consulta;
        //Session ses = HibernateUtil.buildSessionFactory().openSession();
        String ordenConsulta = "from Matrimonios where id ="+id;
        consulta = session.createQuery(ordenConsulta);
        Matrimonios matrimonio = (Matrimonios) consulta.getSingleResult();
        
        divorciados = matrimonio.getFechaFin()!=null;
        return divorciados;
     }
     
                 /*       try{
                        SimpleDateFormat parseador = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaRecogida = parseador.parse(unAsiento.getFecha());
                        
                        int anno=obtenerAnno(fechaRecogida);
                        int mes= obtenerMes(fechaRecogida);
                        int dia=obtenerDia(fechaRecogida);
                        
                        
                        String fechaTransformada = anno+"/"+mes+"/"+dia;
                        
         
                    } catch (ParseException e) {
                      System.out.println("Se Produjo un Error!!!  "+e.getMessage());
                    }  
                        */
     
     
     
}
