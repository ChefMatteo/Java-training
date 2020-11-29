import com.google.gson.Gson;

import java.util.Objects;
import java.util.UUID;

 class Product {
     private String name;
     private String body;
     private int stock;
     private double price;

     //costruttori
     public Product(){
     }
     public Product(String name, String body, int stock, double price) {
         this.name = name;
         this.body = body;
         this.stock = stock;
         this.price = price;
     }

     //getter
     public String getName() {
         return name;
     }
     public String getBody() {
         return body;
     }
     public int getStock() {
         return stock;
     }
     public double getPrice() {
         return price;
     }

     //setter
     public void setName(String name) {
         this.name = name;
     }
     public void setBody(String body) {
         this.body = body;
     }
     public void setStock(int stock) {
         this.stock += stock;
     }
     public void setPrice(double price) {
         this.price = price;
     }

     //metodi


     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Product product = (Product) o;
         return Objects.equals(name, product.name);
     }
     @Override
     public int hashCode() {
         return Objects.hash(name);
     }
     @Override
     public String toString() {
         return "Product name: " + name;
     }
 }
