package io.lanza.backend;

import io.lanza.frontend.ICode;
import io.lanza.frontend.SymTab;

import io.lanza.message.MessageType;
import io.lanza.message.Message;

public class CodeGenerator extends Backend {

    public void process(ICode iCode, SymTab symTab) throws Exception {
        long startTime = System.currentTimeMillis();
        float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
        int instructionCount = 0;

        sendMessage(new Message(MessageType.COMPILER_SUMMARY, new Number[] {instructionCount, elapsedTime}));
    }
}
