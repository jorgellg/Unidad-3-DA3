package mx.edu.utng.orders;

import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.Calendar;

import mx.edu.utng.orders.model.Customer;
import mx.edu.utng.orders.model.MethodPayment;
import mx.edu.utng.orders.model.OrderDetail;
import mx.edu.utng.orders.model.OrderHeader;
import mx.edu.utng.orders.model.Product;
import mx.edu.utng.orders.sqlite.DBOperations;
import mx.edu.utng.orders.sqlite.OrderDB;

public class MainActivity extends AppCompatActivity {

    DBOperations data;

    public class DataTaskTest extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            // [INSERCIONES]
            String currentDate =
                    Calendar.getInstance().getTime().toString();

            try {

                data.getDb().beginTransaction();

                // Insert customers
                String customer1 = data.insertCustomer(
                        new Customer(null, "Veronica", "Del Topo", "4552000"));
                String customer2 = data.insertCustomer(
                        new Customer(null, "Carlos", "Villagran", "4440000"));

                // Insert Methods of payment
                String methodPayment1 = data.insertMethodPayment(
                        new MethodPayment(null, "Efectivo"));
                String methodPayment2 = data.insertMethodPayment(
                        new MethodPayment(null, "Crédito"));
                Log.i("Data", "Customers:" + customer1+", "+customer2+" Methods payment: "+ methodPayment1+", "+methodPayment2);

                // Insert Products
                String product1 = data.insertProduct(new Product(
                        null, "Manzana unidad", 2, 100));
                String product2 = data.insertProduct(new Product(
                        null, "Pera unidad", 3, 230));
                String product3 = data.insertProduct(new Product(
                        null, "Guayaba unidad", 5, 55));
                String product4 = data.insertProduct(new Product(
                        null, "Maní unidad", 3.6f, 60));

                // Insert Orders
                String order1 = data.insertOrderHeader(
                        new OrderHeader(null, customer1, methodPayment1, currentDate));
                String order2 = data.insertOrderHeader(
                        new OrderHeader(null, customer2, methodPayment2, currentDate));

                // Insert order details
                data.insertOrderDetail(new OrderDetail(order1, 1, product1, 5, 2));
                data.insertOrderDetail(new OrderDetail(order1, 2, product2, 10, 3));
                data.insertOrderDetail(new OrderDetail(order2, 1, product3, 30, 5));
                data.insertOrderDetail(new OrderDetail(order2, 2, product4, 20, 3.6f));

                // Eliminación Pedido
                // data.deleteOrderHeader(order1);

                // Actualización Cliente
                data.updateCustomer(new Customer(
                        customer2, "Carlos Alberto", "Villagran", "3333333"));

                data.getDb().setTransactionSuccessful();
            } finally {
                data.getDb().endTransaction();
            }

            // [QUERIES]
            Log.d("Customers","Customers");
            DatabaseUtils.dumpCursor(data.getCustomers());
            Log.d("Method of Payment", "Method of Payments");
            DatabaseUtils.dumpCursor(data.getMethodsPayment());
            Log.d("Products", "Products");
            DatabaseUtils.dumpCursor(data.getProducts());
            Log.d("OrderHeaders", "OrderHeaders");
            DatabaseUtils.dumpCursor(data.getOrders());
            Log.d("OrderDetails", "OrderDetails");
            DatabaseUtils.dumpCursor(data.getOrderDetails());
            Log.d("Categories", "Categories");
            DatabaseUtils.dumpCursor(data.getCategories());

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getApplicationContext().deleteDatabase(
                OrderDB.DATABASE_NAME);
        data = DBOperations.getDBOperations(
                getApplicationContext());

        new DataTaskTest().execute();
    }

}
