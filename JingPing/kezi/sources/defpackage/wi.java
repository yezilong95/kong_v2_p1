package defpackage;

import java.text.SimpleDateFormat;
import kotlin.Result;
import kotlin.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class wi {
    public static final /* synthetic */ int a = 0;

    static {
        Object objM3constructorimpl;
        oy.a(nv0.class.getSimpleName(), new Exception());
        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        new nf(false);
        try {
            Object objNewInstance = Class.forName("kotlinx.coroutines.debug.internal.ByteBuddyDynamicAttach").getConstructors()[0].newInstance(new Object[0]);
            oy.h(objNewInstance, "null cannot be cast to non-null type kotlin.Function1<kotlin.Boolean, kotlin.Unit>");
            rp0.e(1, objNewInstance);
            objM3constructorimpl = Result.m3constructorimpl((pu) objNewInstance);
        } catch (Throwable th) {
            objM3constructorimpl = Result.m3constructorimpl(a.a(th));
        }
        if (Result.m8isFailureimpl(objM3constructorimpl)) {
            objM3constructorimpl = null;
        }
        new nf(true);
    }
}
