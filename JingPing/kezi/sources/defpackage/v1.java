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
public final class v1 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ v1(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        b bVar;
        y60 y60Var;
        Activity activity;
        Rect rect;
        Object obj = this.c;
        switch (this.b) {
            case 0:
                ActivityChooserView activityChooserView = (ActivityChooserView) obj;
                if (activityChooserView.b()) {
                    if (!activityChooserView.isShown()) {
                        activityChooserView.getListPopupWindow().dismiss();
                        break;
                    } else {
                        activityChooserView.getListPopupWindow().f();
                        r1 r1Var = activityChooserView.h;
                        if (r1Var != null && (bVar = r1Var.a) != null && (y60Var = bVar.f) != null) {
                            y60Var.p(bVar.d);
                            break;
                        }
                    }
                }
                break;
            case 1:
                AppCompatSpinner appCompatSpinner = (AppCompatSpinner) obj;
                if (!appCompatSpinner.getInternalPopup().b()) {
                    appCompatSpinner.g.e(j5.b(appCompatSpinner), j5.a(appCompatSpinner));
                }
                ViewTreeObserver viewTreeObserver = appCompatSpinner.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    i5.a(viewTreeObserver, this);
                }
                break;
            case 2:
                p5 p5Var = (p5) obj;
                AppCompatSpinner appCompatSpinner2 = p5Var.H;
                p5Var.getClass();
                WeakHashMap weakHashMap = xr0.a;
                if (appCompatSpinner2.isAttachedToWindow() && appCompatSpinner2.getGlobalVisibleRect(p5Var.F)) {
                    p5Var.s();
                    p5Var.f();
                } else {
                    p5Var.dismiss();
                }
                break;
            case 3:
                za zaVar = (za) obj;
                if (zaVar.b()) {
                    ArrayList arrayList = zaVar.i;
                    if (arrayList.size() > 0 && !((ya) arrayList.get(0)).a.z) {
                        View view = zaVar.p;
                        if (view != null && view.isShown()) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ((ya) it.next()).a.f();
                            }
                        } else {
                            zaVar.dismiss();
                        }
                        break;
                    }
                }
                break;
            case 4:
                NavigationView navigationView = (NavigationView) obj;
                int[] iArr = navigationView.l;
                navigationView.getLocationOnScreen(iArr);
                boolean z = iArr[1] == 0;
                b90 b90Var = navigationView.j;
                if (b90Var.y != z) {
                    b90Var.y = z;
                    int i = (b90Var.c.getChildCount() <= 0 && b90Var.y) ? b90Var.A : 0;
                    NavigationMenuView navigationMenuView = b90Var.b;
                    navigationMenuView.setPadding(0, i, 0, navigationMenuView.getPaddingBottom());
                }
                navigationView.setDrawTopInsetForeground(z && navigationView.o);
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
                    int i4 = k9.m;
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
                    navigationView.setDrawBottomInsetForeground((rect.height() - navigationView.getHeight() == iArr[1]) && (Color.alpha(activity.getWindow().getNavigationBarColor()) != 0) && navigationView.p);
                    navigationView.setDrawRightInsetForeground(rect.width() == iArr[0] || rect.width() - navigationView.getWidth() == iArr[0]);
                }
                break;
            default:
                lk0 lk0Var = (lk0) obj;
                if (lk0Var.b()) {
                    d dVar = lk0Var.i;
                    if (!dVar.z) {
                        View view2 = lk0Var.n;
                        if (view2 != null && view2.isShown()) {
                            dVar.f();
                        } else {
                            lk0Var.dismiss();
                        }
                    }
                }
                break;
        }
    }
}
