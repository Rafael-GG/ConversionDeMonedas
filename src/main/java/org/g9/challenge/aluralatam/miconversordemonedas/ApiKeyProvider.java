package org.g9.challenge.aluralatam.miconversordemonedas;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ApiKeyProvider {

    private static final String ENV_NAME = "API_KEY";
    private static final String CONFIG_PATH = "/config.properties";
    private static final String CONFIG_KEY = "api.key";

    private ApiKeyProvider() {}

    public static String getApiKey() {
        // 1) Preferir variable de entorno
        String key = System.getenv(ENV_NAME);
        if (key != null && !key.isBlank()) {
            return key.trim();
        }

        // 2) Fallback a config local (no subir este archivo al repo)
        try (InputStream in = ApiKeyProvider.class.getResourceAsStream(CONFIG_PATH)) {
            if (in != null) {
                Properties p = new Properties();
                p.load(in);
                key = p.getProperty(CONFIG_KEY);
                if (key != null && !key.isBlank()) {
                    return key.trim();
                }
            }
        } catch (IOException ignored) {}

        // 3) Si no existe, devolver null (manejar en el código llamante)
        return null;
    }
}

