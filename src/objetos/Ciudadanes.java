package objetos;
// Generated 28-feb-2018 21:28:15 by Hibernate Tools 4.3.1


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ciudadanes generated by hbm2java
 */
public class Ciudadanes  implements java.io.Serializable {


     private int id;
     private Ciudadanes ciudadanesByIdPadre;
     private Ciudadanes ciudadanesByIdMadre;
     private Pastas pastas;
     private String nombre;
     private String apellidos;
     private Date fechaNacimiento;
     private Date fechaMuerte;
     private char sexo;
     private short kgPasta;
     private Set ciudadanesesForIdPadre = new HashSet(0);
     private Set matrimoniosesForIdconyuge2 = new HashSet(0);
     private Set matrimoniosesForIdconyuge1 = new HashSet(0);
     private Set ciudadanesesForIdMadre = new HashSet(0);

    public Ciudadanes() {
    }

	
    public Ciudadanes(int id, Pastas pastas, String nombre, String apellidos, Date fechaNacimiento, char sexo, short kgPasta) {
        this.id = id;
        this.pastas = pastas;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.kgPasta = kgPasta;
    }
    public Ciudadanes(int id, Ciudadanes ciudadanesByIdPadre, Ciudadanes ciudadanesByIdMadre, Pastas pastas, String nombre, String apellidos, Date fechaNacimiento, Date fechaMuerte, char sexo, short kgPasta, Set ciudadanesesForIdPadre, Set matrimoniosesForIdconyuge2, Set matrimoniosesForIdconyuge1, Set ciudadanesesForIdMadre) {
       this.id = id;
       this.ciudadanesByIdPadre = ciudadanesByIdPadre;
       this.ciudadanesByIdMadre = ciudadanesByIdMadre;
       this.pastas = pastas;
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.fechaNacimiento = fechaNacimiento;
       this.fechaMuerte = fechaMuerte;
       this.sexo = sexo;
       this.kgPasta = kgPasta;
       this.ciudadanesesForIdPadre = ciudadanesesForIdPadre;
       this.matrimoniosesForIdconyuge2 = matrimoniosesForIdconyuge2;
       this.matrimoniosesForIdconyuge1 = matrimoniosesForIdconyuge1;
       this.ciudadanesesForIdMadre = ciudadanesesForIdMadre;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Ciudadanes getCiudadanesByIdPadre() {
        return this.ciudadanesByIdPadre;
    }
    
    public void setCiudadanesByIdPadre(Ciudadanes ciudadanesByIdPadre) {
        this.ciudadanesByIdPadre = ciudadanesByIdPadre;
    }
    public Ciudadanes getCiudadanesByIdMadre() {
        return this.ciudadanesByIdMadre;
    }
    
    public void setCiudadanesByIdMadre(Ciudadanes ciudadanesByIdMadre) {
        this.ciudadanesByIdMadre = ciudadanesByIdMadre;
    }
    public Pastas getPastas() {
        return this.pastas;
    }
    
    public void setPastas(Pastas pastas) {
        this.pastas = pastas;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public Date getFechaMuerte() {
        return this.fechaMuerte;
    }
    
    public void setFechaMuerte(Date fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }
    public char getSexo() {
        return this.sexo;
    }
    
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    public short getKgPasta() {
        return this.kgPasta;
    }
    
    public void setKgPasta(short kgPasta) {
        this.kgPasta = kgPasta;
    }
    public Set getCiudadanesesForIdPadre() {
        return this.ciudadanesesForIdPadre;
    }
    
    public void setCiudadanesesForIdPadre(Set ciudadanesesForIdPadre) {
        this.ciudadanesesForIdPadre = ciudadanesesForIdPadre;
    }
    public Set getMatrimoniosesForIdconyuge2() {
        return this.matrimoniosesForIdconyuge2;
    }
    
    public void setMatrimoniosesForIdconyuge2(Set matrimoniosesForIdconyuge2) {
        this.matrimoniosesForIdconyuge2 = matrimoniosesForIdconyuge2;
    }
    public Set getMatrimoniosesForIdconyuge1() {
        return this.matrimoniosesForIdconyuge1;
    }
    
    public void setMatrimoniosesForIdconyuge1(Set matrimoniosesForIdconyuge1) {
        this.matrimoniosesForIdconyuge1 = matrimoniosesForIdconyuge1;
    }
    public Set getCiudadanesesForIdMadre() {
        return this.ciudadanesesForIdMadre;
    }
    
    public void setCiudadanesesForIdMadre(Set ciudadanesesForIdMadre) {
        this.ciudadanesesForIdMadre = ciudadanesesForIdMadre;
    }

    @Override
    public String toString() {
        return "Ciudadanes{" + "id=" + id + ", ciudadanesByIdPadre=" + ciudadanesByIdPadre + ", ciudadanesByIdMadre=" + ciudadanesByIdMadre + ", pastas=" + pastas + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", fechaMuerte=" + fechaMuerte + ", sexo=" + sexo + ", kgPasta=" + kgPasta + ", ciudadanesesForIdPadre=" + ciudadanesesForIdPadre + ", matrimoniosesForIdconyuge2=" + matrimoniosesForIdconyuge2 + ", matrimoniosesForIdconyuge1=" + matrimoniosesForIdconyuge1 + ", ciudadanesesForIdMadre=" + ciudadanesesForIdMadre + '}';
    }




}


