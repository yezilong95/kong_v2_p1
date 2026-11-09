package defpackage;

import android.content.Context;
import androidx.room.RoomDatabase$JournalMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class mf0 {
    public final Context a;
    public final String b;
    public Executor f;
    public Executor g;
    public h9 h;
    public boolean i;
    public boolean l;
    public HashSet p;
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();
    public final ArrayList e = new ArrayList();
    public final RoomDatabase$JournalMode j = RoomDatabase$JournalMode.AUTOMATIC;
    public boolean k = true;
    public final long m = -1;
    public final d90 n = new d90(6);
    public final LinkedHashSet o = new LinkedHashSet();

    public mf0(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public final void a(g70... g70VarArr) {
        if (this.p == null) {
            this.p = new HashSet();
        }
        for (g70 g70Var : g70VarArr) {
            HashSet hashSet = this.p;
            oy.g(hashSet);
            hashSet.add(Integer.valueOf(g70Var.startVersion));
            HashSet hashSet2 = this.p;
            oy.g(hashSet2);
            hashSet2.add(Integer.valueOf(g70Var.endVersion));
        }
        this.n.a((g70[]) Arrays.copyOf(g70VarArr, g70VarArr.length));
    }
}
