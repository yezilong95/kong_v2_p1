package defpackage;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class dq {
    public final u8 a;
    public final /* synthetic */ FloatingActionButton b;

    public dq(FloatingActionButton floatingActionButton, u8 u8Var) {
        this.b = floatingActionButton;
        this.a = u8Var;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof dq) && ((dq) obj).a.equals(this.a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
