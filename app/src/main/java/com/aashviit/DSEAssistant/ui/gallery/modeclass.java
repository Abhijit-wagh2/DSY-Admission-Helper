package com.aashviit.DSEAssistant.ui.gallery;
public class modeclass {

    String websitename,websitelink;

    public modeclass(String websitename, String websitelink) {
        this.websitename = websitename;
        this.websitelink = websitelink;
    }

    public  modeclass(){

    }

    public String getWebsitename() {
        return websitename;
    }

    public void setWebsitename(String websitename) {
        this.websitename = websitename;
    }

    public String getWebsitelink() {
        return websitelink;
    }

    public void setWebsitelink(String websitelink) {
        this.websitelink = websitelink;
    }
}
