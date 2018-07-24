package ua.nure.danylenko.entity;


/**
 * <p>Java class for ParentPlane complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParentPlane"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

public abstract class ParentPlane {
    public abstract String getModel();
    public abstract void setModel(String value);
    public abstract String getOrigin();
    public abstract void setOrigin(String value);
    public abstract Object getChars();
    public abstract void setChars(CharsScout value);
    public abstract void setChars(Chars value);
    public abstract Parameters getParameters();
    public abstract void setParameters(Parameters value);
    public abstract Price getPrice();
    public abstract void setPrice(Price value);
}
