package njl.backend.interpreter;

import njl.backend.Backend;
import njl.intermediate.SymTabStack;
import njl.intermediate.ICode;

import njl.message.Message;
import njl.message.MessageType;

public class Executor extends Backend {
    public void process(ICode icode, SymTabStack symTabStack) throws  Exception {
        long startTime = System.currentTimeMillis();
        float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
        int executionCount = 0;
        int runtimeErrors = 0;

        sendMessage(new Message(MessageType.INTERPRETER_SUMMARY, new Number[] {executionCount, runtimeErrors, elapsedTime}));
    }
}
