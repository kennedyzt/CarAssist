package com.ruiyi.carassistant.utils;

public class TestMethod {
    public TestMethod() { // /xx/weblogic60b2_win.exe
        try {
            SiteInfoBean bean = new SiteInfoBean("http://120.24.152.177:8080/OtaUpdatePkg/eng.fang.1476855693/update.zip",
                    "E:\\", "update.zip", 1);
            // SiteInfoBean bean = new SiteInfoBean("http://localhost:8080/down.zip","L:\\temp",
            // "weblogic60b2_win.exe",5);
            SiteFileFetch fileFetch = new SiteFileFetch(bean);
            fileFetch.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestMethod();
    }
}