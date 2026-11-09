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
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.TextPaint;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.Log;
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
import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkDatabase_Impl;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.mamimobi.AccessService;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Pair;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public class i6 implements av, dl0, jy0 {
    public static final int[] H = {R.attr.indeterminateDrawable, R.attr.progressDrawable};
    public final /* synthetic */ int E;
    public Object F;
    public Object G;

    public /* synthetic */ i6(int i, Object obj) {
        this.E = i;
        this.F = obj;
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

    public static String b(fq0 fq0Var) {
        fq0Var.getClass();
        return "SHA" + "SHA-512".substring(4);
    }

    public static boolean e(Editable editable, KeyEvent keyEvent, boolean z) {
        t21[] t21VarArr;
        if (KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState())) {
            int selectionStart = Selection.getSelectionStart(editable);
            int selectionEnd = Selection.getSelectionEnd(editable);
            if (selectionStart != -1 && selectionEnd != -1 && selectionStart == selectionEnd && (t21VarArr = (t21[]) editable.getSpans(selectionStart, selectionEnd, t21.class)) != null && t21VarArr.length > 0) {
                for (t21 t21Var : t21VarArr) {
                    int spanStart = editable.getSpanStart(t21Var);
                    int spanEnd = editable.getSpanEnd(t21Var);
                    if ((z && spanStart == selectionStart) || ((!z && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                        editable.delete(spanStart, spanEnd);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean B(CharSequence charSequence, int i, int i2, ht htVar) {
        if (htVar.c == 0) {
            xm xmVar = (xm) this.G;
            ve0 ve0VarB = htVar.b();
            int iA = ve0VarB.a(8);
            if (iA != 0) {
                ((ByteBuffer) ve0VarB.H).getShort(iA + ve0VarB.E);
            }
            xmVar.getClass();
            ThreadLocal threadLocal = xm.b;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = (StringBuilder) threadLocal.get();
            sb.setLength(0);
            while (i < i2) {
                sb.append(charSequence.charAt(i));
                i++;
            }
            TextPaint textPaint = xmVar.a;
            String string = sb.toString();
            int i3 = ek0.a;
            htVar.c = dk0.a(textPaint, string) ? 2 : 1;
        }
        return htVar.c == 2;
    }

    public void C() {
        ((SparseIntArray) this.F).clear();
    }

    public void D(AttributeSet attributeSet, int i) {
        switch (this.E) {
            case 0:
                AbsSeekBar absSeekBar = (AbsSeekBar) this.F;
                ou ouVarP = ou.P(absSeekBar.getContext(), attributeSet, H, i);
                Drawable drawableB = ouVarP.B(0);
                if (drawableB != null) {
                    if (drawableB instanceof AnimationDrawable) {
                        AnimationDrawable animationDrawable = (AnimationDrawable) drawableB;
                        int numberOfFrames = animationDrawable.getNumberOfFrames();
                        AnimationDrawable animationDrawable2 = new AnimationDrawable();
                        animationDrawable2.setOneShot(animationDrawable.isOneShot());
                        for (int i2 = 0; i2 < numberOfFrames; i2++) {
                            Drawable drawableQ = Q(animationDrawable.getFrame(i2), true);
                            drawableQ.setLevel(10000);
                            animationDrawable2.addFrame(drawableQ, animationDrawable.getDuration(i2));
                        }
                        animationDrawable2.setLevel(10000);
                        drawableB = animationDrawable2;
                    }
                    absSeekBar.setIndeterminateDrawable(drawableB);
                }
                Drawable drawableB2 = ouVarP.B(1);
                if (drawableB2 != null) {
                    absSeekBar.setProgressDrawable(Q(drawableB2, false));
                }
                ouVarP.S();
                return;
            default:
                TypedArray typedArrayObtainStyledAttributes = ((EditText) this.F).getContext().obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i, 0);
                try {
                    boolean z = true;
                    if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTextView_emojiCompatEnabled)) {
                        z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTextView_emojiCompatEnabled, true);
                        break;
                    }
                    typedArrayObtainStyledAttributes.recycle();
                    O(z);
                    return;
                } catch (Throwable th) {
                    typedArrayObtainStyledAttributes.recycle();
                    throw th;
                }
        }
    }

    public dt E(InputConnection inputConnection, EditorInfo editorInfo) {
        p50 p50Var = (p50) this.G;
        if (inputConnection == null) {
            p50Var.getClass();
            inputConnection = null;
        } else {
            i6 i6Var = (i6) p50Var.F;
            i6Var.getClass();
            if (!(inputConnection instanceof dt)) {
                inputConnection = new dt((EditText) i6Var.F, inputConnection, editorInfo);
            }
        }
        return (dt) inputConnection;
    }

    public void F(l3 l3Var) {
        tb tbVar = (tb) this.F;
        ((ActionMode.Callback) tbVar.a).onDestroyActionMode(tbVar.b(l3Var));
        c6 c6Var = (c6) this.G;
        if (c6Var.a0 != null) {
            c6Var.P.getDecorView().removeCallbacks(c6Var.b0);
        }
        if (c6Var.Z != null) {
            h61 h61Var = c6Var.c0;
            if (h61Var != null) {
                h61Var.b();
            }
            h61 h61VarA = f51.a(c6Var.Z);
            h61VarA.a(0.0f);
            c6Var.c0 = h61VarA;
            h61VarA.d(new s5(2, this));
        }
        c6Var.Y = null;
        ViewGroup viewGroup = c6Var.e0;
        WeakHashMap weakHashMap = f51.a;
        r41.c(viewGroup);
        c6Var.I();
    }

    public boolean G(l3 l3Var, zd0 zd0Var) {
        ViewGroup viewGroup = ((c6) this.G).e0;
        WeakHashMap weakHashMap = f51.a;
        r41.c(viewGroup);
        tb tbVar = (tb) this.F;
        zx0 zx0VarB = tbVar.b(l3Var);
        vv0 vv0Var = (vv0) tbVar.d;
        Menu te0Var = (Menu) vv0Var.getOrDefault(zd0Var, null);
        if (te0Var == null) {
            te0Var = new te0((Context) tbVar.b, zd0Var);
            vv0Var.put(zd0Var, te0Var);
        }
        return ((ActionMode.Callback) tbVar.a).onPrepareActionMode(zx0VarB, te0Var);
    }

    public void H(hz hzVar) {
        int i = hzVar.b;
        Handler handler = (Handler) this.G;
        cw0 cw0Var = (cw0) this.F;
        if (i != 0) {
            handler.post(new wb(cw0Var, i, 0));
        } else {
            handler.post(new h3(4, cw0Var, hzVar.a, false));
        }
    }

    public void I(Bundle bundle) {
        jr0 jr0Var = (jr0) this.F;
        if (!jr0Var.e) {
            jr0Var.a();
        }
        kr0 kr0Var = jr0Var.a;
        if (((z80) kr0Var.getLifecycle()).c.isAtLeast(Lifecycle$State.STARTED)) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + ((z80) kr0Var.getLifecycle()).c).toString());
        }
        if (jr0Var.g) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        Bundle bundleF = null;
        if (bundle != null && bundle.containsKey("androidx.lifecycle.BundlableSavedStateRegistry.key")) {
            bundleF = la0.F("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle);
        }
        jr0Var.f = bundleF;
        jr0Var.g = true;
    }

    public void J(Bundle bundle) {
        jr0 jr0Var = (jr0) this.F;
        Bundle bundleH = j31.h((Pair[]) Arrays.copyOf(new Pair[0], 0));
        Bundle bundle2 = jr0Var.f;
        if (bundle2 != null) {
            bundleH.putAll(bundle2);
        }
        synchronized (jr0Var.c) {
            for (Map.Entry entry : jr0Var.d.entrySet()) {
                String str = (String) entry.getKey();
                Bundle bundleA = ((ir0) entry.getValue()).a();
                t40.n(str, "key");
                bundleH.putBundle(str, bundleA);
            }
        }
        if (bundleH.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundleH);
    }

    public void K(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        Object object = objectInputStream.readObject();
        if (object instanceof Hashtable) {
            this.F = (Hashtable) object;
            this.G = (Vector) objectInputStream.readObject();
        } else {
            x xVar = new x((byte[]) object);
            while (true) {
                d0 d0Var = (d0) xVar.z();
                if (d0Var == null) {
                    return;
                } else {
                    N(d0Var, xVar.z());
                }
            }
        }
    }

    public void L(String str, ir0 ir0Var) {
        t40.n(ir0Var, "provider");
        jr0 jr0Var = (jr0) this.F;
        synchronized (jr0Var.c) {
            if (jr0Var.d.containsKey(str)) {
                throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
            }
            jr0Var.d.put(str, ir0Var);
        }
    }

    public void M() {
        if (!((jr0) this.F).h) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        g5 g5Var = (g5) this.G;
        if (g5Var == null) {
            g5Var = new g5(this);
        }
        this.G = g5Var;
        try {
            n80.class.getDeclaredConstructor(new Class[0]);
            g5 g5Var2 = (g5) this.G;
            if (g5Var2 != null) {
                ((LinkedHashSet) g5Var2.b).add(n80.class.getName());
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class " + n80.class.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
        }
    }

    public void N(d0 d0Var, t tVar) {
        if (((Hashtable) this.F).containsKey(d0Var)) {
            ((Hashtable) this.F).put(d0Var, tVar);
        } else {
            ((Hashtable) this.F).put(d0Var, tVar);
            ((Vector) this.G).addElement(d0Var);
        }
    }

    public void O(boolean z) {
        nt ntVar = (nt) ((i6) ((p50) this.G).F).G;
        if (ntVar.G != z) {
            if (ntVar.F != null) {
                zs zsVarA = zs.a();
                mt mtVar = ntVar.F;
                zsVarA.getClass();
                la0.i(mtVar, "initCallback cannot be null");
                ReentrantReadWriteLock reentrantReadWriteLock = zsVarA.a;
                reentrantReadWriteLock.writeLock().lock();
                try {
                    zsVarA.b.remove(mtVar);
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
            ntVar.G = z;
            if (z) {
                nt.a(ntVar.E, zs.a().b());
            }
        }
    }

    public void P(int i, int i2, int i3, int i4) {
        CardView cardView = (CardView) this.G;
        cardView.H.set(i, i2, i3, i4);
        Rect rect = cardView.G;
        super/*android.view.View*/.setPadding(i + rect.left, i2 + rect.top, i3 + rect.right, i4 + rect.bottom);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Drawable Q(Drawable drawable, boolean z) {
        if (drawable instanceof g91) {
            ((h91) ((g91) drawable)).getClass();
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i = 0; i < numberOfLayers; i++) {
                    int id = layerDrawable.getId(i);
                    drawableArr[i] = Q(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
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
                if (((Bitmap) this.G) == null) {
                    this.G = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
            }
        }
        return drawable;
    }

    public void R() {
        Integer num;
        tg tgVar = (tg) this.G;
        ArrayList arrayList = tgVar.d;
        String str = (String) this.F;
        if (!arrayList.contains(str) && (num = (Integer) tgVar.b.remove(str)) != null) {
            tgVar.a.remove(num);
        }
        tgVar.e.remove(str);
        HashMap map = tgVar.f;
        if (map.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + map.get(str));
            map.remove(str);
        }
        Bundle bundle = tgVar.g;
        if (bundle.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + bundle.getParcelable(str));
            bundle.remove(str);
        }
        if (tgVar.c.get(str) != null) {
            throw new ClassCastException();
        }
    }

    public void S(ObjectOutputStream objectOutputStream) throws IOException {
        if (((Vector) this.G).size() == 0) {
            objectOutputStream.writeObject(new Hashtable());
            objectOutputStream.writeObject(new Vector());
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        p50 p50Var = new p50(2, byteArrayOutputStream);
        Enumeration enumerationElements = ((Vector) this.G).elements();
        while (enumerationElements.hasMoreElements()) {
            d0 d0VarS = d0.s(enumerationElements.nextElement());
            if (d0VarS == null) {
                throw new IOException("null object detected");
            }
            d0VarS.h(p50Var, true);
            t tVar = (t) ((Hashtable) this.F).get(d0VarS);
            if (tVar == null) {
                throw new IOException("null object detected");
            }
            tVar.b().h(p50Var, true);
        }
        objectOutputStream.writeObject(byteArrayOutputStream.toByteArray());
    }

    @Override // defpackage.dl0
    public el0 a(el0 el0Var) {
        l21 l21Var;
        iw iwVar = el0Var instanceof iw ? (iw) el0Var : null;
        xq xqVar = (xq) this.F;
        BigInteger bigInteger = xqVar.d;
        int iJ = bigInteger == null ? xqVar.j() + 1 : bigInteger.bitLength();
        int i = iJ > 250 ? 6 : 5;
        int i2 = 1 << i;
        if (iwVar != null && (l21Var = iwVar.b) != null && l21Var.P() >= i2) {
            return iwVar;
        }
        int i3 = ((iJ + i) - 1) / i;
        or[] orVarArr = new or[i + 1];
        orVarArr[0] = (or) this.G;
        for (int i4 = 1; i4 < i; i4++) {
            orVarArr[i4] = orVarArr[i4 - 1].t(i3);
        }
        orVarArr[i] = orVarArr[0].s(orVarArr[1]);
        xqVar.m(orVarArr);
        or[] orVarArr2 = new or[i2];
        orVarArr2[0] = orVarArr[0];
        for (int i5 = i - 1; i5 >= 0; i5--) {
            or orVar = orVarArr[i5];
            int i6 = 1 << i5;
            for (int i7 = i6; i7 < i2; i7 += i6 << 1) {
                orVarArr2[i7] = orVarArr2[i7 - i6].a(orVar);
            }
        }
        xqVar.m(orVarArr2);
        iw iwVar2 = new iw();
        iwVar2.a = null;
        iwVar2.b = null;
        iwVar2.c = -1;
        iwVar2.b = xqVar.b(orVarArr2, i2);
        iwVar2.a = orVarArr[i];
        iwVar2.c = i;
        return iwVar2;
    }

    @Override // defpackage.av
    public int c() {
        return ((ExtendedFloatingActionButton) this.G).i0;
    }

    public Bundle d(String str) {
        jr0 jr0Var = (jr0) this.F;
        if (!jr0Var.g) {
            throw new IllegalStateException("You can 'consumeRestoredStateForKey' only after the corresponding component has moved to the 'CREATED' state");
        }
        Bundle bundle = jr0Var.f;
        if (bundle == null) {
            return null;
        }
        Bundle bundleF = bundle.containsKey(str) ? la0.F(str, bundle) : null;
        bundle.remove(str);
        if (bundle.isEmpty()) {
            jr0Var.f = null;
        }
        return bundleF;
    }

    public void f(boolean z) {
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.f(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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

    @Override // defpackage.av
    public int g() {
        return ((ExtendedFloatingActionButton) this.G).h0;
    }

    @Override // defpackage.av
    public int getHeight() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) this.G;
        int i = extendedFloatingActionButton.p0;
        ExtendedFloatingActionButton extendedFloatingActionButton2 = (ExtendedFloatingActionButton) ((z8) this.F).F;
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

    @Override // defpackage.av
    public int getWidth() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) this.G;
        boolean z = extendedFloatingActionButton.getParent() instanceof View;
        z8 z8Var = (z8) this.F;
        if (!z) {
            return z8Var.getWidth();
        }
        View view = (View) extendedFloatingActionButton.getParent();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null || layoutParams.width != -2) {
            return (view.getWidth() - ((!(extendedFloatingActionButton.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) || (marginLayoutParams = (ViewGroup.MarginLayoutParams) extendedFloatingActionButton.getLayoutParams()) == null) ? 0 : marginLayoutParams.leftMargin + marginLayoutParams.rightMargin)) - (view.getPaddingRight() + view.getPaddingLeft());
        }
        return z8Var.getWidth();
    }

    public void h(boolean z) {
        c cVar = (c) this.G;
        FragmentActivity fragmentActivity = cVar.o.c0;
        vz vzVar = cVar.q;
        if (vzVar != null) {
            vzVar.i().l.h(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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

    public void i(boolean z) {
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.i(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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

    @Override // defpackage.jy0
    public String j() {
        return (String) this.F;
    }

    public void k(boolean z) {
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.k(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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

    @Override // defpackage.jy0
    public void l(iy0 iy0Var) {
        ma0.h(iy0Var, (Object[]) this.G);
    }

    public void m(boolean z) {
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.m(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.n(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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
        c cVar = (c) this.G;
        FragmentActivity fragmentActivity = cVar.o.c0;
        vz vzVar = cVar.q;
        if (vzVar != null) {
            vzVar.i().l.o(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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

    @Override // defpackage.av
    public ViewGroup.LayoutParams p() {
        int i = ((ExtendedFloatingActionButton) this.G).p0;
        if (i == 0) {
            i = -2;
        }
        return new ViewGroup.LayoutParams(-1, i);
    }

    public void q(boolean z) {
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.q(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.r(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.s(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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

    public void t(boolean z) {
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.t(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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

    public String toString() {
        switch (this.E) {
            case 10:
                return ((Map) this.F).toString();
            default:
                return super.toString();
        }
    }

    public void u(boolean z) {
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.u(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.v(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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
        vz vzVar = ((c) this.G).q;
        if (vzVar != null) {
            vzVar.i().l.w(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.F).iterator();
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

    /* JADX WARN: Removed duplicated region for block: B:46:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public defpackage.qi0 x(defpackage.m21 r7) {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.i6.x(m21):qi0");
    }

    public t y(d0 d0Var) {
        return (t) ((Hashtable) this.F).get(d0Var);
    }

    public KeyListener z(KeyListener keyListener) {
        if (keyListener instanceof NumberKeyListener) {
            return keyListener;
        }
        ((i6) ((p50) this.G).F).getClass();
        if (keyListener instanceof gt) {
            return keyListener;
        }
        if (keyListener == null) {
            return null;
        }
        return keyListener instanceof NumberKeyListener ? keyListener : new gt(keyListener);
    }

    public /* synthetic */ i6(int i, Object obj, Object obj2, boolean z) {
        this.E = i;
        this.F = obj;
        this.G = obj2;
    }

    public /* synthetic */ i6(int i, boolean z) {
        this.E = i;
    }

    public /* synthetic */ i6(Object obj, int i, Object obj2) {
        this.E = i;
        this.G = obj;
        this.F = obj2;
    }

    public i6(jr0 jr0Var) {
        this.E = 27;
        this.F = jr0Var;
        this.G = new i6(26, jr0Var);
    }

    public i6(AccessService accessService, i6 i6Var) {
        this.E = 23;
        this.F = (ConnectivityManager) accessService.getSystemService("connectivity");
        this.G = new rh0(0, i6Var);
    }

    public i6(String str, Object[] objArr) {
        this.E = 28;
        t40.n(str, "query");
        this.F = str;
        this.G = objArr;
    }

    public i6(WorkDatabase_Impl workDatabase_Impl) {
        this.E = 21;
        this.F = workDatabase_Impl;
        Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        t40.m(setNewSetFromMap, "newSetFromMap(IdentityHashMap())");
        this.G = setNewSetFromMap;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public i6(String str) {
        this(str, (Object[]) null);
        this.E = 28;
        t40.n(str, "query");
    }

    public i6(EditText editText, int i) {
        this.E = i;
        switch (i) {
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                this.F = editText;
                nt ntVar = new nt(editText);
                this.G = ntVar;
                editText.addTextChangedListener(ntVar);
                if (ct.b == null) {
                    synchronized (ct.a) {
                        try {
                            if (ct.b == null) {
                                ct ctVar = new ct();
                                try {
                                    ct.c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, ct.class.getClassLoader());
                                    break;
                                } catch (Throwable unused) {
                                }
                                ct.b = ctVar;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                editText.setEditableFactory(ct.b);
                return;
            default:
                this.F = editText;
                this.G = new p50(editText);
                return;
        }
    }

    public i6(c cVar) {
        this.E = 18;
        this.F = new CopyOnWriteArrayList();
        this.G = cVar;
    }

    public i6(Runnable runnable) {
        this.E = 22;
        this.G = new CopyOnWriteArrayList();
        new HashMap();
        this.F = runnable;
    }

    public i6(tb tbVar, di diVar, xm xmVar) {
        this.E = 12;
        this.F = tbVar;
        this.G = xmVar;
    }

    public i6(ArrayList arrayList, ArrayList arrayList2) {
        this.E = 19;
        int size = arrayList.size();
        this.F = new int[size];
        this.G = new float[size];
        for (int i = 0; i < size; i++) {
            ((int[]) this.F)[i] = ((Integer) arrayList.get(i)).intValue();
            ((float[]) this.G)[i] = ((Float) arrayList2.get(i)).floatValue();
        }
    }

    public i6(int i, int i2) {
        this.E = 19;
        this.F = new int[]{i, i2};
        this.G = new float[]{0.0f, 1.0f};
    }

    public i6(int i, int i2, int i3) {
        this.E = 19;
        this.F = new int[]{i, i2, i3};
        this.G = new float[]{0.0f, 0.5f, 1.0f};
    }

    public i6(Animation animation) {
        this.E = 17;
        this.F = animation;
        this.G = null;
    }

    public i6(Animator animator) {
        this.E = 17;
        this.F = null;
        this.G = animator;
    }

    public i6(CardView cardView) {
        this.E = 8;
        this.G = cardView;
    }

    public i6(v70 v70Var, v70 v70Var2) {
        this.E = 9;
        if (v70Var.a <= v70Var2.a) {
            this.F = v70Var;
            this.G = v70Var2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public i6(int i) {
        this.E = i;
        switch (i) {
            case 10:
                Map map = Collections.EMPTY_MAP;
                this.G = zn0.a;
                this.F = map;
                break;
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                this.F = new SparseIntArray();
                this.G = new SparseIntArray();
                break;
            case 24:
                Hashtable hashtable = new Hashtable();
                Vector vector = new Vector();
                this.F = hashtable;
                this.G = vector;
                break;
            case 29:
                this.F = new gq0(Uuid.SIZE_BITS);
                this.G = new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
                break;
            default:
                this.F = new Rect();
                this.G = new Rect();
                break;
        }
    }
}
