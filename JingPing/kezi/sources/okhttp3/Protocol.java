package okhttp3;

import defpackage.nc0;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    public static final nc0 Companion = new nc0();
    public final String b;

    Protocol(String str) {
        this.b = str;
    }

    public static final Protocol get(String str) throws IOException {
        Companion.getClass();
        return nc0.a(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.b;
    }
}
