package com.foodsharetest.android.util;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    private static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for(Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
    public static Activity getCurrentActivity(){
        return activities.size()==0 ? null : activities.get(activities.size()-1);
    }
}
