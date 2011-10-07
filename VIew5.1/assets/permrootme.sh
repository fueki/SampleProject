#!/system/bin/sh
# By Paul O'Brien - @paulobrien - http://www.MoDaCo.com

KERNEL_REL=`uname -r`

/system/bin/chmod 4755 /data/data/com.modaco.visionaryplus/busybox

/data/data/com.modaco.visionaryplus/busybox umount /system/xbin
/data/data/com.modaco.visionaryplus/busybox sed "s/THEMAGICNUMBERHERE/${KERNEL_REL}/g" /data/data/com.modaco.visionaryplus/wpthis-GENERIC.ko > /data/data/com.modaco.visionaryplus/wpthis-PATCHED.ko
/data/data/com.modaco.visionaryplus/busybox cp /data/data/com.modaco.visionaryplus/wpthis-PATCHED.ko /data/local/wpthis-lovinglymadeforyoubymodaco.ko
/data/data/com.modaco.visionaryplus/busybox mount -o remount,rw /system
/data/data/com.modaco.visionaryplus/busybox insmod /data/data/com.modaco.visionaryplus/wpthis-PATCHED.ko
/data/data/com.modaco.visionaryplus/busybox sleep 1
/data/data/com.modaco.visionaryplus/busybox mkdir /system/xbin
/data/data/com.modaco.visionaryplus/busybox chmod 0644 /data/data/com.modaco.visionaryplus/Superuser.apk
/data/data/com.modaco.visionaryplus/busybox cp /data/data/com.modaco.visionaryplus/Superuser.apk /data/app
/data/data/com.modaco.visionaryplus/busybox cp /data/data/com.modaco.visionaryplus/busybox /system/xbin/busybox
/data/data/com.modaco.visionaryplus/busybox chmod 4755 /system/xbin/busybox
/data/data/com.modaco.visionaryplus/busybox cp /data/data/com.modaco.visionaryplus/su /system/xbin
/data/data/com.modaco.visionaryplus/busybox chmod 4755 /system/xbin/su
/data/data/com.modaco.visionaryplus/busybox --install -s /system/xbin
/data/data/com.modaco.visionaryplus/busybox sync
/data/data/com.modaco.visionaryplus/reboot