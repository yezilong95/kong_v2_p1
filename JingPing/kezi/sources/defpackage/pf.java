package defpackage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import kotlinx.coroutines.channels.BufferOverflow;

/* JADX INFO: loaded from: classes.dex */
public final class pf extends s9 {
    public final BufferOverflow l;

    public pf(BufferOverflow bufferOverflow) {
        String str;
        super(1);
        this.l = bufferOverflow;
        if (bufferOverflow != BufferOverflow.SUSPEND) {
            return;
        }
        StringBuilder sb = new StringBuilder("This implementation does not support suspension for senders, use ");
        Class cls = ce0.a(s9.class).b;
        oy.j(cls, "jClass");
        String simpleName = null;
        if (!cls.isAnonymousClass()) {
            if (cls.isLocalClass()) {
                simpleName = cls.getSimpleName();
                Method enclosingMethod = cls.getEnclosingMethod();
                if (enclosingMethod == null) {
                    Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
                    if (enclosingConstructor == null) {
                        int iO0 = vk0.o0(simpleName, '$', 0, 6);
                        if (iO0 != -1) {
                            simpleName = simpleName.substring(iO0 + 1, simpleName.length());
                            oy.i(simpleName, "substring(...)");
                        }
                    } else {
                        simpleName = vk0.y0(simpleName, enclosingConstructor.getName() + '$');
                    }
                } else {
                    simpleName = vk0.y0(simpleName, enclosingMethod.getName() + '$');
                }
            } else {
                boolean zIsArray = cls.isArray();
                LinkedHashMap linkedHashMap = jd.e;
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
        throw new IllegalArgumentException(hk.k(sb, simpleName, " instead").toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00e6, code lost:
    
        r2 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01b0, code lost:
    
        r2 = r19;
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fa  */
    @Override // defpackage.s9, defpackage.wh0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(defpackage.wx r27, defpackage.ke r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 449
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pf.a(wx, ke):java.lang.Object");
    }

    @Override // defpackage.s9
    public final boolean p() {
        return this.l == BufferOverflow.DROP_OLDEST;
    }
}
