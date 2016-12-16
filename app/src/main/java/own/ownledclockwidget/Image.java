package own.ownledclockwidget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Image {

        private static final String TAG = Image.class.getSimpleName();



/*
        static Bitmap buildImage(Context context) {
                Bitmap myBitmap = Bitmap.createBitmap(400, 200, Bitmap.Config.ARGB_8888);

                Calendar c = Calendar.getInstance();
                int ampm = c.get(Calendar.AM_PM);

                Log.i(LOG, "My Clock Image built with " + currenttime + " " + ampm);
                Canvas myCanvas = new Canvas(myBitmap);
                Paint paint = new Paint();
                ///Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/MouseLEDUmod.ttf");
                ///Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/DS-DIGIB.TTF");
                Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/PixelLCD-7.ttf");
                paint.setAntiAlias(true);
                paint.setSubpixelText(true);
                paint.setTypeface(font);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.CYAN);
                paint.setTextSize(48);
                paint.setTextAlign(Paint.Align.CENTER);
                myCanvas.drawText(currenttime, 170, 100, paint);
                paint.setTextSize(30);
                myCanvas.drawText(currentampm, 350, 100, paint);
                return myBitmap;


        }
*/

        //clock image//
        static Bitmap buildImage(Context context) {

                WindowManager windowManager = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
                int screenwidth = windowManager.getDefaultDisplay().getWidth();
                int screenheight = windowManager.getDefaultDisplay().getHeight();

                Date time = new Date();
                String textTime = new SimpleDateFormat("hh:mm:ss").format(time);


                ///ampm
                Calendar clock = Calendar.getInstance();
                int ampm = clock.get(Calendar.AM_PM);

                String logstring = textTime+" "+ampm;
                Log.i(TAG, "My Clock Image built with " + logstring);

                Resources resources = context.getResources();
                Bitmap myBitmap = Bitmap.createBitmap(screenwidth, 350, Bitmap.Config.ARGB_8888);

                //bitmap settings to prevent blurry images
                Paint bitmapsettings = new Paint();
                bitmapsettings.setAntiAlias(true);
                bitmapsettings.setSubpixelText(true);

                Canvas myCanvas = new Canvas(myBitmap);


                int setclock = 0;

                //hour1
                Bitmap hour1Bitmap;
                Drawable hour1Drawable = resources.getDrawable(NUMBERS[Character.getNumericValue(textTime.charAt(0))]);
                hour1Bitmap = ((BitmapDrawable) hour1Drawable).getBitmap();
                //only draw if hour is 10, 11 or 12
                if (Character.getNumericValue(textTime.charAt(0)) == 1) {
                        myCanvas.drawBitmap(hour1Bitmap, setclock, 100, bitmapsettings);
                } else {
                        setclock = -(hour1Bitmap.getWidth() / 2);
                }
                Log.i(TAG, "setclock: " + setclock);

                //hour2
                Bitmap hour2Bitmap;
                Drawable hour2Drawable = resources.getDrawable(NUMBERS[Character.getNumericValue(textTime.charAt(1))]);
                hour2Bitmap = ((BitmapDrawable) hour2Drawable).getBitmap();
                myCanvas.drawBitmap(hour2Bitmap, setclock + hour1Bitmap.getWidth(), 100, bitmapsettings);


                //colon
                Bitmap colonBitmap;
                Drawable colonDrawable = resources.getDrawable(R.drawable.dot);
                colonBitmap = ((BitmapDrawable) colonDrawable).getBitmap();
                myCanvas.drawBitmap(colonBitmap, setclock + hour1Bitmap.getWidth() + hour2Bitmap.getWidth(), 100, bitmapsettings);

                //minute1
                Bitmap minute1Bitmap;
                Drawable minute1Drawable = resources.getDrawable(NUMBERS[Character.getNumericValue(textTime.charAt(3))]);
                minute1Bitmap = ((BitmapDrawable) minute1Drawable).getBitmap();
                myCanvas.drawBitmap(minute1Bitmap, setclock +hour1Bitmap.getWidth() + hour2Bitmap.getWidth() + colonBitmap.getWidth(), 100, bitmapsettings);

                //minute2
                Bitmap minute2Bitmap;
                Drawable minute2Drawable = resources.getDrawable(NUMBERS[Character.getNumericValue(textTime.charAt(4))]);
                minute2Bitmap = ((BitmapDrawable) minute2Drawable).getBitmap();
                myCanvas.drawBitmap(minute2Bitmap, setclock +hour1Bitmap.getWidth() + hour2Bitmap.getWidth() + colonBitmap.getWidth() + minute1Bitmap.getWidth(), 100, bitmapsettings);

                //colon2
                myCanvas.drawBitmap(colonBitmap, setclock +hour1Bitmap.getWidth() + hour2Bitmap.getWidth() + colonBitmap.getWidth() + minute1Bitmap.getWidth() + minute2Bitmap.getWidth(), 100, bitmapsettings);


                //seconds1
                Bitmap seconds1Bitmap;
                Drawable seconds1Drawable = resources.getDrawable(NUMBERS[Character.getNumericValue(textTime.charAt(6))]);
                seconds1Bitmap = ((BitmapDrawable) seconds1Drawable).getBitmap();
                myCanvas.drawBitmap(seconds1Bitmap, setclock +hour1Bitmap.getWidth() + hour2Bitmap.getWidth() + colonBitmap.getWidth() + minute1Bitmap.getWidth() + minute2Bitmap.getWidth() + colonBitmap.getWidth(), 100, bitmapsettings);

                //seconds2
                Bitmap seconds2Bitmap;
                Drawable seconds2Drawable = resources.getDrawable(NUMBERS[Character.getNumericValue(textTime.charAt(7))]);
                seconds2Bitmap = ((BitmapDrawable) seconds2Drawable).getBitmap();
                myCanvas.drawBitmap(seconds2Bitmap, setclock +hour1Bitmap.getWidth() + hour2Bitmap.getWidth() + colonBitmap.getWidth() + minute1Bitmap.getWidth() + minute2Bitmap.getWidth() + colonBitmap.getWidth() + seconds1Bitmap.getWidth(), 100, bitmapsettings);



                //ampm
                Bitmap ampmBitmap;
                Bitmap ampmResizedBitmap;
                Drawable ampmDrawable = resources.getDrawable(AMPM[ampm]);
                ampmBitmap = ((BitmapDrawable) ampmDrawable).getBitmap();
                //ampmResizedBitmap = Bitmap.createScaledBitmap(ampmBitmap, 100, 131, false);
                myCanvas.drawBitmap(ampmBitmap, setclock +hour1Bitmap.getWidth() + hour2Bitmap.getWidth() + colonBitmap.getWidth() + minute1Bitmap.getWidth() + minute2Bitmap.getWidth() + colonBitmap.getWidth() + seconds1Bitmap.getWidth() + seconds2Bitmap.getWidth(), 100, bitmapsettings);


                return myBitmap;


        }




        private static final int [] AMPM = {
                R.drawable.am,
                R.drawable.pm,

        };

        private static final int [] NUMBERS = {
                R.drawable.n0,
                R.drawable.n1,
                R.drawable.n2,
                R.drawable.n3,
                R.drawable.n4,
                R.drawable.n5,
                R.drawable.n6,
                R.drawable.n7,
                R.drawable.n8,
                R.drawable.n9,

        };



}