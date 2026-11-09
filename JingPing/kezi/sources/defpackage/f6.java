package defpackage;

import android.text.StaticLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class f6 extends e6 {
    @Override // defpackage.e6, defpackage.g6
    public void a(StaticLayout.Builder builder, TextView textView) {
        builder.setTextDirection(textView.getTextDirectionHeuristic());
    }

    @Override // defpackage.g6
    public boolean b(TextView textView) {
        return textView.isHorizontallyScrollable();
    }
}
