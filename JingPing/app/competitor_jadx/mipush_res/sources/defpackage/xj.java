package defpackage;

import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

/* JADX INFO: loaded from: classes.dex */
public abstract class xj extends BaseAdapter implements Filterable {
    public boolean E;
    public boolean F;
    public Cursor G;
    public int H;
    public vj I;
    public wj J;
    public yj K;

    public abstract void a(View view, Cursor cursor);

    public void b(Cursor cursor) {
        Cursor cursor2 = this.G;
        if (cursor == cursor2) {
            cursor2 = null;
        } else {
            if (cursor2 != null) {
                vj vjVar = this.I;
                if (vjVar != null) {
                    cursor2.unregisterContentObserver(vjVar);
                }
                wj wjVar = this.J;
                if (wjVar != null) {
                    cursor2.unregisterDataSetObserver(wjVar);
                }
            }
            this.G = cursor;
            if (cursor != null) {
                vj vjVar2 = this.I;
                if (vjVar2 != null) {
                    cursor.registerContentObserver(vjVar2);
                }
                wj wjVar2 = this.J;
                if (wjVar2 != null) {
                    cursor.registerDataSetObserver(wjVar2);
                }
                this.H = cursor.getColumnIndexOrThrow("_id");
                this.E = true;
                notifyDataSetChanged();
            } else {
                this.H = -1;
                this.E = false;
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
        if (!this.E || (cursor = this.G) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.E) {
            return null;
        }
        this.G.moveToPosition(i);
        if (view == null) {
            xx0 xx0Var = (xx0) this;
            view = xx0Var.N.inflate(xx0Var.M, viewGroup, false);
        }
        a(view, this.G);
        return view;
    }

    @Override // android.widget.Filterable
    public final Filter getFilter() {
        if (this.K == null) {
            yj yjVar = new yj();
            yjVar.a = this;
            this.K = yjVar;
        }
        return this.K;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        Cursor cursor;
        if (!this.E || (cursor = this.G) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.G;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        Cursor cursor;
        if (this.E && (cursor = this.G) != null && cursor.moveToPosition(i)) {
            return this.G.getLong(this.H);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.E) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.G.moveToPosition(i)) {
            throw new IllegalStateException(zn.j(i, "couldn't move cursor to position "));
        }
        if (view == null) {
            view = d(viewGroup);
        }
        a(view, this.G);
        return view;
    }
}
