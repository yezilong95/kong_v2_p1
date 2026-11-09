package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.R$styleable;
import androidx.work.impl.Scheduler;

/* JADX INFO: loaded from: classes.dex */
public class MockView extends View {
    public final Paint E;
    public final Paint F;
    public final Paint G;
    public boolean H;
    public boolean I;
    public String J;
    public final Rect K;
    public int L;
    public int M;
    public int N;
    public int O;

    public MockView(Context context) {
        super(context);
        this.E = new Paint();
        this.F = new Paint();
        this.G = new Paint();
        this.H = true;
        this.I = true;
        this.J = null;
        this.K = new Rect();
        this.L = Color.argb(255, 0, 0, 0);
        this.M = Color.argb(255, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
        this.N = Color.argb(255, 50, 50, 50);
        this.O = 4;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MockView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MockView_mock_label) {
                    this.J = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == R$styleable.MockView_mock_showDiagonals) {
                    this.H = typedArrayObtainStyledAttributes.getBoolean(index, this.H);
                } else if (index == R$styleable.MockView_mock_diagonalsColor) {
                    this.L = typedArrayObtainStyledAttributes.getColor(index, this.L);
                } else if (index == R$styleable.MockView_mock_labelBackgroundColor) {
                    this.N = typedArrayObtainStyledAttributes.getColor(index, this.N);
                } else if (index == R$styleable.MockView_mock_labelColor) {
                    this.M = typedArrayObtainStyledAttributes.getColor(index, this.M);
                } else if (index == R$styleable.MockView_mock_showLabel) {
                    this.I = typedArrayObtainStyledAttributes.getBoolean(index, this.I);
                }
            }
        }
        if (this.J == null) {
            try {
                this.J = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        int i2 = this.L;
        Paint paint = this.E;
        paint.setColor(i2);
        paint.setAntiAlias(true);
        int i3 = this.M;
        Paint paint2 = this.F;
        paint2.setColor(i3);
        paint2.setAntiAlias(true);
        this.G.setColor(this.N);
        this.O = Math.round((getResources().getDisplayMetrics().xdpi / 160.0f) * this.O);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Canvas canvas2;
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.H) {
            width--;
            height--;
            float f = width;
            float f2 = height;
            canvas2 = canvas;
            canvas2.drawLine(0.0f, 0.0f, f, f2, this.E);
            canvas2.drawLine(0.0f, f2, f, 0.0f, this.E);
            canvas2.drawLine(0.0f, 0.0f, f, 0.0f, this.E);
            canvas2.drawLine(f, 0.0f, f, f2, this.E);
            canvas2.drawLine(f, f2, 0.0f, f2, this.E);
            canvas2.drawLine(0.0f, f2, 0.0f, 0.0f, this.E);
        } else {
            canvas2 = canvas;
        }
        String str = this.J;
        if (str == null || !this.I) {
            return;
        }
        int length = str.length();
        Paint paint = this.F;
        Rect rect = this.K;
        paint.getTextBounds(str, 0, length, rect);
        float fWidth = (width - rect.width()) / 2.0f;
        float fHeight = ((height - rect.height()) / 2.0f) + rect.height();
        rect.offset((int) fWidth, (int) fHeight);
        int i = rect.left;
        int i2 = this.O;
        rect.set(i - i2, rect.top - i2, rect.right + i2, rect.bottom + i2);
        canvas2.drawRect(rect, this.G);
        canvas2.drawText(this.J, fWidth, fHeight, paint);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = new Paint();
        this.F = new Paint();
        this.G = new Paint();
        this.H = true;
        this.I = true;
        this.J = null;
        this.K = new Rect();
        this.L = Color.argb(255, 0, 0, 0);
        this.M = Color.argb(255, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
        this.N = Color.argb(255, 50, 50, 50);
        this.O = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.E = new Paint();
        this.F = new Paint();
        this.G = new Paint();
        this.H = true;
        this.I = true;
        this.J = null;
        this.K = new Rect();
        this.L = Color.argb(255, 0, 0, 0);
        this.M = Color.argb(255, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
        this.N = Color.argb(255, 50, 50, 50);
        this.O = 4;
        a(context, attributeSet);
    }
}
