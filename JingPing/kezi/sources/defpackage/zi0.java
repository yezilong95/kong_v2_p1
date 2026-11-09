package defpackage;

import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes.dex */
public final class zi0 extends ViewOutlineProvider {
    public final Rect a = new Rect();
    public final /* synthetic */ ShapeableImageView b;

    public zi0(ShapeableImageView shapeableImageView) {
        this.b = shapeableImageView;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        ShapeableImageView shapeableImageView = this.b;
        if (shapeableImageView.m == null) {
            return;
        }
        if (shapeableImageView.l == null) {
            shapeableImageView.l = new u50(shapeableImageView.m);
        }
        RectF rectF = shapeableImageView.f;
        Rect rect = this.a;
        rectF.round(rect);
        shapeableImageView.l.setBounds(rect);
        shapeableImageView.l.getOutline(outline);
    }
}
