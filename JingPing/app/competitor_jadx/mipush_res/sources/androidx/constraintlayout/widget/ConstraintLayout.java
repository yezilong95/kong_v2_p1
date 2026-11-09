package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import defpackage.ai;
import defpackage.j90;
import defpackage.o20;
import defpackage.sh;
import defpackage.yh;
import defpackage.zh;
import io.nats.client.BaseConsumeOptions;
import io.nats.client.support.NatsJetStreamConstants;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    public static final /* synthetic */ int T = 0;
    public final SparseArray E;
    public final ArrayList F;
    public final zh G;
    public int H;
    public int I;
    public int J;
    public int K;
    public boolean L;
    public int M;
    public d N;
    public sh O;
    public int P;
    public HashMap Q;
    public final SparseArray R;
    public final b S;

    public ConstraintLayout(Context context) {
        super(context);
        this.E = new SparseArray();
        this.F = new ArrayList(4);
        this.G = new zh();
        this.H = 0;
        this.I = 0;
        this.J = Integer.MAX_VALUE;
        this.K = Integer.MAX_VALUE;
        this.L = true;
        this.M = 263;
        this.N = null;
        this.O = null;
        this.P = -1;
        this.Q = new HashMap();
        this.R = new SparseArray();
        this.S = new b(this);
        h(null, 0);
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingRight()) + Math.max(0, getPaddingLeft());
        int iMax2 = Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart());
        return iMax2 > 0 ? iMax2 : iMax;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* JADX WARN: Removed duplicated region for block: B:154:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01c1  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:154:0x02ea -> B:155:0x02ec). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(boolean r17, android.view.View r18, defpackage.yh r19, androidx.constraintlayout.widget.ConstraintLayout.LayoutParams r20, android.util.SparseArray r21) {
        /*
            Method dump skipped, instruction units count: 849
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.d(boolean, android.view.View, yh, androidx.constraintlayout.widget.ConstraintLayout$LayoutParams, android.util.SparseArray):void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList arrayList = this.F;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i = 0; i < size; i++) {
                ((ConstraintHelper) arrayList.get(i)).k(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = getWidth();
            float height = getHeight();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i3 = Integer.parseInt(strArrSplit[0]);
                        int i4 = Integer.parseInt(strArrSplit[1]);
                        int i5 = Integer.parseInt(strArrSplit[2]);
                        int i6 = (int) ((i3 / 1080.0f) * width);
                        int i7 = (int) ((i4 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f = i6;
                        float f2 = i7;
                        float f3 = i6 + ((int) ((i5 / 1080.0f) * width));
                        canvas.drawLine(f, f2, f3, f2, paint);
                        float f4 = i7 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f3, f2, f3, f4, paint);
                        canvas.drawLine(f3, f4, f, f4, paint);
                        canvas.drawLine(f, f4, f, f2, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f, f2, f3, f4, paint);
                        canvas.drawLine(f, f4, f3, f2, paint);
                    }
                }
            }
        }
    }

    public final View e(int i) {
        return (View) this.E.get(i);
    }

    public final yh f(View view) {
        if (view == this) {
            return this.G;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).l0;
    }

    @Override // android.view.View
    public final void forceLayout() {
        this.L = true;
        super.forceLayout();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public int getMaxHeight() {
        return this.K;
    }

    public int getMaxWidth() {
        return this.J;
    }

    public int getMinHeight() {
        return this.I;
    }

    public int getMinWidth() {
        return this.H;
    }

    public int getOptimizationLevel() {
        return this.G.s0;
    }

    public final void h(AttributeSet attributeSet, int i) {
        zh zhVar = this.G;
        zhVar.W = this;
        b bVar = this.S;
        zhVar.j0 = bVar;
        zhVar.i0.f = bVar;
        this.E.put(getId(), this);
        this.N = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout, i, 0);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == R$styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.H = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.H);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.I = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.I);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.J = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.J);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.K = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.K);
                } else if (index == R$styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.M = typedArrayObtainStyledAttributes.getInt(index, this.M);
                } else if (index == R$styleable.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            l(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.O = null;
                        }
                    }
                } else if (index == R$styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        d dVar = new d();
                        this.N = dVar;
                        dVar.h(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.N = null;
                    }
                    this.P = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        int i3 = this.M;
        zhVar.s0 = i3;
        j90.p = (i3 & NatsJetStreamConstants.MAX_PULL_SIZE) == 256;
    }

    public final boolean k() {
        return (getContext().getApplicationInfo().flags & 4194304) != 0 && 1 == getLayoutDirection();
    }

    public void l(int i) {
        this.O = new sh(getContext(), this, i);
    }

    public final void m(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        b bVar = this.S;
        int i5 = bVar.e;
        int iResolveSizeAndState = View.resolveSizeAndState(i3 + bVar.d, i, 0);
        int iResolveSizeAndState2 = View.resolveSizeAndState(i4 + i5, i2, 0) & 16777215;
        int iMin = Math.min(this.J, iResolveSizeAndState & 16777215);
        int iMin2 = Math.min(this.K, iResolveSizeAndState2);
        if (z) {
            iMin |= 16777216;
        }
        if (z2) {
            iMin2 |= 16777216;
        }
        setMeasuredDimension(iMin, iMin2);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00b4 A[PHI: r12
      0x00b4: PHI (r12v6 androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour) = 
      (r12v5 androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour)
      (r12v1 androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour)
     binds: [B:33:0x00c1, B:29:0x00b2] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void n(defpackage.zh r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 1770
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.n(zh, int, int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View content;
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            yh yhVar = layoutParams.l0;
            if ((childAt.getVisibility() != 8 || layoutParams.Y || layoutParams.Z || zIsInEditMode) && !layoutParams.a0) {
                int iN = yhVar.n();
                int iO = yhVar.o();
                int iM = yhVar.m() + iN;
                int iJ = yhVar.j() + iO;
                childAt.layout(iN, iO, iM, iJ);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(iN, iO, iM, iJ);
                }
            }
        }
        ArrayList arrayList = this.F;
        int size = arrayList.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                ((ConstraintHelper) arrayList.get(i6)).j();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x01a6  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instruction units count: 547
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        yh yhVarF = f(view);
        if ((view instanceof Guideline) && !(yhVarF instanceof o20)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            o20 o20Var = new o20();
            layoutParams.l0 = o20Var;
            layoutParams.Y = true;
            o20Var.C(layoutParams.R);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.l();
            ((LayoutParams) view.getLayoutParams()).Z = true;
            ArrayList arrayList = this.F;
            if (!arrayList.contains(constraintHelper)) {
                arrayList.add(constraintHelper);
            }
        }
        this.E.put(view.getId(), view);
        this.L = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.E.remove(view.getId());
        yh yhVarF = f(view);
        this.G.g0.remove(yhVarF);
        yhVarF.K = null;
        this.F.remove(view);
        this.L = true;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.L = true;
        super.requestLayout();
    }

    public void setConstraintSet(d dVar) {
        this.N = dVar;
    }

    @Override // android.view.View
    public void setId(int i) {
        int id = getId();
        SparseArray sparseArray = this.E;
        sparseArray.remove(id);
        super.setId(i);
        sparseArray.put(getId(), this);
    }

    public void setMaxHeight(int i) {
        if (i == this.K) {
            return;
        }
        this.K = i;
        requestLayout();
    }

    public void setMaxWidth(int i) {
        if (i == this.J) {
            return;
        }
        this.J = i;
        requestLayout();
    }

    public void setMinHeight(int i) {
        if (i == this.I) {
            return;
        }
        this.I = i;
        requestLayout();
    }

    public void setMinWidth(int i) {
        if (i == this.H) {
            return;
        }
        this.H = i;
        requestLayout();
    }

    public void setOnConstraintsChanged(ai aiVar) {
        sh shVar = this.O;
        if (shVar != null) {
            shVar.getClass();
        }
    }

    public void setOptimizationLevel(int i) {
        this.M = i;
        this.G.s0 = i;
        j90.p = (i & NatsJetStreamConstants.MAX_PULL_SIZE) == 256;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = new SparseArray();
        this.F = new ArrayList(4);
        this.G = new zh();
        this.H = 0;
        this.I = 0;
        this.J = Integer.MAX_VALUE;
        this.K = Integer.MAX_VALUE;
        this.L = true;
        this.M = 263;
        this.N = null;
        this.O = null;
        this.P = -1;
        this.Q = new HashMap();
        this.R = new SparseArray();
        this.S = new b(this);
        h(attributeSet, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.E = new SparseArray();
        this.F = new ArrayList(4);
        this.G = new zh();
        this.H = 0;
        this.I = 0;
        this.J = Integer.MAX_VALUE;
        this.K = Integer.MAX_VALUE;
        this.L = true;
        this.M = 263;
        this.N = null;
        this.O = null;
        this.P = -1;
        this.Q = new HashMap();
        this.R = new SparseArray();
        this.S = new b(this);
        h(attributeSet, i);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public float A;
        public String B;
        public final int C;
        public float D;
        public float E;
        public int F;
        public int G;
        public int H;
        public int I;
        public int J;
        public int K;
        public int L;
        public int M;
        public float N;
        public float O;
        public int P;
        public int Q;
        public int R;
        public boolean S;
        public boolean T;
        public String U;
        public boolean V;
        public boolean W;
        public boolean X;
        public boolean Y;
        public boolean Z;
        public int a;
        public boolean a0;
        public int b;
        public int b0;
        public float c;
        public int c0;
        public int d;
        public int d0;
        public int e;
        public int e0;
        public int f;
        public int f0;
        public int g;
        public int g0;
        public int h;
        public float h0;
        public int i;
        public int i0;
        public int j;
        public int j0;
        public int k;
        public float k0;
        public int l;
        public yh l0;
        public int m;
        public int n;
        public float o;
        public int p;
        public int q;
        public int r;
        public int s;
        public final int t;
        public int u;
        public final int v;
        public int w;
        public int x;
        public int y;
        public float z;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            int i;
            super(context, attributeSet);
            this.a = -1;
            this.b = -1;
            this.c = -1.0f;
            this.d = -1;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 1;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = 0;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 1.0f;
            this.O = 1.0f;
            this.P = -1;
            this.Q = -1;
            this.R = -1;
            this.S = false;
            this.T = false;
            this.U = null;
            this.V = true;
            this.W = true;
            this.X = false;
            this.Y = false;
            this.Z = false;
            this.a0 = false;
            this.b0 = -1;
            this.c0 = -1;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = -1;
            this.h0 = 0.5f;
            this.l0 = new yh();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                int i3 = a.a.get(index);
                switch (i3) {
                    case 1:
                        this.R = typedArrayObtainStyledAttributes.getInt(index, this.R);
                        break;
                    case 2:
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.m);
                        this.m = resourceId;
                        if (resourceId == -1) {
                            this.m = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 3:
                        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.n);
                        break;
                    case 4:
                        float f = typedArrayObtainStyledAttributes.getFloat(index, this.o) % 360.0f;
                        this.o = f;
                        if (f < 0.0f) {
                            this.o = (360.0f - f) % 360.0f;
                        }
                        break;
                    case 5:
                        this.a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.a);
                        break;
                    case 6:
                        this.b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.b);
                        break;
                    case 7:
                        this.c = typedArrayObtainStyledAttributes.getFloat(index, this.c);
                        break;
                    case 8:
                        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, this.d);
                        this.d = resourceId2;
                        if (resourceId2 == -1) {
                            this.d = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case WorkDatabaseVersions.VERSION_9 /* 9 */:
                        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, this.e);
                        this.e = resourceId3;
                        if (resourceId3 == -1) {
                            this.e = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 10:
                        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, this.f);
                        this.f = resourceId4;
                        if (resourceId4 == -1) {
                            this.f = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case WorkDatabaseVersions.VERSION_11 /* 11 */:
                        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, this.g);
                        this.g = resourceId5;
                        if (resourceId5 == -1) {
                            this.g = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case WorkDatabaseVersions.VERSION_12 /* 12 */:
                        int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, this.h);
                        this.h = resourceId6;
                        if (resourceId6 == -1) {
                            this.h = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case WorkDatabaseVersions.VERSION_13 /* 13 */:
                        int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, this.i);
                        this.i = resourceId7;
                        if (resourceId7 == -1) {
                            this.i = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case WorkDatabaseVersions.VERSION_14 /* 14 */:
                        int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, this.j);
                        this.j = resourceId8;
                        if (resourceId8 == -1) {
                            this.j = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case WorkDatabaseVersions.VERSION_15 /* 15 */:
                        int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, this.k);
                        this.k = resourceId9;
                        if (resourceId9 == -1) {
                            this.k = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 16:
                        int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, this.l);
                        this.l = resourceId10;
                        if (resourceId10 == -1) {
                            this.l = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 17:
                        int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, this.p);
                        this.p = resourceId11;
                        if (resourceId11 == -1) {
                            this.p = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 18:
                        int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, this.q);
                        this.q = resourceId12;
                        if (resourceId12 == -1) {
                            this.q = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 19:
                        int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, this.r);
                        this.r = resourceId13;
                        if (resourceId13 == -1) {
                            this.r = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                        int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, this.s);
                        this.s = resourceId14;
                        if (resourceId14 == -1) {
                            this.s = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 21:
                        this.t = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.t);
                        break;
                    case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                        this.u = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.u);
                        break;
                    case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                        this.v = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.v);
                        break;
                    case 24:
                        this.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.w);
                        break;
                    case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                        this.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.x);
                        break;
                    case 26:
                        this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.y);
                        break;
                    case 27:
                        this.S = typedArrayObtainStyledAttributes.getBoolean(index, this.S);
                        break;
                    case 28:
                        this.T = typedArrayObtainStyledAttributes.getBoolean(index, this.T);
                        break;
                    case 29:
                        this.z = typedArrayObtainStyledAttributes.getFloat(index, this.z);
                        break;
                    case 30:
                        this.A = typedArrayObtainStyledAttributes.getFloat(index, this.A);
                        break;
                    case 31:
                        int i4 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.H = i4;
                        if (i4 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        }
                        break;
                    case 32:
                        int i5 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.I = i5;
                        if (i5 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                        }
                        break;
                    case 33:
                        try {
                            this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                        } catch (Exception unused) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.J) == -2) {
                                this.J = -2;
                            }
                        }
                        break;
                    case 34:
                        try {
                            this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        } catch (Exception unused2) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.L) == -2) {
                                this.L = -2;
                            }
                        }
                        break;
                    case 35:
                        this.N = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.N));
                        this.H = 2;
                        break;
                    case 36:
                        try {
                            this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                        } catch (Exception unused3) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.K) == -2) {
                                this.K = -2;
                            }
                        }
                        break;
                    case 37:
                        try {
                            this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                        } catch (Exception unused4) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.M) == -2) {
                                this.M = -2;
                            }
                        }
                        break;
                    case 38:
                        this.O = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.O));
                        this.I = 2;
                        break;
                    default:
                        switch (i3) {
                            case 44:
                                String string = typedArrayObtainStyledAttributes.getString(index);
                                this.B = string;
                                this.C = -1;
                                if (string != null) {
                                    int length = string.length();
                                    int iIndexOf = this.B.indexOf(44);
                                    if (iIndexOf <= 0 || iIndexOf >= length - 1) {
                                        i = 0;
                                    } else {
                                        String strSubstring = this.B.substring(0, iIndexOf);
                                        if (strSubstring.equalsIgnoreCase("W")) {
                                            this.C = 0;
                                        } else if (strSubstring.equalsIgnoreCase("H")) {
                                            this.C = 1;
                                        }
                                        i = iIndexOf + 1;
                                    }
                                    int iIndexOf2 = this.B.indexOf(58);
                                    if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                                        String strSubstring2 = this.B.substring(i);
                                        if (strSubstring2.length() > 0) {
                                            Float.parseFloat(strSubstring2);
                                        }
                                    } else {
                                        String strSubstring3 = this.B.substring(i, iIndexOf2);
                                        String strSubstring4 = this.B.substring(iIndexOf2 + 1);
                                        if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                                            try {
                                                float f2 = Float.parseFloat(strSubstring3);
                                                float f3 = Float.parseFloat(strSubstring4);
                                                if (f2 > 0.0f && f3 > 0.0f) {
                                                    if (this.C == 1) {
                                                        Math.abs(f3 / f2);
                                                    } else {
                                                        Math.abs(f2 / f3);
                                                    }
                                                }
                                            } catch (NumberFormatException unused5) {
                                            }
                                        }
                                    }
                                }
                                break;
                            case 45:
                                this.D = typedArrayObtainStyledAttributes.getFloat(index, this.D);
                                break;
                            case 46:
                                this.E = typedArrayObtainStyledAttributes.getFloat(index, this.E);
                                break;
                            case 47:
                                this.F = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 48:
                                this.G = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 49:
                                this.P = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.P);
                                break;
                            case 50:
                                this.Q = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.Q);
                                break;
                            case 51:
                                this.U = typedArrayObtainStyledAttributes.getString(index);
                                break;
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            a();
        }

        public final void a() {
            this.Y = false;
            this.V = true;
            this.W = true;
            int i = ((ViewGroup.MarginLayoutParams) this).width;
            if (i == -2 && this.S) {
                this.V = false;
                if (this.H == 0) {
                    this.H = 1;
                }
            }
            int i2 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i2 == -2 && this.T) {
                this.W = false;
                if (this.I == 0) {
                    this.I = 1;
                }
            }
            if (i == 0 || i == -1) {
                this.V = false;
                if (i == 0 && this.H == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.S = true;
                }
            }
            if (i2 == 0 || i2 == -1) {
                this.W = false;
                if (i2 == 0 && this.I == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.T = true;
                }
            }
            if (this.c == -1.0f && this.a == -1 && this.b == -1) {
                return;
            }
            this.Y = true;
            this.V = true;
            this.W = true;
            if (!(this.l0 instanceof o20)) {
                this.l0 = new o20();
            }
            ((o20) this.l0).C(this.R);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x004f  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0056  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x007c  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void resolveLayoutDirection(int r10) {
            /*
                Method dump skipped, instruction units count: 253
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.resolveLayoutDirection(int):void");
        }

        public LayoutParams() {
            super(-2, -2);
            this.a = -1;
            this.b = -1;
            this.c = -1.0f;
            this.d = -1;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 1;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = 0;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 1.0f;
            this.O = 1.0f;
            this.P = -1;
            this.Q = -1;
            this.R = -1;
            this.S = false;
            this.T = false;
            this.U = null;
            this.V = true;
            this.W = true;
            this.X = false;
            this.Y = false;
            this.Z = false;
            this.a0 = false;
            this.b0 = -1;
            this.c0 = -1;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = -1;
            this.h0 = 0.5f;
            this.l0 = new yh();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = -1;
            this.b = -1;
            this.c = -1.0f;
            this.d = -1;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 1;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = 0;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 1.0f;
            this.O = 1.0f;
            this.P = -1;
            this.Q = -1;
            this.R = -1;
            this.S = false;
            this.T = false;
            this.U = null;
            this.V = true;
            this.W = true;
            this.X = false;
            this.Y = false;
            this.Z = false;
            this.a0 = false;
            this.b0 = -1;
            this.c0 = -1;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = -1;
            this.h0 = 0.5f;
            this.l0 = new yh();
        }
    }
}
