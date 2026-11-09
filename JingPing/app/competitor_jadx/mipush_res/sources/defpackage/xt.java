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
public final class xt extends LinearLayout {
    public final TextInputLayout E;
    public final FrameLayout F;
    public final CheckableImageButton G;
    public ColorStateList H;
    public PorterDuff.Mode I;
    public View.OnLongClickListener J;
    public final CheckableImageButton K;
    public final wt L;
    public int M;
    public final LinkedHashSet N;
    public ColorStateList O;
    public PorterDuff.Mode P;
    public int Q;
    public ImageView.ScaleType R;
    public View.OnLongClickListener S;
    public CharSequence T;
    public final AppCompatTextView U;
    public boolean V;
    public EditText W;
    public final AccessibilityManager a0;
    public fb b0;
    public final ut c0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public xt(TextInputLayout textInputLayout, ou ouVar) {
        CharSequence text;
        super(textInputLayout.getContext());
        int i = 1;
        this.M = 0;
        this.N = new LinkedHashSet();
        this.c0 = new ut(this);
        vt vtVar = new vt(this);
        this.a0 = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.E = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.F = frameLayout;
        frameLayout.setVisibility(8);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        CheckableImageButton checkableImageButtonA = a(this, layoutInflaterFrom, R$id.text_input_error_icon);
        this.G = checkableImageButtonA;
        CheckableImageButton checkableImageButtonA2 = a(frameLayout, layoutInflaterFrom, R$id.text_input_end_icon);
        this.K = checkableImageButtonA2;
        this.L = new wt(this, ouVar);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.U = appCompatTextView;
        int i2 = R$styleable.TextInputLayout_errorIconTint;
        TypedArray typedArray = (TypedArray) ouVar.G;
        if (typedArray.hasValue(i2)) {
            this.H = la0.o(getContext(), ouVar, R$styleable.TextInputLayout_errorIconTint);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_errorIconTintMode)) {
            this.I = fa0.p0(typedArray.getInt(R$styleable.TextInputLayout_errorIconTintMode, -1), null);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_errorIconDrawable)) {
            i(ouVar.A(R$styleable.TextInputLayout_errorIconDrawable));
        }
        checkableImageButtonA.setContentDescription(getResources().getText(R$string.error_icon_content_description));
        WeakHashMap weakHashMap = f51.a;
        checkableImageButtonA.setImportantForAccessibility(2);
        checkableImageButtonA.setClickable(false);
        checkableImageButtonA.setPressable(false);
        checkableImageButtonA.setFocusable(false);
        if (!typedArray.hasValue(R$styleable.TextInputLayout_passwordToggleEnabled)) {
            if (typedArray.hasValue(R$styleable.TextInputLayout_endIconTint)) {
                this.O = la0.o(getContext(), ouVar, R$styleable.TextInputLayout_endIconTint);
            }
            if (typedArray.hasValue(R$styleable.TextInputLayout_endIconTintMode)) {
                this.P = fa0.p0(typedArray.getInt(R$styleable.TextInputLayout_endIconTintMode, -1), null);
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
                this.O = la0.o(getContext(), ouVar, R$styleable.TextInputLayout_passwordToggleTint);
            }
            if (typedArray.hasValue(R$styleable.TextInputLayout_passwordToggleTintMode)) {
                this.P = fa0.p0(typedArray.getInt(R$styleable.TextInputLayout_passwordToggleTintMode, -1), null);
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
        if (dimensionPixelSize != this.Q) {
            this.Q = dimensionPixelSize;
            checkableImageButtonA2.setMinimumWidth(dimensionPixelSize);
            checkableImageButtonA2.setMinimumHeight(dimensionPixelSize);
            checkableImageButtonA.setMinimumWidth(dimensionPixelSize);
            checkableImageButtonA.setMinimumHeight(dimensionPixelSize);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_endIconScaleType)) {
            ImageView.ScaleType scaleTypeS = l21.s(typedArray.getInt(R$styleable.TextInputLayout_endIconScaleType, -1));
            this.R = scaleTypeS;
            checkableImageButtonA2.setScaleType(scaleTypeS);
            checkableImageButtonA.setScaleType(scaleTypeS);
        }
        appCompatTextView.setVisibility(8);
        appCompatTextView.setId(R$id.textinput_suffix_text);
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
        appCompatTextView.setAccessibilityLiveRegion(1);
        appCompatTextView.setTextAppearance(typedArray.getResourceId(R$styleable.TextInputLayout_suffixTextAppearance, 0));
        if (typedArray.hasValue(R$styleable.TextInputLayout_suffixTextColor)) {
            appCompatTextView.setTextColor(ouVar.y(R$styleable.TextInputLayout_suffixTextColor));
        }
        CharSequence text3 = typedArray.getText(R$styleable.TextInputLayout_suffixText);
        this.T = TextUtils.isEmpty(text3) ? null : text3;
        appCompatTextView.setText(text3);
        n();
        frameLayout.addView(checkableImageButtonA2);
        addView(appCompatTextView);
        addView(frameLayout);
        addView(checkableImageButtonA);
        textInputLayout.addOnEditTextAttachedListener(vtVar);
        addOnAttachStateChangeListener(new rc(i, this));
    }

    public final CheckableImageButton a(ViewGroup viewGroup, LayoutInflater layoutInflater, int i) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(R$layout.design_text_input_end_icon, viewGroup, false);
        checkableImageButton.setId(i);
        if (la0.P(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginStart(0);
        }
        return checkableImageButton;
    }

    public void addOnEndIconChangedListener(a01 a01Var) {
        this.N.add(a01Var);
    }

    public final yt b() {
        yt ekVar;
        int i = this.M;
        wt wtVar = this.L;
        SparseArray sparseArray = wtVar.a;
        yt ytVar = (yt) sparseArray.get(i);
        if (ytVar != null) {
            return ytVar;
        }
        xt xtVar = wtVar.b;
        if (i == -1) {
            ekVar = new ek(xtVar, 0);
        } else if (i == 0) {
            ekVar = new ek(xtVar, 1);
        } else if (i == 1) {
            ekVar = new kk0(xtVar, wtVar.d);
        } else if (i == 2) {
            ekVar = new mf(xtVar);
        } else {
            if (i != 3) {
                throw new IllegalArgumentException(zn.j(i, "Invalid end icon mode: "));
            }
            ekVar = new jq(xtVar);
        }
        sparseArray.append(i, ekVar);
        return ekVar;
    }

    public final int c() {
        int marginStart;
        if (d() || e()) {
            CheckableImageButton checkableImageButton = this.K;
            marginStart = ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).getMarginStart() + checkableImageButton.getMeasuredWidth();
        } else {
            marginStart = 0;
        }
        WeakHashMap weakHashMap = f51.a;
        return this.U.getPaddingEnd() + getPaddingEnd() + marginStart;
    }

    public final boolean d() {
        return this.F.getVisibility() == 0 && this.K.getVisibility() == 0;
    }

    public final boolean e() {
        return this.G.getVisibility() == 0;
    }

    public final void f(boolean z) {
        boolean z2;
        boolean zIsActivated;
        boolean z3;
        yt ytVarB = b();
        boolean zK = ytVarB.k();
        CheckableImageButton checkableImageButton = this.K;
        boolean z4 = true;
        if (!zK || (z3 = checkableImageButton.H) == ytVarB.l()) {
            z2 = false;
        } else {
            checkableImageButton.setChecked(!z3);
            z2 = true;
        }
        if (!(ytVarB instanceof jq) || (zIsActivated = checkableImageButton.isActivated()) == ytVarB.j()) {
            z4 = z2;
        } else {
            checkableImageButton.setActivated(!zIsActivated);
        }
        if (z || z4) {
            l21.N0(this.E, checkableImageButton, this.O);
        }
    }

    public final void g(int i) {
        if (this.M == i) {
            return;
        }
        yt ytVarB = b();
        fb fbVar = this.b0;
        AccessibilityManager accessibilityManager = this.a0;
        if (fbVar != null && accessibilityManager != null) {
            accessibilityManager.removeTouchExplorationStateChangeListener(new z1(fbVar));
        }
        this.b0 = null;
        ytVarB.s();
        this.M = i;
        Iterator it = this.N.iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        h(i != 0);
        yt ytVarB2 = b();
        int iD = this.L.c;
        if (iD == 0) {
            iD = ytVarB2.d();
        }
        Drawable drawableZ = iD != 0 ? qm.z(getContext(), iD) : null;
        CheckableImageButton checkableImageButton = this.K;
        checkableImageButton.setImageDrawable(drawableZ);
        TextInputLayout textInputLayout = this.E;
        if (drawableZ != null) {
            l21.l(textInputLayout, checkableImageButton, this.O, this.P);
            l21.N0(textInputLayout, checkableImageButton, this.O);
        }
        int iC = ytVarB2.c();
        CharSequence text = iC != 0 ? getResources().getText(iC) : null;
        if (checkableImageButton.getContentDescription() != text) {
            checkableImageButton.setContentDescription(text);
        }
        checkableImageButton.setCheckable(ytVarB2.k());
        if (!ytVarB2.i(textInputLayout.getBoxBackgroundMode())) {
            throw new IllegalStateException("The current box background mode " + textInputLayout.getBoxBackgroundMode() + " is not supported by the end icon mode " + i);
        }
        ytVarB2.r();
        fb fbVarH = ytVarB2.h();
        this.b0 = fbVarH;
        if (fbVarH != null && accessibilityManager != null) {
            WeakHashMap weakHashMap = f51.a;
            if (isAttachedToWindow()) {
                accessibilityManager.addTouchExplorationStateChangeListener(new z1(this.b0));
            }
        }
        setEndIconOnClickListener(ytVarB2.f());
        EditText editText = this.W;
        if (editText != null) {
            ytVarB2.m(editText);
            j(ytVarB2);
        }
        l21.l(textInputLayout, checkableImageButton, this.O, this.P);
        f(true);
    }

    public final void h(boolean z) {
        if (d() != z) {
            this.K.setVisibility(z ? 0 : 8);
            k();
            m();
            this.E.q();
        }
    }

    public final void i(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.G;
        checkableImageButton.setImageDrawable(drawable);
        l();
        l21.l(this.E, checkableImageButton, this.H, this.I);
    }

    public final void j(yt ytVar) {
        if (this.W == null) {
            return;
        }
        if (ytVar.e() != null) {
            this.W.setOnFocusChangeListener(ytVar.e());
        }
        if (ytVar.g() != null) {
            this.K.setOnFocusChangeListener(ytVar.g());
        }
    }

    public final void k() {
        this.F.setVisibility((this.K.getVisibility() != 0 || e()) ? 8 : 0);
        setVisibility((d() || e() || ((this.T == null || this.V) ? '\b' : (char) 0) == 0) ? 0 : 8);
    }

    public final void l() {
        CheckableImageButton checkableImageButton = this.G;
        Drawable drawable = checkableImageButton.getDrawable();
        TextInputLayout textInputLayout = this.E;
        checkableImageButton.setVisibility((drawable != null && textInputLayout.N.q && textInputLayout.m()) ? 0 : 8);
        k();
        m();
        if (this.M != 0) {
            return;
        }
        textInputLayout.q();
    }

    public final void m() {
        int paddingEnd;
        TextInputLayout textInputLayout = this.E;
        if (textInputLayout.H == null) {
            return;
        }
        if (d() || e()) {
            paddingEnd = 0;
        } else {
            EditText editText = textInputLayout.H;
            WeakHashMap weakHashMap = f51.a;
            paddingEnd = editText.getPaddingEnd();
        }
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R$dimen.material_input_text_to_prefix_suffix_padding);
        int paddingTop = textInputLayout.H.getPaddingTop();
        int paddingBottom = textInputLayout.H.getPaddingBottom();
        WeakHashMap weakHashMap2 = f51.a;
        this.U.setPaddingRelative(dimensionPixelSize, paddingTop, paddingEnd, paddingBottom);
    }

    public final void n() {
        AppCompatTextView appCompatTextView = this.U;
        int visibility = appCompatTextView.getVisibility();
        int i = (this.T == null || this.V) ? 8 : 0;
        if (visibility != i) {
            b().p(i == 0);
        }
        k();
        appCompatTextView.setVisibility(i);
        this.E.q();
    }

    public void removeOnEndIconChangedListener(a01 a01Var) {
        this.N.remove(a01Var);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.S;
        CheckableImageButton checkableImageButton = this.K;
        checkableImageButton.setOnClickListener(onClickListener);
        l21.P0(checkableImageButton, onLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.S = onLongClickListener;
        CheckableImageButton checkableImageButton = this.K;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l21.P0(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.J;
        CheckableImageButton checkableImageButton = this.G;
        checkableImageButton.setOnClickListener(onClickListener);
        l21.P0(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.J = onLongClickListener;
        CheckableImageButton checkableImageButton = this.G;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l21.P0(checkableImageButton, onLongClickListener);
    }
}
