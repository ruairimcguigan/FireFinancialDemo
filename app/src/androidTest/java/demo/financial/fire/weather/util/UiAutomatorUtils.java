package demo.financial.fire.weather.util;

import android.os.Build;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import timber.log.Timber;

public final class UiAutomatorUtils {

    public static final String TEXT_ALLOW = "Allow";
    public static final String TEXT_DENY = "Deny";
    private static final String TEXT_NEVER_ASK_AGAIN = "Don't ask again";
    private static final String TEXT_PERMISSIONS = "Permissions";

    private UiAutomatorUtils() {
        // no instances
    }


    // Navigation

    public static void openPermissions(UiDevice device) throws UiObjectNotFoundException {
        UiObject permissions = device.findObject(new UiSelector().text(TEXT_PERMISSIONS));
        permissions.click();
    }

    // Assertions

    public static void assertViewWithTextIsVisible(UiDevice device, String text) {
        UiObject allowButton = device.findObject(new UiSelector().text(text));
    }

    // Actions

//    public static void allowCurrentPermission(UiDevice device) throws UiObjectNotFoundException {
//        UiObject allowButton = device.findObject(new UiSelector().text(TEXT_ALLOW));
//        allowButton.click();
//    }

    public static void denyPermissionsIfNeeded(UiDevice device)  {
        if (Build.VERSION.SDK_INT >= 23) {
            UiObject denyPermissions = device.findObject(new UiSelector().text("Deny"));
            if (denyPermissions.exists()) {
                try {
                    denyPermissions.click();
                } catch (UiObjectNotFoundException e) {
                    Timber.w(e, "There is no permissions dialog to interact with ");
                }
            }
        }
    }

    public static void allowPermissionsIfNeeded(UiDevice device)  {
        if (Build.VERSION.SDK_INT >= 23) {
            UiObject allowPermissions = device.findObject(new UiSelector().text("Allow"));
            if (allowPermissions.exists()) {
                try {
                    allowPermissions.click();
                } catch (UiObjectNotFoundException e) {
                    Timber.w(e, "There is no permissions dialog to interact with ");
                }
            }
        }
    }

//    public static void denyCurrentPermission(UiDevice device) throws UiObjectNotFoundException {
//        UiObject denyButton = device.findObject(new UiSelector().text(TEXT_DENY));
//    }

    public static void denyCurrentPermissionPermanently(UiDevice device) throws UiObjectNotFoundException {
        UiObject neverAskAgainCheckbox = device.findObject(new UiSelector().text(TEXT_NEVER_ASK_AGAIN));
        neverAskAgainCheckbox.click();
        denyPermissionsIfNeeded(device);
    }

    public static void grantPermission(UiDevice device, String permissionTitle) throws UiObjectNotFoundException {
        UiObject permissionEntry = device.findObject(new UiSelector().text(permissionTitle));
        permissionEntry.click();
    }

    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
