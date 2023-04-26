package pl.ing.tesla.transaction;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.tesla.transaction.dto.AccountReport;
import pl.ing.tesla.transaction.dto.Transaction;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class TransactionController {

    @PostMapping("/transactions/report")
    public Collection<AccountReport> report(@RequestBody List<Transaction> transactions) {
        Map<String, AccountReport> accountToReport = new TreeMap<>();
        transactions.forEach(t -> {
            accountToReport.computeIfAbsent(t.creditAccount(), AccountReport::new).credit(t.amount());
            accountToReport.computeIfAbsent(t.debitAccount(), AccountReport::new).debit(t.amount());
        });
        return accountToReport.values();
    }
}
