package util;

import java.util.Locale;

public class ThreadLocalWrapper {
    private static final ThreadLocal<Locale> threadLocal = new ThreadLocal<>();

    public static Locale getLocale() {
        return threadLocal.get();
    }

    public static void setLocale(Locale locale) {
        threadLocal.set(locale);
    }
}
