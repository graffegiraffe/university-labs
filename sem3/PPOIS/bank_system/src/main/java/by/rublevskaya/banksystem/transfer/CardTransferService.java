package by.rublevskaya.banksystem.transfer;

import by.rublevskaya.banksystem.model.card.BaseCard;
import by.rublevskaya.banksystem.model.document.Check;

public interface CardTransferService {
    Check transferFromCardToCard(BaseCard fromCard, BaseCard toCard, double amount);
    Check transferFromCardToAccount(BaseCard fromCard, String toAccount, double amount);
}
