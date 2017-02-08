package com.github.czarijb.interfaces.impl;

import com.github.czarijb.interfaces.ExpensesDAO;
import com.github.czarijb.objects.Expenses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExpensesDAOImpl implements ExpensesDAO {

    private ObservableList<Expenses> expensesList = FXCollections.observableArrayList();
    private static final Logger log = LoggerFactory.getLogger(ExpensesDAOImpl.class);
    private Session session;

    @Override
    public void addExpenses(Expenses expenses) {
        expensesList.add(expenses);
        log.debug("add new expenses in expensesList");
        try {
            log.debug("try to insert new expenses in DB");
            session.beginTransaction();
            session.save(expenses);
            session.getTransaction().commit();
            log.debug("insert new expenses in DB");
        }catch (Exception e){
            log.debug("can't adding new expenses in DB");
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
        log.debug("delete expenses in expensesList");
        try {
            log.debug("try to delete expenses in DB");
            session.beginTransaction();
            session.delete(expenses);
            session.getTransaction().commit();
            log.debug("delete expenses in DB");
        }catch (Exception e){
            log.debug("can't deleting expenses in DB");
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public ObservableList<Expenses> getExpensesList() {
        return expensesList;
    }

    public void fillExpensesTestData(){

        try {
            log.debug("try get list of expenses from DB");
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Expenses.class);
            this.expensesList.addAll(criteria.list());
            session.getTransaction().commit();
            log.debug("get all expenses from DB");
        }catch (Exception e){
            log.debug("cant't get all expenses from DB");
            e.printStackTrace();
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
