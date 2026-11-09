package defpackage;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ax0 extends LinearLayout {
    public final TextInputLayout E;
    public final AppCompatTextView F;
    public CharSequence G;
    public final CheckableImageButton H;
    public ColorStateList I;
    public PorterDuff.Mode J;
    public int K;
    public ImageView.ScaleType L;
    public View.OnLongClickListener M;
    public boolean N;

    public ax0(TextInputLayout textInputLayout, ou ouVar) {
        CharSequence text;
        super(textInputLayout.getContext());
        this.E = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R$layout.design_text_input_start_icon, (ViewGroup) this, false);
        this.H = checkableImageButton;
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.F = appCompatTextView;
        if (la0.P(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginEnd(0);
        }
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i = R$styleable.TextInputLayout_startIconTint;
        TypedArray typedArray = (TypedArray) ouVar.G;
        if (typedArray.hasValue(i)) {
            this.I = la0.o(getContext(), ouVar, R$styleable.TextInputLayout_startIconTint);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_startIconTintMode)) {
            this.J = fa0.p0(typedArray.getInt(R$styleable.TextInputLayout_startIconTintMode, -1), null);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_startIconDrawable)) {
            b(ouVar.A(R$styleable.TextInputLayout_startIconDrawable));
            if (typedArray.hasValue(R$styleable.TextInputLayout_startIconContentDescription) && checkableImageButton.getContentDescription() != (text = typedArray.getText(R$styleable.TextInputLayout_startIconContentDescription))) {
                checkableImageButton.setContentDescription(text);
            }
            checkableImageButton.setCheckable(typedArray.getBoolean(R$styleable.TextInputLayout_startIconCheckable, true));
        }
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R$styleable.TextInputLayout_startIconMinSize, getResources().getDimensionPixelSize(R$dimen.mtrl_min_touch_target_size));
        if (dimensionPixelSize < 0) {
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if (dimensionPixelSize != this.K) {
            this.K = dimensionPixelSize;
            checkableImageButton.setMinimumWidth(dimensionPixelSize);
            checkableImageButton.setMinimumHeight(dimensionPixelSize);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_startIconScaleType)) {
            ImageView.ScaleType scaleTypeS = l21.s(typedArray.getInt(R$styleable.TextInputLayout_startIconScaleType, -1));
            this.L = scaleTypeS;
            checkableImageButton.setScaleType(scaleTypeS);
        }
        appCompatTextView.setVisibility(8);
        appCompatTextView.setId(R$id.textinput_prefix_text);
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        WeakHashMap weakHashMap = f51.a;
        appCompatTextView.setAccessibilityLiveRegion(1);
        appCompatTextView.setTextAppearance(typedArray.getResourceId(R$styleable.TextInputLayout_prefixTextAppearance, 0));
        if (typedArray.hasValue(R$styleable.TextInputLayout_prefixTextColor)) {
            appCompatTextView.setTextColor(ouVar.y(R$styleable.TextInputLayout_prefixTextColor));
        }
        CharSequence text2 = typedArray.getText(R$styleable.TextInputLayout_prefixText);
        this.G = TextUtils.isEmpty(text2) ? null : text2;
        appCompatTextView.setText(text2);
        e();
        addView(checkableImageButton);
        addView(appCompatTextView);
    }

    public final int a() {
        int marginEnd;
        CheckableImageButton checkableImageButton = this.H;
        if (checkableImageButton.getVisibility() == 0) {
            marginEnd = ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).getMarginEnd() + checkableImageButton.getMeasuredWidth();
        } else {
            marginEnd = 0;
        }
        WeakHashMap weakHashMap = f51.a;
        return this.F.getPaddingStart() + getPaddingStart() + marginEnd;
    }

    public final void b(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.H;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            ColorStateList colorStateList = this.I;
            PorterDuff.Mode mode = this.J;
            TextInputLayout textInputLayout = this.E;
            l21.l(textInputLayout, checkableImageButton, colorStateList, mode);
            c(true);
            l21.N0(textInputLayout, checkableImageButton, this.I);
            return;
        }
        c(false);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        if (checkableImageButton.getContentDescription() != null) {
            checkableImageButton.setContentDescription(null);
        }
    }

    public final void c(boolean z) {
        CheckableImageButton checkableImageButton = this.H;
        if ((checkableImageButton.getVisibility() == 0) != z) {
            checkableImageButton.setVisibility(z ? 0 : 8);
            d();
            e();
        }
    }

    public final void d() {
        int paddingStart;
        EditText editText = this.E.H;
        if (editText == null) {
            return;
        }
        if (this.H.getVisibility() == 0) {
            paddingStart = 0;
        } else {
            WeakHashMap weakHashMap = f51.a;
            paddingStart = editText.getPaddingStart();
        }
        int compoundPaddingTop = editText.getCompoundPaddingTop();
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R$dimen.material_input_text_to_prefix_suffix_padding);
        int compoundPaddingBottom = editText.getCompoundPaddingBottom();
        WeakHashMap weakHashMap2 = f51.a;
        this.F.setPaddingRelative(paddingStart, compoundPaddingTop, dimensionPixelSize, compoundPaddingBottom);
    }

    public final void e() {
        int i = (this.G == null || this.N) ? 8 : 0;
        setVisibility((this.H.getVisibility() == 0 || i == 0) ? 0 : 8);
        this.F.setVisibility(i);
        this.E.q();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        d();
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.M;
        CheckableImageButton checkableImageButton = this.H;
        checkableImageButton.setOnClickListener(onClickListener);
        l21.P0(checkableImageButton, onLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.M = onLongClickListener;
        CheckableImageButton checkableImageButton = this.H;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l21.P0(checkableImageButton, onLongClickListener);
    }
}
