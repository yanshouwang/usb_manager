package dev.yanshouwang.usb_manager

object UsbConfigurationApi : UsbConfigurationHostApi {
    override fun getInterface(deviceName: String, configurationIndex: Long, index: Long): UsbInterface {
        val nativeDevice = usbManager.deviceList[deviceName]!!
        val nativeConfiguration = nativeDevice.getConfiguration(configurationIndex.toInt())
        return nativeConfiguration.getInterface(index.toInt()).toApi()
    }
}