package com.example.bigme.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2023-12-25 21:56:23
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Links {

    private String url;
    private String html;
    private String bbcode;
    private String markdown;
    @JsonProperty("markdown_with_link")
    private String markdownWithLink;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
    @JsonProperty("delete_url")
    private String deleteUrl;
    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setHtml(String html) {
         this.html = html;
     }
     public String getHtml() {
         return html;
     }

    public void setBbcode(String bbcode) {
         this.bbcode = bbcode;
     }
     public String getBbcode() {
         return bbcode;
     }

    public void setMarkdown(String markdown) {
         this.markdown = markdown;
     }
     public String getMarkdown() {
         return markdown;
     }

    public void setMarkdownWithLink(String markdownWithLink) {
         this.markdownWithLink = markdownWithLink;
     }
     public String getMarkdownWithLink() {
         return markdownWithLink;
     }

    public void setThumbnailUrl(String thumbnailUrl) {
         this.thumbnailUrl = thumbnailUrl;
     }
     public String getThumbnailUrl() {
         return thumbnailUrl;
     }

    public void setDeleteUrl(String deleteUrl) {
         this.deleteUrl = deleteUrl;
     }
     public String getDeleteUrl() {
         return deleteUrl;
     }

}