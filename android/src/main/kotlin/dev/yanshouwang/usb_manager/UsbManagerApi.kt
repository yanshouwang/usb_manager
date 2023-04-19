package dev.yanshouwang.usb_manager

object UsbManagerApi : UsbManagerHostApi {
    override fun getAccessoryList(): List<UsbAccessory> {
        return usbManager.accessoryList.map { accessory -> accessory.toApi() }
    }

    override fun getDeviceList(): Map<String, UsbDevice> {
        return usbManager.deviceList.mapValues { entry -> entry.value.toApi() }
    }

    override fun hasAccessoryPermission(accessory: UsbAccessory): Boolean {
        val nativeAccessory = usbManager.accessoryList.single { item ->
            item.manufacturer == accessory.manufacturer &&
                    item.model == accessory.model &&
                    item.serial == accessory.serial
        }
        return usbManager.hasPermission(nativeAccessory)
    }

    override fun hasDevicePermission(device: UsbDevice): Boolean {
        val nativeDevice = usbManager.deviceList[device.deviceName]!!
        return usbManager.hasPermission(nativeDevice)
    }

}

fun android.hardware.usb.UsbAccessory.toApi(): UsbAccessory {
    return UsbAccessory(
        manufacturer,
        model,
        description,
        version,
        uri,
        serial
    )
}

fun android.hardware.usb.UsbDevice.toApi(): UsbDevice {
    return UsbDevice(
        deviceClass.toLong(),
        deviceSubclass.toLong(),
        deviceProtocol.toLong(),
        deviceName,
        vendorId.toLong(),
        productId.toLong(),
        manufacturerName,
        productName,
        configurationCount.toLong(),
        interfaceCount.toLong(),
        version
    )
}