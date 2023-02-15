package com.company.kencinas.bank.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.company.kencinas.bank.exception.ShortPositionAlertException;
import com.company.kencinas.bank.model.Instrument;
import com.company.kencinas.bank.model.Portfolio;
import com.company.kencinas.bank.model.Price;
import com.company.kencinas.bank.model.Transaction;
import com.company.kencinas.bank.repository.TransactionRepository;
import com.company.kencinas.bank.repository.impl.TransactionRepositoryOperations;
import com.company.kencinas.bank.service.PortfolioService;
import com.company.kencinas.bank.service.PriceService;
import com.company.kencinas.bank.util.cash.Currency;
import com.company.kencinas.bank.util.instrument.InstrumentFactory;
import com.company.kencinas.bank.util.instrument.InvestType;
import com.company.kencinas.bank.util.transaction.Action;
import com.company.kencinas.bank.util.transaction.TransactionDate;

public class PortfolioServiceOperations implements PortfolioService {
    private final PriceService priceService = new PriceServiceOperations();

    private final TransactionRepository transactionRepository = new TransactionRepositoryOperations();

    @Override
    public Portfolio getCurrentPortfolio() {
        Portfolio portfolio = new Portfolio();

        Map<InvestType, List<Transaction>> transactionsByInstrumentType = transactionRepository.getTransactionHistory()
                .stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getInstrument().getType()));

        portfolio.getInstruments().addAll(
                transactionsByInstrumentType.entrySet().stream()
                        .map(entry -> getInvestFromTransactions(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList()));

        return portfolio;
    }

    @Override
    public Price getCurrentPortfolioValueByCurrency(final Currency currency) {
        Double value = getCurrentPortfolio().getInstruments().stream()
                .map(instrument -> instrument.getAmount()
                        * priceService.getPriceForInstrumentByCurrency(currency, instrument.getType()).getAmount())
                .reduce(0.0, (val, amount) -> val + amount);
        return new Price(currency, value);
    }

    @Override
    public void sellInstrument(final InvestType investType, final Double amount,
            final TransactionDate transactionDate) {
        if (!validateNotShortPositionOnSell(investType, amount)) {
            throw new ShortPositionAlertException();
        }

        Transaction transaction = new Transaction();
        transaction.setPrice(priceService.getPriceForInstrumentByCurrency(Currency.US_DOLLAR, investType));
        transaction.setInstrument(InstrumentFactory.getInstrument(investType, amount));
        transaction.setAction(Action.SELL);
        transaction.setDate(transactionDate);

        transactionRepository.insertTransaction(transaction);
    }

    @Override
    public void buyInstrument(final InvestType investType, final Double amount, final TransactionDate transactionDate) {
        Transaction transaction = new Transaction();

        transaction.setPrice(priceService.getPriceForInstrumentByCurrency(Currency.US_DOLLAR, investType));
        transaction.setInstrument(InstrumentFactory.getInstrument(investType, amount));
        transaction.setAction(Action.BUY);
        transaction.setDate(transactionDate);

        transactionRepository.insertTransaction(transaction);
    }

    @Override
    public Double getProfitAndLossFromInvest(final InvestType investType) {
        Price currentPrice = priceService.getPriceForInstrumentByCurrency(Currency.US_DOLLAR, investType);
        return transactionRepository.getTransactionHistory()
                .stream()
                .filter(transaction -> investType.equals(transaction.getInstrument().getType()))
                .map(transaction -> transaction.getInstrument().getAmount()
                        * (currentPrice.getAmount() - transaction.getPrice().getAmount()))
                .reduce(0.0, (subtotal, next) -> subtotal + next);
    }

    private boolean validateNotShortPositionOnSell(final InvestType type, final Double amount) {
        Portfolio portfolio = getCurrentPortfolio();

        Double amountAvailable = portfolio.getInstruments().stream()
                .filter(instrument -> type.equals(instrument.getType()))
                .map(Instrument::getAmount)
                .findFirst()
                .orElse(0.0);

        return amountAvailable > amount;
    }

    private Instrument getInvestFromTransactions(final InvestType type, final List<Transaction> transactions) {
        Double amount = transactions.stream()
                .map(transaction -> transaction.getInstrument().getAmount()
                        * (Action.BUY.equals(transaction.getAction()) ? 1 : -1))
                .reduce(0.0, (subtotal, next) -> subtotal + next);

        return InstrumentFactory.getInstrument(type, amount);
    }
}
