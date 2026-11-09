package defpackage;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import kotlin.collections.builders.ListBuilder;
import kotlin.coroutines.EmptyCoroutineContext;
import okio.SegmentedByteString;

/* JADX INFO: loaded from: classes.dex */
public abstract class k9 implements kj0 {
    public static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final nm b = new nm("REHASH");
    public static final p40 c = new p40(null);
    public static final p40 d = new p40(Boolean.TRUE);
    public static final nm e = new nm("CONDITION_FALSE");
    public static final Object f = new Object();
    public static Method g = null;
    public static boolean h = false;
    public static Method i = null;
    public static Method j = null;
    public static boolean k = false;
    public static boolean l = true;
    public static final /* synthetic */ int m = 0;

    public static nh A(nh nhVar, lh lhVar) {
        oy.j(nhVar, "context");
        return nhVar == EmptyCoroutineContext.INSTANCE ? lhVar : (nh) nhVar.fold(lhVar, pe.f);
    }

    public static final Cursor B(pf0 pf0Var, vl0 vl0Var, boolean z) throws IllegalAccessException, IOException, InvocationTargetException {
        oy.j(pf0Var, "db");
        oy.j(vl0Var, "sqLiteQuery");
        Cursor cursorQuery = pf0Var.query(vl0Var, (CancellationSignal) null);
        if (z && (cursorQuery instanceof AbstractWindowedCursor)) {
            AbstractWindowedCursor abstractWindowedCursor = (AbstractWindowedCursor) cursorQuery;
            int count = abstractWindowedCursor.getCount();
            if ((abstractWindowedCursor.hasWindow() ? abstractWindowedCursor.getWindow().getNumRows() : count) < count) {
                oy.j(cursorQuery, "c");
                try {
                    MatrixCursor matrixCursor = new MatrixCursor(cursorQuery.getColumnNames(), cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        Object[] objArr = new Object[cursorQuery.getColumnCount()];
                        int columnCount = cursorQuery.getColumnCount();
                        for (int i2 = 0; i2 < columnCount; i2++) {
                            int type = cursorQuery.getType(i2);
                            if (type == 0) {
                                objArr[i2] = null;
                            } else if (type == 1) {
                                objArr[i2] = Long.valueOf(cursorQuery.getLong(i2));
                            } else if (type == 2) {
                                objArr[i2] = Double.valueOf(cursorQuery.getDouble(i2));
                            } else if (type == 3) {
                                objArr[i2] = cursorQuery.getString(i2);
                            } else {
                                if (type != 4) {
                                    throw new IllegalStateException();
                                }
                                objArr[i2] = cursorQuery.getBlob(i2);
                            }
                        }
                        matrixCursor.addRow(objArr);
                    }
                    cursorQuery.close();
                    return matrixCursor;
                } finally {
                }
            }
        }
        return cursorQuery;
    }

    public static final List C(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex("from");
        int columnIndex4 = cursor.getColumnIndex("to");
        ListBuilder listBuilder = new ListBuilder(0, 1, null);
        while (cursor.moveToNext()) {
            int i2 = cursor.getInt(columnIndex);
            int i3 = cursor.getInt(columnIndex2);
            String string = cursor.getString(columnIndex3);
            oy.i(string, "cursor.getString(fromColumnIndex)");
            String string2 = cursor.getString(columnIndex4);
            oy.i(string2, "cursor.getString(toColumnIndex)");
            listBuilder.add(new sm0(i2, i3, string, string2));
        }
        List listBuild = listBuilder.build();
        oy.j(listBuild, "<this>");
        if (listBuild.size() <= 1) {
            return ae.f0(listBuild);
        }
        Object[] array = listBuild.toArray(new Comparable[0]);
        Comparable[] comparableArr = (Comparable[]) array;
        oy.j(comparableArr, "<this>");
        if (comparableArr.length > 1) {
            Arrays.sort(comparableArr);
        }
        return g7.W(array);
    }

