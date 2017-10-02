package estimator.recovery.com.customsplicerecoveryestimator;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;


public class Calculation extends BaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    // Array of VehicleType
    String VehicleType[] = {"--Vehicle Type--",
            "Large SUV",
            "Small SUV",
            "1/2 Ton Truck",
            "3/4 Ton Truck",
            "1 Ton Truck",
            "SxS or UTV",
            "Quad or ATV",
            "Jeep 2dr",
            "Jeep 4dr",
            "2wd Tractor",
            "Tractor Trailer 53'",
            "Box Truck 24'",
            "Quint Firetruck",
            "Large Car",
            "Small Car"};
    // Array of Weight
    String Weight[] = {"Weight",
            "7000",
            "5000",
            "6000",
            "7000",
            "9000",
            "2000",
            "1200",
            "5000",
            "5500",
            "10000",
            "85000",
            "26000",
            "40000",
            "6000",
            "4000"};

    // Array of SurfaceTypeResistance
    String SurfaceTypeResistance[] = {"--Surface Type | Resistance--",
            "Hard Surface | 4% of vehicle weight",
            "Grass | 15% of vehicle weight",
            "Gravel | 20% of vehicle weight",
            "Dry Sand | 25% of vehicle weight",
            "Dry Snow | 30% of vehicle weight",
            "Wet Snow | 40% of Vehicle Weight",
            "Clay Mud | 50% of vehicle weight" };

    // Array of SurfaceTypeResistancePer
    String SurfaceTypeResistancePer[] = {"Surface Type | Resistance",
            "4",
            "15",
            "20",
            "25",
            "30",
            "40",
            "50"};

    // Array of DepthResistance
    String DepthResistance[] = {"--Depth Resistance--",
            "Axle | 100% of vehicle weight",
            "Top of Tires | 200% of vehicle weight",
            "Hood | 300% of vehicle weight"};

    // Array of DepthResistancePer
    String DepthResistancePer[] = {"Depth Resistance",
            "100",
            "200",
            "300"};

    // Array of Incline
    String Incline[] = {"--Incline--",
            "Flat Ground",
            "5°|10%",
            "10°|20%",
            "15°|30%",
            "20°|40%",
            "25°|50%",
            "30°|60%",
            "35°|70%",
            "40°|80%",
            "45°|90%",
            "50°|100%"};

    // Array of InclinePer
    String InclinePer[] = {"Incline",
            "0",
            "10",
            "20",
            "30",
            "40",
            "50",
            "60",
            "70",
            "80",
            "90",
            "100"};

    private Spinner spnVehicleType, spnSurfaceType, spnDepth, spnIncline;
    private Button btnCalculate, btnResult, btnRegister;
    private RelativeLayout RLbtnJoinFacebook, RLbtnCheckOut;
    private EditText edtVahicleWeight;
    private String WeightForCal,SurfaceTypeResistancePerForCal,DepthResistancePerForCal,InclinePerForCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calculation);

        castingOfControls();

        events();

        generalTask();


    }

    private void generalTask() {
        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, VehicleType);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spnVehicleType.setAdapter(spinnerArrayAdapter);

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SurfaceTypeResistance);
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spnSurfaceType.setAdapter(spinnerArrayAdapter1);

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DepthResistance);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spnDepth.setAdapter(spinnerArrayAdapter2);

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Incline);
        spinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spnIncline.setAdapter(spinnerArrayAdapter3);

    }

    private void events() {

        // spinner item selected events
        spnVehicleType.setOnItemSelectedListener(this);
        spnSurfaceType.setOnItemSelectedListener(this);
        spnDepth.setOnItemSelectedListener(this);
        spnIncline.setOnItemSelectedListener(this);

        // button click events
        btnCalculate.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        RLbtnJoinFacebook.setOnClickListener(this);
        RLbtnCheckOut.setOnClickListener(this);


    }

    private void castingOfControls() {

        // Selection of the spinner
        spnVehicleType = (Spinner) findViewById(R.id.spnVehicleType);
        spnSurfaceType = (Spinner) findViewById(R.id.spnSurfaceType);
        spnDepth = (Spinner) findViewById(R.id.spnDepth);
        spnIncline = (Spinner) findViewById(R.id.spnIncline);

        // Button
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnResult = (Button) findViewById(R.id.btnResult);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        // Relative layout
        RLbtnJoinFacebook = (RelativeLayout) findViewById(R.id.RLbtnJoinFacebook);
        RLbtnCheckOut = (RelativeLayout) findViewById(R.id.RLbtnCheckOut);

        // Edit text
        edtVahicleWeight = (EditText) findViewById(R.id.edtVahicleWeight);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spnVehicleType:
                if (position == 0) {
                    edtVahicleWeight.setText("");
                }

                if (position != 0) {
                    edtVahicleWeight.setText(Weight[position]+" lbs");
                    WeightForCal = Weight[position];
                }

                break;
            case R.id.spnSurfaceType:
                if (position != 0) {
                    SurfaceTypeResistancePerForCal = SurfaceTypeResistancePer[position];
                }
                break;
            case R.id.spnDepth:
                if (position != 0) {
                    DepthResistancePerForCal = DepthResistancePer[position];
                }
                break;
            case R.id.spnIncline:
                if (position != 0) {
                    InclinePerForCal = InclinePer[position];
                }
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCalculate:
                if (ValidatAllControl()) {
                    int ATempRes = (Integer.parseInt(WeightForCal)*Integer.parseInt(SurfaceTypeResistancePerForCal))/100;
                    int BTempRes = (Integer.parseInt(WeightForCal)*Integer.parseInt(DepthResistancePerForCal))/100;
                    int CTempRes = (Integer.parseInt(WeightForCal)*Integer.parseInt(InclinePerForCal))/100;
                    int ResultTemp = ATempRes+BTempRes+CTempRes;
                    btnResult.setText(String.valueOf(ResultTemp) + " lbs");
                }
                break;
            case R.id.btnRegister:
                Intent _CommonWebview = new Intent(Calculation.this,
                        CommonWebview.class);
                _CommonWebview.putExtra("linkToOpen","http://www.customsplice.com/v/subscription/");
                startActivity(_CommonWebview);
                Calculation.this.finish();
                break;
            case R.id.RLbtnCheckOut:

//                Intent intent = new Intent(this, PDFView.class);
//                intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, "file:///android_asset/conversion.pdf");
//                startActivity(intent);
                Intent _CommonWebview1 = new Intent(Calculation.this,
                        CommonWebview.class);
                _CommonWebview1.putExtra("linkToOpen", "https://docs.google.com/gview?embedded=true&url=http://www.customsplice.com/v/pdfToShowInApp/conversion.pdf");
                startActivity(_CommonWebview1);
                Calculation.this.finish();
                break;
            case R.id.RLbtnJoinFacebook:
                Intent _CommonWebview2 = new Intent(Calculation.this,
                        CommonWebview.class);
                _CommonWebview2.putExtra("linkToOpen", "https://www.facebook.com/customsplice");
                startActivity(_CommonWebview2);
                Calculation.this.finish();
                break;
        }

    }


    private boolean ValidatAllControl() {

        boolean validate = false;

        if (spnVehicleType.getSelectedItemPosition()  == 0) {

            showDialogOneButton(
                    Calculation.this,
                    "Please select Vehicle Type !",
                    "Alert",
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            Dialog dialog = (Dialog) v.getTag();
                            dialog.dismiss();

                        }
                    });

        } else if (spnSurfaceType.getSelectedItemPosition()  == 0) {

            showDialogOneButton(
                    Calculation.this,
                    "Please select Surface Type | Resistance !",
                    "Alert",
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            Dialog dialog = (Dialog) v.getTag();
                            dialog.dismiss();

                        }
                    });


        } else if (spnDepth.getSelectedItemPosition()  == 0) {

            showDialogOneButton(
                    Calculation.this,
                    "Please select Depth Resistance !",
                    "Alert",
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            Dialog dialog = (Dialog) v.getTag();
                            dialog.dismiss();

                        }
                    });


        } else if (spnIncline.getSelectedItemPosition()  == 0) {

            showDialogOneButton(
                    Calculation.this,
                    "Please select Incline Type !",
                    "Alert",
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            Dialog dialog = (Dialog) v.getTag();
                            dialog.dismiss();

                        }
                    });

        }else
            validate = true;
        return validate;

    }
}
