package azhar.itemselection.nanosoft.viewpagerdemo001;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoogleFragment extends Fragment {


    public GoogleFragment() {
    }


    private View view;
    private WebView webView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_google, container, false);
        webView = (WebView)view.findViewById(R.id.googleWv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.google.com");
        return view;
    }

}
