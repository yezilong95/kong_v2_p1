package defpackage;

import android.graphics.Matrix;
import android.graphics.Paint;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class u31 extends v31 {
    public final Matrix a;
    public final ArrayList b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public final Matrix j;
    public String k;

    public u31() {
        this.a = new Matrix();
        this.b = new ArrayList();
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 1.0f;
        this.g = 1.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = new Matrix();
        this.k = null;
    }

    @Override // defpackage.v31
    public final boolean a() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.b;
            if (i >= arrayList.size()) {
                return false;
            }
            if (((v31) arrayList.get(i)).a()) {
                return true;
            }
            i++;
        }
    }

    @Override // defpackage.v31
    public final boolean b(int[] iArr) {
        int i = 0;
        boolean zB = false;
        while (true) {
            ArrayList arrayList = this.b;
            if (i >= arrayList.size()) {
                return zB;
            }
            zB |= ((v31) arrayList.get(i)).b(iArr);
            i++;
        }
    }

    public final void c() {
        Matrix matrix = this.j;
        matrix.reset();
        matrix.postTranslate(-this.d, -this.e);
        matrix.postScale(this.f, this.g);
        matrix.postRotate(this.c, 0.0f, 0.0f);
        matrix.postTranslate(this.h + this.d, this.i + this.e);
    }

    public String getGroupName() {
        return this.k;
    }

    public Matrix getLocalMatrix() {
        return this.j;
    }

    public float getPivotX() {
        return this.d;
    }

    public float getPivotY() {
        return this.e;
    }

    public float getRotation() {
        return this.c;
    }

    public float getScaleX() {
        return this.f;
    }

    public float getScaleY() {
        return this.g;
    }

    public float getTranslateX() {
        return this.h;
    }

    public float getTranslateY() {
        return this.i;
    }

    public void setPivotX(float f) {
        if (f != this.d) {
            this.d = f;
            c();
        }
    }

    public void setPivotY(float f) {
        if (f != this.e) {
            this.e = f;
            c();
        }
    }

    public void setRotation(float f) {
        if (f != this.c) {
            this.c = f;
            c();
        }
    }

    public void setScaleX(float f) {
        if (f != this.f) {
            this.f = f;
            c();
        }
    }

    public void setScaleY(float f) {
        if (f != this.g) {
            this.g = f;
            c();
        }
    }

    public void setTranslateX(float f) {
        if (f != this.h) {
            this.h = f;
            c();
        }
    }

    public void setTranslateY(float f) {
        if (f != this.i) {
            this.i = f;
            c();
        }
    }

    public u31(u31 u31Var, k8 k8Var) {
        w31 s31Var;
        this.a = new Matrix();
        this.b = new ArrayList();
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 1.0f;
        this.g = 1.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        Matrix matrix = new Matrix();
        this.j = matrix;
        this.k = null;
        this.c = u31Var.c;
        this.d = u31Var.d;
        this.e = u31Var.e;
        this.f = u31Var.f;
        this.g = u31Var.g;
        this.h = u31Var.h;
        this.i = u31Var.i;
        String str = u31Var.k;
        this.k = str;
        if (str != null) {
            k8Var.put(str, this);
        }
        matrix.set(u31Var.j);
        ArrayList arrayList = u31Var.b;
        for (int i = 0; i < arrayList.size(); i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof u31) {
                this.b.add(new u31((u31) obj, k8Var));
            } else {
                if (obj instanceof t31) {
                    t31 t31Var = (t31) obj;
                    t31 t31Var2 = new t31(t31Var);
                    t31Var2.e = 0.0f;
                    t31Var2.g = 1.0f;
                    t31Var2.h = 1.0f;
                    t31Var2.i = 0.0f;
                    t31Var2.j = 1.0f;
                    t31Var2.k = 0.0f;
                    t31Var2.l = Paint.Cap.BUTT;
                    t31Var2.m = Paint.Join.MITER;
                    t31Var2.n = 4.0f;
                    t31Var2.d = t31Var.d;
                    t31Var2.e = t31Var.e;
                    t31Var2.g = t31Var.g;
                    t31Var2.f = t31Var.f;
                    t31Var2.c = t31Var.c;
                    t31Var2.h = t31Var.h;
                    t31Var2.i = t31Var.i;
                    t31Var2.j = t31Var.j;
                    t31Var2.k = t31Var.k;
                    t31Var2.l = t31Var.l;
                    t31Var2.m = t31Var.m;
                    t31Var2.n = t31Var.n;
                    s31Var = t31Var2;
                } else if (obj instanceof s31) {
                    s31Var = new s31((s31) obj);
                } else {
                    throw new IllegalStateException("Unknown object in the tree!");
                }
                this.b.add(s31Var);
                Object obj2 = s31Var.b;
                if (obj2 != null) {
                    k8Var.put(obj2, s31Var);
                }
            }
        }
    }
}
