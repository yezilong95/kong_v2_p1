package defpackage;

import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.R$id;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends View.AccessibilityDelegate {
    public final b0 a;

    public a0(b0 b0Var) {
        this.a = b0Var;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.a.a(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        o70 o70VarB = this.a.b(view);
        if (o70VarB != null) {
            return (AccessibilityNodeProvider) o70VarB.c;
        }
        return null;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.a.c(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        Object tag;
        Object tag2;
        Object tag3;
        int iKeyAt;
        int i = 1;
        o0 o0Var = new o0(accessibilityNodeInfo);
        WeakHashMap weakHashMap = xr0.a;
        int i2 = R$id.tag_screen_reader_focusable;
        if (Build.VERSION.SDK_INT >= 28) {
            tag = Boolean.valueOf(qr0.d(view));
        } else {
            tag = view.getTag(i2);
            if (!Boolean.class.isInstance(tag)) {
                tag = null;
            }
        }
        Boolean bool = (Boolean) tag;
        int i3 = 0;
        boolean z = bool != null && bool.booleanValue();
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 28) {
            accessibilityNodeInfo.setScreenReaderFocusable(z);
        } else {
            o0Var.h(1, z);
        }
        int i5 = R$id.tag_accessibility_heading;
        if (Build.VERSION.SDK_INT >= 28) {
            tag2 = Boolean.valueOf(qr0.c(view));
        } else {
            tag2 = view.getTag(i5);
            if (!Boolean.class.isInstance(tag2)) {
                tag2 = null;
            }
        }
        Boolean bool2 = (Boolean) tag2;
        boolean z2 = bool2 != null && bool2.booleanValue();
        if (i4 >= 28) {
            accessibilityNodeInfo.setHeading(z2);
        } else {
            o0Var.h(2, z2);
        }
        CharSequence charSequenceE = xr0.e(view);
        if (i4 >= 28) {
            accessibilityNodeInfo.setPaneTitle(charSequenceE);
        } else {
            accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequenceE);
        }
        int i6 = R$id.tag_state_description;
        if (Build.VERSION.SDK_INT >= 30) {
            tag3 = sr0.b(view);
        } else {
            tag3 = view.getTag(i6);
            if (!CharSequence.class.isInstance(tag3)) {
                tag3 = null;
            }
        }
        CharSequence charSequence = (CharSequence) tag3;
        if (i4 >= 30) {
            k0.c(accessibilityNodeInfo, charSequence);
        } else {
            accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence);
        }
        this.a.d(view, o0Var);
        CharSequence text = accessibilityNodeInfo.getText();
        if (i4 < 26) {
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
            SparseArray sparseArray = (SparseArray) view.getTag(R$id.tag_accessibility_clickable_spans);
            if (sparseArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i7 = 0; i7 < sparseArray.size(); i7++) {
                    if (((WeakReference) sparseArray.valueAt(i7)).get() == null) {
                        arrayList.add(Integer.valueOf(i7));
                    }
                }
                for (int i8 = 0; i8 < arrayList.size(); i8++) {
                    sparseArray.remove(((Integer) arrayList.get(i8)).intValue());
                }
            }
            ClickableSpan[] clickableSpanArr = text instanceof Spanned ? (ClickableSpan[]) ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class) : null;
            if (clickableSpanArr != null && clickableSpanArr.length > 0) {
                accessibilityNodeInfo.getExtras().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", R$id.accessibility_action_clickable_span);
                SparseArray sparseArray2 = (SparseArray) view.getTag(R$id.tag_accessibility_clickable_spans);
                if (sparseArray2 == null) {
                    sparseArray2 = new SparseArray();
                    view.setTag(R$id.tag_accessibility_clickable_spans, sparseArray2);
                }
                int i9 = 0;
                while (i9 < clickableSpanArr.length) {
                    ClickableSpan clickableSpan = clickableSpanArr[i9];
                    int i10 = i3;
                    while (true) {
                        if (i10 >= sparseArray2.size()) {
                            iKeyAt = o0.c;
                            o0.c = iKeyAt + 1;
                            break;
                        } else {
                            if (clickableSpan.equals((ClickableSpan) ((WeakReference) sparseArray2.valueAt(i10)).get())) {
                                iKeyAt = sparseArray2.keyAt(i10);
                                break;
                            }
                            i10 += i;
                        }
                    }
                    sparseArray2.put(iKeyAt, new WeakReference(clickableSpanArr[i9]));
                    ClickableSpan clickableSpan2 = clickableSpanArr[i9];
                    Spanned spanned = (Spanned) text;
                    o0Var.c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan2)));
                    o0Var.c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan2)));
                    o0Var.c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan2)));
                    o0Var.c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(iKeyAt));
                    i9++;
                    i = i;
                    i3 = 0;
                }
            }
        }
        List list = (List) view.getTag(R$id.tag_accessibility_actions);
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        for (int i11 = 0; i11 < list.size(); i11++) {
            o0Var.b((j0) list.get(i11));
        }
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.a.e(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.a.f(viewGroup, view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return this.a.g(view, i, bundle);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void sendAccessibilityEvent(View view, int i) {
        this.a.h(view, i);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.a.i(view, accessibilityEvent);
    }
}
