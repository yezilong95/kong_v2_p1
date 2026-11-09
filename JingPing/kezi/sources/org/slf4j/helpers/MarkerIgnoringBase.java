package org.slf4j.helpers;

import defpackage.i80;
import defpackage.q30;
import defpackage.qm;
import org.slf4j.Marker;
import org.slf4j.event.Level;

/* JADX INFO: loaded from: classes.dex */
public abstract class MarkerIgnoringBase extends i80 {
    private static final long serialVersionUID = 9044267456635152283L;

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

    public abstract /* synthetic */ void debug(String str);

    public abstract /* synthetic */ void debug(String str, Object obj);

    public abstract /* synthetic */ void debug(String str, Object obj, Object obj2);

    public abstract /* synthetic */ void debug(String str, Throwable th);

    public abstract /* synthetic */ void debug(String str, Object... objArr);

    public void debug(Marker marker, String str) {
        debug(str);
    }

    @Override // defpackage.n30
    public abstract /* synthetic */ void error(String str);

    @Override // defpackage.n30
    public abstract /* synthetic */ void error(String str, Object obj);

    @Override // defpackage.n30
    public abstract /* synthetic */ void error(String str, Object obj, Object obj2);

    @Override // defpackage.n30
    public abstract /* synthetic */ void error(String str, Throwable th);

    public abstract /* synthetic */ void error(String str, Object... objArr);

    public void error(Marker marker, String str) {
        error(str);
    }

    @Override // defpackage.i80, defpackage.n30
    public /* bridge */ /* synthetic */ String getName() {
        return null;
    }

    public abstract /* synthetic */ void info(String str);

    public abstract /* synthetic */ void info(String str, Object obj);

    public abstract /* synthetic */ void info(String str, Object obj, Object obj2);

    public abstract /* synthetic */ void info(String str, Throwable th);

    public abstract /* synthetic */ void info(String str, Object... objArr);

    public void info(Marker marker, String str) {
        info(str);
    }

    @Override // defpackage.n30
    public abstract /* synthetic */ boolean isDebugEnabled();

    public boolean isDebugEnabled(Marker marker) {
        return isDebugEnabled();
    }

    @Override // defpackage.n30
    public /* bridge */ /* synthetic */ boolean isEnabledForLevel(Level level) {
        return super.isEnabledForLevel(level);
    }

    @Override // defpackage.n30
    public abstract /* synthetic */ boolean isErrorEnabled();

    public boolean isErrorEnabled(Marker marker) {
        return isErrorEnabled();
    }

    @Override // defpackage.n30
    public abstract /* synthetic */ boolean isInfoEnabled();

    public boolean isInfoEnabled(Marker marker) {
        return isInfoEnabled();
    }

    @Override // defpackage.n30
    public abstract /* synthetic */ boolean isTraceEnabled();

    public boolean isTraceEnabled(Marker marker) {
        return isTraceEnabled();
    }

    @Override // defpackage.n30
    public abstract /* synthetic */ boolean isWarnEnabled();

    public boolean isWarnEnabled(Marker marker) {
        return isWarnEnabled();
    }

    @Override // defpackage.n30
    public q30 makeLoggingEventBuilder(Level level) {
        return new qm();
    }

    public String toString() {
        return getClass().getName() + "(" + getName() + ")";
    }

    @Override // defpackage.n30
    public abstract /* synthetic */ void trace(String str);

    @Override // defpackage.n30
    public abstract /* synthetic */ void trace(String str, Object obj);

    @Override // defpackage.n30
    public abstract /* synthetic */ void trace(String str, Object obj, Object obj2);

    @Override // defpackage.n30
    public abstract /* synthetic */ void trace(String str, Throwable th);

    public abstract /* synthetic */ void trace(String str, Object... objArr);

    public void trace(Marker marker, String str) {
        trace(str);
    }

    public abstract /* synthetic */ void warn(String str);

    public abstract /* synthetic */ void warn(String str, Object obj);

    public abstract /* synthetic */ void warn(String str, Object obj, Object obj2);

    public abstract /* synthetic */ void warn(String str, Throwable th);

    public abstract /* synthetic */ void warn(String str, Object... objArr);

    public void warn(Marker marker, String str) {
        warn(str);
    }

    public void debug(Marker marker, String str, Object obj) {
        debug(str, obj);
    }

    public void error(Marker marker, String str, Object obj) {
        error(str, obj);
    }

    public void info(Marker marker, String str, Object obj) {
        info(str, obj);
    }

    public void trace(Marker marker, String str, Object obj) {
        trace(str, obj);
    }

    public void warn(Marker marker, String str, Object obj) {
        warn(str, obj);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2) {
        debug(str, obj, obj2);
    }

    public void error(Marker marker, String str, Object obj, Object obj2) {
        error(str, obj, obj2);
    }

    public void info(Marker marker, String str, Object obj, Object obj2) {
        info(str, obj, obj2);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2) {
        trace(str, obj, obj2);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2) {
        warn(str, obj, obj2);
    }

    public void debug(Marker marker, String str, Object... objArr) {
        debug(str, objArr);
    }

    public void error(Marker marker, String str, Object... objArr) {
        error(str, objArr);
    }

    public void info(Marker marker, String str, Object... objArr) {
        info(str, objArr);
    }

    public void trace(Marker marker, String str, Object... objArr) {
        trace(str, objArr);
    }

    public void warn(Marker marker, String str, Object... objArr) {
        warn(str, objArr);
    }

    public void debug(Marker marker, String str, Throwable th) {
        debug(str, th);
    }

    public void error(Marker marker, String str, Throwable th) {
        error(str, th);
    }

    public void info(Marker marker, String str, Throwable th) {
        info(str, th);
    }

    public void trace(Marker marker, String str, Throwable th) {
        trace(str, th);
    }

    public void warn(Marker marker, String str, Throwable th) {
        warn(str, th);
    }
}
