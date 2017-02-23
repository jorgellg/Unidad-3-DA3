package mx.edu.utng.orders.sqlite;

import java.util.UUID;

/**
 * Created by Jorge on 07/02/2017.
 */
public class OrderContract {

    interface OrderHeaderColumns{
        String ID = "id";
        String DATE = "record_date";
        String ID_CUSTOMER = "id_customer";
        String ID_METHOD_PAYMENT = "id_method_payment";
    }

    interface OrderDetailColumns{
        String ID = "id";
        String SEQUENCE = "sequence";
        String ID_PRODUCT = "id_product";
        String QUANTITY = "quantity";
        String PRICE = "price";
    }

    interface ProductColumns{
        String ID = "id";
        String NAME = "name";
        String PRICE = "price";
        String STOCK = "stock";
    }

    interface CustomerColumns{
        String ID = "id";
        String FIRSTNAME = "firstname";
        String LASTNAME = "lastname";
        String PHONE = "phone";
    }

    interface MethodPaymentColumns{
        String ID = "id";
        String NAME = "name";
    }

    interface CategoriesColumns{
        String ID = "id";
        String NAME = "name";
        String DESCRIPTION = "description";
        String NUMBER = "number";
    }

    public static class OrderHeaders implements OrderHeaderColumns{
        public static String generateIdOrderHeader(){
            return "OH-"+ UUID.randomUUID().toString();
        }
    }

    public static class OrderDetails implements OrderDetailColumns{

    }

    public static class Products implements ProductColumns{
        public static String generateIdProduct(){
            return "PR-"+ UUID.randomUUID().toString();
        }
    }

    public static class Customers implements CustomerColumns{
        public static String generateIdCustomer(){
            return "CU-"+ UUID.randomUUID().toString();
        }
    }

    public static class MethodsPayment implements MethodPaymentColumns{
        public static String generateIdMethodPAyment(){
            return "MP-"+ UUID.randomUUID().toString();
        }

    }

    public static class Categories implements CategoriesColumns{
        public static String generateIdCategories(){
            return "CT-"+ UUID.randomUUID().toString();
        }
    }

    private OrderContract(){

    }
}
