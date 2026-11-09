package defpackage;

import android.content.res.AssetManager;
import android.os.Build;
import io.nats.client.BaseConsumeOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class go {
    public final Executor a;
    public final xl0 b;
    public final byte[] c;
    public final File d;
    public final String e;
    public boolean f = false;
    public ho[] g;
    public byte[] h;

    public go(AssetManager assetManager, Executor executor, xl0 xl0Var, String str, File file) {
        byte[] bArr;
        this.a = executor;
        this.b = xl0Var;
        this.e = str;
        this.d = file;
        int i = Build.VERSION.SDK_INT;
        if (i < 31) {
            switch (i) {
                case 24:
                case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                    bArr = l21.H;
                    break;
                case 26:
                    bArr = l21.G;
                    break;
                case 27:
                    bArr = l21.F;
                    break;
                case 28:
                case 29:
                case 30:
                    bArr = l21.E;
                    break;
                default:
                    bArr = null;
                    break;
            }
        } else {
            bArr = l21.D;
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
            this.b.e();
            return null;
        }
    }

    public final void b(int i, Serializable serializable) {
        this.a.execute(new q1(this, i, serializable));
    }
}
