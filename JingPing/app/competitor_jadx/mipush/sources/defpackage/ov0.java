package defpackage;

import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes.dex */
public final class ov0 extends ViewOutlineProvider {
    public final Rect a = new Rect();
    public final /* synthetic */ ShapeableImageView b;

    public ov0(ShapeableImageView shapeableImageView) {
        this.b = shapeableImageView;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        ShapeableImageView shapeableImageView = this.b;
        if (shapeableImageView.P == null) {
            return;
        }
        if (shapeableImageView.O == null) {
            shapeableImageView.O = new ld0(shapeableImageView.P);
        }
        RectF rectF = shapeableImageView.I;
        Rect rect = this.a;
        rectF.round(rect);
        shapeableImageView.O.setBounds(rect);
        shapeableImageView.O.getOutline(outline);
    }
}
