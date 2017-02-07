package com.github.czarijb.interfaces.impl;

import com.github.czarijb.interfaces.ExpensesDAO;
import com.github.czarijb.objects.Expenses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;


public class ExpensesDAOImpl implements ExpensesDAO {

    private ObservableList<Expenses> expensesList = FXCollections.observableArrayList();
    private Session session;

    @Override
    public void addExpenses(Expenses expenses) {
        expensesList.add(expenses);
        try {
            session.beginTransaction();
            session.save(expenses);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateExpenses(Expenses expenses) {
        try {
            session.beginTransaction();
            session.update(expenses);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExpenses(Expenses expenses) {

        expensesList.remove(expenses);
        try {
            session.beginTransaction();
            session.delete(expenses);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public ObservableList<Expenses> getExpensesList() {
        return expensesList;
    }

    public void fillExpensesTestData(){

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Expenses.class);
            this.expensesList.addAll(criteria.list());
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
