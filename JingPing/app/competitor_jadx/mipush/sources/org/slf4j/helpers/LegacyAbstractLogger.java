package org.slf4j.helpers;

import defpackage.di;
import defpackage.ya0;
import org.slf4j.Marker;
import org.slf4j.event.Level;

/* JADX INFO: loaded from: classes.dex */
public abstract class LegacyAbstractLogger extends AbstractLogger {
    private static final long serialVersionUID = -7041884104854048950L;

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atDebug() {
        return super.atDebug();
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atError() {
        return super.atError();
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atInfo() {
        return super.atInfo();
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atLevel(Level level) {
        return super.atLevel(level);
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atTrace() {
        return super.atTrace();
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atWarn() {
        return super.atWarn();
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public abstract /* synthetic */ boolean isDebugEnabled();

    @Override // org.slf4j.helpers.AbstractLogger
    public boolean isDebugEnabled(Marker marker) {
        return isDebugEnabled();
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ boolean isEnabledForLevel(Level level) {
        return super.isEnabledForLevel(level);
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public abstract /* synthetic */ boolean isErrorEnabled();

    @Override // org.slf4j.helpers.AbstractLogger
    public boolean isErrorEnabled(Marker marker) {
        return isErrorEnabled();
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public abstract /* synthetic */ boolean isInfoEnabled();

    @Override // org.slf4j.helpers.AbstractLogger
    public boolean isInfoEnabled(Marker marker) {
        return isInfoEnabled();
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public abstract /* synthetic */ boolean isTraceEnabled();

    @Override // org.slf4j.helpers.AbstractLogger
    public boolean isTraceEnabled(Marker marker) {
        return isTraceEnabled();
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public abstract /* synthetic */ boolean isWarnEnabled();

    @Override // org.slf4j.helpers.AbstractLogger
    public boolean isWarnEnabled(Marker marker) {
        return isWarnEnabled();
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public ya0 makeLoggingEventBuilder(Level level) {
        return new di(7);
    }
}
