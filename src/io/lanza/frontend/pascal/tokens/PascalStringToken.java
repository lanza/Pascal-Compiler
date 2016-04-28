package io.lanza.frontend.pascal.tokens;

import io.lanza.frontend.*;
import io.lanza.frontend.pascal.*;

import static io.lanza.frontend.Source.EOL;
import static io.lanza.frontend.Source.EOF;
import static io.lanza.frontend.pascal.PascalTokenType.*;
import static io.lanza.frontend.pascal.PascalErrorCode.*;

public class PascalStringToken extends PascalToken {
    public PascalStringToken(Source source) throws Exception {
        super(source);
    }

    protected void extract() throws Exception {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();

        char currentChar = nextChar();
        textBuffer.append('\'');

        do {
            if (Character.isWhitespace(currentChar)) {
                currentChar = ' ';
            }

            if ((currentChar != '\'') && (currentChar != EOF)) {
                textBuffer.append(currentChar);
                valueBuffer.append(currentChar);
                currentChar = nextChar();
            }

            if (currentChar == '\'') {
                while ((currentChar == '\'') && (peekChar() == '\'')) {
                    textBuffer.append("''");
                    valueBuffer.append(currentChar);
                    currentChar = nextChar();
                    currentChar = nextChar();
                }
            }
        } while ((currentChar != '\'') && (currentChar != EOF));

        if (currentChar == '\'') {
            nextChar();
            textBuffer.append('\'');

            type = STRING;
            value = valueBuffer.toString();
        } else {
            type = ERROR;
            value = UNEXPECTED_EOF;
        }
        text = textBuffer.toString();
    }
}
