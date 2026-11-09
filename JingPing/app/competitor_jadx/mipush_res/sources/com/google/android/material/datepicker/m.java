package com.google.android.material.datepicker;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
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
import defpackage.Cif;
import defpackage.dg;
import defpackage.f51;
import defpackage.fa0;
import defpackage.fd0;
import defpackage.gd0;
import defpackage.h31;
import defpackage.hd0;
import defpackage.k40;
import defpackage.ld0;
import defpackage.m9;
import defpackage.mo;
import defpackage.od0;
import defpackage.qm;
import defpackage.s90;
import defpackage.t41;
import defpackage.u71;
import defpackage.vk0;
import defpackage.z81;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class m<S> extends mo {
    public final LinkedHashSet K0 = new LinkedHashSet();
    public final LinkedHashSet L0 = new LinkedHashSet();
    public final LinkedHashSet M0 = new LinkedHashSet();
    public final LinkedHashSet N0 = new LinkedHashSet();
    public int O0;
    public DateSelector P0;
    public vk0 Q0;
    public CalendarConstraints R0;
    public DayViewDecorator S0;
    public l T0;
    public int U0;
    public CharSequence V0;
    public boolean W0;
    public int X0;
    public int Y0;
    public CharSequence Z0;
    public int a1;
    public CharSequence b1;
    public int c1;
    public CharSequence d1;
    public int e1;
    public CharSequence f1;
    public TextView g1;
    public TextView h1;
    public CheckableImageButton i1;
    public ld0 j1;
    public Button k1;
    public boolean l1;
    public CharSequence m1;
    public CharSequence n1;

    public static int F(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_content_padding);
        Month month = new Month(h31.f());
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.mtrl_calendar_day_width);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_month_horizontal_padding);
        int i = month.H;
        return ((i - 1) * dimensionPixelOffset2) + (dimensionPixelSize * i) + (dimensionPixelOffset * 2);
    }

    public static boolean G(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(s90.W(context, R$attr.materialCalendarStyle, l.class.getCanonicalName()).data, new int[]{i});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }

    @Override // defpackage.mo
    public final Dialog D() {
        Context contextY = y();
        Context contextY2 = y();
        int iC = this.O0;
        if (iC == 0) {
            iC = E().c(contextY2);
        }
        Dialog dialog = new Dialog(contextY, iC);
        Context context = dialog.getContext();
        this.W0 = G(context, R.attr.windowFullscreen);
        this.j1 = new ld0(context, null, R$attr.materialCalendarStyle, R$style.Widget_MaterialComponents_MaterialCalendar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.MaterialCalendar, R$attr.materialCalendarStyle, R$style.Widget_MaterialComponents_MaterialCalendar);
        int color = typedArrayObtainStyledAttributes.getColor(R$styleable.MaterialCalendar_backgroundTint, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.j1.k(context);
        this.j1.m(ColorStateList.valueOf(color));
        ld0 ld0Var = this.j1;
        View decorView = dialog.getWindow().getDecorView();
        WeakHashMap weakHashMap = f51.a;
        ld0Var.l(t41.i(decorView));
        return dialog;
    }

    public final DateSelector E() {
        if (this.P0 == null) {
            this.P0 = (DateSelector) this.J.getParcelable("DATE_SELECTOR_KEY");
        }
        return this.P0;
    }

    public final void H() {
        Context contextY = y();
        int iC = this.O0;
        if (iC == 0) {
            iC = E().c(contextY);
        }
        DateSelector dateSelectorE = E();
        CalendarConstraints calendarConstraints = this.R0;
        DayViewDecorator dayViewDecorator = this.S0;
        l lVar = new l();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", iC);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelectorE);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", dayViewDecorator);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.H);
        lVar.B(bundle);
        this.T0 = lVar;
        vk0 vk0Var = lVar;
        if (this.X0 == 1) {
            DateSelector dateSelectorE2 = E();
            CalendarConstraints calendarConstraints2 = this.R0;
            od0 od0Var = new od0();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("THEME_RES_ID_KEY", iC);
            bundle2.putParcelable("DATE_SELECTOR_KEY", dateSelectorE2);
            bundle2.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints2);
            od0Var.B(bundle2);
            vk0Var = od0Var;
        }
        this.Q0 = vk0Var;
        this.g1.setText((this.X0 == 1 && y().getResources().getConfiguration().orientation == 2) ? this.n1 : this.m1);
        String strB = E().b(g());
        this.h1.setContentDescription(E().a(y()));
        this.h1.setText(strB);
        androidx.fragment.app.c cVarF = f();
        cVarF.getClass();
        m9 m9Var = new m9(cVarF);
        int i = R$id.mtrl_calendar_frame;
        vk0 vk0Var2 = this.Q0;
        if (i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        m9Var.e(i, vk0Var2, null, 2);
        if (m9Var.g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        m9Var.p.u(m9Var, false);
        this.Q0.C(new hd0(0, this));
    }

    public final void I(CheckableImageButton checkableImageButton) {
        this.i1.setContentDescription(this.X0 == 1 ? checkableImageButton.getContext().getString(R$string.mtrl_picker_toggle_to_calendar_input_mode) : checkableImageButton.getContext().getString(R$string.mtrl_picker_toggle_to_text_input_mode));
    }

    @Override // defpackage.mo, defpackage.vz
    public final void l(Bundle bundle) {
        super.l(bundle);
        if (bundle == null) {
            bundle = this.J;
        }
        this.O0 = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.P0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.R0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.S0 = (DayViewDecorator) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.U0 = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.V0 = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.X0 = bundle.getInt("INPUT_MODE_KEY");
        this.Y0 = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.Z0 = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.a1 = bundle.getInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.b1 = bundle.getCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        this.c1 = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.d1 = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
        this.e1 = bundle.getInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.f1 = bundle.getCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        CharSequence text = this.V0;
        if (text == null) {
            text = y().getResources().getText(this.U0);
        }
        this.m1 = text;
        if (text != null) {
            CharSequence[] charSequenceArrSplit = TextUtils.split(String.valueOf(text), "\n");
            if (charSequenceArrSplit.length > 1) {
                text = charSequenceArrSplit[0];
            }
        } else {
            text = null;
        }
        this.n1 = text;
    }

    @Override // defpackage.vz
    public final View m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(this.W0 ? R$layout.mtrl_picker_fullscreen : R$layout.mtrl_picker_dialog, viewGroup);
        Context context = viewInflate.getContext();
        if (this.W0) {
            viewInflate.findViewById(R$id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(F(context), -2));
        } else {
            viewInflate.findViewById(R$id.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(F(context), -1));
        }
        TextView textView = (TextView) viewInflate.findViewById(R$id.mtrl_picker_header_selection_text);
        this.h1 = textView;
        WeakHashMap weakHashMap = f51.a;
        textView.setAccessibilityLiveRegion(1);
        this.i1 = (CheckableImageButton) viewInflate.findViewById(R$id.mtrl_picker_header_toggle);
        this.g1 = (TextView) viewInflate.findViewById(R$id.mtrl_picker_title_text);
        this.i1.setTag("TOGGLE_BUTTON_TAG");
        CheckableImageButton checkableImageButton = this.i1;
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, qm.z(context, R$drawable.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], qm.z(context, R$drawable.material_ic_edit_black_24dp));
        checkableImageButton.setImageDrawable(stateListDrawable);
        this.i1.setChecked(this.X0 != 0);
        f51.o(this.i1, null);
        I(this.i1);
        this.i1.setOnClickListener(new Cif(2, this));
        this.k1 = (Button) viewInflate.findViewById(R$id.confirm_button);
        if (E().f()) {
            this.k1.setEnabled(true);
        } else {
            this.k1.setEnabled(false);
        }
        this.k1.setTag("CONFIRM_BUTTON_TAG");
        CharSequence charSequence = this.Z0;
        if (charSequence != null) {
            this.k1.setText(charSequence);
        } else {
            int i = this.Y0;
            if (i != 0) {
                this.k1.setText(i);
            }
        }
        CharSequence charSequence2 = this.b1;
        if (charSequence2 != null) {
            this.k1.setContentDescription(charSequence2);
        } else if (this.a1 != 0) {
            this.k1.setContentDescription(g().getResources().getText(this.a1));
        }
        this.k1.setOnClickListener(new fd0(this, 0));
        Button button = (Button) viewInflate.findViewById(R$id.cancel_button);
        button.setTag("CANCEL_BUTTON_TAG");
        CharSequence charSequence3 = this.d1;
        if (charSequence3 != null) {
            button.setText(charSequence3);
        } else {
            int i2 = this.c1;
            if (i2 != 0) {
                button.setText(i2);
            }
        }
        CharSequence charSequence4 = this.f1;
        if (charSequence4 != null) {
            button.setContentDescription(charSequence4);
        } else if (this.e1 != 0) {
            button.setContentDescription(g().getResources().getText(this.e1));
        }
        button.setOnClickListener(new fd0(this, 1));
        return viewInflate;
    }

    @Override // defpackage.mo, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.M0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
    }

    @Override // defpackage.mo, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.N0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) this.i0;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // defpackage.mo, defpackage.vz
    public final void q(Bundle bundle) {
        super.q(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.O0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.P0);
        CalendarConstraints calendarConstraints = this.R0;
        b bVar = new b();
        int i = b.c;
        int i2 = b.c;
        bVar.b = new DateValidatorPointForward(Long.MIN_VALUE);
        long j = calendarConstraints.E.J;
        long j2 = calendarConstraints.F.J;
        bVar.a = Long.valueOf(calendarConstraints.H.J);
        CalendarConstraints.DateValidator dateValidator = calendarConstraints.G;
        bVar.b = dateValidator;
        l lVar = this.T0;
        Month month = lVar == null ? null : lVar.A0;
        if (month != null) {
            bVar.a = Long.valueOf(month.J);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("DEEP_COPY_VALIDATOR_KEY", dateValidator);
        Month monthM = Month.m(j);
        Month monthM2 = Month.m(j2);
        CalendarConstraints.DateValidator dateValidator2 = (CalendarConstraints.DateValidator) bundle2.getParcelable("DEEP_COPY_VALIDATOR_KEY");
        Long l = bVar.a;
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", new CalendarConstraints(monthM, monthM2, dateValidator2, l != null ? Month.m(l.longValue()) : null, calendarConstraints.I));
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.S0);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.U0);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.V0);
        bundle.putInt("INPUT_MODE_KEY", this.X0);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.Y0);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.Z0);
        bundle.putInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.a1);
        bundle.putCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.b1);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.c1);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.d1);
        bundle.putInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.e1);
        bundle.putCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.f1);
    }

    @Override // defpackage.mo, defpackage.vz
    public final void r() {
        super.r();
        Dialog dialog = this.G0;
        if (dialog == null) {
            throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
        }
        Window window = dialog.getWindow();
        if (this.W0) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.j1);
            if (!this.l1) {
                View viewFindViewById = z().findViewById(R$id.fullscreen_header);
                ColorStateList colorStateListS = qm.s(viewFindViewById.getBackground());
                Integer numValueOf = colorStateListS != null ? Integer.valueOf(colorStateListS.getDefaultColor()) : null;
                int i = Build.VERSION.SDK_INT;
                boolean z = false;
                boolean z2 = numValueOf == null || numValueOf.intValue() == 0;
                int iO = fa0.O(window.getContext(), R.attr.colorBackground, -16777216);
                if (z2) {
                    numValueOf = Integer.valueOf(iO);
                }
                if (i >= 30) {
                    u71.a(window, false);
                } else {
                    View decorView = window.getDecorView();
                    decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 1792);
                }
                window.getContext();
                int iE = i < 27 ? dg.e(fa0.O(window.getContext(), R.attr.navigationBarColor, -16777216), Uuid.SIZE_BITS) : 0;
                window.setStatusBarColor(0);
                window.setNavigationBarColor(iE);
                new z81(window, window.getDecorView()).a.c(fa0.c0(0) || fa0.c0(numValueOf.intValue()));
                boolean zC0 = fa0.c0(iO);
                if (fa0.c0(iE) || (iE == 0 && zC0)) {
                    z = true;
                }
                new z81(window, window.getDecorView()).a.b(z);
                gd0 gd0Var = new gd0(viewFindViewById, viewFindViewById.getLayoutParams().height, viewFindViewById.getPaddingTop());
                WeakHashMap weakHashMap = f51.a;
                t41.u(viewFindViewById, gd0Var);
                this.l1 = true;
            }
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = y().getResources().getDimensionPixelOffset(R$dimen.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.j1, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            View decorView2 = window.getDecorView();
            Dialog dialog2 = this.G0;
            if (dialog2 == null) {
                throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
            }
            decorView2.setOnTouchListener(new k40(dialog2, rect));
        }
        H();
    }

    @Override // defpackage.mo, defpackage.vz
    public final void s() {
        this.Q0.v0.clear();
        super.s();
    }
}
