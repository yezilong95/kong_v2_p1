package org.java_websocket.protocols;

import java.util.regex.Pattern;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class Protocol implements IProtocol {
    private final String providedProtocol;
    private static final Pattern patternSpace = Pattern.compile(" ");
    private static final Pattern patternComma = Pattern.compile(",");

    public Protocol(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        this.providedProtocol = str;
    }

    @Override // org.java_websocket.protocols.IProtocol
    public boolean acceptProvidedProtocol(String str) {
        if (ExtensionRequestData.EMPTY_VALUE.equals(this.providedProtocol)) {
            return true;
        }
        for (String str2 : patternComma.split(patternSpace.matcher(str).replaceAll(ExtensionRequestData.EMPTY_VALUE))) {
            if (this.providedProtocol.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.java_websocket.protocols.IProtocol
    public IProtocol copyInstance() {
        return new Protocol(getProvidedProtocol());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.providedProtocol.equals(((Protocol) obj).providedProtocol);
    }

    @Override // org.java_websocket.protocols.IProtocol
    public String getProvidedProtocol() {
        return this.providedProtocol;
    }

    public int hashCode() {
        return this.providedProtocol.hashCode();
    }

    @Override // org.java_websocket.protocols.IProtocol
    public String toString() {
        return getProvidedProtocol();
    }
}
