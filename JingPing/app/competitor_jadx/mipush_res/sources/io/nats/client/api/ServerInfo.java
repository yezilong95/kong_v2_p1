package io.nats.client.api;

import defpackage.zn;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonParseException;
import io.nats.client.support.JsonParser;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.ServerVersion;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ServerInfo {
    private final boolean authRequired;
    private final int clientId;
    private final String clientIp;
    private final String cluster;
    private final List<String> connectURLs;
    private final String go;
    private final boolean headersSupported;
    private final String host;
    private final boolean jetStream;
    private final boolean lameDuckMode;
    private final long maxPayload;
    private final byte[] nonce;
    private final int port;
    private final int protocolVersion;
    private final String serverId;
    private final String serverName;
    private final boolean tlsAvailable;
    private final boolean tlsRequired;
    private final String version;

    public ServerInfo(String str) {
        if (str == null || str.length() < 6 || !('{' == str.charAt(0) || '{' == str.charAt(5))) {
            throw new IllegalArgumentException("Invalid Server Info");
        }
        try {
            JsonValue jsonValue = JsonParser.parse(str, str.indexOf("{"));
            this.serverId = JsonValueUtils.readString(jsonValue, ApiConstants.SERVER_ID);
            this.serverName = JsonValueUtils.readString(jsonValue, ApiConstants.SERVER_NAME);
            this.version = JsonValueUtils.readString(jsonValue, ApiConstants.VERSION);
            this.go = JsonValueUtils.readString(jsonValue, ApiConstants.GO);
            this.host = JsonValueUtils.readString(jsonValue, ApiConstants.HOST);
            this.headersSupported = JsonValueUtils.readBoolean(jsonValue, ApiConstants.HEADERS);
            this.authRequired = JsonValueUtils.readBoolean(jsonValue, ApiConstants.AUTH_REQUIRED);
            this.nonce = JsonValueUtils.readBytes(jsonValue, ApiConstants.NONCE);
            this.tlsRequired = JsonValueUtils.readBoolean(jsonValue, "tls_required");
            this.tlsAvailable = JsonValueUtils.readBoolean(jsonValue, ApiConstants.TLS_AVAILABLE);
            this.lameDuckMode = JsonValueUtils.readBoolean(jsonValue, ApiConstants.LAME_DUCK_MODE);
            this.jetStream = JsonValueUtils.readBoolean(jsonValue, ApiConstants.JETSTREAM);
            this.port = JsonValueUtils.readInteger(jsonValue, ApiConstants.PORT, 0);
            this.protocolVersion = JsonValueUtils.readInteger(jsonValue, ApiConstants.PROTO, 0);
            this.maxPayload = JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_PAYLOAD, 0L);
            this.clientId = JsonValueUtils.readInteger(jsonValue, ApiConstants.CLIENT_ID, 0);
            this.clientIp = JsonValueUtils.readString(jsonValue, ApiConstants.CLIENT_IP);
            this.cluster = JsonValueUtils.readString(jsonValue, ApiConstants.CLUSTER);
            this.connectURLs = JsonValueUtils.readStringListIgnoreEmpty(jsonValue, ApiConstants.CONNECT_URLS);
        } catch (JsonParseException unused) {
            throw new IllegalArgumentException("Invalid Server Info Json");
        }
    }

    public int getClientId() {
        return this.clientId;
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public String getCluster() {
        return this.cluster;
    }

    public List<String> getConnectURLs() {
        return this.connectURLs;
    }

    public String getGoVersion() {
        return this.go;
    }

    public String getHost() {
        return this.host;
    }

    public long getMaxPayload() {
        return this.maxPayload;
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public int getPort() {
        return this.port;
    }

    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getServerName() {
        return this.serverName;
    }

    public String getVersion() {
        return this.version;
    }

    public boolean isAuthRequired() {
        return this.authRequired;
    }

    public boolean isHeadersSupported() {
        return this.headersSupported;
    }

    public boolean isJetStreamAvailable() {
        return this.jetStream;
    }

    public boolean isLameDuckMode() {
        return this.lameDuckMode;
    }

    public boolean isNewerVersionThan(String str) {
        return ServerVersion.isNewer(this.version, str);
    }

    public boolean isOlderThanVersion(String str) {
        return ServerVersion.isOlder(this.version, str);
    }

    public boolean isSameOrNewerThanVersion(String str) {
        return ServerVersion.isSameOrNewer(this.version, str);
    }

    public boolean isSameOrOlderThanVersion(String str) {
        return ServerVersion.isSameOrOlder(this.version, str);
    }

    public boolean isSameVersion(String str) {
        return ServerVersion.isSame(this.version, str);
    }

    public boolean isTLSAvailable() {
        return this.tlsAvailable;
    }

    public boolean isTLSRequired() {
        return this.tlsRequired;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ServerInfo{serverId='");
        sb.append(this.serverId);
        sb.append("', serverName='");
        sb.append(this.serverName);
        sb.append("', version='");
        sb.append(this.version);
        sb.append("', go='");
        sb.append(this.go);
        sb.append("', host='");
        sb.append(this.host);
        sb.append("', port=");
        sb.append(this.port);
        sb.append(", headersSupported=");
        sb.append(this.headersSupported);
        sb.append(", authRequired=");
        sb.append(this.authRequired);
        sb.append(", tlsRequired=");
        sb.append(this.tlsRequired);
        sb.append(", tlsAvailable=");
        sb.append(this.tlsAvailable);
        sb.append(", maxPayload=");
        sb.append(this.maxPayload);
        sb.append(", connectURLs=");
        sb.append(this.connectURLs);
        sb.append(", protocolVersion=");
        sb.append(this.protocolVersion);
        sb.append(", nonce=");
        sb.append(Arrays.toString(this.nonce));
        sb.append(", lameDuckMode=");
        sb.append(this.lameDuckMode);
        sb.append(", jetStream=");
        sb.append(this.jetStream);
        sb.append(", clientId=");
        sb.append(this.clientId);
        sb.append(", clientIp='");
        sb.append(this.clientIp);
        sb.append("', cluster='");
        return zn.o(sb, this.cluster, "'}");
    }
}
