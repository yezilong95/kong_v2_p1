package androidx.work.impl;

import android.content.ContentValues;
import defpackage.o7;
import defpackage.oy;
import defpackage.ql0;

/* JADX INFO: loaded from: classes.dex */
public final class AutoMigration_14_15 implements o7 {
    @Override // defpackage.o7
    public void onPostMigrate(ql0 ql0Var) {
        oy.j(ql0Var, "db");
        ql0Var.i("UPDATE workspec SET period_count = 1 WHERE last_enqueue_time <> 0 AND interval_duration <> 0");
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("last_enqueue_time", Long.valueOf(System.currentTimeMillis()));
        ql0Var.v(contentValues, new Object[0]);
    }
}
