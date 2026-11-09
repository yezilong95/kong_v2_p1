package com.mamimobi.videostream.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import defpackage.e;
import defpackage.f;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AIFieldView extends View {
    public static final /* synthetic */ int S = 0;
    public ArrayList E;
    public float F;
    public float G;
    public float H;
    public float I;
    public float J;
    public float K;
    public float L;
    public boolean M;
    public f N;
    public final boolean O;
    public final Paint P;
    public final Paint Q;
    public final Paint R;

    public AIFieldView(Context context) {
        super(context);
        this.L = 0.0f;
        this.M = true;
        this.O = true;
        this.P = new Paint(1);
        this.Q = new Paint(1);
        this.R = new Paint(1);
        a();
    }

    public final void a() {
        this.Q.setMaskFilter(new BlurMaskFilter(60.0f, BlurMaskFilter.Blur.NORMAL));
        this.P.setColor(-7811841);
        this.E = new ArrayList();
        post(new e(0, this));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00fe  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onDraw(android.graphics.Canvas r20) {
        /*
            Method dump skipped, instruction units count: 558
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mamimobi.videostream.view.AIFieldView.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.H = i / 2.0f;
        this.I = i2 / 2.0f;
    }

    public void setAutoAnimation(boolean z) {
        this.M = z;
    }

    public AIFieldView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.L = 0.0f;
        this.M = true;
        this.O = true;
        this.P = new Paint(1);
        this.Q = new Paint(1);
        this.R = new Paint(1);
        a();
    }

    public AIFieldView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.L = 0.0f;
        this.M = true;
        this.O = true;
        this.P = new Paint(1);
        this.Q = new Paint(1);
        this.R = new Paint(1);
        a();
    }
}
