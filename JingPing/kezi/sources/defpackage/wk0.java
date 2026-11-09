package defpackage;

import java.util.ArrayList;
import java.util.List;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public abstract class wk0 extends rp0 {
    public static String d0(String str) {
        oy.j(str, "<this>");
        if (vk0.r0("|")) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.");
        }
        List listT0 = vk0.t0(str);
        int length = str.length();
        listT0.size();
        int size = listT0.size() - 1;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : listT0) {
            int i2 = i + 1;
            String strSubstring = null;
            if (i < 0) {
                be.Y();
                throw null;
            }
            String str2 = (String) obj;
            if ((i != 0 && i != size) || !vk0.r0(str2)) {
                int length2 = str2.length();
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        i3 = -1;
                        break;
                    }
                    if (!jq0.J(str2.charAt(i3))) {
                        break;
                    }
                    i3++;
                }
                if (i3 != -1 && dl0.i0(str2, "|", i3, false)) {
                    strSubstring = str2.substring("|".length() + i3);
                    oy.i(strSubstring, "substring(...)");
                }
                if (strSubstring != null) {
                    oh.e.getClass();
                } else {
                    strSubstring = str2;
                }
            }
            if (strSubstring != null) {
                arrayList.add(strSubstring);
            }
            i = i2;
        }
        StringBuilder sb = new StringBuilder(length);
        ae.b0(arrayList, sb, "\n", ExtensionRequestData.EMPTY_VALUE, ExtensionRequestData.EMPTY_VALUE, "...", null);
        String string = sb.toString();
        oy.i(string, "toString(...)");
        return string;
    }
}
