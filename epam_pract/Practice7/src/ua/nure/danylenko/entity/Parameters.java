package ua.nure.danylenko.entity;

/**
 * <p>Java class for parameters complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parameters"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Length" type="{http://nure.ua/danylenko/entity}unitAttribute"/&gt;
 *         &lt;element name="Width" type="{http://nure.ua/danylenko/entity}unitAttribute"/&gt;
 *         &lt;element name="Height" type="{http://nure.ua/danylenko/entity}unitAttribute"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

public class Parameters {

    protected UnitAttribute length;
    protected UnitAttribute width;
    protected UnitAttribute height;

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link UnitAttribute }
     *     
     */
    public UnitAttribute getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitAttribute }
     *     
     */
    public void setLength(UnitAttribute value) {
        this.length = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link UnitAttribute }
     *     
     */
    public UnitAttribute getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitAttribute }
     *     
     */
    public void setWidth(UnitAttribute value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link UnitAttribute }
     *     
     */
    public UnitAttribute getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitAttribute }
     *     
     */
    public void setHeight(UnitAttribute value) {
        this.height = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(length).append('\n');
        sb.append(width).append('\n');
        sb.append(height);
        return sb.toString();
    }
}
