package android.os.storage;

/**
 * Used for receiving notifications from the StorageManager
 * @hide
 */
public abstract class StorageEventListener {
    /**
     * Called when the detection state of a USB Mass Storage host has changed.
     * @param connected true if the USB mass storage is connected.
     */
    public void onUsbMassStorageConnectionChanged(boolean connected) {
    }

    /**
     * Called when storage has changed state
     * @param path the filesystem path for the storage
     * @param oldState the old state as returned by {@link android.os.Environment#getExternalStorageState()}.
     * @param newState the old state as returned by {@link android.os.Environment#getExternalStorageState()}.
     */
    public void onStorageStateChanged(String path, String oldState, String newState) {
    }
}
