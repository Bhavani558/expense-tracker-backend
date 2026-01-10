package com.example.expense_tracker;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/expenses")
public class AnalyticsController {
    private final ExpenseRepository expenseRepository;

    public AnalyticsController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/summary")
    public Map<String, Double> getExpenseSummary() {
        Map<String, Double> summary = new HashMap<>();
        // Total spent overall
        Double total = expenseRepository.getTotalExpense();
        Double today = expenseRepository.getTodayExpense(LocalDate.now());
        // Total spent this month
        LocalDate now = LocalDate.now();
        Double thisMonth = expenseRepository.getMonthlyExpense(now.getMonthValue(), now.getYear());
        summary.put("total", total);
        summary.put("today", today);
        summary.put("thisMonth", thisMonth);
        return summary;
    }
    @GetMapping("/category-summary")
    public Map<String, Double> getCategorySummary() {
        Map<String, Double> categorySummary = new HashMap<>();
        // Call the repository method
        List<Object[]> results = expenseRepository.getExpenseByCategory();
        // Convert List<Object[]> to Map<String, Double>
        for (Object[] row : results) {
            String category = (String) row[0];
            Double amount = (Double) row[1];
            categorySummary.put(category, amount);
        }
        return categorySummary;
    }
    @GetMapping("/budget-check")
    public Map<String, Object> checkMonthlyBudget(@RequestParam Double budget) {

        Map<String, Object> response = new HashMap<>();

        LocalDate now = LocalDate.now();
        Double spentThisMonth =
                expenseRepository.getMonthlyExpense(now.getMonthValue(), now.getYear());

        response.put("budget", budget);
        response.put("spent", spentThisMonth);

        if (spentThisMonth > budget) {
            response.put("status", "LIMIT_EXCEEDED");
            response.put("overBy", spentThisMonth - budget);
        } else {
            response.put("status", "WITHIN_LIMIT");
            response.put("remaining", budget - spentThisMonth);
        }

        return response;
    }

}


