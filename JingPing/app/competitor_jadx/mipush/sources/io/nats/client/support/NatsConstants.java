package io.nats.client.support;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface NatsConstants {
    public static final byte COLON = 58;
    public static final byte[] COLON_BYTES;
    public static final byte[] CONNECT_SP_BYTES;
    public static final byte CR = 13;
    public static final String CRLF = "\r\n";
    public static final byte[] CRLF_BYTES;
    public static final int DEFAULT_PORT = 4222;
    public static final String DOT = ".";
    public static final String EMPTY = "";
    public static final byte[] EMPTY_BODY;
    public static final String GREATER_THAN = ">";
    public static final String HEADER_VERSION = "NATS/1.0";
    public static final byte[] HEADER_VERSION_BYTES;
    public static final int HEADER_VERSION_BYTES_LEN;
    public static final byte[] HEADER_VERSION_BYTES_PLUS_CRLF;
    public static final byte[] HPUB_SP_BYTES;
    public static final int HPUB_SP_BYTES_LEN;
    public static final String INVALID_HEADER_COMPOSITION = "Invalid header composition";
    public static final String INVALID_HEADER_STATUS_CODE = "Invalid header status code";
    public static final String INVALID_HEADER_VERSION = "Invalid header version";
    public static final byte LF = 10;
    public static final int MAX_PROTOCOL_RECEIVE_OP_LENGTH = 4;
    public static final long NANOS_PER_MILLI = 1000000;
    public static final String NATS_PROTOCOL = "nats";
    public static final String NATS_PROTOCOL_SLASH_SLASH = "nats://";
    public static final String OP_CONNECT = "CONNECT";
    public static final int OP_CONNECT_SP_LEN;
    public static final String OP_ERR = "-ERR";
    public static final String OP_HMSG = "HMSG";
    public static final String OP_HPUB = "HPUB";
    public static final String OP_INFO = "INFO";
    public static final String OP_MSG = "MSG";
    public static final String OP_OK = "+OK";
    public static final String OP_PING = "PING";
    public static final byte[] OP_PING_BYTES;
    public static final String OP_PONG = "PONG";
    public static final byte[] OP_PONG_BYTES;
    public static final String OP_PUB = "PUB";
    public static final String OP_SUB = "SUB";
    public static final int OP_SUB_SP_LEN;
    public static final String OP_UNSUB = "UNSUB";
    public static final int OP_UNSUB_SP_LEN;
    public static final String OUTPUT_QUEUE_IS_FULL = "Output queue is full ";
    public static final byte[] PUB_SP_BYTES;
    public static final int PUB_SP_BYTES_LEN;
    public static final String SERIALIZED_HEADER_CANNOT_BE_NULL_OR_EMPTY = "Serialized header cannot be null or empty.";
    public static final byte SP = 32;
    public static final String SPACE = " ";
    public static final String STAR = "*";
    public static final byte[] SUB_SP_BYTES;
    public static final byte TAB = 9;
    public static final String UNKNOWN_OP = "UNKNOWN";
    public static final byte[] UNSUB_SP_BYTES;
    public static final List<String> WEBSOCKET_PROTOCOLS;

    @Deprecated
    public static final List<String> WSS_PROTOCOLS;
    public static final String TLS_PROTOCOL = "tls";
    public static final String OPENTLS_PROTOCOL = "opentls";
    public static final String WEBSOCKET_PROTOCOL = "ws";
    public static final String SECURE_WEBSOCKET_PROTOCOL = "wss";
    public static final List<String> KNOWN_PROTOCOLS = Arrays.asList("nats", TLS_PROTOCOL, OPENTLS_PROTOCOL, WEBSOCKET_PROTOCOL, SECURE_WEBSOCKET_PROTOCOL);
    public static final List<String> SECURE_PROTOCOLS = Arrays.asList(TLS_PROTOCOL, OPENTLS_PROTOCOL, SECURE_WEBSOCKET_PROTOCOL);

    static {
        List<String> listAsList = Arrays.asList(WEBSOCKET_PROTOCOL, SECURE_WEBSOCKET_PROTOCOL);
        WEBSOCKET_PROTOCOLS = listAsList;
        EMPTY_BODY = new byte[0];
        Charset charset = StandardCharsets.US_ASCII;
        byte[] bytes = HEADER_VERSION.getBytes(charset);
        HEADER_VERSION_BYTES = bytes;
        HEADER_VERSION_BYTES_PLUS_CRLF = "NATS/1.0\r\n".getBytes(charset);
        COLON_BYTES = ":".getBytes(charset);
        CRLF_BYTES = CRLF.getBytes(charset);
        HEADER_VERSION_BYTES_LEN = bytes.length;
        OP_PING_BYTES = "PING".getBytes();
        OP_PONG_BYTES = OP_PONG.getBytes();
        byte[] bytes2 = "PUB ".getBytes(charset);
        PUB_SP_BYTES = bytes2;
        byte[] bytes3 = "HPUB ".getBytes(charset);
        HPUB_SP_BYTES = bytes3;
        byte[] bytes4 = "CONNECT ".getBytes();
        CONNECT_SP_BYTES = bytes4;
        byte[] bytes5 = "SUB ".getBytes();
        SUB_SP_BYTES = bytes5;
        byte[] bytes6 = "UNSUB ".getBytes();
        UNSUB_SP_BYTES = bytes6;
        PUB_SP_BYTES_LEN = bytes2.length;
        HPUB_SP_BYTES_LEN = bytes3.length;
        OP_CONNECT_SP_LEN = bytes4.length;
        OP_SUB_SP_LEN = bytes5.length;
        OP_UNSUB_SP_LEN = bytes6.length;
        WSS_PROTOCOLS = listAsList;
    }
}
