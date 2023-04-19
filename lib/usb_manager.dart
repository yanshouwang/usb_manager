import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'usb_manager_pigeon.dart' as pigeon;

export 'usb_constants.dart';

abstract class UsbManager extends PlatformInterface {
  /// Constructs a UsbManager.
  UsbManager() : super(token: _token);

  static final Object _token = Object();

  static UsbManager _instance = _UsbManager();

  /// The default instance of [UsbManager] to use.
  static UsbManager get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [UsbManager] when
  /// they register themselves.
  static set instance(UsbManager instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<List<UsbAccessory>> getAccessoryList();
  Future<Map<String, UsbDevice>> getDeviceList();
  Future<bool> hasAccessoryPermission(UsbAccessory accessory);
  Future<bool> hasDevicePermission(UsbDevice device);
}

abstract class UsbAccessory {
  String get manufacturer;
  String get model;
  String? get description;
  String? get version;
  String? get uri;
  String? get serial;
}

abstract class UsbDevice {
  int get deviceClass;
  int get deviceSubclass;
  int get deviceProtocol;
  String get deviceName;
  int get vendorId;
  int get productId;
  String? get manufacturerName;
  String? get productName;
  int get configurationCount;
  int get interfaceCount;
  String get version;

  Future<String?> getSerialNumber();
  Future<UsbConfiguration> getConfiguration(int index);
  Future<UsbInterface> getInterface(int index);
}

abstract class UsbConfiguration {
  int get id;
  String? get name;
  int get maxPower;
  int get interfaceCount;
  bool get isRemoteWakeup;
  bool get isSelfPowered;

  Future<UsbInterface> getInterface(int index);
}

abstract class UsbInterface {
  int get id;
  int get alternateSetting;
  int get interfaceClass;
  int get interfaceSubclass;
  int get interfaceProtocol;
  String? get name;
  int get endpointCount;
}

class _UsbManager extends UsbManager {
  final pigeon.UsbManagerHostApi hostApi;

  _UsbManager() : hostApi = pigeon.UsbManagerHostApi();

  @override
  Future<List<UsbAccessory>> getAccessoryList() {
    return hostApi.getAccessoryList().then((accessories) {
      return accessories
          .cast<pigeon.UsbAccessory>()
          .map((accessory) => accessory.toNative())
          .toList();
    });
  }

  @override
  Future<Map<String, UsbDevice>> getDeviceList() {
    return hostApi.getDeviceList().then((devices) {
      return devices
          .cast<String, pigeon.UsbDevice>()
          .map((key, value) => MapEntry(key, value.toNative()));
    });
  }

  @override
  Future<bool> hasAccessoryPermission(UsbAccessory accessory) {
    return hostApi.hasAccessoryPermission(accessory.toApi());
  }

  @override
  Future<bool> hasDevicePermission(UsbDevice device) {
    return hostApi.hasDevicePermission(device.toApi());
  }
}

class _UsbAccessory extends UsbAccessory {
  @override
  final String manufacturer;
  @override
  final String model;
  @override
  final String? description;
  @override
  final String? version;
  @override
  final String? uri;
  @override
  final String? serial;

  _UsbAccessory({
    required this.manufacturer,
    required this.model,
    required this.description,
    required this.version,
    required this.uri,
    required this.serial,
  });
}

class _UsbDevice extends UsbDevice {
  @override
  final int deviceClass;
  @override
  final int deviceSubclass;
  @override
  final int deviceProtocol;
  @override
  final String deviceName;
  @override
  final int vendorId;
  @override
  final int productId;
  @override
  final String? manufacturerName;
  @override
  final String? productName;
  @override
  final int configurationCount;
  @override
  final int interfaceCount;
  @override
  final String version;

  final pigeon.UsbDeviceHostApi hostApi;

  _UsbDevice({
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
  }) : hostApi = pigeon.UsbDeviceHostApi();

  @override
  Future<String?> getSerialNumber() {
    return hostApi.getSerialNumber(deviceName);
  }

