/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/fuekisatoru/System/YoutubeDownloader/src/jp/oesf/app/youtubedownloader/service/IDownloadService.aidl
 */
package jp.oesf.app.youtubedownloader.service;
/**
 * サービスのインターフェイス.
 */
public interface IDownloadService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements jp.oesf.app.youtubedownloader.service.IDownloadService
{
private static final java.lang.String DESCRIPTOR = "jp.oesf.app.youtubedownloader.service.IDownloadService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an jp.oesf.app.youtubedownloader.service.IDownloadService interface,
 * generating a proxy if needed.
 */
public static jp.oesf.app.youtubedownloader.service.IDownloadService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof jp.oesf.app.youtubedownloader.service.IDownloadService))) {
return ((jp.oesf.app.youtubedownloader.service.IDownloadService)iin);
}
return new jp.oesf.app.youtubedownloader.service.IDownloadService.Stub.Proxy(obj);
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
case TRANSACTION_downloadFile:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _result = this.downloadFile(_arg0, _arg1);
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements jp.oesf.app.youtubedownloader.service.IDownloadService
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
/**
     * ダウンロードサービス
     */
public java.lang.String downloadFile(java.lang.String url, java.lang.String title) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(url);
_data.writeString(title);
mRemote.transact(Stub.TRANSACTION_downloadFile, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_downloadFile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
     * ダウンロードサービス
     */
public java.lang.String downloadFile(java.lang.String url, java.lang.String title) throws android.os.RemoteException;
}
