package io.lanza.frontend.pascal.tokens;

import io.lanza.frontend.*;
import io.lanza.frontend.pascal.*;

import static io.lanza.frontend.pascal.PascalTokenType.*;

public class PascalErrorToken extends PascalToken {
    public PascalErrorToken(Source source, PascalErrorCode errorCode, String tokenText) throws Exception {
        super(source);

        this.text = tokenText;
        this.type = ERROR;
        this.value = errorCode;
    }

    protected void extract() throws Exception {
    }
}
