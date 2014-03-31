package be.rubus.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "CUSTOMER_ID")
    @ManyToOne
    private Customer customer;

    @Column(name = "DELIVERY_DATE")
    @NotNull(message = "Leverings datum is verplicht")
    private Date deliveryDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderlines = new ArrayList<OrderLine>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer someId) {
        id = someId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer someCustomer) {
        customer = someCustomer;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date someDeliveryDate) {
        deliveryDate = someDeliveryDate;
    }

    public List<OrderLine> getOrderlines() {
        return Collections.unmodifiableList(orderlines);
    }

    public void addOrderLine(OrderLine line) {
        line.setOrder(this);
        orderlines.add(line);
    }

    public double getOrderTotal() {
        double result = 0.0;
        if (orderlines != null) {
            for (OrderLine line : orderlines) {
                result += line.getProduct().getPrice() * line.getQuantity();
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }

        Order order = (Order) o;

        if (!customer.equals(order.customer)) {
            return false;
        }
        if (!deliveryDate.equals(order.deliveryDate)) {
            return false;
        }
        if (!orderlines.equals(order.orderlines)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = customer.hashCode();
        result = 31 * result + deliveryDate.hashCode();
        result = 31 * result + orderlines.hashCode();
        return result;
    }
}
