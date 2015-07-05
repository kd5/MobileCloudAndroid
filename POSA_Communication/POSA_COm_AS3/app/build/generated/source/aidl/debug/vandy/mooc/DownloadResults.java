/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\kuladasd\\AndroidstudioProjects\\POSA_AS3\\app\\src\\main\\aidl\\vandy\\mooc\\DownloadResults.aidl
 */
package vandy.mooc;
/**
 * @class
 *
 * @brief An AIDL Interface used for receiving results from a call to
 *        DownloadRequest.downloadImage()
 *
 *	  This file generates a java interface in /gen
 */
public interface DownloadResults extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements vandy.mooc.DownloadResults
{
private static final java.lang.String DESCRIPTOR = "vandy.mooc.DownloadResults";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an vandy.mooc.DownloadResults interface,
 * generating a proxy if needed.
 */
public static vandy.mooc.DownloadResults asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof vandy.mooc.DownloadResults))) {
return ((vandy.mooc.DownloadResults)iin);
}
return new vandy.mooc.DownloadResults.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_sendPath:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.sendPath(_arg0);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements vandy.mooc.DownloadResults
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * Send the location of a downloaded file on the file system back
     * to the caller.
     */
@Override public void sendPath(java.lang.String filePath) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(filePath);
mRemote.transact(Stub.TRANSACTION_sendPath, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_sendPath = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
     * Send the location of a downloaded file on the file system back
     * to the caller.
     */
public void sendPath(java.lang.String filePath) throws android.os.RemoteException;
}
