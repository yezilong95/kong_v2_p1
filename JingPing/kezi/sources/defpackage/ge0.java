package defpackage;

import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;

/* JADX INFO: loaded from: classes.dex */
public final class ge0 extends Lambda implements ou {
    public final /* synthetic */ Regex b;
    public final /* synthetic */ CharSequence c;
    public final /* synthetic */ int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ge0(Regex regex, CharSequence charSequence, int i) {
        super(0);
        this.b = regex;
        this.c = charSequence;
        this.d = i;
    }

    @Override // defpackage.ou
    public final Object invoke() {
        return this.b.find(this.c, this.d);
    }
}
