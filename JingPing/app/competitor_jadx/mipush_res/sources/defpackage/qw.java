package defpackage;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class qw {
    public final z8 a;
    public final /* synthetic */ FloatingActionButton b;

    public qw(FloatingActionButton floatingActionButton, z8 z8Var) {
        this.b = floatingActionButton;
        this.a = z8Var;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof qw) && ((qw) obj).a.equals(this.a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
