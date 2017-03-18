package com.aabramov.view;

import com.aabramov.entity.Feedback;
import com.aabramov.entity.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Andrii Abramov on 12/18/15.
 */
public class FeedbackListController implements Initializable {
    
    private Restaurant restaurant;
    
    @FXML
    private ListView<Feedback> lvFeedback;
    
    private ObservableList<Feedback> items;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lvFeedback.setCellFactory(p -> new FeedbackListCell());
        lvFeedback.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                
                Feedback selected = lvFeedback.getSelectionModel().getSelectedItem();
                
                Parent pane = null;
                FeedbackOverviewController controller = null;
                Stage overviewStage = new Stage();
                
                FXMLLoader loader = new FXMLLoader(FeedbackListController.class.getResource("/view/feedback_overview.fxml"));
                try {
                    pane = loader.load();
                    controller = loader.getController();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                controller.setStage(overviewStage);
                controller.setFeedback(selected);
                
                final Scene scene = new Scene(pane, 600, 400);
                overviewStage.setScene(scene);
                overviewStage.show();
            }
        });
    }
    
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.items = FXCollections.observableArrayList(restaurant.getFeedback());
        lvFeedback.setItems(this.items);
    }
    
    
    @FXML
    public void btnAddClicked() {
        
        Stage addDialog = new Stage();
        Parent pane = null;
        AddFeedbackController controller = null;
        
        FXMLLoader loader = new FXMLLoader(FeedbackListController.class.getResource("/view/add_feed_dialog.fxml"));
        try {
            pane = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        controller.setParent(addDialog);
        controller.setRestaurant(restaurant);
        controller.setItems(items);
        
        final Scene scene = new Scene(pane, 600, 400);
        addDialog.setScene(scene);
        addDialog.show();
    }
    
    
    @FXML
    public void btnRemoveClicked() {
        
        Feedback itemToRemove = lvFeedback.getSelectionModel().getSelectedItem();
        restaurant.getFeedback().remove(itemToRemove);
        this.items.remove(itemToRemove);
    }
    
    
    private class FeedbackListCell extends ListCell<Feedback> {
        @Override
        protected void updateItem(Feedback item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setText(item.getTitle() + " : " + item.getDate());
            } else {
                setText("");
            }
        }
        
    }
    
    
}
