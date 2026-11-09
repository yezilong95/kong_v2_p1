package io.nats.client.support;

import defpackage.zn;
import io.nats.client.impl.Headers;

/* JADX INFO: loaded from: classes.dex */
public abstract class NatsObjectStoreUtil {
    public static final int DEFAULT_CHUNK_SIZE = 131072;
    public static final String OBJ_CHUNK_PART = ".C";
    public static final String OBJ_META_PART = ".M";
    public static final String OBJ_STREAM_PREFIX = "OBJ_";
    public static final int OBJ_STREAM_PREFIX_LEN = 4;
    public static final String OBJ_SUBJECT_PREFIX = "$O.";
    public static final String OBJ_SUBJECT_SUFFIX = ".>";

    private NatsObjectStoreUtil() {
    }

    public static String encodeForSubject(String str) {
        return Encoding.base64BasicEncodeToString(str);
    }

    public static String extractBucketName(String str) {
        return str.substring(OBJ_STREAM_PREFIX_LEN);
    }

    public static Headers getMetaHeaders() {
        return new Headers().put(NatsJetStreamConstants.ROLLUP_HDR, NatsJetStreamConstants.ROLLUP_HDR_SUBJECT);
    }

    public static String toChunkPrefix(String str) {
        return zn.n(OBJ_SUBJECT_PREFIX, str, ".C.");
    }

    public static String toChunkStreamSubject(String str) {
        return zn.n(OBJ_SUBJECT_PREFIX, str, ".C.>");
    }

    public static String toMetaPrefix(String str) {
        return zn.n(OBJ_SUBJECT_PREFIX, str, ".M.");
    }

    public static String toMetaStreamSubject(String str) {
        return zn.n(OBJ_SUBJECT_PREFIX, str, ".M.>");
    }

    public static String toStreamName(String str) {
        return zn.v(OBJ_STREAM_PREFIX, str);
    }
}
