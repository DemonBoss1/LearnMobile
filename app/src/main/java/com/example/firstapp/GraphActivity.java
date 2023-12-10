package com.example.firstapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;


public class GraphActivity extends AppCompatActivity {
    private static final int TEXT_SIZE = 40;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = buildIntent();
        //startActivity(intent);
    }

    public Intent buildIntent() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        try {
            int[] values = new int[]{25, 25, 25, 25};
            String[] bars = new String[]{"Bananas", "Kiwi", "Oranges", "Cream"};
            int[] colors = new int[]{Color.YELLOW, Color.GREEN, Color.RED, Color.WHITE};

            CategorySeries series = new CategorySeries("Pie Chart");
            DefaultRenderer dr = new DefaultRenderer();

            for (int v = 0; v < 4; v++) {
                series.add(bars[v], values[v]);
                SimpleSeriesRenderer r = new SimpleSeriesRenderer();
                r.setColor(colors[v]);
                dr.addSeriesRenderer(r);
            }
            dr.setZoomButtonsVisible(true);
            dr.setZoomEnabled(true);
            dr.setChartTitleTextSize(20);
            dr.setLegendTextSize(TEXT_SIZE);
            dr.setChartTitleTextSize(20);
            dr.setZoomButtonsVisible(false);
            dr.setLabelsTextSize(TEXT_SIZE);
            dr.setLegendTextSize(TEXT_SIZE);
            dr.setLabelsColor(Color.BLACK);
            intent = ChartFactory.getPieChartIntent(this, series, dr, "Fruit Salad");
        }catch (Exception e){

        }
        return intent;
    }

}