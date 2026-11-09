package defpackage;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.SingleDateSelector;
import com.google.android.material.datepicker.g;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes.dex */
public final class wv0 extends g {
    public final /* synthetic */ hd0 M;
    public final /* synthetic */ TextInputLayout N;
    public final /* synthetic */ SingleDateSelector O;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public wv0(SingleDateSelector singleDateSelector, String str, SimpleDateFormat simpleDateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, hd0 hd0Var, TextInputLayout textInputLayout2) {
        super(str, simpleDateFormat, textInputLayout, calendarConstraints);
        this.O = singleDateSelector;
        this.M = hd0Var;
        this.N = textInputLayout2;
    }

    @Override // com.google.android.material.datepicker.g
    public final void a() {
        this.N.getError();
        this.O.getClass();
        this.M.a();
    }

    @Override // com.google.android.material.datepicker.g
    public final void b(Long l) {
        SingleDateSelector singleDateSelector = this.O;
        if (l == null) {
            singleDateSelector.E = null;
        } else {
            singleDateSelector.E = l;
        }
        singleDateSelector.getClass();
        this.M.b(singleDateSelector.E);
    }
}
