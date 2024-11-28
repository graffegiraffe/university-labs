package by.rublevskaya.banksystem.transfer.impl;

import by.rublevskaya.banksystem.model.card.MasterCard;
import by.rublevskaya.banksystem.model.document.Check;
import by.rublevskaya.banksystem.transfer.CardTransferService;
import by.rublevskaya.banksystem.model.card.BaseCard;

public class MasterCardTransferService implements CardTransferService {

    @Override
    public Check transferFromCardToCard(BaseCard sourceCard, BaseCard destinationCard, double amount) {
        if (!(sourceCard instanceof MasterCard) || !(destinationCard instanceof MasterCard)) {
            throw new IllegalArgumentException("Both cards must be MasterCard for this transfer");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        return new Check("123", "Transfer from card " + sourceCard.getCardNumber() + " to card " + destinationCard.getCardNumber() + " amount: " + amount);
    }

    @Override
    public Check transferFromCardToAccount(BaseCard sourceCard, String destinationAccountNumber, double amount) {
        if (!(sourceCard instanceof MasterCard)) {
            throw new IllegalArgumentException("Source card must be MasterCard for this transfer");
        }
        if (destinationAccountNumber == null || destinationAccountNumber.isEmpty()) {
            throw new IllegalArgumentException("Destination account number cannot be null or empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        return new Check("456", "Transfer from card " + sourceCard.getCardNumber() + " to account " + destinationAccountNumber + " amount: " + amount);
    }
}