package be.rubus.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ORDER_LINES")
public class OrderLine {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "QUANTITY")
    @NotNull(message = "aantal producten is verplicht in order")
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer someId) {
        id = someId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order someOrder) {
        order = someOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product someProduct) {
        product = someProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer someQuantity) {
        quantity = someQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderLine)) {
            return false;
        }

        OrderLine orderLine = (OrderLine) o;

        if (!product.equals(orderLine.product)) {
            return false;
        }
        if (!quantity.equals(orderLine.quantity)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }
}
