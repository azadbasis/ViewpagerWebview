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
public class CNNFragment extends Fragment {


    public CNNFragment() {
        // Required empty public constructor
    }


    private  View view;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_cnn, container, false);
        webView = (WebView)view.findViewById(R.id.cnnWv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://edition.cnn.com/");
        return view;
    }

}
