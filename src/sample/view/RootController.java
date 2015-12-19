package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.Main;
import sample.entity.City;
import sample.entity.Restaurant;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by abrasha on 12/18/15.
 */
public class RootController implements Initializable {

    private Main mainApp;
    private List<City> cities;
    private ObservableSet<Restaurant> restaurantItems;

    @FXML
    private ComboBox<City> cbCities;
    @FXML
    private ListView<Restaurant> lvRestaurants;
    @FXML
    private StackPane spContent;



    @Override
    public void initialize(URL location, ResourceBundle resources){

        restaurantItems = FXCollections.observableSet();

        cbCities.setConverter(new StringConverter<City>() {
            @Override
            public String toString(City object){
                return object.getName();
            }



            @Override
            public City fromString(String string){
                return null;
            }
        });

        cbCities.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            restaurantItems.clear();
            restaurantItems.addAll(newValue.getRestaurants());
            lvRestaurants.getItems().setAll(restaurantItems);
        });


        lvRestaurants.setCellFactory(p -> new RestaurantListCell());

        lvRestaurants.setOnMouseClicked(e -> {

            Restaurant selected = lvRestaurants.getSelectionModel().getSelectedItem();

            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2){
                loadFeedbackList(selected);
            }

        });

    }



    private void setContent(Node content){
        spContent.getChildren().clear();
        spContent.getChildren().add(content);
    }



    private void loadFeedbackList(Restaurant selected){

        FXMLLoader loader = new FXMLLoader(RootController.class.getResource("feedback_list.fxml"));
        try{
            Node p = loader.load();
            FeedbackListController controller = loader.getController();
            controller.setRestaurant(selected);
            setContent(p);
        } catch (IOException e){
            e.printStackTrace();
        }

    }



    public void setCities(List<City> cities){
        this.cities = cities;
        ObservableList<City> cityItems = FXCollections.observableList(cities);
        cbCities.setItems(cityItems);
        cbCities.setValue(cityItems.get(0));
    }



    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;
    }



    @FXML
    public void deleteCityAction(){

        City selected = cbCities.getSelectionModel().getSelectedItem();
        cbCities.getItems().remove(selected);
        cities.remove(selected);

    }



    @FXML
    public void deleteRestaurantAction(){

        Restaurant selected = lvRestaurants.getSelectionModel().getSelectedItem();
        City city = cbCities.getSelectionModel().getSelectedItem();
        city.getRestaurants().remove(selected);
        lvRestaurants.getItems().remove(selected);

    }



    @FXML
    public void addCityAction(){

        Stage addDialog = new Stage();
        Parent pane = null;
        AddCityController controller = null;

        FXMLLoader loader = new FXMLLoader(FeedbackListController.class.getResource("add_city_dialog.fxml"));
        try{
            pane = loader.load();
            controller = loader.getController();
        } catch (IOException e){
            e.printStackTrace();
        }

        controller.setParent(addDialog);
        controller.setItems(cbCities.getItems());

        final Scene scene = new Scene(pane, 600, 400);
        addDialog.setScene(scene);
        addDialog.show();
    }



    @FXML
    public void addRestaurantAction(){

        Stage addDialog = new Stage();
        Parent pane = null;
        AddRestaurantController controller = null;

        FXMLLoader loader = new FXMLLoader(FeedbackListController.class.getResource("add_restaurant_dialog.fxml"));
        try{
            pane = loader.load();
            controller = loader.getController();
        } catch (IOException e){
            e.printStackTrace();
        }

        controller.setParent(addDialog);
        controller.setCities(cbCities.getItems());
        controller.setItems(restaurantItems);

        final Scene scene = new Scene(pane, 600, 400);
        addDialog.setScene(scene);
        addDialog.show();
    }



    private class RestaurantListCell extends ListCell<Restaurant> {
        @Override
        protected void updateItem(Restaurant item, boolean empty){
            super.updateItem(item, empty);
            if (!empty){
                setText(item.getName());
            } else {
                setText("");
            }
        }

    }
}
