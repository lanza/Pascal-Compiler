package njl.backend.interpreter;

import njl.intermediate.*;
import njl.intermediate.icodeimpl.*;
import njl.backend.*;
import njl.backend.interpreter.executors.*;
import njl.message.*;

import javax.sql.StatementEvent;

import static njl.intermediate.icodeimpl.ICodeNodeTypeImpl.*;
import static njl.message.MessageType.INTERPRETER_SUMMARY;

public class Executor extends Backend {

    protected static int executionCount;
    protected static RuntimeErrorHandler errorHandler;

    static {
        executionCount = 0;
        errorHandler = new RuntimeErrorHandler();
    }

    public Executor() {}

    public Executor(Executor parent) {
        super();
    }

    public RuntimeErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void process(ICode iCode, SymTabStack symTabStack) throws  Exception {
        Executor.symTabStack = symTabStack;
        this.iCode = iCode;

        long startTime = System.currentTimeMillis();

        ICodeNode rootNode = iCode.getRoot();
        StatementExecutor statementExecutor = new StatementExecutor(this);
        statementExecutor.execute(rootNode);

        float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
        int runtimeErrors = errorHandler.getErrorCount();

        sendMessage(new Message(INTERPRETER_SUMMARY, new Number[] {executionCount, runtimeErrors, elapsedTime}));
    }
}
