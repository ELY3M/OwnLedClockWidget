package own.ownledclockwidget;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;




public class Widget extends AppWidgetProvider {


	public void onEnabled(Context context) {
		super.onEnabled(context);
		context.startService(new Intent(context, UpdateService.class));
	}

	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		context.stopService(new Intent(context, UpdateService.class));
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		context.stopService(new Intent(context, UpdateService.class));
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
						 int[] appWidgetIds) {


		RemoteViews updateviews = new RemoteViews(context.getPackageName(),	R.layout.main);
		updateviews.setImageViewBitmap(R.id.box, Image.buildImage(context));

		ComponentName thiswidget = new ComponentName(context, Widget.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(thiswidget, updateviews);
	}





}
