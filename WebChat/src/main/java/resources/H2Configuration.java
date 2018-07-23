package resources;

public class H2Configuration {

    private final String dialect;
    private final String connectionDriverClass;
    private final String connectionURL;
    private final String connectionUsername;
    private final String connectionPassword;
    private final String showSql;
    private final String hbm2ddlAuto;

    public H2Configuration(String dialect, String connectionDroverClass, String connectionURL, String connectionUsername, String connectionPassword, String showSql, String hbm2ddlAuto) {
        this.dialect = dialect;
        this.connectionDriverClass = connectionDroverClass;
        this.connectionURL = connectionURL;
        this.connectionUsername = connectionUsername;
        this.connectionPassword = connectionPassword;
        this.showSql = showSql;
        this.hbm2ddlAuto = hbm2ddlAuto;
    }

    public H2Configuration() {
        this.dialect = "";
        this.connectionDriverClass = "";
        this.connectionURL = "";
        this.connectionUsername = "";
        this.connectionPassword = "";
        this.showSql = "";
        this.hbm2ddlAuto = "";
    }

    public String getDialect() {
        return dialect;
    }

    public String getConnectionDriverClass() {
        return connectionDriverClass;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public String getConnectionUsername() {
        return connectionUsername;
    }


    public String getConnectionPassword() {
        return connectionPassword;
    }

    public String getShowSql() {
        return showSql;
    }

    public String getHbm2ddlAuto() {
        return hbm2ddlAuto;
    }


    @Override
    public String toString() {
        return "H2Configuration{" +
                "dialect='" + dialect + '\'' +
                ", connectionDriverClass='" + connectionDriverClass + '\'' +
                ", connectionURL='" + connectionURL + '\'' +
                ", connectionUsername='" + connectionUsername + '\'' +
                ", connectionPassword='" + connectionPassword + '\'' +
                ", showSql='" + showSql + '\'' +
                ", hbm2ddlAuto='" + hbm2ddlAuto + '\'' +
                '}';
    }
}
