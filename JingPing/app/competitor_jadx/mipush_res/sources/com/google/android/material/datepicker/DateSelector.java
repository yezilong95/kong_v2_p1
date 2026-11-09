package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import defpackage.hd0;
import defpackage.jf;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface DateSelector<S> extends Parcelable {
    static void j(EditText... editTextArr) {
        if (editTextArr.length == 0) {
            return;
        }
        jf jfVar = new jf(1, editTextArr);
        for (EditText editText : editTextArr) {
            editText.setOnFocusChangeListener(jfVar);
        }
        EditText editText2 = editTextArr[0];
        editText2.postDelayed(new defpackage.e(12, editText2), 100L);
    }

    String a(Context context);

    String b(Context context);

    int c(Context context);

    ArrayList d();

    boolean f();

    ArrayList g();

    Object h();

    void i(long j);

    View k(LayoutInflater layoutInflater, ViewGroup viewGroup, CalendarConstraints calendarConstraints, hd0 hd0Var);
}
