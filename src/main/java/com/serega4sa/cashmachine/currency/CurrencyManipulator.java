package com.serega4sa.cashmachine.currency;

import com.serega4sa.cashmachine.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        denominations.put(denomination, count);
    }

    public long getTotalAmount() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }
        return total;
    }

    public boolean isAmountAvailable(long amount) {
        return amount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(long expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new HashMap<>();
        Map<Integer, Integer> denominations = ((TreeMap<Integer, Integer>) this.denominations).descendingMap();
        long neededAmount = expectedAmount;
        for (Map.Entry<Integer, Integer> item : denominations.entrySet()) {
            if (neededAmount == 0) break;
            int neededNumberOfBanknotes = (int) neededAmount / item.getKey();
            if (neededNumberOfBanknotes > 0) {
                int availableNumberOfBanknotes = item.getValue();
                int usedNumberOfBanknotes = neededNumberOfBanknotes <= availableNumberOfBanknotes ? neededNumberOfBanknotes : availableNumberOfBanknotes;
                neededAmount -= item.getKey() * usedNumberOfBanknotes;
                result.put(item.getKey(), usedNumberOfBanknotes);
            }
        }
        if (result.isEmpty()) {
            throw new NotEnoughMoneyException();
        }
        removeUsedDenominations(result);
        return result;
    }

    private void removeUsedDenominations(Map<Integer, Integer> withdrawnBanknotes) {
        for (Map.Entry<Integer, Integer> entry : withdrawnBanknotes.entrySet()) {
            Integer oldValue = denominations.get(entry.getKey());
            denominations.replace(entry.getKey(), oldValue - entry.getValue());
        }
    }
}
