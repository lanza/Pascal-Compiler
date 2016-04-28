package io.lanza.frontend.pascal.tokens;

import io.lanza.frontend.*;
import io.lanza.frontend.pascal.*;

import io.lanza.frontend.pascal.PascalToken;
import io.lanza.frontend.pascal.PascalTokenType;


public class PascalWordToken extends PascalToken {

    public PascalWordToken(Source source) throws Exception {
        super(source);
    }

    protected void extract() throws Exception {
        StringBuilder textBuffer = new StringBuilder();
        char currentChar = currentChar();

        while (Character.isLetterOrDigit(currentChar)) {
            textBuffer.append(currentChar);
            currentChar = nextChar();
        }

        text = textBuffer.toString();

        type = (PascalTokenType.RESERVED_WORDS.contains(text.toLowerCase()))
                ? PascalTokenType.valueOf(text.toUpperCase())
                : PascalTokenType.IDENTIFIER;
    }
}

