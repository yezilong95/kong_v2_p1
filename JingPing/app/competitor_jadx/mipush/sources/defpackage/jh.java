package defpackage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import kotlinx.coroutines.channels.BufferOverflow;

/* JADX INFO: loaded from: classes.dex */
public final class jh extends nb {
    public final BufferOverflow l;

    public jh(BufferOverflow bufferOverflow) {
        String str;
        super(1);
        this.l = bufferOverflow;
        if (bufferOverflow != BufferOverflow.SUSPEND) {
            return;
        }
        StringBuilder sb = new StringBuilder("This implementation does not support suspension for senders, use ");
        Class cls = yn0.a(nb.class).E;
        t40.n(cls, "jClass");
        String simpleName = null;
        if (!cls.isAnonymousClass()) {
            if (cls.isLocalClass()) {
                simpleName = cls.getSimpleName();
                Method enclosingMethod = cls.getEnclosingMethod();
                if (enclosingMethod == null) {
                    Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
                    if (enclosingConstructor == null) {
                        int iIndexOf = simpleName.indexOf(36, 0);
                        if (iIndexOf != -1) {
                            simpleName = simpleName.substring(iIndexOf + 1, simpleName.length());
                            t40.m(simpleName, "substring(...)");
                        }
                    } else {
                        simpleName = qx0.s0(simpleName, enclosingConstructor.getName() + '$');
                    }
                } else {
                    simpleName = qx0.s0(simpleName, enclosingMethod.getName() + '$');
                }
            } else {
                boolean zIsArray = cls.isArray();
                LinkedHashMap linkedHashMap = df.H;
                if (zIsArray) {
                    Class<?> componentType = cls.getComponentType();
                    if (componentType.isPrimitive() && (str = (String) linkedHashMap.get(componentType.getName())) != null) {
                        simpleName = str.concat("Array");
                    }
                    if (simpleName == null) {
                        simpleName = "Array";
                    }
                } else {
                    simpleName = (String) linkedHashMap.get(cls.getName());
                    if (simpleName == null) {
                        simpleName = cls.getSimpleName();
                    }
                }
            }
        }
        throw new IllegalArgumentException(zn.o(sb, simpleName, " instead").toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00e6, code lost:
    
        r2 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01b0, code lost:
    
        r2 = r19;
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fa  */
    @Override // defpackage.nb, defpackage.ku0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(defpackage.b40 r27, defpackage.eg r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 449
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jh.a(b40, eg):java.lang.Object");
    }

    @Override // defpackage.nb
    public final boolean p() {
        return this.l == BufferOverflow.DROP_OLDEST;
    }
}
