package njl.frontend.pascal.tokens;

import njl.frontend.Source;
import njl.frontend.pascal.PascalToken;
import njl.frontend.pascal.PascalTokenType;
import njl.frontend.pascal.PascalErrorCode;

public class PascalSpecialSymbolToken extends PascalToken {
    public PascalSpecialSymbolToken(Source source) throws Exception {
        super(source);
    }

    protected void extract() throws Exception {
        char currentChar = currentChar();
        text = Character.toString(currentChar);
        type = null;

        switch (currentChar) {
            case '+': case '-': case '*': case '/': case ',':
            case ';': case '\'':case '=':case '(':case ')':
            case '[':case ']':case '{':case '}':case '^': {
                nextChar();
                break;
            }

            case ':': {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }

                break;
            }

            case '<': {
                currentChar = nextChar();

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                } else if (currentChar == '>') {
                    text += currentChar;
                    nextChar();
                }

                break;
            }

            case '>': {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }

                break;
            }

            case '.': {
                currentChar = nextChar();

                if (currentChar == '.') {
                    text += currentChar;
                    nextChar();
                }

                break;
            }

            default: {
                nextChar();
                type = PascalTokenType.ERROR;
                value = PascalErrorCode.INVALID_CHARACTER;
            }
        }

        if (type == null) {
            type = PascalTokenType.SPECIAL_SYMBOLS.get(text);
        }
    }
}
