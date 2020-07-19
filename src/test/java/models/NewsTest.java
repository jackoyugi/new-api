package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {
    @Test
    public void NewsInstantiatesCorrectly() throws Exception{
        News news = setupNews();
        assertTrue(news instanceof News);
    }
    @Test
    public void NewsInstantiatesCorrectlyWith_Values() throws Exception{
        News news = setupNews();
        assertEquals("drug shortage",news.getTitle());
        assertEquals("We are facing drug shortage stay patient",news.getContent());
        assertEquals(2,news.getDepartmentId());
    }
    @Test
    public void setId() throws Exception{
        News news = setupNews();
        news.setId(5);
        assertNotEquals(2,news.getId());
    }

    public News setupNews(){
        return new News("drug shortage","We are facing drug shortage stay patient",2);
    }

}