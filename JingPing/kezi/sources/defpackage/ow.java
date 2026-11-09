package defpackage;

import java.io.IOException;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: loaded from: classes.dex */
public final class ow implements ou {
    public final xw b;
    public final /* synthetic */ uw c;

    public ow(uw uwVar, xw xwVar) {
        this.c = uwVar;
        this.b = xwVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [uw] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [okhttp3.internal.http2.ErrorCode] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // defpackage.ou
    public final Object invoke() {
        Throwable th;
        ErrorCode errorCode;
        ?? r0 = this.c;
        xw xwVar = this.b;
        ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
        ?? r3 = 1;
        IOException e = null;
        try {
            try {
                try {
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e2) {
                e = e2;
                errorCode = errorCode2;
            }
            if (!xwVar.a(true, this)) {
                throw new IOException("Required SETTINGS preface not received");
            }
            do {
                try {
                } catch (Throwable th3) {
                    th = th3;
                }
            } while (xwVar.a(false, this));
            errorCode = ErrorCode.NO_ERROR;
            try {
                errorCode2 = ErrorCode.CANCEL;
                r0.a(errorCode, errorCode2, null);
                r3 = errorCode;
            } catch (IOException e3) {
                e = e3;
                errorCode2 = ErrorCode.PROTOCOL_ERROR;
                r0.a(errorCode2, errorCode2, e);
                r3 = errorCode;
            }
            kq0.d(xwVar);
            return bq0.a;
            r3 = errorCode2;
        } catch (Throwable th4) {
            th = th4;
        }
        r0.a(r3, errorCode2, e);
        kq0.d(xwVar);
        throw th;
    }
}
