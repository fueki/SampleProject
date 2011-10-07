/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/fuekisatoru/System01/ServiceSample/src/jp/android/sarvice/IMyService.aidl
 */
package jp.android.sarvice;
public interface IMyService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements jp.android.sarvice.IMyService
{
private static final java.lang.String DESCRIPTOR = "jp.android.sarvice.IMyService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an jp.android.sarvice.IMyService interface,
 * generating a proxy if needed.
 */
public static jp.android.sarvice.IMyService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof jp.android.sarvice.IMyService))) {
return ((jp.android.sarvice.IMyService)iin);
}
return new jp.android.sarvice.IMyService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
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
case TRANSACTION_showToast:
{
data.enforceInterface(DESCRIPTOR);
this.showToast();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements jp.android.sarvice.IMyService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void showToast() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_showToast, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_showToast = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void showToast() throws android.os.RemoteException;
}
