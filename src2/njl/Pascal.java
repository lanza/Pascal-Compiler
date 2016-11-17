package njl;

import java.io.BufferedReader;
import java.io.FileReader;

import njl.backend.*;
import njl.frontend.*;
import njl.intermediate.*;
import njl.message.*;
import njl.util.*;

import static njl.frontend.pascal.PascalTokenType.STRING;

public class Pascal {
    private Parser parser;
    private Source source;
    private ICode iCode;
    private SymTabStack symTabStack;
    private Backend backend;

    public Pascal(String operation, String filePath, String flags) {
        try {
            boolean intermediate = flags.indexOf('i') > -1;
            boolean xref         = flags.indexOf('x') > -1;

            source = new Source(new BufferedReader(new FileReader(filePath)));
            source.addMessageListener(new SourceMessageListener());

            parser = FrontendFactory.createParser("njl.Pascal", "top-down", source);
            parser.addMessageListener(new ParserMessageListener());

            backend = BackendFactory.createBackend(operation);
            backend.addMessageListener(new BackendMessageListener());

            parser.parse();
            source.close();

            iCode = parser.iCode;
            symTabStack = parser.symTabStack;

            if (xref) {
                CrossReferencer crossReferencer = new CrossReferencer();
                crossReferencer.print(symTabStack);
            }

            backend.process(iCode, symTabStack);
        } catch (Exception ex) {
            System.out.println("***** Internal translator error. *****");
            ex.printStackTrace();
        }
    }

    private static final String FLAGS = "[-ix]";
    private static final String USAGE = "Usage: njl.Pascal execute|compile " + FLAGS + " <source file path>";

    public static void main(String args[]) {
        try {
            String operation = args[0];

            if (!(operation.equalsIgnoreCase("compile") || operation.equalsIgnoreCase("execute"))) {
                throw new Exception();
            }

            int i = 0;
            String flags = "";

            while ((++i < args.length) && (args[i].charAt(0) == '-')) {
                flags += args[i].substring(1);
            }

            if (i < args.length) {
                String path = args[i];
                new Pascal(operation, path, flags);
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println(USAGE);
        }
    }

    private static final String SOURCE_LINE_FORMAT = "%03d %s";

    private class SourceMessageListener implements MessageListener {
        public void messageReceived(Message message) {
            MessageType type = message.type;
            Object body[] = (Object []) message.body;

            switch (type) {
                case SOURCE_LINE: {
                    int lineNumber = (Integer) body[0];
                    String lineText = (String) body[1];

                    System.out.println(String.format(SOURCE_LINE_FORMAT, lineNumber, lineText));
                    break;
                }
            }
        }
    }

    private static final String PARSER_SUMMARY_FORMAT =
            "\n%,20d source lines." +
                    "\n%,20d sytax errors." +
                    "\n%,20.2f seconds total parsing time.\n";

    private static final String TOKEN_FORMAT =
            ">>> %-15s line=%03d, pos=%2d, text=\"%s\"";
    private static final String VALUE_FORMAT =
            ">>>                 value=%s";

    private static final int PREFIX_WIDTH = 5;

    private class ParserMessageListener implements MessageListener {
        public void messageReceived(Message message) {
            MessageType type = message.type;
            switch (type) {
                case TOKEN: {
                    Object body[] = (Object []) message.body;
                    int line = (Integer) body[0];
                    int position = (Integer) body[1];
                    TokenType tokenType = (TokenType) body[2];
                    String tokenText = (String) body[3];
                    Object tokenValue = body[4];

                    System.out.println(String.format(TOKEN_FORMAT, tokenType, line, position, tokenText));

                    if (tokenValue != null) {
                        if (tokenType == STRING) {
                            tokenValue = "\"" + tokenValue + "\"";
                        }

                        System.out.println(String.format(VALUE_FORMAT, tokenValue));
                    }
                    break;
                }
                case SYNTAX_ERROR: {
                    Object body[] = (Object []) message.body;
                    int lineNumber = (Integer) body[0];
                    int position = (Integer) body[1];
                    String tokenText = (String) body[2];
                    String errorMessage = (String) body[3];

                    int spaceCount = PREFIX_WIDTH + position;
                    StringBuilder flagBuffer = new StringBuilder();

                    for (int i = 1; i < spaceCount; ++i) {
                        flagBuffer.append(' ');
                    }

                    flagBuffer.append("^\n*** ").append(errorMessage);

                    if (tokenText != null) {
                        flagBuffer.append(" [at \"").append(tokenText).append("\"]");
                    }
                    System.out.println(flagBuffer.toString());
                    break;
                }
                case PARSER_SUMMARY: {
                    Number body[] = (Number[]) message.body;
                    int statementCount = (Integer) body[0];

                    int syntaxErrors = (Integer) body[1];
                    float elapsedTime = (Float) body[2];

                    System.out.printf(PARSER_SUMMARY_FORMAT, statementCount, syntaxErrors, elapsedTime);
                    break;
                }
            }
        }
    }

    private static final String INTERPRETER_SUMMARY_FORMAT =
            "\n%,20d statements executed." +
                    "\n%,20d runtime errors." +
                    "\n%,20.2f seconds total execution time.\n";

    private static final String COMPILER_SUMMARY_FORMAT =
            "\n%,20d instructions generated." +
                    "\n%,20.2f seconds total code generation time.\n";

    private class BackendMessageListener implements MessageListener {
        public void messageReceived(Message message) {
            MessageType type = message.type;

            switch (type) {
                case INTERPRETER_SUMMARY: {
                    Number body[] = (Number[]) message.body;
                    int executionCount = (Integer) body[0];
                    int runtimeErrors = (Integer) body[1];
                    float elapsedTime = (Float) body[2];

                    System.out.printf(INTERPRETER_SUMMARY_FORMAT, executionCount, runtimeErrors, elapsedTime);
                    break;
                }

                case COMPILER_SUMMARY: {
                    Number body[] = (Number[]) message.body;
                    int instructionCount = (Integer) body[0];
                    float elapsedTime = (Float) body[1];

                    System.out.printf(COMPILER_SUMMARY_FORMAT, instructionCount, elapsedTime);
                    break;
                }
            }
        }
    }
}
