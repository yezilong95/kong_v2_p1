package defpackage;

import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;

/* JADX INFO: loaded from: classes.dex */
public final class go0 extends Lambda implements a10 {
    public final /* synthetic */ Regex E;
    public final /* synthetic */ CharSequence F;
    public final /* synthetic */ int G;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public go0(Regex regex, CharSequence charSequence, int i) {
        super(0);
        this.E = regex;
        this.F = charSequence;
        this.G = i;
    }

    @Override // defpackage.a10
    public final Object invoke() {
        return this.E.find(this.F, this.G);
    }
}
