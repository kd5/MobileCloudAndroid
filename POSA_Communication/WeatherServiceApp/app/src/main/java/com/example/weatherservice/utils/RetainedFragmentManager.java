/**
 * 
 */
package com.example.weatherservice.utils;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.weatherservice.services.ConfigChange;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;


public class RetainedFragmentManager extends Fragment
{

	private static final String TAG = RetainedFragmentManager.class
			.getSimpleName();
	private WeakReference<Activity> mCallbacks;

	
	/**
	 * Maps keys to objects.
	 */
	private Map<String, ConfigChange> mTasks = new HashMap<String, ConfigChange>();

	/**
	 * This method will only be called once when the retained Fragment is first
	 * created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// Retain this fragment across configuration changes.
		setRetainInstance(true);

	}

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		// **** NOTE: I have to do reattachMainActivityToTask here because 
		// it seems that the UI elements inside the Activity
		// are not created if I initialize them from onAttach(). ****
		
		// assuming there are more than one tasks in the map, we need
		// to reattach the weak ref of the MainActivity to all of them.
		reattachMainActivityToTask();
	}



	/**
	 * Hold a reference to the parent Activity so we can report the task's
	 * current progress and results. The Android framework will pass us a
	 * reference to the newly created Activity after each configuration change.
	 */
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		mCallbacks = new WeakReference<Activity>(activity);
		Log.d(TAG, "----- RetainedFragmentManager.onAttach(). Activity="
				+ activity.toString() + " -----");
	}

	
	
	/**
	 * Set the callback to null so we don't accidentally leak the Activity
	 * instance.
	 */
	@Override
	public void onDetach()
	{
		Log.d(TAG, "----- RetainedFragmentManager.onDetach() -----");
		super.onDetach();
		mCallbacks = null;
	}

	
	/**
	 * Add the @a object with the @a key.
	 */
	public void put(String key, ConfigChange task)
	{
		mTasks.put(key, task);
	}

	/**
	 * Get the object with @a key.
	 */
	public ConfigChange get(String key)
	{
		return mTasks.get(key);
	}
	
	
	/**
	 * This method is to reattach the activity to the task
	 * after configuration change.
	 */
	private void reattachMainActivityToTask()
	{
		Collection<ConfigChange> tasks = mTasks.values();
		for (ConfigChange task : tasks)
		{
			task.onConfigurationChange(mCallbacks);
		}
	}
}
