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
public final class jq extends yt {
    public final int e;
    public final int f;
    public final TimeInterpolator g;
    public AutoCompleteTextView h;
    public final Cif i;
    public final jf j;
    public final fb k;
    public boolean l;
    public boolean m;
    public boolean n;
    public long o;
    public AccessibilityManager p;
    public ValueAnimator q;
    public ValueAnimator r;

    public jq(xt xtVar) {
        super(xtVar);
        this.i = new Cif(1, this);
        this.j = new jf(2, this);
        this.k = new fb(1, this);
        this.o = Long.MAX_VALUE;
        this.f = s90.U(xtVar.getContext(), R$attr.motionDurationShort3, 67);
        this.e = s90.U(xtVar.getContext(), R$attr.motionDurationShort3, 50);
        this.g = s90.V(xtVar.getContext(), R$attr.motionEasingLinearInterpolator, y4.a);
    }

    @Override // defpackage.yt
    public final void a() {
        if (this.p.isTouchExplorationEnabled() && j31.z(this.h) && !this.d.hasFocus()) {
            this.h.dismissDropDown();
        }
        this.h.post(new e(13, this));
    }

    @Override // defpackage.yt
    public final int c() {
        return R$string.exposed_dropdown_menu_content_description;
    }

    @Override // defpackage.yt
    public final int d() {
        return R$drawable.mtrl_dropdown_arrow;
    }

    @Override // defpackage.yt
    public final View.OnFocusChangeListener e() {
        return this.j;
    }

    @Override // defpackage.yt
    public final View.OnClickListener f() {
        return this.i;
    }

    @Override // defpackage.yt
    public final fb h() {
        return this.k;
    }

    @Override // defpackage.yt
    public final boolean i(int i) {
        return i != 0;
    }

    @Override // defpackage.yt
    public final boolean j() {
        return this.l;
    }

    @Override // defpackage.yt
    public final boolean l() {
        return this.n;
    }

    @Override // defpackage.yt
    public final void m(EditText editText) {
        if (!(editText instanceof AutoCompleteTextView)) {
            throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
        }
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
        this.h = autoCompleteTextView;
        autoCompleteTextView.setOnTouchListener(new hq(0, this));
        this.h.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() { // from class: iq
            @Override // android.widget.AutoCompleteTextView.OnDismissListener
            public final void onDismiss() {
                jq jqVar = this.a;
                jqVar.m = true;
                jqVar.o = System.currentTimeMillis();
                jqVar.t(false);
            }
        });
        this.h.setThreshold(0);
        TextInputLayout textInputLayout = this.a;
        textInputLayout.setErrorIconDrawable((Drawable) null);
        if (!j31.z(editText) && this.p.isTouchExplorationEnabled()) {
            WeakHashMap weakHashMap = f51.a;
            this.d.setImportantForAccessibility(2);
        }
        textInputLayout.setEndIconVisible(true);
    }

    @Override // defpackage.yt
    public final void n(j2 j2Var) {
        if (!j31.z(this.h)) {
            j2Var.i(Spinner.class.getName());
        }
        if (Build.VERSION.SDK_INT >= 26 ? j2Var.a.isShowingHintText() : j2Var.e(4)) {
            j2Var.k(null);
        }
    }

    @Override // defpackage.yt
    public final void o(AccessibilityEvent accessibilityEvent) {
        if (!this.p.isEnabled() || j31.z(this.h)) {
            return;
        }
        boolean z = (accessibilityEvent.getEventType() == 32768 || accessibilityEvent.getEventType() == 8) && this.n && !this.h.isPopupShowing();
        if (accessibilityEvent.getEventType() == 1 || z) {
            u();
            this.m = true;
            this.o = System.currentTimeMillis();
        }
    }

    @Override // defpackage.yt
    public final void r() {
        int i = 1;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        TimeInterpolator timeInterpolator = this.g;
        valueAnimatorOfFloat.setInterpolator(timeInterpolator);
        valueAnimatorOfFloat.setDuration(this.f);
        valueAnimatorOfFloat.addUpdateListener(new wp(i, this));
        this.r = valueAnimatorOfFloat;
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat2.setInterpolator(timeInterpolator);
        valueAnimatorOfFloat2.setDuration(this.e);
        valueAnimatorOfFloat2.addUpdateListener(new wp(i, this));
        this.q = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.addListener(new z2(3, this));
        this.p = (AccessibilityManager) this.c.getSystemService("accessibility");
    }

    @Override // defpackage.yt
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
