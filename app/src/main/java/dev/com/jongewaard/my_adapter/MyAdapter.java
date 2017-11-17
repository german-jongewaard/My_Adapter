package dev.com.jongewaard.my_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by german on 16-11-17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> names;
    private int layout;
    private OnItemClickListener itemClickListener;


    //Constructor

    public MyAdapter(List<String> names, int layout, OnItemClickListener listener){
        this.names = names;
        this.layout = layout;
        this.itemClickListener = listener;
    }


    @Override   //este metodo nos obliga a devolver un ViewHolder y que usemos este patron de diseño
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos el layout y se lo pasamos al constructor del ViewHolder, donde manejaremos
        // toda la lógica como extraer los datos, referencias...  aqui tengo la Vista inflada!
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        //a mi ViewHolder la paso la lista inflada!!! (que seria v)
        ViewHolder vh = new ViewHolder(v);

        return vh; //devuelvo el ViewHolder inflado
    }


    @Override   //a este metodo se lo va a llamar tantas veces como elementos tengamos
    // Llamamos al método Bind del ViewHolder pasándole objeto y listener
    public void onBindViewHolder(ViewHolder holder, int position) {
    //este holder llama a su elemento bind y le pasa una lista! y espera un
    // listener, y le paso el listener que tengo global, que va a ser el que
    // se le va a pasar cuando instanciemos fuera (en el Main)
        holder.bind(names.get(position), itemClickListener);
    }


    @Override
    public int getItemCount() { return names.size(); }//el tamaño de la lista que tenemos


    public static class ViewHolder extends RecyclerView.ViewHolder{
        // Elementos UI a rellenar
        public TextView textViewName;

        public ViewHolder(View v){
            // Recibe la View completa. La pasa al constructor padre y enlazamos referencias UI
            // con nuestras propiedades ViewHolder declaradas justo arriba.
            super(v);
            this.textViewName = (TextView) v.findViewById(R.id.textViewName);
        }


        public void bind(final String name, final OnItemClickListener listener){
            // Procesamos los datos a renderizar
            this.textViewName.setText(name);
            // Definimos que por cada elemento de nuestro recycler view, tenemos un click listener
            // que se comporta de la siguiente manera...
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ... pasamos nuestro objeto modelo (este caso String) y posición
                    listener.onItemClick(name, getAdapterPosition());
                }
            });

        }
    }


    // Declaramos nuestra interfaz con el/los método/s a implementar
    public interface OnItemClickListener {

        void onItemClick(String name, int position);
    }

}
