package dev.yanshouwang.usb_manager

import android.content.Context
import android.hardware.usb.UsbManager
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin

const val KEY_CONTEXT = "KEY_CONTEXT"

val instances = mutableMapOf<String, Any>()

val context get() = instances[KEY_CONTEXT] as Context
val usbManager get() = context.getSystemService(Context.USB_SERVICE) as UsbManager

/** UsbManagerPlugin */
class UsbManagerPlugin : FlutterPlugin {
    override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        val binaryMessenger = binding.binaryMessenger

        instances[KEY_CONTEXT] = binding.applicationContext

        UsbManagerHostApi.setUp(binaryMessenger, UsbManagerApi)
        UsbDeviceHostApi.setUp(binaryMessenger, UsbDeviceApi)
        UsbConfigurationHostApi.setUp(binaryMessenger, UsbConfigurationApi)
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        val binaryMessenger = binding.binaryMessenger

        UsbManagerHostApi.setUp(binaryMessenger, null)
        UsbDeviceHostApi.setUp(binaryMessenger, null)
        UsbConfigurationHostApi.setUp(binaryMessenger, null)

        instances.remove(KEY_CONTEXT)
    }
}
