package defpackage;

import android.R;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcelable;
import android.text.InputFilter;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Base64;
import android.util.Size;
import android.util.SizeF;
import android.util.TypedValue;
import android.util.Xml;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.R$styleable;
import androidx.work.impl.WorkDatabaseVersions;
import com.google.android.material.appbar.MaterialToolbar;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Pair;
import kotlin.jvm.internal.FunctionImpl;
import okio.ByteString;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public abstract class rp0 {
    public static final nm a = new nm("RESUME_TOKEN");
    public static final nm b = new nm("CLOSED_EMPTY");
    public static final yo c = new yo(12);
    public static final l3 d = new l3(8);

    public rp0() {
        new ConcurrentHashMap();
    }

    public static void A() {
        throw new NullPointerException("请在Application中调用init进行数据库工具注册注册");
    }

    public static ImageButton B(Toolbar toolbar) {
        Drawable navigationIcon = toolbar.getNavigationIcon();
        if (navigationIcon == null) {
            return null;
        }
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View childAt = toolbar.getChildAt(i);
            if (childAt instanceof ImageButton) {
                ImageButton imageButton = (ImageButton) childAt;
                if (imageButton.getDrawable() == navigationIcon) {
                    return imageButton;
                }
            }
        }
        return null;
    }

    public static yb0 C(AppCompatTextView appCompatTextView) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            return new yb0(qn0.c(appCompatTextView));
        }
        TextPaint textPaint = new TextPaint(appCompatTextView.getPaint());
        TextDirectionHeuristic textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        int iA = on0.a(appCompatTextView);
        int iD = on0.d(appCompatTextView);
        if (appCompatTextView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            textDirectionHeuristic = TextDirectionHeuristics.LTR;
        } else if (i < 28 || (appCompatTextView.getInputType() & 15) != 3) {
            boolean z = appCompatTextView.getLayoutDirection() == 1;
            switch (appCompatTextView.getTextDirection()) {
                case 2:
                    textDirectionHeuristic = TextDirectionHeuristics.ANYRTL_LTR;
                    break;
                case 3:
                    textDirectionHeuristic = TextDirectionHeuristics.LTR;
                    break;
                case 4:
                    textDirectionHeuristic = TextDirectionHeuristics.RTL;
                    break;
                case 5:
                    textDirectionHeuristic = TextDirectionHeuristics.LOCALE;
                    break;
                case 6:
                    break;
                case 7:
                    textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    break;
                default:
                    if (z) {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    }
                    break;
            }
        } else {
            byte directionality = Character.getDirectionality(qn0.b(pn0.a(appCompatTextView.getTextLocale()))[0].codePointAt(0));
            textDirectionHeuristic = (directionality == 1 || directionality == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
        }
        return new yb0(textPaint, textDirectionHeuristic, iA, iD);
    }

    public static ArrayList D(MaterialToolbar materialToolbar, CharSequence charSequence) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < materialToolbar.getChildCount(); i++) {
            View childAt = materialToolbar.getChildAt(i);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (TextUtils.equals(textView.getText(), charSequence)) {
                    arrayList.add(textView);
                }
            }
        }
        return arrayList;
    }

    public static final void E(nh nhVar, Throwable th) throws IllegalAccessException, InvocationTargetException {
        try {
            d3 d3Var = (d3) nhVar.get(qm.e);
            if (d3Var != null) {
                d3Var.t(th);
            } else {
                za0.t(nhVar, th);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                nv0.c(runtimeException, th);
                th = runtimeException;
            }
            za0.t(nhVar, th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x00c5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.dl F(defpackage.bz r11, defpackage.jz r12, int r13) {
        /*
            Method dump skipped, instruction units count: 232
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rp0.F(bz, jz, int):dl");
    }

    public static boolean H(int i, Object obj) {
        int arity;
        if (obj instanceof qu) {
            if (obj instanceof yu) {
                arity = ((yu) obj).getArity();
            } else if (obj instanceof ou) {
                arity = 0;
            } else if (obj instanceof pu) {
                arity = 1;
            } else if (obj instanceof ru) {
                arity = 2;
            } else if (obj instanceof su) {
                arity = 3;
            } else if (obj instanceof tu) {
                arity = 4;
            } else if (obj instanceof uu) {
                arity = 5;
            } else if (obj instanceof vu) {
                arity = 6;
            } else if (obj instanceof wu) {
                arity = 7;
            } else {
                boolean z = obj instanceof FunctionImpl;
                arity = z ? 8 : z ? 9 : z ? 10 : z ? 11 : z ? 12 : z ? 13 : z ? 14 : z ? 15 : z ? 16 : z ? 17 : z ? 18 : z ? 19 : z ? 20 : z ? 21 : z ? 22 : -1;
            }
            if (arity == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean I(View view) {
        WeakHashMap weakHashMap = xr0.a;
        return view.getLayoutDirection() == 1;
    }

    public static int J(int i, int i2, int i3) throws IOException {
        if ((i2 & 8) != 0) {
            i--;
        }
        if (i3 <= i) {
            return i - i3;
        }
        throw new IOException(hk.f(i3, i, "PROTOCOL_ERROR padding ", " > remaining length "));
    }

    public static void K(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    public static ws N(XmlResourceParser xmlResourceParser, Resources resources) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlResourceParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        xmlResourceParser.require(2, null, "font-family");
        if (!xmlResourceParser.getName().equals("font-family")) {
            X(xmlResourceParser);
            return null;
        }
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.FontFamily);
        String string = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderAuthority);
        String string2 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderPackage);
        String string3 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderQuery);
        int resourceId = typedArrayObtainAttributes.getResourceId(R$styleable.FontFamily_fontProviderCerts, 0);
        int integer = typedArrayObtainAttributes.getInteger(R$styleable.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(R$styleable.FontFamily_fontProviderFetchTimeout, 500);
        String string4 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderSystemFontFamily);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlResourceParser.next() != 3) {
                X(xmlResourceParser);
            }
            return new zs(new o2(string, string2, string3, R(resources, resourceId)), integer, integer2, string4);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlResourceParser.next() != 3) {
            if (xmlResourceParser.getEventType() == 2) {
                if (xmlResourceParser.getName().equals("font")) {
                    TypedArray typedArrayObtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.FontFamilyFont);
                    int i = typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(R$styleable.FontFamilyFont_fontWeight) ? R$styleable.FontFamilyFont_fontWeight : R$styleable.FontFamilyFont_android_fontWeight, 400);
                    boolean z = 1 == typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(R$styleable.FontFamilyFont_fontStyle) ? R$styleable.FontFamilyFont_fontStyle : R$styleable.FontFamilyFont_android_fontStyle, 0);
                    int i2 = typedArrayObtainAttributes2.hasValue(R$styleable.FontFamilyFont_ttcIndex) ? R$styleable.FontFamilyFont_ttcIndex : R$styleable.FontFamilyFont_android_ttcIndex;
                    String string5 = typedArrayObtainAttributes2.getString(typedArrayObtainAttributes2.hasValue(R$styleable.FontFamilyFont_fontVariationSettings) ? R$styleable.FontFamilyFont_fontVariationSettings : R$styleable.FontFamilyFont_android_fontVariationSettings);
                    int i3 = typedArrayObtainAttributes2.getInt(i2, 0);
                    int i4 = typedArrayObtainAttributes2.hasValue(R$styleable.FontFamilyFont_font) ? R$styleable.FontFamilyFont_font : R$styleable.FontFamilyFont_android_font;
                    int resourceId2 = typedArrayObtainAttributes2.getResourceId(i4, 0);
                    String string6 = typedArrayObtainAttributes2.getString(i4);
                    typedArrayObtainAttributes2.recycle();
                    while (xmlResourceParser.next() != 3) {
                        X(xmlResourceParser);
                    }
                    arrayList.add(new ys(i, i3, resourceId2, string6, string5, z));
                } else {
                    X(xmlResourceParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new xs((ys[]) arrayList.toArray(new ys[0]));
    }

    public static PorterDuff.Mode O(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return PorterDuff.Mode.MULTIPLY;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static List R(Resources resources, int i) {
        if (i == 0) {
            return Collections.EMPTY_LIST;
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.EMPTY_LIST;
            }
            ArrayList arrayList = new ArrayList();
            if (vs.a(typedArrayObtainTypedArray, 0) == 1) {
                for (int i2 = 0; i2 < typedArrayObtainTypedArray.length(); i2++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i2, 0);
                    if (resourceId != 0) {
                        String[] stringArray = resources.getStringArray(resourceId);
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArray) {
                            arrayList2.add(Base64.decode(str, 0));
                        }
                        arrayList.add(arrayList2);
                    }
                }
            } else {
                String[] stringArray2 = resources.getStringArray(i);
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : stringArray2) {
                    arrayList3.add(Base64.decode(str2, 0));
                }
                arrayList.add(arrayList3);
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    public static final void S(Object[] objArr, int i, int i2) {
        oy.j(objArr, "<this>");
        while (i < i2) {
            objArr[i] = null;
            i++;
        }
    }

    public static void V(TextView textView, int i) {
        k9.g(i);
        if (Build.VERSION.SDK_INT >= 28) {
            qn0.d(textView, i);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i2 = textView.getIncludeFontPadding() ? fontMetricsInt.top : fontMetricsInt.ascent;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), i + i2, textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void W(TextView textView, int i) {
        k9.g(i);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i2 = textView.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
        }
    }

    public static void X(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        int i = 1;
        while (i > 0) {
            int next = xmlResourceParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }

    public static void Y(Object obj, String str) {
        ClassCastException classCastException = new ClassCastException((obj == null ? "null" : obj.getClass().getName()) + " cannot be cast to " + str);
        oy.M(classCastException, rp0.class.getName());
        throw classCastException;
    }

    public static ActionMode.Callback Z(ActionMode.Callback callback) {
        return (!(callback instanceof rn0) || Build.VERSION.SDK_INT < 26) ? callback : ((rn0) callback).a;
    }

    public static final boolean a(Object[] objArr, int i, int i2, List list) {
        if (i2 == list.size()) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (oy.c(objArr[i + i3], list.get(i3))) {
                }
            }
            return true;
        }
        return false;
    }

    public static ActionMode.Callback a0(ActionMode.Callback callback, TextView textView) {
        int i = Build.VERSION.SDK_INT;
        return (i < 26 || i > 27 || (callback instanceof rn0) || callback == null) ? callback : new rn0(callback, textView);
    }

    public static final String b(Object[] objArr, int i, int i2, m mVar) {
        StringBuilder sb = new StringBuilder((i2 * 3) + 2);
        sb.append("[");
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            Object obj = objArr[i + i3];
            if (obj == mVar) {
                sb.append("(this Collection)");
            } else {
                sb.append(obj);
            }
        }
        sb.append("]");
        String string = sb.toString();
        oy.i(string, "toString(...)");
        return string;
    }

    public static void c(StringBuilder sb, Object obj, pu puVar) {
        if (puVar != null) {
            sb.append((CharSequence) puVar.invoke(obj));
            return;
        }
        if (obj == null ? true : obj instanceof CharSequence) {
            sb.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            sb.append(((Character) obj).charValue());
        } else {
            sb.append((CharSequence) String.valueOf(obj));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:115:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x02da A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x016b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x01c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0153  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c0(android.content.Context r18, java.util.concurrent.Executor r19, defpackage.ic0 r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 748
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rp0.c0(android.content.Context, java.util.concurrent.Executor, ic0, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:178:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0670 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0694  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0697  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x069d  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x06a0  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x06a4  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x06b4  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x06b8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:419:0x06d3 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void d(defpackage.ng r39, defpackage.f20 r40, int r41) {
        /*
            Method dump skipped, instruction units count: 1762
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rp0.d(ng, f20, int):void");
    }

    public static void e(int i, Object obj) {
        if (obj == null || H(i, obj)) {
            return;
        }
        Y(obj, "kotlin.jvm.functions.Function" + i);
        throw null;
    }

    public static void f(long j, p9 p9Var, int i, ArrayList arrayList, int i2, int i3, ArrayList arrayList2) {
        int i4;
        int i5;
        ArrayList arrayList3;
        long j2;
        int i6;
        int i7 = i;
        ArrayList arrayList4 = arrayList;
        ArrayList arrayList5 = arrayList2;
        if (i2 >= i3) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        for (int i8 = i2; i8 < i3; i8++) {
            if (((ByteString) arrayList4.get(i8)).size() < i7) {
                throw new IllegalArgumentException("Failed requirement.");
            }
        }
        ByteString byteString = (ByteString) arrayList.get(i2);
        ByteString byteString2 = (ByteString) arrayList4.get(i3 - 1);
        if (i7 == byteString.size()) {
            int iIntValue = ((Number) arrayList5.get(i2)).intValue();
            int i9 = i2 + 1;
            ByteString byteString3 = (ByteString) arrayList4.get(i9);
            i4 = i9;
            i5 = iIntValue;
            byteString = byteString3;
        } else {
            i4 = i2;
            i5 = -1;
        }
        if (byteString.getByte(i7) == byteString2.getByte(i7)) {
            int iMin = Math.min(byteString.size(), byteString2.size());
            int i10 = 0;
            for (int i11 = i7; i11 < iMin && byteString.getByte(i11) == byteString2.getByte(i11); i11++) {
                i10++;
            }
            long j3 = 4;
            long j4 = (p9Var.c / j3) + j + ((long) 2) + ((long) i10) + 1;
            p9Var.Q(-i10);
            p9Var.Q(i5);
            int i12 = i7 + i10;
            while (i7 < i12) {
                p9Var.Q(byteString.getByte(i7) & 255);
                i7++;
            }
            if (i4 + 1 == i3) {
                if (i12 != ((ByteString) arrayList4.get(i4)).size()) {
                    throw new IllegalStateException("Check failed.");
                }
                p9Var.Q(((Number) arrayList5.get(i4)).intValue());
                return;
            } else {
                p9 p9Var2 = new p9();
                p9Var.Q(((int) ((p9Var2.c / j3) + j4)) * (-1));
                f(j4, p9Var2, i12, arrayList4, i4, i3, arrayList5);
                p9Var.N(p9Var2);
                return;
            }
        }
        int i13 = 1;
        for (int i14 = i4 + 1; i14 < i3; i14++) {
            if (((ByteString) arrayList4.get(i14 - 1)).getByte(i7) != ((ByteString) arrayList4.get(i14)).getByte(i7)) {
                i13++;
            }
        }
        long j5 = 4;
        long j6 = (p9Var.c / j5) + j + ((long) 2) + ((long) (i13 * 2));
        p9Var.Q(i13);
        p9Var.Q(i5);
        for (int i15 = i4; i15 < i3; i15++) {
            int i16 = ((ByteString) arrayList4.get(i15)).getByte(i7);
            if (i15 == i4 || i16 != ((ByteString) arrayList4.get(i15 - 1)).getByte(i7)) {
                p9Var.Q(i16 & 255);
            }
        }
        p9 p9Var3 = new p9();
        int i17 = i4;
        while (i17 < i3) {
            byte b2 = ((ByteString) arrayList4.get(i17)).getByte(i7);
            int i18 = i17 + 1;
            int i19 = i18;
            while (true) {
                if (i19 >= i3) {
                    i19 = i3;
                    break;
                } else if (b2 != ((ByteString) arrayList4.get(i19)).getByte(i7)) {
                    break;
                } else {
                    i19++;
                }
            }
            if (i18 == i19 && i7 + 1 == ((ByteString) arrayList4.get(i17)).size()) {
                p9Var.Q(((Number) arrayList5.get(i17)).intValue());
                arrayList3 = arrayList5;
                j2 = j6;
                i6 = i19;
            } else {
                p9Var.Q(((int) ((p9Var3.c / j5) + j6)) * (-1));
                arrayList3 = arrayList5;
                j2 = j6;
                i6 = i19;
                f(j2, p9Var3, i7 + 1, arrayList, i17, i6, arrayList3);
                arrayList4 = arrayList;
            }
            j6 = j2;
            i17 = i6;
            arrayList5 = arrayList3;
        }
        p9Var.N(p9Var3);
    }

    public static final Bundle g(Pair... pairArr) {
        Bundle bundle = new Bundle(pairArr.length);
        for (Pair pair : pairArr) {
            String str = (String) pair.component1();
            Object objComponent2 = pair.component2();
            if (objComponent2 == null) {
                bundle.putString(str, null);
            } else if (objComponent2 instanceof Boolean) {
                bundle.putBoolean(str, ((Boolean) objComponent2).booleanValue());
            } else if (objComponent2 instanceof Byte) {
                bundle.putByte(str, ((Number) objComponent2).byteValue());
            } else if (objComponent2 instanceof Character) {
                bundle.putChar(str, ((Character) objComponent2).charValue());
            } else if (objComponent2 instanceof Double) {
                bundle.putDouble(str, ((Number) objComponent2).doubleValue());
            } else if (objComponent2 instanceof Float) {
                bundle.putFloat(str, ((Number) objComponent2).floatValue());
            } else if (objComponent2 instanceof Integer) {
                bundle.putInt(str, ((Number) objComponent2).intValue());
            } else if (objComponent2 instanceof Long) {
                bundle.putLong(str, ((Number) objComponent2).longValue());
            } else if (objComponent2 instanceof Short) {
                bundle.putShort(str, ((Number) objComponent2).shortValue());
            } else if (objComponent2 instanceof Bundle) {
                bundle.putBundle(str, (Bundle) objComponent2);
            } else if (objComponent2 instanceof CharSequence) {
                bundle.putCharSequence(str, (CharSequence) objComponent2);
            } else if (objComponent2 instanceof Parcelable) {
                bundle.putParcelable(str, (Parcelable) objComponent2);
            } else if (objComponent2 instanceof boolean[]) {
                bundle.putBooleanArray(str, (boolean[]) objComponent2);
            } else if (objComponent2 instanceof byte[]) {
                bundle.putByteArray(str, (byte[]) objComponent2);
            } else if (objComponent2 instanceof char[]) {
                bundle.putCharArray(str, (char[]) objComponent2);
            } else if (objComponent2 instanceof double[]) {
                bundle.putDoubleArray(str, (double[]) objComponent2);
            } else if (objComponent2 instanceof float[]) {
                bundle.putFloatArray(str, (float[]) objComponent2);
            } else if (objComponent2 instanceof int[]) {
                bundle.putIntArray(str, (int[]) objComponent2);
            } else if (objComponent2 instanceof long[]) {
                bundle.putLongArray(str, (long[]) objComponent2);
            } else if (objComponent2 instanceof short[]) {
                bundle.putShortArray(str, (short[]) objComponent2);
            } else if (objComponent2 instanceof Object[]) {
                Class<?> componentType = objComponent2.getClass().getComponentType();
                oy.g(componentType);
                if (Parcelable.class.isAssignableFrom(componentType)) {
                    bundle.putParcelableArray(str, (Parcelable[]) objComponent2);
                } else if (String.class.isAssignableFrom(componentType)) {
                    bundle.putStringArray(str, (String[]) objComponent2);
                } else if (CharSequence.class.isAssignableFrom(componentType)) {
                    bundle.putCharSequenceArray(str, (CharSequence[]) objComponent2);
                } else {
                    if (!Serializable.class.isAssignableFrom(componentType)) {
                        throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + str + '\"');
                    }
                    bundle.putSerializable(str, (Serializable) objComponent2);
                }
            } else if (objComponent2 instanceof Serializable) {
                bundle.putSerializable(str, (Serializable) objComponent2);
            } else if (objComponent2 instanceof IBinder) {
                bundle.putBinder(str, (IBinder) objComponent2);
            } else if (objComponent2 instanceof Size) {
                z9.a(bundle, str, (Size) objComponent2);
            } else {
                if (!(objComponent2 instanceof SizeF)) {
                    throw new IllegalArgumentException("Illegal value type " + objComponent2.getClass().getCanonicalName() + " for key \"" + str + '\"');
                }
                z9.b(bundle, str, (SizeF) objComponent2);
            }
        }
        return bundle;
    }

    public static Rect h(View view, View view2) {
        int[] iArr = new int[2];
        view2.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int i3 = i - iArr2[0];
        int i4 = i2 - iArr2[1];
        return new Rect(i3, i4, view2.getWidth() + i3, view2.getHeight() + i4);
    }

    public static float m(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    public static int n(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public static void s(View view, ht0 ht0Var) {
        WeakHashMap weakHashMap = xr0.a;
        int paddingStart = view.getPaddingStart();
        int paddingTop = view.getPaddingTop();
        int paddingEnd = view.getPaddingEnd();
        int paddingBottom = view.getPaddingBottom();
        it0 it0Var = new it0();
        it0Var.a = paddingStart;
        it0Var.b = paddingTop;
        it0Var.c = paddingEnd;
        it0Var.d = paddingBottom;
        lr0.u(view, new e5(ht0Var, it0Var, 26, false));
        if (view.isAttachedToWindow()) {
            jr0.c(view);
        } else {
            view.addOnAttachStateChangeListener(new gt0());
        }
    }

    public static float t(Context context, int i) {
        return TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static ActionMenuView v(Toolbar toolbar) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View childAt = toolbar.getChildAt(i);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
    
        if (r5.c == r8.hashCode()) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.res.ColorStateList w(android.content.Context r8, int r9) {
        /*
            android.content.res.Resources r0 = r8.getResources()
            android.content.res.Resources$Theme r8 = r8.getTheme()
            ze0 r1 = new ze0
            r1.<init>(r0, r8)
            java.lang.Object r2 = defpackage.cf0.c
            monitor-enter(r2)
            java.util.WeakHashMap r3 = defpackage.cf0.b     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> L3c
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L3c
            r4 = 0
            if (r3 == 0) goto L50
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L3c
            if (r5 <= 0) goto L50
            java.lang.Object r5 = r3.get(r9)     // Catch: java.lang.Throwable -> L3c
            ye0 r5 = (defpackage.ye0) r5     // Catch: java.lang.Throwable -> L3c
            if (r5 == 0) goto L50
            android.content.res.Configuration r6 = r5.b     // Catch: java.lang.Throwable -> L3c
            android.content.res.Configuration r7 = r0.getConfiguration()     // Catch: java.lang.Throwable -> L3c
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L4d
            if (r8 != 0) goto L3f
            int r6 = r5.c     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L49
            goto L3f
        L3c:
            r8 = move-exception
            goto Lb8
        L3f:
            if (r8 == 0) goto L4d
            int r6 = r5.c     // Catch: java.lang.Throwable -> L3c
            int r7 = r8.hashCode()     // Catch: java.lang.Throwable -> L3c
            if (r6 != r7) goto L4d
        L49:
            android.content.res.ColorStateList r3 = r5.a     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            goto L52
        L4d:
            r3.remove(r9)     // Catch: java.lang.Throwable -> L3c
        L50:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            r3 = r4
        L52:
            if (r3 == 0) goto L55
            return r3
        L55:
            java.lang.ThreadLocal r2 = defpackage.cf0.a
            java.lang.Object r3 = r2.get()
            android.util.TypedValue r3 = (android.util.TypedValue) r3
            if (r3 != 0) goto L67
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            r2.set(r3)
        L67:
            r2 = 1
            r0.getValue(r9, r3, r2)
            int r2 = r3.type
            r3 = 28
            if (r2 < r3) goto L76
            r3 = 31
            if (r2 > r3) goto L76
            goto L87
        L76:
            android.content.res.XmlResourceParser r2 = r0.getXml(r9)
            android.content.res.ColorStateList r4 = defpackage.ie.a(r0, r2, r8)     // Catch: java.lang.Exception -> L7f
            goto L87
        L7f:
            r2 = move-exception
            java.lang.String r3 = "ResourcesCompat"
            java.lang.String r5 = "Failed to inflate ColorStateList, leaving it to the framework"
            android.util.Log.w(r3, r5, r2)
        L87:
            if (r4 == 0) goto Lb3
            java.lang.Object r2 = defpackage.cf0.c
            monitor-enter(r2)
            java.util.WeakHashMap r0 = defpackage.cf0.b     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r3 = r0.get(r1)     // Catch: java.lang.Throwable -> L9f
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L9f
            if (r3 != 0) goto La1
            android.util.SparseArray r3 = new android.util.SparseArray     // Catch: java.lang.Throwable -> L9f
            r3.<init>()     // Catch: java.lang.Throwable -> L9f
            r0.put(r1, r3)     // Catch: java.lang.Throwable -> L9f
            goto La1
        L9f:
            r8 = move-exception
            goto Lb1
        La1:
            ye0 r0 = new ye0     // Catch: java.lang.Throwable -> L9f
            android.content.res.Resources r1 = r1.a     // Catch: java.lang.Throwable -> L9f
            android.content.res.Configuration r1 = r1.getConfiguration()     // Catch: java.lang.Throwable -> L9f
            r0.<init>(r4, r1, r8)     // Catch: java.lang.Throwable -> L9f
            r3.append(r9, r0)     // Catch: java.lang.Throwable -> L9f
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9f
            goto Lb7
        Lb1:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9f
            throw r8
        Lb3:
            android.content.res.ColorStateList r4 = defpackage.xe0.b(r0, r9, r8)
        Lb7:
            return r4
        Lb8:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rp0.w(android.content.Context, int):android.content.res.ColorStateList");
    }

    public static ViewGroup x(View view) {
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.content);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    public static Drawable y(Context context, int i) {
        return ve0.b().c(context, i);
    }

    public abstract boolean G();

    public abstract void L(int i);

    public abstract void M(Typeface typeface);

    public abstract void P(v vVar, v vVar2);

    public abstract void Q(v vVar, Thread thread);

    public abstract void T(boolean z);

    public abstract void U(boolean z);

    public abstract TransformationMethod b0(TransformationMethod transformationMethod);

    public void i(int i) {
        new Handler(Looper.getMainLooper()).post(new af0(this, i, 0));
    }

    public abstract boolean j(w wVar, s sVar, s sVar2);

    public abstract boolean k(w wVar, Object obj, Object obj2);

    public abstract boolean l(w wVar, v vVar, v vVar2);

    public abstract Typeface o(Context context, xs xsVar, Resources resources, int i);

    public abstract Typeface p(Context context, at[] atVarArr, int i);

    public Typeface q(Context context, InputStream inputStream) {
        File fileE = jq0.E(context);
        if (fileE == null) {
            return null;
        }
        try {
            if (jq0.i(fileE, inputStream)) {
                return Typeface.createFromFile(fileE.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileE.delete();
        }
    }

    public Typeface r(Context context, Resources resources, int i, String str, int i2) {
        File fileE = jq0.E(context);
        if (fileE == null) {
            return null;
        }
        try {
            if (jq0.h(fileE, resources, i)) {
                return Typeface.createFromFile(fileE.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileE.delete();
        }
    }

    public at u(int i, at[] atVarArr) {
        new yo(26);
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        at atVar = null;
        int i3 = Integer.MAX_VALUE;
        for (at atVar2 : atVarArr) {
            int iAbs = (Math.abs(atVar2.c - i2) * 2) + (atVar2.d == z ? 0 : 1);
            if (atVar == null || i3 > iAbs) {
                atVar = atVar2;
                i3 = iAbs;
            }
        }
        return atVar;
    }

    public abstract InputFilter[] z(InputFilter[] inputFilterArr);
}
