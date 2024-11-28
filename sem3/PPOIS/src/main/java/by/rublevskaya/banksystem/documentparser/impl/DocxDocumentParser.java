package by.rublevskaya.banksystem.documentparser.impl;

import by.rublevskaya.banksystem.documentparser.IParser;

public class DocxDocumentParser implements IParser {

    @Override
    public void parseFile(String fileName) {
        System.out.println("get payment details from .docx document");
    }
}
