
import 'usb_manager_platform_interface.dart';

class UsbManager {
  Future<String?> getPlatformVersion() {
    return UsbManagerPlatform.instance.getPlatformVersion();
  }
}
