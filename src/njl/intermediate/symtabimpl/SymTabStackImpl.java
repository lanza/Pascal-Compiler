package njl.intermediate.symtabimpl;

import java.util.ArrayList;

import njl.intermediate.SymTab;
import njl.intermediate.SymTabEntry;
import njl.intermediate.SymTabFactory;
import njl.intermediate.SymTabStack;

public class SymTabStackImpl extends ArrayList<SymTab> implements SymTabStack {
    private int currentNestingLevel;

    public int getCurrentNestingLevel() {
        return currentNestingLevel;
    }

    public SymTabStackImpl() {
        this.currentNestingLevel = 0;
        add(SymTabFactory.createSymTab(currentNestingLevel));
    }

    public SymTab getLocalSymTab() {
        return get(currentNestingLevel);
    }

    public SymTabEntry enterLocal(String name) {
        return get(currentNestingLevel).enter(name);
    }

    public SymTabEntry lookupLocal(String name) {
        return get(currentNestingLevel).lookup(name);
    }

    public SymTabEntry lookup(String name) {
        return lookupLocal(name);
    }
}
