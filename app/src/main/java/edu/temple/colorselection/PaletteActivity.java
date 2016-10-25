package edu.temple.colorselection;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PaletteActivity extends AppCompatActivity
        implements PaletteFragment.OnFragmentInteractionListener {
    String color;
    boolean twoPanes;
    PaletteFragment paletteFragment;
    CanvasFragment canvasFragment;

    @Override
    protected void onSaveInstanceState (Bundle outState){
        outState.putString("color", color);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        paletteFragment = new PaletteFragment();

        //  Determine if only one or two panes are visible
        twoPanes = (findViewById(R.id.fragment_canvas) != null);

        canvasFragment = new CanvasFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_palette, paletteFragment);
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
        if (savedInstanceState != null) {
            color = savedInstanceState.getString("color");
            if (twoPanes) {
                getFragmentManager().beginTransaction()
                        .add(R.id.fragment_canvas, canvasFragment)
                        .commit();
            } else {
                getFragmentManager().executePendingTransactions();
            }
        }
        else {
            //  Load palette fragment by default
            if (twoPanes) {
                getFragmentManager().beginTransaction()
                        .add(R.id.fragment_canvas, canvasFragment)
                        .commit();
            }
        }
    }

    protected void onResume(){
        getFragmentManager().executePendingTransactions();
        if (color != null) {
            if (twoPanes)
                canvasFragment.changeColor(color);
        }
        super.onResume();
    }

    @Override
    public void onFragmentInteraction(String color) {
        doTransition(color);
    }


    private void doTransition(String color){
        this.color = color;
        if (twoPanes) {
            canvasFragment.changeColor(color);
        } else {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_palette, canvasFragment)
                    .addToBackStack(null)
                    .commit();
            getFragmentManager().executePendingTransactions();
            canvasFragment.changeColor(color);
        }
    }
}