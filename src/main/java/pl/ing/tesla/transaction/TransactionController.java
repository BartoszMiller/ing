package pl.ing.tesla.transaction;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.tesla.transaction.model.AccountReport;
import pl.ing.tesla.transaction.model.Transaction;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @PostMapping("/report")
    public Collection<AccountReport> generateReport(@RequestBody List<Transaction> transactions) {
        final Map<String, AccountReport> accountToReport = new TreeMap<>();
        transactions.forEach(t -> {
            accountToReport.computeIfAbsent(t.creditAccount(), AccountReport::new).credit(t.amount());
            accountToReport.computeIfAbsent(t.debitAccount(), AccountReport::new).debit(t.amount());
        });
        return accountToReport.values();
    }
}
