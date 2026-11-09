package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.customview.view.AbsSavedState;
import defpackage.ei;
import defpackage.jh0;
import defpackage.jl0;
import defpackage.jq0;
import defpackage.k80;
import defpackage.kh0;
import defpackage.lh0;
import defpackage.mh0;
import defpackage.n5;
import defpackage.nh0;
import defpackage.oh0;
import defpackage.ph0;
import defpackage.po;
import defpackage.q20;
import defpackage.qh0;
import defpackage.vd;
import defpackage.wd;
import defpackage.xr0;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements wd {
    public static final vd h0;
    public final Rect A;
    public final Rect B;
    public final int[] C;
    public final int[] D;
    public final ImageView E;
    public final Drawable F;
    public final int G;
    public final int H;
    public final Intent I;
    public final Intent J;
    public final CharSequence K;
    public View.OnFocusChangeListener L;
    public View.OnClickListener M;
    public boolean N;
    public boolean O;
    public ei P;
    public boolean Q;
    public CharSequence R;
    public boolean S;
    public boolean T;
    public int U;
    public boolean V;
    public CharSequence W;
    public boolean a0;
    public int b0;
    public SearchableInfo c0;
    public Bundle d0;
    public final kh0 e0;
    public final kh0 f0;
    public final WeakHashMap g0;
    public final SearchAutoComplete q;
    public final View r;
    public final View s;
    public final View t;
    public final ImageView u;
    public final ImageView v;
    public final ImageView w;
    public final ImageView x;
    public final View y;
    public qh0 z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new h();
        public boolean d;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public final String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.d + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.d));
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        public int f;
        public SearchView g;
        public boolean h;
        public final i i;

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i < 600) {
                return (i < 640 || i2 < 480) ? 160 : 192;
            }
            return 192;
        }

        public final void a() {
            if (Build.VERSION.SDK_INT >= 29) {
                g.b(this, 1);
                if (enoughToFilter()) {
                    showDropDown();
                    return;
                }
                return;
            }
            vd vdVar = SearchView.h0;
            vdVar.getClass();
            vd.a();
            Method method = vdVar.c;
            if (method != null) {
                try {
                    method.invoke(this, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final boolean enoughToFilter() {
            return this.f <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.h) {
                i iVar = this.i;
                removeCallbacks(iVar);
                post(iVar);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public final void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            SearchView searchView = this.g;
            searchView.y(searchView.O);
            searchView.post(searchView.e0);
            if (searchView.q.hasFocus()) {
                searchView.n();
            }
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.g.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.g.hasFocus() && getVisibility() == 0) {
                this.h = true;
                Context context = getContext();
                vd vdVar = SearchView.h0;
                if (context.getResources().getConfiguration().orientation == 2) {
                    a();
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public final void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            i iVar = this.i;
            if (!z) {
                this.h = false;
                removeCallbacks(iVar);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.h = true;
                    return;
                }
                this.h = false;
                removeCallbacks(iVar);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.g = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.f = i;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.i = new i(this);
            this.f = getThreshold();
        }
    }

    static {
        vd vdVar = null;
        if (Build.VERSION.SDK_INT < 29) {
            vd vdVar2 = new vd();
            vdVar2.a = null;
            vdVar2.b = null;
            vdVar2.c = null;
            vd.a();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                vdVar2.a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                vdVar2.b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                vdVar2.c = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
            vdVar = vdVar2;
        }
        h0 = vdVar;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_width);
    }

    private void setQuery(CharSequence charSequence) {
        SearchAutoComplete searchAutoComplete = this.q;
        searchAutoComplete.setText(charSequence);
        searchAutoComplete.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    @Override // defpackage.wd
    public final void b() {
        if (this.a0) {
            return;
        }
        this.a0 = true;
        SearchAutoComplete searchAutoComplete = this.q;
        int imeOptions = searchAutoComplete.getImeOptions();
        this.b0 = imeOptions;
        searchAutoComplete.setImeOptions(imeOptions | 33554432);
        searchAutoComplete.setText(ExtensionRequestData.EMPTY_VALUE);
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void clearFocus() {
        this.T = true;
        super.clearFocus();
        SearchAutoComplete searchAutoComplete = this.q;
        searchAutoComplete.clearFocus();
        searchAutoComplete.setImeVisibility(false);
        this.T = false;
    }

    @Override // defpackage.wd
    public final void e() {
        SearchAutoComplete searchAutoComplete = this.q;
        searchAutoComplete.setText(ExtensionRequestData.EMPTY_VALUE);
        searchAutoComplete.setSelection(searchAutoComplete.length());
        this.W = ExtensionRequestData.EMPTY_VALUE;
        clearFocus();
        y(true);
        searchAutoComplete.setImeOptions(this.b0);
        this.a0 = false;
    }

    public int getImeOptions() {
        return this.q.getImeOptions();
    }

    public int getInputType() {
        return this.q.getInputType();
    }

    public int getMaxWidth() {
        return this.U;
    }

    public CharSequence getQuery() {
        return this.q.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.R;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.c0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.K : getContext().getText(this.c0.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.H;
    }

    public int getSuggestionRowLayout() {
        return this.G;
    }

    public ei getSuggestionsAdapter() {
        return this.P;
    }

    public final Intent l(String str, Uri uri, String str2, String str3) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.W);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.d0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        intent.setComponent(this.c0.getSearchActivity());
        return intent;
    }

    public final Intent m(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1107296256);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.d0;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    public final void n() {
        int i = Build.VERSION.SDK_INT;
        SearchAutoComplete searchAutoComplete = this.q;
        if (i >= 29) {
            g.a(searchAutoComplete);
            return;
        }
        vd vdVar = h0;
        vdVar.getClass();
        vd.a();
        Method method = vdVar.a;
        if (method != null) {
            try {
                method.invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused) {
            }
        }
        vdVar.getClass();
        vd.a();
        Method method2 = vdVar.b;
        if (method2 != null) {
            try {
                method2.invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused2) {
            }
        }
    }

    public final void o() {
        SearchAutoComplete searchAutoComplete = this.q;
        if (!TextUtils.isEmpty(searchAutoComplete.getText())) {
            searchAutoComplete.setText(ExtensionRequestData.EMPTY_VALUE);
            searchAutoComplete.requestFocus();
            searchAutoComplete.setImeVisibility(true);
        } else if (this.N) {
            clearFocus();
            y(true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.e0);
        post(this.f0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            int[] iArr = this.C;
            SearchAutoComplete searchAutoComplete = this.q;
            searchAutoComplete.getLocationInWindow(iArr);
            int[] iArr2 = this.D;
            getLocationInWindow(iArr2);
            int i5 = iArr[1] - iArr2[1];
            int i6 = iArr[0] - iArr2[0];
            int width = searchAutoComplete.getWidth() + i6;
            int height = searchAutoComplete.getHeight() + i5;
            Rect rect = this.A;
            rect.set(i6, i5, width, height);
            int i7 = rect.left;
            int i8 = rect.right;
            int i9 = i4 - i2;
            Rect rect2 = this.B;
            rect2.set(i7, 0, i8, i9);
            qh0 qh0Var = this.z;
            if (qh0Var == null) {
                qh0 qh0Var2 = new qh0(rect2, rect, searchAutoComplete);
                this.z = qh0Var2;
                setTouchDelegate(qh0Var2);
            } else {
                qh0Var.b.set(rect2);
                Rect rect3 = qh0Var.d;
                rect3.set(rect2);
                int i10 = -qh0Var.e;
                rect3.inset(i10, i10);
                qh0Var.c.set(rect);
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        if (this.O) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            int i4 = this.U;
            size = i4 > 0 ? Math.min(i4, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.U;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i3 = this.U) > 0) {
            size = Math.min(i3, size);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b);
        y(savedState.d);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.d = this.O;
        return savedState;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        post(this.e0);
    }

    public final void p(int i) {
        int position;
        String strH;
        Cursor cursor = this.P.d;
        if (cursor != null && cursor.moveToPosition(i)) {
            Intent intentL = null;
            try {
                int i2 = jl0.y;
                String strH2 = jl0.h(cursor, cursor.getColumnIndex("suggest_intent_action"));
                if (strH2 == null) {
                    strH2 = this.c0.getSuggestIntentAction();
                }
                if (strH2 == null) {
                    strH2 = "android.intent.action.SEARCH";
                }
                String strH3 = jl0.h(cursor, cursor.getColumnIndex("suggest_intent_data"));
                if (strH3 == null) {
                    strH3 = this.c0.getSuggestIntentData();
                }
                if (strH3 != null && (strH = jl0.h(cursor, cursor.getColumnIndex("suggest_intent_data_id"))) != null) {
                    strH3 = strH3 + "/" + Uri.encode(strH);
                }
                intentL = l(strH2, strH3 == null ? null : Uri.parse(strH3), jl0.h(cursor, cursor.getColumnIndex("suggest_intent_extra_data")), jl0.h(cursor, cursor.getColumnIndex("suggest_intent_query")));
            } catch (RuntimeException e) {
                try {
                    position = cursor.getPosition();
                } catch (RuntimeException unused) {
                    position = -1;
                }
                Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e);
            }
            if (intentL != null) {
                try {
                    getContext().startActivity(intentL);
                } catch (RuntimeException e2) {
                    Log.e("SearchView", "Failed launch activity: " + intentL, e2);
                }
            }
        }
        SearchAutoComplete searchAutoComplete = this.q;
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    public final void q(int i) {
        Editable text = this.q.getText();
        Cursor cursor = this.P.d;
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(i)) {
            setQuery(text);
            return;
        }
        String strC = this.P.c(cursor);
        if (strC != null) {
            setQuery(strC);
        } else {
            setQuery(text);
        }
    }

    public final void r(CharSequence charSequence) {
        setQuery(charSequence);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean requestFocus(int i, Rect rect) {
        if (this.T || !isFocusable()) {
            return false;
        }
        if (this.O) {
            return super.requestFocus(i, rect);
        }
        boolean zRequestFocus = this.q.requestFocus(i, rect);
        if (zRequestFocus) {
            y(false);
        }
        return zRequestFocus;
    }

    public final void s() {
        SearchAutoComplete searchAutoComplete = this.q;
        Editable text = searchAutoComplete.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        if (this.c0 != null) {
            getContext().startActivity(l("android.intent.action.SEARCH", null, null, text.toString()));
        }
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    public void setAppSearchData(Bundle bundle) {
        this.d0 = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            o();
            return;
        }
        y(false);
        SearchAutoComplete searchAutoComplete = this.q;
        searchAutoComplete.requestFocus();
        searchAutoComplete.setImeVisibility(true);
        View.OnClickListener onClickListener = this.M;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.N == z) {
            return;
        }
        this.N = z;
        y(z);
        v();
    }

    public void setImeOptions(int i) {
        this.q.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.q.setInputType(i);
    }

    public void setMaxWidth(int i) {
        this.U = i;
        requestLayout();
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.L = onFocusChangeListener;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.M = onClickListener;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.R = charSequence;
        v();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.S = z;
        ei eiVar = this.P;
        if (eiVar instanceof jl0) {
            ((jl0) eiVar).q = z ? 2 : 1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setSearchableInfo(android.app.SearchableInfo r7) {
        /*
            r6 = this;
            r6.c0 = r7
            r0 = 0
            r1 = 1
            androidx.appcompat.widget.SearchView$SearchAutoComplete r2 = r6.q
            if (r7 == 0) goto L65
            int r7 = r7.getSuggestThreshold()
            r2.setThreshold(r7)
            android.app.SearchableInfo r7 = r6.c0
            int r7 = r7.getImeOptions()
            r2.setImeOptions(r7)
            android.app.SearchableInfo r7 = r6.c0
            int r7 = r7.getInputType()
            r3 = r7 & 15
            if (r3 != r1) goto L31
            r3 = -65537(0xfffffffffffeffff, float:NaN)
            r7 = r7 & r3
            android.app.SearchableInfo r3 = r6.c0
            java.lang.String r3 = r3.getSuggestAuthority()
            if (r3 == 0) goto L31
            r3 = 589824(0x90000, float:8.2652E-40)
            r7 = r7 | r3
        L31:
            r2.setInputType(r7)
            ei r7 = r6.P
            if (r7 == 0) goto L3b
            r7.b(r0)
        L3b:
            android.app.SearchableInfo r7 = r6.c0
            java.lang.String r7 = r7.getSuggestAuthority()
            if (r7 == 0) goto L62
            jl0 r7 = new jl0
            android.content.Context r3 = r6.getContext()
            android.app.SearchableInfo r4 = r6.c0
            java.util.WeakHashMap r5 = r6.g0
            r7.<init>(r3, r6, r4, r5)
            r6.P = r7
            r2.setAdapter(r7)
            ei r7 = r6.P
            jl0 r7 = (defpackage.jl0) r7
            boolean r3 = r6.S
            if (r3 == 0) goto L5f
            r3 = 2
            goto L60
        L5f:
            r3 = r1
        L60:
            r7.q = r3
        L62:
            r6.v()
        L65:
            android.app.SearchableInfo r7 = r6.c0
            r3 = 0
            if (r7 == 0) goto L98
            boolean r7 = r7.getVoiceSearchEnabled()
            if (r7 == 0) goto L98
            android.app.SearchableInfo r7 = r6.c0
            boolean r7 = r7.getVoiceSearchLaunchWebSearch()
            if (r7 == 0) goto L7b
            android.content.Intent r0 = r6.I
            goto L85
        L7b:
            android.app.SearchableInfo r7 = r6.c0
            boolean r7 = r7.getVoiceSearchLaunchRecognizer()
            if (r7 == 0) goto L85
            android.content.Intent r0 = r6.J
        L85:
            if (r0 == 0) goto L98
            android.content.Context r7 = r6.getContext()
            android.content.pm.PackageManager r7 = r7.getPackageManager()
            r4 = 65536(0x10000, float:9.1835E-41)
            android.content.pm.ResolveInfo r7 = r7.resolveActivity(r0, r4)
            if (r7 == 0) goto L98
            goto L99
        L98:
            r1 = r3
        L99:
            r6.V = r1
            if (r1 == 0) goto La2
            java.lang.String r7 = "nm"
            r2.setPrivateImeOptions(r7)
        La2:
            boolean r7 = r6.O
            r6.y(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.setSearchableInfo(android.app.SearchableInfo):void");
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.Q = z;
        y(this.O);
    }

    public void setSuggestionsAdapter(ei eiVar) {
        this.P = eiVar;
        this.q.setAdapter(eiVar);
    }

    public final void t() {
        boolean zIsEmpty = TextUtils.isEmpty(this.q.getText());
        int i = (!zIsEmpty || (this.N && !this.a0)) ? 0 : 8;
        ImageView imageView = this.w;
        imageView.setVisibility(i);
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            drawable.setState(!zIsEmpty ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    public final void u() {
        int[] iArr = this.q.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.s.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.t.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void v() {
        CharSequence queryHint = getQueryHint();
        CharSequence charSequence = queryHint;
        if (queryHint == null) {
            charSequence = ExtensionRequestData.EMPTY_VALUE;
        }
        boolean z = this.N;
        SearchAutoComplete searchAutoComplete = this.q;
        CharSequence charSequence2 = charSequence;
        if (z) {
            Drawable drawable = this.F;
            charSequence2 = charSequence;
            if (drawable != null) {
                int textSize = (int) (((double) searchAutoComplete.getTextSize()) * 1.25d);
                drawable.setBounds(0, 0, textSize, textSize);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
                spannableStringBuilder.setSpan(new ImageSpan(drawable), 1, 2, 33);
                spannableStringBuilder.append(charSequence);
                charSequence2 = spannableStringBuilder;
            }
        }
        searchAutoComplete.setHint(charSequence2);
    }

    public final void w() {
        this.t.setVisibility(((this.Q || this.V) && !this.O && (this.v.getVisibility() == 0 || this.x.getVisibility() == 0)) ? 0 : 8);
    }

    public final void x(boolean z) {
        boolean z2 = this.Q;
        this.v.setVisibility((!z2 || !(z2 || this.V) || this.O || !hasFocus() || (!z && this.V)) ? 8 : 0);
    }

    public final void y(boolean z) {
        this.O = z;
        int i = 8;
        int i2 = z ? 0 : 8;
        boolean zIsEmpty = TextUtils.isEmpty(this.q.getText());
        this.u.setVisibility(i2);
        x(!zIsEmpty);
        this.r.setVisibility(z ? 8 : 0);
        ImageView imageView = this.E;
        imageView.setVisibility((imageView.getDrawable() == null || this.N) ? 8 : 0);
        t();
        if (this.V && !this.O && zIsEmpty) {
            this.v.setVisibility(8);
            i = 0;
        }
        this.x.setVisibility(i);
        w();
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.A = new Rect();
        this.B = new Rect();
        this.C = new int[2];
        this.D = new int[2];
        this.e0 = new kh0(this, 0);
        this.f0 = new kh0(this, 1);
        this.g0 = new WeakHashMap();
        e eVar = new e(this);
        f fVar = new f(this);
        mh0 mh0Var = new mh0(this);
        n5 n5Var = new n5(2, this);
        q20 q20Var = new q20(1, this);
        jh0 jh0Var = new jh0(this, 0);
        po poVarN = po.N(context, attributeSet, R$styleable.SearchView, i);
        xr0.o(this, context, R$styleable.SearchView, attributeSet, (TypedArray) poVarN.d, i);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        int i2 = R$styleable.SearchView_layout;
        int i3 = R$layout.abc_search_view;
        TypedArray typedArray = (TypedArray) poVarN.d;
        layoutInflaterFrom.inflate(typedArray.getResourceId(i2, i3), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R$id.search_src_text);
        this.q = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.r = findViewById(R$id.search_edit_frame);
        View viewFindViewById = findViewById(R$id.search_plate);
        this.s = viewFindViewById;
        View viewFindViewById2 = findViewById(R$id.submit_area);
        this.t = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(R$id.search_button);
        this.u = imageView;
        ImageView imageView2 = (ImageView) findViewById(R$id.search_go_btn);
        this.v = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R$id.search_close_btn);
        this.w = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R$id.search_voice_btn);
        this.x = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R$id.search_mag_icon);
        this.E = imageView5;
        viewFindViewById.setBackground(poVarN.y(R$styleable.SearchView_queryBackground));
        viewFindViewById2.setBackground(poVarN.y(R$styleable.SearchView_submitBackground));
        imageView.setImageDrawable(poVarN.y(R$styleable.SearchView_searchIcon));
        imageView2.setImageDrawable(poVarN.y(R$styleable.SearchView_goIcon));
        imageView3.setImageDrawable(poVarN.y(R$styleable.SearchView_closeIcon));
        imageView4.setImageDrawable(poVarN.y(R$styleable.SearchView_voiceIcon));
        imageView5.setImageDrawable(poVarN.y(R$styleable.SearchView_searchIcon));
        this.F = poVarN.y(R$styleable.SearchView_searchHintIcon);
        jq0.d0(imageView, getResources().getString(R$string.abc_searchview_description_search));
        this.G = typedArray.getResourceId(R$styleable.SearchView_suggestionRowLayout, R$layout.abc_search_dropdown_item_icons_2line);
        this.H = typedArray.getResourceId(R$styleable.SearchView_commitIcon, 0);
        imageView.setOnClickListener(eVar);
        imageView3.setOnClickListener(eVar);
        imageView2.setOnClickListener(eVar);
        imageView4.setOnClickListener(eVar);
        searchAutoComplete.setOnClickListener(eVar);
        searchAutoComplete.addTextChangedListener(jh0Var);
        searchAutoComplete.setOnEditorActionListener(mh0Var);
        searchAutoComplete.setOnItemClickListener(n5Var);
        searchAutoComplete.setOnItemSelectedListener(q20Var);
        searchAutoComplete.setOnKeyListener(fVar);
        searchAutoComplete.setOnFocusChangeListener(new lh0(this));
        setIconifiedByDefault(typedArray.getBoolean(R$styleable.SearchView_iconifiedByDefault, true));
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R$styleable.SearchView_android_maxWidth, -1);
        if (dimensionPixelSize != -1) {
            setMaxWidth(dimensionPixelSize);
        }
        this.K = typedArray.getText(R$styleable.SearchView_defaultQueryHint);
        this.R = typedArray.getText(R$styleable.SearchView_queryHint);
        int i4 = typedArray.getInt(R$styleable.SearchView_android_imeOptions, -1);
        if (i4 != -1) {
            setImeOptions(i4);
        }
        int i5 = typedArray.getInt(R$styleable.SearchView_android_inputType, -1);
        if (i5 != -1) {
            setInputType(i5);
        }
        setFocusable(typedArray.getBoolean(R$styleable.SearchView_android_focusable, true));
        poVarN.P();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.I = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.J = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.y = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new k80(1, this));
        }
        y(this.N);
        v();
    }

    public void setOnCloseListener(nh0 nh0Var) {
    }

    public void setOnQueryTextListener(oh0 oh0Var) {
    }

    public void setOnSuggestionListener(ph0 ph0Var) {
    }
}
