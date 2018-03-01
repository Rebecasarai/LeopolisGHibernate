package objetos;
// Generated 28-feb-2018 21:28:15 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Matrimonios generated by hbm2java
 */
public class Matrimonios  implements java.io.Serializable {


     private int id;
     private Ciudadanes ciudadanesByIdconyuge2;
     private Ciudadanes ciudadanesByIdconyuge1;
     private Date fechaMatrimonio;
     private Date fechaFin;

    public Matrimonios() {
    }

	
    public Matrimonios(int id, Ciudadanes ciudadanesByIdconyuge2, Ciudadanes ciudadanesByIdconyuge1, Date fechaMatrimonio) {
        this.id = id;
        this.ciudadanesByIdconyuge2 = ciudadanesByIdconyuge2;
        this.ciudadanesByIdconyuge1 = ciudadanesByIdconyuge1;
        this.fechaMatrimonio = fechaMatrimonio;
    }
    public Matrimonios(int id, Ciudadanes ciudadanesByIdconyuge2, Ciudadanes ciudadanesByIdconyuge1, Date fechaMatrimonio, Date fechaFin) {
       this.id = id;
       this.ciudadanesByIdconyuge2 = ciudadanesByIdconyuge2;
       this.ciudadanesByIdconyuge1 = ciudadanesByIdconyuge1;
       this.fechaMatrimonio = fechaMatrimonio;
       this.fechaFin = fechaFin;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Ciudadanes getCiudadanesByIdconyuge2() {
        return this.ciudadanesByIdconyuge2;
    }
    
    public void setCiudadanesByIdconyuge2(Ciudadanes ciudadanesByIdconyuge2) {
        this.ciudadanesByIdconyuge2 = ciudadanesByIdconyuge2;
    }
    public Ciudadanes getCiudadanesByIdconyuge1() {
        return this.ciudadanesByIdconyuge1;
    }
    
    public void setCiudadanesByIdconyuge1(Ciudadanes ciudadanesByIdconyuge1) {
        this.ciudadanesByIdconyuge1 = ciudadanesByIdconyuge1;
    }
    public Date getFechaMatrimonio() {
        return this.fechaMatrimonio;
    }
    
    public void setFechaMatrimonio(Date fechaMatrimonio) {
        this.fechaMatrimonio = fechaMatrimonio;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }




}


