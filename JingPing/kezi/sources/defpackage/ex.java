package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class ex {
    public String a;
    public String d;
    public final ArrayList f;
    public ArrayList g;
    public String h;
    public String b = ExtensionRequestData.EMPTY_VALUE;
    public String c = ExtensionRequestData.EMPTY_VALUE;
    public int e = -1;

    public ex() {
        ArrayList arrayList = new ArrayList();
        this.f = arrayList;
        arrayList.add(ExtensionRequestData.EMPTY_VALUE);
    }

    public final fx a() {
        ArrayList arrayList;
        String str = this.a;
        if (str == null) {
            throw new IllegalStateException("scheme == null");
        }
        String strQ = yo.q(this.b, 0, 0, 7);
        String strQ2 = yo.q(this.c, 0, 0, 7);
        String str2 = this.d;
        if (str2 == null) {
            throw new IllegalStateException("host == null");
        }
        int iB = b();
        ArrayList arrayList2 = this.f;
        ArrayList arrayList3 = new ArrayList(ce.Z(arrayList2));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(yo.q((String) it.next(), 0, 0, 7));
        }
        ArrayList<String> arrayList4 = this.g;
        if (arrayList4 != null) {
            ArrayList arrayList5 = new ArrayList(ce.Z(arrayList4));
            for (String str3 : arrayList4) {
                arrayList5.add(str3 != null ? yo.q(str3, 0, 0, 3) : null);
            }
            arrayList = arrayList5;
        } else {
            arrayList = null;
        }
        String str4 = this.h;
        return new fx(str, strQ, strQ2, str2, iB, arrayList3, arrayList, str4 != null ? yo.q(str4, 0, 0, 7) : null, toString());
    }

    public final int b() {
        int i = this.e;
        if (i != -1) {
            return i;
        }
        String str = this.a;
        oy.g(str);
        if (str.equals("http")) {
            return 80;
        }
        if (str.equals("https")) {
            return WebSocketImpl.DEFAULT_WSS_PORT;
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0135  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(defpackage.fx r19, java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 936
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ex.c(fx, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ex.toString():java.lang.String");
    }
}
