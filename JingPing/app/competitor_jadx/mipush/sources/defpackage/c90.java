package defpackage;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class c90 extends InputStream {
    public final InputStream E;
    public final int F;

    public c90(InputStream inputStream, int i) {
        this.E = inputStream;
        this.F = i;
    }

    public final void j() {
        InputStream inputStream = this.E;
        if (inputStream instanceof z30) {
            z30 z30Var = (z30) inputStream;
            z30Var.J = true;
            z30Var.l();
        }
    }
}
