package defpackage;

import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class lp extends p {
    public final ni c = new ni(1);

    @Override // defpackage.p
    public final Random a() {
        Object obj = this.c.get();
        oy.i(obj, "get(...)");
        return (Random) obj;
    }
}
