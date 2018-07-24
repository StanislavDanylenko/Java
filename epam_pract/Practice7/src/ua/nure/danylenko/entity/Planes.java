package ua.nure.danylenko.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Plane" type="{http://nure.ua/danylenko/entity}ParentPlane" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

public class Planes {

    protected List<ParentPlane> plane;

    @Override
    public String toString() {
        if (plane == null) {
            return "Planes contains no planes\n";
        } else {
            StringBuilder result = new StringBuilder();
            for (ParentPlane pl : plane) {
                result.append(pl).append('\n');
            }
            return result.toString();
        }
    }

    /**
     * Gets the value of the plane property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plane property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlane().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParentPlane }
     * 
     * 
     */
    public List<ParentPlane> getPlane() {
        if (plane == null) {
            plane = new ArrayList<ParentPlane>();
        }
        return this.plane;
    }

}
