package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class hi extends go {
    public final /* synthetic */ int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ hi(fo foVar, int i) {
        super(foVar);
        this.e = i;
    }

    @Override // defpackage.go
    public void r() {
        switch (this.e) {
            case 0:
                this.b.setEndIconOnLongClickListener(null);
                break;
        }
    }
}
