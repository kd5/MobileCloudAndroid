package com.example.weatherservice.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * @class GenericServiceConnection
 * 
 * @brief This class provides a generic framework for defining a
 *        ServiceConnection object to an AIDLInterface that resides in a Bound
 *        Service. It uses Java reflection to factor out common behavior that is
 *        otherwise written in a tedious and error-prone way for each
 *        ServiceConnection object.
 * 
 *        Thanks to Peter Koves for providing the reflection logic!
 */
public class GenericServiceConnection<AIDLInterface extends android.os.IInterface>
		implements ServiceConnection
{
	/**
	 * The following are dependent on code generated by the AIDL compiler, so if
	 * that generated code were to change (which is unlikely) we'd need to
	 * update this code.
	 */
	private static final String STUB = "Stub";
	private static final String AS_INTERFACE = "asInterface";
	private static final Class<?>[] AI_PARAMS = { IBinder.class };

	/**
	 * Reference to the AIDL interface object after the client has finished
	 * binding to the Bound Service.
	 */
	private AIDLInterface mInterface;

	/**
	 * The stub class of the AIDL interface.
	 */
	private final Class<?> mStub;

	/**
	 * The reflective asInterface(IBinder service) method of the stub.
	 */
	private final Method mAsInterface;

	/**
	 * Accessor that returns the AIDL interface object.
	 */
	public AIDLInterface getInterface()
	{
		//System.out.println("---- mInterface -----" + mInterface);
		return mInterface;
	}

	/**
	 * Create a Generic ServiceConnection using the specified AIDL interface.
	 * 
	 * @param aidl
	 *            the AIDL Interface class object which must match the generic
	 *            parameter.
	 */
	public GenericServiceConnection(final Class<AIDLInterface> aidl)
	{

		//System.out.println("---- GenericServiceConnection -----");

		Class<?> stub = null;
		Method method = null;
		for (final Class<?> c : aidl.getDeclaredClasses())
		{
			if (c.getSimpleName().equals(STUB))
			{
				try
				{

					//System.out.println("---- GenericServiceConnection -----"
						//	+ c.getSimpleName());

					stub = c;
					method = stub.getMethod(AS_INTERFACE, AI_PARAMS);
					break;
				}
				catch (final NoSuchMethodException e)
				{ // Should not be possible
					e.printStackTrace();
				}
			}
		}
		mStub = stub;
		mAsInterface = method;
	}

	/**
	 * Hook method called back by the Android Service framework after connection
	 * is established to a Bound Service.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onServiceConnected(ComponentName name, IBinder service)
	{
		Log.d("GenericServiceConnection", "Connected to ComponentName " + name);
		try
		{
			mInterface = (AIDLInterface) mAsInterface.invoke(mStub,
					new Object[] { service });
		}
		catch (IllegalArgumentException e)
		{ // Should not be possible
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{ // Should not be possible
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{ // Should not be possible
			e.printStackTrace();
		}
	}

	/**
	 * Called if the Bound Service crashes and is no longer available. The
	 * ServiceConnection will remain bound, but the Service will not respond to
	 * any requests.
	 */
	@Override
	public void onServiceDisconnected(ComponentName name)
	{
		mInterface = null;
	}
}
