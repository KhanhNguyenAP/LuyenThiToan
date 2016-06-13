package com.thud.huynhnhu.luyenthitoan.utils.interfaces;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by Nhu Le on 12/05/2016.
 */
public class ApplicationName extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        //Parse initialize
        Parse.initialize(this, "8ScDczHLmAVHqzL4hDxCSC7Sc3bjwQUCpOvYiQH3",
                "TCV9VFmzHJyrcRM1BxgzIDR3WK09vrOUzlPYQPOm");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        //set ACL
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
