package dev.com.jongewaard.my_adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declarando mi recycler view, lo primero la lista de nombres con la que voy a trabajar
    private List<String> names;

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //aqui INSTANCIO la lista
        names = this.getAllNames();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(this);

        //tambien el adaptador
        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {

                Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();

            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);
    }

    private List<String> getAllNames(){
            return new ArrayList<String>(){{
                add("Germán");
                add("Miriam");
                add("Bebé");
                add("María");
                add("Guido");
                add("Pérez");
                add("Jongewaard");
                add("Vignolo");
                add("Niñow");

            }};

    }

}
