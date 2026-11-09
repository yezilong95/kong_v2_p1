package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class wr {
    public final int[] a;
    public final int[] b;

    public wr(int i) {
        switch (i) {
            case 1:
                this.a = new int[10];
                this.b = new int[10];
                break;
            case 2:
                this.a = new int[16];
                this.b = new int[16];
                break;
            default:
                this.a = new int[10];
                this.b = new int[10];
                break;
        }
    }
}
