package org.slf4j.helpers;

import defpackage.ag0;
import defpackage.di;
import defpackage.ya0;
import org.slf4j.Marker;
import org.slf4j.event.Level;

/* JADX INFO: loaded from: classes.dex */
public class NOPLogger extends ag0 {
    public static final NOPLogger NOP_LOGGER = new NOPLogger();
    private static final long serialVersionUID = -517220405410904473L;

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

    public final void debug(String str) {
    }

    @Override // defpackage.ua0
    public final void error(String str) {
    }

    @Override // defpackage.ag0, defpackage.ua0
    public String getName() {
        return "NOP";
    }

    public final void info(String str) {
    }

    @Override // defpackage.ua0
    public final boolean isDebugEnabled() {
        return false;
    }

    @Override // defpackage.ua0
    public /* bridge */ /* synthetic */ boolean isEnabledForLevel(Level level) {
        return super.isEnabledForLevel(level);
    }

    @Override // defpackage.ua0
    public final boolean isErrorEnabled() {
        return false;
    }

    @Override // defpackage.ua0
    public final boolean isInfoEnabled() {
        return false;
    }

    @Override // defpackage.ua0
    public final boolean isTraceEnabled() {
        return false;
    }

    @Override // defpackage.ua0
    public final boolean isWarnEnabled() {
        return false;
    }

    @Override // defpackage.ua0
    public ya0 makeLoggingEventBuilder(Level level) {
        return new di(7);
    }

    @Override // defpackage.ua0
    public final void trace(String str) {
    }

    public final void warn(String str) {
    }

    public final void debug(String str, Object obj) {
    }

    @Override // defpackage.ua0
    public final void error(String str, Object obj) {
    }

    public final void info(String str, Object obj) {
    }

    public final boolean isDebugEnabled(Marker marker) {
        return false;
    }

    public final boolean isErrorEnabled(Marker marker) {
        return false;
    }

    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    public final boolean isTraceEnabled(Marker marker) {
        return false;
    }

    public final boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override // defpackage.ua0
    public final void trace(String str, Object obj) {
    }

    public final void warn(String str, Object obj) {
    }

    public final void debug(String str, Object obj, Object obj2) {
    }

    @Override // defpackage.ua0
    public final void error(String str, Object obj, Object obj2) {
    }

    public final void info(String str, Object obj, Object obj2) {
    }

    @Override // defpackage.ua0
    public final void trace(String str, Object obj, Object obj2) {
    }

    public final void warn(String str, Object obj, Object obj2) {
    }

    public final void debug(String str, Throwable th) {
    }

    @Override // defpackage.ua0
    public final void error(String str, Throwable th) {
    }

    public final void info(String str, Throwable th) {
    }

    @Override // defpackage.ua0
    public final void trace(String str, Throwable th) {
    }

    public final void warn(String str, Throwable th) {
    }

    public final void debug(String str, Object... objArr) {
    }

    public final void error(String str, Object... objArr) {
    }

    public final void info(String str, Object... objArr) {
    }

    public final void trace(String str, Object... objArr) {
    }

    public final void warn(String str, Object... objArr) {
    }

    public final void debug(Marker marker, String str) {
    }

    public final void error(Marker marker, String str) {
    }

    public final void info(Marker marker, String str) {
    }

    public final void trace(Marker marker, String str) {
    }

    public final void warn(Marker marker, String str) {
    }

    public final void debug(Marker marker, String str, Object obj) {
    }

    public final void error(Marker marker, String str, Object obj) {
    }

    public final void info(Marker marker, String str, Object obj) {
    }

    public final void trace(Marker marker, String str, Object obj) {
    }

    public final void warn(Marker marker, String str, Object obj) {
    }

    public final void debug(Marker marker, String str, Object obj, Object obj2) {
    }

    public final void error(Marker marker, String str, Object obj, Object obj2) {
    }

    public final void info(Marker marker, String str, Object obj, Object obj2) {
    }

    public final void trace(Marker marker, String str, Object obj, Object obj2) {
    }

    public final void warn(Marker marker, String str, Object obj, Object obj2) {
    }

    public final void debug(Marker marker, String str, Throwable th) {
    }

    public final void error(Marker marker, String str, Throwable th) {
    }

    public final void info(Marker marker, String str, Throwable th) {
    }

    public final void trace(Marker marker, String str, Throwable th) {
    }

    public final void warn(Marker marker, String str, Throwable th) {
    }

    public final void debug(Marker marker, String str, Object... objArr) {
    }

    public final void error(Marker marker, String str, Object... objArr) {
    }

    public final void info(Marker marker, String str, Object... objArr) {
    }

    public final void trace(Marker marker, String str, Object... objArr) {
    }

    public final void warn(Marker marker, String str, Object... objArr) {
    }
}
