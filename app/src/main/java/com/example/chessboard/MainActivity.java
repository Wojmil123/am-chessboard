package com.example.chessboard;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Panel panel;
    Paint lightSquare = new Paint();
    Paint darkSquare = new Paint();

    private static final String LIGHT_SQUARE_COLOR = "light_square_color";
    private static final String DARK_SQUARE_COLOR = "dark_square_color";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(panel = new Panel(this));
        if (savedInstanceState != null) {
            lightSquare.setColor(savedInstanceState.getInt(LIGHT_SQUARE_COLOR));
            darkSquare.setColor(savedInstanceState.getInt(DARK_SQUARE_COLOR));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LIGHT_SQUARE_COLOR, lightSquare.getColor());
        outState.putInt(DARK_SQUARE_COLOR, darkSquare.getColor());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.author) {
            Toast toast = Toast.makeText(getApplicationContext(), "Twórca: Jerzy Kisielewski", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.BOTTOM, 0, 0);
            toast.show();
            return true;
        } else if (menuItem.getItemId() == R.id.exit) {
            finish();
            return true;
        } else if (menuItem.getItemId() == R.id.black_and_white) {
            lightSquare.setColor(Color.WHITE);
            darkSquare.setColor(Color.BLACK);
            panel.postInvalidate();
            return true;
        } else if (menuItem.getItemId() == R.id.red_and_yellow) {
            lightSquare.setColor(Color.YELLOW);
            darkSquare.setColor(Color.RED);
            panel.postInvalidate();
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }


    public class Panel extends View {

        public Panel(Context context) {
            super(context);
            lightSquare.setColor(Color.WHITE);
            darkSquare.setColor(Color.BLACK);
        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.GRAY);
            float width = panel.getWidth();
            float height = panel.getHeight();

            float calc = Math.min(width, height);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) {
                        canvas.drawRect(i * calc / 8, j * calc / 8, (i + 1) * calc / 8, (j + 1) * calc / 8, lightSquare);
                    } else {
                        canvas.drawRect(i * calc / 8, j * calc / 8, (i + 1) * calc / 8, (j + 1) * calc / 8, darkSquare);
                    }
                }
            }
        }
    }
}

