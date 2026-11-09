package defpackage;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class jm extends BaseAdapter {
    public static final int H;
    public final Calendar E;
    public final int F;
    public final int G;

    static {
        H = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public jm() {
        Calendar calendarG = h31.g(null);
        this.E = calendarG;
        this.F = calendarG.getMaximum(7);
        this.G = calendarG.getFirstDayOfWeek();
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.F;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        int i2 = this.F;
        if (i >= i2) {
            return null;
        }
        int i3 = i + this.G;
        if (i3 > i2) {
            i3 -= i2;
        }
        return Integer.valueOf(i3);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_day_of_week, viewGroup, false);
        }
        int i2 = i + this.G;
        int i3 = this.F;
        if (i2 > i3) {
            i2 -= i3;
        }
        Calendar calendar = this.E;
        calendar.set(7, i2);
        textView.setText(calendar.getDisplayName(7, H, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(R$string.mtrl_picker_day_of_week_column_header), calendar.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }

    public jm(int i) {
        Calendar calendarG = h31.g(null);
        this.E = calendarG;
        this.F = calendarG.getMaximum(7);
        this.G = i;
    }
}
