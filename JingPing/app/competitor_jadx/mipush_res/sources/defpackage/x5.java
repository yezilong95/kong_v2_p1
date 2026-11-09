package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.PowerManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class x5 extends z5 {
    public final /* synthetic */ int c = 0;
    public final /* synthetic */ c6 d;
    public final Object e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x5(c6 c6Var, ou ouVar) {
        super(c6Var);
        this.d = c6Var;
        this.e = ouVar;
    }

    @Override // defpackage.z5
    public final IntentFilter f() {
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

    @Override // defpackage.z5
    public final int g() {
        Location location;
        boolean z;
        long j;
        switch (this.c) {
            case 0:
                return ((PowerManager) this.e).isPowerSaveMode() ? 2 : 1;
            default:
                ou ouVar = (ou) this.e;
                d21 d21Var = (d21) ouVar.H;
                if (d21Var.b <= System.currentTimeMillis()) {
                    Context context = (Context) ouVar.F;
                    int iO = fa0.o(context, "android.permission.ACCESS_COARSE_LOCATION");
                    Location lastKnownLocation = null;
                    LocationManager locationManager = (LocationManager) ouVar.G;
                    if (iO == 0) {
                        try {
                        } catch (Exception e) {
                            Log.d("TwilightManager", "Failed to get last known location", e);
                        }
                        Location lastKnownLocation2 = locationManager.isProviderEnabled("network") ? locationManager.getLastKnownLocation("network") : null;
                        location = lastKnownLocation2;
                    } else {
                        location = null;
                    }
                    if (fa0.o(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
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
                        if (c21.d == null) {
                            c21.d = new c21();
                        }
                        c21 c21Var = c21.d;
                        c21Var.a(location.getLatitude(), location.getLongitude(), jCurrentTimeMillis - 86400000);
                        c21Var.a(location.getLatitude(), location.getLongitude(), jCurrentTimeMillis);
                        z = c21Var.c == 1;
                        long j2 = c21Var.b;
                        long j3 = c21Var.a;
                        c21Var.a(location.getLatitude(), location.getLongitude(), jCurrentTimeMillis + 86400000);
                        long j4 = c21Var.b;
                        if (j2 == -1 || j3 == -1) {
                            j = jCurrentTimeMillis + 43200000;
                        } else {
                            if (jCurrentTimeMillis <= j3) {
                                j4 = jCurrentTimeMillis > j2 ? j3 : j2;
                            }
                            j = j4 + 60000;
                        }
                        d21Var.a = z;
                        d21Var.b = j;
                    } else {
                        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
                        int i = Calendar.getInstance().get(11);
                        if (i < 6 || i >= 22) {
                            z = true;
                        }
                    }
                    break;
                } else {
                    z = d21Var.a;
                }
                return z ? 2 : 1;
        }
    }

    @Override // defpackage.z5
    public final void k() throws IllegalAccessException {
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
    public x5(c6 c6Var, Context context) {
        super(c6Var);
        this.d = c6Var;
        this.e = (PowerManager) context.getApplicationContext().getSystemService("power");
    }
}
