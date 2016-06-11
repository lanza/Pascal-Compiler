package njl.backend.compiler;

import njl.backend.Backend;
import njl.intermediate.ICode;
import njl.intermediate.SymTabStack;

import njl.message.MessageType;
import njl.message.Message;

public class CodeGenerator extends Backend {

    public void process(ICode iCode, SymTabStack symTabStack) throws Exception {
        long startTime = System.currentTimeMillis();
        float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
        int instructionCount = 0;

        sendMessage(new Message(MessageType.COMPILER_SUMMARY, new Number[] {instructionCount, elapsedTime}));
    }
}
