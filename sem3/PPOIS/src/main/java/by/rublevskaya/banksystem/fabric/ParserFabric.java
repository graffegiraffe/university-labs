package by.rublevskaya.banksystem.fabric;

import by.rublevskaya.banksystem.documentparser.IParser;
import by.rublevskaya.banksystem.documentparser.impl.DocxDocumentParser;
import by.rublevskaya.banksystem.documentparser.impl.PdfDocumentParser;
import by.rublevskaya.banksystem.documentparser.impl.TxtDocumentParser;

public class ParserFabric {
    public static IParser create(String type) {
        switch (type.toLowerCase()) {
            case "pdf":
                return new PdfDocumentParser();
            case "docx":
                return new DocxDocumentParser();
            case "txt":
                return new TxtDocumentParser();
            default:
                throw new IllegalArgumentException("Unknown document type: " + type);
        }
    }
}