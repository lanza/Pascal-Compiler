package njl.intermediate.icodeimpl;

import njl.intermediate.ICode;
import njl.intermediate.ICodeNode;

public class ICodeImpl implements  ICode {
    private ICodeNode root;
    public ICodeNode setRoot(ICodeNode node) {
        root = node;
        return root;
    }

    public ICodeNode getRoot() {
        return root;
    }
}
