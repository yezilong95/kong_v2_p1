package defpackage;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.RangeDateSelector;
import com.google.android.material.datepicker.g;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes.dex */
public final class qc0 extends g {
    public final /* synthetic */ int j;
    public final /* synthetic */ TextInputLayout k;
    public final /* synthetic */ TextInputLayout l;
    public final /* synthetic */ q50 m;
    public final /* synthetic */ RangeDateSelector n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qc0(RangeDateSelector rangeDateSelector, String str, SimpleDateFormat simpleDateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, q50 q50Var, int i) {
        super(str, simpleDateFormat, textInputLayout, calendarConstraints);
        this.j = i;
        this.n = rangeDateSelector;
        this.k = textInputLayout2;
        this.l = textInputLayout3;
        this.m = q50Var;
    }

    @Override // com.google.android.material.datepicker.g
    public final void a() {
        switch (this.j) {
            case 0:
                RangeDateSelector rangeDateSelector = this.n;
                rangeDateSelector.e = null;
                RangeDateSelector.l(rangeDateSelector, this.k, this.l, this.m);
                break;
            default:
                RangeDateSelector rangeDateSelector2 = this.n;
                rangeDateSelector2.f = null;
                RangeDateSelector.l(rangeDateSelector2, this.k, this.l, this.m);
                break;
        }
    }

    @Override // com.google.android.material.datepicker.g
    public final void b(Long l) {
        switch (this.j) {
            case 0:
                RangeDateSelector rangeDateSelector = this.n;
                rangeDateSelector.e = l;
                RangeDateSelector.l(rangeDateSelector, this.k, this.l, this.m);
                break;
            default:
                RangeDateSelector rangeDateSelector2 = this.n;
                rangeDateSelector2.f = l;
                RangeDateSelector.l(rangeDateSelector2, this.k, this.l, this.m);
                break;
        }
    }
}
