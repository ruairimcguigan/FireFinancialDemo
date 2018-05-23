package demo.financial.fire.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.support.v4.app.ActivityCompat.requestPermissions;
import static android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale;
import static android.support.v4.content.ContextCompat.checkSelfPermission;

public class PermissionsChecker {

    private static final int PERMISSIONS_REQUEST_CODE = 100;

    private final Context mContext;

    public PermissionsChecker(Context context) {
        mContext = context;
    }

     public boolean hasPermissions(String... permissions) {
        if (permissions != null) {
            for (String permission : permissions) {
                if (checkSelfPermission(mContext, permission) != PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean shouldShowRequestRationale(Activity activity){
        return shouldShowRequestPermissionRationale(activity, ACCESS_FINE_LOCATION);
    }

    public void startLocationPermissionRequest(Activity activity) {
        requestPermissions(activity, new String[]{ ACCESS_FINE_LOCATION }, PERMISSIONS_REQUEST_CODE);
    }

    public int getPermissionsRequestCode(){
        return PERMISSIONS_REQUEST_CODE;
    }
}
