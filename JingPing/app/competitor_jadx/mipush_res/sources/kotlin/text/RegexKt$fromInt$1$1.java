package kotlin.text;

import defpackage.b10;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class RegexKt$fromInt$1$1 extends Lambda implements b10 {
    public final /* synthetic */ int E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RegexKt$fromInt$1$1(int i) {
        super(1);
        this.E = i;
    }

    @Override // defpackage.b10
    public final Boolean invoke(Enum<Object> r3) {
        RegexOption regexOption = (RegexOption) r3;
        return Boolean.valueOf((regexOption.getMask() & this.E) == regexOption.getValue());
    }
}
