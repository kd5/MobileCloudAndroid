/**
 * 
 */
package com.example.weatherservice.services;

import java.lang.ref.WeakReference;

import android.app.Activity;





public interface ConfigChange
{

    /**
     * Called after a runtime configuration change occurs to finish
     * the initialization steps.
     */
    public void onConfigurationChange(WeakReference<Activity> activity);
}
