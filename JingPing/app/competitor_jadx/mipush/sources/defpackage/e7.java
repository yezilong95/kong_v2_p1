package defpackage;

import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes.dex */
public final class e7 extends p50 {
    public final /* synthetic */ AppCompatTextView I;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e7(AppCompatTextView appCompatTextView) {
        super(7, appCompatTextView);
        this.I = appCompatTextView;
    }

    @Override // defpackage.p50, defpackage.d7
    public final void d(int i) {
        super/*android.widget.TextView*/.setLastBaselineToBottomHeight(i);
    }

    @Override // defpackage.p50, defpackage.d7
    public final void k(int i) {
        super/*android.widget.TextView*/.setFirstBaselineToTopHeight(i);
    }
}
