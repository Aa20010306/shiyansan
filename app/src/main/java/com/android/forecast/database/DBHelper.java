package com.android.forecast.database;
//创建数据库，创建表，存储数据，
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
//创建数据库
    //public SQLiteOpenHelper(@Nullable Context context,
//                        @Nullable String name,
//                        @Nullable CursorFactory factory,
//                        int version)
//    {
//        this(context, name, factory, version, null);
//    }
    public DBHelper(Context context){
        super(context,"forecast.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//  由于是抽象方法，所以子类一定要实现；
//它的作用是数据库第一次创建时，进入执行；一般将创建表的sql语句写进其中。
//一个参数：当前连接或者创建的SQLiteDatabase对象
        String sql = "create table info(_id integer primary key autoincrement,city varchar(20) unique not null,content text not null)";
        db.execSQL(sql);
    }
//它的作用是数据库的版本号更新（增加时），进入执行；一般将修改表整体结构的sql语句写入其中。
//三个参数：当前连接或者创建的SQLiteDatabase对象、旧版本号、新版本号
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
