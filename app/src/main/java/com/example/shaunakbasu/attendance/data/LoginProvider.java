package com.example.shaunakbasu.attendance.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by shaunak basu on 09-04-2017.
 */
@ContentProvider(authority = LoginProvider.AUTHORITY, database = LoginDatabase.class)
public class LoginProvider {

    public static final String AUTHORITY = "com.example.shaunakbasu.attendance.data.LoginProvider";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path{
        String LOGIN = "login";
    }

    private static Uri buildUri(String... paths){
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path:paths){
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = LoginDatabase.LOGIN)
    public static class Login{
        @ContentUri(
                path = Path.LOGIN,
                type = "vnd.android.cursor.dir/login"
        )
        public static final Uri CONTENT_URI = buildUri(Path.LOGIN);

        @InexactContentUri(
                name = "LOGIN_ID",
                path = Path.LOGIN + "/*",
                type = "vnd.android.cursor.item/login",
                whereColumn =LoginColumns._ID,
                pathSegment = 1
        )
        public static Uri withID(int id){
            return buildUri(Path.LOGIN, Integer.toString(id));
        }
    }
}

