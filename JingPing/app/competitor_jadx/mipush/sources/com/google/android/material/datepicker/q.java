package com.google.android.material.datepicker;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.R$id;
import defpackage.f51;
import defpackage.p41;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class q extends androidx.recyclerview.widget.n {
    public final TextView t;
    public final MaterialCalendarGridView u;

    public q(LinearLayout linearLayout, boolean z) {
        super(linearLayout);
        TextView textView = (TextView) linearLayout.findViewById(R$id.month_title);
        this.t = textView;
        WeakHashMap weakHashMap = f51.a;
        new p41(androidx.core.R$id.tag_accessibility_heading, Boolean.class, 0, 28, 3).f(textView, Boolean.TRUE);
        this.u = (MaterialCalendarGridView) linearLayout.findViewById(R$id.month_grid);
        if (z) {
            return;
        }
        textView.setVisibility(8);
    }
}
