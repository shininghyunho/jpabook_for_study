package study.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();

    // getter setter
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<ItemCategory> getItemCategories() { return itemCategories; }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
