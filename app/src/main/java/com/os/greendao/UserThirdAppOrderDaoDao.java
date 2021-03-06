package com.os.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.os.police.data.UserThirdAppOrderDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_THIRD_APP_ORDER_DAO".
*/
public class UserThirdAppOrderDaoDao extends AbstractDao<UserThirdAppOrderDao, Long> {

    public static final String TABLENAME = "USER_THIRD_APP_ORDER_DAO";

    /**
     * Properties of entity UserThirdAppOrderDao.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property AppId = new Property(0, Long.class, "appId", true, "_id");
        public final static Property Id = new Property(1, int.class, "id", false, "ID");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Icon = new Property(3, String.class, "icon", false, "ICON");
        public final static Property Url = new Property(4, String.class, "url", false, "URL");
        public final static Property Desc = new Property(5, String.class, "desc", false, "DESC");
        public final static Property AppType = new Property(6, String.class, "appType", false, "APP_TYPE");
        public final static Property StartPage = new Property(7, String.class, "startPage", false, "START_PAGE");
        public final static Property AppSize = new Property(8, int.class, "appSize", false, "APP_SIZE");
        public final static Property Deletable = new Property(9, boolean.class, "deletable", false, "DELETABLE");
        public final static Property Install = new Property(10, String.class, "install", false, "INSTALL");
        public final static Property GUID = new Property(11, String.class, "GUID", false, "GUID");
        public final static Property Time = new Property(12, String.class, "Time", false, "TIME");
        public final static Property CREATEUSERID = new Property(13, String.class, "CREATEUSERID", false, "CREATEUSERID");
        public final static Property Sort = new Property(14, int.class, "sort", false, "SORT");
    };


    public UserThirdAppOrderDaoDao(DaoConfig config) {
        super(config);
    }
    
    public UserThirdAppOrderDaoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_THIRD_APP_ORDER_DAO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: appId
                "\"ID\" INTEGER NOT NULL ," + // 1: id
                "\"TITLE\" TEXT," + // 2: title
                "\"ICON\" TEXT," + // 3: icon
                "\"URL\" TEXT," + // 4: url
                "\"DESC\" TEXT," + // 5: desc
                "\"APP_TYPE\" TEXT," + // 6: appType
                "\"START_PAGE\" TEXT," + // 7: startPage
                "\"APP_SIZE\" INTEGER NOT NULL ," + // 8: appSize
                "\"DELETABLE\" INTEGER NOT NULL ," + // 9: deletable
                "\"INSTALL\" TEXT," + // 10: install
                "\"GUID\" TEXT," + // 11: GUID
                "\"TIME\" TEXT," + // 12: Time
                "\"CREATEUSERID\" TEXT," + // 13: CREATEUSERID
                "\"SORT\" INTEGER NOT NULL );"); // 14: sort
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_THIRD_APP_ORDER_DAO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserThirdAppOrderDao entity) {
        stmt.clearBindings();
 
        Long appId = entity.getAppId();
        if (appId != null) {
            stmt.bindLong(1, appId);
        }
        stmt.bindLong(2, entity.getId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(4, icon);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(5, url);
        }
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(6, desc);
        }
 
        String appType = entity.getAppType();
        if (appType != null) {
            stmt.bindString(7, appType);
        }
 
        String startPage = entity.getStartPage();
        if (startPage != null) {
            stmt.bindString(8, startPage);
        }
        stmt.bindLong(9, entity.getAppSize());
        stmt.bindLong(10, entity.getDeletable() ? 1L: 0L);
 
        String install = entity.getInstall();
        if (install != null) {
            stmt.bindString(11, install);
        }
 
        String GUID = entity.getGUID();
        if (GUID != null) {
            stmt.bindString(12, GUID);
        }
 
        String Time = entity.getTime();
        if (Time != null) {
            stmt.bindString(13, Time);
        }
 
        String CREATEUSERID = entity.getCREATEUSERID();
        if (CREATEUSERID != null) {
            stmt.bindString(14, CREATEUSERID);
        }
        stmt.bindLong(15, entity.getSort());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserThirdAppOrderDao entity) {
        stmt.clearBindings();
 
        Long appId = entity.getAppId();
        if (appId != null) {
            stmt.bindLong(1, appId);
        }
        stmt.bindLong(2, entity.getId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(4, icon);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(5, url);
        }
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(6, desc);
        }
 
        String appType = entity.getAppType();
        if (appType != null) {
            stmt.bindString(7, appType);
        }
 
        String startPage = entity.getStartPage();
        if (startPage != null) {
            stmt.bindString(8, startPage);
        }
        stmt.bindLong(9, entity.getAppSize());
        stmt.bindLong(10, entity.getDeletable() ? 1L: 0L);
 
        String install = entity.getInstall();
        if (install != null) {
            stmt.bindString(11, install);
        }
 
        String GUID = entity.getGUID();
        if (GUID != null) {
            stmt.bindString(12, GUID);
        }
 
        String Time = entity.getTime();
        if (Time != null) {
            stmt.bindString(13, Time);
        }
 
        String CREATEUSERID = entity.getCREATEUSERID();
        if (CREATEUSERID != null) {
            stmt.bindString(14, CREATEUSERID);
        }
        stmt.bindLong(15, entity.getSort());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserThirdAppOrderDao readEntity(Cursor cursor, int offset) {
        UserThirdAppOrderDao entity = new UserThirdAppOrderDao( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // appId
            cursor.getInt(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // icon
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // url
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // desc
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // appType
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // startPage
            cursor.getInt(offset + 8), // appSize
            cursor.getShort(offset + 9) != 0, // deletable
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // install
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // GUID
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // Time
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // CREATEUSERID
            cursor.getInt(offset + 14) // sort
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserThirdAppOrderDao entity, int offset) {
        entity.setAppId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.getInt(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIcon(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDesc(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAppType(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setStartPage(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setAppSize(cursor.getInt(offset + 8));
        entity.setDeletable(cursor.getShort(offset + 9) != 0);
        entity.setInstall(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setGUID(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setTime(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setCREATEUSERID(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setSort(cursor.getInt(offset + 14));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserThirdAppOrderDao entity, long rowId) {
        entity.setAppId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserThirdAppOrderDao entity) {
        if(entity != null) {
            return entity.getAppId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
