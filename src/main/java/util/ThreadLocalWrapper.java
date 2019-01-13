package util;

import java.util.Locale;
import java.util.Optional;

public class ThreadLocalWrapper {
    private static final ThreadLocal<Locale> threadLocal = new ThreadLocal<>();

    public static Locale getLocale() {
        return Optional.ofNullable(threadLocal.get()).orElse(Locale.getDefault());
    }

    public static void setLocale(Locale locale) {
        threadLocal.set(locale);
    }
}
