package com.github.czarijb.controllers;


import com.github.czarijb.hibernate.utils.DAOFactory;
import com.github.czarijb.interfaces.impl.AssetsDAOImpl;
import com.github.czarijb.interfaces.impl.ExpensesDAOImpl;
import com.github.czarijb.interfaces.impl.IncomeDAOImpl;
import com.github.czarijb.interfaces.impl.LiabilitiesDAOImpl;
import com.github.czarijb.objects.Assets;
import com.github.czarijb.objects.Expenses;
import com.github.czarijb.objects.Income;
import com.github.czarijb.objects.Liabilities;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;



public class MainWindowController {

    private static final Logger log = LoggerFactory.getLogger(MainWindowController.class);
    private AssetsDAOImpl assetsDAO = DAOFactory.getInstance().getAssetsDao();
    private ExpensesDAOImpl expensesDAO = DAOFactory.getInstance().getExpensesDao();
    private IncomeDAOImpl incomeDAO = DAOFactory.getInstance().getIncomeDao();
    private LiabilitiesDAOImpl liabilitiesDAO = DAOFactory.getInstance().getLiabilitiesDao();

    private Stage mainStage;

    @FXML private Label expensesSumLabel;
    @FXML private Label incomeSumLabel;
    @FXML private Label cashFlowLabel;

    @FXML private PieChart yourProgress;

    @FXML private TableView assetsTableView;
    @FXML private TableColumn<Assets, Long> assetsColumnId;
    @FXML private TableColumn<Assets, String> assetsColumnName;
    @FXML private TableColumn<Assets, Integer> assetsColumnVolume;
    @FXML private TableColumn<Assets, Integer> assetsColumnPrice;
    @FXML private TableColumn<Assets, Integer> assetsColumnTotalPrice;

    @FXML private TableView expensesTableView;
    @FXML private TableColumn<Expenses, Long> expensesColumnId;
    @FXML private TableColumn<Expenses, String> expensesColumnName;
    @FXML private TableColumn<Expenses, Integer> expensesColumnPrice;

    @FXML private TableView incomeTableView;
    @FXML private TableColumn<Income, Long> incomeColumnId;
    @FXML private TableColumn<Income, String> incomeColumnName;
    @FXML private TableColumn<Income, Integer> incomeColumnPrice;

    @FXML private TableView liabilitiesTableView;
    @FXML private TableColumn<Liabilities, Long> liabilitiesColumnId;
    @FXML private TableColumn<Liabilities, String> liabilitiesColumnName;
    @FXML private TableColumn<Liabilities, Integer> liabilitiesColumnPrice;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EventDialogController eventDialogController;
    private Stage eventDialogStage;

    @FXML public void initialize(){

        assetsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        assetsColumnId.setCellValueFactory(new PropertyValueFactory<Assets, Long>("id"));
        assetsColumnName.setCellValueFactory(new PropertyValueFactory<Assets, String>("name"));
        assetsColumnVolume.setCellValueFactory(new PropertyValueFactory<Assets, Integer>("volume"));
        assetsColumnPrice.setCellValueFactory(new PropertyValueFactory<Assets, Integer>("price"));
        assetsColumnTotalPrice.setCellValueFactory(new PropertyValueFactory<Assets, Integer>("totalPrice"));


        expensesTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        expensesColumnId.setCellValueFactory(new PropertyValueFactory<Expenses, Long>("id"));
        expensesColumnName.setCellValueFactory(new PropertyValueFactory<Expenses, String>("name"));
        expensesColumnPrice.setCellValueFactory(new PropertyValueFactory<Expenses, Integer>("price"));


        incomeTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        incomeColumnId.setCellValueFactory(new PropertyValueFactory<Income, Long>("id"));
        incomeColumnName.setCellValueFactory(new PropertyValueFactory<Income, String>("name"));
        incomeColumnPrice.setCellValueFactory(new PropertyValueFactory<Income, Integer>("price"));


        liabilitiesTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        liabilitiesColumnId.setCellValueFactory(new PropertyValueFactory<Liabilities, Long>("id"));
        liabilitiesColumnName.setCellValueFactory(new PropertyValueFactory<Liabilities, String>("name"));
        liabilitiesColumnPrice.setCellValueFactory(new PropertyValueFactory<Liabilities, Integer>("price"));

        initListeners();

        assetsDAO.fillAssetsTestData();
        expensesDAO.fillExpensesTestData();
        incomeDAO.fillIncomeTestData();
        liabilitiesDAO.fillLiabilitiesTestData();

        assetsTableView.setItems(assetsDAO.getAssetsList());
        expensesTableView.setItems(expensesDAO.getExpensesList());
        incomeTableView.setItems(incomeDAO.getIncomeList());
        liabilitiesTableView.setItems(liabilitiesDAO.getLiabilitiesList());

        initPieChartData();
        initLoaders();
    }

