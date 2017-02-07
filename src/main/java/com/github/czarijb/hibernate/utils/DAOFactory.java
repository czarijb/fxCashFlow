package com.github.czarijb.hibernate.utils;

import com.github.czarijb.interfaces.impl.AssetsDAOImpl;
import com.github.czarijb.interfaces.impl.ExpensesDAOImpl;
import com.github.czarijb.interfaces.impl.IncomeDAOImpl;
import com.github.czarijb.interfaces.impl.LiabilitiesDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by aleksandr on 07.02.17.
 */
public class DAOFactory {
    private static DAOFactory ourInstance = new DAOFactory();

    public static DAOFactory getInstance() {
        return ourInstance;
    }

    private DAOFactory() {
    }

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session = sessionFactory.openSession();

    private AssetsDAOImpl assetsDao;

    public AssetsDAOImpl getAssetsDao(){
        if(assetsDao == null){
            assetsDao = new AssetsDAOImpl();
            assetsDao.setSession(session);
        }
        return assetsDao;
    }

    private ExpensesDAOImpl expensesDao;

    public ExpensesDAOImpl getExpensesDao(){
        if(expensesDao == null){
            expensesDao = new ExpensesDAOImpl();
            expensesDao.setSession(session);
        }
        return expensesDao;
    }

    private IncomeDAOImpl incomeDao;

    public IncomeDAOImpl getIncomeDao(){
        if(incomeDao == null){
            incomeDao = new IncomeDAOImpl();
            incomeDao.setSession(session);
        }
        return incomeDao;
    }


    private LiabilitiesDAOImpl liabilitiesDao;

    public LiabilitiesDAOImpl getLiabilitiesDao(){
        if(liabilitiesDao == null){
            liabilitiesDao = new LiabilitiesDAOImpl();
            liabilitiesDao.setSession(session);
        }
        return liabilitiesDao;
    }

    public void closeSession(){
        session.close();
        sessionFactory.close();
    }
}
