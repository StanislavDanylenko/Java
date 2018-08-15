package stanislav.danylenko.chat.client.logic.config;

public class Configuration {

    private int ip1;
    private int ip2;
    private int ip3;
    private int ip4;

    private int port;

    Language language;

    public Configuration(int ip1, int ip2, int ip3, int ip4, int port, Language language) {
        this.ip1 = ip1;
        this.ip2 = ip2;
        this.ip3 = ip3;
        this.ip4 = ip4;
        this.port = port;
        this.language = language;
    }

    public Configuration() {
    }

    public Configuration(Configuration configuration) {
        ip1 = configuration.getIp1();
        ip2 = configuration.getIp2();
        ip2 = configuration.getIp3();
        ip4 = configuration.getIp4();
        port = configuration.getPort();
        language = configuration.getLanguage();
    }

    public int getIp1() {
        return ip1;
    }

    public void setIp1(int ip1) {
        this.ip1 = ip1;
    }

    public int getIp2() {
        return ip2;
    }

    public void setIp2(int ip2) {
        this.ip2 = ip2;
    }

    public int getIp3() {
        return ip3;
    }

    public void setIp3(int ip3) {
        this.ip3 = ip3;
    }

    public int getIp4() {
        return ip4;
    }

    public void setIp4(int ip4) {
        this.ip4 = ip4;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getFullIP() {
        return new StringBuilder()
                .append(ip1)
                .append(".")
                .append(ip2)
                .append(".")
                .append(ip3)
                .append(".")
                .append(ip4)
                .toString();
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "ip=" + ip1 +
                "." + ip2 +
                "." + ip3 +
                "." + ip4 +
                ", port=" + port +
                ", language=" + language +
                '}';
    }

}
