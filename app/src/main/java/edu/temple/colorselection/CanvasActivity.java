package edu.temple.colorselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CanvasActivity extends AppCompatActivity {
    CanvasFragment canvasFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        canvasFragment = new CanvasFragment();
        canvasFragment.setArguments(getIntent().getExtras());

        getFragmentManager().beginTransaction()
                .add(android.R.id.content, canvasFragment).commit();

        canvasFragment.changeColor(getIntent().getStringExtra("color"));
    }
}
