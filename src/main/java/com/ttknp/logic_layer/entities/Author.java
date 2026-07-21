package com.ttknp.logic_layer.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

// @AllArgsConstructor
// @NoArgsConstructor
@Entity
@Table(name = "authors")
@Data
public class Author {
    @Id
    private String aid;
    private String fullname;
    private Short age;
    private Boolean alive;
    // Mapping to the other table
    @OneToMany(cascade = CascadeType.ALL) // ,targetEntity = Address.class ,mappedBy = "ob"
    @JoinColumn(name = "aid")
    private List<EditHistory> editHistories; // ** it's not good for toString()

    public Author() {}

    @Override
    public String toString() {
        return "Author{" +
                "aid='" + aid + '\'' +
                ", fullname='" + fullname + '\'' +
                ", age=" + age +
                ", alive=" + alive +
                '}';
    }
}
