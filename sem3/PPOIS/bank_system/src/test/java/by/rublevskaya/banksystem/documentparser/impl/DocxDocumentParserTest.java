package by.rublevskaya.banksystem.documentparser.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class DocxDocumentParserTest {

    @Test
    public void parseFile() {
        DocxDocumentParser parser = new DocxDocumentParser();
        String testFileName = "test.docx";
        parser.parseFile(testFileName);
        assertNotNull(parser);
    }
}