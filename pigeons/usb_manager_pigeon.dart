import 'package:pigeon/pigeon.dart';

@ConfigurePigeon(
  PigeonOptions(
    dartOut: 'lib/usb_manager_pigeon.dart',
    dartTestOut: 'test/test_usb_manager_pigeon.dart',
    kotlinOut:
        'android/src/main/kotlin/dev/yanshouwang/usb_manager/UsbManagerPigeon.kt',
    kotlinOptions: KotlinOptions(
      package: 'dev.yanshouwang.usb_manager',
    ),
  ),
)

/// A class representing a USB accessory, which is an external hardware component that communicates with an android
/// application over USB. The accessory is the USB host and android the device side of the USB connection.
class UsbAccessory {
  final String manufacturer;
  final String model;
  final String? description;
  final String? version;
  final String? uri;
  final String? serial;

  UsbAccessory({
    required this.manufacturer,
    required this.model,
    required this.description,
    required this.version,
    required this.uri,
    required this.serial,
  });
}

class UsbDevice {
  final int deviceClass;
  final int deviceSubclass;
  final int deviceProtocol;
  final String deviceName;
  final int vendorId;
  final int productId;
  final String? manufacturerName;
  final String? productName;
  final int configurationCount;
  final int interfaceCount;
  final String version;

  UsbDevice({
    required this.deviceClass,
    required this.deviceSubclass,
    required this.deviceProtocol,
    required this.deviceName,
    required this.vendorId,
    required this.productId,
    required this.manufacturerName,
    required this.productName,
    required this.configurationCount,
    required this.interfaceCount,
    required this.version,
  });
}

class UsbConfiguration {
  final int id;
  final String? name;
  final int maxPower;
  final int interfaceCount;
  final bool isRemoteWakeup;
  final bool isSelfPowered;

  UsbConfiguration({
    required this.id,
    required this.name,
    required this.maxPower,
    required this.interfaceCount,
    required this.isRemoteWakeup,
    required this.isSelfPowered,
  });
}

class UsbInterface {
  final int id;
  final int alternateSetting;
  final int interfaceClass;
  final int interfaceSubclass;
  final int interfaceProtocol;
  final String? name;
  final int endpointCount;

  UsbInterface({
    required this.id,
    required this.alternateSetting,
    required this.interfaceClass,
    required this.interfaceSubclass,
    required this.interfaceProtocol,
    required this.name,
    required this.endpointCount,
  });
}

/// This class allows you to access the state of USB and communicate with USB devices. Currently only host mode is
/// supported in the public API.
@HostApi()
abstract class UsbManagerHostApi {
  /// Returns a list of currently attached USB accessories. (in the current implementation there can be at most one)
  ///
  /// Requires the PackageManager#FEATURE_USB_ACCESSORY feature which can be detected using
  /// PackageManager.hasSystemFeature(String).
  List<UsbAccessory> getAccessoryList();

  /// Returns a HashMap containing all USB devices currently attached. USB device name is the key for the returned
  /// HashMap. The result will be empty if no devices are attached, or if USB host mode is inactive or unsupported.
  ///
  /// Requires the PackageManager#FEATURE_USB_HOST feature which can be detected using
  /// PackageManager.hasSystemFeature(String).
  Map<String, UsbDevice> getDeviceList();

  /// Returns true if the caller has permission to access the accessory. Permission might have been granted temporarily via
  /// requestPermission(android.hardware.usb.UsbAccessory, android.app.PendingIntent) or by the user
  /// choosing the caller as the default application for the accessory.
  ///
  /// Requires the PackageManager#FEATURE_USB_ACCESSORY feature which can be detected using
  /// PackageManager.hasSystemFeature(String).
  bool hasAccessoryPermission(UsbAccessory accessory);

  /// Returns true if the caller has permission to access the device. Permission might have been granted temporarily via
  /// requestPermission(android.hardware.usb.UsbDevice, android.app.PendingIntent) or by the user
  /// choosing the caller as the default application for the device. Permission for USB devices of class
  /// UsbConstants#USB_CLASS_VIDEO for clients that target SDK Build.VERSION_CODES.P and above can be granted
  /// only if they have additionally the Manifest.permission.CAMERA permission.
  ///
  /// Requires the PackageManager#FEATURE_USB_HOST feature which can be detected using
  /// PackageManager.hasSystemFeature(String).
  bool hasDevicePermission(UsbDevice device);
}

/// This class represents a USB device attached to the android device with the android device acting as the USB host. Each
/// device contains one or more UsbInterfaces, each of which contains a number of UsbEndpoints (the channels via
/// which data is transmitted over USB).
///
/// This class contains information (along with UsbInterface and UsbEndpoint) that describes the capabilities of the
/// USB device. To communicate with the device, you open a UsbDeviceConnection for the device and use UsbRequest
/// to send and receive data on an endpoint. UsbDeviceConnection#controlTransfer is used for control requests on
/// endpoint zero.
@HostApi()
abstract class UsbDeviceHostApi {
  String? getSerialNumber(String deviceName);

  /// Returns the UsbConfiguration at the given index.
  UsbConfiguration getConfiguration(String deviceName, int index);

  /// Returns the UsbInterface at the given index. For devices with multiple configurations, you will probably want to use
  /// UsbConfiguration#getInterface instead.
  UsbInterface getInterface(String deviceName, int index);
}

/// A class representing a configuration on a UsbDevice. A USB configuration can have one or more interfaces, each one
/// providing a different piece of functionality, separate from the other interfaces. An interface will have one or more
/// UsbEndpoints, which are the channels by which the host transfers data with the device.
@HostApi()
abstract class UsbConfigurationHostApi {
  /// Returns the UsbInterface at the given index.
  UsbInterface getInterface(
    String deviceName,
    int configurationIndex,
    int index,
  );
}
