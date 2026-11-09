package defpackage;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class oz {
    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            qz qzVar = new qz(stringWriter);
            qzVar.f = true;
            oy.T(qzVar, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
