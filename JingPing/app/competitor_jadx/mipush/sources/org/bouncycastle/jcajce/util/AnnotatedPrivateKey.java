package org.bouncycastle.jcajce.util;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AnnotatedPrivateKey implements PrivateKey {
    public static final String LABEL = "label";

    public AnnotatedPrivateKey addAnnotation(String str, Object obj) {
        new HashMap((Map) null).put(str, obj);
        throw null;
    }

    public boolean equals(Object obj) {
        boolean z = obj instanceof AnnotatedPrivateKey;
        throw null;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        throw null;
    }

    public Object getAnnotation(String str) {
        throw null;
    }

    public Map<String, Object> getAnnotations() {
        return null;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        throw null;
    }

    @Override // java.security.Key
    public String getFormat() {
        throw null;
    }

    public PrivateKey getKey() {
        throw null;
    }

    public int hashCode() {
        throw null;
    }

    public AnnotatedPrivateKey removeAnnotation(String str) {
        new HashMap((Map) null).remove(str);
        throw null;
    }

    public String toString() {
        throw null;
    }
}
