package com.company.kencinas.bank.service;

import com.company.kencinas.bank.model.Portfolio;
import com.company.kencinas.bank.model.Price;
import com.company.kencinas.bank.util.cash.Currency;
import com.company.kencinas.bank.util.instrument.InvestType;
import com.company.kencinas.bank.util.transaction.TransactionDate;

public interface PortfolioService {
    Portfolio getCurrentPortfolio();

    Price getCurrentPortfolioValueByCurrency(Currency currency);

    void sellInstrument(InvestType investType, Double amount, TransactionDate transactionDate);

    void buyInstrument(InvestType investType, Double amount, TransactionDate transactionDate);

    Double getProfitAndLossFromInvest(InvestType investType);

}
