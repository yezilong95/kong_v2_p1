package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class te implements fh {
    public static final te b = new te();

    @Override // defpackage.fh
    public final nh getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    @Override // defpackage.fh
    public final void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete");
    }

    public final String toString() {
        return "This continuation is already complete";
    }
}
