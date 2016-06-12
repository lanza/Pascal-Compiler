package njl.backend.interpreter.executors;

import njl.intermediate.*;
import njl.intermediate.*;
import njl.backend.interpreter.*;
import njl.intermediate.icodeimpl.ICodeNodeTypeImpl;
import njl.message.*;

import static njl.intermediate.ICodeNodeType.*;
import static njl.intermediate.icodeimpl.ICodeKeyImpl.*;
import static njl.backend.interpreter.RuntimeErrorCode.*;
import static njl.message.MessageType.SOURCE_LINE;

public class StatementExecutor extends Executor {
    public StatementExecutor(Executor parent) {
        super(parent);
    }

    public Object execute(ICodeNode node) {
        ICodeNodeTypeImpl nodeType = (ICodeNodeTypeImpl) node.getType();

        sendSourceLineMessage(node);

        switch (nodeType) {
            case COMPOUND: {
                CompoundExecutor compoundExecutor = new CompoundExecutor(this);
                return compoundExecutor.execute(node);
            }
            case ASSIGN: {
                AssignmentExecutor assignmentExecutor = new AssignmentExecutor(this);
                return assignmentExecutor.execute(node);
            }
            case NO_OP: return null;
            default: {
                errorHandler.flag(node, UNIMPLEMENTED_FEATURE, this);
                return null;
            }
        }
    }
    private void sendSourceLineMessage(ICodeNode node) {
        Object lineNumber = node.getAttribute(LINE);

        if (lineNumber != null) {
            sendMessage(new Message(SOURCE_LINE, lineNumber));
        }
    }
}
