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
public final class mk0 extends LinearLayout {
    public final TextInputLayout b;
    public final AppCompatTextView c;
    public CharSequence d;
    public final CheckableImageButton e;
    public ColorStateList f;
    public PorterDuff.Mode g;
    public int h;
    public ImageView.ScaleType i;
    public View.OnLongClickListener j;
    public boolean k;

    public mk0(TextInputLayout textInputLayout, po poVar) {
        CharSequence text;
        super(textInputLayout.getContext());
        this.b = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R$layout.design_text_input_start_icon, (ViewGroup) this, false);
        this.e = checkableImageButton;
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.c = appCompatTextView;
        if (xi.F(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginEnd(0);
        }
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i = R$styleable.TextInputLayout_startIconTint;
        TypedArray typedArray = (TypedArray) poVar.d;
        if (typedArray.hasValue(i)) {
            this.f = xi.r(getContext(), poVar, R$styleable.TextInputLayout_startIconTint);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_startIconTintMode)) {
            this.g = rp0.O(typedArray.getInt(R$styleable.TextInputLayout_startIconTintMode, -1), null);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_startIconDrawable)) {
            b(poVar.y(R$styleable.TextInputLayout_startIconDrawable));
            if (typedArray.hasValue(R$styleable.TextInputLayout_startIconContentDescription) && checkableImageButton.getContentDescription() != (text = typedArray.getText(R$styleable.TextInputLayout_startIconContentDescription))) {
                checkableImageButton.setContentDescription(text);
            }
            checkableImageButton.setCheckable(typedArray.getBoolean(R$styleable.TextInputLayout_startIconCheckable, true));
        }
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R$styleable.TextInputLayout_startIconMinSize, getResources().getDimensionPixelSize(R$dimen.mtrl_min_touch_target_size));
        if (dimensionPixelSize < 0) {
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if (dimensionPixelSize != this.h) {
            this.h = dimensionPixelSize;
            checkableImageButton.setMinimumWidth(dimensionPixelSize);
            checkableImageButton.setMinimumHeight(dimensionPixelSize);
        }
        if (typedArray.hasValue(R$styleable.TextInputLayout_startIconScaleType)) {
            ImageView.ScaleType scaleTypeH = nv0.h(typedArray.getInt(R$styleable.TextInputLayout_startIconScaleType, -1));
            this.i = scaleTypeH;
            checkableImageButton.setScaleType(scaleTypeH);
        }
        appCompatTextView.setVisibility(8);
        appCompatTextView.setId(R$id.textinput_prefix_text);
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        WeakHashMap weakHashMap = xr0.a;
        appCompatTextView.setAccessibilityLiveRegion(1);
        appCompatTextView.setTextAppearance(typedArray.getResourceId(R$styleable.TextInputLayout_prefixTextAppearance, 0));
        if (typedArray.hasValue(R$styleable.TextInputLayout_prefixTextColor)) {
            appCompatTextView.setTextColor(poVar.w(R$styleable.TextInputLayout_prefixTextColor));
        }
        CharSequence text2 = typedArray.getText(R$styleable.TextInputLayout_prefixText);
        this.d = TextUtils.isEmpty(text2) ? null : text2;
        appCompatTextView.setText(text2);
        e();
        addView(checkableImageButton);
        addView(appCompatTextView);
    }

    public final int a() {
        int marginEnd;
        CheckableImageButton checkableImageButton = this.e;
        if (checkableImageButton.getVisibility() == 0) {
            marginEnd = ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).getMarginEnd() + checkableImageButton.getMeasuredWidth();
        } else {
            marginEnd = 0;
        }
        WeakHashMap weakHashMap = xr0.a;
        return this.c.getPaddingStart() + getPaddingStart() + marginEnd;
    }

    public final void b(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.e;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            ColorStateList colorStateList = this.f;
            PorterDuff.Mode mode = this.g;
            TextInputLayout textInputLayout = this.b;
            nv0.e(textInputLayout, checkableImageButton, colorStateList, mode);
            c(true);
            nv0.I(textInputLayout, checkableImageButton, this.f);
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
        CheckableImageButton checkableImageButton = this.e;
        if ((checkableImageButton.getVisibility() == 0) != z) {
            checkableImageButton.setVisibility(z ? 0 : 8);
            d();
            e();
        }
    }

    public final void d() {
        int paddingStart;
        EditText editText = this.b.e;
        if (editText == null) {
            return;
        }
        if (this.e.getVisibility() == 0) {
            paddingStart = 0;
        } else {
            WeakHashMap weakHashMap = xr0.a;
            paddingStart = editText.getPaddingStart();
        }
        int compoundPaddingTop = editText.getCompoundPaddingTop();
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R$dimen.material_input_text_to_prefix_suffix_padding);
        int compoundPaddingBottom = editText.getCompoundPaddingBottom();
        WeakHashMap weakHashMap2 = xr0.a;
        this.c.setPaddingRelative(paddingStart, compoundPaddingTop, dimensionPixelSize, compoundPaddingBottom);
    }

    public final void e() {
        int i = (this.d == null || this.k) ? 8 : 0;
        setVisibility((this.e.getVisibility() == 0 || i == 0) ? 0 : 8);
        this.c.setVisibility(i);
        this.b.q();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        d();
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.j;
        CheckableImageButton checkableImageButton = this.e;
        checkableImageButton.setOnClickListener(onClickListener);
        nv0.M(checkableImageButton, onLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.j = onLongClickListener;
        CheckableImageButton checkableImageButton = this.e;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        nv0.M(checkableImageButton, onLongClickListener);
    }
}
