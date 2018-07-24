package ua.nure.danylenko.entity;

/**
 * <p>Java class for chars complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chars"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Type" type="{http://nure.ua/danylenko/entity}type"/&gt;
 *         &lt;element name="Places" type="{http://nure.ua/danylenko/entity}places"/&gt;
 *         &lt;element name="Ammunition" type="{http://nure.ua/danylenko/entity}ammunition"/&gt;
 *         &lt;element name="Radar" type="{http://nure.ua/danylenko/entity}radar"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

public class Chars {

    protected Type type;
    protected int places;
    protected Ammunition ammunition;
    protected boolean radar;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setType(Type value) {
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
     * Gets the value of the ammunition property.
     * 
     * @return
     *     possible object is
     *     {@link Ammunition }
     *     
     */
    public Ammunition getAmmunition() {
        return ammunition;
    }

    /**
     * Sets the value of the ammunition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ammunition }
     *     
     */
    public void setAmmunition(Ammunition value) {
        this.ammunition = value;
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
        result.append(ammunition).append('\n');
        result.append(radar);
        return result.toString();
    }
}
