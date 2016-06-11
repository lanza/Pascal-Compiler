package njl.frontend.pascal.tokens;

import njl.frontend.pascal.PascalToken;
import njl.frontend.pascal.PascalTokenType;
import njl.frontend.Source;


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

