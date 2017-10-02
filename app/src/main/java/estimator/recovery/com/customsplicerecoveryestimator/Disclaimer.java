package estimator.recovery.com.customsplicerecoveryestimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Disclaimer extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disclaimer);

        Button _btnLetsCalculate = (Button) findViewById(R.id.btnLetsCalculate);
        _btnLetsCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calculation = new Intent(Disclaimer.this, Calculation.class);
                startActivity(calculation);
                Disclaimer.this.finish();
            }
        });
    }
}
