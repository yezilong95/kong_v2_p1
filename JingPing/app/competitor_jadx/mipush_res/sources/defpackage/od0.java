package defpackage;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;

/* JADX INFO: loaded from: classes.dex */
public final class od0<S> extends vk0 {
    public int w0;
    public DateSelector x0;
    public CalendarConstraints y0;

    @Override // defpackage.vz
    public final void l(Bundle bundle) {
        super.l(bundle);
        if (bundle == null) {
            bundle = this.J;
        }
        this.w0 = bundle.getInt("THEME_RES_ID_KEY");
        this.x0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.y0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // defpackage.vz
    public final View m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.x0.k(layoutInflater.cloneInContext(new ContextThemeWrapper(g(), this.w0)), viewGroup, this.y0, new hd0(1, this));
    }

    @Override // defpackage.vz
    public final void q(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.w0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.x0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.y0);
    }
}
