package dao;

import com.example.fus.dao.ProductDAO;
import com.example.fus.dao.ReviewDAO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Log4j2
public class ReviewDAOTests {
    private ReviewDAO reviewDAO;

    @BeforeEach
    public void ready() {reviewDAO = new ReviewDAO();}

    //product 추가 (완)
    @Test
    public void removeTest() throws Exception{
        reviewDAO.removeReview(3);
    }
}
