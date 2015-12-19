package sample.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by abrasha on 12/15/15.
 */
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {

    private int id;
    private String name;
    private Address address;
    private List<Feedback> feedback;



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



    @OneToOne
    @JoinColumn(name = "add_id")
    public Address getAddress(){
        return address;
    }



    public void setAddress(Address address){
        this.address = address;
    }



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "rest_id")
    public List<Feedback> getFeedback(){
        return feedback;
    }



    public void setFeedback(List<Feedback> feedback){
        this.feedback = feedback;
    }



    @Override
    public String toString(){
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", feedback=" + feedback +
                '}';
    }
}
