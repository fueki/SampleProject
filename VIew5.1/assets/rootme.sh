#!/system/bin/sh
# By Paul O'Brien - @paulobrien - http://www.MoDaCo.com

/system/bin/chmod 4755 /data/data/com.modaco.visionaryplus/busybox
/system/bin/chmod 4755 /data/data/com.modaco.visionaryplus/ratc

if /data/data/com.modaco.visionaryplus/busybox [ "$(/data/data/com.modaco.visionaryplus/busybox id -u)" == "0" ]
then
	/data/data/com.modaco.visionaryplus/busybox killall ratc
	/data/data/com.modaco.visionaryplus/busybox killall -9 ratc
	/data/data/com.modaco.visionaryplus/busybox mount -o remount,rw /system
	/data/data/com.modaco.visionaryplus/busybox mkdir /system/xbin
	/data/data/com.modaco.visionaryplus/busybox chmod 0644 /data/data/com.modaco.visionaryplus/Superuser.apk
	/data/data/com.modaco.visionaryplus/busybox cp /data/data/com.modaco.visionaryplus/Superuser.apk /data/app
	/data/data/com.modaco.visionaryplus/busybox mount -t tmpfs none /system/xbin
	/data/data/com.modaco.visionaryplus/busybox cp /data/data/com.modaco.visionaryplus/busybox /system/xbin/busybox
	/data/data/com.modaco.visionaryplus/busybox chmod 4755 /system/xbin/busybox
	/data/data/com.modaco.visionaryplus/busybox cp /data/data/com.modaco.visionaryplus/su /system/xbin
	/data/data/com.modaco.visionaryplus/busybox chmod 4755 /system/xbin/su
	/data/data/com.modaco.visionaryplus/busybox --install -s /system/xbin
	/data/data/com.modaco.visionaryplus/busybox mount -o remount,ro /system
else
	/data/data/com.modaco.visionaryplus/busybox killall ratc
	/data/data/com.modaco.visionaryplus/busybox killall -9 ratc
	/data/data/com.modaco.visionaryplus/ratc
	count=0
	while /data/data/com.modaco.visionaryplus/busybox [ $count ]
	do
		/data/data/com.modaco.visionaryplus/busybox sleep 1
		unset count
		count=`jobs|/data/data/com.modaco.visionaryplus/busybox wc -l`
	done
fi