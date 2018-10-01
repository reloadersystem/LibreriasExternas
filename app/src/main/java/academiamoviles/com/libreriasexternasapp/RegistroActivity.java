package academiamoviles.com.libreriasexternasapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroActivity extends AppCompatActivity implements Validator.ValidationListener {

    @Bind(R.id.edt_nombres)
    @Required(order = 1, message = "Campo obligatorio")
    EditText edt_nombres;

    @Bind(R.id.edt_dni)
    @Required(order = 2, message = "Campo obligatorio")
    @NumberRule(order = 3, message = "Dni es númerico", type = NumberRule.NumberType.LONG)
    @TextRule(order = 4, minLength = 8, maxLength = 8, message = "Dni debe tener  8 digitos")
    EditText edt_dni;

    @Bind(R.id.edt_correo)
    @Required(order = 5, message = "Campo obligatorio")
    @Email(order = 6, message = "Debe ingresar un formato apropiado")
    EditText edt_correo;

    @Bind(R.id.edt_pass)
    @Required(order = 7, message = "Campo obligatorio")
    @Password(order = 8)
    EditText edt_pass;

    @Bind(R.id.edt_repeat_pass)
    @Required(order = 9, message = "Campo obligatorio")
    @ConfirmPassword(order = 10, message = "No coincide")
    EditText edt_repeat_pass;

    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ButterKnife.bind(this);


        validator = new Validator(this);
        validator.setValidationListener(this);
    }



    @Override
    public void onValidationSucceeded() {
        Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.btn_registrar_usuario)
    public void registrarUsuario(){
        validator.validate();
    }
}
