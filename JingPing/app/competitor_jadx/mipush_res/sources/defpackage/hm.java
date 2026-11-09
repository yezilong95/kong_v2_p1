package defpackage;

import android.content.Context;
import androidx.room.RoomDatabase$JournalMode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class hm {
    public final Context a;
    public final String b;
    public final gy0 c;
    public final z8 d;
    public final ArrayList e;
    public final boolean f;
    public final RoomDatabase$JournalMode g;
    public final Executor h;
    public final Executor i;
    public final boolean j;
    public final boolean k;
    public final LinkedHashSet l;
    public final ArrayList m;
    public final ArrayList n;

    public hm(Context context, String str, gy0 gy0Var, z8 z8Var, ArrayList arrayList, boolean z, RoomDatabase$JournalMode roomDatabase$JournalMode, Executor executor, Executor executor2, boolean z2, boolean z3, LinkedHashSet linkedHashSet, ArrayList arrayList2, ArrayList arrayList3) {
        t40.n(z8Var, "migrationContainer");
        t40.n(roomDatabase$JournalMode, "journalMode");
        t40.n(executor, "queryExecutor");
        t40.n(executor2, "transactionExecutor");
        t40.n(arrayList2, "typeConverters");
        t40.n(arrayList3, "autoMigrationSpecs");
        this.a = context;
        this.b = str;
        this.c = gy0Var;
        this.d = z8Var;
        this.e = arrayList;
        this.f = z;
        this.g = roomDatabase$JournalMode;
        this.h = executor;
        this.i = executor2;
        this.j = z2;
        this.k = z3;
        this.l = linkedHashSet;
        this.m = arrayList2;
        this.n = arrayList3;
    }
}
