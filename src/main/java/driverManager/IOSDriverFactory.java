package driverManager;

import io.appium.java_client.ios.IOSDriver;
import utils.PropertiesReader;
import io.appium.java_client.ios.options.XCUITestOptions;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class IOSDriverFactory {

    public IOSDriverFactory() {
    }

    public static IOSDriver createIOSLocalDriver() throws MalformedURLException {
        URL appiumServerUrl =  URI.create(PropertiesReader.get("appium.server.url")).toURL();

        try {
            return new IOSDriver(appiumServerUrl, buildRealDeviceOptions());
        } catch (IllegalArgumentException exception) {
            throw new IllegalStateException(
                    "Unable to create IOSDriver. Check appiumServerUrl, appiumServerPath, and iOS configuration.",
                    exception
            );
        }
    }

    public static XCUITestOptions buildRealDeviceOptions() {
        var options = new XCUITestOptions()
                .setUdid(PropertiesReader.get("ios.device.udid"))
                .setPlatformName("iOS")
                .setPlatformVersion(PropertiesReader.get("ios.platform.version"))
                .setDeviceName(PropertiesReader.get("ios.device.name"))
                .setBundleId(PropertiesReader.get("ios.app.bundleId"))
                .setShowXcodeLog(true)
                .setAutomationName("XCUITest");

        options.setCapability("appium:xcodeSigningId", "Apple Development");
        options.setCapability("appium:xcodeOrgId", PropertiesReader.get("ios.xcode.orgId"));
        options.setCapability("appium:updatedWDABundleId","com.ayah.WebDriverAgentRunner");

        // Preserve the existing Wikipedia installation and data
        options.setNoReset(true);



//        return new IOSDriver(new URL("http://127.0.0.1:4723"), options);

        return options;
    }

}
