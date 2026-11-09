package defpackage;

import android.text.StaticLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class g6 {
    public abstract void a(StaticLayout.Builder builder, TextView textView);

    public boolean b(TextView textView) {
        return ((Boolean) h6.e("getHorizontallyScrolling", textView, Boolean.FALSE)).booleanValue();
    }
}
