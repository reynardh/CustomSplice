package estimator.recovery.com.customsplicerecoveryestimator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import java.lang.reflect.InvocationTargetException;

public class CommonWebview extends BaseActivity {

	WebView _webview;
	String _webViewUrl;
	ToggleButton _imgNavigationMenu;
	private ProgressBar _commonProgress;
	private float scale;


	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.commonwebview);

		CastingOfControls();

		GeneralTask();

	}

	private void CastingOfControls() {
		_webview = (WebView) findViewById(R.id.webView);
		scale = 100 * _webview.getScale();

		_commonProgress = (ProgressBar) findViewById(R.id.commonProgress);

		_commonProgress.getIndeterminateDrawable().setColorFilter(0xFFE71E73,
				android.graphics.PorterDuff.Mode.MULTIPLY);

	}

	private void GeneralTask() {

		
		_webViewUrl = getIntent().getStringExtra("linkToOpen").toString();
		_webview.setWebViewClient(new MyWebviewClient());
		_webview.getSettings().setLoadsImagesAutomatically(true);
		_webview.getSettings().setJavaScriptEnabled(true);
		_webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		_webview.getSettings().setPluginState(PluginState.ON);
		WebSettings webSettings = _webview.getSettings();
		webSettings.setSupportZoom(true);
		_webview.setInitialScale(1);
//		_webview.setInitialScale((int) scale);
		webSettings.setBuiltInZoomControls(true);
		_webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		_webview.getSettings().setBuiltInZoomControls(true);
		_webview.getSettings().setDisplayZoomControls(true);

		
		//for webpage display in full screen
		_webview.getSettings().setLoadWithOverviewMode(true);
		_webview.getSettings().setUseWideViewPort(true);
		
		//extra added start for alert display
		_webview.setWebChromeClient(new WebChromeClient() {
			  @Override
		        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
		                    return super.onJsAlert(view, url, message, result);
		           }
		   });
		_webview.getSettings().setSupportZoom(true);
		_webview.getSettings().setBuiltInZoomControls(true);
		//extra added end
		_webview.loadUrl(_webViewUrl);
	}

	private class MyWebviewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			_commonProgress.setVisibility(View.VISIBLE);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			_commonProgress.setVisibility(View.GONE);
		}
	}


	@Override
	public void onPause() {
		super.onPause();

		try {
			Class.forName("android.webkit.WebView")
					.getMethod("onPause", (Class[]) null)
					.invoke(_webview, (Object[]) null);

		} catch (ClassNotFoundException cnfe) {
		} catch (NoSuchMethodException nsme) {
		} catch (InvocationTargetException ite) {
		} catch (IllegalAccessException iae) {
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent _Calculation = new Intent(CommonWebview.this,
				Calculation.class);
		startActivity(_Calculation);
		CommonWebview.this.finish();
	}
}
