package util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LocaleManager {
    private static final Map<String, ResourceBundle> bundleMap = new HashMap<>();
    static {
        bundleMap.put("ua",ResourceBundle.getBundle(("fields"), Locale.forLanguageTag("ua")));
        bundleMap.put("en",ResourceBundle.getBundle("fields"));
        bundleMap.put(null, ResourceBundle.getBundle("fields"));
    }

    public LocaleManager() {
    }
    public static String getProperty(String key, String locale) {
        return bundleMap.getOrDefault(locale, bundleMap.get(null)).getString(key);
    }
}
