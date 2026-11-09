package org.bouncycastle.crypto;

import defpackage.zn;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
class NativeFeatures {
    public static final Logger a = Logger.getLogger(NativeFeatures.class.getName());

    public static boolean a() {
        try {
            return nativeAES();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native aes exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean b() {
        try {
            return nativeCBC();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native cbc exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean c() {
        try {
            return nativeCBCPC();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.WARNING;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native cbc packet cipher exception: "), logger, Level.WARNING, e);
            return false;
        }
    }

    public static boolean d() {
        try {
            return nativeCCM();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native ccm exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean e() {
        try {
            return nativeCCMPC();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.WARNING;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native ccm packet cipher exception: "), logger, Level.WARNING, e);
            return false;
        }
    }

    public static boolean f() {
        try {
            return nativeCFB();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native cfb exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean g() {
        try {
            return nativeCFBPC();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.WARNING;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native cfb packet cipher exception: "), logger, Level.WARNING, e);
            return false;
        }
    }

    public static boolean h() {
        try {
            return nativeCTR();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native ctr exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean i() {
        try {
            return nativeCTRPC();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.WARNING;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native ctr packet cipher exception: "), logger, Level.WARNING, e);
            return false;
        }
    }

    public static boolean j() {
        try {
            return nativeGCM();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native gcm exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean k() {
        try {
            return nativeGCMPC();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.WARNING;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native gcm packet cipher exception: "), logger, Level.WARNING, e);
            return false;
        }
    }

    public static boolean l() {
        try {
            return nativeGCMSIV();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native gcm-siv exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean m() {
        try {
            return nativeGCMSIVPC();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.WARNING;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native gcm-siv packet cipher exception: "), logger, Level.WARNING, e);
            return false;
        }
    }

    public static boolean n() {
        try {
            return nativeRand();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native rand exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    private static native boolean nativeAES();

    private static native boolean nativeCBC();

    private static native boolean nativeCBCPC();

    private static native boolean nativeCCM();

    private static native boolean nativeCCMPC();

    private static native boolean nativeCFB();

    private static native boolean nativeCFBPC();

    private static native boolean nativeCTR();

    private static native boolean nativeCTRPC();

    private static native boolean nativeGCM();

    private static native boolean nativeGCMPC();

    private static native boolean nativeGCMSIV();

    private static native boolean nativeGCMSIVPC();

    private static native boolean nativeMulAcc();

    private static native boolean nativeRSA();

    private static native boolean nativeRand();

    private static native boolean nativeSHA224();

    private static native boolean nativeSHA256();

    private static native boolean nativeSHA3();

    private static native boolean nativeSHA384();

    private static native boolean nativeSHA512();

    private static native boolean nativeSHAKE();

    private static native boolean nativeSeed();

    private static native boolean nativeSlhDSASha256();

    public static boolean o() {
        try {
            return nativeSHA224();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native sha exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean p() {
        try {
            return nativeSHA256();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native sha exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean q() {
        try {
            return nativeSHA384();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native sha exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean r() {
        try {
            return nativeSHA512();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native sha exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean s() {
        try {
            return nativeSeed();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native seed exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean t() {
        try {
            return nativeMulAcc();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native multiply and accumulate exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean u() {
        try {
            return nativeSHA3();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native sha3 exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean v() {
        try {
            return nativeSHAKE();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native shake exception: "), logger, Level.FINE, e);
            return false;
        }
    }

    public static boolean w() {
        try {
            return nativeSlhDSASha256();
        } catch (UnsatisfiedLinkError e) {
            Level level = Level.FINE;
            Logger logger = a;
            if (!logger.isLoggable(level)) {
                return false;
            }
            zn.t(e, new StringBuilder("native shake exception: "), logger, Level.FINE, e);
            return false;
        }
    }
}
