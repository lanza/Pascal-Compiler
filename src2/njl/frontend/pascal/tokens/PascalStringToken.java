package njl.frontend.pascal.tokens;

import njl.frontend.Source;
import njl.frontend.pascal.PascalErrorCode;
import njl.frontend.pascal.PascalToken;
import njl.frontend.pascal.PascalTokenType;


import static njl.frontend.Source.EOF;

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

            type = PascalTokenType.STRING;
            value = valueBuffer.toString();
        } else {
            type = PascalTokenType.ERROR;
            value = PascalErrorCode.UNEXPECTED_EOF;
        }
        text = textBuffer.toString();
    }
}
