package tasks.task4;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String name;
    private double price;
    private int quantity;

    public static void run(){
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Task: Create class Product with fields name, price and quantity. \n\tCreate four instances of type Product\n\tDisplay the name and quantity of the most expensive item \n\tDisplay the name of the items, which has the biggest quantity\n");
        List<Product> products = new ArrayList<>();
        products.add(new Product("prod1", 35, 3));
        products.add(new Product("prod2", 51, 6));
        products.add(new Product("prod3", 95, 1));
        products.add(new Product("prod4", 18, 10));

        Product mostExpensive = new Product();
        Product mostQuantity = new Product();

        for(Product product : products){
            if(product.getPrice() > mostExpensive.getPrice()){
                mostExpensive = product;
            }
        }

        for(Product product : products){
            if(product.getQuantity() > mostQuantity.getQuantity()){
                mostQuantity = product;
            }
        }

        System.out.println("Most Expensive product: " + mostExpensive.getName());
        System.out.println("Product with most quantity: " + mostQuantity.getName());
    }
}
