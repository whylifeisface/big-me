package com.example.bigme.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Auto-generated: 2023-12-25 21:56:23
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Data {

    private String key;
    private String name;
    private String pathname;
    @JsonProperty("origin_name")
    private String originName;
    private String size;
    private String mimetype;
    private String extension;
    private String md5;
    private String sha1;
    private Links links;
    public void setKey(String key) {
         this.key = key;
     }
     public String getKey() {
         return key;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setPathname(String pathname) {
         this.pathname = pathname;
     }
     public String getPathname() {
         return pathname;
     }

    public void setOriginName(String originName) {
         this.originName = originName;
     }
     public String getOriginName() {
         return originName;
     }

    public void setSize(String size) {
         this.size = size;
     }
     public String getSize() {
         return size;
     }

    public void setMimetype(String mimetype) {
         this.mimetype = mimetype;
     }
     public String getMimetype() {
         return mimetype;
     }

    public void setExtension(String extension) {
         this.extension = extension;
     }
     public String getExtension() {
         return extension;
     }

    public void setMd5(String md5) {
         this.md5 = md5;
     }
     public String getMd5() {
         return md5;
     }

    public void setSha1(String sha1) {
         this.sha1 = sha1;
     }
     public String getSha1() {
         return sha1;
     }

    public void setLinks(Links links) {
         this.links = links;
     }
     public Links getLinks() {
         return links;
     }

}