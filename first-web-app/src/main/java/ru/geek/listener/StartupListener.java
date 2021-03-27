package ru.geek.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geek.persist.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class StartupListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Startup Listener");

        ProductRepository productRepository = new ProductRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        CustomerRepository customerRepository = new CustomerRepository();

        productRepository.save(new Product(null, "Product 1", "Description 1", new BigDecimal(100)));
        productRepository.save(new Product(null, "Product 2", "Description 2", new BigDecimal(200)));
        productRepository.save(new Product(null, "Product 3", "Description 3", new BigDecimal(300)));

        sce.getServletContext().setAttribute("productRepository", productRepository);
        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
        sce.getServletContext().setAttribute("customerRepository", customerRepository);
    }
}