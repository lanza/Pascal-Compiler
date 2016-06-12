package njl.backend.interpreter.executors;

import java.util.ArrayList;

import njl.intermediate.*;
import njl.backend.interpreter.*;

public class CompoundExecutor extends StatementExecutor {
    public CompoundExecutor(Executor parent) {
        super(parent);
    }

    public Object execute(ICodeNode node) {
        StatementExecutor statementExecutor = new StatementExecutor(this);
        ArrayList<ICodeNode> children = node.getChildren();
        for (ICodeNode child : children) {
            statementExecutor.execute(child);
        }
        return null;
    }
}

