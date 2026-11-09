package io.nats.client.support;

import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class Token {
    private int end;
    private boolean hasValue;
    private final byte[] serialized;
    private final int start;
    private final TokenType type;

    public Token(byte[] bArr, int i, Token token, TokenType tokenType) {
        this(bArr, i, token.end + (token.type == TokenType.KEY ? 2 : 1), tokenType);
    }

    private void mustBeCrlf(int i, int i2) {
        int i3 = i2 + 1;
        if (i3 >= i || this.serialized[i3] != 10) {
            throw new IllegalArgumentException(NatsConstants.INVALID_HEADER_COMPOSITION);
        }
    }

    public String getValue() {
        if (!this.hasValue) {
            return "";
        }
        byte[] bArr = this.serialized;
        int i = this.start;
        return new String(bArr, i, (this.end - i) + 1, StandardCharsets.US_ASCII).trim();
    }

    public boolean hasValue() {
        return this.hasValue;
    }

    public boolean isType(TokenType tokenType) {
        return this.type == tokenType;
    }

    public void mustBe(TokenType tokenType) {
        if (this.type != tokenType) {
            throw new IllegalArgumentException(NatsConstants.INVALID_HEADER_COMPOSITION);
        }
    }

    public boolean samePoint(Token token) {
        return this.start == token.start && this.end == token.end && this.type == token.type;
    }

    public Token(byte[] bArr, int i, int i2, TokenType tokenType) {
        byte b;
        byte b2;
        this.serialized = bArr;
        if (i2 >= i) {
            throw new IllegalArgumentException(NatsConstants.INVALID_HEADER_COMPOSITION);
        }
        byte b3 = bArr[i2];
        byte b4 = NatsConstants.SP;
        if (b3 != 32) {
            if (b3 == 13) {
                mustBeCrlf(i, i2);
                this.type = TokenType.CRLF;
                this.start = i2;
                this.end = i2 + 1;
                return;
            }
            if (tokenType == TokenType.CRLF || tokenType == TokenType.SPACE) {
                throw new IllegalArgumentException(NatsConstants.INVALID_HEADER_COMPOSITION);
            }
            if (tokenType == null || tokenType == TokenType.TEXT) {
                this.type = TokenType.TEXT;
                b = 13;
                b4 = 13;
            } else {
                TokenType tokenType2 = TokenType.WORD;
                if (tokenType == tokenType2) {
                    this.type = tokenType2;
                    b = 13;
                } else {
                    this.type = TokenType.KEY;
                    b4 = NatsConstants.COLON;
                    b = 58;
                }
            }
            this.start = i2;
            this.end = i2;
            while (true) {
                i2++;
                if (i2 >= i || (b2 = bArr[i2]) == b4 || b2 == b) {
                    break;
                } else {
                    this.end = i2;
                }
            }
            if (i2 >= i) {
                throw new IllegalArgumentException(NatsConstants.INVALID_HEADER_COMPOSITION);
            }
            if (bArr[i2] == 13) {
                mustBeCrlf(i, i2);
            }
            this.hasValue = true;
            return;
        }
        this.type = TokenType.SPACE;
        this.start = i2;
        this.end = i2;
        while (true) {
            i2++;
            if (bArr[i2] != 32) {
                return;
            } else {
                this.end = i2;
            }
        }
    }
}
