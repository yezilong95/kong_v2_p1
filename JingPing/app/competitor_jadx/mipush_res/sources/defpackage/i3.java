package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.b;

/* JADX INFO: loaded from: classes.dex */
public final class i3 extends AppCompatImageView implements j3 {
    public final /* synthetic */ b H;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i3(b bVar, Context context) {
        super(context, null, R$attr.actionOverflowButtonStyle);
        this.H = bVar;
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
        la0.e0(this, getContentDescription());
        setOnTouchListener(new d3(this, this, 1));
    }

    @Override // defpackage.j3
    public final boolean a() {
        return false;
    }

    @Override // defpackage.j3
    public final boolean b() {
        return false;
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        playSoundEffect(0);
        this.H.n();
        return true;
    }

    @Override // android.widget.ImageView
    public final boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        Drawable drawable = getDrawable();
        Drawable background = getBackground();
        if (drawable != null && background != null) {
            int width = getWidth();
            int height = getHeight();
            int iMax = Math.max(width, height) / 2;
            int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
            int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
            ip.f(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
        }
        return frame;
    }
}
