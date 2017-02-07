package com.github.czarijb.interfaces;

import com.github.czarijb.objects.Income;


public interface IncomeDAO {
    public void addIncome (Income income);
    public void updateIncome(Income income);
    public void deleteIncome(Income income);
}
