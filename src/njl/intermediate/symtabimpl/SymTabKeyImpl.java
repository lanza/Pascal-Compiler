package njl.intermediate.symtabimpl;

import njl.intermediate.SymTabKey;

public enum SymTabKeyImpl implements SymTabKey {
    CONSTANT_VALUE,

    ROUTINE_CODE, ROUTINE_SYMTAB, ROUTINE_ICODE,
    ROUTINE_PARMS, ROUTINE_ROUTINES,

    DATA_VALUE
}
