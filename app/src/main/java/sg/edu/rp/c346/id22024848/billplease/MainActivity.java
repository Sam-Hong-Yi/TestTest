package sg.edu.rp.c346.id22024848.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView twAmount;
    EditText etAmount;
    TextView twPax;
    EditText etPax;
    ToggleButton tbtnSvs;
    ToggleButton tbtnGst;
    TextView twDiscount;
    EditText etDiscount;
    RadioButton rbtnCash;
    RadioButton rbtnPayNow;
    Button btnSplit;
    Button btnReset;
    TextView twBill;
    TextView twEach;
    RadioGroup rgPayment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twAmount=findViewById(R.id.textViewAmount);
        etAmount=findViewById(R.id.editTextAmount);
        twPax=findViewById(R.id.textViewPax);
        etPax=findViewById(R.id.editTextPax);
        tbtnSvs=findViewById(R.id.toggleButtonSvs);
        tbtnGst=findViewById(R.id.toggleButtonGst);
        twDiscount=findViewById(R.id.textViewDiscount);
        etDiscount=findViewById(R.id.editTextDiscount);
        rbtnCash=findViewById(R.id.radioButtonCash);
        rbtnPayNow=findViewById(R.id.radioButtonPayNow);
        btnSplit=findViewById(R.id.buttonSplit);
        btnReset=findViewById(R.id.buttonReset);
        twBill=findViewById(R.id.textViewBill);
        twEach=findViewById(R.id.textViewEach);
        rgPayment=findViewById(R.id.radioGroupPayment);





        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                boolean serviceCharge=tbtnSvs.isChecked();
                boolean gstCharge=tbtnGst.isChecked();
                double totalCharge=0;
                double amount=Double.parseDouble(etAmount.getText().toString());
                int pax= Integer.parseInt(etPax.getText().toString());
                if (serviceCharge==true && gstCharge==false){
                    totalCharge=1.1;
                }
                else if (gstCharge==true && serviceCharge==false){
                    totalCharge=1.07;
                }
                else if(gstCharge==true && serviceCharge==true){
                    totalCharge=1.17;
                }
                else if (gstCharge==false && serviceCharge==false){
                    totalCharge=1;
                }
                double discount=Double.parseDouble(etDiscount.getText().toString());
                double discountPerc=1-discount/100;
                String totalBill=String.format("%.2f", (amount*discountPerc)*totalCharge);
                twBill.setText("Total Bill: "+(totalBill));

                int method=rgPayment.getCheckedRadioButtonId();
                if (method== R.id.radioButtonPayNow){
                    twEach.setText("Each Pays: "+((Double.parseDouble(totalBill))/pax)+" via PayNow to 00000000");
                }
                else{
                    twEach.setText("Each Pays: "+((Double.parseDouble(totalBill))/pax));
                }


            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                tbtnSvs.setChecked(false);
                tbtnGst.setChecked(false);
                etAmount.setText("");
                etPax.setText("");
                etDiscount.setText("");
                rbtnCash.setChecked(true);
                twBill.setText("Total Bill: ");
                twEach.setText("Each Pays: ");


            }
        });


    }
}