package ua.nure.danylenko.entity;


/**
 * <p>Java class for charsScout complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="charsScout"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Type" type="{http://nure.ua/danylenko/entity}typeScout"/&gt;
 *         &lt;element name="Places" type="{http://nure.ua/danylenko/entity}places"/&gt;
 *         &lt;element name="Radar" type="{http://nure.ua/danylenko/entity}radar"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

public class CharsScout {

    protected TypeScout type;
    protected int places;
    protected boolean radar;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link TypeScout }
     *     
     */
    public TypeScout getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeScout }
     *     
     */
    public void setType(TypeScout value) {
        this.type = value;
    }

    /**
     * Gets the value of the places property.
     * 
     */
    public int getPlaces() {
        return places;
    }

    /**
     * Sets the value of the places property.
     * 
     */
    public void setPlaces(int value) {
        this.places = value;
    }

    /**
     * Gets the value of the radar property.
     * 
     */
    public boolean isRadar() {
        return radar;
    }

    /**
     * Sets the value of the radar property.
     * 
     */
    public void setRadar(boolean value) {
        this.radar = value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(type.value()).append('\n');
        result.append(places).append('\n');
        result.append(radar);
        return result.toString();
    }
}
