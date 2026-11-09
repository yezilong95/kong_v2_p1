package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ng implements ui {
    public static final ng E = new ng();

    @Override // defpackage.ui
    public final bj getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    @Override // defpackage.ui
    public final void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete");
    }

    public final String toString() {
        return "This continuation is already complete";
    }
}
