package cl.ucn.disc.dsm.javalos.directorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jose Avalos Guzman
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Funcionario Adapter
     */
    protected FuncionarioAdapter funcionarioAdapter;

    /**
     * @param savedInstanceState the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the List (RecyclerView)
        final RecyclerView recyclerView = findViewById(R.id.am_rv_funcionarios);
        //The Type of layout of RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        // Build the Adapter
        this.funcionarioAdapter = new FuncionarioAdapter();
        // Union of Adapter + RecyclerView
        recyclerView.setAdapter(this.funcionarioAdapter);


        // Build the ViewModel
        FuncionarioViewModel funcionarioViewModel = ViewModelProvider  // Provider
                .AndroidViewModelFactory    // The Factory
                .getInstance(this.getApplication())     // The Singleton instance of Factory
                .create(FuncionarioViewModel.class);    // Call the Constructor of FuncionarioViewModel

        funcionarioViewModel.getFuncionarios().observe(this,funcionarios -> {
            // Set the funcionarios (from view model)
            funcionarioAdapter.setFuncionarios(funcionarios);
            // Refresh the Recycler (ListView)
            funcionarioAdapter.notifyDataSetChanged();

         });

    }
}