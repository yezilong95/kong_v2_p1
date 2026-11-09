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
public abstract class f51 {
    public static WeakHashMap a = null;
    public static Field b = null;
    public static boolean c = false;
    public static final int[] d = {R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};
    public static final o41 e = new o41();
    public static final q41 f = new q41();

    public static h61 a(View view) {
        if (a == null) {
            a = new WeakHashMap();
        }
        h61 h61Var = (h61) a.get(view);
        if (h61Var != null) {
            return h61Var;
        }
        h61 h61Var2 = new h61(view);
        a.put(view, h61Var2);
        return h61Var2;
    }

    public static s81 b(View view, s81 s81Var) {
        WindowInsets windowInsetsG = s81Var.g();
        if (windowInsetsG != null) {
            WindowInsets windowInsetsA = r41.a(view, windowInsetsG);
            if (!windowInsetsA.equals(windowInsetsG)) {
                return s81.h(view, windowInsetsA);
            }
        }
        return s81Var;
    }

    public static boolean c(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList = e51.d;
        e51 e51Var = (e51) view.getTag(R$id.tag_unhandled_key_event_manager);
        if (e51Var == null) {
            e51Var = new e51();
            e51Var.a = null;
            e51Var.b = null;
            e51Var.c = null;
            view.setTag(R$id.tag_unhandled_key_event_manager, e51Var);
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap weakHashMap = e51Var.a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList2 = e51.d;
            if (!arrayList2.isEmpty()) {
                synchronized (arrayList2) {
                    try {
                        if (e51Var.a == null) {
                            e51Var.a = new WeakHashMap();
                        }
                        for (int size = arrayList2.size() - 1; size >= 0; size--) {
                            ArrayList arrayList3 = e51.d;
                            View view2 = (View) ((WeakReference) arrayList3.get(size)).get();
                            if (view2 == null) {
                                arrayList3.remove(size);
                            } else {
                                e51Var.a.put(view2, Boolean.TRUE);
                                for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                    e51Var.a.put((View) parent, Boolean.TRUE);
                                }
                            }
                        }
                    } finally {
                    }
                }
            }
        }
        View viewA = e51Var.a(view);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (viewA != null && !KeyEvent.isModifierKey(keyCode)) {
                if (e51Var.b == null) {
                    e51Var.b = new SparseArray();
                }
                e51Var.b.put(keyCode, new WeakReference(viewA));
            }
        }
        return viewA != null;
    }

    public static View.AccessibilityDelegate d(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return z41.a(view);
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
            tag = y41.b(view);
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
        return Build.VERSION.SDK_INT >= 31 ? b51.a(appCompatEditText) : (String[]) appCompatEditText.getTag(R$id.tag_on_receive_content_mime_types);
    }

    public static z81 h(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            return a51.c(view);
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    return new z81(window, view);
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

    public static s81 j(View view, s81 s81Var) {
        WindowInsets windowInsetsG = s81Var.g();
        if (windowInsetsG != null) {
            WindowInsets windowInsetsB = r41.b(view, windowInsetsG);
            if (!windowInsetsB.equals(windowInsetsG)) {
                return s81.h(view, windowInsetsB);
            }
        }
        return s81Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ki k(View view, ki kiVar) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + kiVar + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return b51.b(view, kiVar);
        }
        jj0 jj0Var = (jj0) view.getTag(R$id.tag_on_receive_content_listener);
        kj0 kj0Var = e;
        if (jj0Var == null) {
            if (view instanceof kj0) {
                kj0Var = (kj0) view;
            }
            return kj0Var.a(kiVar);
        }
        ki kiVarA = ((g01) jj0Var).a(view, kiVar);
        if (kiVarA == null) {
            return null;
        }
        if (view instanceof kj0) {
            kj0Var = (kj0) view;
        }
        return kj0Var.a(kiVarA);
    }

    public static void l(View view, int i) {
        ArrayList arrayListF = f(view);
        for (int i2 = 0; i2 < arrayListF.size(); i2++) {
            if (((e2) arrayListF.get(i2)).a() == i) {
                arrayListF.remove(i2);
                return;
            }
        }
    }

    public static void m(View view, e2 e2Var, String str, u2 u2Var) {
        if (u2Var == null && str == null) {
            l(view, e2Var.a());
            i(view, 0);
            return;
        }
        e2 e2Var2 = new e2(null, e2Var.b, str, u2Var, e2Var.c);
        View.AccessibilityDelegate accessibilityDelegateD = d(view);
        y1 y1Var = accessibilityDelegateD == null ? null : accessibilityDelegateD instanceof x1 ? ((x1) accessibilityDelegateD).a : new y1(accessibilityDelegateD);
        if (y1Var == null) {
            y1Var = new y1();
        }
        o(view, y1Var);
        l(view, e2Var2.a());
        f(view).add(e2Var2);
        i(view, 0);
    }

    public static void n(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            z41.d(view, context, iArr, attributeSet, typedArray, i, 0);
        }
    }

    public static void o(View view, y1 y1Var) {
        if (y1Var == null && (d(view) instanceof x1)) {
            y1Var = new y1();
        }
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
        view.setAccessibilityDelegate(y1Var == null ? null : y1Var.b);
    }

    public static void p(View view, CharSequence charSequence) {
        new p41(R$id.tag_accessibility_pane_title, CharSequence.class, 8, 28, 1).f(view, charSequence);
        q41 q41Var = f;
        if (charSequence == null) {
            q41Var.E.remove(view);
            view.removeOnAttachStateChangeListener(q41Var);
            view.getViewTreeObserver().removeOnGlobalLayoutListener(q41Var);
        } else {
            q41Var.E.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(q41Var);
            if (view.isAttachedToWindow()) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(q41Var);
            }
        }
    }
}
