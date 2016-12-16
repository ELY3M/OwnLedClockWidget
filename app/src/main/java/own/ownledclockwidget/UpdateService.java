package own.ownledclockwidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateService extends Service {
    private static final String LOG = UpdateService.class.getSimpleName();
    private Context context;

    private Handler handler = new Handler();
    private Runnable runn = new Runnable() {
        @Override
        public void run() {
            //You can do the processing here update the widget/remote views.
            RemoteViews updateviews = new RemoteViews(context.getPackageName(),	R.layout.main);
            updateviews.setImageViewBitmap(R.id.box, Image.buildImage(context));
            ComponentName thiswidget = new ComponentName(context, Widget.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(thiswidget, updateviews);
            handler.postDelayed(runn, 1000);
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(handler!=null){
            handler.removeCallbacks(runn);
        }
        handler = null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = this;
        handler.post(runn);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}