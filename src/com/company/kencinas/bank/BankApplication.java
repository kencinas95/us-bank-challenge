package com.company.kencinas.bank;

import com.company.kencinas.test.BaseTest;
import com.company.kencinas.test.PortfolioServiceTest;

public class BankApplication {
    private BaseTest portfolioServiceTest = new PortfolioServiceTest();

    private void runTests() {
        portfolioServiceTest.run();
    }

    public static void main(String[] args) {
        BankApplication bankApplication = new BankApplication();
        bankApplication.runTests();
    }
}
