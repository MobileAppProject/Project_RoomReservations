package be.vdab.meetingroomreservations.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "DESCRIPTION")
	@NotNull(message="Omschrijving product is verplicht")
	private String description;

	
	@Column(name = "PRICE")
	@NotNull(message="Prijs product is verplicht")
	private double price;
	
	@Column(name = "ACTIVE")
	@NotNull
	private String active = "Y";

    @Column(name = "MAX_QUANTITY")
    @NotNull
    private Integer maxOrderQuantity;
	
	@Column(name = "version")
	@Version
	private int version = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer someId) {
        id = someId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String someDescription) {
        description = someDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double somePrice) {
        price = somePrice;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String someActive) {
        active = someActive;
    }

    public Integer getMaxOrderQuantity() {
        return maxOrderQuantity;
    }

    public void setMaxOrderQuantity(Integer someMaxOrderQuantity) {
        maxOrderQuantity = someMaxOrderQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }

        Product product = (Product) o;

        if (Double.compare(product.price, price) != 0) {
            return false;
        }
        if (!description.equals(product.description)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = description.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
