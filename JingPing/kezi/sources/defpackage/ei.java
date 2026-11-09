package defpackage;

import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

/* JADX INFO: loaded from: classes.dex */
public abstract class ei extends BaseAdapter implements Filterable {
    public boolean b;
    public boolean c;
    public Cursor d;
    public int e;
    public ci f;
    public di g;
    public fi h;

    public abstract void a(View view, Cursor cursor);

    public void b(Cursor cursor) {
        Cursor cursor2 = this.d;
        if (cursor == cursor2) {
            cursor2 = null;
        } else {
            if (cursor2 != null) {
                ci ciVar = this.f;
                if (ciVar != null) {
                    cursor2.unregisterContentObserver(ciVar);
                }
                di diVar = this.g;
                if (diVar != null) {
                    cursor2.unregisterDataSetObserver(diVar);
                }
            }
            this.d = cursor;
            if (cursor != null) {
                ci ciVar2 = this.f;
                if (ciVar2 != null) {
                    cursor.registerContentObserver(ciVar2);
                }
                di diVar2 = this.g;
                if (diVar2 != null) {
                    cursor.registerDataSetObserver(diVar2);
                }
                this.e = cursor.getColumnIndexOrThrow("_id");
                this.b = true;
                notifyDataSetChanged();
            } else {
                this.e = -1;
                this.b = false;
                notifyDataSetInvalidated();
            }
        }
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    public abstract String c(Cursor cursor);

    public abstract View d(ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public final int getCount() {
        Cursor cursor;
        if (!this.b || (cursor = this.d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.b) {
            return null;
        }
        this.d.moveToPosition(i);
        if (view == null) {
            jl0 jl0Var = (jl0) this;
            view = jl0Var.k.inflate(jl0Var.j, viewGroup, false);
        }
        a(view, this.d);
        return view;
    }

    @Override // android.widget.Filterable
    public final Filter getFilter() {
        if (this.h == null) {
            fi fiVar = new fi();
            fiVar.a = this;
            this.h = fiVar;
        }
        return this.h;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        Cursor cursor;
        if (!this.b || (cursor = this.d) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.d;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        Cursor cursor;
        if (this.b && (cursor = this.d) != null && cursor.moveToPosition(i)) {
            return this.d.getLong(this.e);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.d.moveToPosition(i)) {
            throw new IllegalStateException(hk.g(i, "couldn't move cursor to position "));
        }
        if (view == null) {
            view = d(viewGroup);
        }
        a(view, this.d);
        return view;
    }
}
