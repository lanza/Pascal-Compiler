package io.lanza.frontend.pascal;

import io.lanza.frontend.*;
import io.lanza.frontend.pascal.tokens.*;

import static io.lanza.frontend.Source.EOF;

public class PascalScanner extends Scanner {
    public PascalScanner(Source source) {
        super(source);
    }

    protected Token extractToken() throws Exception {

        skipWhiteSpace();

        Token token;
        char currentChar = currentChar();

        if (currentChar == EOF) {
            token = new EofToken(source, END_OF_FILE);
        } else if (Character.isLetter(currentChar)) {
            token = new PascalWordToken(source);
        } else if (Character.isDigit(currentChar)) {
            token = new PascalNumberToken(source);
        } else if (currentChar == '\'') {
            token = new PascalStringToken(source);
        } else if (PascalTokenType.SPECIAL_SYMBOLS.containsKey(Character.toString(currentChar))) {
            token = new PascalSpecialSymbolToken(source);
        } else {
            token = new PascalErrorToken(source, INVALID_CHARACTER, Character.toString(currentChar));
            nextChar();
        }
        return token;
    }
    private void skipWhiteSpace() throws Exception {
        char currentChar = currentChar();

        while (Character.isWhitespace(currentChar) || (currentChar == '{')) {
            if (currentChar == '{') {
                do {
                    currentChar = nextChar();
                } while ((currentChar != '}') && (currentChar != EOF));

                if (currentChar == '}') {
                    currentChar = nextChar();
                }
            } else {
                currentChar = nextChar();
            }
        }
    }
}
