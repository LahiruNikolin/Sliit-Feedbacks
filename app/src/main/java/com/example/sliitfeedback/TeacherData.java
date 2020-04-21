package com.example.sliitfeedback;

public class TeacherData {

    private String name;
    private String img;
    private String docId;

    public TeacherData(String name, String img, String docId) {
        this.name = name;
        this.img = img;
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }
}
