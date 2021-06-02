package study.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name; // 상품 이름
    private int price; // 상품 가격
    private int stockQuantity; // 재고 수량

    @OneToMany(mappedBy = "item")
    private List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();

    // getter setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<ItemCategory> getItemCategories() { return itemCategories; }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
