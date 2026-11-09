package defpackage;

import android.os.Bundle;
import android.util.Size;
import android.util.SizeF;

/* JADX INFO: loaded from: classes.dex */
public abstract class z9 {
    public static final void a(Bundle bundle, String str, Size size) {
        bundle.putSize(str, size);
    }

    public static final void b(Bundle bundle, String str, SizeF sizeF) {
        bundle.putSizeF(str, sizeF);
    }
}
