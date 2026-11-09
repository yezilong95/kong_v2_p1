package defpackage;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class yt {
    public final TextInputLayout a;
    public final xt b;
    public final Context c;
    public final CheckableImageButton d;

    public yt(xt xtVar) {
        this.a = xtVar.E;
        this.b = xtVar;
        this.c = xtVar.getContext();
        this.d = xtVar.K;
    }

    public int c() {
        return 0;
    }

    public int d() {
        return 0;
    }

    public View.OnFocusChangeListener e() {
        return null;
    }

    public View.OnClickListener f() {
        return null;
    }

    public View.OnFocusChangeListener g() {
        return null;
    }

    public fb h() {
        return null;
    }

    public boolean i(int i) {
        return true;
    }

    public boolean j() {
        return false;
    }

    public boolean k() {
        return this instanceof jq;
    }

    public boolean l() {
        return false;
    }

    public final void q() {
        this.b.f(false);
    }

    public void a() {
    }

    public void b() {
    }

    public void r() {
    }

    public void s() {
    }

    public void m(EditText editText) {
    }

    public void n(j2 j2Var) {
    }

    public void o(AccessibilityEvent accessibilityEvent) {
    }

    public void p(boolean z) {
    }
}
