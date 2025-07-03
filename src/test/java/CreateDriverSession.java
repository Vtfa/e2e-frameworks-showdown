import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CreateDriverSession {

    public static void main(String[] args) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("sociedade")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.saucelabs.mydemoapp.android")
                .setAppActivity(".view.activities.SplashActivity");

        URL url = new URL("http://127.0.0.1:4723");
        AppiumDriver driver = new AndroidDriver(url, options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Test 1 - Login
        measure("Test 1 - Login", () -> {
            driver.findElement(AppiumBy.accessibilityId("View menu")).click();
            driver.findElement(AppiumBy.accessibilityId("Login Menu Item")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET")).sendKeys("bod@example.com");
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET")).sendKeys("10203040");
            driver.findElement(AppiumBy.accessibilityId("Tap to login with given credentials")).click();
        });

        // Test 2 - Start items and close reviews
        measure("Test 2 - Browse Features", () -> {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/start1IV\").instance(0)")).click();
            driver.findElement(AppiumBy.accessibilityId("Closes review dialog")).click();
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/start2IV\").instance(1)")).click();
            driver.findElement(AppiumBy.accessibilityId("Closes review dialog")).click();
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/start3IV\").instance(2)")).click();
            driver.findElement(AppiumBy.accessibilityId("Closes review dialog")).click();
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/start4IV\").instance(3)")).click();
            driver.findElement(AppiumBy.accessibilityId("Closes review dialog")).click();
        });

        // Test 3 - Full checkout flow
        measure("Test 3 - Checkout Flow", () -> {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/productIV\").instance(0)")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/plusIV")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/plusIV")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/plusIV")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartIV")).click();
            driver.findElement(AppiumBy.accessibilityId("Confirms products for checkout")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/fullNameET")).sendKeys("Test user");
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/address1ET")).sendKeys("Test address");
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cityET")).sendKeys("BH");
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/zipET")).sendKeys("90210");
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/countryET")).sendKeys("United States");
            driver.findElement(AppiumBy.accessibilityId("Saves user info for checkout")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET")).sendKeys("Test user");
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cardNumberET")).sendKeys("123456789010");
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/expirationDateET")).sendKeys("0129");
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/securityCodeET")).sendKeys("456");
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn")).click();
            driver.findElement(AppiumBy.accessibilityId("Completes the process of checkout")).click();
            driver.findElement(AppiumBy.accessibilityId("Tap to open catalog")).click();
        });

        // Test 4 - Add/remove item from cart
        measure("Test 4 - Modify Cart", () -> {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/productIV\").instance(0)")).click();
            driver.findElement(AppiumBy.accessibilityId("Increase item quantity")).click();
            driver.findElement(AppiumBy.accessibilityId("Increase item quantity")).click();
            driver.findElement(AppiumBy.accessibilityId("Tap to add product to cart")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartIV")).click();
            driver.findElement(AppiumBy.accessibilityId("Decrease item quantity")).click();
            driver.findElement(AppiumBy.accessibilityId("Decrease item quantity")).click();
            driver.findElement(AppiumBy.accessibilityId("Removes product from cart")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/shoppingBt")).click();
        });

        // Test 5 - Open WebView and enter URL
        measure("Test 5 - WebView", () -> {
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/menuIV")).click();
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"WebView\")")).click();
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/urlET")).sendKeys("google.com");
            driver.findElement(AppiumBy.accessibilityId("Tap to view content of given url")).click();
        });

        // Optional: driver.quit();
    }

    public static void measure(String testName, Runnable test) {
        long start = System.currentTimeMillis();
        try {
            test.run();
            long duration = System.currentTimeMillis() - start;
            System.out.println(testName + " ✅ finished in " + duration + " ms");
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - start;
            System.out.println(testName + " ❌ failed after " + duration + " ms");
            e.printStackTrace();
        }
    }
}
