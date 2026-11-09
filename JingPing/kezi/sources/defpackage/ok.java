package defpackage;

import android.content.res.AssetManager;
import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class ok {
    public final Executor a;
    public final ic0 b;
    public final byte[] c;
    public final File d;
    public final String e;
    public boolean f = false;
    public pk[] g;
    public byte[] h;

    public ok(AssetManager assetManager, Executor executor, ic0 ic0Var, String str, File file) {
        byte[] bArr;
        this.a = executor;
        this.b = ic0Var;
        this.e = str;
        this.d = file;
        int i = Build.VERSION.SDK_INT;
        if (i < 31) {
            switch (i) {
                case 24:
                case 25:
                    bArr = nv0.n;
                    break;
                case 26:
                    bArr = nv0.m;
                    break;
                case 27:
                    bArr = nv0.l;
                    break;
                case 28:
                case 29:
                case 30:
                    bArr = nv0.k;
                    break;
                default:
                    bArr = null;
                    break;
            }
        } else {
            bArr = nv0.j;
        }
        this.c = bArr;
    }

    public final FileInputStream a(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            if (message == null || !message.contains("compressed")) {
                return null;
            }
            this.b.d();
            return null;
        }
    }

    public final void b(int i, Serializable serializable) {
        this.a.execute(new nk(this, i, serializable));
    }
}
