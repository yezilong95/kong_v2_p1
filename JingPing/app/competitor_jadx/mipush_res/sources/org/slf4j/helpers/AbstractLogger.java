package org.slf4j.helpers;

import defpackage.di;
import defpackage.ua0;
import defpackage.wa0;
import defpackage.ya0;
import java.io.ObjectStreamException;
import java.io.Serializable;
import org.slf4j.Marker;
import org.slf4j.event.Level;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractLogger implements ua0, Serializable {
    private static final long serialVersionUID = -2529255052481744503L;

    public final void a(Level level, Marker marker, String str, Object obj, Object obj2) {
        if (obj2 instanceof Throwable) {
            c(level, marker, str, new Object[]{obj}, (Throwable) obj2);
        } else {
            c(level, marker, str, new Object[]{obj, obj2}, null);
        }
    }

    @Override // defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atDebug() {
        return super.atDebug();
    }

    @Override // defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atError() {
        return super.atError();
    }

    @Override // defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atInfo() {
        return super.atInfo();
    }

    @Override // defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atLevel(Level level) {
        return super.atLevel(level);
    }

    @Override // defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atTrace() {
        return super.atTrace();
    }

    @Override // defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atWarn() {
        return super.atWarn();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(org.slf4j.event.Level r8, org.slf4j.Marker r9, java.lang.String r10, java.lang.Object[] r11) {
        /*
            r7 = this;
            if (r11 == 0) goto L13
            int r0 = r11.length
            if (r0 != 0) goto L6
            goto L13
        L6:
            int r0 = r11.length
            int r0 = r0 + (-1)
            r0 = r11[r0]
            boolean r1 = r0 instanceof java.lang.Throwable
            if (r1 == 0) goto L13
            java.lang.Throwable r0 = (java.lang.Throwable) r0
        L11:
            r6 = r0
            goto L15
        L13:
            r0 = 0
            goto L11
        L15:
            if (r6 == 0) goto L37
            if (r11 == 0) goto L2f
            int r0 = r11.length
            if (r0 == 0) goto L2f
            int r0 = r11.length
            int r0 = r0 + (-1)
            java.lang.Object[] r5 = new java.lang.Object[r0]
            if (r0 <= 0) goto L27
            r1 = 0
            java.lang.System.arraycopy(r11, r1, r5, r1, r0)
        L27:
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r1.c(r2, r3, r4, r5, r6)
            return
        L2f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "non-sensical empty or null argument array"
            r0.<init>(r1)
            throw r0
        L37:
            r5 = 0
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r0.c(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.slf4j.helpers.AbstractLogger.b(org.slf4j.event.Level, org.slf4j.Marker, java.lang.String, java.lang.Object[]):void");
    }

    public abstract void c(Level level, Marker marker, String str, Object[] objArr, Throwable th);

    public final void d(Level level, Marker marker, String str, Object obj) {
        c(level, marker, str, new Object[]{obj}, null);
    }

    public void debug(String str) {
        if (isDebugEnabled()) {
            c(Level.DEBUG, null, str, null, null);
        }
    }

    @Override // defpackage.ua0
    public void error(String str) {
        if (isErrorEnabled()) {
            c(Level.ERROR, null, str, null, null);
        }
    }

    @Override // defpackage.ua0
    public String getName() {
        return null;
    }

    public void info(String str) {
        if (isInfoEnabled()) {
            c(Level.INFO, null, str, null, null);
        }
    }

    @Override // defpackage.ua0
    public abstract /* synthetic */ boolean isDebugEnabled();

    public abstract /* synthetic */ boolean isDebugEnabled(Marker marker);

    @Override // defpackage.ua0
    public /* bridge */ /* synthetic */ boolean isEnabledForLevel(Level level) {
        return super.isEnabledForLevel(level);
    }

    @Override // defpackage.ua0
    public abstract /* synthetic */ boolean isErrorEnabled();

    public abstract /* synthetic */ boolean isErrorEnabled(Marker marker);

    @Override // defpackage.ua0
    public abstract /* synthetic */ boolean isInfoEnabled();

    public abstract /* synthetic */ boolean isInfoEnabled(Marker marker);

    @Override // defpackage.ua0
    public abstract /* synthetic */ boolean isTraceEnabled();

    public abstract /* synthetic */ boolean isTraceEnabled(Marker marker);

    @Override // defpackage.ua0
    public abstract /* synthetic */ boolean isWarnEnabled();

    public abstract /* synthetic */ boolean isWarnEnabled(Marker marker);

    @Override // defpackage.ua0
    public ya0 makeLoggingEventBuilder(Level level) {
        return new di(7);
    }

    public Object readResolve() throws ObjectStreamException {
        return wa0.c(getName());
    }

    @Override // defpackage.ua0
    public void trace(String str) {
        if (isTraceEnabled()) {
            c(Level.TRACE, null, str, null, null);
        }
    }

    public void warn(String str) {
        if (isWarnEnabled()) {
            c(Level.WARN, null, str, null, null);
        }
    }

    public void debug(String str, Object obj) {
        if (isDebugEnabled()) {
            d(Level.DEBUG, null, str, obj);
        }
    }

    @Override // defpackage.ua0
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

    @Override // defpackage.ua0
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

    @Override // defpackage.ua0
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

    @Override // defpackage.ua0
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
            c(Level.DEBUG, null, str, null, th);
        }
    }

    @Override // defpackage.ua0
    public void error(String str, Throwable th) {
        if (isErrorEnabled()) {
            c(Level.ERROR, null, str, null, th);
        }
    }

    public void info(String str, Throwable th) {
        if (isInfoEnabled()) {
            c(Level.INFO, null, str, null, th);
        }
    }

    @Override // defpackage.ua0
    public void trace(String str, Throwable th) {
        if (isTraceEnabled()) {
            c(Level.TRACE, null, str, null, th);
        }
    }

    public void warn(String str, Throwable th) {
        if (isWarnEnabled()) {
            c(Level.WARN, null, str, null, th);
        }
    }

    public void debug(Marker marker, String str) {
        if (isDebugEnabled(marker)) {
            c(Level.DEBUG, marker, str, null, null);
        }
    }

    public void error(Marker marker, String str) {
        if (isErrorEnabled(marker)) {
            c(Level.ERROR, marker, str, null, null);
        }
    }

    public void info(Marker marker, String str) {
        if (isInfoEnabled(marker)) {
            c(Level.INFO, marker, str, null, null);
        }
    }

    public void trace(Marker marker, String str) {
        if (isTraceEnabled(marker)) {
            c(Level.TRACE, marker, str, null, null);
        }
    }

    public void warn(Marker marker, String str) {
        if (isWarnEnabled(marker)) {
            c(Level.WARN, marker, str, null, null);
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
            c(Level.DEBUG, marker, str, null, th);
        }
    }

    public void error(Marker marker, String str, Throwable th) {
        if (isErrorEnabled(marker)) {
            c(Level.ERROR, marker, str, null, th);
        }
    }

    public void info(Marker marker, String str, Throwable th) {
        if (isInfoEnabled(marker)) {
            c(Level.INFO, marker, str, null, th);
        }
    }

    public void trace(Marker marker, String str, Throwable th) {
        if (isTraceEnabled(marker)) {
            c(Level.TRACE, marker, str, null, th);
        }
    }

    public void warn(Marker marker, String str, Throwable th) {
        if (isWarnEnabled(marker)) {
            c(Level.WARN, marker, str, null, th);
        }
    }
}
