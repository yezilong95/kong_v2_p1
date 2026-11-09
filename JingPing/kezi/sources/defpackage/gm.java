package defpackage;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.android.material.R$attr;
import com.google.android.material.R$drawable;
import com.google.android.material.R$string;
import com.google.android.material.textfield.TextInputLayout;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class gm extends go {
    public final int e;
    public final int f;
    public final TimeInterpolator g;
    public AutoCompleteTextView h;
    public final nd i;
    public final od j;
    public final h9 k;
    public boolean l;
    public boolean m;
    public boolean n;
    public long o;
    public AccessibilityManager p;
    public ValueAnimator q;
    public ValueAnimator r;

    public gm(fo foVar) {
        super(foVar);
        this.i = new nd(1, this);
        this.j = new od(2, this);
        this.k = new h9(1, this);
        this.o = Long.MAX_VALUE;
        this.f = k9.E(foVar.getContext(), R$attr.motionDurationShort3, 67);
        this.e = k9.E(foVar.getContext(), R$attr.motionDurationShort3, 50);
        this.g = k9.F(foVar.getContext(), R$attr.motionEasingLinearInterpolator, t3.a);
    }

    @Override // defpackage.go
    public final void a() {
        if (this.p.isTouchExplorationEnabled() && oy.F(this.h) && !this.d.hasFocus()) {
            this.h.dismissDropDown();
        }
        this.h.post(new z1(7, this));
    }

    @Override // defpackage.go
    public final int c() {
        return R$string.exposed_dropdown_menu_content_description;
    }

    @Override // defpackage.go
    public final int d() {
        return R$drawable.mtrl_dropdown_arrow;
    }

    @Override // defpackage.go
    public final View.OnFocusChangeListener e() {
        return this.j;
    }

    @Override // defpackage.go
    public final View.OnClickListener f() {
        return this.i;
    }

    @Override // defpackage.go
    public final h9 h() {
        return this.k;
    }

    @Override // defpackage.go
    public final boolean i(int i) {
        return i != 0;
    }

    @Override // defpackage.go
    public final boolean j() {
        return this.l;
    }

    @Override // defpackage.go
    public final boolean l() {
        return this.n;
    }

    @Override // defpackage.go
    public final void m(EditText editText) {
        if (!(editText instanceof AutoCompleteTextView)) {
            throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
        }
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
        this.h = autoCompleteTextView;
        autoCompleteTextView.setOnTouchListener(new em(0, this));
        this.h.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() { // from class: fm
            @Override // android.widget.AutoCompleteTextView.OnDismissListener
            public final void onDismiss() {
                gm gmVar = this.a;
                gmVar.m = true;
                gmVar.o = System.currentTimeMillis();
                gmVar.t(false);
            }
        });
        this.h.setThreshold(0);
        TextInputLayout textInputLayout = this.a;
        textInputLayout.setErrorIconDrawable((Drawable) null);
        if (!oy.F(editText) && this.p.isTouchExplorationEnabled()) {
            WeakHashMap weakHashMap = xr0.a;
            this.d.setImportantForAccessibility(2);
        }
        textInputLayout.setEndIconVisible(true);
    }

    @Override // defpackage.go
    public final void n(o0 o0Var) {
        if (!oy.F(this.h)) {
            o0Var.i(Spinner.class.getName());
        }
        if (Build.VERSION.SDK_INT >= 26 ? o0Var.a.isShowingHintText() : o0Var.e(4)) {
            o0Var.k(null);
        }
    }

    @Override // defpackage.go
    public final void o(AccessibilityEvent accessibilityEvent) {
        if (!this.p.isEnabled() || oy.F(this.h)) {
            return;
        }
        boolean z = accessibilityEvent.getEventType() == 32768 && this.n && !this.h.isPopupShowing();
        if (accessibilityEvent.getEventType() == 1 || z) {
            u();
            this.m = true;
            this.o = System.currentTimeMillis();
        }
    }

    @Override // defpackage.go
    public final void r() {
        int i = 1;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        TimeInterpolator timeInterpolator = this.g;
        valueAnimatorOfFloat.setInterpolator(timeInterpolator);
        valueAnimatorOfFloat.setDuration(this.f);
        valueAnimatorOfFloat.addUpdateListener(new tl(i, this));
        this.r = valueAnimatorOfFloat;
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat2.setInterpolator(timeInterpolator);
        valueAnimatorOfFloat2.setDuration(this.e);
        valueAnimatorOfFloat2.addUpdateListener(new tl(i, this));
        this.q = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.addListener(new d1(3, this));
        this.p = (AccessibilityManager) this.c.getSystemService("accessibility");
    }

    @Override // defpackage.go
    public final void s() {
        AutoCompleteTextView autoCompleteTextView = this.h;
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setOnTouchListener(null);
            this.h.setOnDismissListener(null);
        }
    }

    public final void t(boolean z) {
        if (this.n != z) {
            this.n = z;
            this.r.cancel();
            this.q.start();
        }
    }

    public final void u() {
        if (this.h == null) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.o;
        if (jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300) {
            this.m = false;
        }
        if (this.m) {
            this.m = false;
            return;
        }
        t(!this.n);
        if (!this.n) {
            this.h.dismissDropDown();
        } else {
            this.h.requestFocus();
            this.h.showDropDown();
        }
    }
}
