package music.com.br.musicgame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import music.com.br.musicgame.R;


/**
 * Created by mtsodf on 13/02/16.
 */
public class GuitarView extends View {

    private ShapeDrawable mDrawable;

    float radius = 20;
    float x = 40;
    float y = 40;


    public GuitarView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        setBackgroundResource(R.drawable.guitar_brace);


    }



    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x,y,radius,paint);
    }

    public void setGuitarString(int num){
        y = num * 10 + 10;
    }

    public void setCase(int num){
        x = num*20+10;
    }



}
