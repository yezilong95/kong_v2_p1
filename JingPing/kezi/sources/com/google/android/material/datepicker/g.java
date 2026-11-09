package com.google.android.material.datepicker;

import android.text.Editable;
import android.text.TextUtils;
import com.google.android.material.R$string;
import com.google.android.material.datepicker.g;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.fq0;
import defpackage.l6;
import defpackage.mi;
import defpackage.tn0;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends tn0 {
    public final TextInputLayout b;
    public final String c;
    public final SimpleDateFormat d;
    public final CalendarConstraints e;
    public final String f;
    public final l6 g;
    public mi h;
    public int i = 0;

    public g(String str, SimpleDateFormat simpleDateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.c = str;
        this.d = simpleDateFormat;
        this.b = textInputLayout;
        this.e = calendarConstraints;
        this.f = textInputLayout.getContext().getString(R$string.mtrl_picker_out_of_range);
        this.g = new l6(this, 3, str);
    }

    public abstract void a();

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage()) || editable.length() == 0) {
            return;
        }
        int length = editable.length();
        String str = this.c;
        if (length >= str.length() || editable.length() < this.i) {
            return;
        }
        char cCharAt = str.charAt(editable.length());
        if (Character.isDigit(cCharAt)) {
            return;
        }
        editable.append(cCharAt);
    }

    public abstract void b(Long l);

    @Override // defpackage.tn0, android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.i = charSequence.length();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.Runnable, mi] */
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
    @Override // defpackage.tn0, android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        CalendarConstraints calendarConstraints = this.e;
        TextInputLayout textInputLayout = this.b;
        l6 l6Var = this.g;
        textInputLayout.removeCallbacks(l6Var);
        textInputLayout.removeCallbacks(this.h);
        textInputLayout.setError(null);
        b(null);
        if (TextUtils.isEmpty(charSequence) || charSequence.length() < this.c.length()) {
            return;
        }
        try {
            Date date = this.d.parse(charSequence.toString());
            textInputLayout.setError(null);
            final long time = date.getTime();
            if (calendarConstraints.d.f(time)) {
                Calendar calendarC = fq0.c(calendarConstraints.b.b);
                calendarC.set(5, 1);
                if (calendarC.getTimeInMillis() <= time) {
                    Month month = calendarConstraints.c;
                    int i4 = month.f;
                    Calendar calendarC2 = fq0.c(month.b);
                    calendarC2.set(5, i4);
                    if (time <= calendarC2.getTimeInMillis()) {
                        b(Long.valueOf(date.getTime()));
                        return;
                    }
                }
            }
            ?? r7 = new Runnable() { // from class: mi
                @Override // java.lang.Runnable
                public final void run() {
                    g gVar = this.b;
                    gVar.b.setError(String.format(gVar.f, xi.u(time).replace(' ', (char) 160)));
                    gVar.a();
                }
            };
            this.h = r7;
            textInputLayout.post(r7);
        } catch (ParseException unused) {
            textInputLayout.post(l6Var);
        }
    }
}
