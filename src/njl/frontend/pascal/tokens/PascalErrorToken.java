package njl.frontend.pascal.tokens;

import njl.frontend.Source;
import njl.frontend.pascal.PascalErrorCode;
import njl.frontend.pascal.PascalToken;
import njl.frontend.pascal.PascalTokenType;

public class PascalErrorToken extends PascalToken {
    public PascalErrorToken(Source source, PascalErrorCode errorCode, String tokenText) throws Exception {
        super(source);

        this.text = tokenText;
        this.type = PascalTokenType.ERROR;
        this.value = errorCode;
    }

    protected void extract() throws Exception {
    }
}
