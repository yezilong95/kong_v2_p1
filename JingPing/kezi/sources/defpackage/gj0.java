package defpackage;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.SingleDateSelector;
import com.google.android.material.datepicker.g;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes.dex */
public final class gj0 extends g {
    public final /* synthetic */ q50 j;
    public final /* synthetic */ TextInputLayout k;
    public final /* synthetic */ SingleDateSelector l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public gj0(SingleDateSelector singleDateSelector, String str, SimpleDateFormat simpleDateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, q50 q50Var, TextInputLayout textInputLayout2) {
        super(str, simpleDateFormat, textInputLayout, calendarConstraints);
        this.l = singleDateSelector;
        this.j = q50Var;
        this.k = textInputLayout2;
    }

    @Override // com.google.android.material.datepicker.g
    public final void a() {
        this.k.getError();
        this.l.getClass();
        this.j.a();
    }

    @Override // com.google.android.material.datepicker.g
    public final void b(Long l) {
        SingleDateSelector singleDateSelector = this.l;
        if (l == null) {
            singleDateSelector.b = null;
        } else {
            singleDateSelector.b = l;
        }
        singleDateSelector.getClass();
        this.j.b(singleDateSelector.b);
    }
}
