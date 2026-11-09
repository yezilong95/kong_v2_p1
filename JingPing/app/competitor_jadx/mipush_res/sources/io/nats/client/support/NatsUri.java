package io.nats.client.support;

import defpackage.zn;
import io.nats.client.Options;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class NatsUri {
    private static final String COLON_SLASH_SLASH = "://";
    private static final int NO_PORT = -1;
    private static final String UNABLE_TO_PARSE = "Unable to parse URI string.";
    private static final String UNSUPPORTED_SCHEME = "Unsupported NATS URI scheme.";
    private static final String URI_E_ALLOW_TRY_PREFIXED = "Illegal character in scheme name at index";
    private boolean hostIsIpAddress;
    private boolean isSecure;
    private boolean isWebsocket;
    private final URI uri;
    private static final Pattern IPV4_RE = Pattern.compile("(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])");
    public static NatsUri DEFAULT_NATS_URI = new NatsUri();

    public static class Helper {
        URI uri;
        String url;
    }

    public NatsUri() {
        try {
            this.uri = new URI(Options.DEFAULT_URL);
            postConstruct();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String equivalentComparable() {
        return this.uri.getHost().toLowerCase() + this.uri.getPort();
    }

    public static String join(String str, List<NatsUri> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(str);
            }
            sb.append(list.get(i).toString());
        }
        return sb.toString();
    }

    private Helper parse(String str, boolean z, String str2) throws URISyntaxException {
        Helper helper = new Helper();
        try {
            helper.url = str.trim();
            helper.uri = new URI(helper.url);
            return helper;
        } catch (URISyntaxException e) {
            if (z && e.getMessage().contains(URI_E_ALLOW_TRY_PREFIXED)) {
                return tryPrefixed(helper, str2);
            }
            throw e;
        }
    }

    private void postConstruct() {
        String lowerCase = this.uri.getScheme().toLowerCase();
        this.isSecure = NatsConstants.SECURE_PROTOCOLS.contains(lowerCase);
        this.isWebsocket = NatsConstants.WEBSOCKET_PROTOCOLS.contains(lowerCase);
        String host = this.uri.getHost();
        this.hostIsIpAddress = IPV4_RE.matcher(host).matches() || (host.startsWith("[") && host.endsWith("]"));
    }

    private Helper tryPrefixed(Helper helper, String str) throws URISyntaxException {
        StringBuilder sbP = zn.p(str);
        sbP.append(helper.url);
        return parse(sbP.toString(), false, str);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof NatsUri) {
            obj = ((NatsUri) obj).uri;
        }
        return this.uri.equals(obj);
    }

    public boolean equivalent(NatsUri natsUri) {
        return equivalentComparable().compareTo(natsUri.equivalentComparable()) == 0;
    }

    public String getHost() {
        return this.uri.getHost();
    }

    public int getPort() {
        return this.uri.getPort();
    }

    public String getScheme() {
        return this.uri.getScheme();
    }

    public URI getUri() {
        return this.uri;
    }

    public String getUserInfo() {
        return this.uri.getUserInfo();
    }

    public int hashCode() {
        return this.uri.hashCode();
    }

    public boolean hostIsIpAddress() {
        return this.hostIsIpAddress;
    }

    public boolean isSecure() {
        return this.isSecure;
    }

    public boolean isWebsocket() {
        return this.isWebsocket;
    }

    public NatsUri reHost(String str) throws URISyntaxException {
        String str2;
        if (this.uri.getRawUserInfo() == null) {
            str2 = this.uri.getScheme() + COLON_SLASH_SLASH + str + ":" + this.uri.getPort();
        } else {
            str2 = this.uri.getScheme() + COLON_SLASH_SLASH + this.uri.getRawUserInfo() + "@" + str + ":" + this.uri.getPort();
        }
        return new NatsUri(str2, this.uri.getScheme());
    }

    public String toString() {
        return this.uri.toString();
    }

    public NatsUri(URI uri) throws URISyntaxException {
        this(uri.toString(), null);
    }

    public NatsUri(String str) throws URISyntaxException {
        this(str, null);
    }

    public NatsUri(String str, String str2) throws URISyntaxException {
        String lowerCase;
        if (str2 == null) {
            lowerCase = NatsConstants.NATS_PROTOCOL_SLASH_SLASH;
        } else {
            lowerCase = str2.toLowerCase();
            if (!lowerCase.endsWith(COLON_SLASH_SLASH)) {
                lowerCase = lowerCase.concat(COLON_SLASH_SLASH);
            }
        }
        Helper helperTryPrefixed = parse(str, true, lowerCase);
        String scheme = helperTryPrefixed.uri.getScheme();
        String path = helperTryPrefixed.uri.getPath();
        if (scheme == null) {
            if (path != null) {
                helperTryPrefixed = tryPrefixed(helperTryPrefixed, lowerCase);
                scheme = helperTryPrefixed.uri.getScheme();
                path = helperTryPrefixed.uri.getPath();
            } else {
                throw new URISyntaxException(str, UNABLE_TO_PARSE);
            }
        }
        String host = helperTryPrefixed.uri.getHost();
        if (host == null) {
            if (path == null) {
                helperTryPrefixed = tryPrefixed(helperTryPrefixed, lowerCase);
                scheme = helperTryPrefixed.uri.getScheme();
                host = helperTryPrefixed.uri.getHost();
            } else {
                throw new URISyntaxException(str, UNABLE_TO_PARSE);
            }
        }
        if (host != null) {
            String lowerCase2 = scheme.toLowerCase();
            if (NatsConstants.KNOWN_PROTOCOLS.contains(lowerCase2)) {
                if (!lowerCase2.equals(scheme)) {
                    helperTryPrefixed.url = helperTryPrefixed.url.replace(scheme, lowerCase2);
                }
                if (helperTryPrefixed.uri.getPort() == -1) {
                    this.uri = new URI(zn.o(new StringBuilder(), helperTryPrefixed.url, ":4222"));
                } else {
                    this.uri = new URI(helperTryPrefixed.url);
                }
                postConstruct();
                return;
            }
            throw new URISyntaxException(str, UNSUPPORTED_SCHEME);
        }
        throw new URISyntaxException(str, UNABLE_TO_PARSE);
    }
}
