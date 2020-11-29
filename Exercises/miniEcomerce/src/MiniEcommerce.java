import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

import static spark.Spark.*;

 class MiniEcommerce {
     HashSet<Product> listOfProduct = new HashSet<>();

     //metodi
     boolean searchProductByName(String name){
         if(!listOfProduct.stream().filter(x->x.getName().equals(name)).findFirst().isPresent())
             return false;
         return true;
     }
     Product returnProductByName(String name){
         if(searchProductByName(name))
             return listOfProduct.stream().filter(x->x.getName().equals(name)).findFirst().get();
         return null;
     }

     public static void main(String[] args) {
         MiniEcommerce miniEcommerce = new MiniEcommerce();
         miniEcommerce.listOfProduct.add(new Product("fanta", "non è bona ma è tanta", 5, 1.20));
         miniEcommerce.listOfProduct.add(new Product("chinotto", "sempre meglio di niente", 5, 1.20));
         miniEcommerce.listOfProduct.add(new Product("cocacola", "la coca, cola", 5, 1.20));
         Gson gson = new Gson();

         //metodiWebService
         post("/addProduct", (request, response) -> {
             if(miniEcommerce.listOfProduct.add(gson.fromJson(request.body(), Product.class))){
                 response.status(201);
                 return "Created";
             }
             response.status(406);
             return "Not Acceptable: product already exist";
         });


         put("/buyProduct", (request, response) -> {
             String nameOfProduct = gson.fromJson(request.body(), String.class);
             if(miniEcommerce.searchProductByName(nameOfProduct)){
                 if(Integer.parseInt(gson.fromJson(request.queryParams("qta"), String.class)) <= returnProductByName(nameOfProduct) ){
                     miniEcommerce.returnProductByName(nameOfProduct).setStock(Integer.parseInt(gson.fromJson(request.queryParams("qta"), String.class)));
                     response.status(200);
                     return "OK";
                 }
                 response.status(406);
                 return "Not Acceptable: product finished";
             }
             response.status(404);
             return "Not Found";
         });


         delete("/removeProduct", (request, response) -> {
             if(miniEcommerce.listOfProduct.remove(miniEcommerce.returnProductByName(gson.fromJson(request.body(), String.class)))){
                 response.status(200);
                 return "OK";
             }
             response.status(404);
             return "Not Found";
         });


         get("/listOfProduct", (request, response) -> {
             response.type("application/json");
             return gson.toJson(miniEcommerce.listOfProduct.stream()
                     .collect(Collectors.toList()));
         });
     }
 }
