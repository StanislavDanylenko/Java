package ua.nure.danylenko.entity;

/**
 * <p>Java class for typeScout.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeScout"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Scout"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
public enum TypeScout {

    SCOUT("Scout");
    private final String value;

    TypeScout(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeScout fromValue(String v) {
        for (TypeScout c: TypeScout.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
