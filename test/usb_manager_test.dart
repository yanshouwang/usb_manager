import 'package:flutter_test/flutter_test.dart';
import 'package:usb_manager/usb_manager.dart';
import 'package:usb_manager/usb_manager_platform_interface.dart';
import 'package:usb_manager/usb_manager_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockUsbManagerPlatform
    with MockPlatformInterfaceMixin
    implements UsbManagerPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final UsbManagerPlatform initialPlatform = UsbManagerPlatform.instance;

  test('$MethodChannelUsbManager is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelUsbManager>());
  });

  test('getPlatformVersion', () async {
    UsbManager usbManagerPlugin = UsbManager();
    MockUsbManagerPlatform fakePlatform = MockUsbManagerPlatform();
    UsbManagerPlatform.instance = fakePlatform;

    expect(await usbManagerPlugin.getPlatformVersion(), '42');
  });
}
