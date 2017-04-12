package pass.com.passsecurity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mohitbadwal.rxconnect.RxConnect;
import pass.com.passsecurity.Constants.Constants;
import pass.com.passsecurity.Utils.JSONParser;

public class Display extends AppCompatActivity {

    private ImageView scan_id2;
    private ImageView DL_IMAGE_VIEW;
    private ImageView RC_BOOK_IMAGE_VIEW;
    private ImageView INSURANCE_IMAGE_VIEW;
    private TextView Name2;
    private TextView Address2;
    private TextView Mobile2;
    private TextView CarNumber;
    private TextView DriverName;
    private TextView Dateofbirth2;
    private TextView Dateofjourney2;
    private TextView DESTINATION;
    private TextView ID_No2;
    private TextView Purpose2;
    private TextView DRIVER_LICENSE;
    private TextView VEHICLE_MODEL;
    private TextView Scan_id2;
    private ImageView Profile2;
    private  String pass;
    private TextView ID_Sources;
    private TextView Application_status2;
   private TextView GATE_NUMBER;
    private int WIDTH_SCREEN;
    private String APPLICATION_STATUS;
    private int HEIGHT_SCREEN;
    private boolean Check;
    private RxConnect rxConnect;
    private Button RETRY_BUTTON;
    private String PassDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        DRIVER_LICENSE=(TextView) findViewById(R.id.driver_license_id);
        VEHICLE_MODEL=(TextView) findViewById(R.id.vehicle_model_id);
        DL_IMAGE_VIEW=(ImageView) findViewById(R.id.DL_PIC);
        INSURANCE_IMAGE_VIEW=(ImageView) findViewById(R.id.INSURANCE_PIC);
        RC_BOOK_IMAGE_VIEW=(ImageView) findViewById(R.id.RC_BOOK_PIC);
        RETRY_BUTTON=(Button)findViewById(R.id.RETRY);
        rxConnect=new RxConnect(this);
        rxConnect.setCachingEnabled(false);
        GATE_NUMBER=(TextView) findViewById(R.id.GATE);
        DESTINATION=(TextView)findViewById(R.id.DESTINATION);
        CarNumber=(TextView)findViewById(R.id.car_num);
        DriverName=(TextView)findViewById(R.id.driver_name);
        scan_id2=(ImageView)findViewById(R.id.SCAN_PIC) ;
        Name2=(TextView)findViewById(R.id.SCAN_NAME);
        Address2=(TextView)findViewById(R.id.SCAN_ADDRESS);
        Mobile2=(TextView)findViewById(R.id.SCAN_MOBILE);
        ID_No2=(TextView)findViewById(R.id.SCAN_ID);
        ID_Sources=(TextView)findViewById(R.id.ID_Source);

        Dateofbirth2=(TextView)findViewById(R.id.SCAN_DOB);
        Dateofjourney2=(TextView)findViewById(R.id.SCAN_DOJ);
        Purpose2=(TextView)findViewById(R.id.SCAN_REASON);
        Profile2=(ImageView)findViewById(R.id.SCAN_PROFILE);
        Application_status2=(TextView)findViewById(R.id.SCAN_STATUS);
        Intent i=getIntent();
        Bundle extras=i.getExtras();
        pass=extras.getString("Pass");
        PassDetails=extras.getString("PassDetails");


        WindowManager wm = (WindowManager) Display.this.getSystemService(Context.WINDOW_SERVICE);
        android.view.Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        WIDTH_SCREEN = metrics.widthPixels;
        HEIGHT_SCREEN = metrics.heightPixels;


        android.view.ViewGroup.LayoutParams layoutParams = Profile2.getLayoutParams();
        layoutParams.width = WIDTH_SCREEN/2;
        layoutParams.height = HEIGHT_SCREEN/3;
        Profile2.setLayoutParams(layoutParams);




