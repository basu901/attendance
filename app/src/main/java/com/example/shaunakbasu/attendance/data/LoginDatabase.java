package com.example.shaunakbasu.attendance.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by shaunak basu on 09-04-2017.
 */
@Database(version = LoginDatabase.VERSION)
public class LoginDatabase {

    private LoginDatabase(){}

    public static final int VERSION = 1;

    @Table(LoginColumns.class) public static final String LOGIN = "login";
}