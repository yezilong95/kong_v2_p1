package defpackage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a40 extends qp {
    public final aq P;
    public z5 Q;
    public a41 R;

    public a40(Context context, aa aaVar, aq aqVar, z5 z5Var) {
        super(context, aaVar);
        this.P = aqVar;
        this.Q = z5Var;
        z5Var.a = this;
    }

    @Override // defpackage.qp
    public final boolean d(boolean z, boolean z2, boolean z3) {
        a41 a41Var;
        boolean zD = super.d(z, z2, z3);
        if (this.G != null && Settings.Global.getFloat(this.E.getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f && (a41Var = this.R) != null) {
            return a41Var.setVisible(z, z2);
        }
        if (!isRunning()) {
            this.Q.c();
        }
        if (z && z3) {
            this.Q.o();
        }
        return zD;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int i;
        a41 a41Var;
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            boolean z = this.G != null && Settings.Global.getFloat(this.E.getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f;
            aa aaVar = this.F;
            if (z && (a41Var = this.R) != null) {
                a41Var.setBounds(getBounds());
                ip.g(this.R, aaVar.c[0]);
                this.R.draw(canvas);
                return;
            }
            canvas.save();
            aq aqVar = this.P;
            Rect bounds = getBounds();
            float fB = b();
            ObjectAnimator objectAnimator = this.H;
            boolean z2 = objectAnimator != null && objectAnimator.isRunning();
            ObjectAnimator objectAnimator2 = this.I;
            boolean z3 = objectAnimator2 != null && objectAnimator2.isRunning();
            aqVar.a.a();
            aqVar.a(canvas, bounds, fB, z2, z3);
            int i2 = aaVar.g;
            int i3 = this.N;
            Paint paint = this.M;
            if (i2 == 0) {
                this.P.d(canvas, paint, 0.0f, 1.0f, aaVar.d, i3, 0);
                i = i2;
            } else {
                zp zpVar = (zp) ((ArrayList) this.Q.b).get(0);
                ArrayList arrayList = (ArrayList) this.Q.b;
                zp zpVar2 = (zp) arrayList.get(arrayList.size() - 1);
                aq aqVar2 = this.P;
                if (aqVar2 instanceof d90) {
                    i = i2;
                    aqVar2.d(canvas, paint, 0.0f, zpVar.a, aaVar.d, i3, i);
                    this.P.d(canvas, paint, zpVar2.b, 1.0f, aaVar.d, i3, i);
                } else {
                    i = i2;
                    i3 = 0;
                    aqVar2.d(canvas, paint, zpVar2.b, zpVar.a + 1.0f, aaVar.d, 0, i);
                }
            }
            for (int i4 = 0; i4 < ((ArrayList) this.Q.b).size(); i4++) {
                zp zpVar3 = (zp) ((ArrayList) this.Q.b).get(i4);
                this.P.c(canvas, paint, zpVar3, this.N);
                if (i4 > 0 && i > 0) {
                    this.P.d(canvas, paint, ((zp) ((ArrayList) this.Q.b).get(i4 - 1)).b, zpVar3.a, aaVar.d, i3, i);
                }
            }
            canvas.restore();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.P.e();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.P.f();
    }
}
