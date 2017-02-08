package com.github.czarijb.interfaces.impl;

import com.github.czarijb.interfaces.IncomeDAO;
import com.github.czarijb.objects.Income;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IncomeDAOImpl implements IncomeDAO {
    private ObservableList<Income> incomeList = FXCollections.observableArrayList();
    private static final Logger log = LoggerFactory.getLogger(IncomeDAOImpl.class);
    private Session session;

    @Override
    public void addIncome(Income income) {

        incomeList.add(income);
        log.debug("add new income in incomeList");
        try {
            log.debug("try to insert new income in DB");
            session.beginTransaction();
            session.save(income);
            session.getTransaction().commit();
            log.debug("insert new income in DB");
        }catch (Exception e){
            log.debug("can't adding new income in DB");
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
        log.debug("delete income in incomeList");
        try {
            log.debug("try to delete income in DB");
            session.beginTransaction();
            session.delete(income);
            session.getTransaction().commit();
            log.debug("delete income in DB");
        }catch (Exception e){
            log.debug("can't deleting income in DB");
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public javafx.collections.ObservableList<Income> getIncomeList() {
        return incomeList;
    }

    public void fillIncomeTestData() {

        try {
            log.debug("try get list of income from DB");
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Income.class);
            this.incomeList.addAll(criteria.list());
            session.getTransaction().commit();
            log.debug("get all income from DB");
        }catch (Exception e){
            log.debug("cant't get all income from DB");
            e.printStackTrace();
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
