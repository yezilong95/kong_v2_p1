package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.PowerManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class s4 extends u4 {
    public final /* synthetic */ int c = 0;
    public final /* synthetic */ x4 d;
    public final Object e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s4(x4 x4Var, po poVar) {
        super(x4Var);
        this.d = x4Var;
        this.e = poVar;
    }

    @Override // defpackage.u4
    public final IntentFilter e() {
        switch (this.c) {
            case 0:
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                return intentFilter;
            default:
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction("android.intent.action.TIME_SET");
                intentFilter2.addAction("android.intent.action.TIMEZONE_CHANGED");
                intentFilter2.addAction("android.intent.action.TIME_TICK");
                return intentFilter2;
        }
    }

    @Override // defpackage.u4
    public final int f() {
        Location location;
        boolean z;
        long j;
        switch (this.c) {
            case 0:
                return ((PowerManager) this.e).isPowerSaveMode() ? 2 : 1;
            default:
                po poVar = (po) this.e;
                qp0 qp0Var = (qp0) poVar.e;
                if (qp0Var.b <= System.currentTimeMillis()) {
                    Context context = (Context) poVar.c;
                    int iG = nv0.g(context, "android.permission.ACCESS_COARSE_LOCATION");
                    Location lastKnownLocation = null;
                    LocationManager locationManager = (LocationManager) poVar.d;
                    if (iG == 0) {
                        try {
                        } catch (Exception e) {
                            Log.d("TwilightManager", "Failed to get last known location", e);
                        }
                        Location lastKnownLocation2 = locationManager.isProviderEnabled("network") ? locationManager.getLastKnownLocation("network") : null;
                        location = lastKnownLocation2;
                    } else {
                        location = null;
                    }
                    if (nv0.g(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                        try {
                            if (locationManager.isProviderEnabled("gps")) {
                                lastKnownLocation = locationManager.getLastKnownLocation("gps");
                            }
                        } catch (Exception e2) {
                            Log.d("TwilightManager", "Failed to get last known location", e2);
                        }
                    }
                    if (lastKnownLocation == null || location == null ? lastKnownLocation != null : lastKnownLocation.getTime() > location.getTime()) {
                        location = lastKnownLocation;
                    }
                    if (location != null) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (pp0.d == null) {
                            pp0.d = new pp0();
                        }
                        pp0 pp0Var = pp0.d;
                        pp0Var.a(location.getLatitude(), location.getLongitude(), jCurrentTimeMillis - 86400000);
                        pp0Var.a(location.getLatitude(), location.getLongitude(), jCurrentTimeMillis);
                        z = pp0Var.c == 1;
                        long j2 = pp0Var.b;
                        long j3 = pp0Var.a;
                        pp0Var.a(location.getLatitude(), location.getLongitude(), jCurrentTimeMillis + 86400000);
                        long j4 = pp0Var.b;
                        if (j2 == -1 || j3 == -1) {
                            j = jCurrentTimeMillis + 43200000;
                        } else {
                            if (jCurrentTimeMillis <= j3) {
                                j4 = jCurrentTimeMillis > j2 ? j3 : j2;
                            }
                            j = j4 + 60000;
                        }
                        qp0Var.a = z;
                        qp0Var.b = j;
                    } else {
                        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
                        int i = Calendar.getInstance().get(11);
                        if (i < 6 || i >= 22) {
                            z = true;
                        }
                    }
                    break;
                } else {
                    z = qp0Var.a;
                }
                return z ? 2 : 1;
        }
    }

    @Override // defpackage.u4
    public final void h() {
        switch (this.c) {
            case 0:
                this.d.n(true, true);
                break;
            default:
                this.d.n(true, true);
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s4(x4 x4Var, Context context) {
        super(x4Var);
        this.d = x4Var;
        this.e = (PowerManager) context.getApplicationContext().getSystemService("power");
    }
}
