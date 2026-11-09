package defpackage;

import android.content.Context;
import androidx.room.RoomDatabase$JournalMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class hp0 {
    public final Context a;
    public final String b;
    public Executor f;
    public Executor g;
    public fb h;
    public boolean i;
    public boolean l;
    public HashSet p;
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();
    public final ArrayList e = new ArrayList();
    public final RoomDatabase$JournalMode j = RoomDatabase$JournalMode.AUTOMATIC;
    public boolean k = true;
    public final long m = -1;
    public final z8 n = new z8(29);
    public final LinkedHashSet o = new LinkedHashSet();

    public hp0(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public final void a(ye0... ye0VarArr) {
        if (this.p == null) {
            this.p = new HashSet();
        }
        for (ye0 ye0Var : ye0VarArr) {
            HashSet hashSet = this.p;
            t40.k(hashSet);
            hashSet.add(Integer.valueOf(ye0Var.startVersion));
            HashSet hashSet2 = this.p;
            t40.k(hashSet2);
            hashSet2.add(Integer.valueOf(ye0Var.endVersion));
        }
        this.n.s((ye0[]) Arrays.copyOf(ye0VarArr, ye0VarArr.length));
    }
}
