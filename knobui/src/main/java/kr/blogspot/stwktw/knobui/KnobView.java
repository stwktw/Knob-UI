package kr.blogspot.stwktw.knobui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by stwktw on 12/11/15.
 */
public class KnobView extends BaseView implements View.OnTouchListener{

    private float userKnobValue = 0.5f;
    private float touchX;
    private float touchY;
    private String title = "Knob";

    public KnobView(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public KnobView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public KnobView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final float cWidth = dipToPx(120);;
        final float cHeight = dipToPx(120);;

        final float paddingDip = 20;
        final float textSizeDip = 15;

        final float paddingPx = dipToPx(paddingDip);
        final float textSizePx = dipToPx(textSizeDip);

        final float leftPx = 0 + paddingPx;
        final float topPx = 0 + paddingPx;
        final float rightPx = cWidth - paddingPx;
        final float bottomPx = cHeight - paddingPx;

        final String text = title;

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;

        Matrix matrix = new Matrix();
        matrix.preScale((float) getWidth() / cWidth, (float) getHeight() / cHeight, centerX, centerY);

        Path knobBaseShadow = new Path();

        final float knobBaseShadowRadiDip = pxToDip(cWidth/4);
        final float knobBaseShadowRadiPx = dipToPx(knobBaseShadowRadiDip);

        knobBaseShadow.addCircle(centerX, centerY, knobBaseShadowRadiPx, Path.Direction.CCW);

        Paint knobBaseShadowPaint = new Paint();
        knobBaseShadowPaint.setColor(Color.WHITE);
        knobBaseShadowPaint.setAntiAlias(true);
        knobBaseShadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        knobBaseShadowPaint.setShader(new RadialGradient(centerX, centerY, knobBaseShadowRadiPx, Color.argb(10, 0, 0, 0), Color.argb(32, 0, 0, 0), Shader.TileMode.CLAMP));

        knobBaseShadow.transform(matrix);

        canvas.drawPath(knobBaseShadow, knobBaseShadowPaint);

        Path knobBase = new Path();

        final float knobBaseRadiDip = pxToDip(cWidth/4)-5;
        final float knobBaseRadiPx = dipToPx(knobBaseRadiDip);

        knobBase.addCircle(centerX, centerY, knobBaseRadiPx, Path.Direction.CCW);

        Paint knobBasePaint = new Paint();
        knobBasePaint.setColor(Color.WHITE);
        knobBasePaint.setAntiAlias(true);
        knobBasePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        int knobBaseCircle[] = {Color.argb(255, 237, 238, 238), Color.argb(255, 192, 193, 193),
                Color.argb(255, 237, 238, 238),Color.argb(255, 118, 119, 122),
                Color.argb(255, 237, 238, 238), Color.argb(255, 192, 193, 193),
                Color.argb(255, 237, 238, 238),Color.argb(255, 118, 119, 122),
                Color.argb(255, 158, 156, 160), Color.argb(255, 237, 238, 238)
        };

        knobBasePaint.setShader(new SweepGradient(centerX, centerY, knobBaseCircle, null));

        knobBase.transform(matrix);

        canvas.drawPath(knobBase, knobBasePaint);

        Path knobBaseRef = new Path();

        final float knobBaseRefRadiDip = pxToDip(cWidth/2);
        final float knobBaseRefRadiPx = dipToPx(knobBaseRefRadiDip);

        knobBaseRef.addCircle(centerX, centerY/6, knobBaseRefRadiPx, Path.Direction.CCW);
        knobBaseRef.op(knobBase, Path.Op.INTERSECT);

        Paint knobBaseRefPaint = new Paint();
        knobBaseRefPaint.setColor(Color.WHITE);
        knobBaseRefPaint.setAntiAlias(true);
        knobBaseRefPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        knobBaseRefPaint.setShader(new RadialGradient(centerX, centerY / 6, knobBaseRefRadiPx, Color.argb(10, 0, 0, 0), Color.argb(32, 0, 0, 0), Shader.TileMode.CLAMP));

        knobBaseRef.transform(matrix);

        canvas.drawPath(knobBaseRef, knobBaseRefPaint);

        Path knobTopShadow = new Path();

        final float knobTopShadowRadiDip = pxToDip(cWidth/4) - 13;
        final float knobTopShadowRadiPx = dipToPx(knobTopShadowRadiDip);

        knobTopShadow.addCircle(centerX, centerY, knobTopShadowRadiPx, Path.Direction.CCW);

        Paint knobTopShadowPaint = new Paint();
        knobTopShadowPaint.setColor(Color.WHITE);
        knobTopShadowPaint.setAntiAlias(true);
        knobTopShadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        knobTopShadowPaint.setShader(new RadialGradient(centerX, centerY, knobTopShadowRadiPx, Color.argb(10, 0, 0, 0), Color.argb(32, 0, 0, 0), Shader.TileMode.CLAMP));

        knobTopShadow.transform(matrix);

        canvas.drawPath(knobTopShadow, knobTopShadowPaint);

        Path knobTop = new Path();

        final float knobTopRadiDip = pxToDip(cWidth/4) - 13f - 0.7f;
        final float knobTopRadiPx = dipToPx(knobTopRadiDip);

        knobTop.addCircle(centerX, centerY, knobTopRadiPx, Path.Direction.CCW);

        Paint knobTopPaint = new Paint();
        knobTopPaint.setColor(Color.WHITE);
        knobTopPaint.setAntiAlias(true);
        knobTopPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        knobTopPaint.setShader(new SweepGradient(centerX, centerY, knobBaseCircle, null));

        knobTop.transform(matrix);

        canvas.drawPath(knobTop, knobTopPaint);

        Path knobRingBase = new Path();

        final float knobRingStrokeWidthDip = 4.3f;
        final float knobRingStrokeWidthPx = dipToPx(knobRingStrokeWidthDip);
        final float knobRingBaseRadiDip = pxToDip(cWidth/4) - 13f + knobRingStrokeWidthDip/2.0f;
        final float knobRingBaseRadiPx = dipToPx(knobRingBaseRadiDip);

//        knobRingBase.addCircle(centerX, centerY, knobTopRadiPx, Path.Direction.CCW);
        knobRingBase.addArc(centerX - knobRingBaseRadiPx, centerY - knobRingBaseRadiPx,
                centerX + knobRingBaseRadiPx, centerY + knobRingBaseRadiPx, -225, 270
        );

        Paint knobRingBasePaint = new Paint();
        knobRingBasePaint.setColor(Color.parseColor("#ff6c6d71"));
        knobRingBasePaint.setAntiAlias(true);
        knobRingBasePaint.setStyle(Paint.Style.STROKE);
        knobRingBasePaint.setStrokeWidth(knobRingStrokeWidthPx*((float) getWidth() / cWidth));
        knobRingBasePaint.setStrokeCap(Paint.Cap.ROUND);

        knobRingBase.transform(matrix);

        canvas.drawPath(knobRingBase, knobRingBasePaint);

        Path knobDotTop = new Path();

        final float knobDotTopDip = 4.3f/2.0f;
        final float knobDotTopPx = dipToPx(knobDotTopDip);

        knobDotTop.addCircle(centerX, centerY, knobDotTopPx, Path.Direction.CCW);

        Paint knobDotTopPaint = new Paint();
        knobDotTopPaint.setColor(Color.parseColor("#ff3dd8c7"));
        knobDotTopPaint.setAntiAlias(true);
        knobDotTopPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        final float knobTopDx = (knobTopRadiPx - knobDotTopPx*2) / (float)Math.sqrt(2);

        final float knobTopDegree = 270f *userKnobValue;

        Matrix knobDotTopMatrix = new Matrix();
        knobDotTopMatrix.preTranslate(-knobTopDx, knobTopDx);
        knobDotTopMatrix.postRotate(knobTopDegree, centerX, centerY);

        knobDotTop.transform(knobDotTopMatrix);

        knobDotTop.transform(matrix);

        canvas.drawPath(knobDotTop, knobDotTopPaint);

        Path knobRingUserBase = new Path();

        final float knobRingUserBaseStrokeWidthDip = 4.3f;
        final float knobRingUserBaseStrokeWidthPx = dipToPx(knobRingUserBaseStrokeWidthDip);
        final float knobRingUserBaseRadiDip = pxToDip(cWidth/4) - 13f + knobRingUserBaseStrokeWidthDip/2.0f;
        final float knobRingUserBaseRadiPx = dipToPx(knobRingUserBaseRadiDip);

//        knobRingBase.addCircle(centerX, centerY, knobTopRadiPx, Path.Direction.CCW);
        knobRingUserBase.addArc(centerX - knobRingUserBaseRadiPx, centerY - knobRingUserBaseRadiPx,
                centerX + knobRingUserBaseRadiPx, centerY + knobRingUserBaseRadiPx, -225, knobTopDegree
        );

        Paint knobRingUserBasePaint = new Paint();
        knobRingUserBasePaint.setColor(Color.parseColor("#ff3dd8c7"));
        knobRingUserBasePaint.setAntiAlias(true);
        knobRingUserBasePaint.setStyle(Paint.Style.STROKE);
        knobRingUserBasePaint.setStrokeWidth(knobRingUserBaseStrokeWidthPx*((float) getWidth() / cWidth));
        knobRingUserBasePaint.setStrokeCap(Paint.Cap.ROUND);

        knobRingUserBase.transform(matrix);

        canvas.drawPath(knobRingUserBase, knobRingUserBasePaint);

    }

    public void touch(MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touchX = event.getX();
            touchY = event.getY();
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            float dx = event.getX() - touchX;
            float dy = event.getY() - touchY;

            if(userKnobValue >= 1.0f && dy < 0)
                return;
            if(userKnobValue <= 0.0f && dy > 0)
                return;
            userKnobValue = userKnobValue - dy / 1000 ;
            if(userKnobValue > 1.0f)
                userKnobValue = 1.0f;
            if(userKnobValue < 0f)
                userKnobValue = 0f;


            touchX = event.getX();
            touchY = event.getY();
            this.invalidate();
        }else if(event.getAction() == MotionEvent.ACTION_UP){

        }

    }

    public void setUserKnobValue(float userKnobValue) {
        this.userKnobValue = userKnobValue;
    }

    public float getUserKnobValue() {
        return userKnobValue;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        touch(event);
        return true;
    }
}