        try {

            /**
             * {"response_status":"1",
             *  "msg":"sucess",
             *  "application_info":{"token_no":"token_17-04-1017:05:4411",
             *                       "applicant_name":"Dwijraj",
             *                       "applicant_address":"dhhf",
             *                       "place_visting":"konark",
             *                       "application_mobile":"8093679890",
             *                       "applicant_id_source":"Passport",
             *                       "applicant_id_no":"scheme",
             *                       "dob":"2017-04-10",
             *                       "purpose_visting":"Roam",
             *                       "Photo":"http:\/\/192.168.2.2\/pass3\/upload\/pic\/17:05:44187487.jpeg",
             *                       "Scan_id_photo":"http:\/\/192.168.2.2\/pass3\/upload\/pic\/17:05:4469025.jpeg",
             *                       "paid_status":"1","status":"1","date_journey":"2017-04-15"},
             * "vehicle":{"token_no":"token_17-04-1017:05:4411",
             *           // "vehicle_novehicle_no":"fhhdbn",
             *            //"driver_name":"disowned",
             *            "driver_licence":"chunks",
             *            "vehicle_mode":"cjjxjfnd",
             *            "vehicle_rc_card_scan":"http:\/\/192.168.2.2\/pass3\/upload\/vehicle\/rc_book\/17:23:5269025.jpeg",
             *            "vehicle_insurance_scan":"http:\/\/192.168.2.2\/pass3\/upload\/vehicle\/insurance\/17:23:52186826.jpeg",
             *            "driver_licence_scan":"http:\/\/192.168.2.2\/pass3\/upload\/vehicle\/driver_scan\/17:23:5218982.jpeg"}}

             *
             *
             */


            JSONObject jsonObject2=new JSONObject(PassDetails);

            JSONObject jsonObject=jsonObject2.getJSONObject("application_info");
            Log.v("Here","1");
            JSONObject jsonObject1=jsonObject2.getJSONObject("vehicle");
            Log.v("Here","1.1");
            String Vehicle_no=JSONParser.JSONValue(jsonObject1,"vehicle_novehicle_no");
            Log.v("Here","1.2");
            String Driver_Name=JSONParser.JSONValue(jsonObject1,"driver_name");
            Log.v("Here","1.3");
            String Driver_License=JSONParser.JSONValue(jsonObject1,"driver_licence");
            Log.v("Here","1.4");
            String Vehicle_Model=JSONParser.JSONValue(jsonObject1,"vehicle_mode");
            Log.v("Here","1.5");
            final String RC_LINK=JSONParser.JSONValue(jsonObject1,"vehicle_rc_card_scan").replaceAll("\"","");
            Log.v("Here","1.6");
            final String DL_LINK=JSONParser.JSONValue(jsonObject1,"driver_licence_scan").replaceAll("\"","");
            Log.v("Here","1.7");
            final String INSURANCE_LINK=JSONParser.JSONValue(jsonObject1,"vehicle_insurance_scan").replaceAll("\"","");
            Log.v("Here","1.8");
            String Name= JSONParser.JSONValue(jsonObject,"applicant_name");
            String Address= JSONParser.JSONValue(jsonObject,"applicant_address");
            String PlaceOfVisit= JSONParser.JSONValue(jsonObject,"place_visting");
            String Mobile= JSONParser.JSONValue(jsonObject,"application_mobile");
            String IDNumber= JSONParser.JSONValue(jsonObject,"applicant_id_no");
            String IDSource= JSONParser.JSONValue(jsonObject,"applicant_id_source");
            String DateOfBirth= JSONParser.JSONValue(jsonObject,"dob");
            String Purpose= JSONParser.JSONValue(jsonObject,"purpose_visting");
            String DateOfJourney= JSONParser.JSONValue(jsonObject,"date_journey");
            String Res= JSONParser.JSONValue(jsonObject,"Photo");
            final String Profile = Res.replaceAll("\"","");
            String ResId= JSONParser.JSONValue(jsonObject,"Scan_id_photo");
            final String ScanId=ResId.replaceAll("\"","");
            String ApplicationStatus=JSONParser.JSONValue(jsonObject,"paid_status");
            APPLICATION_STATUS=ApplicationStatus;
            DRIVER_LICENSE.setText(Driver_License);
            VEHICLE_MODEL.setText(Vehicle_Model);
            DriverName.setText(Driver_Name);
            CarNumber.setText(Vehicle_no);
            Name2.setText(Name);
            Address2.setText(Address);
            Mobile2.setText(Mobile);
            ID_No2.setText(IDNumber);
            Dateofbirth2.setText(DateOfBirth);
            Dateofjourney2.setText(DateOfJourney);
            Purpose2.setText(Purpose);
            Application_status2.setText(ApplicationStatus.toUpperCase());

            if(ApplicationStatus.equals("1"))
                RETRY_BUTTON.setEnabled(true);
            else
                RETRY_BUTTON.setEnabled(false);
            ID_Sources.setText(IDSource);
            DESTINATION.setText(PlaceOfVisit);


            Glide.with(getApplicationContext())
                    .load(Profile)
                    .into(Profile2);

            Profile2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Zoomed(Profile);

                }
            });


            Glide.with(getApplicationContext())
                    .load(ScanId)
                    .into(scan_id2);

            scan_id2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Zoomed(ScanId);

                }
            });

            Glide.with(getApplicationContext())
                    .load(RC_LINK)
                    .into(RC_BOOK_IMAGE_VIEW);

            RC_BOOK_IMAGE_VIEW.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                    Zoomed(RC_LINK);
                }
            });

            Glide.with(getApplicationContext())
                    .load(INSURANCE_LINK)
                    .into(INSURANCE_IMAGE_VIEW);
            INSURANCE_IMAGE_VIEW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Zoomed(INSURANCE_LINK);
                }
            });

            Glide.with(getApplicationContext())
                    .load(DL_LINK)
                    .into(DL_IMAGE_VIEW)  ;

            DL_IMAGE_VIEW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Zoomed(DL_LINK);
                }
            });



            RETRY_BUTTON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeApplicationStatus();
                }
            });



            
            

        }catch (JSONException e)
        {

        }




    }
    public void Zoomed(String URL)
    {
        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.zoomed);

        ImageView Image=(ImageView) dialog.findViewById(R.id.ZoomedInImage);

        Glide.with(Display.this)
                .load(URL)
                .into(Image);

        dialog.show();

    }

    public void changeApplicationStatus()
    {
        rxConnect.setParam("token_id",pass);
        rxConnect.setParam("security_number",
                getSharedPreferences(Constants.USER,MODE_PRIVATE).getString(Constants.SHARED_PREF_KEY,"DEFAULT"));
        rxConnect.setParam("application_status",APPLICATION_STATUS);
        rxConnect.setParam("security_name",
                getSharedPreferences(Constants.USER,MODE_PRIVATE).getString(Constants.SHARED_PREF_KEY_NAME,"DEFAULT"));
        rxConnect.setParam("check_time",new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date(System.currentTimeMillis())));
        rxConnect.execute(Constants.PASS_STATUS_CHANGE_URL, RxConnect.POST, new RxConnect.RxResultHelper() {
            @Override
            public void onResult(String result) {

                try {
                    JSONObject jsonObject=new JSONObject(result);
                   if(jsonObject.getString("response_status").equals("1"))
                   {
                       RETRY_BUTTON.setEnabled(false);
                       Toast.makeText(getApplicationContext(),"Application status updated",Toast.LENGTH_SHORT).show();

                   }
                   else if (jsonObject.getString("response_status").equals("2"))
                   {
                       Toast.makeText(getApplicationContext(),"Status couldn't be changed",Toast.LENGTH_SHORT).show();
                   }


                }catch (Exception e)
                {

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
}
