//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.01 a las 06:10:28 PM CET 
//


package claseIncidencias;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the claseIncidencias package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IDAsiento_QNAME = new QName("", "ID_Asiento");
    private final static QName _FechaError_QNAME = new QName("", "FechaError");
    private final static QName _Error_QNAME = new QName("", "Error");
    private final static QName _TipoAsiento_QNAME = new QName("", "Tipo_Asiento");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: claseIncidencias
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Incidencias }
     * 
     */
    public Incidencias createIncidencias() {
        return new Incidencias();
    }

    /**
     * Create an instance of {@link Incidencia }
     * 
     */
    public Incidencia createIncidencia() {
        return new Incidencia();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ID_Asiento")
    public JAXBElement<BigInteger> createIDAsiento(BigInteger value) {
        return new JAXBElement<BigInteger>(_IDAsiento_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "FechaError")
    public JAXBElement<String> createFechaError(String value) {
        return new JAXBElement<String>(_FechaError_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Error")
    public JAXBElement<String> createError(String value) {
        return new JAXBElement<String>(_Error_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Tipo_Asiento")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTipoAsiento(String value) {
        return new JAXBElement<String>(_TipoAsiento_QNAME, String.class, null, value);
    }

}
