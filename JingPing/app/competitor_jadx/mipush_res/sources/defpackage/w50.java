package defpackage;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class w50 {
    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            f60 f60Var = new f60(stringWriter);
            f60Var.I = true;
            h21 h21Var = k21.a;
            j20.d(f60Var, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
