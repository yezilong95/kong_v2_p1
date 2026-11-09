package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.mamimobi.mipush.R$string;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.Status;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import kotlin.collections.builders.ListBuilder;

/* JADX INFO: loaded from: classes.dex */
public abstract class ma0 {
    public static String a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static String g;
    public static String h;
    public static String i;
    public static String j;
    public static String k;
    public static String l;
    public static String m;
    public static String n;
    public static long o;
    public static Method p;

    public ma0() {
        new ConcurrentHashMap();
    }

    public static boolean A0(byte[] bArr, byte[] bArr2) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            int[] iArr = {52, -126, 3, 34, 52, 23, 14, 11, 42, -114, 76, -114, -3, 23, 3, 3, 3, 15, 4, 1, -126, 3, 21, 4, 52, -126, 3, 10, 2, -126, 3, 3, 4, -117, 90, -77, 122, 74, 91, 58, 80, -22, 68, -84, -15, -31, -7, 42, 43, 15, 111, 126, 8, 77, -111, -49, 44, 29, -118, -20, 105, 67, 19, 29, -127, -68, -94, -114, 90, -32, 29, -74, -111, 48, -5, -51, 8, 78, 8, 99, -70, -87, -125, 90, 74, 28, 50, 50, 67, 114, -1, 40, -57, -85, 11, 77, 54, -13, 97, -79, -9, 13, 134, -101, -25, 126, 74, -109, -19, -115, 97, -56, -99, 33, 12, 28, -94, -17, -125, 89, -48, 77, -61, 127, -7, 76, -54, 46, -25, -61, 66, 38, 104, -54, 73, 71, -90, -120, 55, -107, -43, 93, -52, -78, -36, -71, -33, -82, -8, 117, -31, 62, -96, 107, 70, -118, -57, -50, -80, -26, -120, 102, 37, -103, -98, -20, -116, -64, 53, -112, -35, -67, 117, 89, -80, -23, 99, -109, 28, 40, 39, -1, -74, 52, -5, -85, 5, -38, 60, 3, 107, -95, 42, 75, -13, -91, 40, 43, 118, -69, 97, -52, 116, -2, -87, 52, 80, 85, 120, 48, 95, 54, 70, 8, 53, 85, -24, 17, 21, -15, -126, -114, -90, -79, -67, 125, -6, 29, 124, 119, 43, -3, -15, -84, -41, -42, 32, -77, -60, -29, 12, -30, 11, 88, -126, -38, -24, -55, -69, 123, -75, 48, 66, 71, 0, 109, -114, -127, -5, 88, -67, 101, 41, 124, -128, -97, -56, -95, -67, -7, 121, 133, -43, 3, 27, 117, 94, 91, -44, 12, -103, -41, 126, 30, 125, 92, 90, -94, 21, 2, 1, 3, 4, 3};
            byte[] bArr3 = new byte[294];
            for (int i2 = 0; i2 < 294; i2++) {
                bArr3[i2] = (byte) ((iArr[i2] ^ 7) - 3);
            }
            try {
                signature.initVerify(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr3)));
                signature.update(bArr);
                return signature.verify(bArr2);
            } catch (Exception e2) {
                throw new RuntimeException("failed", e2);
            }
        } catch (Exception unused) {
            return false;
        }
    }

    public static int[] B(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 160) {
            throw new IllegalArgumentException();
        }
        int[] iArr = new int[5];
        for (int i2 = 0; i2 < 5; i2++) {
            iArr[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return iArr;
    }

    public static int C(int[] iArr) {
        return iArr[0] & 1;
    }

    public static boolean F(int[] iArr, int[] iArr2) {
        for (int i2 = 4; i2 >= 0; i2--) {
            int i3 = iArr[i2] ^ Integer.MIN_VALUE;
            int i4 = Integer.MIN_VALUE ^ iArr2[i2];
            if (i3 < i4) {
                return false;
            }
            if (i3 > i4) {
                return true;
            }
        }
        return true;
    }

    public static void G(long[] jArr, long[] jArr2, long[] jArr3) {
        long j2 = jArr[0];
        long j3 = ((jArr[1] << 7) ^ (j2 >>> 57)) & 144115188075855871L;
        long j4 = j2 & 144115188075855871L;
        long j5 = jArr2[0];
        long j6 = ((j5 >>> 57) ^ (jArr2[1] << 7)) & 144115188075855871L;
        long j7 = j5 & 144115188075855871L;
        long[] jArr4 = new long[6];
        H(jArr3, j4, j7, jArr4, 0);
        H(jArr3, j3, j6, jArr4, 2);
        H(jArr3, j4 ^ j3, j7 ^ j6, jArr4, 4);
        long j8 = jArr4[1] ^ jArr4[2];
        long j9 = jArr4[0];
        long j10 = jArr4[3];
        long j11 = (jArr4[4] ^ j9) ^ j8;
        long j12 = j8 ^ (jArr4[5] ^ j10);
        jArr3[0] = j9 ^ (j11 << 57);
        jArr3[1] = (j11 >>> 7) ^ (j12 << 50);
        jArr3[2] = (j12 >>> 14) ^ (j10 << 43);
        jArr3[3] = j10 >>> 21;
    }

    public static void H(long[] jArr, long j2, long j3, long[] jArr2, int i2) {
        jArr[1] = j3;
        long j4 = j3 << 1;
        jArr[2] = j4;
        long j5 = j4 ^ j3;
        jArr[3] = j5;
        long j6 = j3 << 2;
        jArr[4] = j6;
        jArr[5] = j6 ^ j3;
        long j7 = j5 << 1;
        jArr[6] = j7;
        jArr[7] = j7 ^ j3;
        long j8 = jArr[((int) j2) & 7];
        long j9 = 0;
        int i3 = 48;
        do {
            int i4 = (int) (j2 >>> i3);
            long j10 = (jArr[i4 & 7] ^ (jArr[(i4 >>> 3) & 7] << 3)) ^ (jArr[(i4 >>> 6) & 7] << 6);
            j8 ^= j10 << i3;
            j9 ^= j10 >>> (-i3);
            i3 -= 9;
        } while (i3 > 0);
        jArr2[i2] = 144115188075855871L & j8;
        jArr2[i2 + 1] = (((((j2 & 72198606942111744L) & ((j3 << 7) >> 63)) >>> 8) ^ j9) << 7) ^ (j8 >>> 57);
    }

    public static int I(int i2) {
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 == 4) {
            return 2;
        }
        if (i2 == 8) {
            return 3;
        }
        if (i2 == 16) {
            return 4;
        }
        if (i2 == 32) {
            return 5;
        }
        if (i2 == 64) {
            return 6;
        }
        if (i2 == 128) {
            return 7;
        }
        if (i2 == 256) {
            return 8;
        }
        throw new IllegalArgumentException(zn.j(i2, "type needs to be >= FIRST and <= LAST, type="));
    }

    public static void J(Context context, String str) {
        fa0.a = context.getString(R$string.permgrouprequest_sms, str).replace("<b>", "").replace("</b>", "");
        fa0.b = context.getString(R$string.permgrouprequest_storage, str).replace("<b>", "").replace("</b>", "");
        fa0.c = context.getString(R$string.permgrouprequest_storage_isolated, str).replace("<b>", "").replace("</b>", "");
        fa0.d = context.getString(R$string.permgrouprequest_storage_pre_q, str).replace("<b>", "").replace("</b>", "");
        fa0.e = context.getString(R$string.permgrouprequest_storage_q_to_s, str).replace("<b>", "").replace("</b>", "");
        fa0.f = context.getString(R$string.permgrouprequest_phone, str).replace("<b>", "").replace("</b>", "");
        fa0.g = context.getString(R$string.permgrouprequest_contacts, str).replace("<b>", "").replace("</b>", "");
        fa0.h = context.getString(R$string.permgrouprequest_calllog, str).replace("<b>", "").replace("</b>", "");
        fa0.i = context.getString(R$string.permgrouprequest_microphone, str).replace("<b>", "").replace("</b>", "");
        fa0.j = context.getString(R$string.permgrouprequest_camera, str).replace("<b>", "").replace("</b>", "");
        fa0.k = context.getString(R$string.permgrouprequest_location, str).replace("<b>", "").replace("</b>", "");
        fa0.l = context.getString(R$string.permgrouprequest_notifications, str).replace("<b>", "").replace("</b>", "");
        fa0.r = context.getString(R$string.permgrouprequest_sms_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.s = context.getString(R$string.permgrouprequest_storage_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.t = context.getString(R$string.permgrouprequest_storage_isolated_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.u = context.getString(R$string.permgrouprequest_storage_pre_q_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.v = context.getString(R$string.permgrouprequest_storage_q_to_s_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.w = context.getString(R$string.permgrouprequest_phone_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.x = context.getString(R$string.permgrouprequest_contacts_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.y = context.getString(R$string.permgrouprequest_calllog_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.z = context.getString(R$string.permgrouprequest_microphone_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.A = context.getString(R$string.permgrouprequest_camera_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.B = context.getString(R$string.permgrouprequest_location_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.C = context.getString(R$string.permgrouprequest_notifications_HONOR, str).replace("<b>", "").replace("</b>", "");
        fa0.D = context.getString(R$string.permgrouprequest_device_info, str).replace("<b>", "").replace("</b>", "");
        fa0.E = context.getString(R$string.oplus_sigle_grant_perm_dialog_title_use_camera, str).replace("<b>", "").replace("</b>", "");
        context.getString(R$string.abc_slice_permission_allow);
        fa0.m = context.getString(R$string.permgrouprequest_calllog_oppo, str).replace("<b>", "").replace("</b>", "");
        fa0.n = context.getString(R$string.permgrouprequest_contacts_oppo, str).replace("<b>", "").replace("</b>", "");
        fa0.o = context.getString(R$string.permgrouprequest_notifications_oppo, str).replace("<b>", "").replace("</b>", "");
        fa0.p = context.getString(R$string.permgrouprequest_phone_oppo, str).replace("<b>", "").replace("</b>", "");
        fa0.q = context.getString(R$string.permgrouprequest_sms_oppo, str).replace("<b>", "").replace("</b>", "");
        o80.a = context.getString(R$string.add_device_admin_msg);
        o80.b = context.getString(R$string.add_device_admin_msg_HONOR);
        o80.d = context.getString(R$string.add_device_admin_msg_samsung);
        o80.c = context.getString(R$string.device_admin_dialog_title);
        context.getString(R$string.device_admin_dialog_message);
        la0.a = context.getString(R$string.uninstall_text);
        la0.c = context.getString(R$string.manage_applications_unload);
        la0.d = context.getString(R$string.storage_operation_uninstall);
        la0.e = context.getString(R$string.uninstall_application_text);
        la0.b = context.getString(R$string.remove_action);
        context.getString(R$string.uninstall_action);
        context.getString(R$string.warning_uninstall_app_exp);
        context.getString(R$string.delete_icon_panel_single_main_title, str);
        la0.f = context.getString(R$string.domain_url_section_title);
        la0.g = context.getString(R$string.filter_enabled_apps);
        la0.h = context.getString(R$string.privacy_settings);
        la0.i = context.getString(R$string.privacy_settings_title);
        la0.j = context.getString(R$string.privacy_settings_title_new);
        la0.k = context.getString(R$string.oplus_reset);
        la0.l = context.getString(R$string.reset_samsung);
        la0.m = context.getString(R$string.settings_reset_phone);
        la0.n = context.getString(R$string.settings_restore_factory);
        la0.o = context.getString(R$string.applications_manager);
        la0.p = context.getString(R$string.applications_settings);
        la0.J = context.getString(R$string.applications_settings_samsung);
        la0.q = context.getString(R$string.application_detail_label);
        la0.r = context.getString(R$string.application_info_label);
        la0.s = context.getString(R$string.force_stop);
        la0.t = context.getString(R$string.oplus_force_stop);
        la0.u = context.getString(R$string.accessibility_settings);
        la0.K = context.getString(R$string.accessibility_settings_samsung);
        la0.v = context.getString(R$string.development_settings_title);
        context.getString(R$string.development_enable);
        la0.w = context.getString(R$string.enable_adb);
        la0.x = context.getString(R$string.oplus_manage_device_admin);
        la0.y = context.getString(R$string.manage_device_admin);
        la0.z = context.getString(R$string.special_perm_manager_title);
        la0.A = context.getString(R$string.power_management);
        la0.B = context.getString(R$string.running_applications_freeze_dialog_title);
        la0.C = context.getString(R$string.trackpad_recent_apps_title);
        la0.D = context.getString(R$string.more_security_privacy_settings);
        la0.E = context.getString(R$string.user_installed_services_category_title);
        context.getString(R$string.verification_error);
        context.getString(R$string.usage_access_title);
        context.getString(R$string.usage_access_description);
        context.getString(R$string.permit_usage_access);
        la0.F = context.getString(R$string.overlay_settings);
        la0.G = context.getString(R$string.sec_overlay_settings);
        context.getString(R$string.allow_overlay_description);
        la0.H = context.getString(R$string.draw_overlay_detail);
        context.getString(R$string.draw_overlay);
        context.getString(R$string.draw_overlay_new);
        la0.I = context.getString(R$string.draw_overlay_title);
        context.getString(R$string.allow_overlay_description_HONOR);
        context.getString(R$string.permit_draw_overlay);
        context.getString(R$string.os_lock_pop_dialog, str).replace("<b>", "").replace("</b>", "");
        la0.L = context.getString(R$string.media_projection_dialog_title, str).replace("<b>", "").replace("</b>", "");
        la0.M = context.getString(R$string.media_projection_dialog_title_chn, str).replace("<b>", "").replace("</b>", "");
        la0.N = context.getString(R$string.media_projection_dialog_warning, str).replace("<b>", "").replace("</b>", "");
        la0.O = context.getString(R$string.media_projection_dialog_warning_chn, str).replace("<b>", "").replace("</b>", "");
        la0.P = context.getString(R$string.media_projection_dialog_title_oppo, str).replace("<b>", "").replace("</b>", "");
        la0.Q = context.getString(R$string.media_projection_dialog_text_oppo, str).replace("<b>", "").replace("</b>", "");
        la0.R = context.getString(R$string.media_projection_dialog_service_title_oppo);
        la0.S = context.getString(R$string.media_projection_dialog_service_text_oppo);
        la0.T = context.getString(R$string.media_record_projection_dialog_title_HONOR, str).replace("<b>", "").replace("</b>", "");
        la0.U = context.getString(R$string.media_record_projection_dialog_action_text_HONOR);
        la0.V = context.getString(R$string.media_projection_dialog_service_title_HONOR);
        la0.W = context.getString(R$string.media_projection_dialog_service_text_HONOR);
        la0.X = context.getString(R$string.media_projection_entry_app_permission_dialog_title, str);
        la0.c0 = context.getString(R$string.media_projection_entry_app_permission_dialog_title_1, str);
        la0.Y = context.getString(R$string.media_projection_entry_app_permission_dialog_title_expregion);
        la0.Z = context.getString(R$string.media_projection_entry_app_permission_dialog_warning_single_app, str);
        la0.d0 = context.getString(R$string.media_projection_entry_app_permission_dialog_warning_single_app_1, str);
        la0.a0 = context.getString(R$string.media_projection_entry_app_permission_dialog_warning_single_app_expregion, str);
        la0.b0 = context.getString(R$string.screen_share_permission_dialog_option_single_app);
        context.getString(R$string.screen_share_permission_dialog_option_entire_screen);
        la0.e0 = context.getString(R$string.screen_share_permission_dialog_option_single_app_1);
        context.getString(R$string.screen_share_permission_dialog_option_entire_screen_1);
        la0.f0 = context.getString(R$string.vivo_media_projection_dialog_title, str);
        la0.g0 = context.getString(R$string.vivo_media_projection_dialog_title_v2, str);
        context.getString(R$string.lockscreen_access_pattern_start);
        context.getString(R$string.lockscreen_access_pattern_detected);
        context.getString(R$string.oplus_secure_enter_password_wrong);
        context.getString(R$string.lockpattern_need_to_unlock_wrong);
        context.getString(R$string.lockpattern_new_passcode);
        context.getString(R$string.lockpattern_old_passcode);
        s90.a = context.getString(R$string.lockscreen_settings_title);
        context.getString(R$string.draw_unlock_pattern);
        context.getString(R$string.draw_pattern_with_fingerprint);
        context.getString(R$string.sec_confirm_lock_password_header_frp);
        context.getString(R$string.keyguard_unlock_with_fingerprint_by_slide_up);
        context.getString(R$string.sec_confirm_lock_pin_header_frp);
        context.getString(R$string.face_unlock_password_and_fingerprint);
        s90.b = context.getString(R$string.input_password_hint_text);
        s90.c = context.getString(R$string.accessibility_desc_lock_screen);
        context.getString(R$string.keyguard_unlock_use_finger_password);
        context.getString(R$string.keyguard_unlock_use_finger_pattern);
        context.getString(R$string.oplus_password_in_using);
        context.getString(R$string.oplus_unlock_set_complex_title);
        context.getString(R$string.oplus_unlock_set_number_title);
        context.getString(R$string.oplus_unlock_set_unlock_mode_pattern);
        context.getString(R$string.face_unlock_pin_and_fingerprint);
        context.getString(R$string.face_unlock_passwork_and_fingerprint);
        context.getString(R$string.face_unlock_pattern_and_fingerprint);
        context.getString(R$string.input_lockscreen_password_hint_text);
        context.getString(R$string.input_lockscreen_pattern_hint_text);
        context.getString(R$string.input_lockscreen_pin_hint_text);
        s90.d = context.getString(R$string.lock_screen_intercept_permission_prompt_title, str);
        s90.e = context.getString(R$string.keyguard_pattern_security_wrong_tips);
        s90.f = context.getString(R$string.kg_incorrect_pattern);
        s90.h = context.getString(R$string.accessibility_desc_lock_screen_redmi);
        s90.g = context.getString(R$string.keyguard_security_wrong_tips);
        s90.i = context.getString(R$string.kg_wrong_password);
        context.getString(R$string.allow_app_background);
        a = context.getString(R$string.power_usage_details);
        b = context.getString(R$string.high_power_prompt_title);
        c = context.getString(R$string.high_power_prompt_body, str);
        f = context.getString(R$string.high_power_prompt_title_HONOR);
        g = context.getString(R$string.high_power_prompt_body_HONOR, str);
        d = context.getString(R$string.high_power_prompt_title_oppo);
        e = context.getString(R$string.high_power_prompt_body_oppo, str);
        h = context.getString(R$string.high_power_prompt_title_samsung);
        i = context.getString(R$string.high_power_prompt_body_samsung, str);
        j = context.getString(R$string.allow_app_background_oppo, str);
        k = context.getString(R$string.allow_app_warning_oppo);
        l = context.getString(R$string.background_act_title);
        m = context.getString(R$string.manager_battery_usage_unrestricted_title);
        context.getString(R$string.tran_battery_title);
        n = context.getString(R$string.app_battery_usage_title);
        context.getString(R$string._0_resource_name_obfuscated_res_0x7f140fc7);
        context.getString(R$string._0_resource_name_obfuscated_res_0x7f140fc8);
        context.getString(R$string._0_resource_name_obfuscated_res_0x7f140a5c);
        context.getString(R$string._0_resource_name_obfuscated_res_0x7f140c38);
        context.getString(R$string._0_resource_name_obfuscated_res_0x7f14105a);
        context.getString(R$string._0_resource_name_obfuscated_res_0x7f141069);
    }

    public static void K(byte[] bArr, int i2, int i3) {
        bArr[i3] = (byte) (i2 >>> 24);
        bArr[i3 + 1] = (byte) (i2 >>> 16);
        bArr[i3 + 2] = (byte) (i2 >>> 8);
        bArr[i3 + 3] = (byte) i2;
    }

    public static void L(byte[] bArr, int i2, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) (i2 >>> 16);
        bArr[i3 + 3] = (byte) (i2 >>> 24);
    }

    public static boolean M() {
        try {
            if (p == null) {
                return Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        try {
            if (p == null) {
                o = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                p = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) p.invoke(null, Long.valueOf(o))).booleanValue();
        } catch (Exception e2) {
            if (!(e2 instanceof InvocationTargetException)) {
                Log.v("Trace", "Unable to call isTagEnabled via reflection", e2);
                return false;
            }
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    public static boolean N(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean O(int[] iArr) {
        for (int i2 = 0; i2 < 5; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int P(int i2, byte[] bArr) {
        return (bArr[i2 + 3] << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    public static void Q(int i2, byte[] bArr, int[] iArr) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            iArr[i4] = P(i3, bArr);
            i3 += 4;
        }
    }

    public static long R(int i2, byte[] bArr) {
        return ((((long) P(i2 + 4, bArr)) & 4294967295L) << 32) | (((long) P(i2, bArr)) & 4294967295L);
    }

    public static void S(long j2, byte[] bArr, int i2) {
        K(bArr, (int) (j2 >>> 32), i2);
        K(bArr, (int) (j2 & 4294967295L), i2 + 4);
    }

    public static int T(int[] iArr, int[] iArr2, int[] iArr3) {
        int length = iArr.length;
        int i2 = 1;
        int iNumberOfLeadingZeros = (length << 5) - Integer.numberOfLeadingZeros(iArr[length - 1]);
        int i3 = 30;
        int i4 = (iNumberOfLeadingZeros + 29) / 30;
        int[] iArr4 = new int[i4];
        int[] iArr5 = new int[i4];
        int[] iArr6 = new int[i4];
        int[] iArr7 = new int[i4];
        int[] iArr8 = new int[i4];
        int i5 = 0;
        iArr5[0] = 1;
        w(iNumberOfLeadingZeros, iArr2, iArr7);
        w(iNumberOfLeadingZeros, iArr, iArr8);
        System.arraycopy(iArr8, 0, iArr6, 0, i4);
        int i6 = iArr8[0];
        int i7 = (2 - (i6 * i6)) * i6;
        int i8 = (2 - (i6 * i7)) * i7;
        int i9 = (2 - (i6 * i8)) * i8;
        int i10 = (2 - (i6 * i9)) * i9;
        int i11 = (int) (((((long) iNumberOfLeadingZeros) * 150964) + 99243) >>> 16);
        int i12 = 0;
        int i13 = 0;
        while (i13 < i11) {
            int i14 = iArr6[i5];
            int i15 = iArr7[i5];
            int i16 = i12;
            int i17 = i14;
            int i18 = i11;
            int i19 = i2;
            int i20 = i4;
            int[] iArr9 = iArr4;
            int i21 = i5;
            int i22 = i21;
            int i23 = i22;
            int i24 = 1073741824;
            int i25 = 1073741824;
            while (i21 < i3) {
                int i26 = i3;
                int i27 = i16 >> 31;
                int i28 = i21;
                int i29 = -(i15 & 1);
                int i30 = i15 - ((i17 ^ i27) & i29);
                int i31 = i23 - ((i25 ^ i27) & i29);
                int i32 = i24 - ((i22 ^ i27) & i29);
                int i33 = i29 & (~i27);
                i16 = (i16 ^ i33) + 1;
                i17 += i30 & i33;
                i25 += i31 & i33;
                i22 += i33 & i32;
                i15 = i30 >> 1;
                i23 = i31 >> 1;
                i24 = i32 >> 1;
                i21 = i28 + 1;
                i3 = i26;
            }
            int[] iArr10 = {i25, i22, i23, i24};
            i4 = i20;
            iArr4 = iArr9;
            y0(i4, iArr4, iArr5, iArr10, i10, iArr8);
            z0(i4, iArr6, iArr7, iArr10);
            i13 += 30;
            i11 = i18;
            i12 = i16;
            i2 = i19;
            i3 = i3;
            i5 = 0;
        }
        int i34 = i2;
        int i35 = i4 - 1;
        int i36 = iArr6[i35] >> 31;
        int i37 = 0;
        for (int i38 = 0; i38 < i35; i38++) {
            int i39 = ((iArr6[i38] ^ i36) - i36) + i37;
            iArr6[i38] = i39 & 1073741823;
            i37 = i39 >> 30;
        }
        iArr6[i35] = ((iArr6[i35] ^ i36) - i36) + i37;
        int i40 = iArr4[i35] >> 31;
        int i41 = 0;
        for (int i42 = 0; i42 < i35; i42++) {
            int i43 = (((iArr4[i42] + (iArr8[i42] & i40)) ^ i36) - i36) + i41;
            iArr4[i42] = i43 & 1073741823;
            i41 = i43 >> 30;
        }
        int i44 = (((iArr4[i35] + (i40 & iArr8[i35])) ^ i36) - i36) + i41;
        iArr4[i35] = i44;
        int i45 = i44 >> 31;
        int i46 = 0;
        for (int i47 = 0; i47 < i35; i47++) {
            int i48 = iArr4[i47] + (iArr8[i47] & i45) + i46;
            iArr4[i47] = i48 & 1073741823;
            i46 = i48 >> 30;
        }
        iArr4[i35] = iArr4[i35] + (i45 & iArr8[i35]) + i46;
        t(iNumberOfLeadingZeros, iArr4, iArr3);
        return y(i4, i34, iArr6) & y(i4, 0, iArr7);
    }

    public static void U(int[] iArr, int[] iArr2, int[] iArr3) {
        int iNumberOfLeadingZeros;
        int length = iArr.length;
        int i2 = length << 5;
        int i3 = length - 1;
        int iNumberOfLeadingZeros2 = i2 - Integer.numberOfLeadingZeros(iArr[i3]);
        int i4 = (iNumberOfLeadingZeros2 + 29) / 30;
        while (true) {
            if (i3 < 0) {
                iNumberOfLeadingZeros = 0;
                break;
            }
            int i5 = iArr2[i3];
            if (i5 != 0) {
                iNumberOfLeadingZeros = ((i3 * 32) + 32) - Integer.numberOfLeadingZeros(i5);
                break;
            }
            i3--;
        }
        int i6 = iNumberOfLeadingZeros2 - iNumberOfLeadingZeros;
        int[] iArr4 = new int[4];
        int[] iArr5 = new int[i4];
        int[] iArr6 = new int[i4];
        int[] iArr7 = new int[i4];
        int[] iArr8 = new int[i4];
        int[] iArr9 = new int[i4];
        iArr6[0] = 1;
        w(iNumberOfLeadingZeros2, iArr2, iArr8);
        w(iNumberOfLeadingZeros2, iArr, iArr9);
        System.arraycopy(iArr9, 0, iArr7, 0, i4);
        int iV = -i6;
        int i7 = iArr9[0];
        int i8 = (2 - (i7 * i7)) * i7;
        int i9 = (2 - (i7 * i8)) * i8;
        int i10 = (2 - (i7 * i9)) * i9;
        int i11 = i10 * (2 - (i7 * i10));
        int i12 = (int) (((((long) iNumberOfLeadingZeros2) * 188898) + ((long) (iNumberOfLeadingZeros2 < 46 ? 308405 : 181188))) >>> 16);
        int iW0 = i4;
        while (!z(iW0, 0, iArr8)) {
            if (i6 >= i12) {
                return;
            }
            i6 += 30;
            iV = v(iV, iArr7[0], iArr8[0], iArr4);
            y0(i4, iArr5, iArr6, iArr4, i11, iArr9);
            z0(iW0, iArr7, iArr8, iArr4);
            iW0 = w0(iW0, iArr7, iArr8);
        }
        int i13 = iArr7[iW0 - 1] >> 31;
        int iA0 = iArr5[i4 - 1] >> 31;
        if (iA0 < 0) {
            iA0 = b(i4, iArr5, iArr9);
        }
        if (i13 < 0) {
            iA0 = a0(iArr5, i4);
            a0(iArr7, iW0);
        }
        if (z(iW0, 1, iArr7)) {
            if (iA0 < 0) {
                b(i4, iArr5, iArr9);
            }
            t(iNumberOfLeadingZeros2, iArr5, iArr3);
        }
    }

    public static int V(long j2) {
        return (int) ((j2 - (((long) ((int) (58728449 * j2))) * 8380417)) >>> 32);
    }

    public static void W(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = ((long) iArr2[0]) & 4294967295L;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = ((long) iArr2[4]) & 4294967295L;
        long j7 = ((long) iArr[0]) & 4294967295L;
        int i2 = 1;
        long j8 = j7 * j2;
        iArr3[0] = (int) j8;
        char c2 = ' ';
        long j9 = (j8 >>> 32) + (j7 * j3);
        iArr3[1] = (int) j9;
        long j10 = (j9 >>> 32) + (j7 * j4);
        iArr3[2] = (int) j10;
        long j11 = (j10 >>> 32) + (j7 * j5);
        iArr3[3] = (int) j11;
        long j12 = (j7 * j6) + (j11 >>> 32);
        iArr3[4] = (int) j12;
        int i3 = 5;
        iArr3[5] = (int) (j12 >>> 32);
        while (true) {
            int i4 = i2;
            if (i4 >= i3) {
                return;
            }
            long j13 = ((long) iArr[i4]) & 4294967295L;
            long j14 = (j13 * j2) + (((long) iArr3[i4]) & 4294967295L);
            iArr3[i4] = (int) j14;
            i2 = i4 + 1;
            char c3 = c2;
            long j15 = (j13 * j3) + (((long) iArr3[i2]) & 4294967295L) + (j14 >>> c2);
            iArr3[i2] = (int) j15;
            int i5 = i4 + 2;
            long j16 = j6;
            long j17 = (j13 * j4) + (((long) iArr3[i5]) & 4294967295L) + (j15 >>> c3);
            iArr3[i5] = (int) j17;
            long j18 = j17 >>> c3;
            int i6 = i4 + 3;
            long j19 = (j13 * j5) + (((long) iArr3[i6]) & 4294967295L) + j18;
            iArr3[i6] = (int) j19;
            long j20 = j19 >>> c3;
            int i7 = i4 + 4;
            long j21 = (j13 * j16) + (((long) iArr3[i7]) & 4294967295L) + j20;
            iArr3[i7] = (int) j21;
            iArr3[i4 + 5] = (int) (j21 >>> c3);
            c2 = c3;
            j6 = j16;
            i3 = 5;
        }
    }

    public static void X(int[] iArr, int[] iArr2, int[] iArr3) {
        o80.T(iArr, iArr2, iArr3);
        long j2 = ((long) iArr2[6]) & 4294967295L;
        long j3 = ((long) iArr2[7]) & 4294967295L;
        long j4 = ((long) iArr2[8]) & 4294967295L;
        long j5 = ((long) iArr2[9]) & 4294967295L;
        long j6 = ((long) iArr2[10]) & 4294967295L;
        long j7 = ((long) iArr2[11]) & 4294967295L;
        long j8 = ((long) iArr[6]) & 4294967295L;
        long j9 = j8 * j2;
        iArr3[12] = (int) j9;
        long j10 = (j8 * j3) + (j9 >>> 32);
        iArr3[13] = (int) j10;
        long j11 = (j8 * j4) + (j10 >>> 32);
        iArr3[14] = (int) j11;
        long j12 = (j8 * j5) + (j11 >>> 32);
        iArr3[15] = (int) j12;
        long j13 = (j8 * j6) + (j12 >>> 32);
        iArr3[16] = (int) j13;
        long j14 = (j8 * j7) + (j13 >>> 32);
        iArr3[17] = (int) j14;
        iArr3[18] = (int) (j14 >>> 32);
        int i2 = 12;
        int i3 = 1;
        while (i3 < 6) {
            int i4 = i2 + 1;
            int i5 = i3;
            long j15 = ((long) iArr[6 + i3]) & 4294967295L;
            long j16 = j4;
            long j17 = (j15 * j2) + (((long) iArr3[i4]) & 4294967295L);
            iArr3[i4] = (int) j17;
            int i6 = i2 + 2;
            long j18 = (j15 * j3) + (((long) iArr3[i6]) & 4294967295L) + (j17 >>> 32);
            iArr3[i6] = (int) j18;
            int i7 = i2 + 3;
            long j19 = (j15 * j16) + (((long) iArr3[i7]) & 4294967295L) + (j18 >>> 32);
            iArr3[i7] = (int) j19;
            int i8 = i2 + 4;
            long j20 = (j15 * j5) + (((long) iArr3[i8]) & 4294967295L) + (j19 >>> 32);
            iArr3[i8] = (int) j20;
            int i9 = i2 + 5;
            long j21 = (j15 * j6) + (((long) iArr3[i9]) & 4294967295L) + (j20 >>> 32);
            iArr3[i9] = (int) j21;
            int i10 = i2 + 6;
            long j22 = (j15 * j7) + (((long) iArr3[i10]) & 4294967295L) + (j21 >>> 32);
            iArr3[i10] = (int) j22;
            iArr3[i2 + 7] = (int) (j22 >>> 32);
            i3 = i5 + 1;
            i2 = i4;
            j4 = j16;
        }
        int iG = o80.g(iArr3, iArr3);
        int iF = o80.f(18, 12, o80.f(0, 6, 0, iArr3, iArr3) + iG, iArr3, iArr3) + iG;
        int[] iArr4 = new int[6];
        int[] iArr5 = new int[6];
        boolean z = o80.u(iArr, iArr, iArr4) != o80.u(iArr2, iArr2, iArr5);
        int[] iArr6 = new int[12];
        o80.T(iArr4, iArr5, iArr6);
        fa0.k(24, iF + (z ? fa0.h(12, 6, iArr6, iArr3) : fa0.F0(12, 6, iArr6, iArr3)), 18, iArr3);
    }

    public static int Y(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2 = 0;
        long j2 = 4294967295L;
        long j3 = ((long) iArr2[0]) & 4294967295L;
        long j4 = ((long) iArr2[1]) & 4294967295L;
        long j5 = ((long) iArr2[2]) & 4294967295L;
        long j6 = ((long) iArr2[3]) & 4294967295L;
        long j7 = ((long) iArr2[4]) & 4294967295L;
        long j8 = 0;
        while (i2 < 5) {
            long j9 = j2;
            long j10 = ((long) iArr[i2]) & j9;
            int i3 = i2;
            long j11 = (j10 * j3) + (((long) iArr3[i2]) & j9);
            iArr3[i3] = (int) j11;
            int i4 = i3 + 1;
            long j12 = (j10 * j4) + (((long) iArr3[i4]) & j9) + (j11 >>> 32);
            iArr3[i4] = (int) j12;
            int i5 = i3 + 2;
            long j13 = (j10 * j5) + (((long) iArr3[i5]) & j9) + (j12 >>> 32);
            iArr3[i5] = (int) j13;
            int i6 = i3 + 3;
            long j14 = (j10 * j6) + (((long) iArr3[i6]) & j9) + (j13 >>> 32);
            iArr3[i6] = (int) j14;
            int i7 = i3 + 4;
            long j15 = (j10 * j7) + (((long) iArr3[i7]) & j9) + (j14 >>> 32);
            iArr3[i7] = (int) j15;
            int i8 = i3 + 5;
            long j16 = (j15 >>> 32) + (((long) iArr3[i8]) & j9) + j8;
            iArr3[i8] = (int) j16;
            j8 = j16 >>> 32;
            j2 = j9;
            j3 = j3;
            i2 = i4;
        }
        return (int) j8;
    }

    public static void Z(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[8];
        G(jArr, jArr2, jArr4);
        l0(jArr4, jArr3);
    }

    public static int a(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (j2 >>> 32);
        iArr3[1] = (int) j3;
        long j4 = (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (j3 >>> 32);
        iArr3[2] = (int) j4;
        long j5 = (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (j4 >>> 32);
        iArr3[3] = (int) j5;
        long j6 = (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L) + (j5 >>> 32);
        iArr3[4] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int a0(int[] iArr, int i2) {
        int i3 = i2 - 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i4 - iArr[i5];
            iArr[i5] = 1073741823 & i6;
            i4 = i6 >> 30;
        }
        int i7 = i4 - iArr[i3];
        iArr[i3] = i7;
        return i7 >> 30;
    }

    public static int b(int i2, int[] iArr, int[] iArr2) {
        int i3 = i2 - 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = iArr[i5] + iArr2[i5] + i4;
            iArr[i5] = 1073741823 & i6;
            i4 = i6 >> 30;
        }
        int i7 = iArr[i3] + iArr2[i3] + i4;
        iArr[i3] = i7;
        return i7 >> 30;
    }

    public static int c(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + (((long) iArr3[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (((long) iArr3[1]) & 4294967295L) + (j2 >>> 32);
        iArr3[1] = (int) j3;
        long j4 = (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (((long) iArr3[2]) & 4294967295L) + (j3 >>> 32);
        iArr3[2] = (int) j4;
        long j5 = (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (((long) iArr3[3]) & 4294967295L) + (j4 >>> 32);
        iArr3[3] = (int) j5;
        long j6 = (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L) + (((long) iArr3[4]) & 4294967295L) + (j5 >>> 32);
        iArr3[4] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static void d(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr2[3] ^ jArr[3];
    }

    public static final void e(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append("?");
            if (i3 < i2 - 1) {
                sb.append(",");
            }
        }
    }

    public static void f(Class cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: ".concat(cls.getName()));
        }
        if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: ".concat(cls.getName()));
        }
    }

    public static int g(int i2, byte[] bArr) {
        return (bArr[i2 + 3] & 255) | (bArr[i2] << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }

    public static void h(iy0 iy0Var, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        int length = objArr.length;
        int i2 = 0;
        while (i2 < length) {
            Object obj = objArr[i2];
            i2++;
            if (obj == null) {
                iy0Var.h(i2);
            } else if (obj instanceof byte[]) {
                iy0Var.s(i2, (byte[]) obj);
            } else if (obj instanceof Float) {
                iy0Var.i(i2, ((Number) obj).floatValue());
            } else if (obj instanceof Double) {
                iy0Var.i(i2, ((Number) obj).doubleValue());
            } else if (obj instanceof Long) {
                iy0Var.n(i2, ((Number) obj).longValue());
            } else if (obj instanceof Integer) {
                iy0Var.n(i2, ((Number) obj).intValue());
            } else if (obj instanceof Short) {
                iy0Var.n(i2, ((Number) obj).shortValue());
            } else if (obj instanceof Byte) {
                iy0Var.n(i2, ((Number) obj).byteValue());
            } else if (obj instanceof String) {
                iy0Var.e(i2, (String) obj);
            } else {
                if (!(obj instanceof Boolean)) {
                    throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i2 + " Supported types: Null, ByteArray, Float, Double, Long, Int, Short, Byte, String");
                }
                iy0Var.n(i2, ((Boolean) obj).booleanValue() ? 1L : 0L);
            }
        }
    }

    public static void i(BigInteger[] bigIntegerArr, String str) {
        if (bigIntegerArr.length != 2 || bigIntegerArr[0] == null || bigIntegerArr[1] == null) {
            throw new IllegalArgumentException(zn.n("'", str, "' must consist of exactly 2 (non-null) values"));
        }
    }

    public static e0 i0(tk tkVar) {
        g0 g0VarD;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(tkVar.E);
        int i2 = byteArrayInputStream.read();
        int i3 = byteArrayInputStream.read();
        int i4 = i3 & 127;
        if (i3 != i4) {
            i3 = 0;
            while (true) {
                int i5 = i4 - 1;
                if (i4 == 0) {
                    break;
                }
                i3 = (i3 << 8) + byteArrayInputStream.read();
                i4 = i5;
            }
        }
        byte[] bArr = tkVar.E;
        if (i2 == 4 && i3 == byteArrayInputStream.available()) {
            return e0.o(bArr);
        }
        o oVar = e0.F;
        if (i2 == 128 && i3 == byteArrayInputStream.available()) {
            g0VarD = oVar.d(j9.p(bArr), false);
        } else {
            if (i2 != 160 || i3 != byteArrayInputStream.available()) {
                return tkVar;
            }
            g0VarD = oVar.d(j9.p(bArr), true);
        }
        return (e0) g0VarD;
    }

    public static void j(int[] iArr, int[] iArr2, int[] iArr3) {
        if (T(iArr, iArr2, iArr3) == 0) {
            throw new ArithmeticException("Inverse does not exist.");
        }
    }

    public static final List j0(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex(ApiConstants.ID);
        int columnIndex2 = cursor.getColumnIndex(ApiConstants.SEQ);
        int columnIndex3 = cursor.getColumnIndex("from");
        int columnIndex4 = cursor.getColumnIndex("to");
        ListBuilder listBuilder = new ListBuilder(0, 1, null);
        while (cursor.moveToNext()) {
            int i2 = cursor.getInt(columnIndex);
            int i3 = cursor.getInt(columnIndex2);
            String string = cursor.getString(columnIndex3);
            t40.m(string, "cursor.getString(fromColumnIndex)");
            String string2 = cursor.getString(columnIndex4);
            t40.m(string2, "cursor.getString(toColumnIndex)");
            listBuilder.add(new hz0(string, i2, i3, string2));
        }
        List listBuild = listBuilder.build();
        t40.n(listBuild, "<this>");
        if (listBuild.size() <= 1) {
            return vf.m1(listBuild);
        }
        Object[] array = listBuild.toArray(new Comparable[0]);
        Comparable[] comparableArr = (Comparable[]) array;
        t40.n(comparableArr, "<this>");
        if (comparableArr.length > 1) {
            Arrays.sort(comparableArr);
        }
        t40.n(array, "<this>");
        List listAsList = Arrays.asList(array);
        t40.m(listAsList, "asList(...)");
        return listAsList;
    }

    public static final iz0 k0(ey0 ey0Var, String str, boolean z) throws IOException {
        Cursor cursorV = ey0Var.v("PRAGMA index_xinfo(`" + str + "`)");
        try {
            int columnIndex = cursorV.getColumnIndex("seqno");
            int columnIndex2 = cursorV.getColumnIndex("cid");
            int columnIndex3 = cursorV.getColumnIndex(ApiConstants.NAME);
            int columnIndex4 = cursorV.getColumnIndex("desc");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1 && columnIndex4 != -1) {
                TreeMap treeMap = new TreeMap();
                TreeMap treeMap2 = new TreeMap();
                while (cursorV.moveToNext()) {
                    if (cursorV.getInt(columnIndex2) >= 0) {
                        int i2 = cursorV.getInt(columnIndex);
                        String string = cursorV.getString(columnIndex3);
                        String str2 = cursorV.getInt(columnIndex4) > 0 ? "DESC" : "ASC";
                        Integer numValueOf = Integer.valueOf(i2);
                        t40.m(string, "columnName");
                        treeMap.put(numValueOf, string);
                        treeMap2.put(Integer.valueOf(i2), str2);
                    }
                }
                Collection collectionValues = treeMap.values();
                t40.m(collectionValues, "columnsMap.values");
                List listM1 = vf.m1(collectionValues);
                Collection collectionValues2 = treeMap2.values();
                t40.m(collectionValues2, "ordersMap.values");
                iz0 iz0Var = new iz0(str, z, listM1, vf.m1(collectionValues2));
                cursorV.close();
                return iz0Var;
            }
            cursorV.close();
            return null;
        } finally {
        }
    }

    public static void l0(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = j4 ^ ((j5 >>> 40) ^ (j5 >>> 49));
        long j7 = j2 ^ ((j6 << 15) ^ (j6 << 24));
        long j8 = (j3 ^ ((j5 << 15) ^ (j5 << 24))) ^ ((j6 >>> 40) ^ (j6 >>> 49));
        long j9 = j8 >>> 49;
        jArr2[0] = (j7 ^ j9) ^ (j9 << 9);
        jArr2[1] = 562949953421311L & j8;
    }

    public static void m(int i2, int[] iArr, int[] iArr2) {
        iArr2[i2] = iArr[0];
        iArr2[i2 + 1] = iArr[1];
        iArr2[i2 + 2] = iArr[2];
        iArr2[i2 + 3] = iArr[3];
        iArr2[i2 + 4] = iArr[4];
    }

    public static void m0(Context context) {
        ((ConnectivityManager) context.getSystemService("connectivity")).registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), new rh0(1, context));
    }

    public static z91 n(int i2) {
        return i2 != 0 ? i2 != 1 ? new up0() : new hk() : new up0();
    }

    public static void n0(ViewGroup viewGroup, float f2) {
        Drawable background = viewGroup.getBackground();
        if (background instanceof ld0) {
            ((ld0) background).l(f2);
        }
    }

    public static void o0(View view, ld0 ld0Var) {
        ts tsVar = ld0Var.E.b;
        if (tsVar == null || !tsVar.a) {
            return;
        }
        float fI = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            WeakHashMap weakHashMap = f51.a;
            fI += t41.i((View) parent);
        }
        kd0 kd0Var = ld0Var.E;
        if (kd0Var.l != fI) {
            kd0Var.l = fI;
            ld0Var.v();
        }
    }

    public static void p0(ViewGroup viewGroup) {
        Drawable background = viewGroup.getBackground();
        if (background instanceof ld0) {
            o0(viewGroup, (ld0) background);
        }
    }

    public static void q0(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[0]) & 4294967295L;
        int i2 = 10;
        int i3 = 0;
        int i4 = 4;
        while (true) {
            int i5 = i4 - 1;
            long j3 = ((long) iArr[i4]) & 4294967295L;
            long j4 = j3 * j3;
            iArr2[i2 - 1] = (i3 << 31) | ((int) (j4 >>> 33));
            i2 -= 2;
            iArr2[i2] = (int) (j4 >>> 1);
            i3 = (int) j4;
            if (i5 <= 0) {
                long j5 = j2 * j2;
                long j6 = (j5 >>> 33) | (((long) (i3 << 31)) & 4294967295L);
                iArr2[0] = (int) j5;
                int i6 = ((int) (j5 >>> 32)) & 1;
                long j7 = ((long) iArr[1]) & 4294967295L;
                long j8 = ((long) iArr2[2]) & 4294967295L;
                long j9 = (j7 * j2) + j6;
                int i7 = (int) j9;
                iArr2[1] = i6 | (i7 << 1);
                long j10 = ((long) iArr[2]) & 4294967295L;
                long j11 = ((long) iArr2[3]) & 4294967295L;
                long j12 = ((long) iArr2[4]) & 4294967295L;
                long j13 = (j10 * j2) + j8 + (j9 >>> 32);
                int i8 = (int) j13;
                iArr2[2] = (i8 << 1) | (i7 >>> 31);
                long jE = zn.e(j10, j7, j13 >>> 32, j11);
                long j14 = j12 + (jE >>> 32);
                long j15 = ((long) iArr[3]) & 4294967295L;
                long j16 = (((long) iArr2[5]) & 4294967295L) + (j14 >>> 32);
                long j17 = j14 & 4294967295L;
                long j18 = (((long) iArr2[6]) & 4294967295L) + (j16 >>> 32);
                long j19 = j16 & 4294967295L;
                long j20 = (j15 * j2) + (jE & 4294967295L);
                int i9 = (int) j20;
                iArr2[3] = (i8 >>> 31) | (i9 << 1);
                int i10 = i9 >>> 31;
                long jE2 = zn.e(j15, j7, j20 >>> 32, j17);
                long jE3 = zn.e(j15, j10, jE2 >>> 32, j19);
                long j21 = j18 + (jE3 >>> 32);
                long j22 = ((long) iArr[4]) & 4294967295L;
                long j23 = (((long) iArr2[7]) & 4294967295L) + (j21 >>> 32);
                long j24 = j21 & 4294967295L;
                long j25 = (((long) iArr2[8]) & 4294967295L) + (j23 >>> 32);
                long j26 = (j22 * j2) + (jE2 & 4294967295L);
                int i11 = (int) j26;
                iArr2[4] = i10 | (i11 << 1);
                int i12 = i11 >>> 31;
                long jE4 = zn.e(j22, j7, j26 >>> 32, jE3 & 4294967295L);
                long jE5 = zn.e(j22, j10, jE4 >>> 32, j24);
                long jE6 = zn.e(j22, j15, jE5 >>> 32, j23 & 4294967295L);
                long j27 = j25 + (jE6 >>> 32);
                int i13 = (int) jE4;
                iArr2[5] = (i13 << 1) | i12;
                int i14 = (int) jE5;
                iArr2[6] = (i13 >>> 31) | (i14 << 1);
                int i15 = i14 >>> 31;
                int i16 = (int) jE6;
                iArr2[7] = i15 | (i16 << 1);
                int i17 = i16 >>> 31;
                int i18 = (int) j27;
                iArr2[8] = i17 | (i18 << 1);
                iArr2[9] = ((iArr2[9] + ((int) (j27 >>> 32))) << 1) | (i18 >>> 31);
                return;
            }
            i4 = i5;
        }
    }

    public static void r0(int[] iArr, int[] iArr2) {
        o80.b0(iArr, iArr2);
        long j2 = 4294967295L;
        long j3 = ((long) iArr[6]) & 4294967295L;
        int i2 = 5;
        int i3 = 12;
        int i4 = 0;
        while (true) {
            int i5 = i2 - 1;
            long j4 = ((long) iArr[i2 + 6]) & j2;
            long j5 = j4 * j4;
            long j6 = j2;
            iArr2[i3 + 11] = ((int) (j5 >>> 33)) | (i4 << 31);
            int i6 = i3 - 2;
            iArr2[i3 + 10] = (int) (j5 >>> 1);
            i4 = (int) j5;
            if (i5 <= 0) {
                long j7 = j3 * j3;
                iArr2[12] = (int) j7;
                long j8 = ((long) iArr[7]) & j6;
                long j9 = ((long) iArr2[14]) & j6;
                long j10 = (j8 * j3) + ((((long) (i4 << 31)) & j6) | (j7 >>> 33));
                int i7 = (int) j10;
                iArr2[13] = (i7 << 1) | (((int) (j7 >>> 32)) & 1);
                long j11 = ((long) iArr[8]) & j6;
                long j12 = ((long) iArr2[15]) & j6;
                long j13 = ((long) iArr2[16]) & j6;
                long j14 = (j11 * j3) + j9 + (j10 >>> 32);
                int i8 = (int) j14;
                iArr2[14] = (i8 << 1) | (i7 >>> 31);
                int i9 = i8 >>> 31;
                long jE = zn.e(j11, j8, j14 >>> 32, j12);
                long j15 = j13 + (jE >>> 32);
                long j16 = ((long) iArr[9]) & j6;
                long j17 = (((long) iArr2[17]) & j6) + (j15 >>> 32);
                long j18 = (((long) iArr2[18]) & j6) + (j17 >>> 32);
                long j19 = j17 & j6;
                long j20 = (j16 * j3) + (jE & j6);
                int i10 = (int) j20;
                iArr2[15] = (i10 << 1) | i9;
                long jE2 = zn.e(j16, j8, j20 >>> 32, j15 & j6);
                long jE3 = zn.e(j16, j11, jE2 >>> 32, j19);
                long j21 = j18 + (jE3 >>> 32);
                long j22 = jE3 & j6;
                long j23 = ((long) iArr[10]) & j6;
                long j24 = (((long) iArr2[19]) & j6) + (j21 >>> 32);
                long j25 = j21 & j6;
                long j26 = (((long) iArr2[20]) & j6) + (j24 >>> 32);
                long j27 = j24 & j6;
                long j28 = (j23 * j3) + (jE2 & j6);
                int i11 = (int) j28;
                iArr2[16] = (i10 >>> 31) | (i11 << 1);
                int i12 = i11 >>> 31;
                long jE4 = zn.e(j23, j8, j28 >>> 32, j22);
                long jE5 = zn.e(j23, j11, jE4 >>> 32, j25);
                long jE6 = zn.e(j23, j16, jE5 >>> 32, j27);
                long j29 = jE5 & j6;
                long j30 = j26 + (jE6 >>> 32);
                long j31 = ((long) iArr[11]) & j6;
                long j32 = (((long) iArr2[21]) & j6) + (j30 >>> 32);
                long j33 = (((long) iArr2[22]) & j6) + (j32 >>> 32);
                long j34 = j32 & j6;
                long j35 = (j3 * j31) + (jE4 & j6);
                int i13 = (int) j35;
                iArr2[17] = i12 | (i13 << 1);
                int i14 = i13 >>> 31;
                long jE7 = zn.e(j31, j8, j35 >>> 32, j29);
                long jE8 = zn.e(j31, j11, jE7 >>> 32, jE6 & j6);
                long jE9 = zn.e(j31, j16, jE8 >>> 32, j30 & j6);
                long jE10 = zn.e(j31, j23, jE9 >>> 32, j34);
                long j36 = j33 + (jE10 >>> 32);
                int i15 = (int) jE7;
                iArr2[18] = (i15 << 1) | i14;
                int i16 = (int) jE8;
                iArr2[19] = (i15 >>> 31) | (i16 << 1);
                int i17 = i16 >>> 31;
                int i18 = (int) jE9;
                iArr2[20] = i17 | (i18 << 1);
                int i19 = i18 >>> 31;
                int i20 = (int) jE10;
                iArr2[21] = i19 | (i20 << 1);
                int i21 = i20 >>> 31;
                int i22 = (int) j36;
                iArr2[22] = i21 | (i22 << 1);
                iArr2[23] = (i22 >>> 31) | ((iArr2[23] + ((int) (j36 >>> 32))) << 1);
                int iG = o80.g(iArr2, iArr2);
                int iF = iG + o80.f(18, 12, o80.f(0, 6, 0, iArr2, iArr2) + iG, iArr2, iArr2);
                int[] iArr3 = new int[6];
                o80.u(iArr, iArr, iArr3);
                int[] iArr4 = new int[12];
                o80.b0(iArr3, iArr4);
                fa0.k(24, fa0.F0(12, 6, iArr4, iArr2) + iF, 18, iArr2);
                return;
            }
            i3 = i6;
            i2 = i5;
            j2 = j6;
        }
    }

    public static s8 s(ol0 ol0Var) throws IOException {
        if (ol0Var == null) {
            throw new IllegalArgumentException("keyInfo array null");
        }
        d0 d0Var = ol0Var.F.E;
        boolean zK = d0Var.k(vj0.g);
        p pVar = ol0Var.I;
        if (!zK) {
            HashMap map = l31.f;
            if (map.containsKey(d0Var)) {
                return new kq0((jq0) map.get(d0Var), i0(ol0Var.h()).E);
            }
            if (d0Var.k(yf0.R) || d0Var.k(yf0.S) || d0Var.k(yf0.T)) {
                return new nb0((mb0) l31.b.get(d0Var), i0(ol0Var.h()).E);
            }
            HashMap map2 = l31.d;
            if (!map2.containsKey(d0Var)) {
                throw new RuntimeException("algorithm identifier in private key not recognised");
            }
            e0 e0VarI0 = i0(ol0Var.h());
            hb0 hb0Var = (hb0) map2.get(d0Var);
            if (!(e0VarI0 instanceof tk)) {
                throw new IOException("not supported");
            }
            byte[] bArr = e0.o(e0VarI0).E;
            return pVar != null ? new ib0(hb0Var, bArr, fm0.a(hb0Var, pVar)) : new ib0(hb0Var, bArr, null);
        }
        byte[] bArr2 = e0.o(ol0Var.i()).E;
        if (g(0, bArr2) != 1) {
            if (pVar == null) {
                return s20.a(z91.r(bArr2, 4, bArr2.length));
            }
            byte[] bArrR = pVar.r();
            s20 s20VarA = s20.a(z91.r(bArr2, 4, bArr2.length));
            t20.a(bArrR);
            s20VarA.getClass();
            return s20VarA;
        }
        if (pVar == null) {
            return c80.e(z91.r(bArr2, 4, bArr2.length));
        }
        byte[] bArrR2 = pVar.r();
        byte[] bArrR3 = z91.r(bArr2, 4, bArr2.length);
        byte[] bArrR4 = z91.r(bArrR2, 4, bArrR2.length);
        c80 c80VarE = c80.e(bArrR3);
        c80VarE.O = d80.a(bArrR4);
        return c80VarE;
    }

    public static void s0(int i2, long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[4];
        qm.o(2, jArr, jArr3);
        while (true) {
            l0(jArr3, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                qm.o(2, jArr2, jArr3);
            }
        }
    }

    public static void t(int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        long j2 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 > 0) {
            while (i3 < Math.min(32, i2)) {
                j2 |= ((long) iArr[i4]) << i3;
                i3 += 30;
                i4++;
            }
            iArr2[i5] = (int) j2;
            j2 >>>= 32;
            i3 -= 32;
            i2 -= 32;
            i5++;
        }
    }

    public static int t0(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) - (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = ((((long) iArr[1]) & 4294967295L) - (((long) iArr2[1]) & 4294967295L)) + (j2 >> 32);
        iArr3[1] = (int) j3;
        long j4 = ((((long) iArr[2]) & 4294967295L) - (((long) iArr2[2]) & 4294967295L)) + (j3 >> 32);
        iArr3[2] = (int) j4;
        long j5 = ((((long) iArr[3]) & 4294967295L) - (((long) iArr2[3]) & 4294967295L)) + (j4 >> 32);
        iArr3[3] = (int) j5;
        long j6 = ((((long) iArr[4]) & 4294967295L) - (((long) iArr2[4]) & 4294967295L)) + (j5 >> 32);
        iArr3[4] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static byte[] u(byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        int[] iArr = {53, 115, 71, 117, 84, 80, 91, 76, 119, 59, 49, 72, 66, 112, 53, 83};
        byte[] bArr2 = new byte[16];
        for (int i2 = 0; i2 < 16; i2++) {
            bArr2[i2] = (byte) (iArr[i2] ^ 3);
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
        int[] iArr2 = {67, 71, 81, 45, 71, 65, 64, 45, 82, 73, 65, 81, 55, 82, 99, 102, 102, 107, 108, 101};
        byte[] bArr3 = new byte[20];
        for (int i3 = 0; i3 < 20; i3++) {
            bArr3[i3] = (byte) (2 ^ iArr2[i3]);
        }
        Cipher cipher = Cipher.getInstance(new String(bArr3));
        cipher.init(2, secretKeySpec);
        return cipher.doFinal(bArr);
    }

    public static void u0(int[] iArr, int[] iArr2) {
        long j2 = (((long) iArr2[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L);
        iArr2[0] = (int) j2;
        long j3 = ((((long) iArr2[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L)) + (j2 >> 32);
        iArr2[1] = (int) j3;
        long j4 = ((((long) iArr2[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L)) + (j3 >> 32);
        iArr2[2] = (int) j4;
        long j5 = ((((long) iArr2[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L)) + (j4 >> 32);
        iArr2[3] = (int) j5;
        iArr2[4] = (int) (((((long) iArr2[4]) & 4294967295L) - (4294967295L & ((long) iArr[4]))) + (j5 >> 32));
    }

    public static int v(int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6 = 30;
        int i7 = 1;
        int i8 = 1;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(((-1) << i6) | i4);
            int i11 = i4 >> iNumberOfTrailingZeros;
            i7 <<= iNumberOfTrailingZeros;
            i9 <<= iNumberOfTrailingZeros;
            i2 -= iNumberOfTrailingZeros;
            i6 -= iNumberOfTrailingZeros;
            if (i6 <= 0) {
                iArr[0] = i7;
                iArr[1] = i9;
                iArr[2] = i10;
                iArr[3] = i8;
                return i2;
            }
            if (i2 <= 0) {
                i2 = 2 - i2;
                int i12 = -i3;
                int i13 = -i7;
                int i14 = -i9;
                i5 = ((-1) >>> (32 - (i2 > i6 ? i6 : i2))) & 63 & (((i11 * i11) - 2) * i11 * i12);
                i11 = i12;
                i3 = i11;
                int i15 = i10;
                i10 = i13;
                i7 = i15;
                int i16 = i8;
                i8 = i14;
                i9 = i16;
            } else {
                i5 = ((-1) >>> (32 - (i2 > i6 ? i6 : i2))) & 15 & (((((i3 + 1) & 4) << 1) + i3) * (-i11));
            }
            i4 = i11 + (i3 * i5);
            i10 += i7 * i5;
            i8 += i5 * i9;
        }
    }

    public static BigInteger v0(int[] iArr) {
        byte[] bArr = new byte[20];
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                K(bArr, i3, (4 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void w(int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        long j2 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 > 0) {
            if (i3 < Math.min(30, i2)) {
                j2 |= (((long) iArr[i4]) & 4294967295L) << i3;
                i3 += 32;
                i4++;
            }
            iArr2[i5] = ((int) j2) & 1073741823;
            j2 >>>= 30;
            i3 -= 30;
            i2 -= 30;
            i5++;
        }
    }

    public static int w0(int i2, int[] iArr, int[] iArr2) {
        int i3 = i2 - 1;
        int i4 = iArr[i3];
        int i5 = iArr2[i3];
        int i6 = i2 - 2;
        if (((i6 >> 31) | ((i4 >> 31) ^ i4) | ((i5 >> 31) ^ i5)) != 0) {
            return i2;
        }
        iArr[i6] = (i4 << 30) | iArr[i6];
        iArr2[i6] = iArr2[i6] | (i5 << 30);
        return i2 - 1;
    }

    public static boolean x(int[] iArr, int[] iArr2) {
        for (int i2 = 4; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int y(int i2, int i3, int[] iArr) {
        int i4 = i3 ^ iArr[0];
        for (int i5 = 1; i5 < i2; i5++) {
            i4 |= iArr[i5];
        }
        return (((i4 >>> 1) | (i4 & 1)) - 1) >> 31;
    }

    public static void y0(int i2, int[] iArr, int[] iArr2, int[] iArr3, int i3, int[] iArr4) {
        int i4 = i2;
        int i5 = iArr3[0];
        int i6 = iArr3[1];
        int i7 = iArr3[2];
        int i8 = iArr3[3];
        int i9 = i4 - 1;
        int i10 = iArr[i9] >> 31;
        int i11 = iArr2[i9] >> 31;
        int i12 = (i5 & i10) + (i6 & i11);
        int i13 = (i10 & i7) + (i11 & i8);
        int i14 = iArr4[0];
        long j2 = i5;
        long j3 = iArr[0];
        long j4 = i6;
        long j5 = iArr2[0];
        long j6 = (j4 * j5) + (j2 * j3);
        long j7 = i7;
        long j8 = i8;
        long j9 = (j8 * j5) + (j3 * j7);
        int i15 = i12 - (((((int) j6) * i3) + i12) & 1073741823);
        long j10 = i14;
        long j11 = i15;
        long j12 = (j10 * j11) + j6;
        long j13 = i13 - (((((int) j9) * i3) + i13) & 1073741823);
        char c2 = 30;
        long j14 = j12 >> 30;
        long j15 = ((j10 * j13) + j9) >> 30;
        int i16 = 1;
        while (i16 < i4) {
            int i17 = iArr4[i16];
            char c3 = c2;
            long j16 = j13;
            long j17 = iArr[i16];
            long j18 = j2 * j17;
            long j19 = iArr2[i16];
            long j20 = i17;
            long j21 = j11;
            long jE = zn.e(j20, j21, (j4 * j19) + j18, j14);
            long jE2 = zn.e(j20, j16, (j19 * j8) + (j7 * j17), j15);
            int i18 = i16 - 1;
            iArr[i18] = ((int) jE) & 1073741823;
            j14 = jE >> c3;
            iArr2[i18] = ((int) jE2) & 1073741823;
            j15 = jE2 >> c3;
            i16++;
            i4 = i2;
            c2 = c3;
            j11 = j21;
            j13 = j16;
        }
        iArr[i9] = (int) j14;
        iArr2[i9] = (int) j15;
    }

    public static boolean z(int i2, int i3, int[] iArr) {
        int i4 = i3 ^ iArr[0];
        if (i4 == 0) {
            for (int i5 = 1; i5 < i2; i5++) {
                i4 |= iArr[i5];
            }
            if (i4 == 0) {
                return true;
            }
        }
        return false;
    }

    public static void z0(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        int i3 = iArr3[0];
        int i4 = 1;
        int i5 = iArr3[1];
        int i6 = iArr3[2];
        int i7 = iArr3[3];
        long j2 = i3;
        long j3 = iArr[0];
        long j4 = i5;
        long j5 = iArr2[0];
        long j6 = (j4 * j5) + (j2 * j3);
        long j7 = i6;
        long j8 = i7;
        long j9 = ((j5 * j8) + (j3 * j7)) >> 30;
        int i8 = 1;
        long j10 = j6 >> 30;
        while (i8 < i2) {
            long j11 = iArr[i8];
            int i9 = i4;
            long j12 = j7;
            long j13 = iArr2[i8];
            long jE = zn.e(j4, j13, j2 * j11, j10);
            long j14 = j8;
            long jE2 = zn.e(j14, j13, j12 * j11, j9);
            int i10 = i8 - 1;
            iArr[i10] = ((int) jE) & 1073741823;
            j10 = jE >> 30;
            iArr2[i10] = ((int) jE2) & 1073741823;
            j9 = jE2 >> 30;
            i8++;
            i4 = i9;
            j8 = j14;
            j7 = j12;
        }
        int i11 = i2 - i4;
        iArr[i11] = (int) j10;
        iArr2[i11] = (int) j9;
    }

    public oz A(int i2, oz[] ozVarArr) {
        int i3 = (i2 & 1) == 0 ? Status.BAD_REQUEST_CODE : 700;
        boolean z = (i2 & 2) != 0;
        oz ozVar = null;
        int i4 = Integer.MAX_VALUE;
        for (oz ozVar2 : ozVarArr) {
            int iAbs = (Math.abs(ozVar2.c - i3) * 2) + (ozVar2.d == z ? 0 : 1);
            if (ozVar == null || i4 > iAbs) {
                ozVar = ozVar2;
                i4 = iAbs;
            }
        }
        return ozVar;
    }

    public int D(View view) {
        return 0;
    }

    public int E() {
        return 0;
    }

    public abstract Object b0(Class cls);

    public abstract void f0(int i2);

    public abstract void g0(View view, int i2, int i3);

    public abstract void h0(View view, float f2, float f3);

    public abstract int k(View view, int i2);

    public abstract int l(View view, int i2);

    public abstract Typeface o(Context context, lz lzVar, Resources resources, int i2);

    public abstract Typeface p(Context context, oz[] ozVarArr, int i2);

    public Typeface q(Context context, InputStream inputStream) {
        File fileC = o80.C(context);
        if (fileC == null) {
            return null;
        }
        try {
            if (o80.p(fileC, inputStream)) {
                return Typeface.createFromFile(fileC.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileC.delete();
        }
    }

    public Typeface r(Context context, Resources resources, int i2, String str, int i3) {
        File fileC = o80.C(context);
        if (fileC == null) {
            return null;
        }
        try {
            if (o80.o(fileC, resources, i2)) {
                return Typeface.createFromFile(fileC.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileC.delete();
        }
    }

    public abstract boolean x0(View view, int i2);

    public void d0() {
    }

    public void c0(int i2, int i3) {
    }

    public void e0(View view, int i2) {
    }
}
