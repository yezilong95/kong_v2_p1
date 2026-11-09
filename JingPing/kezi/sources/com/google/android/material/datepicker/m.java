package com.google.android.material.datepicker;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.internal.CheckableImageButton;
import defpackage.ev0;
import defpackage.fq0;
import defpackage.gx;
import defpackage.hy;
import defpackage.je;
import defpackage.k9;
import defpackage.lr0;
import defpackage.nd;
import defpackage.nv0;
import defpackage.p50;
import defpackage.q50;
import defpackage.qb0;
import defpackage.rp0;
import defpackage.s7;
import defpackage.u50;
import defpackage.uk;
import defpackage.x50;
import defpackage.xr0;
import defpackage.yt0;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class m<S> extends uk {
    public TextView A0;
    public CheckableImageButton B0;
    public u50 C0;
    public Button D0;
    public boolean E0;
    public CharSequence F0;
    public CharSequence G0;
    public final LinkedHashSet h0 = new LinkedHashSet();
    public final LinkedHashSet i0 = new LinkedHashSet();
    public final LinkedHashSet j0 = new LinkedHashSet();
    public final LinkedHashSet k0 = new LinkedHashSet();
    public int l0;
    public DateSelector m0;
    public qb0 n0;
    public CalendarConstraints o0;
    public DayViewDecorator p0;
    public l q0;
    public int r0;
    public CharSequence s0;
    public boolean t0;
    public int u0;
    public int v0;
    public CharSequence w0;
    public int x0;
    public CharSequence y0;
    public TextView z0;

    public static int F(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_content_padding);
        Month month = new Month(fq0.f());
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.mtrl_calendar_day_width);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_month_horizontal_padding);
        int i = month.e;
        return ((i - 1) * dimensionPixelOffset2) + (dimensionPixelSize * i) + (dimensionPixelOffset * 2);
    }

    public static boolean G(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(nv0.L(context, R$attr.materialCalendarStyle, l.class.getCanonicalName()).data, new int[]{i});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }

    @Override // defpackage.uk
    public final Dialog D() {
        Context contextY = y();
        Context contextY2 = y();
        int iD = this.l0;
        if (iD == 0) {
            iD = E().d(contextY2);
        }
        Dialog dialog = new Dialog(contextY, iD);
        Context context = dialog.getContext();
        this.t0 = G(context, R.attr.windowFullscreen);
        this.C0 = new u50(context, null, R$attr.materialCalendarStyle, R$style.Widget_MaterialComponents_MaterialCalendar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.MaterialCalendar, R$attr.materialCalendarStyle, R$style.Widget_MaterialComponents_MaterialCalendar);
        int color = typedArrayObtainStyledAttributes.getColor(R$styleable.MaterialCalendar_backgroundTint, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.C0.k(context);
        this.C0.m(ColorStateList.valueOf(color));
        u50 u50Var = this.C0;
        View decorView = dialog.getWindow().getDecorView();
        WeakHashMap weakHashMap = xr0.a;
        u50Var.l(lr0.i(decorView));
        return dialog;
    }

    public final DateSelector E() {
        if (this.m0 == null) {
            this.m0 = (DateSelector) this.g.getParcelable("DATE_SELECTOR_KEY");
        }
        return this.m0;
    }

    public final void H() {
        Context contextY = y();
        int iD = this.l0;
        if (iD == 0) {
            iD = E().d(contextY);
        }
        DateSelector dateSelectorE = E();
        CalendarConstraints calendarConstraints = this.o0;
        DayViewDecorator dayViewDecorator = this.p0;
        l lVar = new l();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", iD);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelectorE);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", dayViewDecorator);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.e);
        lVar.B(bundle);
        this.q0 = lVar;
        qb0 qb0Var = lVar;
        if (this.u0 == 1) {
            DateSelector dateSelectorE2 = E();
            CalendarConstraints calendarConstraints2 = this.o0;
            x50 x50Var = new x50();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("THEME_RES_ID_KEY", iD);
            bundle2.putParcelable("DATE_SELECTOR_KEY", dateSelectorE2);
            bundle2.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints2);
            x50Var.B(bundle2);
            qb0Var = x50Var;
        }
        this.n0 = qb0Var;
        this.z0.setText((this.u0 == 1 && y().getResources().getConfiguration().orientation == 2) ? this.G0 : this.F0);
        String strC = E().c(g());
        this.A0.setContentDescription(E().b(y()));
        this.A0.setText(strC);
        androidx.fragment.app.c cVarF = f();
        cVarF.getClass();
        s7 s7Var = new s7(cVarF);
        int i = R$id.mtrl_calendar_frame;
        qb0 qb0Var2 = this.n0;
        if (i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        s7Var.e(i, qb0Var2, null, 2);
        if (s7Var.g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        s7Var.p.v(s7Var, false);
        this.n0.C(new q50(0, this));
    }

    public final void I(CheckableImageButton checkableImageButton) {
        this.B0.setContentDescription(this.u0 == 1 ? checkableImageButton.getContext().getString(R$string.mtrl_picker_toggle_to_calendar_input_mode) : checkableImageButton.getContext().getString(R$string.mtrl_picker_toggle_to_text_input_mode));
    }

    @Override // defpackage.uk, defpackage.jt
    public final void l(Bundle bundle) {
        super.l(bundle);
        if (bundle == null) {
            bundle = this.g;
        }
        this.l0 = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.m0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.o0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.p0 = (DayViewDecorator) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.r0 = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.s0 = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.u0 = bundle.getInt("INPUT_MODE_KEY");
        this.v0 = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.w0 = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.x0 = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.y0 = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
        CharSequence text = this.s0;
        if (text == null) {
            text = y().getResources().getText(this.r0);
        }
        this.F0 = text;
        if (text != null) {
            CharSequence[] charSequenceArrSplit = TextUtils.split(String.valueOf(text), "\n");
            if (charSequenceArrSplit.length > 1) {
                text = charSequenceArrSplit[0];
            }
        } else {
            text = null;
        }
        this.G0 = text;
    }

    @Override // defpackage.jt
    public final View m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(this.t0 ? R$layout.mtrl_picker_fullscreen : R$layout.mtrl_picker_dialog, viewGroup);
        Context context = viewInflate.getContext();
        if (this.t0) {
            viewInflate.findViewById(R$id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(F(context), -2));
        } else {
            viewInflate.findViewById(R$id.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(F(context), -1));
        }
        TextView textView = (TextView) viewInflate.findViewById(R$id.mtrl_picker_header_selection_text);
        this.A0 = textView;
        WeakHashMap weakHashMap = xr0.a;
        textView.setAccessibilityLiveRegion(1);
        this.B0 = (CheckableImageButton) viewInflate.findViewById(R$id.mtrl_picker_header_toggle);
        this.z0 = (TextView) viewInflate.findViewById(R$id.mtrl_picker_title_text);
        this.B0.setTag("TOGGLE_BUTTON_TAG");
        CheckableImageButton checkableImageButton = this.B0;
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, rp0.y(context, R$drawable.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], rp0.y(context, R$drawable.material_ic_edit_black_24dp));
        checkableImageButton.setImageDrawable(stateListDrawable);
        this.B0.setChecked(this.u0 != 0);
        xr0.p(this.B0, null);
        I(this.B0);
        this.B0.setOnClickListener(new nd(2, this));
        this.D0 = (Button) viewInflate.findViewById(R$id.confirm_button);
        if (E().g()) {
            this.D0.setEnabled(true);
        } else {
            this.D0.setEnabled(false);
        }
        this.D0.setTag("CONFIRM_BUTTON_TAG");
        CharSequence charSequence = this.w0;
        if (charSequence != null) {
            this.D0.setText(charSequence);
        } else {
            int i = this.v0;
            if (i != 0) {
                this.D0.setText(i);
            }
        }
        this.D0.setOnClickListener(new p50(this, 0));
        Button button = (Button) viewInflate.findViewById(R$id.cancel_button);
        button.setTag("CANCEL_BUTTON_TAG");
        CharSequence charSequence2 = this.y0;
        if (charSequence2 != null) {
            button.setText(charSequence2);
        } else {
            int i2 = this.x0;
            if (i2 != 0) {
                button.setText(i2);
            }
        }
        button.setOnClickListener(new p50(this, 1));
        return viewInflate;
    }

    @Override // defpackage.uk, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.j0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
    }

    @Override // defpackage.uk, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.k0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) this.F;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // defpackage.uk, defpackage.jt
    public final void q(Bundle bundle) {
        super.q(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.l0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.m0);
        CalendarConstraints calendarConstraints = this.o0;
        b bVar = new b();
        int i = b.c;
        int i2 = b.c;
        bVar.b = new DateValidatorPointForward(Long.MIN_VALUE);
        long j = calendarConstraints.b.g;
        long j2 = calendarConstraints.c.g;
        bVar.a = Long.valueOf(calendarConstraints.e.g);
        CalendarConstraints.DateValidator dateValidator = calendarConstraints.d;
        bVar.b = dateValidator;
        l lVar = this.q0;
        Month month = lVar == null ? null : lVar.X;
        if (month != null) {
            bVar.a = Long.valueOf(month.g);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("DEEP_COPY_VALIDATOR_KEY", dateValidator);
        Month monthM = Month.m(j);
        Month monthM2 = Month.m(j2);
        CalendarConstraints.DateValidator dateValidator2 = (CalendarConstraints.DateValidator) bundle2.getParcelable("DEEP_COPY_VALIDATOR_KEY");
        Long l = bVar.a;
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", new CalendarConstraints(monthM, monthM2, dateValidator2, l != null ? Month.m(l.longValue()) : null, calendarConstraints.f));
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.p0);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.r0);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.s0);
        bundle.putInt("INPUT_MODE_KEY", this.u0);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.v0);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.w0);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.x0);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.y0);
    }

    @Override // defpackage.uk, defpackage.jt
    public final void r() {
        super.r();
        Dialog dialog = this.d0;
        if (dialog == null) {
            throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
        }
        Window window = dialog.getWindow();
        if (this.t0) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.C0);
            if (!this.E0) {
                View viewFindViewById = z().findViewById(R$id.fullscreen_header);
                Integer numValueOf = viewFindViewById.getBackground() instanceof ColorDrawable ? Integer.valueOf(((ColorDrawable) viewFindViewById.getBackground()).getColor()) : null;
                int i = Build.VERSION.SDK_INT;
                boolean z = false;
                boolean z2 = numValueOf == null || numValueOf.intValue() == 0;
                int iP = k9.p(window.getContext(), R.attr.colorBackground, -16777216);
                if (z2) {
                    numValueOf = Integer.valueOf(iP);
                }
                if (i >= 30) {
                    yt0.a(window, false);
                } else {
                    View decorView = window.getDecorView();
                    decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 1792);
                }
                window.getContext();
                int iE = i < 27 ? je.e(k9.p(window.getContext(), R.attr.navigationBarColor, -16777216), Uuid.SIZE_BITS) : 0;
                window.setStatusBarColor(0);
                window.setNavigationBarColor(iE);
                new ev0(window, window.getDecorView()).a.c(k9.u(0) || k9.u(numValueOf.intValue()));
                boolean zU = k9.u(iP);
                if (k9.u(iE) || (iE == 0 && zU)) {
                    z = true;
                }
                new ev0(window, window.getDecorView()).a.b(z);
                gx gxVar = new gx(viewFindViewById, viewFindViewById.getLayoutParams().height, viewFindViewById.getPaddingTop());
                WeakHashMap weakHashMap = xr0.a;
                lr0.u(viewFindViewById, gxVar);
                this.E0 = true;
            }
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = y().getResources().getDimensionPixelOffset(R$dimen.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.C0, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            View decorView2 = window.getDecorView();
            Dialog dialog2 = this.d0;
            if (dialog2 == null) {
                throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
            }
            decorView2.setOnTouchListener(new hy(dialog2, rect));
        }
        H();
    }

    @Override // defpackage.uk, defpackage.jt
    public final void s() {
        this.n0.S.clear();
        super.s();
    }
}
