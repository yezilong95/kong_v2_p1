package defpackage;

import android.R;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$id;

/* JADX INFO: loaded from: classes.dex */
public final class il0 {
    public final TextView a;
    public final TextView b;
    public final ImageView c;
    public final ImageView d;
    public final ImageView e;

    public il0(View view) {
        this.a = (TextView) view.findViewById(R.id.text1);
        this.b = (TextView) view.findViewById(R.id.text2);
        this.c = (ImageView) view.findViewById(R.id.icon1);
        this.d = (ImageView) view.findViewById(R.id.icon2);
        this.e = (ImageView) view.findViewById(R$id.edit_query);
    }
}