    public void initListeners(){
        expensesDAO.getExpensesList().addListener((ListChangeListener) (c) ->{
            expensesSumm();
            cashFlow();
            initPieChartData();
        });

        incomeDAO.getIncomeList().addListener((ListChangeListener) (c) ->{
            incomeSumm();
            cashFlow();
            initPieChartData();
        });

        assetsTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                eventDialogController.setAssets((Assets) assetsTableView.getSelectionModel().getSelectedItem());
                showDialog();
            }
        });

        expensesTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                eventDialogController.setExpenses((Expenses) expensesTableView.getSelectionModel().getSelectedItem());
                showDialog();
            }
        });

        incomeTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                eventDialogController.setIncome((Income) incomeTableView.getSelectionModel().getSelectedItem());
                showDialog();
            }
        });

        liabilitiesTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                eventDialogController.setLiabilities((Liabilities) liabilitiesTableView.getSelectionModel().getSelectedItem());
                showDialog();
            }
        });
    }

    private void initLoaders() {
        try{
            log.debug("Loading FXML for event view from: /fxml/eventView.fxml");
            fxmlLoader.setLocation(getClass().getResource("/fxml/eventView.fxml"));
            fxmlEdit = fxmlLoader.load();
            eventDialogController = fxmlLoader.getController();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int expensesSumm(){
        log.debug("cosider expenses");
        int a = 0;
        for (Expenses expenses : expensesDAO.getExpensesList()){
            a += expenses.getPrice();
            expensesSumLabel.setText("    Ваши ежемесячные расходы составляют : " + a);
        }
        return a;
    }

    private int incomeSumm(){
        log.debug("consider income");
        int a = 0;
        for (Income income : incomeDAO.getIncomeList()){
            a += income.getPrice();
            incomeSumLabel.setText("    Ваши ежемесячные доходы составляют : " + a);
        }

        return a;
    }

    public void initPieChartData(){
        log.debug("initialize piechart");
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("Доходы", incomeSumm()),
                new PieChart.Data("Расходы", expensesSumm())
        );
        yourProgress.setData(list);
    }

    private void cashFlow(){
        log.debug("consider cashflow");
        cashFlowLabel.setText("    Ваш ежемесячный денежный поток : " + (incomeSumm() - expensesSumm()));
    }



    @FXML
    private void showDialog(){
        log.debug("Event button pushed");
        if(eventDialogStage==null){
            log.debug("Showing JFX event scene" );
            eventDialogStage = new Stage();
            eventDialogStage.setTitle("Редактирование записи");
            eventDialogStage.setMinWidth(600);
            eventDialogStage.setMinHeight(400);
            eventDialogStage.setResizable(false);
            eventDialogStage.setScene(new Scene(fxmlEdit));
            eventDialogStage.initModality(Modality.WINDOW_MODAL);
            eventDialogStage.initOwner(mainStage);
        }

        eventDialogStage.showAndWait();

    }

    public void closeAllThred(){
        log.debug("closing all thread of programm");
        DAOFactory factory = DAOFactory.getInstance();
        factory.closeSession();
    }

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }
}
