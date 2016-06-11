package njl.frontend.pascal;

import njl.frontend.*;
import njl.frontend.pascal.parsers.*;
import njl.intermediate.*;
import njl.message.*;

import static njl.frontend.pascal.PascalTokenType.*;
import static njl.frontend.pascal.PascalErrorCode.*;
import static njl.message.MessageType.PARSER_SUMMARY;

public class PascalParserTD extends Parser {

    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }
    public PascalParserTD(PascalParserTD parent) { super(parent.scanner); }

    protected static PascalErrorHandler errorHandler = new PascalErrorHandler();
    public PascalErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void parse() throws Exception {
        long startTime = System.currentTimeMillis();
        iCode = ICodeFactory.createICode();

        try {
            Token token = nextToken();
            ICodeNode rootNode = null;

            if (token.type == BEGIN) {
                StatementParser statementParser = new StatementParser(this);
                rootNode = statementParser.parse(token);
                token = currentToken();
            } else {
                errorHandler.flag(token, UNEXPECTED_TOKEN, this);
            }
            token = currentToken();

            if (rootNode != null) {
                iCode.setRoot(rootNode);
            }

            float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
            sendMessage(new Message(PARSER_SUMMARY, new Number[]{token.getLineNum(), getErrorCount(), elapsedTime}));
        } catch (java.io.IOException ex) {
            errorHandler.abortTranslation(IO_ERROR, this);
        }
    }

    public int getErrorCount() {
        return errorHandler.errorCount;
    }
}
