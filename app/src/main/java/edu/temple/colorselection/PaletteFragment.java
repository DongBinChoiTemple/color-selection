package edu.temple.colorselection;


import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaletteFragment extends Fragment {

    boolean twoPaneParent;

    public OnFragmentInteractionListener activity;

    public PaletteFragment() {
        // Required empty public constructor
    }

    public void onAttach(Context activity){
        super.onAttach(activity);
        this.activity = (OnFragmentInteractionListener) activity;
    }

    public void onDetach(){
        super.onDetach();
        this.activity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_palette, container, false);
        ListView lv = (ListView) v.findViewById(R.id.listView);
        String[] colors = {"white", "light gray", "dark gray", "red", "yellow", "green",
                "cyan", "blue", "magenta"};
        Adapter a = new ColorAdapter(colors);
        lv.setAdapter((ListAdapter)a);
        return v;
    }

    public class ColorAdapter extends BaseAdapter {
        String[] colors;
        public ColorAdapter (String[] array){
            colors = array;
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            super.unregisterDataSetObserver(observer);
        }

        @Override
        public View getView(int position, View oldView, ViewGroup parent) {

            TextView tv = new TextView((Context) activity);

            LinearLayout layout = new LinearLayout((Context) activity);

            layout.setOrientation(LinearLayout.VERTICAL);

            final String color = colors[position];
            if (color != null) {
                ColorSelector cs = new TenColorSelector();
                tv.setBackgroundColor(cs.stringToColor(color));
                String[] colorNames = getResources().getStringArray(R.array.colors_array);
                tv.setText(colorNames[position]);
                tv.setTextSize(30);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.onFragmentInteraction(color);
                    }
                });

                layout.addView(tv);
            }

            return layout;
        }
        @Override
        public String getItem(int position){
            return colors[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public int getCount(){
            return colors.length;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String color);
    }
}
