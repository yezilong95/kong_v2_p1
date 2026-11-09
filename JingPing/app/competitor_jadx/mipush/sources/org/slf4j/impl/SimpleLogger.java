package org.slf4j.impl;

import defpackage.di;
import defpackage.er0;
import defpackage.fa0;
import defpackage.vx0;
import defpackage.xa0;
import defpackage.ya0;
import defpackage.zn;
import java.util.Properties;
import org.slf4j.event.Level;
import org.slf4j.helpers.MarkerIgnoringBase;

/* JADX INFO: loaded from: classes.dex */
public class SimpleLogger extends MarkerIgnoringBase {
    public static final String CACHE_OUTPUT_STREAM_STRING_KEY = "org.slf4j.simpleLogger.cacheOutputStream";
    public static final String DATE_TIME_FORMAT_KEY = "org.slf4j.simpleLogger.dateTimeFormat";
    public static final String DEFAULT_LOG_LEVEL_KEY = "org.slf4j.simpleLogger.defaultLogLevel";
    public static final er0 E;
    public static final String LEVEL_IN_BRACKETS_KEY = "org.slf4j.simpleLogger.levelInBrackets";
    public static final String LOG_FILE_KEY = "org.slf4j.simpleLogger.logFile";
    public static final String LOG_KEY_PREFIX = "org.slf4j.simpleLogger.log.";
    public static final String SHOW_DATE_TIME_KEY = "org.slf4j.simpleLogger.showDateTime";
    public static final String SHOW_LOG_NAME_KEY = "org.slf4j.simpleLogger.showLogName";
    public static final String SHOW_SHORT_LOG_NAME_KEY = "org.slf4j.simpleLogger.showShortLogName";
    public static final String SHOW_THREAD_ID_KEY = "org.slf4j.simpleLogger.showThreadId";
    public static final String SHOW_THREAD_NAME_KEY = "org.slf4j.simpleLogger.showThreadName";
    public static final String SYSTEM_PREFIX = "org.slf4j.simpleLogger.";
    public static final String WARN_LEVEL_STRING_KEY = "org.slf4j.simpleLogger.warnLevelString";
    private static final long serialVersionUID = -632788891211436180L;

    static {
        System.currentTimeMillis();
        er0 er0Var = new er0();
        new Properties();
        E = er0Var;
    }

    public static void a(int i, String str, Object obj, Object obj2) {
        if (i >= 0) {
            Object[] objArr = {obj, obj2};
            Object obj3 = objArr[1];
            Throwable th = obj3 instanceof Throwable ? (Throwable) obj3 : null;
            if (th != null) {
                Object[] objArr2 = new Object[1];
                System.arraycopy(objArr, 0, objArr2, 0, 1);
                objArr = objArr2;
            }
            fa0.m(str, objArr, th);
            c(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(int r4, java.lang.String r5, java.lang.Object... r6) {
        /*
            if (r4 < 0) goto L37
            if (r6 == 0) goto L14
            int r0 = r6.length
            if (r0 != 0) goto L8
            goto L14
        L8:
            int r0 = r6.length
            int r0 = r0 + (-1)
            r0 = r6[r0]
            boolean r1 = r0 instanceof java.lang.Throwable
            if (r1 == 0) goto L14
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            goto L15
        L14:
            r0 = 0
        L15:
            if (r0 == 0) goto L31
            if (r6 == 0) goto L29
            int r1 = r6.length
            if (r1 == 0) goto L29
            int r1 = r6.length
            int r1 = r1 + (-1)
            java.lang.Object[] r2 = new java.lang.Object[r1]
            if (r1 <= 0) goto L27
            r3 = 0
            java.lang.System.arraycopy(r6, r3, r2, r3, r1)
        L27:
            r6 = r2
            goto L31
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "non-sensical empty or null argument array"
            r4.<init>(r5)
            throw r4
        L31:
            defpackage.fa0.m(r5, r6, r0)
            c(r4)
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.slf4j.impl.SimpleLogger.b(int, java.lang.String, java.lang.Object[]):void");
    }

    public static void c(int i) {
        if (i >= 0) {
            E.getClass();
            Thread.currentThread().getName();
            if (i != 0 && i != 10 && i != 20 && i != 30 && i != 40) {
                throw new IllegalStateException(zn.k(i, "Unrecognized level [", "]"));
            }
            throw null;
        }
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atDebug() {
        return super.atDebug();
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atError() {
        return super.atError();
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atInfo() {
        return super.atInfo();
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atLevel(Level level) {
        return super.atLevel(level);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atTrace() {
        return super.atTrace();
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atWarn() {
        return super.atWarn();
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void debug(String str) {
        c(10);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public void error(String str) {
        c(40);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void info(String str) {
        c(20);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public boolean isDebugEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public /* bridge */ /* synthetic */ boolean isEnabledForLevel(Level level) {
        return super.isEnabledForLevel(level);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public boolean isInfoEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public boolean isTraceEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public boolean isWarnEnabled() {
        return true;
    }

    public void log(xa0 xa0Var) {
        vx0 vx0Var = (vx0) xa0Var;
        int i = vx0Var.a.toInt();
        if (i >= 0) {
            fa0.m(vx0Var.d, vx0Var.e, vx0Var.f);
            c(i);
        }
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public ya0 makeLoggingEventBuilder(Level level) {
        return new di(7);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public void trace(String str) {
        c(0);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void warn(String str) {
        c(30);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void debug(String str, Object obj) {
        a(10, str, obj, null);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public void error(String str, Object obj) {
        a(40, str, obj, null);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void info(String str, Object obj) {
        a(20, str, obj, null);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public void trace(String str, Object obj) {
        a(0, str, obj, null);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void warn(String str, Object obj) {
        a(30, str, obj, null);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void debug(String str, Object obj, Object obj2) {
        a(10, str, obj, obj2);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public void error(String str, Object obj, Object obj2) {
        a(40, str, obj, obj2);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void info(String str, Object obj, Object obj2) {
        a(20, str, obj, obj2);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public void trace(String str, Object obj, Object obj2) {
        a(0, str, obj, obj2);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void warn(String str, Object obj, Object obj2) {
        a(30, str, obj, obj2);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void debug(String str, Object... objArr) {
        b(10, str, objArr);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void error(String str, Object... objArr) {
        b(40, str, objArr);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void info(String str, Object... objArr) {
        b(20, str, objArr);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void trace(String str, Object... objArr) {
        b(0, str, objArr);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void warn(String str, Object... objArr) {
        b(30, str, objArr);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void debug(String str, Throwable th) {
        c(10);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public void error(String str, Throwable th) {
        c(40);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void info(String str, Throwable th) {
        c(20);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, defpackage.ua0
    public void trace(String str, Throwable th) {
        c(0);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase
    public void warn(String str, Throwable th) {
        c(30);
    }
}