  @override
  Future<UsbConfiguration> getConfiguration(int index) {
    return hostApi
        .getConfiguration(deviceName, index)
        .then((configuration) => configuration.toNative(this, index));
  }

  @override
  Future<UsbInterface> getInterface(int index) {
    return hostApi
        .getInterface(deviceName, index)
        .then((interface) => interface.toNative());
  }
}

class _UsbConfiguration extends UsbConfiguration {
  @override
  final int id;
  @override
  final String? name;
  @override
  final int maxPower;
  @override
  final int interfaceCount;
  @override
  final bool isRemoteWakeup;
  @override
  final bool isSelfPowered;

  final UsbDevice device;
  final int index;
  final pigeon.UsbConfigurationHostApi hostApi;

  _UsbConfiguration({
    required this.id,
    required this.name,
    required this.maxPower,
    required this.interfaceCount,
    required this.isRemoteWakeup,
    required this.isSelfPowered,
    required this.device,
    required this.index,
  }) : hostApi = pigeon.UsbConfigurationHostApi();

  @override
  Future<UsbInterface> getInterface(int index) {
    return hostApi
        .getInterface(device.deviceName, index, index)
        .then((interface) => interface.toNative());
  }
}

class _UsbInterface extends UsbInterface {
  @override
  final int id;
  @override
  final int alternateSetting;
  @override
  final int interfaceClass;
  @override
  final int interfaceSubclass;
  @override
  final int interfaceProtocol;
  @override
  final String? name;
  @override
  final int endpointCount;

  _UsbInterface({
    required this.id,
    required this.alternateSetting,
    required this.interfaceClass,
    required this.interfaceSubclass,
    required this.interfaceProtocol,
    required this.name,
    required this.endpointCount,
  });
}

extension on UsbAccessory {
  pigeon.UsbAccessory toApi() {
    return pigeon.UsbAccessory(
      manufacturer: manufacturer,
      model: model,
      description: description,
      version: version,
      uri: uri,
      serial: serial,
    );
  }
}

extension on UsbDevice {
  pigeon.UsbDevice toApi() {
    return pigeon.UsbDevice(
      deviceClass: deviceClass,
      deviceSubclass: deviceSubclass,
      deviceProtocol: deviceProtocol,
      deviceName: deviceName,
      vendorId: vendorId,
      productId: productId,
      manufacturerName: manufacturerName,
      productName: productName,
      configurationCount: configurationCount,
      interfaceCount: interfaceCount,
      version: version,
    );
  }
}

extension on pigeon.UsbAccessory {
  UsbAccessory toNative() {
    return _UsbAccessory(
      manufacturer: manufacturer,
      model: model,
      description: description,
      version: version,
      uri: uri,
      serial: serial,
    );
  }
}

extension on pigeon.UsbDevice {
  UsbDevice toNative() {
    return _UsbDevice(
      deviceClass: deviceClass,
      deviceSubclass: deviceSubclass,
      deviceProtocol: deviceProtocol,
      deviceName: deviceName,
      vendorId: vendorId,
      productId: productId,
      manufacturerName: manufacturerName,
      productName: productName,
      configurationCount: configurationCount,
      interfaceCount: interfaceCount,
      version: version,
    );
  }
}

extension on pigeon.UsbConfiguration {
  UsbConfiguration toNative(UsbDevice device, int index) {
    return _UsbConfiguration(
      id: id,
      name: name,
      maxPower: maxPower,
      interfaceCount: interfaceCount,
      isRemoteWakeup: isRemoteWakeup,
      isSelfPowered: isSelfPowered,
      device: device,
      index: index,
    );
  }
}

extension on pigeon.UsbInterface {
  UsbInterface toNative() {
    return _UsbInterface(
      id: id,
      alternateSetting: alternateSetting,
      interfaceClass: interfaceClass,
      interfaceSubclass: interfaceSubclass,
      interfaceProtocol: interfaceProtocol,
      name: name,
      endpointCount: endpointCount,
    );
  }
}
