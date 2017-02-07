package com.github.czarijb.interfaces;

import com.github.czarijb.objects.Income;

/**
 * Created by aleksandr on 04.02.17.
 */
public interface IncomeDAO {
    public void addIncome (Income income);
    public void updateIncome(Income income);
    public void deleteIncome(Income income);
    /*public List<Expenses> getAllAssetsFromDB();*/
}
