package androidx.work.impl;

import android.content.Context;
import defpackage.t40;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class Api21Impl {
    public static final Api21Impl INSTANCE = new Api21Impl();

    private Api21Impl() {
    }

    public final File getNoBackupFilesDir(Context context) {
        t40.n(context, "context");
        File noBackupFilesDir = context.getNoBackupFilesDir();
        t40.m(noBackupFilesDir, "context.noBackupFilesDir");
        return noBackupFilesDir;
    }
}
