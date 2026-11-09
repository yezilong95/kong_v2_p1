package defpackage;

import android.R;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class gv implements ev {
    public static Class c;
    public static boolean d;
    public static Method e;
    public static boolean f;
    public static Method g;
    public static boolean h;
    public final View b;

    public static void b() {
        if (d) {
            return;
        }
        try {
            c = Class.forName("android.view.GhostView");
        } catch (ClassNotFoundException e2) {
            Log.i("GhostViewApi21", "Failed to retrieve GhostView class", e2);
        }
        d = true;
    }

    public void c() {
        View view = this.b;
        if (view != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void d() {
        View viewFindViewById;
        View view = this.b;
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
        viewFindViewById.post(new s8(viewFindViewById, 1));
    }

    @Override // defpackage.ev
    public void setVisibility(int i) {
        this.b.setVisibility(i);
    }

    @Override // defpackage.ev
    public void a(ViewGroup viewGroup, View view) {
    }
}
