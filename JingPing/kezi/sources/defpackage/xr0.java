package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.R$id;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class xr0 {
    public static WeakHashMap a = null;
    public static Field b = null;
    public static boolean c = false;
    public static final int[] d = {R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};
    public static final gr0 e = new gr0();
    public static final ir0 f = new ir0();

    public static bt0 a(View view) {
        if (a == null) {
            a = new WeakHashMap();
        }
        bt0 bt0Var = (bt0) a.get(view);
        if (bt0Var != null) {
            return bt0Var;
        }
        bt0 bt0Var2 = new bt0(view);
        a.put(view, bt0Var2);
        return bt0Var2;
    }

    public static xu0 b(View view, xu0 xu0Var) {
        WindowInsets windowInsetsG = xu0Var.g();
        if (windowInsetsG != null) {
            WindowInsets windowInsetsA = jr0.a(view, windowInsetsG);
            if (!windowInsetsA.equals(windowInsetsG)) {
                return xu0.h(view, windowInsetsA);
            }
        }
        return xu0Var;
    }

    public static boolean c(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList = wr0.d;
        wr0 wr0Var = (wr0) view.getTag(R$id.tag_unhandled_key_event_manager);
        if (wr0Var == null) {
            wr0Var = new wr0();
            wr0Var.a = null;
            wr0Var.b = null;
            wr0Var.c = null;
            view.setTag(R$id.tag_unhandled_key_event_manager, wr0Var);
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap weakHashMap = wr0Var.a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList2 = wr0.d;
            if (!arrayList2.isEmpty()) {
                synchronized (arrayList2) {
                    try {
                        if (wr0Var.a == null) {
                            wr0Var.a = new WeakHashMap();
                        }
                        for (int size = arrayList2.size() - 1; size >= 0; size--) {
                            ArrayList arrayList3 = wr0.d;
                            View view2 = (View) ((WeakReference) arrayList3.get(size)).get();
                            if (view2 == null) {
                                arrayList3.remove(size);
                            } else {
                                wr0Var.a.put(view2, Boolean.TRUE);
                                for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                    wr0Var.a.put((View) parent, Boolean.TRUE);
                                }
                            }
                        }
                    } finally {
                    }
                }
            }
        }
        View viewA = wr0Var.a(view);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (viewA != null && !KeyEvent.isModifierKey(keyCode)) {
                if (wr0Var.b == null) {
                    wr0Var.b = new SparseArray();
                }
                wr0Var.b.put(keyCode, new WeakReference(viewA));
            }
        }
        return viewA != null;
    }

    public static View.AccessibilityDelegate d(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return rr0.a(view);
        }
        if (c) {
            return null;
        }
        if (b == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                b = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                c = true;
                return null;
            }
        }
        try {
            Object obj = b.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            c = true;
            return null;
        }
    }

    public static CharSequence e(View view) {
        Object tag;
        int i = R$id.tag_accessibility_pane_title;
        if (Build.VERSION.SDK_INT >= 28) {
            tag = qr0.b(view);
        } else {
            tag = view.getTag(i);
            if (!CharSequence.class.isInstance(tag)) {
                tag = null;
            }
        }
        return (CharSequence) tag;
    }

    public static ArrayList f(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R$id.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(R$id.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    public static String[] g(AppCompatEditText appCompatEditText) {
        return Build.VERSION.SDK_INT >= 31 ? tr0.a(appCompatEditText) : (String[]) appCompatEditText.getTag(R$id.tag_on_receive_content_mime_types);
    }

    public static ev0 h(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            return sr0.c(view);
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    return new ev0(window, view);
                }
                return null;
            }
        }
        return null;
    }

    public static void i(View view, int i) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = e(view) != null && view.isShown() && view.getWindowVisibility() == 0;
            if (view.getAccessibilityLiveRegion() != 0 || z) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i);
                if (z) {
                    accessibilityEventObtain.getText().add(e(view));
                    if (view.getImportantForAccessibility() == 0) {
                        view.setImportantForAccessibility(1);
                    }
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (i != 32) {
                if (view.getParent() != null) {
                    try {
                        view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i);
                        return;
                    } catch (AbstractMethodError e2) {
                        Log.e("ViewCompat", view.getParent().getClass().getSimpleName().concat(" does not fully implement ViewParent"), e2);
                        return;
                    }
                }
                return;
            }
            AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain();
            view.onInitializeAccessibilityEvent(accessibilityEventObtain2);
            accessibilityEventObtain2.setEventType(32);
            accessibilityEventObtain2.setContentChangeTypes(i);
            accessibilityEventObtain2.setSource(view);
            view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
            accessibilityEventObtain2.getText().add(e(view));
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
        }
    }

    public static xu0 j(View view, xu0 xu0Var) {
        WindowInsets windowInsetsG = xu0Var.g();
        if (windowInsetsG != null) {
            WindowInsets windowInsetsB = jr0.b(view, windowInsetsG);
            if (!windowInsetsB.equals(windowInsetsG)) {
                return xu0.h(view, windowInsetsB);
            }
        }
        return xu0Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static vg k(View view, vg vgVar) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + vgVar + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return tr0.b(view, vgVar);
        }
        ua0 ua0Var = (ua0) view.getTag(R$id.tag_on_receive_content_listener);
        va0 va0Var = e;
        if (ua0Var == null) {
            if (view instanceof va0) {
                va0Var = (va0) view;
            }
            return va0Var.a(vgVar);
        }
        vg vgVarA = ((sn0) ua0Var).a(view, vgVar);
        if (vgVarA == null) {
            return null;
        }
        if (view instanceof va0) {
            va0Var = (va0) view;
        }
        return va0Var.a(vgVarA);
    }

    public static void l(View view, int i) {
        m(view, i);
        i(view, 0);
    }

    public static void m(View view, int i) {
        ArrayList arrayListF = f(view);
        for (int i2 = 0; i2 < arrayListF.size(); i2++) {
            if (((j0) arrayListF.get(i2)).a() == i) {
                arrayListF.remove(i2);
                return;
            }
        }
    }

    public static void n(View view, j0 j0Var, String str, y0 y0Var) {
        if (y0Var == null && str == null) {
            l(view, j0Var.a());
            return;
        }
        j0 j0Var2 = new j0(null, j0Var.b, str, y0Var, j0Var.c);
        View.AccessibilityDelegate accessibilityDelegateD = d(view);
        b0 b0Var = accessibilityDelegateD == null ? null : accessibilityDelegateD instanceof a0 ? ((a0) accessibilityDelegateD).a : new b0(accessibilityDelegateD);
        if (b0Var == null) {
            b0Var = new b0();
        }
        p(view, b0Var);
        m(view, j0Var2.a());
        f(view).add(j0Var2);
        i(view, 0);
    }

    public static void o(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            rr0.d(view, context, iArr, attributeSet, typedArray, i, 0);
        }
    }

    public static void p(View view, b0 b0Var) {
        if (b0Var == null && (d(view) instanceof a0)) {
            b0Var = new b0();
        }
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
        view.setAccessibilityDelegate(b0Var == null ? null : b0Var.b);
    }

    public static void q(View view, CharSequence charSequence) {
        new hr0(R$id.tag_accessibility_pane_title, CharSequence.class, 8, 28, 1).f(view, charSequence);
        ir0 ir0Var = f;
        if (charSequence == null) {
            ir0Var.b.remove(view);
            view.removeOnAttachStateChangeListener(ir0Var);
            view.getViewTreeObserver().removeOnGlobalLayoutListener(ir0Var);
        } else {
            ir0Var.b.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(ir0Var);
            if (view.isAttachedToWindow()) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(ir0Var);
            }
        }
    }
}
