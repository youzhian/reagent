package com.ylv.generator;

/**
 * 生成器相关配置
 * @author youzhian
 */
public class GenneratorConfig {

    /**
     * 代码创建者名称
     */
    public static String authorName = "youzhian";
    /**
     * 数据库驱动类名
     */
    public static String driverName = "org.h2.Driver";
    /**
     * 数据库连接地址
     */
    public static String dataUrl = "jdbc:h2:D:/h2/db/test;MODE=MySQL";
    /**
     * 数据库用户名
     */
    public static String userName = "sa";
    /**
     * 数据库密码
     */
    public static String password = "";
    /**
     * 生成代码目录的父目录
     */
    public static String moudelParent = "com.ylv.modules";
    /**
     * 生成mybatis相关xml的根目录
     */
    public static String mapperXmlDir = "/src/main/resources/mapper";
    /**
     * 实体类的包名
     */
    public static String entityPath = "bean";
    /**
     * controller的包名
     */
    public static String controllerPath = "controller";
    /**
     * 是否在生成时重新覆盖文件
     */
    public static boolean fileOverride = true;
    /**
     * 是否覆盖mapper和mapper.xml
     */
    public static boolean mapperFileOverride = false;
    /**
     * 是否在mapper中生成baseColumnList
     */
    public static boolean baseColumnList = true;
    /**
     * 是否在mapper中生成baseResultMap
     */
    public static boolean baseResultMap = true;

}
