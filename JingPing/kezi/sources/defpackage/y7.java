package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.widget.ImageView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class y7 extends Property {
    public final /* synthetic */ int a = 2;
    public Object b;

    public /* synthetic */ y7(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public final Object get(Object obj) {
        switch (this.a) {
            case 0:
                ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) obj;
                return Float.valueOf(t3.a(0.0f, 1.0f, (Color.alpha(extendedFloatingActionButton.getCurrentTextColor()) / 255.0f) / Color.alpha(extendedFloatingActionButton.H.getColorForState(extendedFloatingActionButton.getDrawableState(), ((z7) this.b).b.H.getDefaultColor()))));
            case 1:
                ((Drawable) obj).copyBounds((Rect) this.b);
                return new PointF(r0.left, r0.top);
            default:
                Matrix matrix = (Matrix) this.b;
                matrix.set(((ImageView) obj).getImageMatrix());
                return matrix;
        }
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) obj;
                Float f = (Float) obj2;
                int colorForState = extendedFloatingActionButton.H.getColorForState(extendedFloatingActionButton.getDrawableState(), ((z7) this.b).b.H.getDefaultColor());
                ColorStateList colorStateListValueOf = ColorStateList.valueOf(Color.argb((int) (t3.a(0.0f, Color.alpha(colorForState) / 255.0f, f.floatValue()) * 255.0f), Color.red(colorForState), Color.green(colorForState), Color.blue(colorForState)));
                if (f.floatValue() != 1.0f) {
                    extendedFloatingActionButton.f(colorStateListValueOf);
                } else {
                    extendedFloatingActionButton.f(extendedFloatingActionButton.H);
                }
                break;
            case 1:
                Drawable drawable = (Drawable) obj;
                PointF pointF = (PointF) obj2;
                Rect rect = (Rect) this.b;
                drawable.copyBounds(rect);
                rect.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
                drawable.setBounds(rect);
                break;
            default:
                ((ImageView) obj).setImageMatrix((Matrix) obj2);
                break;
        }
    }

    public y7() {
        super(Matrix.class, "imageMatrixProperty");
        this.b = new Matrix();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y7(z7 z7Var) {
        super(Float.class, "LABEL_OPACITY_PROPERTY");
        this.b = z7Var;
    }
}
