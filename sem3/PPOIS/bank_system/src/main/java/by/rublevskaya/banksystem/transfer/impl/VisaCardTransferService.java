package by.rublevskaya.banksystem.transfer.impl;

import by.rublevskaya.banksystem.model.card.VisaCard;
import by.rublevskaya.banksystem.model.document.Check;
import by.rublevskaya.banksystem.transfer.CardTransferService;
import by.rublevskaya.banksystem.model.card.BaseCard;

public class VisaCardTransferService implements CardTransferService {

    private static final double CASHBACK_PERCENTAGE = 0.01;

    @Override
    public Check transferFromCardToCard(BaseCard sourceCard, BaseCard destinationCard, double amount) {
        if (!(sourceCard instanceof VisaCard) || !(destinationCard instanceof VisaCard)) {
            throw new IllegalArgumentException("Both cards must be VisaCard for this transfer");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        if (sourceCard.getAmount() < amount) {
            throw new IllegalArgumentException("Insufficient funds on the source card");
        }

        sourceCard.setAmount(sourceCard.getAmount() - amount);
        double cashback = amount * CASHBACK_PERCENTAGE;
        sourceCard.setAmount(sourceCard.getAmount() + cashback);
        destinationCard.setAmount(destinationCard.getAmount() + amount);

        return new Check("789", "Transfer from card " + sourceCard.getCardNumber() +
                " to card " + destinationCard.getCardNumber() + " amount: " + amount + ", cashback: " + cashback);
    }

    @Override
    public Check transferFromCardToAccount(BaseCard sourceCard, String destinationAccountNumber, double amount) {
        if (!(sourceCard instanceof VisaCard)) {
            throw new IllegalArgumentException("Source card must be VisaCard for this transfer");
        }
        if (destinationAccountNumber == null || destinationAccountNumber.isEmpty()) {
            throw new IllegalArgumentException("Destination account number cannot be null or empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        if (sourceCard.getAmount() < amount) {
            throw new IllegalArgumentException("Insufficient funds on the source card");
        }

        sourceCard.setAmount(sourceCard.getAmount() - amount);
        double cashback = amount * CASHBACK_PERCENTAGE;
        sourceCard.setAmount(sourceCard.getAmount() + cashback);
        return new Check("012", "Transfer from card " + sourceCard.getCardNumber() +
                " to account " + destinationAccountNumber + " amount: " + amount + ", cashback: " + cashback);
    }
}