package by.rublevskaya.banksystem.documentparser.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class TxtDocumentParserTest {

    @Test
    public void parseFile() {
        TxtDocumentParser parser = new TxtDocumentParser();
        String testFileName = "test.txt";
        parser.parseFile(testFileName);
        assertNotNull(parser);
    }
}