/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\android\\studioforworkspace\\Qbggfanqu\\kJPush\\src\\main\\aidl\\org\\kymjs\\push\\core\\IPushService.aidl
 */
package org.kymjs.push.core;
public interface IPushService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.kymjs.push.core.IPushService
{
private static final java.lang.String DESCRIPTOR = "org.kymjs.push.core.IPushService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.kymjs.push.core.IPushService interface,
 * generating a proxy if needed.
 */
public static org.kymjs.push.core.IPushService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.kymjs.push.core.IPushService))) {
return ((org.kymjs.push.core.IPushService)iin);
}
return new org.kymjs.push.core.IPushService.Stub.Proxy(obj);
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
case TRANSACTION_startPull:
{
data.enforceInterface(DESCRIPTOR);
this.startPull();
reply.writeNoException();
return true;
}
case TRANSACTION_stopPull:
{
data.enforceInterface(DESCRIPTOR);
this.stopPull();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.kymjs.push.core.IPushService
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
@Override public void startPull() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_startPull, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void stopPull() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopPull, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_startPull = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_stopPull = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void startPull() throws android.os.RemoteException;
public void stopPull() throws android.os.RemoteException;
}
