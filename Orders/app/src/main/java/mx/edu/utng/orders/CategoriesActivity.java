package mx.edu.utng.orders;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapter.CategoriesAdapter;
import mx.edu.utng.orders.adapter.ProductAdapter;
import mx.edu.utng.orders.model.Categories;
import mx.edu.utng.orders.model.Product;
import mx.edu.utng.orders.sqlite.DBOperations;

/**
 * Created by Jorge on 23/02/2017.
 */
public class CategoriesActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etDescription;
    private EditText etNumber;
    private Button btSaveCategory;
    private DBOperations operations;
    private Categories categories2 = new Categories() ;
    private List<Categories> categories = new ArrayList<Categories>();

    private CategoriesAdapter adapter;

    private RecyclerView rvCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        operations = DBOperations.getDBOperations(getApplicationContext());
        categories2.setIdCategory("");
        initComponents();
    }

    private void initComponents(){
        etName = (EditText)findViewById(R.id.et_name);
        etDescription = (EditText)findViewById(R.id.et_description);
        etNumber = (EditText)findViewById(R.id.et_number);
        rvCategories = (RecyclerView) findViewById(R.id.rv_categories_list);
        rvCategories.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvCategories.setLayoutManager(layoutManager);
        getCategories();
        adapter = new CategoriesAdapter(categories);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_category:
                        operations.deleteCategories(
                                categories.get(rvCategories.getChildPosition((View)v.getParent().getParent())).getIdCategory());
                        getCategories();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_category:

                        Cursor c = operations.getCategoriesById(categories.get(rvCategories.getChildPosition(
                                (View)v.getParent().getParent())).getIdCategory());

                        if(c!=null){
                            if(c.moveToFirst()){
                                categories2 = new Categories(c.getString(1),
                                        c.getString(2),c.getString(3), c.getInt(4));
                            }
                            etName.setText(categories2.getName());
                            etDescription.setText(String.valueOf(categories2.getDescription()));
                            etNumber.setText(String.valueOf(categories2.getNumber()));
                        }else{
                            //Toast.makeText(getApplicationContext(),
                            //      "Registro no encontrado", Toast)
                        }

                        break;
                    default:
                        break;

                }
            }
        });
        rvCategories.setAdapter(adapter);
        btSaveCategory = (Button) findViewById(R.id.bt_save_categories);

        btSaveCategory = (Button)findViewById(R.id.bt_save_categories);

        btSaveCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!categories2.getIdCategory().equals("")) {
                    categories2.setName(etName.getText().toString());
                    categories2.setDescription(etDescription.getText().toString());
                    categories2.setNumber(Integer.parseInt(etNumber.getText().toString()
                    ));
                    operations.updateCategories(categories2);
                } else {
                    categories2 = new Categories("", etName.getText().toString(),
                            etDescription.getText().toString(),
                            Integer.parseInt(etNumber.getText().toString()));
                    operations.insertCategories(categories2);
                }
                // Log.d("Products","Products");
                // DatabaseUtils.dumpCursor(operations.getProducts());
                clearData();
                getCategories();
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void getCategories() {
        Cursor  c = operations.getCategories();
        categories.clear();
        if(c!=null){
            while (c.moveToNext()){
                categories.add(new Categories(c.getString(1),
                        c.getString(2),c.getString(3), c.getInt(4)));

            }
        }
    }

    private void clearData(){
        etName.setText("");
        etDescription.setText("");
        etNumber.setText("");
        categories2= null;
        categories2 = new Categories();
        categories2.setIdCategory("");
    }
}
