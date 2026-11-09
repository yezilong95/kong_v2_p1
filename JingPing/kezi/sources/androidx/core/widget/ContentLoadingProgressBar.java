package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.core.widget.ContentLoadingProgressBar;
import defpackage.wg;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {
    public static final /* synthetic */ int d = 0;
    public final wg b;
    public final wg c;

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks(this.b);
        removeCallbacks(this.c);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.b);
        removeCallbacks(this.c);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [wg] */
    /* JADX WARN: Type inference failed for: r2v2, types: [wg] */
    public ContentLoadingProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        final int i = 0;
        this.b = new Runnable(this) { // from class: wg
            public final /* synthetic */ ContentLoadingProgressBar c;

            {
                this.c = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ContentLoadingProgressBar contentLoadingProgressBar = this.c;
                switch (i) {
                    case 0:
                        int i2 = ContentLoadingProgressBar.d;
                        contentLoadingProgressBar.setVisibility(8);
                        break;
                    default:
                        int i3 = ContentLoadingProgressBar.d;
                        contentLoadingProgressBar.getClass();
                        System.currentTimeMillis();
                        contentLoadingProgressBar.setVisibility(0);
                        break;
                }
            }
        };
        final int i2 = 1;
        this.c = new Runnable(this) { // from class: wg
            public final /* synthetic */ ContentLoadingProgressBar c;

            {
                this.c = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ContentLoadingProgressBar contentLoadingProgressBar = this.c;
                switch (i2) {
                    case 0:
                        int i22 = ContentLoadingProgressBar.d;
                        contentLoadingProgressBar.setVisibility(8);
                        break;
                    default:
                        int i3 = ContentLoadingProgressBar.d;
                        contentLoadingProgressBar.getClass();
                        System.currentTimeMillis();
                        contentLoadingProgressBar.setVisibility(0);
                        break;
                }
            }
        };
    }
}
