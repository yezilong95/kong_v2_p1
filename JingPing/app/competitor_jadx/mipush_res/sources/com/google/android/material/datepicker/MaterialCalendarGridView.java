package com.google.android.material.datepicker;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.google.android.material.R$attr;
import com.google.android.material.R$id;
import defpackage.f51;
import defpackage.fa0;
import defpackage.fk0;
import defpackage.h31;
import defpackage.i5;
import defpackage.ub;
import defpackage.up;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class MaterialCalendarGridView extends GridView {
    public final Calendar E;
    public final boolean F;

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
        DateSelector dateSelector = oVar.F;
        ub ubVar = oVar.H;
        int iMax = Math.max(oVar.a(), materialCalendarGridView.getFirstVisiblePosition());
        int iMin = Math.min(oVar.c(), materialCalendarGridView.getLastVisiblePosition());
        Long item = oVar.getItem(iMax);
        Long item2 = oVar.getItem(iMin);
        Iterator it = dateSelector.d().iterator();
        while (it.hasNext()) {
            fk0 fk0Var = (fk0) it.next();
            Object obj = fk0Var.a;
            if (obj != null) {
                Object obj2 = fk0Var.b;
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
                        boolean zD0 = fa0.d0(materialCalendarGridView);
                        long jLongValue3 = item.longValue();
                        Calendar calendar = materialCalendarGridView.E;
                        Month month = oVar.E;
                        if (jLongValue < jLongValue3) {
                            width = iMax % month.H == 0 ? 0 : !zD0 ? materialCalendarGridView.b(iMax - 1).getRight() : materialCalendarGridView.b(iMax - 1).getLeft();
                            iA = iMax;
                        } else {
                            calendar.setTimeInMillis(jLongValue);
                            iA = oVar.a() + (calendar.get(5) - 1);
                            View viewB = materialCalendarGridView.b(iA);
                            width = (viewB.getWidth() / 2) + viewB.getLeft();
                        }
                        if (jLongValue2 > item2.longValue()) {
                            width2 = (iMin + 1) % month.H == 0 ? materialCalendarGridView.getWidth() : !zD0 ? materialCalendarGridView.b(iMin).getRight() : materialCalendarGridView.b(iMin).getLeft();
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
                            int top = viewB3.getTop() + ((Rect) ((i5) ubVar.a).c).top;
                            Iterator it2 = it;
                            int bottom = viewB3.getBottom() - ((Rect) ((i5) ubVar.a).c).bottom;
                            if (zD0) {
                                int i2 = iA2 > numColumns2 ? 0 : width2;
                                int width4 = numColumns > iA ? getWidth() : width;
                                i = i2;
                                width3 = width4;
                            } else {
                                i = numColumns > iA ? 0 : width;
                                width3 = iA2 > numColumns2 ? getWidth() : width2;
                            }
                            canvas.drawRect(i, top, width3, bottom, (Paint) ubVar.h);
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
        if (!this.F) {
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
        this.E = h31.g(null);
        if (m.G(getContext(), R.attr.windowFullscreen)) {
            setNextFocusLeftId(R$id.cancel_button);
            setNextFocusRightId(R$id.confirm_button);
        }
        this.F = m.G(getContext(), R$attr.nestedScrollable);
        f51.o(this, new up(3));
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
