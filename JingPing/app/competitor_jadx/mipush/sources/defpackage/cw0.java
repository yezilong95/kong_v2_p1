package defpackage;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowInsetsController;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.behavior.SwipeDismissBehavior;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class cw0 implements u2, k3 {
    public static cw0 G;
    public final /* synthetic */ int E;
    public Object F;

    public /* synthetic */ cw0(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // defpackage.u2
    public boolean b(View view) {
        switch (this.E) {
            case 2:
                SwipeDismissBehavior swipeDismissBehavior = (SwipeDismissBehavior) this.F;
                if (!swipeDismissBehavior.w(view)) {
                    return false;
                }
                WeakHashMap weakHashMap = f51.a;
                boolean z = view.getLayoutDirection() == 1;
                int i = swipeDismissBehavior.H;
                view.offsetLeftAndRight((!(i == 0 && z) && (i != 1 || z)) ? view.getWidth() : -view.getWidth());
                view.setAlpha(0.0f);
                return true;
            default:
                int currentItem = ((ViewPager2) view).getCurrentItem() + 1;
                ViewPager2 viewPager2 = (ViewPager2) ((tb) this.F).d;
                if (viewPager2.V) {
                    viewPager2.c(currentItem);
                }
                return true;
        }
    }

    public cw0(View view) {
        this.E = 1;
        if (Build.VERSION.SDK_INT < 30) {
            this.F = new wl0(view);
            return;
        }
        gw0 gw0Var = new gw0(view);
        gw0Var.F = view;
        this.F = gw0Var;
    }

    public cw0(int i) {
        this.E = i;
        switch (i) {
            case 4:
                break;
            default:
                this.F = new Object();
                new Handler(Looper.getMainLooper(), new bw0(this));
                break;
        }
    }

    public cw0(WindowInsetsController windowInsetsController) {
        this.E = 1;
        gw0 gw0Var = new gw0(null);
        gw0Var.G = windowInsetsController;
        this.F = gw0Var;
    }
}
