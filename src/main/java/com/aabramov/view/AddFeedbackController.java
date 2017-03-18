package com.aabramov.view;

import com.aabramov.entity.Feedback;
import com.aabramov.entity.Restaurant;
import com.aabramov.entity.dao.FeedbackDAO;
import com.aabramov.entity.dao.RestaurantDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author Andrii Abramov on 12/19/15.
 */
public class AddFeedbackController implements Initializable {
    
    private Restaurant restaurant;
    private Stage parent;
    private ObservableList<Feedback> items;
    
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfAuthor;
    @FXML
    private TextArea taBody;
    @FXML
    private DatePicker datePicker;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
    @FXML
    public void buttonOkClicked() {
        
        String title = tfTitle.getText();
        String author = tfAuthor.getText();
        String body = taBody.getText();
        LocalDate date = datePicker.getValue();
        
        
        if (!title.isEmpty() && !body.isEmpty()) {
            
            Feedback added = new Feedback();
            added.setAuthor(author);
            added.setTitle(title);
            added.setBody(body);
            added.setDate(date);
            
            restaurant.getFeedback().add(added);
            items.add(added);
            
            FeedbackDAO.getInstance().save(added);
            RestaurantDAO.getInstance().update(restaurant);
            
            parent.close();
            
        } else {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No title or body provided.");
            alert.setHeaderText("Cannot add your feedback.");
            alert.showAndWait();
            
        }
        
    }
    
    
    @FXML
    public void buttonCancelClicked() {
        parent.close();
    }
    
    public void setParent(Stage parent) {
        this.parent = parent;
    }
    
    public Restaurant getRestaurant() {
        return restaurant;
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    public void setItems(ObservableList<Feedback> items) {
        this.items = items;
    }
}
