package njl.intermediate;

import njl.intermediate.symtabimpl.SymTabEntryImpl;
import njl.intermediate.symtabimpl.SymTabImpl;
import njl.intermediate.symtabimpl.SymTabStackImpl;

public class SymTabFactory {

    public static SymTabStack createSymTabStack() {
        return new SymTabStackImpl();
    }

    public static SymTab createSymTab(int nestingLevel) {
        return new SymTabImpl(nestingLevel);
    }

    public static SymTabEntry createSymTabEntry(String name, SymTab symTab) {
        return new SymTabEntryImpl(name, symTab);
    }
}
