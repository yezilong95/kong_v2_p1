package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import defpackage.b30;
import defpackage.th;
import defpackage.yh;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {
    public int[] E;
    public int F;
    public final Context G;
    public b30 H;
    public String I;
    public View[] J;
    public final HashMap K;

    public ConstraintHelper(Context context) {
        super(context);
        this.E = new int[32];
        this.J = null;
        this.K = new HashMap();
        this.G = context;
        g(null);
    }

    public final void a(String str) {
        if (str == null || str.length() == 0 || this.G == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iE = e(strTrim);
        if (iE != 0) {
            this.K.put(Integer.valueOf(iE), strTrim);
            b(iE);
        } else {
            Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
        }
    }

    public final void b(int i) {
        if (i == getId()) {
            return;
        }
        int i2 = this.F + 1;
        int[] iArr = this.E;
        if (i2 > iArr.length) {
            this.E = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.E;
        int i3 = this.F;
        iArr2[i3] = i;
        this.F = i3 + 1;
    }

    public final void c() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) parent;
        int visibility = getVisibility();
        float elevation = getElevation();
        for (int i = 0; i < this.F; i++) {
            View viewE = constraintLayout.e(this.E[i]);
            if (viewE != null) {
                viewE.setVisibility(visibility);
                if (elevation > 0.0f) {
                    viewE.setTranslationZ(viewE.getTranslationZ() + elevation);
                }
            }
        }
    }

    public final int d(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        String resourceEntryName;
        if (str != null && (resources = this.G.getResources()) != null) {
            int childCount = constraintLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = constraintLayout.getChildAt(i);
                if (childAt.getId() != -1) {
                    try {
                        resourceEntryName = resources.getResourceEntryName(childAt.getId());
                    } catch (Resources.NotFoundException unused) {
                        resourceEntryName = null;
                    }
                    if (str.equals(resourceEntryName)) {
                        return childAt.getId();
                    }
                }
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int e(java.lang.String r5) {
        /*
            r4 = this;
            android.view.ViewParent r0 = r4.getParent()
            boolean r0 = r0 instanceof androidx.constraintlayout.widget.ConstraintLayout
            r1 = 0
            if (r0 == 0) goto L10
            android.view.ViewParent r0 = r4.getParent()
            androidx.constraintlayout.widget.ConstraintLayout r0 = (androidx.constraintlayout.widget.ConstraintLayout) r0
            goto L11
        L10:
            r0 = r1
        L11:
            boolean r2 = r4.isInEditMode()
            if (r2 == 0) goto L38
            if (r0 == 0) goto L38
            if (r5 == 0) goto L2c
            java.util.HashMap r2 = r0.Q
            if (r2 == 0) goto L2c
            boolean r2 = r2.containsKey(r5)
            if (r2 == 0) goto L2c
            java.util.HashMap r2 = r0.Q
            java.lang.Object r2 = r2.get(r5)
            goto L2d
        L2c:
            r2 = r1
        L2d:
            boolean r3 = r2 instanceof java.lang.Integer
            if (r3 == 0) goto L38
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            goto L39
        L38:
            r2 = 0
        L39:
            if (r2 != 0) goto L41
            if (r0 == 0) goto L41
            int r2 = r4.d(r0, r5)
        L41:
            if (r2 != 0) goto L4d
            java.lang.Class<androidx.constraintlayout.widget.R$id> r0 = androidx.constraintlayout.widget.R$id.class
            java.lang.reflect.Field r0 = r0.getField(r5)     // Catch: java.lang.Exception -> L4d
            int r2 = r0.getInt(r1)     // Catch: java.lang.Exception -> L4d
        L4d:
            if (r2 != 0) goto L5f
            android.content.Context r0 = r4.G
            android.content.res.Resources r1 = r0.getResources()
            java.lang.String r2 = "id"
            java.lang.String r0 = r0.getPackageName()
            int r2 = r1.getIdentifier(r5, r2, r0)
        L5f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintHelper.e(java.lang.String):int");
    }

    public final View[] f(ConstraintLayout constraintLayout) {
        View[] viewArr = this.J;
        if (viewArr == null || viewArr.length != this.F) {
            this.J = new View[this.F];
        }
        for (int i = 0; i < this.F; i++) {
            this.J[i] = constraintLayout.e(this.E[i]);
        }
        return this.J;
    }

    public void g(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.I = string;
                    setIds(string);
                }
            }
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.E, this.F);
    }

    public void h(c cVar, b30 b30Var, Constraints.LayoutParams layoutParams, SparseArray sparseArray) {
        th thVar = cVar.d;
        int[] iArr = thVar.e0;
        int i = 0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = thVar.f0;
            if (str != null && str.length() > 0) {
                String[] strArrSplit = thVar.f0.split(",");
                getContext();
                int[] iArrCopyOf = new int[strArrSplit.length];
                int i2 = 0;
                for (String str2 : strArrSplit) {
                    int iE = e(str2.trim());
                    if (iE != 0) {
                        iArrCopyOf[i2] = iE;
                        i2++;
                    }
                }
                if (i2 != strArrSplit.length) {
                    iArrCopyOf = Arrays.copyOf(iArrCopyOf, i2);
                }
                thVar.e0 = iArrCopyOf;
            }
        }
        b30Var.h0 = 0;
        Arrays.fill(b30Var.g0, (Object) null);
        if (thVar.e0 == null) {
            return;
        }
        while (true) {
            int[] iArr2 = thVar.e0;
            if (i >= iArr2.length) {
                return;
            }
            yh yhVar = (yh) sparseArray.get(iArr2[i]);
            if (yhVar != null) {
                b30Var.C(yhVar);
            }
            i++;
        }
    }

    public void k(ConstraintLayout constraintLayout) {
    }

    public final void l() {
        if (this.H == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).l0 = this.H;
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.I;
        if (str != null) {
            setIds(str);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setIds(String str) {
        this.I = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.F = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                a(str.substring(i));
                return;
            } else {
                a(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.I = null;
        this.F = 0;
        for (int i : iArr) {
            b(i);
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = new int[32];
        this.J = null;
        this.K = new HashMap();
        this.G = context;
        g(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.E = new int[32];
        this.J = null;
        this.K = new HashMap();
        this.G = context;
        g(attributeSet);
    }

    public void j() {
    }

    public void i(yh yhVar, boolean z) {
    }
}
