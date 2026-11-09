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
import defpackage.c1;
import defpackage.ea;
import defpackage.fb0;
import defpackage.h50;
import defpackage.ha;
import defpackage.j9;
import defpackage.pi;
import defpackage.q50;
import defpackage.qb0;
import defpackage.rl;
import defpackage.xr0;

/* JADX INFO: loaded from: classes.dex */
public final class l<S> extends qb0 {
    public int T;
    public DateSelector U;
    public CalendarConstraints V;
    public DayViewDecorator W;
    public Month X;
    public int Y;
    public ea Z;
    public RecyclerView a0;
    public RecyclerView b0;
    public View c0;
    public View d0;
    public View e0;
    public View f0;

    @Override // defpackage.qb0
    public final void C(q50 q50Var) {
        this.S.add(q50Var);
    }

    public final void D(Month month) {
        r rVar = (r) this.b0.getAdapter();
        int iO = rVar.c.b.o(month);
        int iO2 = iO - rVar.c.b.o(this.X);
        boolean z = Math.abs(iO2) > 3;
        boolean z2 = iO2 > 0;
        this.X = month;
        if (z && z2) {
            this.b0.Z(iO - 3);
            this.b0.post(new ha(this, iO, 2));
        } else if (!z) {
            this.b0.post(new ha(this, iO, 2));
        } else {
            this.b0.Z(iO + 3);
            this.b0.post(new ha(this, iO, 2));
        }
    }

    public final void E(int i) {
        this.Y = i;
        if (i == 2) {
            this.a0.getLayoutManager().m0(this.X.d - ((t) this.a0.getAdapter()).c.V.b.d);
            this.e0.setVisibility(0);
            this.f0.setVisibility(8);
            this.c0.setVisibility(8);
            this.d0.setVisibility(8);
            return;
        }
        if (i == 1) {
            this.e0.setVisibility(8);
            this.f0.setVisibility(0);
            this.c0.setVisibility(0);
            this.d0.setVisibility(0);
            D(this.X);
        }
    }

    @Override // defpackage.jt
    public final void l(Bundle bundle) {
        super.l(bundle);
        if (bundle == null) {
            bundle = this.g;
        }
        this.T = bundle.getInt("THEME_RES_ID_KEY");
        this.U = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.V = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.W = (DayViewDecorator) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.X = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // defpackage.jt
    public final View m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(g(), this.T);
        this.Z = new ea(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month month = this.V.b;
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
        int i3 = o.h;
        viewInflate.setMinimumHeight(dimensionPixelOffset + dimensionPixelSize + (resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_month_vertical_padding) * (i3 - 1)) + (resources.getDimensionPixelSize(R$dimen.mtrl_calendar_day_height) * i3) + resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_bottom_padding));
        GridView gridView = (GridView) viewInflate.findViewById(R$id.mtrl_calendar_days_of_week);
        xr0.p(gridView, new rl(1));
        int i4 = this.V.f;
        gridView.setAdapter((ListAdapter) (i4 > 0 ? new pi(i4) : new pi()));
        gridView.setNumColumns(month.e);
        gridView.setEnabled(false);
        this.b0 = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_months);
        this.b0.setLayoutManager(new h50(this, i2, i2));
        this.b0.setTag("MONTHS_VIEW_GROUP_TAG");
        r rVar = new r(contextThemeWrapper, this.U, this.V, this.W, new i(this));
        this.b0.setAdapter(rVar);
        int integer = contextThemeWrapper.getResources().getInteger(R$integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_year_selector_frame);
        this.a0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.a0.setLayoutManager(new GridLayoutManager(integer));
            this.a0.setAdapter(new t(this));
            this.a0.g(new j(this));
        }
        if (viewInflate.findViewById(R$id.month_navigation_fragment_toggle) != null) {
            MaterialButton materialButton = (MaterialButton) viewInflate.findViewById(R$id.month_navigation_fragment_toggle);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            xr0.p(materialButton, new j9(3, this));
            View viewFindViewById = viewInflate.findViewById(R$id.month_navigation_previous);
            this.c0 = viewFindViewById;
            viewFindViewById.setTag("NAVIGATION_PREV_TAG");
            View viewFindViewById2 = viewInflate.findViewById(R$id.month_navigation_next);
            this.d0 = viewFindViewById2;
            viewFindViewById2.setTag("NAVIGATION_NEXT_TAG");
            this.e0 = viewInflate.findViewById(R$id.mtrl_calendar_year_selector_frame);
            this.f0 = viewInflate.findViewById(R$id.mtrl_calendar_day_selector_frame);
            E(1);
            materialButton.setText(this.X.n());
            this.b0.addOnScrollListener(new k(this, rVar, materialButton));
            materialButton.setOnClickListener(new c1(2, this));
            this.d0.setOnClickListener(new h(this, rVar, 1));
            this.c0.setOnClickListener(new h(this, rVar, 0));
        }
        if (!m.G(contextThemeWrapper, R.attr.windowFullscreen)) {
            new fb0().a(this.b0);
        }
        this.b0.Z(rVar.c.b.o(this.X));
        xr0.p(this.b0, new rl(2));
        return viewInflate;
    }

    @Override // defpackage.jt
    public final void q(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.T);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.U);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.V);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.W);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.X);
    }
}
