package com.example.Splitwise.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "splitWiseGroup")
@Getter
@Setter
public class Group extends BaseModel {
    private String name;
    @ManyToMany
    private List<User> users;
    @OneToMany(mappedBy = "group") //name of the attribute mentioned in Expense class. Here it is "group" field.
    private List<Expense> expenses;
    @ManyToOne
    private User createdBy;
}
