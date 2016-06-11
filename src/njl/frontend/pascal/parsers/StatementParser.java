package njl.frontend.pascal.parsers;

import njl.frontend.*;
import njl.frontend.pascal.*;
import njl.intermediate.*;
import sun.tools.tree.CompoundStatement;

import static njl.frontend.pascal.PascalTokenType.*;
import static njl.frontend.pascal.PascalErrorCode.*;
import static njl.intermediate.icodeimpl.ICodeNodeTypeImpl.*;
import static njl.intermediate.icodeimpl.ICodeKeyImpl.*;

public class StatementParser extends PascalParserTD {
    public StatementParser(PascalParserTD parent) {
        super(parent);
    }

    public ICodeNode parse(Token token) throws Exception {

        ICodeNode statementNode = null;

        switch ((PascalTokenType) token.type) {
            case BEGIN: {
                CompoundStatementParser compoundParser = new CompoundStatementParser(this);
                statementNode = compoundParser.parse(token);
                break;
            }
            case IDENTIFIER: {
                AssignmentStatementParser assignmentParser = new AssignmentStatementParser(this);
                statementNode = assignmentParser.parse(token);
                break;
            }
            default: {
                statementNode = ICodeFactory.createICodeNode(NO_OP);
                break;
            }
        }

        setLineNumber(statementNode, token);
        return statementNode;
    }
    protected void setLineNumber(ICodeNode node, Token token) {
        if (node != null) {
            node.setAttribute(LINE, token.getLineNum());
        }
    }

    protected void parseList(Token token, ICodeNode parentNode, PascalTokenType terminator, PascalErrorCode errorCode) throws Exception {
        while (!(token instanceof EofToken) && (token.type != terminator)) {

            ICodeNode statementNode = parse(token);
            parentNode.addChild(statementNode);

            token = currentToken();
            TokenType tokenType = token.type;

            if (tokenType == SEMICOLON) {
                token = nextToken();
            } else if (tokenType == IDENTIFIER) {
                errorHandler.flag(token, MISSING_SEMICOLON, this);
            } else if (tokenType != terminator) {
                errorHandler.flag(token, UNEXPECTED_TOKEN, this);
                token = nextToken();
            }
        }

        if (token.type == terminator) {
            token = nextToken();
        } else {
            errorHandler.flag(token, errorCode, this);
        }
    }
}
