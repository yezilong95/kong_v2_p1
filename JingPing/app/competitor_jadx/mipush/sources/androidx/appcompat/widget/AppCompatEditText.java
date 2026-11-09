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
import defpackage.c7;
import defpackage.f51;
import defpackage.fa0;
import defpackage.g01;
import defpackage.g6;
import defpackage.hi;
import defpackage.i5;
import defpackage.i6;
import defpackage.ii;
import defpackage.j01;
import defpackage.j6;
import defpackage.ki;
import defpackage.kj0;
import defpackage.p50;
import defpackage.v6;
import defpackage.w01;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatEditText extends EditText implements kj0 {
    public final i5 E;
    public final c7 F;
    public final i6 G;
    public final g01 H;
    public final i6 I;
    public g6 J;

    public AppCompatEditText(Context context) {
        this(context, null);
    }

    private g6 getSuperCaller() {
        if (this.J == null) {
            this.J = new g6(this);
        }
        return this.J;
    }

    @Override // defpackage.kj0
    public final ki a(ki kiVar) {
        return this.H.a(this, kiVar);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.d();
        }
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return fa0.O0(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        i5 i5Var = this.E;
        if (i5Var != null) {
            return i5Var.f();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        i5 i5Var = this.E;
        if (i5Var != null) {
            return i5Var.g();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.F.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.F.e();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        i6 i6Var;
        if (Build.VERSION.SDK_INT >= 28 || (i6Var = this.G) == null) {
            return super.getTextClassifier();
        }
        TextClassifier textClassifier = (TextClassifier) i6Var.G;
        return textClassifier == null ? v6.a((TextView) i6Var.F) : textClassifier;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0057 A[PHI: r1
      0x0057: PHI (r1v10 java.lang.String[]) = (r1v5 java.lang.String[]), (r1v11 java.lang.String[]) binds: [B:30:0x006a, B:22:0x0055] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0071  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.inputmethod.InputConnection onCreateInputConnection(android.view.inputmethod.EditorInfo r8) {
        /*
            r7 = this;
            android.view.inputmethod.InputConnection r0 = super.onCreateInputConnection(r8)
            c7 r1 = r7.F
            r1.getClass()
            defpackage.c7.h(r8, r0, r7)
            defpackage.z91.U(r8, r0, r7)
            if (r0 == 0) goto L77
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 30
            if (r1 > r2) goto L77
            java.lang.String[] r2 = defpackage.f51.g(r7)
            if (r2 == 0) goto L77
            java.lang.String r3 = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES"
            java.lang.String r4 = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES"
            r5 = 25
            if (r1 < r5) goto L29
            defpackage.js.a(r8, r2)
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
            fb r2 = new fb
            r6 = 2
            r2.<init>(r6, r7)
            if (r1 < r5) goto L4d
            g40 r1 = new g40
            r1.<init>(r0, r2)
        L4b:
            r0 = r1
            goto L77
        L4d:
            java.lang.String[] r6 = defpackage.z91.K
            if (r1 < r5) goto L59
            java.lang.String[] r1 = defpackage.js.b(r8)
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
            h40 r1 = new h40
            r1.<init>(r0, r2)
            goto L4b
        L77:
            i6 r1 = r7.I
            dt r8 = r1.E(r0, r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatEditText.onCreateInputConnection(android.view.inputmethod.EditorInfo):android.view.inputmethod.InputConnection");
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onDragEvent(DragEvent dragEvent) {
        Activity activity;
        boolean zA = false;
        if (Build.VERSION.SDK_INT < 31 && dragEvent.getLocalState() == null && f51.g(this) != null) {
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
                zA = j6.a(dragEvent, this, activity);
            }
        }
        if (zA) {
            return true;
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public final boolean onTextContextMenuItem(int i) {
        hi p50Var;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31 || f51.g(this) == null || !(i == 16908322 || i == 16908337)) {
            return super.onTextContextMenuItem(i);
        }
        ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
        ClipData primaryClip = clipboardManager == null ? null : clipboardManager.getPrimaryClip();
        if (primaryClip != null && primaryClip.getItemCount() > 0) {
            if (i2 >= 31) {
                p50Var = new p50(primaryClip, 1);
            } else {
                ii iiVar = new ii();
                iiVar.F = primaryClip;
                iiVar.G = 1;
                p50Var = iiVar;
            }
            p50Var.s(i == 16908322 ? 0 : 1);
            f51.k(this, p50Var.a());
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.i();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.j(i);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(fa0.P0(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        this.I.O(z);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.I.z(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.m(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        c7 c7Var = this.F;
        c7Var.l(colorStateList);
        c7Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        c7 c7Var = this.F;
        c7Var.m(mode);
        c7Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.g(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        i6 i6Var;
        if (Build.VERSION.SDK_INT >= 28 || (i6Var = this.G) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            i6Var.G = textClassifier;
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
        w01.a(context);
        j01.a(this, getContext());
        i5 i5Var = new i5(this);
        this.E = i5Var;
        i5Var.h(attributeSet, i);
        c7 c7Var = new c7(this);
        this.F = c7Var;
        c7Var.f(attributeSet, i);
        c7Var.b();
        i6 i6Var = new i6(6, false);
        i6Var.F = this;
        this.G = i6Var;
        this.H = new g01();
        i6 i6Var2 = new i6(this, 5);
        this.I = i6Var2;
        i6Var2.D(attributeSet, i);
        KeyListener keyListener = getKeyListener();
        if (keyListener instanceof NumberKeyListener) {
            return;
        }
        boolean zIsFocusable = super.isFocusable();
        boolean zIsClickable = super.isClickable();
        boolean zIsLongClickable = super.isLongClickable();
        int inputType = super.getInputType();
        KeyListener keyListenerZ = i6Var2.z(keyListener);
        if (keyListenerZ == keyListener) {
            return;
        }
        super.setKeyListener(keyListenerZ);
        super.setRawInputType(inputType);
        super.setFocusable(zIsFocusable);
        super.setClickable(zIsClickable);
        super.setLongClickable(zIsLongClickable);
    }
}
