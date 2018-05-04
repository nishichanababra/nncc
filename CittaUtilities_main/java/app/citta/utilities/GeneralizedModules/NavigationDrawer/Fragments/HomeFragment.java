package app.citta.utilities.GeneralizedModules.NavigationDrawer.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;
import java.util.List;

import app.citta.utilities.R;


public class HomeFragment extends Fragment {
    public List<String> imageURLs = new ArrayList<String>();
    ViewFlipper homeflipper;
    Animation slide_in_right, slide_out_left;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        slide_in_right = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right);
        slide_out_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left);
        homeflipper = (ViewFlipper)rootView.findViewById(R.id.homeFliper);

        imageURLs.add("http://transformlearning.avila.edu/ctl/wp-content/uploads/sites/20/2015/09/Guidelines_for_Open_Educational_Resources_OER_in_Higher_Education_cover.jpg");
        imageURLs.add("http://www.vpul.upenn.edu/eap/eoc/files/EOC-Application_FINAL.png");
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        for (String url : imageURLs) {
            ImageView image = new ImageView(getActivity());
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            UrlImageViewHelper.setUseBitmapScaling(false);
            UrlImageViewHelper.setUrlDrawable(image, url, R.mipmap.progressbar);
            homeflipper.addView(image);
        }

        homeflipper.showNext();
        homeflipper.setAutoStart(true);
        homeflipper.setFlipInterval(10000);
        homeflipper.startFlipping();

        homeflipper.setInAnimation(slide_in_right);
        homeflipper.setOutAnimation(slide_out_left);
        homeflipper.showPrevious();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}