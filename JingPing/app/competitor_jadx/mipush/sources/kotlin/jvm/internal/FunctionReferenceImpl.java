package kotlin.jvm.internal;

import defpackage.cf;
import defpackage.i60;
import defpackage.j60;

/* JADX INFO: loaded from: classes.dex */
public class FunctionReferenceImpl extends FunctionReference {
    public FunctionReferenceImpl(int i, j60 j60Var, String str, String str2) {
        super(i, CallableReference.NO_RECEIVER, ((cf) j60Var).a(), str, str2, !(j60Var instanceof i60) ? 1 : 0);
    }

    public FunctionReferenceImpl(int i, Class cls, String str, String str2, int i2) {
        super(i, CallableReference.NO_RECEIVER, cls, str, str2, i2);
    }

    public FunctionReferenceImpl(int i, Object obj, Class cls, String str, String str2, int i2) {
        super(i, obj, cls, str, str2, i2);
    }
}
