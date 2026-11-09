package defpackage;

import android.graphics.Matrix;
import android.graphics.Paint;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class pq0 extends qq0 {
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

    public pq0() {
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

    @Override // defpackage.qq0
    public final boolean a() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.b;
            if (i >= arrayList.size()) {
                return false;
            }
            if (((qq0) arrayList.get(i)).a()) {
                return true;
            }
            i++;
        }
    }

    @Override // defpackage.qq0
    public final boolean b(int[] iArr) {
        int i = 0;
        boolean zB = false;
        while (true) {
            ArrayList arrayList = this.b;
            if (i >= arrayList.size()) {
                return zB;
            }
            zB |= ((qq0) arrayList.get(i)).b(iArr);
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

    public pq0(pq0 pq0Var, c7 c7Var) {
        rq0 nq0Var;
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
        this.c = pq0Var.c;
        this.d = pq0Var.d;
        this.e = pq0Var.e;
        this.f = pq0Var.f;
        this.g = pq0Var.g;
        this.h = pq0Var.h;
        this.i = pq0Var.i;
        String str = pq0Var.k;
        this.k = str;
        if (str != null) {
            c7Var.put(str, this);
        }
        matrix.set(pq0Var.j);
        ArrayList arrayList = pq0Var.b;
        for (int i = 0; i < arrayList.size(); i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof pq0) {
                this.b.add(new pq0((pq0) obj, c7Var));
            } else {
                if (obj instanceof oq0) {
                    oq0 oq0Var = (oq0) obj;
                    oq0 oq0Var2 = new oq0(oq0Var);
                    oq0Var2.e = 0.0f;
                    oq0Var2.g = 1.0f;
                    oq0Var2.h = 1.0f;
                    oq0Var2.i = 0.0f;
                    oq0Var2.j = 1.0f;
                    oq0Var2.k = 0.0f;
                    oq0Var2.l = Paint.Cap.BUTT;
                    oq0Var2.m = Paint.Join.MITER;
                    oq0Var2.n = 4.0f;
                    oq0Var2.d = oq0Var.d;
                    oq0Var2.e = oq0Var.e;
                    oq0Var2.g = oq0Var.g;
                    oq0Var2.f = oq0Var.f;
                    oq0Var2.c = oq0Var.c;
                    oq0Var2.h = oq0Var.h;
                    oq0Var2.i = oq0Var.i;
                    oq0Var2.j = oq0Var.j;
                    oq0Var2.k = oq0Var.k;
                    oq0Var2.l = oq0Var.l;
                    oq0Var2.m = oq0Var.m;
                    oq0Var2.n = oq0Var.n;
                    nq0Var = oq0Var2;
                } else if (obj instanceof nq0) {
                    nq0Var = new nq0((nq0) obj);
                } else {
                    throw new IllegalStateException("Unknown object in the tree!");
                }
                this.b.add(nq0Var);
                Object obj2 = nq0Var.b;
                if (obj2 != null) {
                    c7Var.put(obj2, nq0Var);
                }
            }
        }
    }
}
