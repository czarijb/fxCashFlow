package com.github.czarijb.interfaces;

import com.github.czarijb.objects.Expenses;


public interface ExpensesDAO {

    public void addExpenses (Expenses expenses);
    public void updateExpenses(Expenses expenses);
    public void deleteExpenses(Expenses expenses);
}
