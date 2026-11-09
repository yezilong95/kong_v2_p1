package defpackage;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class bq0 {
    public final long a;
    public final String b;
    public final /* synthetic */ int c;

    public bq0(long j, String str, int i) {
        this.c = i;
        this.a = j;
        String str2 = "Reference(" + str + ") 0x" + Long.toHexString(j);
        this.b = str2;
        Logger logger = ep.E;
        ep.G.add(new dp(this, ep.F));
        Level level = Level.FINE;
        Logger logger2 = ep.E;
        if (logger2.isLoggable(level)) {
            logger2.fine("Registered: " + str2);
        }
    }

    public final String toString() {
        return this.b;
    }
}
