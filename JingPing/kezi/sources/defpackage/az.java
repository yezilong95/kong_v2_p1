package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class az extends rb0 {
    public static final boolean c;

    /* JADX WARN: Removed duplicated region for block: B:22:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0061 A[SYNTHETIC] */
    static {
        /*
            r0 = 1
            r1 = 10
            java.lang.String r2 = "java.specification.version"
            java.lang.String r2 = java.lang.System.getProperty(r2)
            r3 = 0
            r4 = 0
            if (r2 == 0) goto L6d
            defpackage.jq0.d(r1)
            int r5 = r2.length()
            if (r5 != 0) goto L18
            goto L6d
        L18:
            char r6 = r2.charAt(r4)
            r7 = 48
            int r7 = defpackage.oy.n(r6, r7)
            r8 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            if (r7 >= 0) goto L3b
            if (r5 != r0) goto L2a
            goto L6d
        L2a:
            r7 = 43
            if (r6 == r7) goto L38
            r7 = 45
            if (r6 == r7) goto L33
            goto L6d
        L33:
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r0
        L36:
            r7 = r6
            goto L3d
        L38:
            r6 = r0
            r7 = r4
            goto L3d
        L3b:
            r6 = r4
            goto L36
        L3d:
            r9 = -59652323(0xfffffffffc71c71d, float:-5.0215282E36)
            r10 = r4
            r11 = r9
        L42:
            if (r6 >= r5) goto L61
            char r12 = r2.charAt(r6)
            int r12 = java.lang.Character.digit(r12, r1)
            if (r12 >= 0) goto L4f
            goto L6d
        L4f:
            if (r10 >= r11) goto L58
            if (r11 != r9) goto L6d
            int r11 = r8 / 10
            if (r10 >= r11) goto L58
            goto L6d
        L58:
            int r10 = r10 * r1
            int r13 = r8 + r12
            if (r10 >= r13) goto L5e
            goto L6d
        L5e:
            int r10 = r10 - r12
            int r6 = r6 + r0
            goto L42
        L61:
            if (r7 == 0) goto L68
            java.lang.Integer r3 = java.lang.Integer.valueOf(r10)
            goto L6d
        L68:
            int r1 = -r10
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)
        L6d:
            if (r3 == 0) goto L7a
            int r1 = r3.intValue()
            r2 = 9
            if (r1 < r2) goto L78
            goto L83
        L78:
            r0 = r4
            goto L83
        L7a:
            java.lang.Class<javax.net.ssl.SSLSocket> r1 = javax.net.ssl.SSLSocket.class
            java.lang.String r2 = "getApplicationProtocol"
            java.lang.Class[] r3 = new java.lang.Class[r4]     // Catch: java.lang.NoSuchMethodException -> L78
            r1.getMethod(r2, r3)     // Catch: java.lang.NoSuchMethodException -> L78
        L83:
            defpackage.az.c = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.az.<clinit>():void");
    }

    @Override // defpackage.rb0
    public final void d(SSLSocket sSLSocket, String str, List list) {
        oy.j(list, "protocols");
        SSLParameters sSLParameters = sSLSocket.getSSLParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((Protocol) obj) != Protocol.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(ce.Z(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((Protocol) it.next()).toString());
        }
        sSLParameters.setApplicationProtocols((String[]) arrayList2.toArray(new String[0]));
        sSLSocket.setSSLParameters(sSLParameters);
    }

    @Override // defpackage.rb0
    public final String f(SSLSocket sSLSocket) {
        try {
            String applicationProtocol = sSLSocket.getApplicationProtocol();
            if (applicationProtocol == null ? true : applicationProtocol.equals(ExtensionRequestData.EMPTY_VALUE)) {
                return null;
            }
            return applicationProtocol;
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }
}
