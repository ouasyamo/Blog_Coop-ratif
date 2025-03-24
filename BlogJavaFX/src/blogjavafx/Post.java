package blogjavafx;

public class Post {
    private int id;
    private String title;
    private String body;
    private String slug;          
    private String publishedAt;   

    // Getters et setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return body; }
    public void setContent(String content) { this.body = content; }

    public String getSlug() { return slug; }                       
    public void setSlug(String slug) { this.slug = slug; }         

    public String getPublishedAt() { return publishedAt; }         
    public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; } // <-- à ajouter

}
