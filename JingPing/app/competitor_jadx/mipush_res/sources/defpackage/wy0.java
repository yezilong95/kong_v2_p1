package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class wy0 implements sj {
    public final /* synthetic */ int a;
    public final String b;

    public /* synthetic */ wy0(String str, int i) {
        this.a = i;
        this.b = str;
    }

    @Override // defpackage.sj
    public String a() {
        return this.b;
    }

    public String toString() {
        switch (this.a) {
            case 0:
                return "<" + this.b + '>';
            default:
                return super.toString();
        }
    }
}
