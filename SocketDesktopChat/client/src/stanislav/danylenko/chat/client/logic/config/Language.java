package stanislav.danylenko.chat.client.logic.config;

public enum Language {
    RUSSIAN("ru"), ENGLISH("en");
    private String locale;

    Language(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}
