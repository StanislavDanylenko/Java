package ua.nure.danylenko.constants;

/**
 * Holds entities declared in XSD document.
 * 
 * @author S.Danylenko
 *
 */
public enum XML {
	// elements names
	PLANES("Planes"), PLANE("Plane"), MODEL("Model"), ORIGIN("Origin"), CHARS("Chars"), TYPE("Type"), PLACES("Places"),
	AMMUNITION("Ammunition"), ROCKETS("Rockets"), RADAR("Radar"), PARAMETERS("Parameters"), LENGTH("Length"),
    WIDTH("Width"), HEIGHT("Height"), PRICE("Price"),

	// attribute name
	TYPEE("type"), UNIT("unit");

	private String value;

	XML(String value) {
		this.value = value;
	}
	
	/**
	 * Determines if a name is equal to the string value wrapped by this enum element.<br/>
	 * If a SAX/StAX parser make all names of elements and attributes interned you can use
	 * <pre>return value == name;</pre> instead <pre>return value.equals(name);</pre>
	 * @param name string to compare with value. 
	 * @return value.equals(name)
	 */
	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
