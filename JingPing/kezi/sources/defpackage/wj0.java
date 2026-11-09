package defpackage;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class wj0 extends SpannableStringBuilder {
    public final Class b;
    public final ArrayList c;

    public wj0(Class cls, CharSequence charSequence) {
        super(charSequence);
        this.c = new ArrayList();
        k9.h(cls, "watcherClass cannot be null");
        this.b = cls;
    }

    public final void a() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.c;
            if (i >= arrayList.size()) {
                return;
            }
            ((vj0) arrayList.get(i)).c.incrementAndGet();
            i++;
        }
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Editable append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    public final void b() {
        e();
        int i = 0;
        while (true) {
            ArrayList arrayList = this.c;
            if (i >= arrayList.size()) {
                return;
            }
            ((vj0) arrayList.get(i)).onTextChanged(this, 0, length(), length());
            i++;
        }
    }

    public final vj0 c(Object obj) {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.c;
            if (i >= arrayList.size()) {
                return null;
            }
            vj0 vj0Var = (vj0) arrayList.get(i);
            if (vj0Var.b == obj) {
                return vj0Var;
            }
            i++;
        }
    }

    public final boolean d(Object obj) {
        if (obj != null) {
            return this.b == obj.getClass();
        }
        return false;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final Editable delete(int i, int i2) {
        super.delete(i, i2);
        return this;
    }

    public final void e() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.c;
            if (i >= arrayList.size()) {
                return;
            }
            ((vj0) arrayList.get(i)).c.decrementAndGet();
            i++;
        }
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public final int getSpanEnd(Object obj) {
        vj0 vj0VarC;
        if (d(obj) && (vj0VarC = c(obj)) != null) {
            obj = vj0VarC;
        }
        return super.getSpanEnd(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public final int getSpanFlags(Object obj) {
        vj0 vj0VarC;
        if (d(obj) && (vj0VarC = c(obj)) != null) {
            obj = vj0VarC;
        }
        return super.getSpanFlags(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public final int getSpanStart(Object obj) {
        vj0 vj0VarC;
        if (d(obj) && (vj0VarC = c(obj)) != null) {
            obj = vj0VarC;
        }
        return super.getSpanStart(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public final Object[] getSpans(int i, int i2, Class cls) {
        if (this.b != cls) {
            return super.getSpans(i, i2, cls);
        }
        vj0[] vj0VarArr = (vj0[]) super.getSpans(i, i2, vj0.class);
        Object[] objArr = (Object[]) Array.newInstance((Class<?>) cls, vj0VarArr.length);
        for (int i3 = 0; i3 < vj0VarArr.length; i3++) {
            objArr[i3] = vj0VarArr[i3].b;
        }
        return objArr;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final Editable insert(int i, CharSequence charSequence) {
        super.insert(i, charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public final int nextSpanTransition(int i, int i2, Class cls) {
        if (cls == null || this.b == cls) {
            cls = vj0.class;
        }
        return super.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public final void removeSpan(Object obj) {
        vj0 vj0VarC;
        if (d(obj)) {
            vj0VarC = c(obj);
            if (vj0VarC != null) {
                obj = vj0VarC;
            }
        } else {
            vj0VarC = null;
        }
        super.removeSpan(obj);
        if (vj0VarC != null) {
            this.c.remove(vj0VarC);
        }
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final /* bridge */ /* synthetic */ Editable replace(int i, int i2, CharSequence charSequence) {
        replace(i, i2, charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public final void setSpan(Object obj, int i, int i2, int i3) {
        if (d(obj)) {
            vj0 vj0Var = new vj0(obj);
            this.c.add(vj0Var);
            obj = vj0Var;
        }
        super.setSpan(obj, i, i2, i3);
    }

    @Override // android.text.SpannableStringBuilder, java.lang.CharSequence
    public final CharSequence subSequence(int i, int i2) {
        return new wj0(this.b, this, i, i2);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final SpannableStringBuilder append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder delete(int i, int i2) {
        super.delete(i, i2);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder insert(int i, CharSequence charSequence) {
        super.insert(i, charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final /* bridge */ /* synthetic */ Editable replace(int i, int i2, CharSequence charSequence, int i3, int i4) {
        replace(i, i2, charSequence, i3, i4);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Appendable append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final Editable insert(int i, CharSequence charSequence, int i2, int i3) {
        super.insert(i, charSequence, i2, i3);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder replace(int i, int i2, CharSequence charSequence) {
        a();
        super.replace(i, i2, charSequence);
        e();
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Editable append(char c) {
        super.append(c);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder insert(int i, CharSequence charSequence, int i2, int i3) {
        super.insert(i, charSequence, i2, i3);
        return this;
    }

    public wj0(Class cls, wj0 wj0Var, int i, int i2) {
        super(wj0Var, i, i2);
        this.c = new ArrayList();
        k9.h(cls, "watcherClass cannot be null");
        this.b = cls;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final SpannableStringBuilder append(char c) {
        super.append(c);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Appendable append(char c) {
        super.append(c);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public final SpannableStringBuilder replace(int i, int i2, CharSequence charSequence, int i3, int i4) {
        a();
        super.replace(i, i2, charSequence, i3, i4);
        e();
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Editable append(CharSequence charSequence, int i, int i2) {
        super.append(charSequence, i, i2);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final SpannableStringBuilder append(CharSequence charSequence, int i, int i2) {
        super.append(charSequence, i, i2);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public final Appendable append(CharSequence charSequence, int i, int i2) {
        super.append(charSequence, i, i2);
        return this;
    }

    @Override // android.text.SpannableStringBuilder
    public final SpannableStringBuilder append(CharSequence charSequence, Object obj, int i) {
        super.append(charSequence, obj, i);
        return this;
    }
}
