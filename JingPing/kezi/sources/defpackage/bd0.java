package defpackage;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class bd0 extends if0 {
    public final String b;
    public final long c;
    public final sc0 d;

    public bd0(String str, long j, sc0 sc0Var) {
        this.b = str;
        this.c = j;
        this.d = sc0Var;
    }

    @Override // defpackage.if0
    public final w9 C() {
        return this.d;
    }

    @Override // defpackage.if0
    public final long a() {
        return this.c;
    }

    @Override // defpackage.if0
    public final c60 t() {
        String str = this.b;
        if (str == null) {
            return null;
        }
        Pattern pattern = c60.c;
        try {
            return jq0.q(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
