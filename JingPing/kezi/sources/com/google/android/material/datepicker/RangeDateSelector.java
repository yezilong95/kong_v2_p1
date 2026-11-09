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
import defpackage.e2;
import defpackage.lb0;
import defpackage.nv0;
import defpackage.q50;
import defpackage.xi;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<lb0> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new e2(11);
    public String b;
    public Long c;
    public Long d;
    public Long e;
    public Long f;

    public static void l(RangeDateSelector rangeDateSelector, TextInputLayout textInputLayout, TextInputLayout textInputLayout2, q50 q50Var) {
        Long l = rangeDateSelector.e;
        if (l == null || rangeDateSelector.f == null) {
            if (textInputLayout.getError() != null && rangeDateSelector.b.contentEquals(textInputLayout.getError())) {
                textInputLayout.setError(null);
            }
            if (textInputLayout2.getError() != null && " ".contentEquals(textInputLayout2.getError())) {
                textInputLayout2.setError(null);
            }
            q50Var.a();
        } else if (l.longValue() <= rangeDateSelector.f.longValue()) {
            Long l2 = rangeDateSelector.e;
            rangeDateSelector.c = l2;
            Long l3 = rangeDateSelector.f;
            rangeDateSelector.d = l3;
            q50Var.b(new lb0(l2, l3));
        } else {
            textInputLayout.setError(rangeDateSelector.b);
            textInputLayout2.setError(" ");
            q50Var.a();
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
    public final Object a() {
        return new lb0(this.c, this.d);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final String b(Context context) {
        Resources resources = context.getResources();
        lb0 lb0VarT = xi.t(this.c, this.d);
        Object obj = lb0VarT.a;
        String string = obj == null ? resources.getString(R$string.mtrl_picker_announce_current_selection_none) : (String) obj;
        Object obj2 = lb0VarT.b;
        return resources.getString(R$string.mtrl_picker_announce_current_range_selection, string, obj2 == null ? resources.getString(R$string.mtrl_picker_announce_current_selection_none) : (String) obj2);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final String c(Context context) {
        Resources resources = context.getResources();
        Long l = this.c;
        if (l == null && this.d == null) {
            return resources.getString(R$string.mtrl_picker_range_header_unselected);
        }
        Long l2 = this.d;
        if (l2 == null) {
            return resources.getString(R$string.mtrl_picker_range_header_only_start_selected, xi.u(l.longValue()));
        }
        if (l == null) {
            return resources.getString(R$string.mtrl_picker_range_header_only_end_selected, xi.u(l2.longValue()));
        }
        lb0 lb0VarT = xi.t(l, l2);
        return resources.getString(R$string.mtrl_picker_range_header_selected, lb0VarT.a, lb0VarT.b);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final int d(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return nv0.L(context, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) > resources.getDimensionPixelSize(R$dimen.mtrl_calendar_maximum_default_fullscreen_minor_axis) ? R$attr.materialCalendarTheme : R$attr.materialCalendarFullscreenTheme, m.class.getCanonicalName()).data;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final ArrayList e() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new lb0(this.c, this.d));
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final boolean g() {
        Long l = this.c;
        return (l == null || this.d == null || l.longValue() > this.d.longValue()) ? false : true;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final void h(long j) {
        Long l = this.c;
        if (l == null) {
            this.c = Long.valueOf(j);
        } else if (this.d == null && l.longValue() <= j) {
            this.d = Long.valueOf(j);
        } else {
            this.d = null;
            this.c = Long.valueOf(j);
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final ArrayList j() {
        ArrayList arrayList = new ArrayList();
        Long l = this.c;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.d;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0047  */
    @Override // com.google.android.material.datepicker.DateSelector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View k(android.view.LayoutInflater r12, android.view.ViewGroup r13, com.google.android.material.datepicker.CalendarConstraints r14, defpackage.q50 r15) {
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
            r11.b = r0
            java.text.SimpleDateFormat r3 = defpackage.fq0.d()
            java.lang.Long r0 = r11.c
            if (r0 == 0) goto L6e
            java.lang.String r0 = r3.format(r0)
            r13.setText(r0)
            java.lang.Long r0 = r11.c
            r11.e = r0
        L6e:
            java.lang.Long r0 = r11.d
            if (r0 == 0) goto L7d
            java.lang.String r0 = r3.format(r0)
            r10.setText(r0)
            java.lang.Long r0 = r11.d
            r11.f = r0
        L7d:
            android.content.res.Resources r0 = r12.getResources()
            java.lang.String r2 = defpackage.fq0.e(r0, r3)
            r4.setPlaceholderText(r2)
            r7.setPlaceholderText(r2)
            qc0 r0 = new qc0
            r9 = 0
            r6 = r4
            r1 = r11
            r5 = r14
            r8 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r13.addTextChangedListener(r0)
            qc0 r0 = new qc0
            r9 = 1
            r4 = r7
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r10.addTextChangedListener(r0)
            android.widget.EditText[] r13 = new android.widget.EditText[]{r13, r10}
            com.google.android.material.datepicker.DateSelector.i(r13)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.RangeDateSelector.k(android.view.LayoutInflater, android.view.ViewGroup, com.google.android.material.datepicker.CalendarConstraints, q50):android.view.View");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.c);
        parcel.writeValue(this.d);
    }
}
