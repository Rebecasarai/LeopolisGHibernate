package objetos;
// Generated 28-feb-2018 21:28:15 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Pastas generated by hbm2java
 */
public class Pastas  implements java.io.Serializable {


     private String nombrePasta;
     private Set ciudadaneses = new HashSet(0);

    public Pastas() {
    }

	
    public Pastas(String nombrePasta) {
        this.nombrePasta = nombrePasta;
    }
    public Pastas(String nombrePasta, Set ciudadaneses) {
       this.nombrePasta = nombrePasta;
       this.ciudadaneses = ciudadaneses;
    }
   
    public String getNombrePasta() {
        return this.nombrePasta;
    }
    
    public void setNombrePasta(String nombrePasta) {
        this.nombrePasta = nombrePasta;
    }
    public Set getCiudadaneses() {
        return this.ciudadaneses;
    }
    
    public void setCiudadaneses(Set ciudadaneses) {
        this.ciudadaneses = ciudadaneses;
    }




}


