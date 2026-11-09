package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.CancellationSignal;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public interface ql0 extends Closeable {
    void e();

    void f();

    Cursor g(vl0 vl0Var);

    boolean h();

    void i(String str);

    boolean isOpen();

    void k(Object[] objArr);

    void l();

    Cursor m(vl0 vl0Var, CancellationSignal cancellationSignal);

    wl0 p(String str);

    void q();

    int v(ContentValues contentValues, Object[] objArr);

    Cursor y(String str);

    boolean z();
}
