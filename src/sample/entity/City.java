package sample.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by abrasha on 12/15/15.
 */
@Entity
@Table(name = "city")
public class City implements Serializable {

    private int id;
    private String name;
    private Set<Restaurant> restaurants;



    public City(int id, String name){
        this.id = id;
        this.name = name;
    }

    public City(){}



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId(){
        return id;
    }



    public void setId(int id){
        this.id = id;
    }


    @Basic
    @Column(name = "name")
    public String getName(){
        return name;
    }



    public void setName(String name){
        this.name = name;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    public Set<Restaurant> getRestaurants(){
        return restaurants;
    }



    public void setRestaurants(Set<Restaurant> restaurants){
        this.restaurants = restaurants;
    }





    @Override
    public String toString(){
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", restaurants=" + restaurants +
                '}';
    }
}
