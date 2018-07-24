package ua.nure.danylenko.entity;

/**
 * <p>Java class for type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Aircraft support"/&gt;
 *     &lt;enumeration value="Escort"/&gt;
 *     &lt;enumeration value="Fighter"/&gt;
 *     &lt;enumeration value="Interceptor"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
public enum Type {

    AIRCRAFT_SUPPORT("Aircraft support"),
    ESCORT("Escort"),
    FIGHTER("Fighter"),
    INTERCEPTOR("Interceptor");
    private final String value;

    Type(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Type fromValue(String v) {
        for (Type c: Type.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
