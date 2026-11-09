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
import defpackage.as0;
import defpackage.bf0;
import defpackage.bs0;
import defpackage.cs0;
import defpackage.ds0;
import defpackage.es0;
import defpackage.f51;
import defpackage.fs0;
import defpackage.gs0;
import defpackage.hs0;
import defpackage.la0;
import defpackage.ng0;
import defpackage.ou;
import defpackage.r6;
import defpackage.rf;
import defpackage.v90;
import defpackage.xj;
import defpackage.xx0;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.NatsJetStreamConstants;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements rf {
    public static final bf0 K0;
    public int A0;
    public boolean B0;
    public CharSequence C0;
    public boolean D0;
    public int E0;
    public SearchableInfo F0;
    public Bundle G0;
    public final bs0 H0;
    public final bs0 I0;
    public final WeakHashMap J0;
    public final SearchAutoComplete T;
    public final View U;
    public final View V;
    public final View W;
    public final ImageView a0;
    public final ImageView b0;
    public final ImageView c0;
    public final ImageView d0;
    public final View e0;
    public hs0 f0;
    public final Rect g0;
    public final Rect h0;
    public final int[] i0;
    public final int[] j0;
    public final ImageView k0;
    public final Drawable l0;
    public final int m0;
    public final int n0;
    public final Intent o0;
    public final Intent p0;
    public final CharSequence q0;
    public View.OnFocusChangeListener r0;
    public View.OnClickListener s0;
    public boolean t0;
    public boolean u0;
    public xj v0;
    public boolean w0;
    public CharSequence x0;
    public boolean y0;
    public boolean z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new h();
        public boolean G;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public final String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.G + JsonUtils.CLOSE;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.G));
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        public int I;
        public SearchView J;
        public boolean K;
        public final i L;

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return NatsJetStreamConstants.MAX_PULL_SIZE;
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
            bf0 bf0Var = SearchView.K0;
            bf0Var.getClass();
            bf0.a();
            Method method = bf0Var.c;
            if (method != null) {
                try {
                    method.invoke(this, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final boolean enoughToFilter() {
            return this.I <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.K) {
                i iVar = this.L;
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
            SearchView searchView = this.J;
            searchView.y(searchView.u0);
            searchView.post(searchView.H0);
            if (searchView.T.hasFocus()) {
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
                        this.J.clearFocus();
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
            if (z && this.J.hasFocus() && getVisibility() == 0) {
                this.K = true;
                Context context = getContext();
                bf0 bf0Var = SearchView.K0;
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
            i iVar = this.L;
            if (!z) {
                this.K = false;
                removeCallbacks(iVar);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.K = true;
                    return;
                }
                this.K = false;
                removeCallbacks(iVar);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.J = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.I = i;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.L = new i(this);
            this.I = getThreshold();
        }
    }

    static {
        bf0 bf0Var = null;
        if (Build.VERSION.SDK_INT < 29) {
            bf0 bf0Var2 = new bf0();
            bf0Var2.a = null;
            bf0Var2.b = null;
            bf0Var2.c = null;
            bf0.a();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                bf0Var2.a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                bf0Var2.b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                bf0Var2.c = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
            bf0Var = bf0Var2;
        }
        K0 = bf0Var;
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
        SearchAutoComplete searchAutoComplete = this.T;
        searchAutoComplete.setText(charSequence);
        searchAutoComplete.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    @Override // defpackage.rf
    public final void b() {
        if (this.D0) {
            return;
        }
        this.D0 = true;
        SearchAutoComplete searchAutoComplete = this.T;
        int imeOptions = searchAutoComplete.getImeOptions();
        this.E0 = imeOptions;
        searchAutoComplete.setImeOptions(imeOptions | 33554432);
        searchAutoComplete.setText("");
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void clearFocus() {
        this.z0 = true;
        super.clearFocus();
        SearchAutoComplete searchAutoComplete = this.T;
        searchAutoComplete.clearFocus();
        searchAutoComplete.setImeVisibility(false);
        this.z0 = false;
    }

    @Override // defpackage.rf
    public final void e() {
        SearchAutoComplete searchAutoComplete = this.T;
        searchAutoComplete.setText("");
        searchAutoComplete.setSelection(searchAutoComplete.length());
        this.C0 = "";
        clearFocus();
        y(true);
        searchAutoComplete.setImeOptions(this.E0);
        this.D0 = false;
    }

    public int getImeOptions() {
        return this.T.getImeOptions();
    }

    public int getInputType() {
        return this.T.getInputType();
    }

    public int getMaxWidth() {
        return this.A0;
    }

    public CharSequence getQuery() {
        return this.T.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.x0;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.F0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.q0 : getContext().getText(this.F0.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.n0;
    }

    public int getSuggestionRowLayout() {
        return this.m0;
    }

    public xj getSuggestionsAdapter() {
        return this.v0;
    }

    public final Intent l(String str, Uri uri, String str2, String str3) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.C0);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.G0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        intent.setComponent(this.F0.getSearchActivity());
        return intent;
    }

    public final Intent m(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1107296256);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.G0;
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
        SearchAutoComplete searchAutoComplete = this.T;
        if (i >= 29) {
            g.a(searchAutoComplete);
            return;
        }
        bf0 bf0Var = K0;
        bf0Var.getClass();
        bf0.a();
        Method method = bf0Var.a;
        if (method != null) {
            try {
                method.invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused) {
            }
        }
        bf0Var.getClass();
        bf0.a();
        Method method2 = bf0Var.b;
        if (method2 != null) {
            try {
                method2.invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused2) {
            }
        }
    }

    public final void o() {
        SearchAutoComplete searchAutoComplete = this.T;
        if (!TextUtils.isEmpty(searchAutoComplete.getText())) {
            searchAutoComplete.setText("");
            searchAutoComplete.requestFocus();
            searchAutoComplete.setImeVisibility(true);
        } else if (this.t0) {
            clearFocus();
            y(true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.H0);
        post(this.I0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            int[] iArr = this.i0;
            SearchAutoComplete searchAutoComplete = this.T;
            searchAutoComplete.getLocationInWindow(iArr);
            int[] iArr2 = this.j0;
            getLocationInWindow(iArr2);
            int i5 = iArr[1] - iArr2[1];
            int i6 = iArr[0] - iArr2[0];
            int width = searchAutoComplete.getWidth() + i6;
            int height = searchAutoComplete.getHeight() + i5;
            Rect rect = this.g0;
            rect.set(i6, i5, width, height);
            int i7 = rect.left;
            int i8 = rect.right;
            int i9 = i4 - i2;
            Rect rect2 = this.h0;
            rect2.set(i7, 0, i8, i9);
            hs0 hs0Var = this.f0;
            if (hs0Var == null) {
                hs0 hs0Var2 = new hs0(rect2, rect, searchAutoComplete);
                this.f0 = hs0Var2;
                setTouchDelegate(hs0Var2);
            } else {
                hs0Var.b.set(rect2);
                Rect rect3 = hs0Var.d;
                rect3.set(rect2);
                int i10 = -hs0Var.e;
                rect3.inset(i10, i10);
                hs0Var.c.set(rect);
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        if (this.u0) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            int i4 = this.A0;
            size = i4 > 0 ? Math.min(i4, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.A0;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i3 = this.A0) > 0) {
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
        super.onRestoreInstanceState(savedState.E);
        y(savedState.G);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.G = this.u0;
        return savedState;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        post(this.H0);
    }

    public final void p(int i) {
        int position;
        String strH;
        Cursor cursor = this.v0.G;
        if (cursor != null && cursor.moveToPosition(i)) {
            Intent intentL = null;
            try {
                int i2 = xx0.b0;
                String strH2 = xx0.h(cursor, cursor.getColumnIndex("suggest_intent_action"));
                if (strH2 == null) {
                    strH2 = this.F0.getSuggestIntentAction();
                }
                if (strH2 == null) {
                    strH2 = "android.intent.action.SEARCH";
                }
                String strH3 = xx0.h(cursor, cursor.getColumnIndex("suggest_intent_data"));
                if (strH3 == null) {
                    strH3 = this.F0.getSuggestIntentData();
                }
                if (strH3 != null && (strH = xx0.h(cursor, cursor.getColumnIndex("suggest_intent_data_id"))) != null) {
                    strH3 = strH3 + "/" + Uri.encode(strH);
                }
                intentL = l(strH2, strH3 == null ? null : Uri.parse(strH3), xx0.h(cursor, cursor.getColumnIndex("suggest_intent_extra_data")), xx0.h(cursor, cursor.getColumnIndex("suggest_intent_query")));
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
        SearchAutoComplete searchAutoComplete = this.T;
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    public final void q(int i) {
        Editable text = this.T.getText();
        Cursor cursor = this.v0.G;
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(i)) {
            setQuery(text);
            return;
        }
        String strC = this.v0.c(cursor);
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
        if (this.z0 || !isFocusable()) {
            return false;
        }
        if (this.u0) {
            return super.requestFocus(i, rect);
        }
        boolean zRequestFocus = this.T.requestFocus(i, rect);
        if (zRequestFocus) {
            y(false);
        }
        return zRequestFocus;
    }

    public final void s() {
        SearchAutoComplete searchAutoComplete = this.T;
        Editable text = searchAutoComplete.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        if (this.F0 != null) {
            getContext().startActivity(l("android.intent.action.SEARCH", null, null, text.toString()));
        }
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    public void setAppSearchData(Bundle bundle) {
        this.G0 = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            o();
            return;
        }
        y(false);
        SearchAutoComplete searchAutoComplete = this.T;
        searchAutoComplete.requestFocus();
        searchAutoComplete.setImeVisibility(true);
        View.OnClickListener onClickListener = this.s0;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.t0 == z) {
            return;
        }
        this.t0 = z;
        y(z);
        v();
    }

    public void setImeOptions(int i) {
        this.T.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.T.setInputType(i);
    }

    public void setMaxWidth(int i) {
        this.A0 = i;
        requestLayout();
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.r0 = onFocusChangeListener;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.s0 = onClickListener;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.x0 = charSequence;
        v();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.y0 = z;
        xj xjVar = this.v0;
        if (xjVar instanceof xx0) {
            ((xx0) xjVar).T = z ? 2 : 1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setSearchableInfo(android.app.SearchableInfo r7) {
        /*
            r6 = this;
            r6.F0 = r7
            r0 = 0
            r1 = 1
            androidx.appcompat.widget.SearchView$SearchAutoComplete r2 = r6.T
            if (r7 == 0) goto L65
            int r7 = r7.getSuggestThreshold()
            r2.setThreshold(r7)
            android.app.SearchableInfo r7 = r6.F0
            int r7 = r7.getImeOptions()
            r2.setImeOptions(r7)
            android.app.SearchableInfo r7 = r6.F0
            int r7 = r7.getInputType()
            r3 = r7 & 15
            if (r3 != r1) goto L31
            r3 = -65537(0xfffffffffffeffff, float:NaN)
            r7 = r7 & r3
            android.app.SearchableInfo r3 = r6.F0
            java.lang.String r3 = r3.getSuggestAuthority()
            if (r3 == 0) goto L31
            r3 = 589824(0x90000, float:8.2652E-40)
            r7 = r7 | r3
        L31:
            r2.setInputType(r7)
            xj r7 = r6.v0
            if (r7 == 0) goto L3b
            r7.b(r0)
        L3b:
            android.app.SearchableInfo r7 = r6.F0
            java.lang.String r7 = r7.getSuggestAuthority()
            if (r7 == 0) goto L62
            xx0 r7 = new xx0
            android.content.Context r3 = r6.getContext()
            android.app.SearchableInfo r4 = r6.F0
            java.util.WeakHashMap r5 = r6.J0
            r7.<init>(r3, r6, r4, r5)
            r6.v0 = r7
            r2.setAdapter(r7)
            xj r7 = r6.v0
            xx0 r7 = (defpackage.xx0) r7
            boolean r3 = r6.y0
            if (r3 == 0) goto L5f
            r3 = 2
            goto L60
        L5f:
            r3 = r1
        L60:
            r7.T = r3
        L62:
            r6.v()
        L65:
            android.app.SearchableInfo r7 = r6.F0
            r3 = 0
            if (r7 == 0) goto L98
            boolean r7 = r7.getVoiceSearchEnabled()
            if (r7 == 0) goto L98
            android.app.SearchableInfo r7 = r6.F0
            boolean r7 = r7.getVoiceSearchLaunchWebSearch()
            if (r7 == 0) goto L7b
            android.content.Intent r0 = r6.o0
            goto L85
        L7b:
            android.app.SearchableInfo r7 = r6.F0
            boolean r7 = r7.getVoiceSearchLaunchRecognizer()
            if (r7 == 0) goto L85
            android.content.Intent r0 = r6.p0
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
            r6.B0 = r1
            if (r1 == 0) goto La2
            java.lang.String r7 = "nm"
            r2.setPrivateImeOptions(r7)
        La2:
            boolean r7 = r6.u0
            r6.y(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.setSearchableInfo(android.app.SearchableInfo):void");
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.w0 = z;
        y(this.u0);
    }

    public void setSuggestionsAdapter(xj xjVar) {
        this.v0 = xjVar;
        this.T.setAdapter(xjVar);
    }

    public final void t() {
        boolean zIsEmpty = TextUtils.isEmpty(this.T.getText());
        int i = (!zIsEmpty || (this.t0 && !this.D0)) ? 0 : 8;
        ImageView imageView = this.c0;
        imageView.setVisibility(i);
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            drawable.setState(!zIsEmpty ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    public final void u() {
        int[] iArr = this.T.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.V.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.W.getBackground();
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
            charSequence = "";
        }
        boolean z = this.t0;
        SearchAutoComplete searchAutoComplete = this.T;
        CharSequence charSequence2 = charSequence;
        if (z) {
            Drawable drawable = this.l0;
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
        this.W.setVisibility(((this.w0 || this.B0) && !this.u0 && (this.b0.getVisibility() == 0 || this.d0.getVisibility() == 0)) ? 0 : 8);
    }

    public final void x(boolean z) {
        boolean z2 = this.w0;
        this.b0.setVisibility((!z2 || !(z2 || this.B0) || this.u0 || !hasFocus() || (!z && this.B0)) ? 8 : 0);
    }

    public final void y(boolean z) {
        this.u0 = z;
        int i = 8;
        int i2 = z ? 0 : 8;
        boolean zIsEmpty = TextUtils.isEmpty(this.T.getText());
        this.a0.setVisibility(i2);
        x(!zIsEmpty);
        this.U.setVisibility(z ? 8 : 0);
        ImageView imageView = this.k0;
        imageView.setVisibility((imageView.getDrawable() == null || this.t0) ? 8 : 0);
        t();
        if (this.B0 && !this.u0 && zIsEmpty) {
            this.b0.setVisibility(8);
            i = 0;
        }
        this.d0.setVisibility(i);
        w();
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g0 = new Rect();
        this.h0 = new Rect();
        this.i0 = new int[2];
        this.j0 = new int[2];
        this.H0 = new bs0(this, 0);
        this.I0 = new bs0(this, 1);
        this.J0 = new WeakHashMap();
        e eVar = new e(this);
        f fVar = new f(this);
        ds0 ds0Var = new ds0(this);
        r6 r6Var = new r6(2, this);
        v90 v90Var = new v90(1, this);
        as0 as0Var = new as0(this, 0);
        ou ouVarP = ou.P(context, attributeSet, R$styleable.SearchView, i);
        f51.n(this, context, R$styleable.SearchView, attributeSet, (TypedArray) ouVarP.G, i);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        int i2 = R$styleable.SearchView_layout;
        int i3 = R$layout.abc_search_view;
        TypedArray typedArray = (TypedArray) ouVarP.G;
        layoutInflaterFrom.inflate(typedArray.getResourceId(i2, i3), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R$id.search_src_text);
        this.T = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.U = findViewById(R$id.search_edit_frame);
        View viewFindViewById = findViewById(R$id.search_plate);
        this.V = viewFindViewById;
        View viewFindViewById2 = findViewById(R$id.submit_area);
        this.W = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(R$id.search_button);
        this.a0 = imageView;
        ImageView imageView2 = (ImageView) findViewById(R$id.search_go_btn);
        this.b0 = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R$id.search_close_btn);
        this.c0 = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R$id.search_voice_btn);
        this.d0 = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R$id.search_mag_icon);
        this.k0 = imageView5;
        viewFindViewById.setBackground(ouVarP.A(R$styleable.SearchView_queryBackground));
        viewFindViewById2.setBackground(ouVarP.A(R$styleable.SearchView_submitBackground));
        imageView.setImageDrawable(ouVarP.A(R$styleable.SearchView_searchIcon));
        imageView2.setImageDrawable(ouVarP.A(R$styleable.SearchView_goIcon));
        imageView3.setImageDrawable(ouVarP.A(R$styleable.SearchView_closeIcon));
        imageView4.setImageDrawable(ouVarP.A(R$styleable.SearchView_voiceIcon));
        imageView5.setImageDrawable(ouVarP.A(R$styleable.SearchView_searchIcon));
        this.l0 = ouVarP.A(R$styleable.SearchView_searchHintIcon);
        la0.e0(imageView, getResources().getString(R$string.abc_searchview_description_search));
        this.m0 = typedArray.getResourceId(R$styleable.SearchView_suggestionRowLayout, R$layout.abc_search_dropdown_item_icons_2line);
        this.n0 = typedArray.getResourceId(R$styleable.SearchView_commitIcon, 0);
        imageView.setOnClickListener(eVar);
        imageView3.setOnClickListener(eVar);
        imageView2.setOnClickListener(eVar);
        imageView4.setOnClickListener(eVar);
        searchAutoComplete.setOnClickListener(eVar);
        searchAutoComplete.addTextChangedListener(as0Var);
        searchAutoComplete.setOnEditorActionListener(ds0Var);
        searchAutoComplete.setOnItemClickListener(r6Var);
        searchAutoComplete.setOnItemSelectedListener(v90Var);
        searchAutoComplete.setOnKeyListener(fVar);
        searchAutoComplete.setOnFocusChangeListener(new cs0(this));
        setIconifiedByDefault(typedArray.getBoolean(R$styleable.SearchView_iconifiedByDefault, true));
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R$styleable.SearchView_android_maxWidth, -1);
        if (dimensionPixelSize != -1) {
            setMaxWidth(dimensionPixelSize);
        }
        this.q0 = typedArray.getText(R$styleable.SearchView_defaultQueryHint);
        this.x0 = typedArray.getText(R$styleable.SearchView_queryHint);
        int i4 = typedArray.getInt(R$styleable.SearchView_android_imeOptions, -1);
        if (i4 != -1) {
            setImeOptions(i4);
        }
        int i5 = typedArray.getInt(R$styleable.SearchView_android_inputType, -1);
        if (i5 != -1) {
            setInputType(i5);
        }
        setFocusable(typedArray.getBoolean(R$styleable.SearchView_android_focusable, true));
        ouVarP.S();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.o0 = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.p0 = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.e0 = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new ng0(1, this));
        }
        y(this.t0);
        v();
    }

    public void setOnCloseListener(es0 es0Var) {
    }

    public void setOnQueryTextListener(fs0 fs0Var) {
    }

    public void setOnSuggestionListener(gs0 gs0Var) {
    }
}
