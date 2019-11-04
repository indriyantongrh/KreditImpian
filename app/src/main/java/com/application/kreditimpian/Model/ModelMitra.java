package com.application.kreditimpian.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelMitra {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_company_category")
    @Expose
    private String idCompanyCategory;
    @SerializedName("id_geodirectory")
    @Expose
    private String idGeodirectory;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("contacts")
    @Expose
    private Contacts contacts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCompanyCategory() {
        return idCompanyCategory;
    }

    public void setIdCompanyCategory(String idCompanyCategory) {
        this.idCompanyCategory = idCompanyCategory;
    }

    public String getIdGeodirectory() {
        return idGeodirectory;
    }

    public void setIdGeodirectory(String idGeodirectory) {
        this.idGeodirectory = idGeodirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

}




   /* @SerializedName("id") //ini yg ada di json
    private String id;

    @SerializedName("name") //ini yg ada di json
    private String name;

    @SerializedName("photo") //ini yg ada di json
    private String photo;

    public ModelMitra(String id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }*/






