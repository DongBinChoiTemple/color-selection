package edu.temple.colorselection;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class CanvasFragment extends Fragment {
    boolean twoPaneParent;
    FrameLayout layout;
    private static final String ARG_COLOR = "color";
    private String color = "white";

    public CanvasFragment() {
        // Required empty public constructor
    }

    public static CanvasFragment newInstance(String color) {
        CanvasFragment fragment = new CanvasFragment();
        Bundle args = new Bundle();
        fragment.color = color;
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_canvas, container, false);
        layout = (FrameLayout) v.findViewById(R.id.background);

        return v;
    }

    public void changeColor (String color) {
        ColorSelector cs = new TenColorSelector();
        layout.setBackgroundColor(cs.stringToColor(color));
    }
}
