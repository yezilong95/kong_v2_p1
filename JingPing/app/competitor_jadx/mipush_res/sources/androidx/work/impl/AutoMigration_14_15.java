package androidx.work.impl;

import android.content.ContentValues;
import defpackage.ey0;
import defpackage.t40;
import defpackage.w8;

/* JADX INFO: loaded from: classes.dex */
public final class AutoMigration_14_15 implements w8 {
    @Override // defpackage.w8
    public void onPostMigrate(ey0 ey0Var) {
        t40.n(ey0Var, "db");
        ey0Var.d("UPDATE workspec SET period_count = 1 WHERE last_enqueue_time <> 0 AND interval_duration <> 0");
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("last_enqueue_time", Long.valueOf(System.currentTimeMillis()));
        ey0Var.t(contentValues, new Object[0]);
    }
}
