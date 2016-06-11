package njl.frontend.pascal.parsers;

import njl.frontend.*;
import njl.frontend.pascal.*;
import njl.intermediate.*;

import static njl.frontend.pascal.PascalTokenType.*;
import static njl.frontend.pascal.PascalErrorCode.*;
import static njl.intermediate.icodeimpl.ICodeNodeTypeImpl.*;

public class CompoundStatementParser extends StatementParser {
    public CompoundStatementParser(PascalParserTD parent) {
        super(parent);
    }

    public ICodeNode parse(Token token) throws Exception {
        token = nextToken();
        ICodeNode compoundNode = ICodeFactory.createICodeNode(COMPOUND);

        StatementParser statementParser = new StatementParser(this);
        statementParser.parseList(token, compoundNode, END, MISSING_END);

        return compoundNode;
    }
}
