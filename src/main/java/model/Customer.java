package model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private String address;

    @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

}
