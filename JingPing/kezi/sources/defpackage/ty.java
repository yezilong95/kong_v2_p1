package defpackage;

import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.work.impl.WorkDatabase_Impl;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import kotlin.collections.builders.SetBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class ty {
    public static final String[] o = {"UPDATE", "DELETE", "INSERT"};
    public final WorkDatabase_Impl a;
    public final HashMap b;
    public final HashMap c;
    public final String[] e;
    public volatile boolean g;
    public volatile wl0 h;
    public final py i;
    public final e5 j;
    public final v6 n;
    public final AtomicBoolean f = new AtomicBoolean(false);
    public final lg0 k = new lg0();
    public final Object l = new Object();
    public final Object m = new Object();
    public final LinkedHashMap d = new LinkedHashMap();

    public ty(WorkDatabase_Impl workDatabase_Impl, HashMap map, HashMap map2, String... strArr) {
        String lowerCase;
        this.a = workDatabase_Impl;
        this.b = map;
        this.c = map2;
        this.i = new py(strArr.length);
        this.j = new e5(workDatabase_Impl);
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            Locale locale = Locale.US;
            oy.i(locale, "US");
            String lowerCase2 = str.toLowerCase(locale);
            oy.i(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            this.d.put(lowerCase2, Integer.valueOf(i));
            String str2 = (String) this.b.get(strArr[i]);
            if (str2 != null) {
                lowerCase = str2.toLowerCase(locale);
                oy.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            } else {
                lowerCase = null;
            }
            if (lowerCase != null) {
                lowerCase2 = lowerCase;
            }
            strArr2[i] = lowerCase2;
        }
        this.e = strArr2;
        for (Map.Entry entry : this.b.entrySet()) {
            String str3 = (String) entry.getValue();
            Locale locale2 = Locale.US;
            oy.i(locale2, "US");
            String lowerCase3 = str3.toLowerCase(locale2);
            oy.i(lowerCase3, "this as java.lang.String).toLowerCase(locale)");
            if (this.d.containsKey(lowerCase3)) {
                String lowerCase4 = ((String) entry.getKey()).toLowerCase(locale2);
                oy.i(lowerCase4, "this as java.lang.String).toLowerCase(locale)");
                LinkedHashMap linkedHashMap = this.d;
                oy.j(linkedHashMap, "<this>");
                Object obj = linkedHashMap.get(lowerCase3);
                if (obj == null && !linkedHashMap.containsKey(lowerCase3)) {
                    throw new NoSuchElementException("Key " + ((Object) lowerCase3) + " is missing in the map.");
                }
                linkedHashMap.put(lowerCase4, obj);
            }
        }
        this.n = new v6(11, this);
    }

    public final vf0 a(String[] strArr, boolean z, Callable callable) {
        String[] strArrC = c(strArr);
        for (String str : strArrC) {
            LinkedHashMap linkedHashMap = this.d;
            Locale locale = Locale.US;
            oy.i(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            oy.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (!linkedHashMap.containsKey(lowerCase)) {
                throw new IllegalArgumentException("There is no table with name ".concat(str).toString());
            }
        }
        e5 e5Var = this.j;
        e5Var.getClass();
        return new vf0((WorkDatabase_Impl) e5Var.c, e5Var, z, callable, strArrC);
    }

    public final boolean b() {
        if (!this.a.isOpen()) {
            return false;
        }
        if (!this.g) {
            ((ku) this.a.getOpenHelper()).a();
        }
        if (this.g) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public final String[] c(String[] strArr) {
        SetBuilder setBuilder = new SetBuilder();
        for (String str : strArr) {
            Locale locale = Locale.US;
            oy.i(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            oy.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            HashMap map = this.c;
            if (map.containsKey(lowerCase)) {
                String lowerCase2 = str.toLowerCase(locale);
                oy.i(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                Object obj = map.get(lowerCase2);
                oy.g(obj);
                setBuilder.addAll((Collection) obj);
            } else {
                setBuilder.add(str);
            }
        }
        Object[] array = setBuilder.build().toArray(new String[0]);
        oy.h(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return (String[]) array;
    }

    public final void d(ql0 ql0Var, int i) {
        ql0Var.i("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i + ", 0)");
        String str = this.e[i];
        String[] strArr = o;
        for (int i2 = 0; i2 < 3; i2++) {
            String str2 = strArr[i2];
            String str3 = "CREATE TEMP TRIGGER IF NOT EXISTS " + za0.s(str, str2) + " AFTER " + str2 + " ON `" + str + "` BEGIN UPDATE room_table_modification_log SET invalidated = 1 WHERE table_id = " + i + " AND invalidated = 0; END";
            oy.i(str3, "StringBuilder().apply(builderAction).toString()");
            ql0Var.i(str3);
        }
    }

    public final void e(ql0 ql0Var) {
        oy.j(ql0Var, "database");
        if (ql0Var.z()) {
            return;
        }
        try {
            Lock closeLock$room_runtime_release = this.a.getCloseLock$room_runtime_release();
            closeLock$room_runtime_release.lock();
            try {
                synchronized (this.l) {
                    int[] iArrA = this.i.a();
                    if (iArrA != null) {
                        if (ql0Var.h()) {
                            ql0Var.q();
                        } else {
                            ql0Var.f();
                        }
                        try {
                            int length = iArrA.length;
                            int i = 0;
                            int i2 = 0;
                            while (i < length) {
                                int i3 = iArrA[i];
                                int i4 = i2 + 1;
                                if (i3 == 1) {
                                    d(ql0Var, i2);
                                } else if (i3 == 2) {
                                    String str = this.e[i2];
                                    String[] strArr = o;
                                    for (int i5 = 0; i5 < 3; i5++) {
                                        String str2 = "DROP TRIGGER IF EXISTS " + za0.s(str, strArr[i5]);
                                        oy.i(str2, "StringBuilder().apply(builderAction).toString()");
                                        ql0Var.i(str2);
                                    }
                                }
                                i++;
                                i2 = i4;
                            }
                            ql0Var.l();
                            ql0Var.e();
                        } catch (Throwable th) {
                            ql0Var.e();
                            throw th;
                        }
                    }
                }
            } finally {
                closeLock$room_runtime_release.unlock();
            }
        } catch (SQLiteException e) {
            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
        } catch (IllegalStateException e2) {
            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e2);
        }
    }
}
