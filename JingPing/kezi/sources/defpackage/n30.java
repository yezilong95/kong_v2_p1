package defpackage;

import org.slf4j.event.Level;

/* JADX INFO: loaded from: classes.dex */
public interface n30 {
    default q30 atDebug() {
        return isDebugEnabled() ? makeLoggingEventBuilder(Level.DEBUG) : qm.g;
    }

    default q30 atError() {
        return isErrorEnabled() ? makeLoggingEventBuilder(Level.ERROR) : qm.g;
    }

    default q30 atInfo() {
        return isInfoEnabled() ? makeLoggingEventBuilder(Level.INFO) : qm.g;
    }

    default q30 atLevel(Level level) {
        return isEnabledForLevel(level) ? makeLoggingEventBuilder(level) : qm.g;
    }

    default q30 atTrace() {
        return isTraceEnabled() ? makeLoggingEventBuilder(Level.TRACE) : qm.g;
    }

    default q30 atWarn() {
        return isWarnEnabled() ? makeLoggingEventBuilder(Level.WARN) : qm.g;
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

    q30 makeLoggingEventBuilder(Level level);

    void trace(String str);

    void trace(String str, Object obj);

    void trace(String str, Object obj, Object obj2);

    void trace(String str, Throwable th);
}
