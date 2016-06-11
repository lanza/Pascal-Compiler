package njl.intermediate;

import njl.intermediate.icodeimpl.ICodeImpl;
import njl.intermediate.icodeimpl.ICodeNodeImpl;


public class ICodeFactory {
    public static ICode createICode() {
        return new ICodeImpl();
    }
    public static ICodeNode createICodeNode(ICodeNodeType type) {
        return new ICodeNodeImpl(type);
    }
}
