package com.google.android.material.datepicker;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.google.android.material.R$attr;
import com.google.android.material.R$id;
import defpackage.ea;
import defpackage.fq0;
import defpackage.lb0;
import defpackage.rl;
import defpackage.rp0;
import defpackage.xr0;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class MaterialCalendarGridView extends GridView {
    public final Calendar b;
    public final boolean c;

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final o a() {
        return (o) super.getAdapter();
    }

    public final View b(int i) {
        return getChildAt(i - getFirstVisiblePosition());
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public final Adapter getAdapter() {
        return (o) super.getAdapter();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((o) super.getAdapter()).notifyDataSetChanged();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int iA;
        int width;
        int iA2;
        int width2;
        int i;
        int width3;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        o oVar = (o) super.getAdapter();
        DateSelector dateSelector = oVar.c;
        ea eaVar = oVar.e;
        int iMax = Math.max(oVar.a(), materialCalendarGridView.getFirstVisiblePosition());
        int iMin = Math.min(oVar.c(), materialCalendarGridView.getLastVisiblePosition());
        Long item = oVar.getItem(iMax);
        Long item2 = oVar.getItem(iMin);
        Iterator it = dateSelector.e().iterator();
        while (it.hasNext()) {
            lb0 lb0Var = (lb0) it.next();
            Object obj = lb0Var.a;
            if (obj != null) {
                Object obj2 = lb0Var.b;
                if (obj2 != null) {
                    Long l = (Long) obj;
                    long jLongValue = l.longValue();
                    Long l2 = (Long) obj2;
                    long jLongValue2 = l2.longValue();
                    if (item == null || item2 == null || l.longValue() > item2.longValue() || l2.longValue() < item.longValue()) {
                        materialCalendarGridView = this;
                        oVar = oVar;
                        it = it;
                    } else {
                        boolean zI = rp0.I(materialCalendarGridView);
                        long jLongValue3 = item.longValue();
                        Calendar calendar = materialCalendarGridView.b;
                        Month month = oVar.b;
                        if (jLongValue < jLongValue3) {
                            width = iMax % month.e == 0 ? 0 : !zI ? materialCalendarGridView.b(iMax - 1).getRight() : materialCalendarGridView.b(iMax - 1).getLeft();
                            iA = iMax;
                        } else {
                            calendar.setTimeInMillis(jLongValue);
                            iA = oVar.a() + (calendar.get(5) - 1);
                            View viewB = materialCalendarGridView.b(iA);
                            width = (viewB.getWidth() / 2) + viewB.getLeft();
                        }
                        if (jLongValue2 > item2.longValue()) {
                            width2 = (iMin + 1) % month.e == 0 ? materialCalendarGridView.getWidth() : !zI ? materialCalendarGridView.b(iMin).getRight() : materialCalendarGridView.b(iMin).getLeft();
                            iA2 = iMin;
                        } else {
                            calendar.setTimeInMillis(jLongValue2);
                            iA2 = oVar.a() + (calendar.get(5) - 1);
                            View viewB2 = materialCalendarGridView.b(iA2);
                            width2 = (viewB2.getWidth() / 2) + viewB2.getLeft();
                        }
                        int itemId = (int) oVar.getItemId(iA);
                        int itemId2 = (int) oVar.getItemId(iA2);
                        while (itemId <= itemId2) {
                            int numColumns = materialCalendarGridView.getNumColumns() * itemId;
                            o oVar2 = oVar;
                            int numColumns2 = (materialCalendarGridView.getNumColumns() + numColumns) - 1;
                            View viewB3 = materialCalendarGridView.b(numColumns);
                            int top = viewB3.getTop() + ((Rect) eaVar.a.b).top;
                            Iterator it2 = it;
                            int bottom = viewB3.getBottom() - ((Rect) eaVar.a.b).bottom;
                            if (zI) {
                                int i2 = iA2 > numColumns2 ? 0 : width2;
                                int width4 = numColumns > iA ? getWidth() : width;
                                i = i2;
                                width3 = width4;
                            } else {
                                i = numColumns > iA ? 0 : width;
                                width3 = iA2 > numColumns2 ? getWidth() : width2;
                            }
                            canvas.drawRect(i, top, width3, bottom, eaVar.h);
                            itemId++;
                            materialCalendarGridView = this;
                            oVar = oVar2;
                            it = it2;
                        }
                    }
                }
            }
            materialCalendarGridView = this;
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        if (!z) {
            super.onFocusChanged(false, i, rect);
            return;
        }
        if (i == 33) {
            setSelection(((o) super.getAdapter()).c());
        } else if (i == 130) {
            setSelection(((o) super.getAdapter()).a());
        } else {
            super.onFocusChanged(true, i, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!super.onKeyDown(i, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= ((o) super.getAdapter()).a()) {
            return true;
        }
        if (19 != i) {
            return false;
        }
        setSelection(((o) super.getAdapter()).a());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public final void onMeasure(int i, int i2) {
        if (!this.c) {
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public final void setSelection(int i) {
        if (i < ((o) super.getAdapter()).a()) {
            super.setSelection(((o) super.getAdapter()).a());
        } else {
            super.setSelection(i);
        }
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = fq0.g(null);
        if (m.G(getContext(), R.attr.windowFullscreen)) {
            setNextFocusLeftId(R$id.cancel_button);
            setNextFocusRightId(R$id.confirm_button);
        }
        this.c = m.G(getContext(), R$attr.nestedScrollable);
        xr0.p(this, new rl(3));
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public final ListAdapter getAdapter() {
        return (o) super.getAdapter();
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (!(listAdapter instanceof o)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), o.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter);
    }
}
