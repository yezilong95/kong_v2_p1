package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.R$styleable;
import androidx.work.impl.WorkDatabaseVersions;

/* JADX INFO: loaded from: classes.dex */
public final class kg {
    public static final SparseIntArray n;
    public boolean a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public boolean l;
    public float m;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        n = sparseIntArray;
        sparseIntArray.append(R$styleable.Transform_android_rotation, 1);
        sparseIntArray.append(R$styleable.Transform_android_rotationX, 2);
        sparseIntArray.append(R$styleable.Transform_android_rotationY, 3);
        sparseIntArray.append(R$styleable.Transform_android_scaleX, 4);
        sparseIntArray.append(R$styleable.Transform_android_scaleY, 5);
        sparseIntArray.append(R$styleable.Transform_android_transformPivotX, 6);
        sparseIntArray.append(R$styleable.Transform_android_transformPivotY, 7);
        sparseIntArray.append(R$styleable.Transform_android_translationX, 8);
        sparseIntArray.append(R$styleable.Transform_android_translationY, 9);
        sparseIntArray.append(R$styleable.Transform_android_translationZ, 10);
        sparseIntArray.append(R$styleable.Transform_android_elevation, 11);
    }

    public final void a(kg kgVar) {
        this.a = kgVar.a;
        this.b = kgVar.b;
        this.c = kgVar.c;
        this.d = kgVar.d;
        this.e = kgVar.e;
        this.f = kgVar.f;
        this.g = kgVar.g;
        this.h = kgVar.h;
        this.i = kgVar.i;
        this.j = kgVar.j;
        this.k = kgVar.k;
        this.l = kgVar.l;
        this.m = kgVar.m;
    }

    public final void b(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Transform);
        this.a = true;
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            switch (n.get(index)) {
                case 1:
                    this.b = typedArrayObtainStyledAttributes.getFloat(index, this.b);
                    break;
                case 2:
                    this.c = typedArrayObtainStyledAttributes.getFloat(index, this.c);
                    break;
                case 3:
                    this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
                    break;
                case 4:
                    this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
                    break;
                case 5:
                    this.f = typedArrayObtainStyledAttributes.getFloat(index, this.f);
                    break;
                case 6:
                    this.g = typedArrayObtainStyledAttributes.getDimension(index, this.g);
                    break;
                case 7:
                    this.h = typedArrayObtainStyledAttributes.getDimension(index, this.h);
                    break;
                case 8:
                    this.i = typedArrayObtainStyledAttributes.getDimension(index, this.i);
                    break;
                case WorkDatabaseVersions.VERSION_9 /* 9 */:
                    this.j = typedArrayObtainStyledAttributes.getDimension(index, this.j);
                    break;
                case WorkDatabaseVersions.VERSION_10 /* 10 */:
                    this.k = typedArrayObtainStyledAttributes.getDimension(index, this.k);
                    break;
                case WorkDatabaseVersions.VERSION_11 /* 11 */:
                    this.l = true;
                    this.m = typedArrayObtainStyledAttributes.getDimension(index, this.m);
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
