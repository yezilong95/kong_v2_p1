package com.google.android.material.datepicker;

import android.R;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$integer;
import com.google.android.material.R$layout;
import com.google.android.material.button.MaterialButton;
import defpackage.f51;
import defpackage.hb;
import defpackage.hd0;
import defpackage.jm;
import defpackage.ub;
import defpackage.up;
import defpackage.vk0;
import defpackage.wb;
import defpackage.xc0;
import defpackage.y2;
import defpackage.zj0;

/* JADX INFO: loaded from: classes.dex */
public final class l<S> extends vk0 {
    public Month A0;
    public int B0;
    public ub C0;
    public RecyclerView D0;
    public RecyclerView E0;
    public View F0;
    public View G0;
    public View H0;
    public View I0;
    public int w0;
    public DateSelector x0;
    public CalendarConstraints y0;
    public DayViewDecorator z0;

    @Override // defpackage.vk0
    public final void C(hd0 hd0Var) {
        this.v0.add(hd0Var);
    }

    public final void D(Month month) {
        r rVar = (r) this.E0.getAdapter();
        int iO = rVar.c.E.o(month);
        int iO2 = iO - rVar.c.E.o(this.A0);
        boolean z = Math.abs(iO2) > 3;
        boolean z2 = iO2 > 0;
        this.A0 = month;
        if (z && z2) {
            this.E0.Z(iO - 3);
            this.E0.post(new wb(this, iO, 2));
        } else if (!z) {
            this.E0.post(new wb(this, iO, 2));
        } else {
            this.E0.Z(iO + 3);
            this.E0.post(new wb(this, iO, 2));
        }
    }

    public final void E(int i) {
        this.B0 = i;
        if (i == 2) {
            this.D0.getLayoutManager().p0(this.A0.G - ((t) this.D0.getAdapter()).c.y0.E.G);
            this.H0.setVisibility(0);
            this.I0.setVisibility(8);
            this.F0.setVisibility(8);
            this.G0.setVisibility(8);
            return;
        }
        if (i == 1) {
            this.H0.setVisibility(8);
            this.I0.setVisibility(0);
            this.F0.setVisibility(0);
            this.G0.setVisibility(0);
            D(this.A0);
        }
    }

    @Override // defpackage.vz
    public final void l(Bundle bundle) {
        super.l(bundle);
        if (bundle == null) {
            bundle = this.J;
        }
        this.w0 = bundle.getInt("THEME_RES_ID_KEY");
        this.x0 = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.y0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.z0 = (DayViewDecorator) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.A0 = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // defpackage.vz
    public final View m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(g(), this.w0);
        this.C0 = new ub(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month month = this.y0.E;
        if (m.G(contextThemeWrapper, R.attr.windowFullscreen)) {
            i = R$layout.mtrl_calendar_vertical;
            i2 = 1;
        } else {
            i = R$layout.mtrl_calendar_horizontal;
            i2 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i, viewGroup, false);
        Resources resources = y().getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_navigation_bottom_padding) + resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelSize(R$dimen.mtrl_calendar_navigation_height);
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.mtrl_calendar_days_of_week_height);
        int i3 = o.K;
        viewInflate.setMinimumHeight(dimensionPixelOffset + dimensionPixelSize + (resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_month_vertical_padding) * (i3 - 1)) + (resources.getDimensionPixelSize(R$dimen.mtrl_calendar_day_height) * i3) + resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_bottom_padding));
        GridView gridView = (GridView) viewInflate.findViewById(R$id.mtrl_calendar_days_of_week);
        f51.o(gridView, new up(1));
        int i4 = this.y0.I;
        gridView.setAdapter((ListAdapter) (i4 > 0 ? new jm(i4) : new jm()));
        gridView.setNumColumns(month.H);
        gridView.setEnabled(false);
        this.E0 = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_months);
        this.E0.setLayoutManager(new xc0(this, i2, i2));
        this.E0.setTag("MONTHS_VIEW_GROUP_TAG");
        r rVar = new r(contextThemeWrapper, this.x0, this.y0, this.z0, new i(this));
        this.E0.setAdapter(rVar);
        int integer = contextThemeWrapper.getResources().getInteger(R$integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_year_selector_frame);
        this.D0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.D0.setLayoutManager(new GridLayoutManager(integer));
            this.D0.setAdapter(new t(this));
            this.D0.g(new j(this));
        }
        if (viewInflate.findViewById(R$id.month_navigation_fragment_toggle) != null) {
            MaterialButton materialButton = (MaterialButton) viewInflate.findViewById(R$id.month_navigation_fragment_toggle);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            f51.o(materialButton, new hb(3, this));
            View viewFindViewById = viewInflate.findViewById(R$id.month_navigation_previous);
            this.F0 = viewFindViewById;
            viewFindViewById.setTag("NAVIGATION_PREV_TAG");
            View viewFindViewById2 = viewInflate.findViewById(R$id.month_navigation_next);
            this.G0 = viewFindViewById2;
            viewFindViewById2.setTag("NAVIGATION_NEXT_TAG");
            this.H0 = viewInflate.findViewById(R$id.mtrl_calendar_year_selector_frame);
            this.I0 = viewInflate.findViewById(R$id.mtrl_calendar_day_selector_frame);
            E(1);
            materialButton.setText(this.A0.n());
            this.E0.addOnScrollListener(new k(this, rVar, materialButton));
            materialButton.setOnClickListener(new y2(2, this));
            this.G0.setOnClickListener(new h(this, rVar, 1));
            this.F0.setOnClickListener(new h(this, rVar, 0));
        }
        if (!m.G(contextThemeWrapper, R.attr.windowFullscreen)) {
            new zj0().a(this.E0);
        }
        this.E0.Z(rVar.c.E.o(this.A0));
        f51.o(this.E0, new up(2));
        return viewInflate;
    }

    @Override // defpackage.vz
    public final void q(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.w0);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.x0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.y0);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.z0);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.A0);
    }
}
