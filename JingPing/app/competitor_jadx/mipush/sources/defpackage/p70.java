package defpackage;

import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class p70 {
    public static final HashMap b;
    public final HashMap a = new HashMap();

    static {
        HashMap map = new HashMap();
        b = map;
        try {
            map.put("KeyAttribute", d70.class.getConstructor(new Class[0]));
            map.put("KeyPosition", r70.class.getConstructor(new Class[0]));
            map.put("KeyCycle", f70.class.getConstructor(new Class[0]));
            map.put("KeyTimeCycle", s70.class.getConstructor(new Class[0]));
            map.put("KeyTrigger", t70.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e) {
            Log.e("KeyFrames", "unable to load", e);
        }
    }

    public p70(XmlResourceParser xmlResourceParser) {
        try {
            int eventType = xmlResourceParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlResourceParser.getName();
                    HashMap map = b;
                    if (map.containsKey(name)) {
                        try {
                            if (((Constructor) map.get(name)).newInstance(new Object[0]) != null) {
                                throw new ClassCastException();
                            }
                            Xml.asAttributeSet(xmlResourceParser);
                            throw null;
                        } catch (Exception e) {
                            Log.e("KeyFrames", "unable to create ", e);
                            eventType = xmlResourceParser.next();
                        }
                    } else {
                        name.equalsIgnoreCase("CustomAttribute");
                    }
                } else if (eventType == 3 && "KeyFrameSet".equals(xmlResourceParser.getName())) {
                    return;
                }
                eventType = xmlResourceParser.next();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    public final void a(ef0 ef0Var) {
        HashMap map = this.a;
        ArrayList arrayList = (ArrayList) map.get(Integer.valueOf(ef0Var.b));
        if (arrayList != null) {
            ef0Var.u.addAll(arrayList);
        }
        ArrayList arrayList2 = (ArrayList) map.get(-1);
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            if (it.hasNext()) {
                if (it.next() != null) {
                    throw new ClassCastException();
                }
                String str = ((ConstraintLayout.LayoutParams) ef0Var.a.getLayoutParams()).U;
                throw null;
            }
        }
    }
}
