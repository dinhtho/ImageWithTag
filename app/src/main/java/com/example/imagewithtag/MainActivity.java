package com.example.imagewithtag;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ZoomableImageView zoomableImageView = findViewById(R.id.ziv);
        TextView tvTag = findViewById(R.id.tv_tag);

        float xTag = 200;
        float yTag = 200;

        zoomableImageView.setMatrixChangedListener((matrix, fitScale) -> {
            PointF pointF = mapPoint(matrix, new PointF(xTag / fitScale, yTag / fitScale));
            tvTag.setX(pointF.x);
            tvTag.setY(pointF.y);
        });
    }

    private PointF mapPoint(Matrix matrix, PointF point) {
        float[] src = {point.x, point.y};
        float[] dst = new float[2];
        matrix.mapPoints(dst, src);

        return new PointF(dst[0], dst[1]);
    }
}
