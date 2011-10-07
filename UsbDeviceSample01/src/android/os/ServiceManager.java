package android.os;

import java.util.Map;

public final class ServiceManager {

    /**
     * Returns a reference to a service with the given name.
     *
     * @param name the name of the service to get
     * @return a reference to the service, or <code>null</code> if the service doesn't exist
     */
    public static IBinder getService(String name) {
        return null;
    }

    /**
     * Place a new @a service called @a name into the service
     * manager.
     *
     * @param name the name of the new service
     * @param service the service object
     */
    public static void addService(String name, IBinder service) {
        // pass
    }

    /**
     * Retrieve an existing service called @a name from the
     * service manager.  Non-blocking.
     */
    public static IBinder checkService(String name) {
        return null;
    }

    /**
     * Return a list of all currently running services.
     */
    public static String[] listServices() throws RemoteException {
        // actual implementation returns null sometimes, so it's ok
        // to return null instead of an empty list.
        return null;
    }

    /**
     * This is only intended to be called when the process is first being brought
     * up and bound by the activity manager. There is only one thread in the process
     * at that time, so no locking is done.
     *
     * @param cache the cache of service references
     * @hide
     */
    public static void initServiceCache(Map<String, IBinder> cache) {
        // pass
    }
}
