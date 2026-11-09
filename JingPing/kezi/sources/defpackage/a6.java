package defpackage;

import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes.dex */
public final class a6 extends o70 {
    public final /* synthetic */ AppCompatTextView f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a6(AppCompatTextView appCompatTextView) {
        super(8, appCompatTextView);
        this.f = appCompatTextView;
    }

    @Override // defpackage.o70, defpackage.z5
    public final void h(int i) {
        super/*android.widget.TextView*/.setLastBaselineToBottomHeight(i);
    }

    @Override // defpackage.o70, defpackage.z5
    public final void k(int i) {
        super/*android.widget.TextView*/.setFirstBaselineToTopHeight(i);
    }
}
