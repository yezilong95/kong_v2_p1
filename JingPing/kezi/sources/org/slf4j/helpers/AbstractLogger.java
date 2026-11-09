package org.slf4j.helpers;

import defpackage.n30;
import defpackage.p30;
import defpackage.q30;
import defpackage.qm;
import java.io.ObjectStreamException;
import java.io.Serializable;
import org.slf4j.Marker;
import org.slf4j.event.Level;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractLogger implements n30, Serializable {
    private static final long serialVersionUID = -2529255052481744503L;

    public final void a(Level level, Marker marker, String str, Object obj, Object obj2) {
        if (obj2 instanceof Throwable) {
            c(level, marker, new Object[]{obj});
        } else {
            c(level, marker, new Object[]{obj, obj2});
        }
    }

    @Override // defpackage.n30
    public /* bridge */ /* synthetic */ q30 atDebug() {
        return super.atDebug();
    }

    @Override // defpackage.n30
    public /* bridge */ /* synthetic */ q30 atError() {
        return super.atError();
    }

    @Override // defpackage.n30
    public /* bridge */ /* synthetic */ q30 atInfo() {
        return super.atInfo();
    }

    @Override // defpackage.n30
    public /* bridge */ /* synthetic */ q30 atLevel(Level level) {
        return super.atLevel(level);
    }

    @Override // defpackage.n30
    public /* bridge */ /* synthetic */ q30 atTrace() {
        return super.atTrace();
    }

    @Override // defpackage.n30
    public /* bridge */ /* synthetic */ q30 atWarn() {
        return super.atWarn();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(org.slf4j.event.Level r3, org.slf4j.Marker r4, java.lang.String r5, java.lang.Object[] r6) {
        /*
            r2 = this;
            if (r6 == 0) goto L12
            int r5 = r6.length
            if (r5 != 0) goto L6
            goto L12
        L6:
            int r5 = r6.length
            int r5 = r5 + (-1)
            r5 = r6[r5]
            boolean r0 = r5 instanceof java.lang.Throwable
            if (r0 == 0) goto L12
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            goto L13
        L12:
            r5 = 0
        L13:
            if (r5 == 0) goto L31
            if (r6 == 0) goto L29
            int r5 = r6.length
            if (r5 == 0) goto L29
            int r5 = r6.length
            int r5 = r5 + (-1)
            java.lang.Object[] r0 = new java.lang.Object[r5]
            if (r5 <= 0) goto L25
            r1 = 0
            java.lang.System.arraycopy(r6, r1, r0, r1, r5)
        L25:
            r2.c(r3, r4, r0)
            return
        L29:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "non-sensical empty or null argument array"
            r3.<init>(r4)
            throw r3
        L31:
            r2.c(r3, r4, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.slf4j.helpers.AbstractLogger.b(org.slf4j.event.Level, org.slf4j.Marker, java.lang.String, java.lang.Object[]):void");
    }

    public abstract void c(Level level, Marker marker, Object[] objArr);

    public final void d(Level level, Marker marker, String str, Object obj) {
        c(level, marker, new Object[]{obj});
    }

    public void debug(String str) {
        if (isDebugEnabled()) {
            c(Level.DEBUG, null, null);
        }
    }

    @Override // defpackage.n30
    public void error(String str) {
        if (isErrorEnabled()) {
            c(Level.ERROR, null, null);
        }
    }

    @Override // defpackage.n30
    public String getName() {
        return null;
    }

    public void info(String str) {
        if (isInfoEnabled()) {
            c(Level.INFO, null, null);
        }
    }

    @Override // defpackage.n30
    public abstract /* synthetic */ boolean isDebugEnabled();

    public abstract /* synthetic */ boolean isDebugEnabled(Marker marker);

    @Override // defpackage.n30
    public /* bridge */ /* synthetic */ boolean isEnabledForLevel(Level level) {
        return super.isEnabledForLevel(level);
    }

    @Override // defpackage.n30
    public abstract /* synthetic */ boolean isErrorEnabled();

    public abstract /* synthetic */ boolean isErrorEnabled(Marker marker);

    @Override // defpackage.n30
    public abstract /* synthetic */ boolean isInfoEnabled();

    public abstract /* synthetic */ boolean isInfoEnabled(Marker marker);

    @Override // defpackage.n30
    public abstract /* synthetic */ boolean isTraceEnabled();

    public abstract /* synthetic */ boolean isTraceEnabled(Marker marker);

    @Override // defpackage.n30
    public abstract /* synthetic */ boolean isWarnEnabled();

    public abstract /* synthetic */ boolean isWarnEnabled(Marker marker);

    @Override // defpackage.n30
    public q30 makeLoggingEventBuilder(Level level) {
        return new qm();
    }

    public Object readResolve() throws ObjectStreamException {
        return p30.c(getName());
    }

    @Override // defpackage.n30
    public void trace(String str) {
        if (isTraceEnabled()) {
            c(Level.TRACE, null, null);
        }
    }

    public void warn(String str) {
        if (isWarnEnabled()) {
            c(Level.WARN, null, null);
        }
    }

    public void debug(String str, Object obj) {
        if (isDebugEnabled()) {
            d(Level.DEBUG, null, str, obj);
        }
    }

    @Override // defpackage.n30
    public void error(String str, Object obj) {
        if (isErrorEnabled()) {
            d(Level.ERROR, null, str, obj);
        }
    }

    public void info(String str, Object obj) {
        if (isInfoEnabled()) {
            d(Level.INFO, null, str, obj);
        }
    }

    @Override // defpackage.n30
    public void trace(String str, Object obj) {
        if (isTraceEnabled()) {
            d(Level.TRACE, null, str, obj);
        }
    }

    public void warn(String str, Object obj) {
        if (isWarnEnabled()) {
            d(Level.WARN, null, str, obj);
        }
    }

    public void debug(String str, Object obj, Object obj2) {
        if (isDebugEnabled()) {
            a(Level.DEBUG, null, str, obj, obj2);
        }
    }

    @Override // defpackage.n30
    public void error(String str, Object obj, Object obj2) {
        if (isErrorEnabled()) {
            a(Level.ERROR, null, str, obj, obj2);
        }
    }

    public void info(String str, Object obj, Object obj2) {
        if (isInfoEnabled()) {
            a(Level.INFO, null, str, obj, obj2);
        }
    }

    @Override // defpackage.n30
    public void trace(String str, Object obj, Object obj2) {
        if (isTraceEnabled()) {
            a(Level.TRACE, null, str, obj, obj2);
        }
    }

    public void warn(String str, Object obj, Object obj2) {
        if (isWarnEnabled()) {
            a(Level.WARN, null, str, obj, obj2);
        }
    }

    public void debug(String str, Object... objArr) {
        if (isDebugEnabled()) {
            b(Level.DEBUG, null, str, objArr);
        }
    }

    public void error(String str, Object... objArr) {
        if (isErrorEnabled()) {
            b(Level.ERROR, null, str, objArr);
        }
    }

    public void info(String str, Object... objArr) {
        if (isInfoEnabled()) {
            b(Level.INFO, null, str, objArr);
        }
    }

    public void trace(String str, Object... objArr) {
        if (isTraceEnabled()) {
            b(Level.TRACE, null, str, objArr);
        }
    }

    public void warn(String str, Object... objArr) {
        if (isWarnEnabled()) {
            b(Level.WARN, null, str, objArr);
        }
    }

    public void debug(String str, Throwable th) {
        if (isDebugEnabled()) {
            c(Level.DEBUG, null, null);
        }
    }

    @Override // defpackage.n30
    public void error(String str, Throwable th) {
        if (isErrorEnabled()) {
            c(Level.ERROR, null, null);
        }
    }

    public void info(String str, Throwable th) {
        if (isInfoEnabled()) {
            c(Level.INFO, null, null);
        }
    }

    @Override // defpackage.n30
    public void trace(String str, Throwable th) {
        if (isTraceEnabled()) {
            c(Level.TRACE, null, null);
        }
    }

    public void warn(String str, Throwable th) {
        if (isWarnEnabled()) {
            c(Level.WARN, null, null);
        }
    }

    public void debug(Marker marker, String str) {
        if (isDebugEnabled(marker)) {
            c(Level.DEBUG, marker, null);
        }
    }

    public void error(Marker marker, String str) {
        if (isErrorEnabled(marker)) {
            c(Level.ERROR, marker, null);
        }
    }

    public void info(Marker marker, String str) {
        if (isInfoEnabled(marker)) {
            c(Level.INFO, marker, null);
        }
    }

    public void trace(Marker marker, String str) {
        if (isTraceEnabled(marker)) {
            c(Level.TRACE, marker, null);
        }
    }

    public void warn(Marker marker, String str) {
        if (isWarnEnabled(marker)) {
            c(Level.WARN, marker, null);
        }
    }

    public void debug(Marker marker, String str, Object obj) {
        if (isDebugEnabled(marker)) {
            d(Level.DEBUG, marker, str, obj);
        }
    }

    public void error(Marker marker, String str, Object obj) {
        if (isErrorEnabled(marker)) {
            d(Level.ERROR, marker, str, obj);
        }
    }

    public void info(Marker marker, String str, Object obj) {
        if (isInfoEnabled(marker)) {
            d(Level.INFO, marker, str, obj);
        }
    }

    public void trace(Marker marker, String str, Object obj) {
        if (isTraceEnabled(marker)) {
            d(Level.TRACE, marker, str, obj);
        }
    }

    public void warn(Marker marker, String str, Object obj) {
        if (isWarnEnabled(marker)) {
            d(Level.WARN, marker, str, obj);
        }
    }

    public void debug(Marker marker, String str, Object obj, Object obj2) {
        if (isDebugEnabled(marker)) {
            a(Level.DEBUG, marker, str, obj, obj2);
        }
    }

    public void error(Marker marker, String str, Object obj, Object obj2) {
        if (isErrorEnabled(marker)) {
            a(Level.ERROR, marker, str, obj, obj2);
        }
    }

    public void info(Marker marker, String str, Object obj, Object obj2) {
        if (isInfoEnabled(marker)) {
            a(Level.INFO, marker, str, obj, obj2);
        }
    }

    public void trace(Marker marker, String str, Object obj, Object obj2) {
        if (isTraceEnabled(marker)) {
            a(Level.TRACE, marker, str, obj, obj2);
        }
    }

    public void warn(Marker marker, String str, Object obj, Object obj2) {
        if (isWarnEnabled(marker)) {
            a(Level.WARN, marker, str, obj, obj2);
        }
    }

    public void debug(Marker marker, String str, Object... objArr) {
        if (isDebugEnabled(marker)) {
            b(Level.DEBUG, marker, str, objArr);
        }
    }

    public void error(Marker marker, String str, Object... objArr) {
        if (isErrorEnabled(marker)) {
            b(Level.ERROR, marker, str, objArr);
        }
    }

    public void info(Marker marker, String str, Object... objArr) {
        if (isInfoEnabled(marker)) {
            b(Level.INFO, marker, str, objArr);
        }
    }

    public void trace(Marker marker, String str, Object... objArr) {
        if (isTraceEnabled(marker)) {
            b(Level.TRACE, marker, str, objArr);
        }
    }

    public void warn(Marker marker, String str, Object... objArr) {
        if (isWarnEnabled(marker)) {
            b(Level.WARN, marker, str, objArr);
        }
    }

    public void debug(Marker marker, String str, Throwable th) {
        if (isDebugEnabled(marker)) {
            c(Level.DEBUG, marker, null);
        }
    }

    public void error(Marker marker, String str, Throwable th) {
        if (isErrorEnabled(marker)) {
            c(Level.ERROR, marker, null);
        }
    }

    public void info(Marker marker, String str, Throwable th) {
        if (isInfoEnabled(marker)) {
            c(Level.INFO, marker, null);
        }
    }

    public void trace(Marker marker, String str, Throwable th) {
        if (isTraceEnabled(marker)) {
            c(Level.TRACE, marker, null);
        }
    }

    public void warn(Marker marker, String str, Throwable th) {
        if (isWarnEnabled(marker)) {
            c(Level.WARN, marker, null);
        }
    }
}
