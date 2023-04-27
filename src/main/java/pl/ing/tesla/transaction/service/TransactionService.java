package pl.ing.tesla.transaction.service;

import org.springframework.stereotype.Service;
import pl.ing.tesla.transaction.model.AccountReport;
import pl.ing.tesla.transaction.model.Transaction;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class TransactionService {

    public Collection<AccountReport> generateReport(List<Transaction> transactions) {
        final Map<String, AccountReport> accountToReport = new TreeMap<>();
        transactions.forEach(t -> {
            accountToReport.computeIfAbsent(t.creditAccount(), AccountReport::new).credit(t.amount());
            accountToReport.computeIfAbsent(t.debitAccount(), AccountReport::new).debit(t.amount());
        });
        return accountToReport.values();
    }
}
