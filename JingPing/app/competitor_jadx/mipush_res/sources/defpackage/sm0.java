package defpackage;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.RangeDateSelector;
import com.google.android.material.datepicker.g;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes.dex */
public final class sm0 extends g {
    public final /* synthetic */ int M;
    public final /* synthetic */ TextInputLayout N;
    public final /* synthetic */ TextInputLayout O;
    public final /* synthetic */ hd0 P;
    public final /* synthetic */ RangeDateSelector Q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ sm0(RangeDateSelector rangeDateSelector, String str, SimpleDateFormat simpleDateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, hd0 hd0Var, int i) {
        super(str, simpleDateFormat, textInputLayout, calendarConstraints);
        this.M = i;
        this.Q = rangeDateSelector;
        this.N = textInputLayout2;
        this.O = textInputLayout3;
        this.P = hd0Var;
    }

    @Override // com.google.android.material.datepicker.g
    public final void a() {
        switch (this.M) {
            case 0:
                RangeDateSelector rangeDateSelector = this.Q;
                rangeDateSelector.H = null;
                RangeDateSelector.l(rangeDateSelector, this.N, this.O, this.P);
                break;
            default:
                RangeDateSelector rangeDateSelector2 = this.Q;
                rangeDateSelector2.I = null;
                RangeDateSelector.l(rangeDateSelector2, this.N, this.O, this.P);
                break;
        }
    }

    @Override // com.google.android.material.datepicker.g
    public final void b(Long l) {
        switch (this.M) {
            case 0:
                RangeDateSelector rangeDateSelector = this.Q;
                rangeDateSelector.H = l;
                RangeDateSelector.l(rangeDateSelector, this.N, this.O, this.P);
                break;
            default:
                RangeDateSelector rangeDateSelector2 = this.Q;
                rangeDateSelector2.I = l;
                RangeDateSelector.l(rangeDateSelector2, this.N, this.O, this.P);
                break;
        }
    }
}
