package pass.com.passsecurity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mohitbadwal.rxconnect.RxConnect;
import pass.com.passsecurity.Constants.Constants;
import pass.com.passsecurity.Utils.JSONParser;

public class sign_In_Activity extends AppCompatActivity {




    private Button Login,Scan;
    private TextView GaurdName,GaurdPhone;
    private EditText GatePassword;
    private RxConnect rxConnect;
    private TextView GATE_N_LOCATION_TEXT;
    private EditText MOBILE_ENTERED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in_);
        rxConnect=new RxConnect(this);
        rxConnect.setCachingEnabled(false);



        MOBILE_ENTERED=(EditText) findViewById(R.id.Mobile_Number);
        GATE_N_LOCATION_TEXT=(TextView) findViewById(R.id.GATE_AND_LOCATION);
        Login=(Button) findViewById(R.id.login);
        Scan=(Button) findViewById(R.id.ID_SCAN);

        GaurdName=(TextView) findViewById(R.id.GaurdName);
        GaurdPhone=(TextView) findViewById(R.id.PhoneNumber);


        GatePassword=(EditText) findViewById(R.id.GATE_PASSWORD);


        Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(sign_In_Activity.this).initiateScan();

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String GatePasswordVal=getSharedPreferences(Constants.USER,MODE_PRIVATE).getString(Constants.SHARED_PREF_KEY_PASSWORD,"DEFAULT");

                   Log.v("JSONResponse","here2"+GatePasswordVal);

                    String GATE_PASS_ENT=GatePassword.getText().toString().trim();

                    if(GATE_PASS_ENT.equals(GatePasswordVal))
                    {

                                            SharedPreferences.Editor Editor=getSharedPreferences(Constants.USER,MODE_PRIVATE).edit();
                                            Editor.putString(Constants.SHARED_PREF_KEY,GaurdPhone.getText().toString().trim());
                                            Editor.putString(Constants.SHARED_PREF_KEY_NAME,GaurdName.getText().toString().trim());
                                            Editor.commit();

                                            Intent i=new Intent(sign_In_Activity.this,MainActivity.class);
                                            finish();
                                            startActivity(i);




                                            Toast.makeText(getApplicationContext(),"Enter Mobile",Toast.LENGTH_SHORT).show();

                    }
            }



        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

     //   IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    //    if(result != null) {
    //        if(result.getContents() == null) {
                // Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
  //          } else {
                // Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                //String RESULT=result.getContents();
                String RESULT;
                if(MOBILE_ENTERED.getText().toString().isEmpty()) {
                     RESULT = "9040777073";

                }
                else
                {
                     RESULT = MOBILE_ENTERED.getText().toString().trim();
                }
                rxConnect.setParam("guard_mobile", RESULT);
                rxConnect.execute(Constants.GAURD_DETAILS_URL, RxConnect.POST, new RxConnect.RxResultHelper() {
                @Override
                public void onResult(String result) {


                    Log.v("Response", result);
                    try {

                        /**
                         *  {   "response_status":"1",
                         *      "msg":"Sucessfull",
                         *      "user_info":{"user_name":"Abc",
                         *                   "place_name":"konark",
                         *                   "gate_password":"1452",
                         *                   "gate_name":"konark check gate"}}

                                 Response Status 1- Success
                                 Response Status 2,3- Invalid
                                 Response Status 0- Blank


                         */


                        JSONObject jsonObject = new JSONObject(result);

                        Log.v("JSONResponse",jsonObject.toString()+"...");

                        if (jsonObject.getString("response_status").equals("1"))///*&&(jsonObject.getString("mobile").equals(REGISTERED_MOBILE_NUBER))||jsonObject.getString("mobile").equals(REGISTERED_MOBILE_NUBER)*/)
                        {

                            Log.v("JSONResponse","here1");

                            JSONObject jsonObject1=jsonObject.getJSONObject("user_info");
                            Log.v("JSONResponse","here1.1");
                            String PASSWORD_ASSIGNED = JSONParser.JSONValue(jsonObject1, "gate_password");

                            Log.v("JSONResponse","here1.2");
                            Toast.makeText(getApplicationContext(),"Password"+PASSWORD_ASSIGNED,Toast.LENGTH_LONG).show();

                            Log.v("JSONResponse","here1.3");
                            GaurdName.setText(JSONParser.JSONValue(jsonObject1, "user_name"));
                            Log.v("JSONResponse","here1.4");
                            GaurdPhone.setText(JSONParser.JSONValue(jsonObject1, "place_name"));
                            Log.v("JSONResponse","here1.5");
                            GaurdPhone.append("\n"+JSONParser.JSONValue(jsonObject1, "gate_name"));
                            Log.v("JSONResponse","here1.6");
                            SharedPreferences.Editor Editor = getSharedPreferences(Constants.USER, MODE_PRIVATE).edit();
                            Log.v("JSONResponse","here1.7");
                            Editor.putString(Constants.SHARED_PREF_KEY_PASSWORD, PASSWORD_ASSIGNED).commit();
                            Log.v("JSONResponse","here1.8");


                            Login.setEnabled(true);

                        } else if (jsonObject.getString("response_status").equals("3") || jsonObject.getString("response_status").equals("2")) {


                             Log.v("JSONResponse","here2");
                             Toast.makeText(getApplicationContext(), "Inavalid  Details", Toast.LENGTH_SHORT).show();
                        } else {

                             Log.v("JSONResponse","here3");
                             Toast.makeText(getApplicationContext(), "Fields left blank", Toast.LENGTH_SHORT).show();


                        }

                } catch (JSONException e) {

                }
            }

            @Override
            public void onNoResult() {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
            }
      //  } else {

      //  }
  //  }
}
