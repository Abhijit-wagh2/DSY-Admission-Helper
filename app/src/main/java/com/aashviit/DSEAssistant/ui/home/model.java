package com.aashviit.DSEAssistant.ui.home;
public class model {

    String cap,CollageName,year,fileurl;
    int Institute_Code;


    public model(String cap, String collageName, String year, String fileurl, int institute_Code) {
        this.cap = cap;
        this.CollageName = collageName;
        this.year = year;
        this.fileurl = fileurl;
        this.Institute_Code = institute_Code;
    }

    public int getInstitute_Code() {
        return Institute_Code;
    }

    public void setInstitute_Code(int institute_Code) {
        Institute_Code = institute_Code;
    }

    public  model(){

    }



    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCollageName() {
        return CollageName;
    }

    public void setCollageName(String collageName) {
        CollageName = collageName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
