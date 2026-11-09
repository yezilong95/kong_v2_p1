package androidx.appcompat.widget;

import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class e implements View.OnClickListener {
    public final /* synthetic */ SearchView E;

    public e(SearchView searchView) {
        this.E = searchView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SearchView searchView = this.E;
        ImageView imageView = searchView.a0;
        SearchView.SearchAutoComplete searchAutoComplete = searchView.T;
        if (view == imageView) {
            searchView.y(false);
            searchAutoComplete.requestFocus();
            searchAutoComplete.setImeVisibility(true);
            View.OnClickListener onClickListener = searchView.s0;
            if (onClickListener != null) {
                onClickListener.onClick(searchView);
                return;
            }
            return;
        }
        if (view == searchView.c0) {
            searchView.o();
            return;
        }
        if (view == searchView.b0) {
            searchView.s();
            return;
        }
        if (view != searchView.d0) {
            if (view == searchAutoComplete) {
                searchView.n();
                return;
            }
            return;
        }
        SearchableInfo searchableInfo = searchView.F0;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (!searchableInfo.getVoiceSearchLaunchWebSearch()) {
                if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                    searchView.getContext().startActivity(searchView.m(searchView.p0, searchableInfo));
                }
            } else {
                Intent intent = new Intent(searchView.o0);
                ComponentName searchActivity = searchableInfo.getSearchActivity();
                intent.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
                searchView.getContext().startActivity(intent);
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }
}
