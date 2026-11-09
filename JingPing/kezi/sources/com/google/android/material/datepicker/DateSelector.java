package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import defpackage.od;
import defpackage.q50;
import defpackage.z1;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface DateSelector<S> extends Parcelable {
    static void i(EditText... editTextArr) {
        if (editTextArr.length == 0) {
            return;
        }
        od odVar = new od(1, editTextArr);
        for (EditText editText : editTextArr) {
            editText.setOnFocusChangeListener(odVar);
        }
        EditText editText2 = editTextArr[0];
        editText2.postDelayed(new z1(6, editText2), 100L);
    }

    Object a();

    String b(Context context);

    String c(Context context);

    int d(Context context);

    ArrayList e();

    boolean g();

    void h(long j);

    ArrayList j();

    View k(LayoutInflater layoutInflater, ViewGroup viewGroup, CalendarConstraints calendarConstraints, q50 q50Var);
}
