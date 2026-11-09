package com.google.android.material.datepicker;

import android.text.Editable;
import android.text.TextUtils;
import com.google.android.material.R$string;
import com.google.android.material.datepicker.g;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.h01;
import defpackage.h31;
import defpackage.im;
import defpackage.p7;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends h01 {
    public final TextInputLayout E;
    public final String F;
    public final SimpleDateFormat G;
    public final CalendarConstraints H;
    public final String I;
    public final p7 J;
    public im K;
    public int L = 0;

    public g(String str, SimpleDateFormat simpleDateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.F = str;
        this.G = simpleDateFormat;
        this.E = textInputLayout;
        this.H = calendarConstraints;
        this.I = textInputLayout.getContext().getString(R$string.mtrl_picker_out_of_range);
        this.J = new p7(this, 5, str);
    }

    public abstract void a();

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage()) || editable.length() == 0) {
            return;
        }
        int length = editable.length();
        String str = this.F;
        if (length >= str.length() || editable.length() < this.L) {
            return;
        }
        char cCharAt = str.charAt(editable.length());
        if (Character.isLetterOrDigit(cCharAt)) {
            return;
        }
        editable.append(cCharAt);
    }

    public abstract void b(Long l);

    @Override // defpackage.h01, android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.L = charSequence.length();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [im, java.lang.Runnable] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // defpackage.h01, android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        CalendarConstraints calendarConstraints = this.H;
        TextInputLayout textInputLayout = this.E;
        p7 p7Var = this.J;
        textInputLayout.removeCallbacks(p7Var);
        textInputLayout.removeCallbacks(this.K);
        textInputLayout.setError(null);
        b(null);
        if (TextUtils.isEmpty(charSequence) || charSequence.length() < this.F.length()) {
            return;
        }
        try {
            Date date = this.G.parse(charSequence.toString());
            textInputLayout.setError(null);
            final long time = date.getTime();
            if (calendarConstraints.G.e(time)) {
                Calendar calendarC = h31.c(calendarConstraints.E.E);
                calendarC.set(5, 1);
                if (calendarC.getTimeInMillis() <= time) {
                    Month month = calendarConstraints.F;
                    int i4 = month.I;
                    Calendar calendarC2 = h31.c(month.E);
                    calendarC2.set(5, i4);
                    if (time <= calendarC2.getTimeInMillis()) {
                        b(Long.valueOf(date.getTime()));
                        return;
                    }
                }
            }
            ?? r7 = new Runnable() { // from class: im
                @Override // java.lang.Runnable
                public final void run() {
                    g gVar = this.E;
                    gVar.E.setError(String.format(gVar.I, qm.x(time).replace(' ', (char) 160)));
                    gVar.a();
                }
            };
            this.K = r7;
            textInputLayout.post(r7);
        } catch (ParseException unused) {
            textInputLayout.post(p7Var);
        }
    }
}
