package by.rublevskaya.banksystem.documentparser.impl;

import by.rublevskaya.banksystem.documentparser.IParser;

public class TxtDocumentParser implements IParser {

    @Override
    public void parseFile(String fileName) {
        System.out.println("get payment details from .txt document");
    }
}