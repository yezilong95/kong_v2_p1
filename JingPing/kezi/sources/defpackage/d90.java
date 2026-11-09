package defpackage;

import android.graphics.Rect;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.profileinstaller.ProfileInstallReceiver;
import androidx.work.impl.WorkDatabaseVersions;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigationrail.NavigationRailView;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.WeakHashMap;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class d90 implements ht0, g60, ic0, ha0, na, y0 {
    public Object b;

    public /* synthetic */ d90(Object obj) {
        this.b = obj;
    }

    public void a(g70... g70VarArr) {
        oy.j(g70VarArr, "migrations");
        for (g70 g70Var : g70VarArr) {
            int i = g70Var.startVersion;
            int i2 = g70Var.endVersion;
            LinkedHashMap linkedHashMap = (LinkedHashMap) this.b;
            Integer numValueOf = Integer.valueOf(i);
            Object treeMap = linkedHashMap.get(numValueOf);
            if (treeMap == null) {
                treeMap = new TreeMap();
                linkedHashMap.put(numValueOf, treeMap);
            }
            TreeMap treeMap2 = (TreeMap) treeMap;
            if (treeMap2.containsKey(Integer.valueOf(i2))) {
                Log.w("ROOM", "Overriding migration " + treeMap2.get(Integer.valueOf(i2)) + " with " + g70Var);
            }
            treeMap2.put(Integer.valueOf(i2), g70Var);
        }
    }

    @Override // defpackage.ic0
    public void b(int i, Serializable serializable) {
        String str;
        switch (i) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
            default:
                str = ExtensionRequestData.EMPTY_VALUE;
                break;
            case WorkDatabaseVersions.VERSION_10 /* 10 */:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i == 6 || i == 7 || i == 8) {
            Log.e("ProfileInstaller", str, (Throwable) serializable);
        } else {
            Log.d("ProfileInstaller", str);
        }
        ((ProfileInstallReceiver) this.b).setResultCode(i);
    }

    @Override // defpackage.y0
    public boolean c(View view) {
        SwipeDismissBehavior swipeDismissBehavior = (SwipeDismissBehavior) this.b;
        if (!swipeDismissBehavior.w(view)) {
            return false;
        }
        WeakHashMap weakHashMap = xr0.a;
        boolean z = view.getLayoutDirection() == 1;
        int i = swipeDismissBehavior.e;
        view.offsetLeftAndRight((!(i == 0 && z) && (i != 1 || z)) ? view.getWidth() : -view.getWidth());
        view.setAlpha(0.0f);
        return true;
    }

    @Override // defpackage.ic0
    public void d() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // defpackage.ha0
    public xu0 e(View view, xu0 xu0Var) {
        ScrimInsetsFrameLayout scrimInsetsFrameLayout = (ScrimInsetsFrameLayout) this.b;
        if (scrimInsetsFrameLayout.c == null) {
            scrimInsetsFrameLayout.c = new Rect();
        }
        scrimInsetsFrameLayout.c.set(xu0Var.b(), xu0Var.d(), xu0Var.c(), xu0Var.a());
        scrimInsetsFrameLayout.e(xu0Var);
        uu0 uu0Var = xu0Var.a;
        scrimInsetsFrameLayout.setWillNotDraw(uu0Var.j().equals(jy.e) || scrimInsetsFrameLayout.b == null);
        WeakHashMap weakHashMap = xr0.a;
        scrimInsetsFrameLayout.postInvalidateOnAnimation();
        return uu0Var.c();
    }

    @Override // defpackage.ht0
    public xu0 f(View view, xu0 xu0Var, it0 it0Var) {
        boolean fitsSystemWindows;
        boolean fitsSystemWindows2;
        boolean fitsSystemWindows3;
        jy jyVarF = xu0Var.a.f(7);
        NavigationRailView navigationRailView = (NavigationRailView) this.b;
        Boolean bool = navigationRailView.i;
        if (bool != null) {
            fitsSystemWindows = bool.booleanValue();
        } else {
            WeakHashMap weakHashMap = xr0.a;
            fitsSystemWindows = navigationRailView.getFitsSystemWindows();
        }
        if (fitsSystemWindows) {
            it0Var.b += jyVarF.b;
        }
        Boolean bool2 = navigationRailView.j;
        if (bool2 != null) {
            fitsSystemWindows2 = bool2.booleanValue();
        } else {
            WeakHashMap weakHashMap2 = xr0.a;
            fitsSystemWindows2 = navigationRailView.getFitsSystemWindows();
        }
        if (fitsSystemWindows2) {
            it0Var.d += jyVarF.d;
        }
        Boolean bool3 = navigationRailView.k;
        if (bool3 != null) {
            fitsSystemWindows3 = bool3.booleanValue();
        } else {
            WeakHashMap weakHashMap3 = xr0.a;
            fitsSystemWindows3 = navigationRailView.getFitsSystemWindows();
        }
        if (fitsSystemWindows3) {
            it0Var.a += rp0.I(view) ? jyVarF.c : jyVarF.a;
        }
        int i = it0Var.a;
        int i2 = it0Var.b;
        int i3 = it0Var.c;
        int i4 = it0Var.d;
        WeakHashMap weakHashMap4 = xr0.a;
        view.setPaddingRelative(i, i2, i3, i4);
        return xu0Var;
    }

    @Override // defpackage.na
    public void h() {
        ((zj0) this.b).a();
    }

    @Override // defpackage.g60
    public boolean m(i60 i60Var, MenuItem menuItem) {
        ((NavigationView) this.b).getClass();
        return false;
    }

    public d90(ViewGroup viewGroup) {
        this.b = viewGroup.getOverlay();
    }

    public d90(int i) {
        switch (i) {
            case 7:
                this.b = new LinkedHashSet();
                break;
            default:
                this.b = new LinkedHashMap();
                break;
        }
    }

    @Override // defpackage.g60
    public void i(i60 i60Var) {
    }
}
