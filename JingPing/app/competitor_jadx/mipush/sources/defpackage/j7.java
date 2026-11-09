package defpackage;

import android.text.StaticLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class j7 extends i7 {
    @Override // defpackage.i7, defpackage.k7
    public void a(StaticLayout.Builder builder, TextView textView) {
        builder.setTextDirection(textView.getTextDirectionHeuristic());
    }

    @Override // defpackage.k7
    public boolean b(TextView textView) {
        return textView.isHorizontallyScrollable();
    }
}
