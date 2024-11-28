package by.rublevskaya.banksystem.documentparser.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class PdfDocumentParserTest {

    @Test
    public void parseFile() {
        PdfDocumentParser parser = new PdfDocumentParser();
        String testFileName = "test.pdf";
        parser.parseFile(testFileName);
        assertNotNull(parser);
    }
}