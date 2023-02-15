package com.jpmorgan.kencinas.test;

import java.util.Objects;

import com.jpmorgan.kencinas.bank.exception.ShortPositionAlertException;
import com.jpmorgan.kencinas.bank.exception.SystemException;
import com.jpmorgan.kencinas.bank.model.Instrument;
import com.jpmorgan.kencinas.bank.model.Portfolio;
import com.jpmorgan.kencinas.bank.model.Price;
import com.jpmorgan.kencinas.bank.model.instrument.Bond;
import com.jpmorgan.kencinas.bank.model.instrument.Stock;
import com.jpmorgan.kencinas.bank.model.instrument.cash.Euro;
import com.jpmorgan.kencinas.bank.model.instrument.cash.USDollar;
import com.jpmorgan.kencinas.bank.repository.TransactionRepository;
import com.jpmorgan.kencinas.bank.repository.impl.TransactionRepositoryOperations;
import com.jpmorgan.kencinas.bank.service.PortfolioService;
import com.jpmorgan.kencinas.bank.service.impl.PortfolioServiceOperations;
import com.jpmorgan.kencinas.bank.util.cash.Currency;
import com.jpmorgan.kencinas.bank.util.cash.CurrencyConverter;
import com.jpmorgan.kencinas.bank.util.instrument.InvestType;
import com.jpmorgan.kencinas.bank.util.transaction.TransactionDate;

import static com.jpmorgan.kencinas.bank.util.instrument.PricingExternalService.EUR_BOND_PRICE;
import static com.jpmorgan.kencinas.bank.util.instrument.PricingExternalService.EUR_STOCK_PRICE;


public class PortfolioServiceTest extends BaseTest {
    private PortfolioService portfolioService;
    private TransactionRepository transactionRepository;

    public PortfolioServiceTest() {
        portfolioService = new PortfolioServiceOperations();
        transactionRepository = new TransactionRepositoryOperations();
    }

    @Override
    public void run() {
        testGetPortfolioForFirstTimeSuccess();
        testSellStockWithOutInstrumentsInThePortfolioFailure();
        testBuyBondInstrumentsSuccess();
        testBuyEurosSuccess();
        testBuyAndSellUSDollarsSuccess();
        testGetPortfolioValueZeroForCurrencyUSDSuccess();
        testGetPortfolioValueForCurrencyEURSuccess();
        testGetProfitAndLossFromInvests();
    }

    private void testGetPortfolioForFirstTimeSuccess() {
        Portfolio portfolio = portfolioService.getCurrentPortfolio();
        assert Objects.nonNull(portfolio);
        assert portfolio.getInstruments().isEmpty();

        transactionRepository.clearTransactionHistory();

    }

    private void testSellStockWithOutInstrumentsInThePortfolioFailure() {
        try {
            portfolioService.sellInstrument(InvestType.STOCK, 100.0, TransactionDate.today());
        } catch (SystemException se) {
            assert se instanceof ShortPositionAlertException;
        }

        transactionRepository.clearTransactionHistory();

    }

    private void testBuyBondInstrumentsSuccess() {
        portfolioService.buyInstrument(InvestType.BOND, 100.0, TransactionDate.today());

        Portfolio portfolio = portfolioService.getCurrentPortfolio();

        assert Objects.nonNull(portfolio);
        assert !portfolio.getInstruments().isEmpty();
        assert portfolio.getInstruments().stream().anyMatch(instrument -> InvestType.BOND.equals(instrument.getType()));

        transactionRepository.clearTransactionHistory();

    }

    private void testBuyEurosSuccess() {
        portfolioService.buyInstrument(InvestType.CASH_EUR, 100.0, TransactionDate.today());

        Portfolio portfolio = portfolioService.getCurrentPortfolio();

        assert Objects.nonNull(portfolio);
        assert !portfolio.getInstruments().isEmpty();
        assert portfolio.getInstruments().stream().anyMatch(instrument -> InvestType.CASH_EUR.equals(instrument.getType()));

        transactionRepository.clearTransactionHistory();

    }

    private void testBuyAndSellUSDollarsSuccess() {
        Double expected = 8000.0;

        portfolioService.buyInstrument(InvestType.CASH_USD, 10000.0, TransactionDate.today());
        portfolioService.sellInstrument(InvestType.CASH_USD, 2000.0, TransactionDate.today());

        Portfolio portfolio = portfolioService.getCurrentPortfolio();

        assert Objects.nonNull(portfolio);
        assert !portfolio.getInstruments().isEmpty();
        assert expected.equals(portfolio.getInstruments().stream()
            .filter(instrument -> InvestType.CASH_USD.equals(instrument.getType()))
            .findFirst()
            .map(Instrument::getAmount)
            .orElse(0.0));

        transactionRepository.clearTransactionHistory();

    }

    private void testGetPortfolioValueZeroForCurrencyUSDSuccess() {
        Double expected = 0.0;
        Price value = portfolioService.getCurrentPortfolioValueByCurrency(Currency.US_DOLLAR);

        assert Currency.US_DOLLAR.equals(value.getCurrency());
        assert expected.equals(value.getAmount());
    }

    private void testGetPortfolioValueForCurrencyEURSuccess() {
        Bond bond = new Bond(100.0);
        Stock stock = new Stock(300.0);
        USDollar dollar = new USDollar(10000.0);
        Euro euro = new Euro(30000.0);

        Double expected = (bond.getAmount() * EUR_BOND_PRICE)
            + (stock.getAmount() * EUR_STOCK_PRICE)
            + CurrencyConverter.convert(Currency.US_DOLLAR, Currency.EURO, dollar.getAmount())
            + euro.getAmount();

        portfolioService.buyInstrument(InvestType.BOND, 100.0, TransactionDate.today());
        portfolioService.buyInstrument(InvestType.STOCK,  300.0, TransactionDate.today());
        portfolioService.buyInstrument(InvestType.CASH_USD, 10000.0, TransactionDate.today());
        portfolioService.buyInstrument(InvestType.CASH_EUR, 30000.0, TransactionDate.today());

        Price price = portfolioService.getCurrentPortfolioValueByCurrency(Currency.EURO);

        assert Currency.EURO.equals(price.getCurrency());
        assert expected.equals(price.getAmount());

        transactionRepository.clearTransactionHistory();
    }

    private void testGetProfitAndLossFromInvests() {
        Double pnl = portfolioService.getProfitAndLossFromInvest(InvestType.BOND);
        assert pnl == 0.0;
    }

}
