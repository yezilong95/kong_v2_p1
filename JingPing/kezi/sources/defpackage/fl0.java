package defpackage;

import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.event.EventRecordingLogger;
import org.slf4j.event.Level;
import org.slf4j.helpers.NOPLogger;

/* JADX INFO: loaded from: classes.dex */
public final class fl0 implements n30 {
    public final String b;
    public volatile n30 c;
    public Boolean d;
    public Method e;
    public EventRecordingLogger f;
    public final LinkedBlockingQueue g;
    public final boolean h;

    public fl0(String str, LinkedBlockingQueue linkedBlockingQueue, boolean z) {
        this.b = str;
        this.g = linkedBlockingQueue;
        this.h = z;
    }

    public final n30 a() {
        if (this.c != null) {
            return this.c;
        }
        if (this.h) {
            return NOPLogger.NOP_LOGGER;
        }
        if (this.f == null) {
            this.f = new EventRecordingLogger(this, this.g);
        }
        return this.f;
    }

    public final boolean b() {
        Boolean bool = this.d;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            this.e = this.c.getClass().getMethod("log", hl0.class);
            this.d = Boolean.TRUE;
        } catch (NoSuchMethodException unused) {
            this.d = Boolean.FALSE;
        }
        return this.d.booleanValue();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && fl0.class == obj.getClass() && this.b.equals(((fl0) obj).b);
    }

    @Override // defpackage.n30
    public final void error(String str) {
        a().error(str);
    }

    @Override // defpackage.n30
    public final String getName() {
        return this.b;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    @Override // defpackage.n30
    public final boolean isDebugEnabled() {
        return a().isDebugEnabled();
    }

    @Override // defpackage.n30
    public final boolean isEnabledForLevel(Level level) {
        return a().isEnabledForLevel(level);
    }

    @Override // defpackage.n30
    public final boolean isErrorEnabled() {
        return a().isErrorEnabled();
    }

    @Override // defpackage.n30
    public final boolean isInfoEnabled() {
        return a().isInfoEnabled();
    }

    @Override // defpackage.n30
    public final boolean isTraceEnabled() {
        return a().isTraceEnabled();
    }

    @Override // defpackage.n30
    public final boolean isWarnEnabled() {
        return a().isWarnEnabled();
    }

    @Override // defpackage.n30
    public final q30 makeLoggingEventBuilder(Level level) {
        return a().makeLoggingEventBuilder(level);
    }

    @Override // defpackage.n30
    public final void trace(String str) {
        a().trace(str);
    }

    @Override // defpackage.n30
    public final void error(String str, Object obj) {
        a().error("Got fatal error in worker thread {}", obj);
    }

    @Override // defpackage.n30
    public final void trace(String str, Object obj) {
        a().trace(str, obj);
    }

    @Override // defpackage.n30
    public final void error(String str, Object obj, Object obj2) {
        a().error("Uncaught exception in thread {}: {}", obj, obj2);
    }

    @Override // defpackage.n30
    public final void trace(String str, Object obj, Object obj2) {
        a().trace(str, obj, obj2);
    }

    @Override // defpackage.n30
    public final void error(String str, Throwable th) {
        a().error(str, th);
    }

    @Override // defpackage.n30
    public final void trace(String str, Throwable th) {
        a().trace(str, th);
    }
}
