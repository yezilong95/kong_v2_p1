package defpackage;

import android.content.Context;
import androidx.room.RoomDatabase$JournalMode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class li {
    public final Context a;
    public final String b;
    public final sl0 c;
    public final d90 d;
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

    public li(Context context, String str, sl0 sl0Var, d90 d90Var, ArrayList arrayList, boolean z, RoomDatabase$JournalMode roomDatabase$JournalMode, Executor executor, Executor executor2, boolean z2, boolean z3, LinkedHashSet linkedHashSet, ArrayList arrayList2, ArrayList arrayList3) {
        oy.j(d90Var, "migrationContainer");
        oy.j(roomDatabase$JournalMode, "journalMode");
        oy.j(executor, "queryExecutor");
        oy.j(executor2, "transactionExecutor");
        oy.j(arrayList2, "typeConverters");
        oy.j(arrayList3, "autoMigrationSpecs");
        this.a = context;
        this.b = str;
        this.c = sl0Var;
        this.d = d90Var;
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
