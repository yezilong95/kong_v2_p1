package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.R$styleable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.zn;
import io.nats.client.support.NatsConstants;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c extends ViewGroup.MarginLayoutParams {
    public CoordinatorLayout.Behavior a;
    public boolean b;
    public final int c;
    public int d;
    public final int e;
    public final int f;
    public final int g;
    public int h;
    public int i;
    public int j;
    public View k;
    public View l;
    public boolean m;
    public boolean n;
    public boolean o;
    public final Rect p;

    public c() {
        super(-2, -2);
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.p = new Rect();
    }

    public final boolean a(int i) {
        if (i == 0) {
            return this.m;
        }
        if (i != 1) {
            return false;
        }
        return this.n;
    }

    public c(Context context, AttributeSet attributeSet) {
        CoordinatorLayout.Behavior behavior;
        super(context, attributeSet);
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.p = new Rect();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout_Layout);
        this.c = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
        this.f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_Layout_layout_anchor, -1);
        this.d = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
        this.e = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_keyline, -1);
        this.g = typedArrayObtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
        this.h = typedArrayObtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(R$styleable.CoordinatorLayout_Layout_layout_behavior);
        this.b = zHasValue;
        if (zHasValue) {
            String string = typedArrayObtainStyledAttributes.getString(R$styleable.CoordinatorLayout_Layout_layout_behavior);
            String str = CoordinatorLayout.a0;
            if (TextUtils.isEmpty(string)) {
                behavior = null;
            } else {
                if (string.startsWith(NatsConstants.DOT)) {
                    string = context.getPackageName() + string;
                } else if (string.indexOf(46) < 0) {
                    String str2 = CoordinatorLayout.a0;
                    if (!TextUtils.isEmpty(str2)) {
                        string = str2 + '.' + string;
                    }
                }
                try {
                    ThreadLocal threadLocal = CoordinatorLayout.c0;
                    Map map = (Map) threadLocal.get();
                    if (map == null) {
                        map = new HashMap();
                        threadLocal.set(map);
                    }
                    Constructor<?> constructor = (Constructor) map.get(string);
                    if (constructor == null) {
                        constructor = Class.forName(string, false, context.getClassLoader()).getConstructor(CoordinatorLayout.b0);
                        constructor.setAccessible(true);
                        map.put(string, constructor);
                    }
                    behavior = (CoordinatorLayout.Behavior) constructor.newInstance(context, attributeSet);
                } catch (Exception e) {
                    throw new RuntimeException(zn.v("Could not inflate Behavior subclass ", string), e);
                }
            }
            this.a = behavior;
        }
        typedArrayObtainStyledAttributes.recycle();
        CoordinatorLayout.Behavior behavior2 = this.a;
        if (behavior2 != null) {
            behavior2.g(this);
        }
    }

    public c(c cVar) {
        super((ViewGroup.MarginLayoutParams) cVar);
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.p = new Rect();
    }

    public c(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.p = new Rect();
    }

    public c(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.p = new Rect();
    }
}
