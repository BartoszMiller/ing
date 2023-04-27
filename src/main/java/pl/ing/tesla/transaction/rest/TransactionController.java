package pl.ing.tesla.transaction.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.tesla.transaction.model.AccountReport;
import pl.ing.tesla.transaction.model.Transaction;
import pl.ing.tesla.transaction.service.TransactionService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/report")
    public Collection<AccountReport> generateReport(@RequestBody List<Transaction> transactions) {
        return transactionService.generateReport(transactions);
    }
}
