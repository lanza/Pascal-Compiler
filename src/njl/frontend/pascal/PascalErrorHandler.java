package njl.frontend.pascal;

import njl.frontend.Parser;
import njl.frontend.Token;
import njl.message.Message;


import static njl.frontend.pascal.PascalErrorCode.TOO_MANY_ERRORS;
import static njl.message.MessageType.SYNTAX_ERROR;

public class PascalErrorHandler {
    private static final int MAX_ERRORS = 25;
    public static int errorCount = 0;

    public void flag(Token token, PascalErrorCode errorCode, Parser parser) {
        parser.sendMessage(new Message(SYNTAX_ERROR, new Object[] {token.lineNum, token.position, token.text, errorCode.toString()}));
        if (++errorCount > MAX_ERRORS) {
            abortTranslation(TOO_MANY_ERRORS, parser);
        }
    }

    public void abortTranslation(PascalErrorCode errorCode, Parser parser) {
        String fatalText = "FATAL ERROR: " + errorCode.toString();
        parser.sendMessage(new Message(SYNTAX_ERROR, new Object[] {0,0,"",fatalText}));
        System.exit(errorCode.status);
    }
}
