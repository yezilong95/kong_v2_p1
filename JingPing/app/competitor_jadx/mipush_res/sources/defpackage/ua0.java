package defpackage;

import org.slf4j.event.Level;

/* JADX INFO: loaded from: classes.dex */
public interface ua0 {
    default ya0 atDebug() {
        return isDebugEnabled() ? makeLoggingEventBuilder(Level.DEBUG) : is.I;
    }

    default ya0 atError() {
        return isErrorEnabled() ? makeLoggingEventBuilder(Level.ERROR) : is.I;
    }

    default ya0 atInfo() {
        return isInfoEnabled() ? makeLoggingEventBuilder(Level.INFO) : is.I;
    }

    default ya0 atLevel(Level level) {
        return isEnabledForLevel(level) ? makeLoggingEventBuilder(level) : is.I;
    }

    default ya0 atTrace() {
        return isTraceEnabled() ? makeLoggingEventBuilder(Level.TRACE) : is.I;
    }

    default ya0 atWarn() {
        return isWarnEnabled() ? makeLoggingEventBuilder(Level.WARN) : is.I;
    }

    void error(String str);

    void error(String str, Object obj);

    void error(String str, Object obj, Object obj2);

    void error(String str, Throwable th);

    String getName();

    boolean isDebugEnabled();

    default boolean isEnabledForLevel(Level level) {
        int i = level.toInt();
        if (i == 0) {
            return isTraceEnabled();
        }
        if (i == 10) {
            return isDebugEnabled();
        }
        if (i == 20) {
            return isInfoEnabled();
        }
        if (i == 30) {
            return isWarnEnabled();
        }
        if (i == 40) {
            return isErrorEnabled();
        }
        throw new IllegalArgumentException("Level [" + level + "] not recognized.");
    }

    boolean isErrorEnabled();

    boolean isInfoEnabled();

    boolean isTraceEnabled();

    boolean isWarnEnabled();

    ya0 makeLoggingEventBuilder(Level level);

    void trace(String str);

    void trace(String str, Object obj);

    void trace(String str, Object obj, Object obj2);

    void trace(String str, Throwable th);
}
