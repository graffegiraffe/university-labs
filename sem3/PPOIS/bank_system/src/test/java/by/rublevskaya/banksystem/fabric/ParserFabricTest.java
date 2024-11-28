package by.rublevskaya.banksystem.fabric;

import by.rublevskaya.banksystem.documentparser.IParser;
import by.rublevskaya.banksystem.documentparser.impl.DocxDocumentParser;
import by.rublevskaya.banksystem.documentparser.impl.PdfDocumentParser;
import by.rublevskaya.banksystem.documentparser.impl.TxtDocumentParser;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParserFabricTest {

    @Test
    public void create() {
        IParser pdfParser = ParserFabric.create("pdf");
        assertNotNull(pdfParser);
        assertTrue(pdfParser instanceof PdfDocumentParser);

        IParser docxParser = ParserFabric.create("docx");
        assertNotNull(docxParser);
        assertTrue(docxParser instanceof DocxDocumentParser);

        IParser txtParser = ParserFabric.create("txt");
        assertNotNull(txtParser);
        assertTrue(txtParser instanceof TxtDocumentParser);
        try {
            ParserFabric.create("unknown");
            fail("Expected IllegalArgumentException for unknown document type");
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown document type: unknown", e.getMessage());
        }
    }
}