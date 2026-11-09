package defpackage;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import okio.ByteString;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public abstract class bw {
    public static final sv[] a;
    public static final Map b;

    static {
        sv svVar = new sv(ExtensionRequestData.EMPTY_VALUE, sv.i);
        ByteString byteString = sv.f;
        sv svVar2 = new sv("GET", byteString);
        sv svVar3 = new sv("POST", byteString);
        ByteString byteString2 = sv.g;
        sv svVar4 = new sv("/", byteString2);
        sv svVar5 = new sv("/index.html", byteString2);
        ByteString byteString3 = sv.h;
        sv svVar6 = new sv("http", byteString3);
        sv svVar7 = new sv("https", byteString3);
        ByteString byteString4 = sv.e;
        sv[] svVarArr = {svVar, svVar2, svVar3, svVar4, svVar5, svVar6, svVar7, new sv("200", byteString4), new sv("204", byteString4), new sv("206", byteString4), new sv("304", byteString4), new sv("400", byteString4), new sv("404", byteString4), new sv("500", byteString4), new sv("accept-charset", ExtensionRequestData.EMPTY_VALUE), new sv("accept-encoding", "gzip, deflate"), new sv("accept-language", ExtensionRequestData.EMPTY_VALUE), new sv("accept-ranges", ExtensionRequestData.EMPTY_VALUE), new sv("accept", ExtensionRequestData.EMPTY_VALUE), new sv("access-control-allow-origin", ExtensionRequestData.EMPTY_VALUE), new sv("age", ExtensionRequestData.EMPTY_VALUE), new sv("allow", ExtensionRequestData.EMPTY_VALUE), new sv("authorization", ExtensionRequestData.EMPTY_VALUE), new sv("cache-control", ExtensionRequestData.EMPTY_VALUE), new sv("content-disposition", ExtensionRequestData.EMPTY_VALUE), new sv("content-encoding", ExtensionRequestData.EMPTY_VALUE), new sv("content-language", ExtensionRequestData.EMPTY_VALUE), new sv("content-length", ExtensionRequestData.EMPTY_VALUE), new sv("content-location", ExtensionRequestData.EMPTY_VALUE), new sv("content-range", ExtensionRequestData.EMPTY_VALUE), new sv("content-type", ExtensionRequestData.EMPTY_VALUE), new sv("cookie", ExtensionRequestData.EMPTY_VALUE), new sv("date", ExtensionRequestData.EMPTY_VALUE), new sv("etag", ExtensionRequestData.EMPTY_VALUE), new sv("expect", ExtensionRequestData.EMPTY_VALUE), new sv("expires", ExtensionRequestData.EMPTY_VALUE), new sv("from", ExtensionRequestData.EMPTY_VALUE), new sv("host", ExtensionRequestData.EMPTY_VALUE), new sv("if-match", ExtensionRequestData.EMPTY_VALUE), new sv("if-modified-since", ExtensionRequestData.EMPTY_VALUE), new sv("if-none-match", ExtensionRequestData.EMPTY_VALUE), new sv("if-range", ExtensionRequestData.EMPTY_VALUE), new sv("if-unmodified-since", ExtensionRequestData.EMPTY_VALUE), new sv("last-modified", ExtensionRequestData.EMPTY_VALUE), new sv("link", ExtensionRequestData.EMPTY_VALUE), new sv("location", ExtensionRequestData.EMPTY_VALUE), new sv("max-forwards", ExtensionRequestData.EMPTY_VALUE), new sv("proxy-authenticate", ExtensionRequestData.EMPTY_VALUE), new sv("proxy-authorization", ExtensionRequestData.EMPTY_VALUE), new sv("range", ExtensionRequestData.EMPTY_VALUE), new sv("referer", ExtensionRequestData.EMPTY_VALUE), new sv("refresh", ExtensionRequestData.EMPTY_VALUE), new sv("retry-after", ExtensionRequestData.EMPTY_VALUE), new sv("server", ExtensionRequestData.EMPTY_VALUE), new sv("set-cookie", ExtensionRequestData.EMPTY_VALUE), new sv("strict-transport-security", ExtensionRequestData.EMPTY_VALUE), new sv("transfer-encoding", ExtensionRequestData.EMPTY_VALUE), new sv("user-agent", ExtensionRequestData.EMPTY_VALUE), new sv("vary", ExtensionRequestData.EMPTY_VALUE), new sv("via", ExtensionRequestData.EMPTY_VALUE), new sv("www-authenticate", ExtensionRequestData.EMPTY_VALUE)};
        a = svVarArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(61);
        for (int i = 0; i < 61; i++) {
            if (!linkedHashMap.containsKey(svVarArr[i].a)) {
                linkedHashMap.put(svVarArr[i].a, Integer.valueOf(i));
            }
        }
        Map mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        oy.i(mapUnmodifiableMap, "unmodifiableMap(result)");
        b = mapUnmodifiableMap;
    }

    public static void a(ByteString byteString) {
        oy.j(byteString, "name");
        int size = byteString.size();
        for (int i = 0; i < size; i++) {
            byte b2 = byteString.getByte(i);
            if (65 <= b2 && b2 < 91) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.utf8());
            }
        }
    }
}
