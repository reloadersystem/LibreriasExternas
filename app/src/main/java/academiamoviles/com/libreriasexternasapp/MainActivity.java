package academiamoviles.com.libreriasexternasapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Required;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {


    @Bind(R.id.tv_titulo)
    TextView tv_titulo;

    @Bind(R.id.edt_usuario)
    @Required(order = 1, message = "Campo obligatorio")
    EditText edt_usuario;

    @Bind(R.id.edt_contrasenia)
    @Required(order = 2, message = "Campo obligatorio")
    EditText edt_contrasenia;

    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        importarFuente();

        edt_contrasenia.setTypeface(Typeface.DEFAULT);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void importarFuente() {

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/abc.ttf");
        tv_titulo.setTypeface(face);
    }

    @OnClick(R.id.btn_iniciar)
    public void autenticar() {

        validator.validate();
    }

    @OnClick(R.id.btn_registrarme)
    public void registrarme() {

        irARegistro();
    }

    @Override
    public void onValidationSucceeded() {

        Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
    }

    public void irARegistro(){

        Intent i = new Intent(this,RegistroActivity.class);
        startActivity(i);
    }


    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        final String failureMessage = failedRule.getFailureMessage();
        if (failedView instanceof EditText) {
                EditText failed = (EditText) failedView;
                failed.requestFocus();
                failed.setError(failureMessage);
        } else {
                Toast.makeText(getApplicationContext(), failureMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
