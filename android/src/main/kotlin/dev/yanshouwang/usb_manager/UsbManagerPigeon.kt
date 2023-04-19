// Autogenerated from Pigeon (v9.2.4), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package dev.yanshouwang.usb_manager

import android.util.Log
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

private fun wrapResult(result: Any?): List<Any?> {
  return listOf(result)
}

private fun wrapError(exception: Throwable): List<Any?> {
  if (exception is FlutterError) {
    return listOf(
      exception.code,
      exception.message,
      exception.details
    )
  } else {
    return listOf(
      exception.javaClass.simpleName,
      exception.toString(),
      "Cause: " + exception.cause + ", Stacktrace: " + Log.getStackTraceString(exception)
    )
  }
}

/**
 * Error class for passing custom error details to Flutter via a thrown PlatformException.
 * @property code The error code.
 * @property message The error message.
 * @property details The error details. Must be a datatype supported by the api codec.
 */
class FlutterError (
  val code: String,
  override val message: String? = null,
  val details: Any? = null
) : Throwable()

/**
 * A class representing a USB accessory, which is an external hardware component that communicates with an android
 * application over USB. The accessory is the USB host and android the device side of the USB connection.
 *
 * Generated class from Pigeon that represents data sent in messages.
 */
data class UsbAccessory (
  val manufacturer: String,
  val model: String,
  val description: String? = null,
  val version: String? = null,
  val uri: String? = null,
  val serial: String? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): UsbAccessory {
      val manufacturer = list[0] as String
      val model = list[1] as String
      val description = list[2] as String?
      val version = list[3] as String?
      val uri = list[4] as String?
      val serial = list[5] as String?
      return UsbAccessory(manufacturer, model, description, version, uri, serial)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      manufacturer,
      model,
      description,
      version,
      uri,
      serial,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class UsbDevice (
  val deviceClass: Long,
  val deviceSubclass: Long,
  val deviceProtocol: Long,
  val deviceName: String,
  val vendorId: Long,
  val productId: Long,
  val manufacturerName: String? = null,
  val productName: String? = null,
  val configurationCount: Long,
  val interfaceCount: Long,
  val version: String

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): UsbDevice {
      val deviceClass = list[0].let { if (it is Int) it.toLong() else it as Long }
      val deviceSubclass = list[1].let { if (it is Int) it.toLong() else it as Long }
      val deviceProtocol = list[2].let { if (it is Int) it.toLong() else it as Long }
      val deviceName = list[3] as String
      val vendorId = list[4].let { if (it is Int) it.toLong() else it as Long }
      val productId = list[5].let { if (it is Int) it.toLong() else it as Long }
      val manufacturerName = list[6] as String?
      val productName = list[7] as String?
      val configurationCount = list[8].let { if (it is Int) it.toLong() else it as Long }
      val interfaceCount = list[9].let { if (it is Int) it.toLong() else it as Long }
      val version = list[10] as String
      return UsbDevice(deviceClass, deviceSubclass, deviceProtocol, deviceName, vendorId, productId, manufacturerName, productName, configurationCount, interfaceCount, version)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      deviceClass,
      deviceSubclass,
      deviceProtocol,
      deviceName,
      vendorId,
      productId,
      manufacturerName,
      productName,
      configurationCount,
      interfaceCount,
      version,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class UsbConfiguration (
  val id: Long,
  val name: String? = null,
  val maxPower: Long,
  val interfaceCount: Long,
  val isRemoteWakeup: Boolean,
  val isSelfPowered: Boolean

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): UsbConfiguration {
      val id = list[0].let { if (it is Int) it.toLong() else it as Long }
      val name = list[1] as String?
      val maxPower = list[2].let { if (it is Int) it.toLong() else it as Long }
      val interfaceCount = list[3].let { if (it is Int) it.toLong() else it as Long }
      val isRemoteWakeup = list[4] as Boolean
      val isSelfPowered = list[5] as Boolean
      return UsbConfiguration(id, name, maxPower, interfaceCount, isRemoteWakeup, isSelfPowered)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      id,
      name,
      maxPower,
      interfaceCount,
      isRemoteWakeup,
      isSelfPowered,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class UsbInterface (
  val id: Long,
  val alternateSetting: Long,
  val interfaceClass: Long,
  val interfaceSubclass: Long,
  val interfaceProtocol: Long,
  val name: String? = null,
  val endpointCount: Long

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): UsbInterface {
      val id = list[0].let { if (it is Int) it.toLong() else it as Long }
      val alternateSetting = list[1].let { if (it is Int) it.toLong() else it as Long }
      val interfaceClass = list[2].let { if (it is Int) it.toLong() else it as Long }
      val interfaceSubclass = list[3].let { if (it is Int) it.toLong() else it as Long }
      val interfaceProtocol = list[4].let { if (it is Int) it.toLong() else it as Long }
      val name = list[5] as String?
      val endpointCount = list[6].let { if (it is Int) it.toLong() else it as Long }
      return UsbInterface(id, alternateSetting, interfaceClass, interfaceSubclass, interfaceProtocol, name, endpointCount)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      id,
      alternateSetting,
      interfaceClass,
      interfaceSubclass,
      interfaceProtocol,
      name,
      endpointCount,
    )
  }
}
@Suppress("UNCHECKED_CAST")
private object UsbManagerHostApiCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          UsbAccessory.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          UsbDevice.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is UsbAccessory -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is UsbDevice -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/**
 * This class allows you to access the state of USB and communicate with USB devices. Currently only host mode is
 * supported in the public API.
 *
 * Generated interface from Pigeon that represents a handler of messages from Flutter.
 */
interface UsbManagerHostApi {
  /**
   * Returns a list of currently attached USB accessories. (in the current implementation there can be at most one)
   *
   * Requires the PackageManager#FEATURE_USB_ACCESSORY feature which can be detected using
   * PackageManager.hasSystemFeature(String).
   */
  fun getAccessoryList(): List<UsbAccessory>
  /**
   * Returns a HashMap containing all USB devices currently attached. USB device name is the key for the returned
   * HashMap. The result will be empty if no devices are attached, or if USB host mode is inactive or unsupported.
   *
   * Requires the PackageManager#FEATURE_USB_HOST feature which can be detected using
   * PackageManager.hasSystemFeature(String).
   */
  fun getDeviceList(): Map<String, UsbDevice>
  /**
   * Returns true if the caller has permission to access the accessory. Permission might have been granted temporarily via
   * requestPermission(android.hardware.usb.UsbAccessory, android.app.PendingIntent) or by the user
   * choosing the caller as the default application for the accessory.
   *
   * Requires the PackageManager#FEATURE_USB_ACCESSORY feature which can be detected using
   * PackageManager.hasSystemFeature(String).
   */
  fun hasAccessoryPermission(accessory: UsbAccessory): Boolean
  /**
   * Returns true if the caller has permission to access the device. Permission might have been granted temporarily via
   * requestPermission(android.hardware.usb.UsbDevice, android.app.PendingIntent) or by the user
   * choosing the caller as the default application for the device. Permission for USB devices of class
   * UsbConstants#USB_CLASS_VIDEO for clients that target SDK Build.VERSION_CODES.P and above can be granted
   * only if they have additionally the Manifest.permission.CAMERA permission.
   *
   * Requires the PackageManager#FEATURE_USB_HOST feature which can be detected using
   * PackageManager.hasSystemFeature(String).
   */
  fun hasDevicePermission(device: UsbDevice): Boolean

  companion object {
    /** The codec used by UsbManagerHostApi. */
    val codec: MessageCodec<Any?> by lazy {
      UsbManagerHostApiCodec
    }
    /** Sets up an instance of `UsbManagerHostApi` to handle messages through the `binaryMessenger`. */
    @Suppress("UNCHECKED_CAST")
    fun setUp(binaryMessenger: BinaryMessenger, api: UsbManagerHostApi?) {
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.UsbManagerHostApi.getAccessoryList", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.getAccessoryList())
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.UsbManagerHostApi.getDeviceList", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.getDeviceList())
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.UsbManagerHostApi.hasAccessoryPermission", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val accessoryArg = args[0] as UsbAccessory
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.hasAccessoryPermission(accessoryArg))
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.UsbManagerHostApi.hasDevicePermission", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val deviceArg = args[0] as UsbDevice
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.hasDevicePermission(deviceArg))
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
@Suppress("UNCHECKED_CAST")
private object UsbDeviceHostApiCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          UsbConfiguration.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          UsbInterface.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is UsbConfiguration -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is UsbInterface -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/**
 * This class represents a USB device attached to the android device with the android device acting as the USB host. Each
 * device contains one or more UsbInterfaces, each of which contains a number of UsbEndpoints (the channels via
 * which data is transmitted over USB).
 *
 * This class contains information (along with UsbInterface and UsbEndpoint) that describes the capabilities of the
 * USB device. To communicate with the device, you open a UsbDeviceConnection for the device and use UsbRequest
 * to send and receive data on an endpoint. UsbDeviceConnection#controlTransfer is used for control requests on
 * endpoint zero.
 *
 * Generated interface from Pigeon that represents a handler of messages from Flutter.
 */
interface UsbDeviceHostApi {
  fun getSerialNumber(deviceName: String): String?
  /** Returns the UsbConfiguration at the given index. */
  fun getConfiguration(deviceName: String, index: Long): UsbConfiguration
  /**
   * Returns the UsbInterface at the given index. For devices with multiple configurations, you will probably want to use
   * UsbConfiguration#getInterface instead.
   */
  fun getInterface(deviceName: String, index: Long): UsbInterface

  companion object {
    /** The codec used by UsbDeviceHostApi. */
    val codec: MessageCodec<Any?> by lazy {
      UsbDeviceHostApiCodec
    }
    /** Sets up an instance of `UsbDeviceHostApi` to handle messages through the `binaryMessenger`. */
    @Suppress("UNCHECKED_CAST")
    fun setUp(binaryMessenger: BinaryMessenger, api: UsbDeviceHostApi?) {
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.UsbDeviceHostApi.getSerialNumber", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val deviceNameArg = args[0] as String
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.getSerialNumber(deviceNameArg))
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.UsbDeviceHostApi.getConfiguration", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val deviceNameArg = args[0] as String
            val indexArg = args[1].let { if (it is Int) it.toLong() else it as Long }
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.getConfiguration(deviceNameArg, indexArg))
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.UsbDeviceHostApi.getInterface", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val deviceNameArg = args[0] as String
            val indexArg = args[1].let { if (it is Int) it.toLong() else it as Long }
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.getInterface(deviceNameArg, indexArg))
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
@Suppress("UNCHECKED_CAST")
private object UsbConfigurationHostApiCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          UsbInterface.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is UsbInterface -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/**
 * A class representing a configuration on a UsbDevice. A USB configuration can have one or more interfaces, each one
 * providing a different piece of functionality, separate from the other interfaces. An interface will have one or more
 * UsbEndpoints, which are the channels by which the host transfers data with the device.
 *
 * Generated interface from Pigeon that represents a handler of messages from Flutter.
 */
interface UsbConfigurationHostApi {
  /** Returns the UsbInterface at the given index. */
  fun getInterface(deviceName: String, configurationIndex: Long, index: Long): UsbInterface

  companion object {
    /** The codec used by UsbConfigurationHostApi. */
    val codec: MessageCodec<Any?> by lazy {
      UsbConfigurationHostApiCodec
    }
    /** Sets up an instance of `UsbConfigurationHostApi` to handle messages through the `binaryMessenger`. */
    @Suppress("UNCHECKED_CAST")
    fun setUp(binaryMessenger: BinaryMessenger, api: UsbConfigurationHostApi?) {
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.UsbConfigurationHostApi.getInterface", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val deviceNameArg = args[0] as String
            val configurationIndexArg = args[1].let { if (it is Int) it.toLong() else it as Long }
            val indexArg = args[2].let { if (it is Int) it.toLong() else it as Long }
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.getInterface(deviceNameArg, configurationIndexArg, indexArg))
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
