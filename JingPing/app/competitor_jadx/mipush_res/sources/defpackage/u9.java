package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Property;
import android.widget.ImageView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class u9 extends Property {
    public final /* synthetic */ int a = 1;
    public final Object b;

    public u9() {
        super(Matrix.class, "imageMatrixProperty");
        this.b = new Matrix();
    }

    @Override // android.util.Property
    public final Object get(Object obj) {
        switch (this.a) {
            case 0:
                ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) obj;
                return Float.valueOf(y4.a(0.0f, 1.0f, (Color.alpha(extendedFloatingActionButton.getCurrentTextColor()) / 255.0f) / Color.alpha(extendedFloatingActionButton.n0.getColorForState(extendedFloatingActionButton.getDrawableState(), ((v9) this.b).b.n0.getDefaultColor()))));
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
                int colorForState = extendedFloatingActionButton.n0.getColorForState(extendedFloatingActionButton.getDrawableState(), ((v9) this.b).b.n0.getDefaultColor());
                ColorStateList colorStateListValueOf = ColorStateList.valueOf(Color.argb((int) (y4.a(0.0f, Color.alpha(colorForState) / 255.0f, f.floatValue()) * 255.0f), Color.red(colorForState), Color.green(colorForState), Color.blue(colorForState)));
                if (f.floatValue() != 1.0f) {
                    extendedFloatingActionButton.f(colorStateListValueOf);
                } else {
                    extendedFloatingActionButton.f(extendedFloatingActionButton.n0);
                }
                break;
            default:
                ((ImageView) obj).setImageMatrix((Matrix) obj2);
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u9(v9 v9Var) {
        super(Float.class, "LABEL_OPACITY_PROPERTY");
        this.b = v9Var;
    }
}
