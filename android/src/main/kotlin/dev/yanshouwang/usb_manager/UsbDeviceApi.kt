package dev.yanshouwang.usb_manager

object UsbDeviceApi : UsbDeviceHostApi {
    override fun getSerialNumber(deviceName: String): String? {
        val nativeDevice = usbManager.deviceList[deviceName]!!
        return nativeDevice.serialNumber
    }

    override fun getConfiguration(deviceName: String, index: Long): UsbConfiguration {
        val nativeDevice = usbManager.deviceList[deviceName]!!
        return nativeDevice.getConfiguration(index.toInt()).toApi()
    }

    override fun getInterface(deviceName: String, index: Long): UsbInterface {
        val nativeDevice = usbManager.deviceList[deviceName]!!
        return nativeDevice.getInterface(index.toInt()).toApi()
    }
}

fun android.hardware.usb.UsbConfiguration.toApi(): UsbConfiguration {
    return UsbConfiguration(
        id.toLong(),
        name,
        maxPower.toLong(),
        interfaceCount.toLong(),
        isRemoteWakeup,
        isSelfPowered
    )
}

fun android.hardware.usb.UsbInterface.toApi(): UsbInterface {
    return UsbInterface(
        id.toLong(),
        alternateSetting.toLong(),
        interfaceClass.toLong(),
        interfaceSubclass.toLong(),
        interfaceProtocol.toLong(),
        name,
        endpointCount.toLong()
    )
}