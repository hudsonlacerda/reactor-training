package digital.osf.reactortraining.util;

import java.util.List;

import digital.osf.reactortraining.model.Brand;
import digital.osf.reactortraining.model.Category;
import digital.osf.reactortraining.model.Product;
import digital.osf.reactortraining.request.ProductRequestBody;

public class ProductUtil {

        public static ProductRequestBody productRequestBody() {
                return ProductRequestBody.builder().productName("Shotgun").brand(1).category(1).listPrice(1.0)
                                .modelYear(2000).build();
        }

        public static Product productToPersist() {
                Brand brand = brandToPersist();
                Category gunCategory = categoryToPersist();
                return Product.builder().productName("Shotgun")
                                .modelYear(2000)
                                .listPrice(1d)
                                .category(gunCategory.getId())
                                .brand(brand.getId())
                                .build();
        }

        public static Product savedProduct() {
                Brand brand = brandToPersist();
                Category gunCategory = categoryToPersist();
                return Product.builder().id(1).productName("Shotgun")
                                .listPrice(1.0)
                                .modelYear(2000)
                                .brand(brand.getId())
                                .category(gunCategory.getId())
                                .build();
        }

        public static List<Product> listProductToPersist() {
                Brand brand = brandToPersist();
                Category gunCategory = categoryToPersist();
                Category foodCategory = Category.builder().categoryName("FOOD").build();
                return List.of(
                                Product.builder().modelYear(2000).listPrice(1d).productName("Shotgun")
                                                .brand(brand.getId())
                                                .category(gunCategory.getId()).build(),
                                Product.builder().modelYear(2000).listPrice(1d).productName("Machinegun")
                                                .brand(brand.getId())
                                                .category(gunCategory.getId()).build(),
                                Product.builder().modelYear(2000).listPrice(1d).productName("Revolver")
                                                .brand(brand.getId())
                                                .category(gunCategory.getId()).build(),
                                Product.builder().modelYear(2000).listPrice(1d).productName("Pizza")
                                                .brand(brand.getId())
                                                .category(foodCategory.getId()).build());
        }

        public static List<Product> savedProducts() {
                Brand brand = brandToPersist();
                Category gunCategory = categoryToPersist();
                Category foodCategory = Category.builder().categoryName("FOOD").build();
                return List.of(
                                Product.builder().id(1).modelYear(2000).listPrice(1d).productName("Shotgun")
                                                .brand(brand.getId())
                                                .category(gunCategory.getId()).build(),
                                Product.builder().id(2).modelYear(2000).listPrice(1d).productName("Machinegun")
                                                .brand(brand.getId())
                                                .category(gunCategory.getId()).build(),
                                Product.builder().id(3).modelYear(2000).listPrice(1d).productName("Revolver")
                                                .brand(brand.getId())
                                                .category(gunCategory.getId()).build(),
                                Product.builder().id(4).modelYear(2000).listPrice(1d).productName("Pizza")
                                                .brand(brand.getId())
                                                .category(foodCategory.getId()).build());
        }

        public static Brand brandToPersist() {
                return Brand.builder().brandName("GUN'S PIZZA").build();
        }

        public static Category categoryToPersist() {
                Category gunCategory = Category.builder().categoryName("GUN").build();
                return gunCategory;
        }
}
