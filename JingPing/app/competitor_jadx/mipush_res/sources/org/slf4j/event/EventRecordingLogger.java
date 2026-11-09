package org.slf4j.event;

import defpackage.di;
import defpackage.tx0;
import defpackage.vx0;
import defpackage.ya0;
import java.util.ArrayList;
import java.util.Queue;
import org.slf4j.Marker;
import org.slf4j.helpers.LegacyAbstractLogger;

/* JADX INFO: loaded from: classes.dex */
public class EventRecordingLogger extends LegacyAbstractLogger {
    private static final long serialVersionUID = -176083308134819629L;
    public final String E;
    public final tx0 F;
    public final Queue G;

    public EventRecordingLogger(tx0 tx0Var, Queue<vx0> queue) {
        this.F = tx0Var;
        this.E = tx0Var.E;
        this.G = queue;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atDebug() {
        return super.atDebug();
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atError() {
        return super.atError();
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atInfo() {
        return super.atInfo();
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atLevel(Level level) {
        return super.atLevel(level);
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atTrace() {
        return super.atTrace();
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ ya0 atWarn() {
        return super.atWarn();
    }

    @Override // org.slf4j.helpers.AbstractLogger
    public final void c(Level level, Marker marker, String str, Object[] objArr, Throwable th) {
        vx0 vx0Var = new vx0();
        System.currentTimeMillis();
        vx0Var.a = level;
        vx0Var.c = this.F;
        if (marker != null) {
            if (vx0Var.b == null) {
                vx0Var.b = new ArrayList(2);
            }
            vx0Var.b.add(marker);
        }
        vx0Var.d = str;
        Thread.currentThread().getName();
        vx0Var.e = objArr;
        vx0Var.f = th;
        this.G.add(vx0Var);
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public String getName() {
        return this.E;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public boolean isDebugEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public /* bridge */ /* synthetic */ boolean isEnabledForLevel(Level level) {
        return super.isEnabledForLevel(level);
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public boolean isInfoEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public boolean isTraceEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public boolean isWarnEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.ua0
    public ya0 makeLoggingEventBuilder(Level level) {
        return new di(7);
    }
}
