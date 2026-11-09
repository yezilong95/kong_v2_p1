package defpackage;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public interface gd {
    void a(Canvas canvas);

    void d();

    void g();

    int getCircularRevealScrimColor();

    fd getRevealInfo();

    boolean h();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i);

    void setRevealInfo(fd fdVar);
}
