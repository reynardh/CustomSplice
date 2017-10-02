package estimator.recovery.com.customsplicerecoveryestimator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class BaseActivity extends Activity {
    public static BaseActivity MContext;
    WeakReference<Activity> weakActivity;


    Context context;

    private Dialog dialog, custom_dialog;

    public void showDialogOneButton(Context _Context, String _Message,
                                    String _Title,
                                    OnClickListener _ButtonClickListner) {

        MContext = BaseActivity.this;

        this.weakActivity = new WeakReference<Activity>(MContext);

        if (weakActivity.get() != null && !weakActivity.get().isFinishing()) {
            if (dialog != null)
                dialog.dismiss();
            dialog = new Dialog(_Context);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_view);

            TextView text = (TextView) dialog.findViewById(R.id.planDetail);
            text.setText(_Message);


            TextView planinformation = (TextView) dialog
                    .findViewById(R.id.txtplaninformation);
            planinformation.setText(_Title);

            TextView dialogButton = (TextView) dialog.findViewById(R.id.txtOk);
            dialogButton.setTag(dialog);


            if (_ButtonClickListner == null)
                dialogButton.setOnClickListener(dialogListnerForDismiss);
            else
                dialogButton.setOnClickListener(_ButtonClickListner);

            dialog.setCancelable(false);
            dialog.show();
        }
    }

    public OnClickListener dialogListnerForFinish = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Dialog dialog = (Dialog) v.getTag();
            dialog.dismiss();
            finish();
        }
    };
    public OnClickListener dialogListnerForDismiss = new OnClickListener() {

        public void onClick(View v) {
            Dialog dialog = (Dialog) v.getTag();
            dialog.dismiss();
        }
    };


}
