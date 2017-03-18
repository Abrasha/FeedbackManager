package com.aabramov.view;

import com.aabramov.entity.Address;
import com.aabramov.entity.City;
import com.aabramov.entity.Restaurant;
import com.aabramov.entity.dao.AddressDAO;
import com.aabramov.entity.dao.CityDAO;
import com.aabramov.entity.dao.RestaurantDAO;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Andrii Abramov on 12/19/15.
 */
public class AddRestaurantController implements Initializable {
    
    @FXML
    private TextField tfName;
    @FXML
    private ComboBox<City> cbCities;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfBuilding;
    @FXML
    private TextField tfPostalCode;
    private Stage parent;
    private ObservableSet<Restaurant> items;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbCities.setConverter(new StringConverter<City>() {
            @Override
            public String toString(City object) {
                return object.getName();
            }
            
            
            @Override
            public City fromString(String string) {
                return null;
            }
        });
    }
    
    
    @FXML
    public void buttonOkClicked() {
        
        String restaurantName = tfName.getText();
        String street = tfName.getText();
        String building = tfBuilding.getText();
        String postalCode = tfPostalCode.getText();
        City selectedCity = cbCities.getSelectionModel().getSelectedItem();
        
        if (!restaurantName.isEmpty() && selectedCity != null) {
            
            Restaurant added = new Restaurant();
            added.setName(restaurantName);
            added.setFeedback(new ArrayList<>());
            
            Address address = new Address();
            address.setStreet(street);
            address.setBuildingNumber(Integer.valueOf(building));
            address.setPostalCode(Integer.valueOf(postalCode));
            
            AddressDAO.getInstance().save(address);
            
            // save address
            
            added.setAddress(address);
            
            RestaurantDAO.getInstance().save(added);
            
            selectedCity.getRestaurants().add(added);
            
            
            items.add(added);
            
            // update city
            
            CityDAO.getInstance().update(selectedCity);
            
            parent.close();
            
        } else {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No nameprovided.");
            alert.setHeaderText("Cannot add city.");
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
    
    
    public void setCities(ObservableList<City> cities) {
        cbCities.setItems(cities);
    }
    
    
    public void setItems(ObservableSet<Restaurant> items) {
        this.items = items;
    }
}
