package defpackage;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;

/* JADX INFO: loaded from: classes.dex */
public final class x50<S> extends qb0 {
    public int T;
    public DateSelector U;
    public CalendarConstraints V;

    @Override // defpackage.jt
    public final void l(Bundle bundle) {
        super.l(bundle);
        if (bundle == null) {
            bundle = this.g;
        }
        this.T = bundle.getInt("THEME_RES_ID_KEY");
        this.U = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.V = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // defpackage.jt
    public final View m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.U.k(layoutInflater.cloneInContext(new ContextThemeWrapper(g(), this.T)), viewGroup, this.V, new q50(1, this));
    }

    @Override // defpackage.jt
    public final void q(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.T);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.U);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.V);
    }
}
