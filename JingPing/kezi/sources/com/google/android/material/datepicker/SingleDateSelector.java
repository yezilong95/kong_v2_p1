package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.R$attr;
import com.google.android.material.R$string;
import defpackage.e2;
import defpackage.nv0;
import defpackage.xi;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class SingleDateSelector implements DateSelector<Long> {
    public static final Parcelable.Creator<SingleDateSelector> CREATOR = new e2(12);
    public Long b;

    @Override // com.google.android.material.datepicker.DateSelector
    public final Object a() {
        return this.b;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final String b(Context context) {
        Resources resources = context.getResources();
        Long l = this.b;
        return resources.getString(R$string.mtrl_picker_announce_current_selection, l == null ? resources.getString(R$string.mtrl_picker_announce_current_selection_none) : xi.E(l.longValue(), Locale.getDefault()));
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final String c(Context context) {
        Resources resources = context.getResources();
        Long l = this.b;
        if (l == null) {
            return resources.getString(R$string.mtrl_picker_date_header_unselected);
        }
        return resources.getString(R$string.mtrl_picker_date_header_selected, xi.E(l.longValue(), Locale.getDefault()));
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final int d(Context context) {
        return nv0.L(context, R$attr.materialCalendarTheme, m.class.getCanonicalName()).data;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final ArrayList e() {
        return new ArrayList();
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final boolean g() {
        return this.b != null;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final void h(long j) {
        this.b = Long.valueOf(j);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final ArrayList j() {
        ArrayList arrayList = new ArrayList();
        Long l = this.b;
        if (l != null) {
            arrayList.add(l);
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003a  */
    @Override // com.google.android.material.datepicker.DateSelector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View k(android.view.LayoutInflater r9, android.view.ViewGroup r10, com.google.android.material.datepicker.CalendarConstraints r11, defpackage.q50 r12) {
        /*
            r8 = this;
            int r0 = com.google.android.material.R$layout.mtrl_picker_text_input_date
            r1 = 0
            android.view.View r9 = r9.inflate(r0, r10, r1)
            int r10 = com.google.android.material.R$id.mtrl_picker_text_input_date
            android.view.View r10 = r9.findViewById(r10)
            r4 = r10
            com.google.android.material.textfield.TextInputLayout r4 = (com.google.android.material.textfield.TextInputLayout) r4
            android.widget.EditText r10 = r4.getEditText()
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r1 = ""
            if (r0 == 0) goto L21
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r2 = r0.toLowerCase(r2)
            goto L22
        L21:
            r2 = r1
        L22:
            java.lang.String r3 = "lge"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L3a
            if (r0 == 0) goto L32
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r1 = r0.toLowerCase(r1)
        L32:
            java.lang.String r0 = "samsung"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L3f
        L3a:
            r0 = 17
            r10.setInputType(r0)
        L3f:
            java.text.SimpleDateFormat r3 = defpackage.fq0.d()
            android.content.res.Resources r0 = r9.getResources()
            java.lang.String r2 = defpackage.fq0.e(r0, r3)
            r4.setPlaceholderText(r2)
            java.lang.Long r0 = r8.b
            if (r0 == 0) goto L59
            java.lang.String r0 = r3.format(r0)
            r10.setText(r0)
        L59:
            gj0 r0 = new gj0
            r7 = r4
            r1 = r8
            r5 = r11
            r6 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            r10.addTextChangedListener(r0)
            android.widget.EditText[] r10 = new android.widget.EditText[]{r10}
            com.google.android.material.datepicker.DateSelector.i(r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.SingleDateSelector.k(android.view.LayoutInflater, android.view.ViewGroup, com.google.android.material.datepicker.CalendarConstraints, q50):android.view.View");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.b);
    }
}
