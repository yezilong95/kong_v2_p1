package defpackage;

import android.util.Log;
import java.io.Writer;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class ta0 extends Writer {
    public final StringBuilder F = new StringBuilder(Uuid.SIZE_BITS);
    public final String E = "FragmentManager";

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        j();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
        j();
    }

    public final void j() {
        StringBuilder sb = this.F;
        if (sb.length() > 0) {
            Log.d(this.E, sb.toString());
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                j();
            } else {
                this.F.append(c);
            }
        }
    }
}
