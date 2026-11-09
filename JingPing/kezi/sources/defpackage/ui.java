package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class ui {
    private volatile int installations;

    static {
        AtomicIntegerFieldUpdater.newUpdater(ui.class, "installations");
    }
}