    public static final tm0 D(ql0 ql0Var, String str, boolean z) throws IllegalAccessException, IOException, InvocationTargetException {
        Cursor cursorY = ql0Var.y("PRAGMA index_xinfo(`" + str + "`)");
        try {
            int columnIndex = cursorY.getColumnIndex("seqno");
            int columnIndex2 = cursorY.getColumnIndex("cid");
            int columnIndex3 = cursorY.getColumnIndex("name");
            int columnIndex4 = cursorY.getColumnIndex("desc");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1 && columnIndex4 != -1) {
                TreeMap treeMap = new TreeMap();
                TreeMap treeMap2 = new TreeMap();
                while (cursorY.moveToNext()) {
                    if (cursorY.getInt(columnIndex2) >= 0) {
                        int i2 = cursorY.getInt(columnIndex);
                        String string = cursorY.getString(columnIndex3);
                        String str2 = cursorY.getInt(columnIndex4) > 0 ? "DESC" : "ASC";
                        Integer numValueOf = Integer.valueOf(i2);
                        oy.i(string, "columnName");
                        treeMap.put(numValueOf, string);
                        treeMap2.put(Integer.valueOf(i2), str2);
                    }
                }
                Collection collectionValues = treeMap.values();
                oy.i(collectionValues, "columnsMap.values");
                List listF0 = ae.f0(collectionValues);
                Collection collectionValues2 = treeMap2.values();
                oy.i(collectionValues2, "ordersMap.values");
                tm0 tm0Var = new tm0(str, z, listF0, ae.f0(collectionValues2));
                cursorY.close();
                return tm0Var;
            }
            cursorY.close();
            return null;
        } finally {
        }
    }

    public static int E(Context context, int i2, int i3) {
        TypedValue typedValueJ = nv0.J(context, i2);
        return (typedValueJ == null || typedValueJ.type != 16) ? i3 : typedValueJ.data;
    }

    public static TimeInterpolator F(Context context, int i2, Interpolator interpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return interpolator;
        }
        if (typedValue.type != 3) {
            throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
        }
        String strValueOf = String.valueOf(typedValue.string);
        if (!v(strValueOf, "cubic-bezier") && !v(strValueOf, "path")) {
            return AnimationUtils.loadInterpolator(context, typedValue.resourceId);
        }
        if (!v(strValueOf, "cubic-bezier")) {
            if (v(strValueOf, "path")) {
                return ob0.c(jq0.l(strValueOf.substring(5, strValueOf.length() - 1)));
            }
            throw new IllegalArgumentException("Invalid motion easing type: ".concat(strValueOf));
        }
        String[] strArrSplit = strValueOf.substring(13, strValueOf.length() - 1).split(",");
        if (strArrSplit.length == 4) {
            return ob0.b(r(strArrSplit, 0), r(strArrSplit, 1), r(strArrSplit, 2), r(strArrSplit, 3));
        }
        throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + strArrSplit.length);
    }

    public static final int G(SegmentedByteString segmentedByteString, int i2) {
        int i3;
        int[] directory$okio = segmentedByteString.getDirectory$okio();
        int i4 = i2 + 1;
        int length = segmentedByteString.getSegments$okio().length;
        oy.j(directory$okio, "<this>");
        int i5 = length - 1;
        int i6 = 0;
        while (true) {
            if (i6 <= i5) {
                i3 = (i6 + i5) >>> 1;
                int i7 = directory$okio[i3];
                if (i7 >= i4) {
                    if (i7 <= i4) {
                        break;
                    }
                    i5 = i3 - 1;
                } else {
                    i6 = i3 + 1;
                }
            } else {
                i3 = (-i6) - 1;
                break;
            }
        }
        return i3 >= 0 ? i3 : ~i3;
    }

    public static void H(Outline outline, Path path) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            outline.setPath(path);
            return;
        }
        if (i2 >= 29) {
            try {
                outline.setConvexPath(path);
            } catch (IllegalArgumentException unused) {
            }
        } else if (path.isConvex()) {
            outline.setConvexPath(path);
        }
    }

    public static final int c(char c2) {
        if ('0' <= c2 && c2 < ':') {
            return c2 - '0';
        }
        if ('a' <= c2 && c2 < 'g') {
            return c2 - 'W';
        }
        if ('A' <= c2 && c2 < 'G') {
            return c2 - '7';
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c2);
    }

    public static final void d(long j2, byte[] bArr, int i2, int i3) {
        int i4 = (i3 * 2) + i2;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = wv.a[(int) (255 & j2)];
            bArr[i4 - 1] = (byte) i6;
            i4 -= 2;
            bArr[i4] = (byte) (i6 >> 8);
            j2 >>= 8;
        }
    }

    public static void e(float f2, float[] fArr) {
        if (f2 <= 0.5f) {
            fArr[0] = 1.0f - (f2 * 2.0f);
            fArr[1] = 0.0f;
        } else {
            fArr[0] = 0.0f;
            fArr[1] = (f2 * 2.0f) - 1.0f;
        }
    }

    public static void f(String str, boolean z) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void g(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void h(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static final void i(Closeable closeable, Throwable th) throws IllegalAccessException, IOException, InvocationTargetException {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                nv0.c(th, th2);
            }
        }
    }

    public static int j(int i2, int i3) {
        return je.e(i2, (Color.alpha(i2) * i3) / 255);
    }

    public static Drawable k(Drawable drawable, Drawable drawable2, int i2, int i3) {
        if (drawable == null) {
            return drawable2;
        }
        if (drawable2 == null) {
            return drawable;
        }
        if (i2 == -1 && (i2 = drawable2.getIntrinsicWidth()) == -1) {
            i2 = drawable.getIntrinsicWidth();
        }
        if (i3 == -1 && (i3 = drawable2.getIntrinsicHeight()) == -1) {
            i3 = drawable.getIntrinsicHeight();
        }
        if (i2 > drawable.getIntrinsicWidth() || i3 > drawable.getIntrinsicHeight()) {
            float f2 = i2 / i3;
            if (f2 >= drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight()) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                i3 = (int) (intrinsicWidth / f2);
                i2 = intrinsicWidth;
            } else {
                i3 = drawable.getIntrinsicHeight();
                i2 = (int) (f2 * i3);
            }
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, drawable2});
        layerDrawable.setLayerSize(1, i2, i3);
        layerDrawable.setLayerGravity(1, 17);
        return layerDrawable;
    }

    public static Drawable l(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (drawable == null) {
            return null;
        }
        if (colorStateList != null) {
            drawable = drawable.mutate();
            if (mode != null) {
                hl.i(drawable, mode);
            }
        }
        return drawable;
    }

    public static void m(Canvas canvas, boolean z) {
        Method method;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            if (z) {
                canvas.enableZ();
                return;
            } else {
                canvas.disableZ();
                return;
            }
        }
        if (i2 == 28) {
            throw new IllegalStateException("This method doesn't work on Pie!");
        }
        if (!k) {
            try {
                Method declaredMethod = Canvas.class.getDeclaredMethod("insertReorderBarrier", new Class[0]);
                i = declaredMethod;
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = Canvas.class.getDeclaredMethod("insertInorderBarrier", new Class[0]);
                j = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            k = true;
        }
        if (z) {
            try {
                Method method2 = i;
                if (method2 != null) {
                    method2.invoke(canvas, new Object[0]);
                }
            } catch (IllegalAccessException unused2) {
                return;
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
        if (z || (method = j) == null) {
            return;
        }
        method.invoke(canvas, new Object[0]);
    }

    public static Object n(lh lhVar, Object obj, ru ruVar) {
        oy.j(ruVar, "operation");
        return ruVar.invoke(obj, lhVar);
    }

    public static int[] o(int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 == 16842912) {
                return iArr;
            }
            if (i3 == 0) {
                int[] iArr2 = (int[]) iArr.clone();
                iArr2[i2] = 16842912;
                return iArr2;
            }
        }
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length + 1);
        iArrCopyOf[iArr.length] = 16842912;
        return iArrCopyOf;
    }

    public static int p(Context context, int i2, int i3) {
        TypedValue typedValueJ = nv0.J(context, i2);
        if (typedValueJ == null) {
            return i3;
        }
        int i4 = typedValueJ.resourceId;
        return i4 != 0 ? bh.a(context, i4) : typedValueJ.data;
    }

    public static int q(View view, int i2) {
        Context context = view.getContext();
        TypedValue typedValueL = nv0.L(view.getContext(), i2, view.getClass().getCanonicalName());
        int i3 = typedValueL.resourceId;
        return i3 != 0 ? bh.a(context, i3) : typedValueL.data;
    }

    public static float r(String[] strArr, int i2) {
        float f2 = Float.parseFloat(strArr[i2]);
        if (f2 >= 0.0f && f2 <= 1.0f) {
            return f2;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + f2);
    }

    public static final Bundle s(String str, Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(str);
        if (bundle2 != null) {
            return bundle2;
        }
        throw new IllegalArgumentException(hk.j("No valid saved state was found for the key '", str, "'. It may be missing, null, or not of the expected type. This can occur if the value was saved with a different type or if the saved state was modified unexpectedly."));
    }

    public static eu t(o70 o70Var, SQLiteDatabase sQLiteDatabase) {
        oy.j(o70Var, "refHolder");
        eu euVar = (eu) o70Var.c;
        if (euVar != null && euVar.b.equals(sQLiteDatabase)) {
            return euVar;
        }
        eu euVar2 = new eu(sQLiteDatabase);
        o70Var.c = euVar2;
        return euVar2;
    }

    public static boolean u(int i2) {
        if (i2 == 0) {
            return false;
        }
        ThreadLocal threadLocal = je.a;
        double[] dArr = (double[]) threadLocal.get();
        if (dArr == null) {
            dArr = new double[3];
            threadLocal.set(dArr);
        }
        int iRed = Color.red(i2);
        int iGreen = Color.green(i2);
        int iBlue = Color.blue(i2);
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d2 = ((double) iRed) / 255.0d;
        double dPow = d2 < 0.04045d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
        double d3 = ((double) iGreen) / 255.0d;
        double dPow2 = d3 < 0.04045d ? d3 / 12.92d : Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
        double d4 = ((double) iBlue) / 255.0d;
        double dPow3 = d4 < 0.04045d ? d4 / 12.92d : Math.pow((d4 + 0.055d) / 1.055d, 2.4d);
        dArr[0] = ((0.1805d * dPow3) + (0.3576d * dPow2) + (0.4124d * dPow)) * 100.0d;
        double d5 = ((0.0722d * dPow3) + (0.7152d * dPow2) + (0.2126d * dPow)) * 100.0d;
        dArr[1] = d5;
        dArr[2] = ((dPow3 * 0.9505d) + (dPow2 * 0.1192d) + (dPow * 0.0193d)) * 100.0d;
        return d5 / 100.0d > 0.5d;
    }

    public static boolean v(String str, String str2) {
        return str.startsWith(str2.concat("(")) && str.endsWith(")");
    }

    public static final i w(Object[] objArr) {
        oy.j(objArr, "array");
        return new i(objArr);
    }

    public static int x(int i2, float f2, int i3) {
        return je.c(je.e(i3, Math.round(Color.alpha(i3) * f2)), i2);
    }

    public static nh y(lh lhVar, mh mhVar) {
        oy.j(mhVar, "key");
        return oy.c(lhVar.getKey(), mhVar) ? EmptyCoroutineContext.INSTANCE : lhVar;
    }

    @Override // defpackage.kj0
    public float b(ViewGroup viewGroup, View view) {
        return view.getTranslationY();
    }

    public abstract Rect z();
}
