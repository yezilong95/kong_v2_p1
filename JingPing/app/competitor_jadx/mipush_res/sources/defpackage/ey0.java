package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.CancellationSignal;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public interface ey0 extends Closeable {
    void a();

    void b();

    Cursor c(jy0 jy0Var);

    void d(String str);

    ky0 g(String str);

    boolean isOpen();

    boolean k();

    boolean m();

    void o(Object[] objArr);

    void p();

    Cursor q(jy0 jy0Var, CancellationSignal cancellationSignal);

    void r();

    int t(ContentValues contentValues, Object[] objArr);

    Cursor v(String str);
}
