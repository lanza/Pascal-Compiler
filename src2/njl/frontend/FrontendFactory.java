package njl.frontend;

import njl.frontend.pascal.PascalParserTD;
import njl.frontend.pascal.PascalScanner;

public class FrontendFactory {

    public static Parser createParser(String language, String type, Source source) throws Exception {
        if (language.equalsIgnoreCase("njl.Pascal") && type.equalsIgnoreCase("top-down")) {
            Scanner scanner = new PascalScanner(source);
            return new PascalParserTD(scanner);
        } else if (!language.equalsIgnoreCase("njl.Pascal")) {
            throw new Exception("Parser factory: Invalid language '" + language + "'");
        } else {
            throw new Exception("Parser factory: Invalid type '" + type + "'");
        }
    }
}
