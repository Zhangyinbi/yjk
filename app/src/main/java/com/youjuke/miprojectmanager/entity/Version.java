package com.youjuke.miprojectmanager.entity;

/**
 * 版本更新实体类
 *
 * @FileName: com.xiaomizhuang.buildcaptain.entity.Version.java
 * @author: Yangbang
 * @date: 2015-12-30 15:46
 */
public class Version {

    /**
     * version_code : 1
     * version_name : 1.0
     * version_msg : 更新内容
     * apk_source : http://m.xiaomizhuang.com/app/Builder_wap.apk
     */

    private String version_code;
    private String version_name;
    private String version_msg;
    private String apk_source;

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public void setVersion_msg(String version_msg) {
        this.version_msg = version_msg;
    }

    public void setApk_source(String apk_source) {
        this.apk_source = apk_source;
    }

    public String getVersion_code() {
        return version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public String getVersion_msg() {
        return version_msg;
    }

    public String getApk_source() {
        return apk_source;
    }
}
