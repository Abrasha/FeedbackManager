package com.aabramov;

import com.aabramov.entity.dao.CityDAO;
import com.aabramov.helpers.HibernateUtil;
import com.aabramov.view.RootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    
    public static final Logger logger = Logger.getLogger(Main.class.getCanonicalName());
    
    
    public static void main(String[] args) {
        logger.log(Level.INFO, "App launched");
        launch(args);
    }
    
    
    @Override
    public void stop() throws Exception {
        HibernateUtil.close();
        logger.log(Level.INFO, "App stopped");
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        Thread hibernateLoading = new Thread(HibernateUtil::load);
        logger.log(Level.INFO, "Started loading hibernate");
        hibernateLoading.start();
        
        hibernateLoading.join();
        Parent root = loadRoot();
        
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        
        stage.show();
    }
    
    
    private Parent loadRoot() throws IOException {
        
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/root.fxml"));
        Parent result = loader.load();
        RootController controller = loader.getController();
        controller.setCities(CityDAO.getInstance().getAll());
        controller.setMainApp(this);
        
        return result;
    }
    
}
