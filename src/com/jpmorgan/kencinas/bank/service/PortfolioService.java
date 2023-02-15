package com.jpmorgan.kencinas.bank.service;

import com.jpmorgan.kencinas.bank.model.Portfolio;
import com.jpmorgan.kencinas.bank.model.Price;
import com.jpmorgan.kencinas.bank.util.cash.Currency;
import com.jpmorgan.kencinas.bank.util.instrument.InvestType;
import com.jpmorgan.kencinas.bank.util.transaction.TransactionDate;

public interface PortfolioService {
    Portfolio getCurrentPortfolio();

    Price getCurrentPortfolioValueByCurrency(Currency currency);

    void sellInstrument(InvestType investType, Double amount, TransactionDate transactionDate);

    void buyInstrument(InvestType investType, Double amount, TransactionDate transactionDate);

    Double getProfitAndLossFromInvest(InvestType investType);

}
