package models;

import java.util.Objects;

public class News {
    private String title;
    private String content;
    private int departmentId;
    private int id;

    public News(String title, String content, int departmentId){
        this.title=title;
        this.content=content;
        this.departmentId=departmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return getDepartmentId() == news.getDepartmentId() &&
                getId() == news.getId() &&
                getTitle().equals(news.getTitle()) &&
                getContent().equals(news.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getContent(), getDepartmentId(), getId());
    }
}
