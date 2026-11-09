package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$string;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.fk0;
import defpackage.hd0;
import defpackage.qm;
import defpackage.s90;
import defpackage.y3;
import io.nats.client.support.NatsConstants;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<fk0> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new y3(11);
    public String E;
    public Long F;
    public Long G;
    public Long H;
    public Long I;

    public static void l(RangeDateSelector rangeDateSelector, TextInputLayout textInputLayout, TextInputLayout textInputLayout2, hd0 hd0Var) {
        Long l = rangeDateSelector.H;
        if (l == null || rangeDateSelector.I == null) {
            if (textInputLayout.getError() != null && rangeDateSelector.E.contentEquals(textInputLayout.getError())) {
                textInputLayout.setError(null);
            }
            if (textInputLayout2.getError() != null && NatsConstants.SPACE.contentEquals(textInputLayout2.getError())) {
                textInputLayout2.setError(null);
            }
            hd0Var.a();
        } else if (l.longValue() <= rangeDateSelector.I.longValue()) {
            Long l2 = rangeDateSelector.H;
            rangeDateSelector.F = l2;
            Long l3 = rangeDateSelector.I;
            rangeDateSelector.G = l3;
            hd0Var.b(new fk0(l2, l3));
        } else {
            textInputLayout.setError(rangeDateSelector.E);
            textInputLayout2.setError(NatsConstants.SPACE);
            hd0Var.a();
        }
        if (!TextUtils.isEmpty(textInputLayout.getError())) {
            textInputLayout.getError();
        } else {
            if (TextUtils.isEmpty(textInputLayout2.getError())) {
                return;
            }
            textInputLayout2.getError();
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final String a(Context context) {
        Resources resources = context.getResources();
        fk0 fk0VarW = qm.w(this.F, this.G);
        Object obj = fk0VarW.a;
        String string = obj == null ? resources.getString(R$string.mtrl_picker_announce_current_selection_none) : (String) obj;
        Object obj2 = fk0VarW.b;
        return resources.getString(R$string.mtrl_picker_announce_current_range_selection, string, obj2 == null ? resources.getString(R$string.mtrl_picker_announce_current_selection_none) : (String) obj2);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final String b(Context context) {
        Resources resources = context.getResources();
        Long l = this.F;
        if (l == null && this.G == null) {
            return resources.getString(R$string.mtrl_picker_range_header_unselected);
        }
        Long l2 = this.G;
        if (l2 == null) {
            return resources.getString(R$string.mtrl_picker_range_header_only_start_selected, qm.x(l.longValue()));
        }
        if (l == null) {
            return resources.getString(R$string.mtrl_picker_range_header_only_end_selected, qm.x(l2.longValue()));
        }
        fk0 fk0VarW = qm.w(l, l2);
        return resources.getString(R$string.mtrl_picker_range_header_selected, fk0VarW.a, fk0VarW.b);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final int c(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return s90.W(context, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) > resources.getDimensionPixelSize(R$dimen.mtrl_calendar_maximum_default_fullscreen_minor_axis) ? R$attr.materialCalendarTheme : R$attr.materialCalendarFullscreenTheme, m.class.getCanonicalName()).data;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final ArrayList d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new fk0(this.F, this.G));
        return arrayList;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final boolean f() {
        Long l = this.F;
        return (l == null || this.G == null || l.longValue() > this.G.longValue()) ? false : true;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final ArrayList g() {
        ArrayList arrayList = new ArrayList();
        Long l = this.F;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.G;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final Object h() {
        return new fk0(this.F, this.G);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final void i(long j) {
        Long l = this.F;
        if (l == null) {
            this.F = Long.valueOf(j);
        } else if (this.G == null && l.longValue() <= j) {
            this.G = Long.valueOf(j);
        } else {
            this.G = null;
            this.F = Long.valueOf(j);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0047  */
    @Override // com.google.android.material.datepicker.DateSelector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View k(android.view.LayoutInflater r12, android.view.ViewGroup r13, com.google.android.material.datepicker.CalendarConstraints r14, defpackage.hd0 r15) {
        /*
            r11 = this;
            int r0 = com.google.android.material.R$layout.mtrl_picker_text_input_date_range
            r1 = 0
            android.view.View r12 = r12.inflate(r0, r13, r1)
            int r13 = com.google.android.material.R$id.mtrl_picker_text_input_range_start
            android.view.View r13 = r12.findViewById(r13)
            r4 = r13
            com.google.android.material.textfield.TextInputLayout r4 = (com.google.android.material.textfield.TextInputLayout) r4
            int r13 = com.google.android.material.R$id.mtrl_picker_text_input_range_end
            android.view.View r13 = r12.findViewById(r13)
            r7 = r13
            com.google.android.material.textfield.TextInputLayout r7 = (com.google.android.material.textfield.TextInputLayout) r7
            android.widget.EditText r13 = r4.getEditText()
            android.widget.EditText r10 = r7.getEditText()
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r1 = ""
            if (r0 == 0) goto L2e
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r2 = r0.toLowerCase(r2)
            goto L2f
        L2e:
            r2 = r1
        L2f:
            java.lang.String r3 = "lge"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L47
            if (r0 == 0) goto L3f
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r1 = r0.toLowerCase(r1)
        L3f:
            java.lang.String r0 = "samsung"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L4f
        L47:
            r0 = 17
            r13.setInputType(r0)
            r10.setInputType(r0)
        L4f:
            android.content.res.Resources r0 = r12.getResources()
            int r1 = com.google.android.material.R$string.mtrl_picker_invalid_range
            java.lang.String r0 = r0.getString(r1)
            r11.E = r0
            java.text.SimpleDateFormat r3 = defpackage.h31.d()
            java.lang.Long r0 = r11.F
            if (r0 == 0) goto L6e
            java.lang.String r0 = r3.format(r0)
            r13.setText(r0)
            java.lang.Long r0 = r11.F
            r11.H = r0
        L6e:
            java.lang.Long r0 = r11.G
            if (r0 == 0) goto L7d
            java.lang.String r0 = r3.format(r0)
            r10.setText(r0)
            java.lang.Long r0 = r11.G
            r11.I = r0
        L7d:
            android.content.res.Resources r0 = r12.getResources()
            java.lang.String r2 = defpackage.h31.e(r0, r3)
            r4.setPlaceholderText(r2)
            r7.setPlaceholderText(r2)
            sm0 r0 = new sm0
            r9 = 0
            r6 = r4
            r1 = r11
            r5 = r14
            r8 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r13.addTextChangedListener(r0)
            sm0 r0 = new sm0
            r9 = 1
            r4 = r7
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r10.addTextChangedListener(r0)
            android.widget.EditText[] r13 = new android.widget.EditText[]{r13, r10}
            com.google.android.material.datepicker.DateSelector.j(r13)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.RangeDateSelector.k(android.view.LayoutInflater, android.view.ViewGroup, com.google.android.material.datepicker.CalendarConstraints, hd0):android.view.View");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.F);
        parcel.writeValue(this.G);
    }
}
