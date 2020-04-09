package com.gupta.udapi.enums;

import com.gupta.udapi.constants.UdapiDatabaseCodes;

public enum DbTypeEnum {
    
    MYSQL((byte) UdapiDatabaseCodes.MYSQL, "mysql"),
    MONGODB((byte) UdapiDatabaseCodes.MONGODB, "mongodb"),
    COUCHDB((byte) UdapiDatabaseCodes.COUCHDB, "couchdb"),
    RIAKDB((byte) UdapiDatabaseCodes.RIAKDB, "riakdb");
    
    private byte id;
    private String value;

    DbTypeEnum(byte id, String value) {
        this.id = id;
        this.value = value;
    }

    public static DbTypeEnum getEnumByValueInt(int i) {
        for (DbTypeEnum e : DbTypeEnum.values()) {
            if (e.getId() == i) {
                return e;
            }
        }
        return null;
    }

    public static DbTypeEnum getEnumByValueByte(byte i) {
        for (DbTypeEnum e : DbTypeEnum.values()) {
            if (e.getId() == i) {
                return e;
            }
        }
        return null;
    }

    public static DbTypeEnum getEnumByDesc(String val) {
        for (DbTypeEnum e : DbTypeEnum.values()) {
            if (e.getValue().equals(val)) {
                return e;
            }
        }
        return null;
    }

    public static String getEnumAsString(DbTypeEnum e) {
        return e.getValue();
    }

    public static Byte getEnumByteFromString(String value) {

        for (DbTypeEnum e : DbTypeEnum.values()) {
            System.out.println(e.value);
            if (e.getValue().equals(value)) {
                return e.getId();
            }
        }
        return null;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
