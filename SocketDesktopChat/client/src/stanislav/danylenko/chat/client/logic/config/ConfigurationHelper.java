package stanislav.danylenko.chat.client.logic.config;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class ConfigurationHelper {
    private static final String FILE_NAME = "client/src/stanislav/danylenko/chat/client/config/settings.json";
    private static final String ENCODING = "UTF-8";

    private static Gson gson = new Gson();

    private static String getConfigString() {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME), ENCODING);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            return sb.toString().trim();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public static void setConfiguration(Configuration configuration) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_NAME), ENCODING))) {
            writer.write(gson.toJson(configuration));
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Configuration getConfiguration() {
        return gson.fromJson(getConfigString(), Configuration.class);
    }
    
}
