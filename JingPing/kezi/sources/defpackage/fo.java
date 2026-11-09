package defpackage;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class fo extends LinearLayout {
    public final TextInputLayout b;
    public final FrameLayout c;
    public final CheckableImageButton d;
    public ColorStateList e;
    public PorterDuff.Mode f;
    public View.OnLongClickListener g;
    public final CheckableImageButton h;
    public final eo i;
    public int j;
    public final LinkedHashSet k;
    public ColorStateList l;
    public PorterDuff.Mode m;
    public int n;
    public ImageView.ScaleType o;
    public View.OnLongClickListener p;
    public CharSequence q;
    public final AppCompatTextView r;
    public boolean s;
    public EditText t;
    public final AccessibilityManager u;
    public h9 v;
    public final bo w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public fo(TextInputLayout textInputLayout, po poVar) {
        CharSequence text;
        super(textInputLayout.getContext());
        int i = 1;
        this.j = 0;
        this.k = new LinkedHashSet();
        this.w = new bo(this);
        co coVar = new co(this);
        this.u = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.b = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.c = frameLayout;
        frameLayout.setVisibility(8);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        CheckableImageButton checkableImageButtonA = a(this, layoutInflaterFrom, R$id.text_input_error_icon);
        this.d = checkableImageButtonA;
        CheckableImageButton checkableImageButtonA2 = a(frameLayout, layoutInflaterFrom, R$id.text_input_end_icon);
        this.h = checkableImageButtonA2;
        this.i = new eo(this, poVar);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.r = appCompatTextView;
        int i2 = R$styleable.TextInputLayout_errorIconTint;
        TypedArray typedArray = (TypedArray) poVar.d;
        if (typedArray.hasValue(i2)) {
            this.e = xi.r(getContext(), poVar, R$styleable.TextInputLayout_errorIconTint);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_errorIconTintMode)) {
            this.f = rp0.O(typedArray.getInt(R$styleable.TextInputLayout_errorIconTintMode, -1), null);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_errorIconDrawable)) {
            i(poVar.y(R$styleable.TextInputLayout_errorIconDrawable));
        }
        checkableImageButtonA.setContentDescription(getResources().getText(R$string.error_icon_content_description));
        WeakHashMap weakHashMap = xr0.a;
        checkableImageButtonA.setImportantForAccessibility(2);
        checkableImageButtonA.setClickable(false);
        checkableImageButtonA.setPressable(false);
        checkableImageButtonA.setFocusable(false);
        if (!typedArray.hasValue(R$styleable.TextInputLayout_passwordToggleEnabled)) {
            if (typedArray.hasValue(R$styleable.TextInputLayout_endIconTint)) {
                this.l = xi.r(getContext(), poVar, R$styleable.TextInputLayout_endIconTint);
            }
            if (typedArray.hasValue(R$styleable.TextInputLayout_endIconTintMode)) {
                this.m = rp0.O(typedArray.getInt(R$styleable.TextInputLayout_endIconTintMode, -1), null);
            }
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_endIconMode)) {
            g(typedArray.getInt(R$styleable.TextInputLayout_endIconMode, 0));
            if (typedArray.hasValue(R$styleable.TextInputLayout_endIconContentDescription) && checkableImageButtonA2.getContentDescription() != (text = typedArray.getText(R$styleable.TextInputLayout_endIconContentDescription))) {
                checkableImageButtonA2.setContentDescription(text);
            }
            checkableImageButtonA2.setCheckable(typedArray.getBoolean(R$styleable.TextInputLayout_endIconCheckable, true));
        } else if (typedArray.hasValue(R$styleable.TextInputLayout_passwordToggleEnabled)) {
            if (typedArray.hasValue(R$styleable.TextInputLayout_passwordToggleTint)) {
                this.l = xi.r(getContext(), poVar, R$styleable.TextInputLayout_passwordToggleTint);
            }
            if (typedArray.hasValue(R$styleable.TextInputLayout_passwordToggleTintMode)) {
                this.m = rp0.O(typedArray.getInt(R$styleable.TextInputLayout_passwordToggleTintMode, -1), null);
            }
            g(typedArray.getBoolean(R$styleable.TextInputLayout_passwordToggleEnabled, false) ? 1 : 0);
            CharSequence text2 = typedArray.getText(R$styleable.TextInputLayout_passwordToggleContentDescription);
            if (checkableImageButtonA2.getContentDescription() != text2) {
                checkableImageButtonA2.setContentDescription(text2);
            }
        }
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R$styleable.TextInputLayout_endIconMinSize, getResources().getDimensionPixelSize(R$dimen.mtrl_min_touch_target_size));
        if (dimensionPixelSize < 0) {
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        }
        if (dimensionPixelSize != this.n) {
            this.n = dimensionPixelSize;
            checkableImageButtonA2.setMinimumWidth(dimensionPixelSize);
            checkableImageButtonA2.setMinimumHeight(dimensionPixelSize);
            checkableImageButtonA.setMinimumWidth(dimensionPixelSize);
            checkableImageButtonA.setMinimumHeight(dimensionPixelSize);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_endIconScaleType)) {
            ImageView.ScaleType scaleTypeH = nv0.h(typedArray.getInt(R$styleable.TextInputLayout_endIconScaleType, -1));
            this.o = scaleTypeH;
            checkableImageButtonA2.setScaleType(scaleTypeH);
            checkableImageButtonA.setScaleType(scaleTypeH);
        }
        appCompatTextView.setVisibility(8);
        appCompatTextView.setId(R$id.textinput_suffix_text);
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
        appCompatTextView.setAccessibilityLiveRegion(1);
        appCompatTextView.setTextAppearance(typedArray.getResourceId(R$styleable.TextInputLayout_suffixTextAppearance, 0));
        if (typedArray.hasValue(R$styleable.TextInputLayout_suffixTextColor)) {
            appCompatTextView.setTextColor(poVar.w(R$styleable.TextInputLayout_suffixTextColor));
        }
        CharSequence text3 = typedArray.getText(R$styleable.TextInputLayout_suffixText);
        this.q = TextUtils.isEmpty(text3) ? null : text3;
        appCompatTextView.setText(text3);
        n();
        frameLayout.addView(checkableImageButtonA2);
        addView(appCompatTextView);
        addView(frameLayout);
        addView(checkableImageButtonA);
        textInputLayout.addOnEditTextAttachedListener(coVar);
        addOnAttachStateChangeListener(new wa(i, this));
    }

    public final CheckableImageButton a(ViewGroup viewGroup, LayoutInflater layoutInflater, int i) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(R$layout.design_text_input_end_icon, viewGroup, false);
        checkableImageButton.setId(i);
        if (xi.F(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginStart(0);
        }
        return checkableImageButton;
    }

    public void addOnEndIconChangedListener(mn0 mn0Var) {
        this.k.add(mn0Var);
    }

    public final go b() {
        go hiVar;
        int i = this.j;
        eo eoVar = this.i;
        SparseArray sparseArray = eoVar.a;
        go goVar = (go) sparseArray.get(i);
        if (goVar != null) {
            return goVar;
        }
        fo foVar = eoVar.b;
        if (i == -1) {
            hiVar = new hi(foVar, 0);
        } else if (i == 0) {
            hiVar = new hi(foVar, 1);
        } else if (i == 1) {
            hiVar = new nb0(foVar, eoVar.d);
        } else if (i == 2) {
            hiVar = new rd(foVar);
        } else {
            if (i != 3) {
                throw new IllegalArgumentException(hk.g(i, "Invalid end icon mode: "));
            }
            hiVar = new gm(foVar);
        }
        sparseArray.append(i, hiVar);
        return hiVar;
    }

    public final int c() {
        int marginStart;
        if (d() || e()) {
            CheckableImageButton checkableImageButton = this.h;
            marginStart = ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).getMarginStart() + checkableImageButton.getMeasuredWidth();
        } else {
            marginStart = 0;
        }
        WeakHashMap weakHashMap = xr0.a;
        return this.r.getPaddingEnd() + getPaddingEnd() + marginStart;
    }

    public final boolean d() {
        return this.c.getVisibility() == 0 && this.h.getVisibility() == 0;
    }

    public final boolean e() {
        return this.d.getVisibility() == 0;
    }

    public final void f(boolean z) {
        boolean z2;
        boolean zIsActivated;
        boolean z3;
        go goVarB = b();
        boolean zK = goVarB.k();
        CheckableImageButton checkableImageButton = this.h;
        boolean z4 = true;
        if (!zK || (z3 = checkableImageButton.e) == goVarB.l()) {
            z2 = false;
        } else {
            checkableImageButton.setChecked(!z3);
            z2 = true;
        }
        if (!(goVarB instanceof gm) || (zIsActivated = checkableImageButton.isActivated()) == goVarB.j()) {
            z4 = z2;
        } else {
            checkableImageButton.setActivated(!zIsActivated);
        }
        if (z || z4) {
            nv0.I(this.b, checkableImageButton, this.l);
        }
    }

    public final void g(int i) {
        if (this.j == i) {
            return;
        }
        go goVarB = b();
        h9 h9Var = this.v;
        AccessibilityManager accessibilityManager = this.u;
        if (h9Var != null && accessibilityManager != null) {
            accessibilityManager.removeTouchExplorationStateChangeListener(new c0(h9Var));
        }
        this.v = null;
        goVarB.s();
        this.j = i;
        Iterator it = this.k.iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        h(i != 0);
        go goVarB2 = b();
        int iD = this.i.c;
        if (iD == 0) {
            iD = goVarB2.d();
        }
        Drawable drawableY = iD != 0 ? rp0.y(getContext(), iD) : null;
        CheckableImageButton checkableImageButton = this.h;
        checkableImageButton.setImageDrawable(drawableY);
        TextInputLayout textInputLayout = this.b;
        if (drawableY != null) {
            nv0.e(textInputLayout, checkableImageButton, this.l, this.m);
            nv0.I(textInputLayout, checkableImageButton, this.l);
        }
        int iC = goVarB2.c();
        CharSequence text = iC != 0 ? getResources().getText(iC) : null;
        if (checkableImageButton.getContentDescription() != text) {
            checkableImageButton.setContentDescription(text);
        }
        checkableImageButton.setCheckable(goVarB2.k());
        if (!goVarB2.i(textInputLayout.getBoxBackgroundMode())) {
            throw new IllegalStateException("The current box background mode " + textInputLayout.getBoxBackgroundMode() + " is not supported by the end icon mode " + i);
        }
        goVarB2.r();
        h9 h9VarH = goVarB2.h();
        this.v = h9VarH;
        if (h9VarH != null && accessibilityManager != null) {
            WeakHashMap weakHashMap = xr0.a;
            if (isAttachedToWindow()) {
                accessibilityManager.addTouchExplorationStateChangeListener(new c0(this.v));
            }
        }
        setEndIconOnClickListener(goVarB2.f());
        EditText editText = this.t;
        if (editText != null) {
            goVarB2.m(editText);
            j(goVarB2);
        }
        nv0.e(textInputLayout, checkableImageButton, this.l, this.m);
        f(true);
    }

    public final void h(boolean z) {
        if (d() != z) {
            this.h.setVisibility(z ? 0 : 8);
            k();
            m();
            this.b.q();
        }
    }

    public final void i(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.d;
        checkableImageButton.setImageDrawable(drawable);
        l();
        nv0.e(this.b, checkableImageButton, this.e, this.f);
    }

    public final void j(go goVar) {
        if (this.t == null) {
            return;
        }
        if (goVar.e() != null) {
            this.t.setOnFocusChangeListener(goVar.e());
        }
        if (goVar.g() != null) {
            this.h.setOnFocusChangeListener(goVar.g());
        }
    }

    public final void k() {
        this.c.setVisibility((this.h.getVisibility() != 0 || e()) ? 8 : 0);
        setVisibility((d() || e() || ((this.q == null || this.s) ? '\b' : (char) 0) == 0) ? 0 : 8);
    }

    public final void l() {
        CheckableImageButton checkableImageButton = this.d;
        Drawable drawable = checkableImageButton.getDrawable();
        TextInputLayout textInputLayout = this.b;
        checkableImageButton.setVisibility((drawable != null && textInputLayout.k.q && textInputLayout.m()) ? 0 : 8);
        k();
        m();
        if (this.j != 0) {
            return;
        }
        textInputLayout.q();
    }

    public final void m() {
        int paddingEnd;
        TextInputLayout textInputLayout = this.b;
        if (textInputLayout.e == null) {
            return;
        }
        if (d() || e()) {
            paddingEnd = 0;
        } else {
            EditText editText = textInputLayout.e;
            WeakHashMap weakHashMap = xr0.a;
            paddingEnd = editText.getPaddingEnd();
        }
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R$dimen.material_input_text_to_prefix_suffix_padding);
        int paddingTop = textInputLayout.e.getPaddingTop();
        int paddingBottom = textInputLayout.e.getPaddingBottom();
        WeakHashMap weakHashMap2 = xr0.a;
        this.r.setPaddingRelative(dimensionPixelSize, paddingTop, paddingEnd, paddingBottom);
    }

    public final void n() {
        AppCompatTextView appCompatTextView = this.r;
        int visibility = appCompatTextView.getVisibility();
        int i = (this.q == null || this.s) ? 8 : 0;
        if (visibility != i) {
            b().p(i == 0);
        }
        k();
        appCompatTextView.setVisibility(i);
        this.b.q();
    }

    public void removeOnEndIconChangedListener(mn0 mn0Var) {
        this.k.remove(mn0Var);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.p;
        CheckableImageButton checkableImageButton = this.h;
        checkableImageButton.setOnClickListener(onClickListener);
        nv0.M(checkableImageButton, onLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.p = onLongClickListener;
        CheckableImageButton checkableImageButton = this.h;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        nv0.M(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.g;
        CheckableImageButton checkableImageButton = this.d;
        checkableImageButton.setOnClickListener(onClickListener);
        nv0.M(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.g = onLongClickListener;
        CheckableImageButton checkableImageButton = this.d;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        nv0.M(checkableImageButton, onLongClickListener);
    }
}
