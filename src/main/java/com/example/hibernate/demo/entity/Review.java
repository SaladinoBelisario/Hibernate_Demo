package com.example.hibernate.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    // annotate the class as an entity and map to db
    // define the fields
    // annotate the fields with column names
    // **Setup mapping to InstructorDetail**
    // create constructors
    //generate getter and setters
    // toString method override

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
