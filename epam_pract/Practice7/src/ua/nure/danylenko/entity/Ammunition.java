package ua.nure.danylenko.entity;

/**
 * <p>Java class for ammunition complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ammunition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Rockets" type="{http://nure.ua/danylenko/entity}rockets"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

public class Ammunition {

    protected int rockets;

    /**
     * Gets the value of the rockets property.
     * 
     */
    public int getRockets() {
        return rockets;
    }

    /**
     * Sets the value of the rockets property.
     * 
     */
    public void setRockets(int value) {
        this.rockets = value;
    }

    @Override
    public String toString() {
        return rockets + "";
    }
}
