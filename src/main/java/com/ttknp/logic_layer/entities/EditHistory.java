package com.ttknp.logic_layer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @AllArgsConstructor
// @NoArgsConstructor
@Entity
@Table(name = "edit_histories")
@Data
public class EditHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;
    private String fullname;
    private Short age;
    private Boolean alive;
    /*
        The default strategy for @Column(name="TestName") will mapped test_name, this is correct behavior!
        If you have a column named TestName in your database you should change Column annotation to @Column(name="testname").
    */
    @Column(name = "datetimeedit") // now it mapped colum "datetimeEdit"
    private Integer datetimeEdit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aid")
    @JsonIgnore
    private Author author;

    public EditHistory() {}
}
