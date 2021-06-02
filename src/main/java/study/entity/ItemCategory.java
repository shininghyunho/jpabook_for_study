package study.entity;

import javax.persistence.*;

@Entity
public class ItemCategory {
    @Id @GeneratedValue
    @Column(name = "ITEM_CATEGORY_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    // getter setter

    public Long getId() {
        return id;
    }

    public Item getItem() { return item; }
    public void setItem(Item item) {
        // inverse 관계 제거
        if (this.item != null){
            item.getItemCategories().remove(this);
        }

        this.item = item;
        item.getItemCategories().add(this);
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) {
        if (this.category != null){
            category.getItemCategories().remove(this);
        }

        this.category = category;
        category.getItemCategories().add(this);
    }

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                '}';
    }
}
