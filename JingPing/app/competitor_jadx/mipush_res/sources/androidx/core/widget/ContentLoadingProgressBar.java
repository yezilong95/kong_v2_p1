package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.core.widget.ContentLoadingProgressBar;
import defpackage.li;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {
    public static final /* synthetic */ int G = 0;
    public final li E;
    public final li F;

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks(this.E);
        removeCallbacks(this.F);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.E);
        removeCallbacks(this.F);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [li] */
    /* JADX WARN: Type inference failed for: r2v2, types: [li] */
    public ContentLoadingProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        final int i = 0;
        this.E = new Runnable(this) { // from class: li
            public final /* synthetic */ ContentLoadingProgressBar F;

            {
                this.F = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ContentLoadingProgressBar contentLoadingProgressBar = this.F;
                switch (i) {
                    case 0:
                        int i2 = ContentLoadingProgressBar.G;
                        contentLoadingProgressBar.setVisibility(8);
                        break;
                    default:
                        int i3 = ContentLoadingProgressBar.G;
                        contentLoadingProgressBar.getClass();
                        System.currentTimeMillis();
                        contentLoadingProgressBar.setVisibility(0);
                        break;
                }
            }
        };
        final int i2 = 1;
        this.F = new Runnable(this) { // from class: li
            public final /* synthetic */ ContentLoadingProgressBar F;

            {
                this.F = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ContentLoadingProgressBar contentLoadingProgressBar = this.F;
                switch (i2) {
                    case 0:
                        int i22 = ContentLoadingProgressBar.G;
                        contentLoadingProgressBar.setVisibility(8);
                        break;
                    default:
                        int i3 = ContentLoadingProgressBar.G;
                        contentLoadingProgressBar.getClass();
                        System.currentTimeMillis();
                        contentLoadingProgressBar.setVisibility(0);
                        break;
                }
            }
        };
    }
}
