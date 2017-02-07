package com.github.czarijb.interfaces.impl;

import com.github.czarijb.interfaces.IncomeDAO;
import com.github.czarijb.objects.Income;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;


public class IncomeDAOImpl implements IncomeDAO {
    private ObservableList<Income> incomeList = FXCollections.observableArrayList();
    private Session session;

    @Override
    public void addIncome(Income income) {

        incomeList.add(income);
        try {
            session.beginTransaction();
            session.save(income);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateIncome(Income income) {
        try {
            session.beginTransaction();
            session.update(income);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIncome(Income income) {

        incomeList.remove(income);
        try {
            session.beginTransaction();
            session.delete(income);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public javafx.collections.ObservableList<Income> getIncomeList() {
        return incomeList;
    }

    public void fillIncomeTestData() {

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Income.class);
            this.incomeList.addAll(criteria.list());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
