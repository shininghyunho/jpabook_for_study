package study.entity.item;

import study.entity.Category;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Album")
public class Album extends Item{
    private String artist;
    private String etc;

    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEtc() {
        return etc;
    }
    public void setEtc(String etc) {
        this.etc = etc;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='"+super.getName()+ '\'' +
                ", price='"+super.getPrice()+ '\'' +
                ", stockQuantity='"+super.getStockQuantity()+ '\'' +
                ", price='"+super.getPrice()+ '\'' +
                ", artist='" + artist + '\'' +
                ", etc='" + etc + '\'' +
                ", category 1='" + super.getCategories().get(0) + '\'' +
                '}';
    }
}
