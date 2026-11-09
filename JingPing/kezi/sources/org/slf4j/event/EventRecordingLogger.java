package org.slf4j.event;

import defpackage.fl0;
import defpackage.hl0;
import defpackage.q30;
import defpackage.qm;
import java.util.ArrayList;
import java.util.Queue;
import org.slf4j.Marker;
import org.slf4j.helpers.LegacyAbstractLogger;

/* JADX INFO: loaded from: classes.dex */
public class EventRecordingLogger extends LegacyAbstractLogger {
    private static final long serialVersionUID = -176083308134819629L;
    public final String b;
    public final fl0 c;
    public final Queue d;

    public EventRecordingLogger(fl0 fl0Var, Queue<hl0> queue) {
        this.c = fl0Var;
        this.b = fl0Var.b;
        this.d = queue;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public /* bridge */ /* synthetic */ q30 atDebug() {
        return super.atDebug();
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public /* bridge */ /* synthetic */ q30 atError() {
        return super.atError();
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public /* bridge */ /* synthetic */ q30 atInfo() {
        return super.atInfo();
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public /* bridge */ /* synthetic */ q30 atLevel(Level level) {
        return super.atLevel(level);
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public /* bridge */ /* synthetic */ q30 atTrace() {
        return super.atTrace();
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public /* bridge */ /* synthetic */ q30 atWarn() {
        return super.atWarn();
    }

    @Override // org.slf4j.helpers.AbstractLogger
    public final void c(Level level, Marker marker, Object[] objArr) {
        hl0 hl0Var = new hl0();
        System.currentTimeMillis();
        hl0Var.a = level;
        hl0Var.c = this.c;
        if (marker != null) {
            if (hl0Var.b == null) {
                hl0Var.b = new ArrayList(2);
            }
            hl0Var.b.add(marker);
        }
        Thread.currentThread().getName();
        hl0Var.d = objArr;
        this.d.add(hl0Var);
    }

    @Override // org.slf4j.helpers.AbstractLogger, defpackage.n30
    public String getName() {
        return this.b;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public boolean isDebugEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public /* bridge */ /* synthetic */ boolean isEnabledForLevel(Level level) {
        return super.isEnabledForLevel(level);
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public boolean isInfoEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public boolean isTraceEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public boolean isWarnEnabled() {
        return true;
    }

    @Override // org.slf4j.helpers.LegacyAbstractLogger, org.slf4j.helpers.AbstractLogger, defpackage.n30
    public q30 makeLoggingEventBuilder(Level level) {
        return new qm();
    }
}
