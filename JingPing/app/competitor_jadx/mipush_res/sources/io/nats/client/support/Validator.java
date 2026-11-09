package io.nats.client.support;

import defpackage.o31;
import defpackage.zn;
import io.nats.client.support.Validator;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class Validator {
    static final char[] WILD_GT = {'*', '>'};
    static final char[] WILD_GT_DOT = {'*', '>', '.'};
    static final char[] WILD_GT_DOT_SLASHES = {'*', '>', '.', '\\', '/'};
    static final Pattern SEMVER_PATTERN = Pattern.compile("^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$");

    private Validator() {
    }

    public static String _validate(String str, boolean z, String str2, Supplier<String> supplier) {
        if (emptyAsNull(str) != null) {
            return supplier.get();
        }
        if (z) {
            throw new IllegalArgumentException(zn.m(str2, " cannot be null or empty."));
        }
        return null;
    }

    public static String emptyAsNull(String str) {
        if (nullOrEmpty(str)) {
            return null;
        }
        return str;
    }

    public static String emptyOrNullAs(String str, String str2) {
        return nullOrEmpty(str) ? str2 : str;
    }

    public static Duration ensureDurationNotLessThanMin(long j, Duration duration, Duration duration2) {
        return ensureNotNullAndNotLessThanMin(Duration.ofMillis(j), duration, duration2);
    }

    public static String ensureEndsWithDot(String str) {
        return (str == null || str.endsWith(NatsConstants.DOT)) ? str : str.concat(NatsConstants.DOT);
    }

    public static Duration ensureNotNullAndNotLessThanMin(Duration duration, Duration duration2, Duration duration3) {
        return (duration == null || duration.toNanos() < duration2.toNanos()) ? duration3 : duration;
    }

    public static boolean isGtEqZero(long j) {
        return j >= 0;
    }

    public static boolean isSemVer(String str) {
        return SEMVER_PATTERN.matcher(str).find();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$validateIsRestrictedTerm$6(String str, String str2) {
        if (!notRestrictedTerm(str)) {
            return str;
        }
        throw new IllegalArgumentException(str2 + " must only contain A-Z, a-z, 0-9, '-' or '_' [" + str + "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$validateMaxLength$1(String str, int i, String str2) {
        int length = str.getBytes(StandardCharsets.UTF_8).length;
        if (length <= i) {
            return str;
        }
        throw new IllegalArgumentException(str2 + " cannot be longer than " + i + " bytes but was " + length + " bytes");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$validateNonWildcardKvKey$8(String str, String str2) {
        if (!notNonWildcardKvKey(str)) {
            return str;
        }
        throw new IllegalArgumentException(str2 + " must only contain A-Z, a-z, 0-9, '-', '_', '/', '=' or '.' and cannot start with '.' [" + str + "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$validatePrefixOrDomain$0(String str, String str2) {
        if (str.startsWith(NatsConstants.DOT)) {
            throw new IllegalArgumentException(str2 + " cannot start with '.' [" + str + "]");
        }
        if (!notPrintableOrHasWildGt(str)) {
            return str;
        }
        throw new IllegalArgumentException(str2 + " must be in the printable ASCII range and cannot include '*', '>' [" + str + "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$validatePrintable$2(String str, String str2) {
        if (!notPrintable(str)) {
            return str;
        }
        throw new IllegalArgumentException(str2 + " must be in the printable ASCII range [" + str + "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$validatePrintableExceptWildDotGt$3(String str, String str2) {
        if (!notPrintableOrHasWildGtDot(str)) {
            return str;
        }
        throw new IllegalArgumentException(str2 + " must be in the printable ASCII range and cannot include '*', '.' or '>' [" + str + "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$validatePrintableExceptWildDotGtSlashes$4(String str, String str2) {
        if (!notPrintableOrHasWildGtDotSlashes(str)) {
            return str;
        }
        throw new IllegalArgumentException(str2 + " must be in the printable ASCII range and cannot include '*', '.', '>', '\\' or  '/' [" + str + "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$validatePrintableExceptWildGt$5(String str, String str2) {
        if (!notPrintableOrHasWildGt(str)) {
            return str;
        }
        throw new IllegalArgumentException(str2 + " must be in the printable ASCII range and cannot include '*' or '>' [" + str + "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$validateSemVer$9(String str, String str2) {
        if (isSemVer(str)) {
            return str;
        }
        throw new IllegalArgumentException(zn.m(str2, " must be a valid SemVer"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$validateWildcardKvKey$7(String str, String str2) {
        if (!notWildcardKvKey(str)) {
            return str;
        }
        throw new IllegalArgumentException(str2 + " must only contain A-Z, a-z, 0-9, '*', '-', '_', '/', '=', '>' or '.' and cannot start with '.' [" + str + "]");
    }

    public static <T> boolean listsAreEquivalent(List<T> list, List<T> list2) {
        if (list == null || list.isEmpty()) {
            return list2 == null || list2.isEmpty();
        }
        if (list2 == null || list.size() != list2.size()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!list2.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean mapsAreEquivalent(Map<String, String> map, Map<String, String> map2) {
        int size = map == null ? 0 : map.size();
        if (size != (map2 == null ? 0 : map2.size())) {
            return false;
        }
        if (size <= 0) {
            return true;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!entry.getValue().equals(map2.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static boolean notNonWildcardKvKey(String str) {
        if (str.charAt(0) == '.') {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0') {
                if (cCharAt != '-' && cCharAt != '.' && cCharAt != '/') {
                    return true;
                }
            } else if (cCharAt < ':') {
                continue;
            } else if (cCharAt < 'A') {
                if (cCharAt != '=') {
                    return true;
                }
            } else if (cCharAt < '[') {
                continue;
            } else if (cCharAt < 'a') {
                if (cCharAt != '_') {
                    return true;
                }
            } else if (cCharAt > 'z') {
                return true;
            }
        }
        return false;
    }

    public static boolean notPrintable(String str) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '!' || cCharAt > '~') {
                return true;
            }
        }
        return false;
    }

    public static boolean notPrintableOrHasChars(String str, char[] cArr) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '!' || cCharAt > '~') {
                return true;
            }
            for (char c : cArr) {
                if (cCharAt == c) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean notPrintableOrHasWildGt(String str) {
        return notPrintableOrHasChars(str, WILD_GT);
    }

    private static boolean notPrintableOrHasWildGtDot(String str) {
        return notPrintableOrHasChars(str, WILD_GT_DOT);
    }

    private static boolean notPrintableOrHasWildGtDotSlashes(String str) {
        return notPrintableOrHasChars(str, WILD_GT_DOT_SLASHES);
    }

    public static boolean notRestrictedTerm(String str) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0') {
                if (cCharAt != '-') {
                    return true;
                }
            } else if (cCharAt < ':') {
                continue;
            } else {
                if (cCharAt < 'A') {
                    return true;
                }
                if (cCharAt < '[') {
                    continue;
                } else if (cCharAt < 'a') {
                    if (cCharAt != '_') {
                        return true;
                    }
                } else if (cCharAt > 'z') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean notWildcardKvKey(String str) {
        if (str.charAt(0) == '.') {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0') {
                if (cCharAt != '*' && cCharAt != '-' && cCharAt != '.' && cCharAt != '/') {
                    return true;
                }
            } else if (cCharAt < ':') {
                continue;
            } else if (cCharAt < 'A') {
                if (cCharAt != '=' && cCharAt != '>') {
                    return true;
                }
            } else if (cCharAt < '[') {
                continue;
            } else if (cCharAt < 'a') {
                if (cCharAt != '_') {
                    return true;
                }
            } else if (cCharAt > 'z') {
                return true;
            }
        }
        return false;
    }

    public static boolean nullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String required(String str, String str2) {
        if (emptyAsNull(str) != null) {
            return str;
        }
        throw new IllegalArgumentException(zn.m(str2, " cannot be null or empty."));
    }

    public static String validateBucketName(String str, boolean z) {
        return validateIsRestrictedTerm(str, "Bucket Name", z);
    }

    public static String validateConsumerName(String str, boolean z) {
        return validatePrintableExceptWildDotGtSlashes(str, "Name", z);
    }

    public static String validateDurable(String str, boolean z) {
        return validatePrintableExceptWildDotGtSlashes(str, "Durable", z);
    }

    public static Duration validateDurationGtOrEqSeconds(long j, long j2, String str) {
        if (j2 >= 1000 * j) {
            return Duration.ofMillis(j2);
        }
        throw new IllegalArgumentException(str + " must be greater than or equal to " + j + " second(s).");
    }

    public static Duration validateDurationNotRequiredGtOrEqSeconds(long j, Duration duration, Duration duration2, String str) {
        return duration == null ? duration2 : validateDurationGtOrEqSeconds(j, duration.toMillis(), str);
    }

    public static Duration validateDurationNotRequiredGtOrEqZero(Duration duration, Duration duration2) {
        if (duration == null) {
            return duration2;
        }
        if (duration.isNegative()) {
            throw new IllegalArgumentException("Duration must be greater than or equal to 0.");
        }
        return duration;
    }

    public static Duration validateDurationRequired(Duration duration) {
        if (duration == null || duration.isZero() || duration.isNegative()) {
            throw new IllegalArgumentException("Duration required and must be greater than 0.");
        }
        return duration;
    }

    public static long validateGtEqMinus1(long j, String str) {
        if (j >= -1) {
            return j;
        }
        throw new IllegalArgumentException(zn.m(str, " must be greater than zero or -1 for unlimited"));
    }

    public static long validateGtEqZero(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(zn.m(str, " must be greater than or equal to zero"));
    }

    public static int validateGtZero(int i, String str) {
        if (i >= 1) {
            return i;
        }
        throw new IllegalArgumentException(zn.m(str, " must be greater than zero"));
    }

    public static long validateGtZeroOrMinus1(long j, String str) {
        if (zeroOrLtMinus1(j)) {
            throw new IllegalArgumentException(zn.m(str, " must be greater than zero or -1 for unlimited"));
        }
        return j;
    }

    public static String validateIsRestrictedTerm(String str, String str2, boolean z) {
        return _validate(str, z, str2, new o31(str, 7, str2));
    }

    public static String validateKvKeyWildcardAllowedRequired(String str) {
        return validateWildcardKvKey(str, "Key", true);
    }

    public static List<String> validateKvKeysWildcardAllowedRequired(List<String> list) {
        required((List<?>) list, "Key");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            validateWildcardKvKey(it.next(), "Key", true);
        }
        return list;
    }

    public static long validateMaxBucketBytes(long j) {
        return validateGtZeroOrMinus1(j, "Max Bucket Bytes");
    }

    public static long validateMaxBytes(long j) {
        return validateGtZeroOrMinus1(j, "Max Bytes");
    }

    public static long validateMaxConsumers(long j) {
        return validateGtZeroOrMinus1(j, "Max Consumers");
    }

    public static int validateMaxHistory(int i) {
        if (i < 1 || i > 64) {
            throw new IllegalArgumentException("Max History must be from 1 to 64 inclusive.");
        }
        return i;
    }

    public static String validateMaxLength(final String str, final int i, boolean z, final String str2) {
        return _validate(str, z, str2, new Supplier() { // from class: p31
            @Override // java.util.function.Supplier
            public final Object get() {
                return Validator.lambda$validateMaxLength$1(str, i, str2);
            }
        });
    }

    private static long validateMaxMessageSize(long j, String str) {
        long jValidateGtZeroOrMinus1 = validateGtZeroOrMinus1(j, str);
        if (jValidateGtZeroOrMinus1 <= 2147483647L) {
            return jValidateGtZeroOrMinus1;
        }
        throw new IllegalArgumentException(zn.m(str, " cannot be larger than 2147483647"));
    }

    public static long validateMaxMessages(long j) {
        return validateGtZeroOrMinus1(j, "Max Messages");
    }

    public static long validateMaxMessagesPerSubject(long j) {
        return validateGtZeroOrMinus1(j, "Max Messages Per Subject");
    }

    public static long validateMaxValueSize(long j) {
        return validateMaxMessageSize(j, "Max Value Size");
    }

    public static String validateMustMatchIfBothSupplied(String str, String str2, NatsJetStreamClientError natsJetStreamClientError) {
        String strEmptyAsNull = emptyAsNull(str);
        String strEmptyAsNull2 = emptyAsNull(str2);
        if (strEmptyAsNull == null) {
            return strEmptyAsNull2;
        }
        if (strEmptyAsNull2 == null || strEmptyAsNull.equals(strEmptyAsNull2)) {
            return strEmptyAsNull;
        }
        throw natsJetStreamClientError.instance();
    }

    public static String validateNonWildcardKvKey(String str, String str2, boolean z) {
        return _validate(str, z, str2, new o31(str, 0, str2));
    }

    public static String validateNonWildcardKvKeyRequired(String str) {
        return validateNonWildcardKvKey(str, "Key", true);
    }

    public static long validateNotNegative(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(zn.m(str, " cannot be negative"));
    }

    public static String validateNotNull(String str, String str2) {
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException(zn.m(str2, " cannot be null"));
    }

    public static void validateNotSupplied(String str, NatsJetStreamClientError natsJetStreamClientError) {
        if (!nullOrEmpty(str)) {
            throw natsJetStreamClientError.instance();
        }
    }

    public static int validateNumberOfReplicas(int i) {
        if (i < 1 || i > 5) {
            throw new IllegalArgumentException("Replicas must be from 1 to 5 inclusive.");
        }
        return i;
    }

    public static String validatePrefixOrDomain(String str, String str2, boolean z) {
        return _validate(str, z, str2, new o31(str, 1, str2));
    }

    public static String validatePrintable(String str, String str2, boolean z) {
        return _validate(str, z, str2, new o31(str, 2, str2));
    }

    public static String validatePrintableExceptWildDotGt(String str, String str2, boolean z) {
        return _validate(str, z, str2, new o31(str, 3, str2));
    }

    public static String validatePrintableExceptWildDotGtSlashes(String str, String str2, boolean z) {
        return _validate(str, z, str2, new o31(str, 5, str2));
    }

    public static String validatePrintableExceptWildGt(String str, String str2, boolean z) {
        return _validate(str, z, str2, new o31(str, 4, str2));
    }

    public static String validateQueueName(String str, boolean z) {
        return validateSubjectTerm(str, "QueueName", z);
    }

    public static String validateReplyTo(String str, boolean z) {
        return validatePrintableExceptWildGt(str, "Reply To", z);
    }

    public static String validateSemVer(String str, String str2, boolean z) {
        return _validate(str, z, str2, new o31(str, 6, str2));
    }

    public static String validateStreamName(String str, boolean z) {
        return validatePrintableExceptWildDotGtSlashes(str, "Stream", z);
    }

    public static String validateSubject(String str, boolean z) {
        return validateSubjectTerm(str, "Subject", z);
    }

    public static String validateSubjectTerm(String str, String str2, boolean z) {
        String strEmptyAsNull = emptyAsNull(str);
        if (strEmptyAsNull == null) {
            if (z) {
                throw new IllegalArgumentException(zn.m(str2, " cannot be null or empty."));
            }
            return null;
        }
        if (strEmptyAsNull.endsWith(NatsConstants.DOT)) {
            throw new IllegalArgumentException(zn.m(str2, " cannot end with '.'"));
        }
        String[] strArrSplit = strEmptyAsNull.split("\\.");
        for (int i = 0; i < strArrSplit.length; i++) {
            String str3 = strArrSplit[i];
            int length = str3.length();
            if (length == 0) {
                if (i == 0) {
                    throw new IllegalArgumentException(zn.m(str2, " cannot start with '.'"));
                }
                throw new IllegalArgumentException(zn.m(str2, " segment cannot be empty"));
            }
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = str3.charAt(i2);
                if (cCharAt == '\t' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ') {
                    throw new IllegalArgumentException(zn.m(str2, " cannot contain space, tab, carriage return or linefeed character"));
                }
                if (cCharAt != '*') {
                    if (cCharAt == '>' && (length != 1 || i + 1 != strArrSplit.length)) {
                        throw new IllegalArgumentException(zn.m(str2, " wildcard improperly placed."));
                    }
                } else if (length != 1) {
                    throw new IllegalArgumentException(zn.m(str2, " wildcard improperly placed."));
                }
            }
        }
        return strEmptyAsNull;
    }

    public static String validateWildcardKvKey(String str, String str2, boolean z) {
        return _validate(str, z, str2, new o31(str, 8, str2));
    }

    public static boolean zeroOrLtMinus1(long j) {
        return j == 0 || j < -1;
    }

    public static boolean nullOrEmpty(String[] strArr) {
        return strArr == null || strArr.length == 0;
    }

    public static String validateSubject(String str, String str2, boolean z, boolean z2) {
        String strValidateSubjectTerm = validateSubjectTerm(str, str2, z);
        if (strValidateSubjectTerm != null && z2 && strValidateSubjectTerm.endsWith(".>")) {
            throw new IllegalArgumentException(zn.m(str2, " last segment cannot be '>'"));
        }
        return strValidateSubjectTerm;
    }

    public static boolean nullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static Duration validateDurationNotRequiredGtOrEqZero(long j) {
        if (j >= 0) {
            return Duration.ofMillis(j);
        }
        throw new IllegalArgumentException("Duration must be greater than or equal to 0.");
    }

    public static long validateGtZero(long j, String str) {
        if (j >= 1) {
            return j;
        }
        throw new IllegalArgumentException(zn.m(str, " must be greater than zero"));
    }

    public static Object validateNotNull(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new IllegalArgumentException(zn.m(str, " cannot be null"));
    }

    @Deprecated
    public static String required(String str, String str2, String str3) {
        if (emptyAsNull(str) == null || emptyAsNull(str2) == null) {
            throw new IllegalArgumentException(zn.m(str3, " cannot be null or empty."));
        }
        return str;
    }

    public static long validateMaxMessageSize(long j) {
        return validateMaxMessageSize(j, "Max Message Size");
    }

    public static <T> T required(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(zn.m(str, " cannot be null."));
    }

    public static void required(List<?> list, String str) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(zn.m(str, " cannot be null or empty."));
        }
    }

    public static void required(Map<?, ?> map, String str) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(zn.m(str, " cannot be null or empty."));
        }
    }
}
