package androidx.appcompat.widget;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import defpackage.b5;
import defpackage.e4;
import defpackage.e5;
import defpackage.f5;
import defpackage.ko0;
import defpackage.o70;
import defpackage.r5;
import defpackage.rp0;
import defpackage.sg;
import defpackage.sn0;
import defpackage.tg;
import defpackage.va0;
import defpackage.vg;
import defpackage.vn0;
import defpackage.xr0;
import defpackage.y5;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatEditText extends EditText implements va0 {
    public final e4 b;
    public final y5 c;
    public final e5 d;
    public final sn0 e;
    public final e5 f;
    public b5 g;

    public AppCompatEditText(Context context) {
        this(context, null);
    }

    private b5 getSuperCaller() {
        if (this.g == null) {
            this.g = new b5(this);
        }
        return this.g;
    }

    @Override // defpackage.va0
    public final vg a(vg vgVar) {
        return this.e.a(this, vgVar);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.a();
        }
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return rp0.Z(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        e4 e4Var = this.b;
        if (e4Var != null) {
            return e4Var.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e4 e4Var = this.b;
        if (e4Var != null) {
            return e4Var.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.c.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.c.e();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        e5 e5Var;
        if (Build.VERSION.SDK_INT >= 28 || (e5Var = this.d) == null) {
            return super.getTextClassifier();
        }
        TextClassifier textClassifier = (TextClassifier) e5Var.d;
        return textClassifier == null ? r5.a((TextView) e5Var.c) : textClassifier;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0057 A[PHI: r1
      0x0057: PHI (r1v10 java.lang.String[]) = (r1v5 java.lang.String[]), (r1v11 java.lang.String[]) binds: [B:30:0x006a, B:22:0x0055] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0071  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.inputmethod.InputConnection onCreateInputConnection(android.view.inputmethod.EditorInfo r8) {
        /*
            r7 = this;
            android.view.inputmethod.InputConnection r0 = super.onCreateInputConnection(r8)
            y5 r1 = r7.c
            r1.getClass()
            defpackage.y5.h(r8, r0, r7)
            defpackage.za0.E(r8, r0, r7)
            if (r0 == 0) goto L77
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 30
            if (r1 > r2) goto L77
            java.lang.String[] r2 = defpackage.xr0.g(r7)
            if (r2 == 0) goto L77
            java.lang.String r3 = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES"
            java.lang.String r4 = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES"
            r5 = 25
            if (r1 < r5) goto L29
            defpackage.rm.a(r8, r2)
            goto L3e
        L29:
            android.os.Bundle r6 = r8.extras
            if (r6 != 0) goto L34
            android.os.Bundle r6 = new android.os.Bundle
            r6.<init>()
            r8.extras = r6
        L34:
            android.os.Bundle r6 = r8.extras
            r6.putStringArray(r4, r2)
            android.os.Bundle r6 = r8.extras
            r6.putStringArray(r3, r2)
        L3e:
            h9 r2 = new h9
            r6 = 2
            r2.<init>(r6, r7)
            if (r1 < r5) goto L4d
            dy r1 = new dy
            r1.<init>(r0, r2)
        L4b:
            r0 = r1
            goto L77
        L4d:
            java.lang.String[] r6 = defpackage.za0.e
            if (r1 < r5) goto L59
            java.lang.String[] r1 = defpackage.rm.b(r8)
            if (r1 == 0) goto L6d
        L57:
            r6 = r1
            goto L6d
        L59:
            android.os.Bundle r1 = r8.extras
            if (r1 != 0) goto L5e
            goto L6d
        L5e:
            java.lang.String[] r1 = r1.getStringArray(r4)
            if (r1 != 0) goto L6a
            android.os.Bundle r1 = r8.extras
            java.lang.String[] r1 = r1.getStringArray(r3)
        L6a:
            if (r1 == 0) goto L6d
            goto L57
        L6d:
            int r1 = r6.length
            if (r1 != 0) goto L71
            goto L77
        L71:
            ey r1 = new ey
            r1.<init>(r0, r2)
            goto L4b
        L77:
            e5 r1 = r7.f
            mn r8 = r1.F(r0, r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatEditText.onCreateInputConnection(android.view.inputmethod.EditorInfo):android.view.inputmethod.InputConnection");
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onDragEvent(DragEvent dragEvent) {
        Activity activity;
        boolean zA = false;
        if (Build.VERSION.SDK_INT < 31 && dragEvent.getLocalState() == null && xr0.g(this) != null) {
            Context context = getContext();
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    activity = null;
                    break;
                }
                if (context instanceof Activity) {
                    activity = (Activity) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (activity == null) {
                Log.i("ReceiveContent", "Can't handle drop: no activity: view=" + this);
            } else if (dragEvent.getAction() != 1 && dragEvent.getAction() == 3) {
                zA = f5.a(dragEvent, this, activity);
            }
        }
        if (zA) {
            return true;
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public final boolean onTextContextMenuItem(int i) {
        sg o70Var;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31 || xr0.g(this) == null || !(i == 16908322 || i == 16908337)) {
            return super.onTextContextMenuItem(i);
        }
        ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
        ClipData primaryClip = clipboardManager == null ? null : clipboardManager.getPrimaryClip();
        if (primaryClip != null && primaryClip.getItemCount() > 0) {
            if (i2 >= 31) {
                o70Var = new o70(primaryClip, 1);
            } else {
                tg tgVar = new tg();
                tgVar.c = primaryClip;
                tgVar.d = 1;
                o70Var = tgVar;
            }
            o70Var.r(i == 16908322 ? 0 : 1);
            xr0.k(this, o70Var.d());
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.f();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.g(i);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(rp0.a0(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        this.f.Q(z);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.f.y(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.j(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        y5 y5Var = this.c;
        y5Var.l(colorStateList);
        y5Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        y5 y5Var = this.c;
        y5Var.m(mode);
        y5Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.g(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        e5 e5Var;
        if (Build.VERSION.SDK_INT >= 28 || (e5Var = this.d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            e5Var.d = textClassifier;
        }
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.editTextStyle);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ko0.a(context);
        vn0.a(this, getContext());
        e4 e4Var = new e4(this);
        this.b = e4Var;
        e4Var.e(attributeSet, i);
        y5 y5Var = new y5(this);
        this.c = y5Var;
        y5Var.f(attributeSet, i);
        y5Var.b();
        e5 e5Var = new e5(4, false);
        e5Var.c = this;
        this.d = e5Var;
        this.e = new sn0();
        e5 e5Var2 = new e5(this, 3);
        this.f = e5Var2;
        e5Var2.E(attributeSet, i);
        KeyListener keyListener = getKeyListener();
        if (keyListener instanceof NumberKeyListener) {
            return;
        }
        boolean zIsFocusable = super.isFocusable();
        boolean zIsClickable = super.isClickable();
        boolean zIsLongClickable = super.isLongClickable();
        int inputType = super.getInputType();
        KeyListener keyListenerY = e5Var2.y(keyListener);
        if (keyListenerY == keyListener) {
            return;
        }
        super.setKeyListener(keyListenerY);
        super.setRawInputType(inputType);
        super.setFocusable(zIsFocusable);
        super.setClickable(zIsClickable);
        super.setLongClickable(zIsLongClickable);
    }
}
