package defpackage;

import android.R;
import android.graphics.Rect;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.b;
import androidx.profileinstaller.ProfileInstallReceiver;
import androidx.viewpager2.widget.ViewPager2;
import androidx.work.impl.WorkDatabaseVersions;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import java.io.Serializable;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class wl0 implements xl0, vi0, fc, xd0, u2 {
    public final Object E;

    public /* synthetic */ wl0(Object obj) {
        this.E = obj;
    }

    public void a() {
        View view = (View) this.E;
        if (view != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override // defpackage.u2
    public boolean b(View view) {
        int currentItem = ((ViewPager2) view).getCurrentItem() - 1;
        ViewPager2 viewPager2 = (ViewPager2) ((tb) this.E).d;
        if (viewPager2.V) {
            viewPager2.c(currentItem);
        }
        return true;
    }

    @Override // defpackage.xl0
    public void c(int i, Serializable serializable) {
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
                str = "";
                break;
            case 10:
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
        ((ProfileInstallReceiver) this.E).setResultCode(i);
    }

    @Override // defpackage.fc
    public void d() {
        ((nw0) this.E).a();
    }

    @Override // defpackage.xl0
    public void e() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // defpackage.xd0
    public boolean f(zd0 zd0Var, MenuItem menuItem) {
        ((Toolbar) this.E).getClass();
        return false;
    }

    public void g() {
        View viewFindViewById;
        View view = (View) this.E;
        if (view == null) {
            return;
        }
        if (view.isInEditMode() || view.onCheckIsTextEditor()) {
            view.requestFocus();
            viewFindViewById = view;
        } else {
            viewFindViewById = view.getRootView().findFocus();
        }
        if (viewFindViewById == null) {
            viewFindViewById = view.getRootView().findViewById(R.id.content);
        }
        if (viewFindViewById == null || !viewFindViewById.hasWindowFocus()) {
            return;
        }
        viewFindViewById.post(new ra(viewFindViewById, 1));
    }

    @Override // defpackage.vi0
    public s81 h(View view, s81 s81Var) {
        ScrimInsetsFrameLayout scrimInsetsFrameLayout = (ScrimInsetsFrameLayout) this.E;
        if (scrimInsetsFrameLayout.F == null) {
            scrimInsetsFrameLayout.F = new Rect();
        }
        scrimInsetsFrameLayout.F.set(s81Var.b(), s81Var.d(), s81Var.c(), s81Var.a());
        scrimInsetsFrameLayout.e(s81Var);
        p81 p81Var = s81Var.a;
        scrimInsetsFrameLayout.setWillNotDraw(p81Var.j().equals(m40.e) || scrimInsetsFrameLayout.E == null);
        WeakHashMap weakHashMap = f51.a;
        scrimInsetsFrameLayout.postInvalidateOnAnimation();
        return p81Var.c();
    }

    @Override // defpackage.xd0
    public void m(zd0 zd0Var) {
        Toolbar toolbar = (Toolbar) this.E;
        b bVar = toolbar.E.a0;
        if (bVar == null || !bVar.h()) {
            Iterator it = ((CopyOnWriteArrayList) toolbar.n0.G).iterator();
            if (it.hasNext()) {
                throw zn.i(it);
            }
        }
    }

    public wl0(ViewGroup viewGroup) {
        this.E = viewGroup.getOverlay();
    }
}
