package be.vdab.starter.controller;

import be.rubus.shop.model.Product;
import be.rubus.shop.service.ProductService;
import be.vdab.starter.AbstractRestTest;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Application;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ProductControllerTest extends AbstractRestTest {

    @Mock
    private ProductService productServiceMock;

    @Override
    protected Application configure() {
        ProductController controller = new ProductController();

        MockitoAnnotations.initMocks(this);
        injectMocks(controller, productServiceMock);

        ResourceConfig config = new ResourceConfig();
        config.register(controller);
        // To disable Spring integration
        config.property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, Boolean.TRUE);

        config.register(JacksonFeature.class);

        return config;
    }
    @Test
    public void testGetAllProducts() {
        List<Product> products = new ArrayList<Product>();
        products.add(newProduct(1, "Product1"));
        products.add(newProduct(2, "Product2"));

        when(productServiceMock.getActiveProducts()).thenReturn(products);

        String hello = target("products").request().get(String.class);
        assertEquals("[{\"id\":1,\"description\":\"name\",\"price\":0.0,\"active\":\"Y\",\"maxOrderQuantity\":null}," +
                "{\"id\":2,\"description\":\"name\",\"price\":0.0,\"active\":\"Y\",\"maxOrderQuantity\":null}]", hello);
    }

    private Product newProduct(int id, String name) {
        Product result = new Product();
        result.setId(id);
        result.setDescription("name");
        return result;
    }
}
