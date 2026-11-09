package defpackage;

import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.event.EventRecordingLogger;
import org.slf4j.event.Level;
import org.slf4j.helpers.NOPLogger;

/* JADX INFO: loaded from: classes.dex */
public final class tx0 implements ua0 {
    public final String E;
    public volatile ua0 F;
    public Boolean G;
    public Method H;
    public EventRecordingLogger I;
    public final LinkedBlockingQueue J;
    public final boolean K;

    public tx0(String str, LinkedBlockingQueue linkedBlockingQueue, boolean z) {
        this.E = str;
        this.J = linkedBlockingQueue;
        this.K = z;
    }

    public final ua0 a() {
        if (this.F != null) {
            return this.F;
        }
        if (this.K) {
            return NOPLogger.NOP_LOGGER;
        }
        if (this.I == null) {
            this.I = new EventRecordingLogger(this, this.J);
        }
        return this.I;
    }

    public final boolean b() {
        Boolean bool = this.G;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            this.H = this.F.getClass().getMethod("log", xa0.class);
            this.G = Boolean.TRUE;
        } catch (NoSuchMethodException unused) {
            this.G = Boolean.FALSE;
        }
        return this.G.booleanValue();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && tx0.class == obj.getClass() && this.E.equals(((tx0) obj).E);
    }

    @Override // defpackage.ua0
    public final void error(String str) {
        a().error(str);
    }

    @Override // defpackage.ua0
    public final String getName() {
        return this.E;
    }

    public final int hashCode() {
        return this.E.hashCode();
    }

    @Override // defpackage.ua0
    public final boolean isDebugEnabled() {
        return a().isDebugEnabled();
    }

    @Override // defpackage.ua0
    public final boolean isEnabledForLevel(Level level) {
        return a().isEnabledForLevel(level);
    }

    @Override // defpackage.ua0
    public final boolean isErrorEnabled() {
        return a().isErrorEnabled();
    }

    @Override // defpackage.ua0
    public final boolean isInfoEnabled() {
        return a().isInfoEnabled();
    }

    @Override // defpackage.ua0
    public final boolean isTraceEnabled() {
        return a().isTraceEnabled();
    }

    @Override // defpackage.ua0
    public final boolean isWarnEnabled() {
        return a().isWarnEnabled();
    }

    @Override // defpackage.ua0
    public final ya0 makeLoggingEventBuilder(Level level) {
        return a().makeLoggingEventBuilder(level);
    }

    @Override // defpackage.ua0
    public final void trace(String str) {
        a().trace(str);
    }

    @Override // defpackage.ua0
    public final void error(String str, Object obj) {
        a().error("Got fatal error in worker thread {}", obj);
    }

    @Override // defpackage.ua0
    public final void trace(String str, Object obj) {
        a().trace(str, obj);
    }

    @Override // defpackage.ua0
    public final void error(String str, Object obj, Object obj2) {
        a().error("Uncaught exception in thread {}: {}", obj, obj2);
    }

    @Override // defpackage.ua0
    public final void trace(String str, Object obj, Object obj2) {
        a().trace(str, obj, obj2);
    }

    @Override // defpackage.ua0
    public final void error(String str, Throwable th) {
        a().error(str, th);
    }

    @Override // defpackage.ua0
    public final void trace(String str, Throwable th) {
        a().trace(str, th);
    }
}
