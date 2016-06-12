package njl.backend.interpreter.executors;

import java.util.ArrayList;

import njl.intermediate.*;
import njl.intermediate.icodeimpl.*;
import njl.backend.interpreter.*;
import njl.backend.interpreter.executors.*;
import njl.backend.interpreter.executors.ExpressionExecutor;
import njl.message.*;

import static njl.intermediate.symtabimpl.SymTabKeyImpl.*;
import static njl.intermediate.icodeimpl.ICodeKeyImpl.*;
import static njl.message.MessageType.ASSIGN;

public class AssignmentExecutor extends StatementExecutor {
    public AssignmentExecutor(Executor parent) {
        super(parent);
    }
    public Object execute(ICodeNode node) {
        ArrayList<ICodeNode> children = node.getChildren();
        ICodeNode variableNode = children.get(0);
        ICodeNode expressionNode = children.get(1);

        ExpressionExecutor expressionExecutor = new ExpressionExecutor(this);
        Object value = expressionExecutor.execute(expressionNode);

        SymTabEntry variableId = (SymTabEntry) variableNode.getAttribute(ID);
        variableId.setAttribute(DATA_VALUE, value);

        sendMessage(node, variableId.getName(), value);

        ++executionCount;
        return null;
    }

    private void sendMessage(ICodeNode node, String variableName, Object value) {
        Object lineNumber = node.getAttribute(LINE);

        if (lineNumber != null) {
            sendMessage(new Message(ASSIGN, new Object[] {lineNumber, variableName, value}));
        }
    }
}
