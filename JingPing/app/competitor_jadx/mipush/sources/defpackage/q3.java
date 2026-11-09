package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.b;
import androidx.appcompat.widget.d;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class q3 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ q3(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        b bVar;
        pe0 pe0Var;
        Activity activity;
        Rect rect;
        Object obj = this.F;
        switch (this.E) {
            case 0:
                ActivityChooserView activityChooserView = (ActivityChooserView) obj;
                if (activityChooserView.b()) {
                    if (!activityChooserView.isShown()) {
                        activityChooserView.getListPopupWindow().dismiss();
                        break;
                    } else {
                        activityChooserView.getListPopupWindow().d();
                        m3 m3Var = activityChooserView.K;
                        if (m3Var != null && (bVar = m3Var.a) != null && (pe0Var = bVar.I) != null) {
                            pe0Var.n(bVar.G);
                            break;
                        }
                    }
                }
                break;
            case 1:
                AppCompatSpinner appCompatSpinner = (AppCompatSpinner) obj;
                if (!appCompatSpinner.getInternalPopup().b()) {
                    appCompatSpinner.J.n(n6.b(appCompatSpinner), n6.a(appCompatSpinner));
                }
                ViewTreeObserver viewTreeObserver = appCompatSpinner.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    m6.a(viewTreeObserver, this);
                }
                break;
            case 2:
                t6 t6Var = (t6) obj;
                AppCompatSpinner appCompatSpinner2 = t6Var.k0;
                t6Var.getClass();
                WeakHashMap weakHashMap = f51.a;
                if (appCompatSpinner2.isAttachedToWindow() && appCompatSpinner2.getGlobalVisibleRect(t6Var.i0)) {
                    t6Var.s();
                    t6Var.d();
                } else {
                    t6Var.dismiss();
                }
                break;
            case 3:
                uc ucVar = (uc) obj;
                if (ucVar.b()) {
                    ArrayList arrayList = ucVar.L;
                    if (arrayList.size() > 0 && !((tc) arrayList.get(0)).a.c0) {
                        View view = ucVar.S;
                        if (view != null && view.isShown()) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ((tc) it.next()).a.d();
                            }
                        } else {
                            ucVar.dismiss();
                        }
                        break;
                    }
                }
                break;
            case 4:
                NavigationView navigationView = (NavigationView) obj;
                int[] iArr = navigationView.O;
                navigationView.getLocationOnScreen(iArr);
                boolean z = iArr[1] == 0;
                eh0 eh0Var = navigationView.M;
                if (eh0Var.b0 != z) {
                    eh0Var.b0 = z;
                    int i = (eh0Var.F.getChildCount() <= 0 && eh0Var.b0) ? eh0Var.d0 : 0;
                    NavigationMenuView navigationMenuView = eh0Var.E;
                    navigationMenuView.setPadding(0, i, 0, navigationMenuView.getPaddingBottom());
                }
                navigationView.setDrawTopInsetForeground(z && navigationView.R);
                int i2 = iArr[0];
                navigationView.setDrawLeftInsetForeground(i2 == 0 || navigationView.getWidth() + i2 == 0);
                Context context = navigationView.getContext();
                while (true) {
                    if (!(context instanceof ContextWrapper)) {
                        activity = null;
                    } else if (context instanceof Activity) {
                        activity = (Activity) context;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                }
                if (activity != null) {
                    int i3 = Build.VERSION.SDK_INT;
                    WindowManager windowManager = (WindowManager) activity.getSystemService("window");
                    if (i3 >= 30) {
                        rect = windowManager.getCurrentWindowMetrics().getBounds();
                    } else {
                        Display defaultDisplay = windowManager.getDefaultDisplay();
                        Point point = new Point();
                        defaultDisplay.getRealSize(point);
                        rect = new Rect();
                        rect.right = point.x;
                        rect.bottom = point.y;
                    }
                    navigationView.setDrawBottomInsetForeground((rect.height() - navigationView.getHeight() == iArr[1]) && (Color.alpha(activity.getWindow().getNavigationBarColor()) != 0) && navigationView.S);
                    navigationView.setDrawRightInsetForeground(rect.width() == iArr[0] || rect.width() - navigationView.getWidth() == iArr[0]);
                }
                break;
            default:
                zw0 zw0Var = (zw0) obj;
                if (zw0Var.b()) {
                    d dVar = zw0Var.L;
                    if (!dVar.c0) {
                        View view2 = zw0Var.Q;
                        if (view2 != null && view2.isShown()) {
                            dVar.d();
                        } else {
                            zw0Var.dismiss();
                        }
                    }
                }
                break;
        }
    }
}
