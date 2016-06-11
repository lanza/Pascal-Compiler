package njl.frontend.pascal.parsers;

import njl.frontend.*;
import njl.frontend.pascal.*;
import njl.intermediate.*;

import static njl.frontend.pascal.PascalTokenType.*;
import static njl.frontend.pascal.PascalErrorCode.*;
import static njl.intermediate.icodeimpl.ICodeNodeTypeImpl.*;
import static njl.intermediate.icodeimpl.ICodeKeyImpl.*;

public class AssignmentStatementParser extends StatementParser {
    public AssignmentStatementParser(PascalParserTD parent) {
        super(parent);
    }

    public ICodeNode parse(Token token) throws Exception {
        ICodeNode assignNode = ICodeFactory.createICodeNode(ASSIGN);

        String targetName = token.text.toLowerCase();
        SymTabEntry targetId = symTabStack.lookup(targetName);
        if (targetId == null) {
            targetId = symTabStack.enterLocal(targetName);
        }
        targetId.appendLineNumber(token.getLineNum());
        token = nextToken();

        ICodeNode variableNode = ICodeFactory.createICodeNode(VARIABLE);
        variableNode.setAttribute(ID, targetId);

        assignNode.addChild(variableNode);

        if (token.type == COLON_EQUALS) {
            token = nextToken();
        } else {
            errorHandler.flag(token, MISSING_COLON_EQUALS, this);
        }

        ExpressionParser expressionParser = new ExpressionParser(this);
        assignNode.addChild(expressionParser.parse(token));

        return assignNode;
    }
}
