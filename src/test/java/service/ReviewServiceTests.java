package service;

import com.example.fus.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
public class ReviewServiceTests extends Exception{
    private ReviewService reviewService;
    @Test
    public void removeReview() throws Exception {
        reviewService.removeReview(1);
    }
}
