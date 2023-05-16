package br.com.etecia.appbuscacep_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCep;
    EditText txtCep;
    EditText lblCEP, txtEndereco, txtNumero, txtComplemento, txtBairro, txtCidade;

    Spinner spnEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCep = findViewById(R.id.txtCep);
        txtEndereco = findViewById(R.id.txtEndereco);
        txtNumero = findViewById(R.id.txtNumero);
        txtComplemento = findViewById(R.id.txtComplemento);
        txtBairro = findViewById(R.id.txtBairro);
        txtCidade = findViewById(R.id.txtCidade);
        spnEstado = findViewById(R.id.spnEstado);
        btnBuscarCep = findViewById(R.id.btnBuscaCep);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String endereco = txtCep.getText().toString().trim();

                try {
                    //preencher o cep no lblResposta do layout
                    CEP retorno = new HttpService(txtCep.getText().toString().trim()).execute().get();
                    lblCEP.setText(retorno.getCep().toString());
                    txtEndereco.setText(retorno.getEndereco().toString());
                    txtNumero.setText(retorno.getComplemento().toString());
                    txtComplemento.setText(retorno.getComplemento().toString());
                    txtBairro.setText(retorno.getBairro().toString());
                    txtCidade.setText(retorno.getUf().toString());


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}