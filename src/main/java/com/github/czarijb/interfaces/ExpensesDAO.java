package com.github.czarijb.interfaces;

import com.github.czarijb.objects.Expenses;

/**
 * Created by aleksandr on 04.02.17.
 */
public interface ExpensesDAO {

    public void addExpenses (Expenses expenses);
    public void updateExpenses(Expenses expenses);
    public void deleteExpenses(Expenses expenses);
    /*public List<Expenses> getAllAssetsFromDB();*/
}
