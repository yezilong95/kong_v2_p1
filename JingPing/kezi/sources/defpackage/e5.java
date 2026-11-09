package defpackage;

import android.R;
import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.TextPaint;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AbsSeekBar;
import android.widget.EditText;
import androidx.appcompat.R$styleable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.c;
import androidx.lifecycle.Lifecycle$State;
import androidx.recyclerview.widget.n;
import androidx.viewpager.widget.ViewPager;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkDatabase_Impl;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public class e5 implements fp, vl0, ha0 {
    public static final int[] e = {R.attr.indeterminateDrawable, R.attr.progressDrawable};
    public final /* synthetic */ int b;
    public Object c;
    public Object d;

    public /* synthetic */ e5(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    public static int A(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            i3++;
            if (i3 == i2) {
                i4++;
                i3 = 0;
            } else if (i3 > i2) {
                i4++;
                i3 = 1;
            }
        }
        return i3 + 1 > i2 ? i4 + 1 : i4;
    }

    public static boolean h(Editable editable, KeyEvent keyEvent, boolean z) {
        yp0[] yp0VarArr;
        if (KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState())) {
            int selectionStart = Selection.getSelectionStart(editable);
            int selectionEnd = Selection.getSelectionEnd(editable);
            if (selectionStart != -1 && selectionEnd != -1 && selectionStart == selectionEnd && (yp0VarArr = (yp0[]) editable.getSpans(selectionStart, selectionEnd, yp0.class)) != null && yp0VarArr.length > 0) {
                for (yp0 yp0Var : yp0VarArr) {
                    int spanStart = editable.getSpanStart(yp0Var);
                    int spanEnd = editable.getSpanEnd(yp0Var);
                    if ((z && spanStart == selectionStart) || ((!z && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                        editable.delete(spanStart, spanEnd);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean B(CharSequence charSequence, int i, int i2, qn qnVar) {
        if (qnVar.c == 0) {
            ej ejVar = (ej) this.d;
            d70 d70VarB = qnVar.b();
            int iA = d70VarB.a(8);
            if (iA != 0) {
                ((ByteBuffer) d70VarB.e).getShort(iA + d70VarB.b);
            }
            ejVar.getClass();
            ThreadLocal threadLocal = ej.b;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = (StringBuilder) threadLocal.get();
            sb.setLength(0);
            while (i < i2) {
                sb.append(charSequence.charAt(i));
                i++;
            }
            TextPaint textPaint = ejVar.a;
            String string = sb.toString();
            int i3 = kb0.a;
            qnVar.c = jb0.a(textPaint, string) ? 2 : 1;
        }
        return qnVar.c == 2;
    }

    public void C() {
        ((SparseIntArray) this.c).clear();
    }

    public boolean D(View view) {
        fr0 fr0Var = (fr0) this.c;
        int iC = fr0Var.c();
        int iB = fr0Var.b();
        int iE = fr0Var.e(view);
        int iD = fr0Var.d(view);
        er0 er0Var = (er0) this.d;
        er0Var.b = iC;
        er0Var.c = iB;
        er0Var.d = iE;
        er0Var.e = iD;
        er0Var.a = 24579;
        return er0Var.a();
    }

    public void E(AttributeSet attributeSet, int i) {
        switch (this.b) {
            case 0:
                AbsSeekBar absSeekBar = (AbsSeekBar) this.c;
                po poVarN = po.N(absSeekBar.getContext(), attributeSet, e, i);
                Drawable drawableZ = poVarN.z(0);
                if (drawableZ != null) {
                    if (drawableZ instanceof AnimationDrawable) {
                        AnimationDrawable animationDrawable = (AnimationDrawable) drawableZ;
                        int numberOfFrames = animationDrawable.getNumberOfFrames();
                        AnimationDrawable animationDrawable2 = new AnimationDrawable();
                        animationDrawable2.setOneShot(animationDrawable.isOneShot());
                        for (int i2 = 0; i2 < numberOfFrames; i2++) {
                            Drawable drawableS = S(animationDrawable.getFrame(i2), true);
                            drawableS.setLevel(10000);
                            animationDrawable2.addFrame(drawableS, animationDrawable.getDuration(i2));
                        }
                        animationDrawable2.setLevel(10000);
                        drawableZ = animationDrawable2;
                    }
                    absSeekBar.setIndeterminateDrawable(drawableZ);
                }
                Drawable drawableZ2 = poVarN.z(1);
                if (drawableZ2 != null) {
                    absSeekBar.setProgressDrawable(S(drawableZ2, false));
                }
                poVarN.P();
                return;
            default:
                TypedArray typedArrayObtainStyledAttributes = ((EditText) this.c).getContext().obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i, 0);
                try {
                    boolean z = true;
                    if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTextView_emojiCompatEnabled)) {
                        z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTextView_emojiCompatEnabled, true);
                        break;
                    }
                    typedArrayObtainStyledAttributes.recycle();
                    Q(z);
                    return;
                } catch (Throwable th) {
                    typedArrayObtainStyledAttributes.recycle();
                    throw th;
                }
        }
    }

    public mn F(InputConnection inputConnection, EditorInfo editorInfo) {
        o70 o70Var = (o70) this.d;
        if (inputConnection == null) {
            o70Var.getClass();
            inputConnection = null;
        } else {
            e5 e5Var = (e5) o70Var.c;
            e5Var.getClass();
            if (!(inputConnection instanceof mn)) {
                inputConnection = new mn((EditText) e5Var.c, inputConnection, editorInfo);
            }
        }
        return (mn) inputConnection;
    }

    public void G(q1 q1Var) {
        ba baVar = (ba) this.c;
        ((ActionMode.Callback) baVar.a).onDestroyActionMode(baVar.c(q1Var));
        x4 x4Var = (x4) this.d;
        if (x4Var.x != null) {
            x4Var.m.getDecorView().removeCallbacks(x4Var.y);
        }
        if (x4Var.w != null) {
            bt0 bt0Var = x4Var.z;
            if (bt0Var != null) {
                bt0Var.b();
            }
            bt0 bt0VarA = xr0.a(x4Var.w);
            bt0VarA.a(0.0f);
            x4Var.z = bt0VarA;
            bt0VarA.d(new n4(2, this));
        }
        x4Var.v = null;
        ViewGroup viewGroup = x4Var.B;
        WeakHashMap weakHashMap = xr0.a;
        jr0.c(viewGroup);
        x4Var.I();
    }

    public boolean H(q1 q1Var, i60 i60Var) {
        ViewGroup viewGroup = ((x4) this.d).B;
        WeakHashMap weakHashMap = xr0.a;
        jr0.c(viewGroup);
        ba baVar = (ba) this.c;
        ll0 ll0VarC = baVar.c(q1Var);
        fj0 fj0Var = (fj0) baVar.d;
        Menu c70Var = (Menu) fj0Var.getOrDefault(i60Var, null);
        if (c70Var == null) {
            c70Var = new c70((Context) baVar.b, i60Var);
            fj0Var.put(i60Var, c70Var);
        }
        return ((ActionMode.Callback) baVar.a).onPrepareActionMode(ll0VarC, c70Var);
    }

    public void I(ts tsVar) {
        int i = tsVar.b;
        Handler handler = (Handler) this.d;
        d90 d90Var = (d90) this.c;
        if (i == 0) {
            handler.post(new m1(d90Var, tsVar.a, 4, false));
        } else {
            handler.post(new ha(d90Var, i, 0));
        }
    }

    public void J(Bundle bundle) {
        ug0 ug0Var = (ug0) this.c;
        if (!ug0Var.e) {
            ug0Var.a();
        }
        vg0 vg0Var = ug0Var.a;
        if (((w10) vg0Var.getLifecycle()).c.isAtLeast(Lifecycle$State.STARTED)) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + ((w10) vg0Var.getLifecycle()).c).toString());
        }
        if (ug0Var.g) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        Bundle bundleS = null;
        if (bundle != null && bundle.containsKey("androidx.lifecycle.BundlableSavedStateRegistry.key")) {
            bundleS = k9.s("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle);
        }
        ug0Var.f = bundleS;
        ug0Var.g = true;
    }

    public void K(Bundle bundle) {
        ug0 ug0Var = (ug0) this.c;
        Bundle bundleG = rp0.g((Pair[]) Arrays.copyOf(new Pair[0], 0));
        Bundle bundle2 = ug0Var.f;
        if (bundle2 != null) {
            bundleG.putAll(bundle2);
        }
        synchronized (ug0Var.c) {
            for (Map.Entry entry : ug0Var.d.entrySet()) {
                String str = (String) entry.getKey();
                Bundle bundleA = ((sg0) entry.getValue()).a();
                oy.j(str, "key");
                bundleG.putBundle(str, bundleA);
            }
        }
        if (bundleG.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundleG);
    }

    public n90 L(n nVar, int i) {
        fs0 fs0Var;
        n90 n90Var;
        fj0 fj0Var = (fj0) this.c;
        int iE = fj0Var.e(nVar);
        if (iE >= 0 && (fs0Var = (fs0) fj0Var.j(iE)) != null) {
            int i2 = fs0Var.a;
            if ((i2 & i) != 0) {
                int i3 = i2 & (~i);
                fs0Var.a = i3;
                if (i == 4) {
                    n90Var = fs0Var.b;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    n90Var = fs0Var.c;
                }
                if ((i3 & 12) == 0) {
                    fj0Var.i(iE);
                    fs0Var.a = 0;
                    fs0Var.b = null;
                    fs0Var.c = null;
                    fs0.d.c(fs0Var);
                }
                return n90Var;
            }
        }
        return null;
    }

    public void M(String str, sg0 sg0Var) {
        oy.j(sg0Var, "provider");
        ug0 ug0Var = (ug0) this.c;
        synchronized (ug0Var.c) {
            if (ug0Var.d.containsKey(str)) {
                throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
            }
            ug0Var.d.put(str, sg0Var);
        }
    }

    public void N(n nVar) {
        fs0 fs0Var = (fs0) ((fj0) this.c).getOrDefault(nVar, null);
        if (fs0Var == null) {
            return;
        }
        fs0Var.a &= -2;
    }

    public void O(n nVar) {
        t30 t30Var = (t30) this.d;
        int iE = t30Var.e() - 1;
        while (true) {
            if (iE < 0) {
                break;
            }
            if (nVar == t30Var.f(iE)) {
                Object[] objArr = t30Var.d;
                Object obj = objArr[iE];
                Object obj2 = t30.f;
                if (obj != obj2) {
                    objArr[iE] = obj2;
                    t30Var.b = true;
                }
            } else {
                iE--;
            }
        }
        fs0 fs0Var = (fs0) ((fj0) this.c).remove(nVar);
        if (fs0Var != null) {
            fs0Var.a = 0;
            fs0Var.b = null;
            fs0Var.c = null;
            fs0.d.c(fs0Var);
        }
    }

    public void P() {
        if (!((ug0) this.c).h) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        c4 c4Var = (c4) this.d;
        if (c4Var == null) {
            c4Var = new c4(this);
        }
        this.d = c4Var;
        try {
            l10.class.getDeclaredConstructor(new Class[0]);
            c4 c4Var2 = (c4) this.d;
            if (c4Var2 != null) {
                ((LinkedHashSet) c4Var2.b).add(l10.class.getName());
            }
        } catch (NoSuchMethodException e2) {
            throw new IllegalArgumentException("Class " + l10.class.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
        }
    }

    public void Q(boolean z) {
        vn vnVar = (vn) ((e5) ((o70) this.d).c).d;
        if (vnVar.d != z) {
            if (vnVar.c != null) {
                hn hnVarA = hn.a();
                un unVar = vnVar.c;
                hnVarA.getClass();
                k9.h(unVar, "initCallback cannot be null");
                ReentrantReadWriteLock reentrantReadWriteLock = hnVarA.a;
                reentrantReadWriteLock.writeLock().lock();
                try {
                    hnVarA.b.remove(unVar);
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
            vnVar.d = z;
            if (z) {
                vn.a(vnVar.b, hn.a().b());
            }
        }
    }

    public void R(int i, int i2, int i3, int i4) {
        CardView cardView = (CardView) this.d;
        cardView.e.set(i, i2, i3, i4);
        Rect rect = cardView.d;
        super/*android.view.View*/.setPadding(i + rect.left, i2 + rect.top, i3 + rect.right, i4 + rect.bottom);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Drawable S(Drawable drawable, boolean z) {
        if (drawable instanceof iv0) {
            ((jv0) ((iv0) drawable)).getClass();
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i = 0; i < numberOfLayers; i++) {
                    int id = layerDrawable.getId(i);
                    drawableArr[i] = S(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                for (int i2 = 0; i2 < numberOfLayers; i2++) {
                    layerDrawable2.setId(i2, layerDrawable.getId(i2));
                    layerDrawable2.setLayerGravity(i2, layerDrawable.getLayerGravity(i2));
                    layerDrawable2.setLayerWidth(i2, layerDrawable.getLayerWidth(i2));
                    layerDrawable2.setLayerHeight(i2, layerDrawable.getLayerHeight(i2));
                    layerDrawable2.setLayerInsetLeft(i2, layerDrawable.getLayerInsetLeft(i2));
                    layerDrawable2.setLayerInsetRight(i2, layerDrawable.getLayerInsetRight(i2));
                    layerDrawable2.setLayerInsetTop(i2, layerDrawable.getLayerInsetTop(i2));
                    layerDrawable2.setLayerInsetBottom(i2, layerDrawable.getLayerInsetBottom(i2));
                    layerDrawable2.setLayerInsetStart(i2, layerDrawable.getLayerInsetStart(i2));
                    layerDrawable2.setLayerInsetEnd(i2, layerDrawable.getLayerInsetEnd(i2));
                }
                return layerDrawable2;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (((Bitmap) this.d) == null) {
                    this.d = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
            }
        }
        return drawable;
    }

    @Override // defpackage.vl0
    public void a(ul0 ul0Var) {
        nv0.f(ul0Var, (Object[]) this.d);
    }

    public void b(n nVar, n90 n90Var) {
        fj0 fj0Var = (fj0) this.c;
        fs0 fs0VarA = (fs0) fj0Var.getOrDefault(nVar, null);
        if (fs0VarA == null) {
            fs0VarA = fs0.a();
            fj0Var.put(nVar, fs0VarA);
        }
        fs0VarA.c = n90Var;
        fs0VarA.a |= 8;
    }

    @Override // defpackage.fp
    public int c() {
        return ((ExtendedFloatingActionButton) this.d).C;
    }

    public Bundle d(String str) {
        ug0 ug0Var = (ug0) this.c;
        if (!ug0Var.g) {
            throw new IllegalStateException("You can 'consumeRestoredStateForKey' only after the corresponding component has moved to the 'CREATED' state");
        }
        Bundle bundle = ug0Var.f;
        if (bundle == null) {
            return null;
        }
        Bundle bundleS = bundle.containsKey(str) ? k9.s(str, bundle) : null;
        bundle.remove(str);
        if (bundle.isEmpty()) {
            ug0Var.f = null;
        }
        return bundleS;
    }

    @Override // defpackage.ha0
    public xu0 e(View view, xu0 xu0Var) {
        switch (this.b) {
            case 25:
                xu0 xu0VarJ = xr0.j(view, xu0Var);
                if (xu0VarJ.a.m()) {
                    return xu0VarJ;
                }
                int iB = xu0VarJ.b();
                Rect rect = (Rect) this.c;
                rect.left = iB;
                rect.top = xu0VarJ.d();
                rect.right = xu0VarJ.c();
                rect.bottom = xu0VarJ.a();
                ViewPager viewPager = (ViewPager) this.d;
                int childCount = viewPager.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    xu0 xu0VarB = xr0.b(viewPager.getChildAt(i), xu0VarJ);
                    rect.left = Math.min(xu0VarB.b(), rect.left);
                    rect.top = Math.min(xu0VarB.d(), rect.top);
                    rect.right = Math.min(xu0VarB.c(), rect.right);
                    rect.bottom = Math.min(xu0VarB.a(), rect.bottom);
                }
                return xu0VarJ.f(rect.left, rect.top, rect.right, rect.bottom);
            default:
                it0 it0Var = new it0();
                it0 it0Var2 = (it0) this.d;
                it0Var.a = it0Var2.a;
                it0Var.b = it0Var2.b;
                it0Var.c = it0Var2.c;
                it0Var.d = it0Var2.d;
                return ((ht0) this.c).f(view, xu0Var, it0Var);
        }
    }

    @Override // defpackage.fp
    public int f() {
        return ((ExtendedFloatingActionButton) this.d).B;
    }

    @Override // defpackage.fp
    public ViewGroup.LayoutParams g() {
        int i = ((ExtendedFloatingActionButton) this.d).J;
        if (i == 0) {
            i = -2;
        }
        return new ViewGroup.LayoutParams(-1, i);
    }

    @Override // defpackage.fp
    public int getHeight() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) this.d;
        int i = extendedFloatingActionButton.J;
        ExtendedFloatingActionButton extendedFloatingActionButton2 = ((bp) this.c).c;
        if (i != -1) {
            return (i == 0 || i == -2) ? extendedFloatingActionButton2.getMeasuredHeight() : i;
        }
        if (!(extendedFloatingActionButton.getParent() instanceof View)) {
            return extendedFloatingActionButton2.getMeasuredHeight();
        }
        View view = (View) extendedFloatingActionButton.getParent();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null || layoutParams.height != -2) {
            return (view.getHeight() - ((!(extendedFloatingActionButton.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) || (marginLayoutParams = (ViewGroup.MarginLayoutParams) extendedFloatingActionButton.getLayoutParams()) == null) ? 0 : marginLayoutParams.topMargin + marginLayoutParams.bottomMargin)) - (view.getPaddingBottom() + view.getPaddingTop());
        }
        return extendedFloatingActionButton2.getMeasuredHeight();
    }

    @Override // defpackage.fp
    public int getWidth() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) this.d;
        boolean z = extendedFloatingActionButton.getParent() instanceof View;
        bp bpVar = (bp) this.c;
        if (!z) {
            return bpVar.getWidth();
        }
        View view = (View) extendedFloatingActionButton.getParent();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null || layoutParams.width != -2) {
            return (view.getWidth() - ((!(extendedFloatingActionButton.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) || (marginLayoutParams = (ViewGroup.MarginLayoutParams) extendedFloatingActionButton.getLayoutParams()) == null) ? 0 : marginLayoutParams.leftMargin + marginLayoutParams.rightMargin)) - (view.getPaddingRight() + view.getPaddingLeft());
        }
        return bpVar.getWidth();
    }

    public void i(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.i(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void j(boolean z) {
        c cVar = (c) this.d;
        FragmentActivity fragmentActivity = cVar.o.u;
        jt jtVar = cVar.q;
        if (jtVar != null) {
            jtVar.i().l.j(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void k(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.k(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void l(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.l(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void m(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.m(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void n(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.n(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void o(boolean z) {
        c cVar = (c) this.d;
        FragmentActivity fragmentActivity = cVar.o.u;
        jt jtVar = cVar.q;
        if (jtVar != null) {
            jtVar.i().l.o(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void p(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.p(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void q(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.q(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void r(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.r(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void s(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.s(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    @Override // defpackage.vl0
    public String t() {
        return (String) this.c;
    }

    public String toString() {
        switch (this.b) {
            case 27:
                return "Bounds{lower=" + ((jy) this.c) + " upper=" + ((jy) this.d) + "}";
            default:
                return super.toString();
        }
    }

    public void u(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.u(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void v(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.v(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void w(boolean z) {
        jt jtVar = ((c) this.d).q;
        if (jtVar != null) {
            jtVar.i().l.w(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.c).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public View x(int i, int i2, int i3, int i4) {
        fr0 fr0Var = (fr0) this.c;
        int iC = fr0Var.c();
        int iB = fr0Var.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = fr0Var.a(i);
            int iE = fr0Var.e(viewA);
            int iD = fr0Var.d(viewA);
            er0 er0Var = (er0) this.d;
            er0Var.b = iC;
            er0Var.c = iB;
            er0Var.d = iE;
            er0Var.e = iD;
            if (i3 != 0) {
                er0Var.a = i3;
                if (er0Var.a()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                er0Var.a = i4;
                if (er0Var.a()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    public KeyListener y(KeyListener keyListener) {
        if (keyListener instanceof NumberKeyListener) {
            return keyListener;
        }
        ((e5) ((o70) this.d).c).getClass();
        if (keyListener instanceof pn) {
            return keyListener;
        }
        if (keyListener == null) {
            return null;
        }
        return keyListener instanceof NumberKeyListener ? keyListener : new pn(keyListener);
    }

    public sg0 z() {
        sg0 sg0Var;
        ug0 ug0Var = (ug0) this.c;
        synchronized (ug0Var.c) {
            Iterator it = ug0Var.d.entrySet().iterator();
            do {
                sg0Var = null;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                String str = (String) entry.getKey();
                sg0 sg0Var2 = (sg0) entry.getValue();
                if (oy.c(str, "androidx.lifecycle.internal.SavedStateHandlesProvider")) {
                    sg0Var = sg0Var2;
                }
            } while (sg0Var == null);
        }
        return sg0Var;
    }

    public /* synthetic */ e5(int i, boolean z) {
        this.b = i;
    }

    public /* synthetic */ e5(Object obj, int i, Object obj2) {
        this.b = i;
        this.d = obj;
        this.c = obj2;
    }

    public /* synthetic */ e5(Object obj, Object obj2, int i, boolean z) {
        this.b = i;
        this.c = obj;
        this.d = obj2;
    }

    public e5(ug0 ug0Var) {
        this.b = 21;
        this.c = ug0Var;
        this.d = new e5(20, ug0Var);
    }

    public e5(String str, Object[] objArr) {
        this.b = 22;
        oy.j(str, "query");
        this.c = str;
        this.d = objArr;
    }

    public e5(WorkDatabase_Impl workDatabase_Impl) {
        this.b = 18;
        this.c = workDatabase_Impl;
        Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        oy.i(setNewSetFromMap, "newSetFromMap(IdentityHashMap())");
        this.d = setNewSetFromMap;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public e5(String str) {
        this(str, (Object[]) null);
        this.b = 22;
        oy.j(str, "query");
    }

    public e5(EditText editText, int i) {
        this.b = i;
        switch (i) {
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                this.c = editText;
                vn vnVar = new vn(editText);
                this.d = vnVar;
                editText.addTextChangedListener(vnVar);
                if (ln.b == null) {
                    synchronized (ln.a) {
                        try {
                            if (ln.b == null) {
                                ln lnVar = new ln();
                                try {
                                    ln.c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, ln.class.getClassLoader());
                                    break;
                                } catch (Throwable unused) {
                                }
                                ln.b = lnVar;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                editText.setEditableFactory(ln.b);
                return;
            default:
                this.c = editText;
                this.d = new o70(editText);
                return;
        }
    }

    public e5(c cVar) {
        this.b = 15;
        this.c = new CopyOnWriteArrayList();
        this.d = cVar;
    }

    public e5(Runnable runnable) {
        this.b = 19;
        this.d = new CopyOnWriteArrayList();
        new HashMap();
        this.c = runnable;
    }

    public e5(ba baVar, qm qmVar, ej ejVar) {
        this.b = 10;
        this.c = baVar;
        this.d = ejVar;
    }

    public e5(fr0 fr0Var) {
        this.b = 23;
        this.c = fr0Var;
        er0 er0Var = new er0();
        er0Var.a = 0;
        this.d = er0Var;
    }

    public e5(ArrayList arrayList, ArrayList arrayList2) {
        this.b = 16;
        int size = arrayList.size();
        this.c = new int[size];
        this.d = new float[size];
        for (int i = 0; i < size; i++) {
            ((int[]) this.c)[i] = ((Integer) arrayList.get(i)).intValue();
            ((float[]) this.d)[i] = ((Float) arrayList2.get(i)).floatValue();
        }
    }

    public e5(int i, int i2) {
        this.b = 16;
        this.c = new int[]{i, i2};
        this.d = new float[]{0.0f, 1.0f};
    }

    public e5(int i, int i2, int i3) {
        this.b = 16;
        this.c = new int[]{i, i2, i3};
        this.d = new float[]{0.0f, 0.5f, 1.0f};
    }

    public e5(Animation animation) {
        this.b = 14;
        this.c = animation;
        this.d = null;
    }

    public e5(Animator animator) {
        this.b = 14;
        this.c = null;
        this.d = animator;
    }

    public e5(ViewPager viewPager) {
        this.b = 25;
        this.d = viewPager;
        this.c = new Rect();
    }

    public e5(CardView cardView) {
        this.b = 7;
        this.d = cardView;
    }

    public e5(d10 d10Var, d10 d10Var2) {
        this.b = 8;
        if (d10Var.a <= d10Var2.a) {
            this.c = d10Var;
            this.d = d10Var2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public e5(int i) {
        this.b = i;
        switch (i) {
            case 17:
                this.c = new SparseIntArray();
                this.d = new SparseIntArray();
                break;
            case 24:
                this.c = new fj0();
                this.d = new t30();
                break;
            default:
                this.c = new Rect();
                this.d = new Rect();
                break;
        }
    }
}
