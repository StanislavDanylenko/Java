package ua.nure.danylenko.entity;


/**
 * <p>Java class for NotScoutPlane complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NotScoutPlane"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://nure.ua/danylenko/entity}ParentPlane"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Model" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Origin" type="{http://nure.ua/danylenko/entity}origin"/&gt;
 *         &lt;element name="Chars" type="{http://nure.ua/danylenko/entity}chars"/&gt;
 *         &lt;element name="Parameters" type="{http://nure.ua/danylenko/entity}parameters"/&gt;
 *         &lt;element name="Price" type="{http://nure.ua/danylenko/entity}price"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

public class NotScoutPlane
    extends ParentPlane
{

    protected String model;
    protected String origin;
    protected Chars chars;
    protected Parameters parameters;
    protected Price price;

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

    /**
     * Gets the value of the chars property.
     * 
     * @return
     *     possible object is
     *     {@link Chars }
     *     
     */
    public Chars getChars() {
        return chars;
    }

    /**
     * Sets the value of the chars property.
     * 
     * @param value
     *     allowed object is
     *     {@link Chars }
     *     
     */
    public void setChars(Chars value) {
        this.chars = value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(model).append('\n');
        result.append(origin).append('\n');
        result.append(chars).append('\n');
        result.append(parameters).append('\n');
        result.append(price).append('\n');
        return result.toString();
    }

    public void setChars(CharsScout value) {
        throw new IllegalArgumentException();
    }

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link Parameters }
     *     
     */
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parameters }
     *     
     */
    public void setParameters(Parameters value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link Price }
     *     
     */
    public Price getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link Price }
     *     
     */
    public void setPrice(Price value) {
        this.price = value;
    }

}
