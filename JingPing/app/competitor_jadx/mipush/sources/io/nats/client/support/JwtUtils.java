package io.nats.client.support;

import io.nats.client.NKey;
import io.nats.client.NatsSystemClock;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class JwtUtils {
    private static final String ENCODED_CLAIM_HEADER = Encoding.base64UrlEncodeToString("{\"typ\":\"JWT\", \"alg\":\"ed25519-nkey\"}");
    public static final String NATS_USER_JWT_FORMAT = "-----BEGIN NATS USER JWT-----\n%s\n------END NATS USER JWT------\n\n************************* IMPORTANT *************************\nNKEY Seed printed below can be used to sign and prove identity.\nNKEYs are sensitive and should be treated as secrets.\n\n-----BEGIN USER NKEY SEED-----\n%s\n------END USER NKEY SEED------\n\n*************************************************************\n";
    private static final long NO_LIMIT = -1;

    public static class Claim implements JsonSerializable {
        String aud;
        Duration exp;
        long iat;
        String iss;
        String jti;
        String name;
        JsonSerializable nats;
        String sub;

        @Override // io.nats.client.support.JsonSerializable
        public String toJson() {
            StringBuilder sbBeginJson = JsonUtils.beginJson();
            JsonUtils.addField(sbBeginJson, "aud", this.aud);
            JsonUtils.addFieldEvenEmpty(sbBeginJson, "jti", this.jti);
            JsonUtils.addField(sbBeginJson, "iat", Long.valueOf(this.iat));
            JsonUtils.addField(sbBeginJson, "iss", this.iss);
            JsonUtils.addField(sbBeginJson, ApiConstants.NAME, this.name);
            JsonUtils.addField(sbBeginJson, NatsJetStreamConstants.ROLLUP_HDR_SUBJECT, this.sub);
            Duration duration = this.exp;
            if (duration != null && !duration.isZero() && !this.exp.isNegative()) {
                JsonUtils.addField(sbBeginJson, "exp", Long.valueOf(this.iat + (this.exp.toMillis() / 1000)));
            }
            JsonUtils.addField(sbBeginJson, "nats", this.nats);
            return JsonUtils.endJson(sbBeginJson).toString();
        }
    }

    public static class Permission implements JsonSerializable {
        public String[] allow;
        public String[] deny;

        public Permission allow(String... strArr) {
            this.allow = strArr;
            return this;
        }

        public Permission deny(String... strArr) {
            this.deny = strArr;
            return this;
        }

        @Override // io.nats.client.support.JsonSerializable
        public String toJson() {
            StringBuilder sbBeginJson = JsonUtils.beginJson();
            JsonUtils.addStrings(sbBeginJson, "allow", this.allow);
            JsonUtils.addStrings(sbBeginJson, "deny", this.deny);
            return JsonUtils.endJson(sbBeginJson).toString();
        }
    }

    public static class ResponsePermission implements JsonSerializable {
        public Duration expires;
        public int maxMsgs;

        public ResponsePermission expires(Duration duration) {
            this.expires = duration;
            return this;
        }

        public ResponsePermission maxMsgs(int i) {
            this.maxMsgs = i;
            return this;
        }

        @Override // io.nats.client.support.JsonSerializable
        public String toJson() {
            StringBuilder sbBeginJson = JsonUtils.beginJson();
            JsonUtils.addField(sbBeginJson, "max", Integer.valueOf(this.maxMsgs));
            JsonUtils.addFieldAsNanos(sbBeginJson, "ttl", this.expires);
            return JsonUtils.endJson(sbBeginJson).toString();
        }

        public ResponsePermission expires(long j) {
            this.expires = Duration.ofMillis(j);
            return this;
        }
    }

    public static class TimeRange implements JsonSerializable {
        public String end;
        public String start;

        public TimeRange(String str, String str2) {
            this.start = str;
            this.end = str2;
        }

        @Override // io.nats.client.support.JsonSerializable
        public String toJson() {
            StringBuilder sbBeginJson = JsonUtils.beginJson();
            JsonUtils.addField(sbBeginJson, "start", this.start);
            JsonUtils.addField(sbBeginJson, "end", this.end);
            return JsonUtils.endJson(sbBeginJson).toString();
        }
    }

    public static class UserClaim implements JsonSerializable {
        public String[] allowedConnectionTypes;
        public boolean bearerToken;
        public String issuerAccount;
        public String locale;
        public Permission pub;
        public ResponsePermission resp;
        public String[] src;
        public Permission sub;
        public String[] tags;
        public List<TimeRange> times;
        public String type = "user";
        public int version = 2;
        public long subs = -1;
        public long data = -1;
        public long payload = -1;

        public UserClaim(String str) {
            this.issuerAccount = str;
        }

        public UserClaim allowedConnectionTypes(String... strArr) {
            this.allowedConnectionTypes = strArr;
            return this;
        }

        public UserClaim bearerToken(boolean z) {
            this.bearerToken = z;
            return this;
        }

        public UserClaim data(long j) {
            this.data = j;
            return this;
        }

        public UserClaim locale(String str) {
            this.locale = str;
            return this;
        }

        public UserClaim payload(long j) {
            this.payload = j;
            return this;
        }

        public UserClaim pub(Permission permission) {
            this.pub = permission;
            return this;
        }

        public UserClaim resp(ResponsePermission responsePermission) {
            this.resp = responsePermission;
            return this;
        }

        public UserClaim src(String... strArr) {
            this.src = strArr;
            return this;
        }

        public UserClaim sub(Permission permission) {
            this.sub = permission;
            return this;
        }

        public UserClaim subs(long j) {
            this.subs = j;
            return this;
        }

        public UserClaim tags(String... strArr) {
            this.tags = strArr;
            return this;
        }

        public UserClaim times(List<TimeRange> list) {
            this.times = list;
            return this;
        }

        @Override // io.nats.client.support.JsonSerializable
        public String toJson() {
            StringBuilder sbBeginJson = JsonUtils.beginJson();
            JsonUtils.addField(sbBeginJson, "issuer_account", this.issuerAccount);
            JsonUtils.addStrings(sbBeginJson, ApiConstants.TAGS, this.tags);
            JsonUtils.addField(sbBeginJson, ApiConstants.TYPE, this.type);
            JsonUtils.addField(sbBeginJson, ApiConstants.VERSION, Integer.valueOf(this.version));
            JsonUtils.addField(sbBeginJson, "pub", this.pub);
            JsonUtils.addField(sbBeginJson, NatsJetStreamConstants.ROLLUP_HDR_SUBJECT, this.sub);
            JsonUtils.addField(sbBeginJson, "resp", this.resp);
            JsonUtils.addStrings(sbBeginJson, ApiConstants.SRC, this.src);
            JsonUtils.addJsons(sbBeginJson, "times", this.times);
            JsonUtils.addField(sbBeginJson, "times_location", this.locale);
            JsonUtils.addFieldWhenGteMinusOne(sbBeginJson, "subs", Long.valueOf(this.subs));
            JsonUtils.addFieldWhenGteMinusOne(sbBeginJson, ApiConstants.DATA, Long.valueOf(this.data));
            JsonUtils.addFieldWhenGteMinusOne(sbBeginJson, "payload", Long.valueOf(this.payload));
            JsonUtils.addFldWhenTrue(sbBeginJson, "bearer_token", Boolean.valueOf(this.bearerToken));
            JsonUtils.addStrings(sbBeginJson, "allowed_connection_types", this.allowedConnectionTypes);
            return JsonUtils.endJson(sbBeginJson).toString();
        }
    }

    private JwtUtils() {
    }

    public static long currentTimeSeconds() {
        return NatsSystemClock.currentTimeMillis() / 1000;
    }

    public static String getClaimBody(String str) {
        return Encoding.base64UrlDecodeToString(str.split("\\.")[1]);
    }

    public static String issueJWT(NKey nKey, String str, String str2, Duration duration, long j, String str3, JsonSerializable jsonSerializable) throws GeneralSecurityException, IOException {
        return issueJWT(nKey, str, str2, duration, j, str3, null, jsonSerializable);
    }

    public static String issueUserJWT(NKey nKey, String str, String str2) throws GeneralSecurityException, IOException {
        return issueUserJWT(nKey, str2, (String) null, (Duration) null, currentTimeSeconds(), (String) null, new UserClaim(str));
    }

    public static String issueJWT(NKey nKey, String str, String str2, Duration duration, long j, String str3, String str4, JsonSerializable jsonSerializable) throws GeneralSecurityException, IOException {
        Claim claim = new Claim();
        claim.aud = str4;
        claim.iat = j;
        claim.iss = str3;
        claim.name = str2;
        claim.sub = str;
        claim.exp = duration;
        claim.nats = jsonSerializable;
        claim.jti = new String(Encoding.base32Encode(MessageDigest.getInstance(Digester.DEFAULT_DIGEST_ALGORITHM).digest(claim.toJson().getBytes(StandardCharsets.US_ASCII))));
        String strBase64UrlEncodeToString = Encoding.base64UrlEncodeToString(claim.toJson());
        StringBuilder sb = new StringBuilder();
        String str5 = ENCODED_CLAIM_HEADER;
        sb.append(str5);
        sb.append(NatsConstants.DOT);
        sb.append(strBase64UrlEncodeToString);
        return str5 + NatsConstants.DOT + strBase64UrlEncodeToString + NatsConstants.DOT + Encoding.base64UrlEncodeToString(nKey.sign(sb.toString().getBytes(StandardCharsets.UTF_8)));
    }

    public static String issueUserJWT(NKey nKey, String str, String str2, String str3) throws GeneralSecurityException, IOException {
        return issueUserJWT(nKey, str2, str3, (Duration) null, currentTimeSeconds(), (String) null, new UserClaim(str));
    }

    public static String issueUserJWT(NKey nKey, String str, String str2, String str3, Duration duration, String... strArr) throws GeneralSecurityException, IOException {
        return issueUserJWT(nKey, str2, str3, duration, currentTimeSeconds(), (String) null, new UserClaim(str).tags(strArr));
    }

    public static String issueUserJWT(NKey nKey, String str, String str2, String str3, Duration duration, String[] strArr, long j) throws GeneralSecurityException, IOException {
        return issueUserJWT(nKey, str2, str3, duration, j, (String) null, new UserClaim(str).tags(strArr));
    }

    public static String issueUserJWT(NKey nKey, String str, String str2, String str3, Duration duration, String[] strArr, long j, String str4) throws GeneralSecurityException, IOException {
        return issueUserJWT(nKey, str2, str3, duration, j, str4, new UserClaim(str).tags(strArr));
    }

    public static String issueUserJWT(NKey nKey, String str, String str2, Duration duration, long j, UserClaim userClaim) throws GeneralSecurityException, IOException {
        return issueUserJWT(nKey, str, str2, duration, j, (String) null, userClaim);
    }

    public static String issueUserJWT(NKey nKey, String str, String str2, Duration duration, long j, String str3, UserClaim userClaim) throws GeneralSecurityException, IOException {
        NKey.Type type = nKey.getType();
        NKey.Type type2 = NKey.Type.ACCOUNT;
        if (type == type2) {
            NKey nKeyFromPublicKey = NKey.fromPublicKey(userClaim.issuerAccount.toCharArray());
            if (nKeyFromPublicKey.getType() == type2) {
                NKey nKeyFromPublicKey2 = NKey.fromPublicKey(str.toCharArray());
                if (nKeyFromPublicKey2.getType() == NKey.Type.USER) {
                    return issueJWT(nKey, str, Validator.nullOrEmpty(str2) ? str : str2, duration, j, new String(nKey.getPublicKey()), str3, userClaim);
                }
                throw new IllegalArgumentException("issueUserJWT requires a user key for the publicUserKey parameter, but got " + nKeyFromPublicKey2.getType());
            }
            throw new IllegalArgumentException("issueUserJWT requires an account key for the accountId parameter, but got " + nKeyFromPublicKey.getType());
        }
        throw new IllegalArgumentException("issueUserJWT requires an account key for the signingKey parameter, but got " + nKey.getType());
    }
}
