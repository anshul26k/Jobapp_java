package com.jobapp.jabapp.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> GetAllReviews(@PathVariable Long companyId){
         List<Review>reviews =  reviewService.getAllReviews(companyId);
         return  ResponseEntity.ok(reviews);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        Review result = reviewService.getReviewById(companyId,reviewId);
        if(result!=null){
            return ResponseEntity.ok(result);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review){
        boolean isadded = reviewService.addReview(companyId,review);
        if(isadded){
            return ResponseEntity.ok("succefuly added");
        }
         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review updatereview){
        boolean isupdated = reviewService.updateReview(companyId, reviewId, updatereview);
        if(isupdated){
            return ResponseEntity.ok("done");
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean isdeleted = reviewService.deleteReview(companyId, reviewId);
        if(isdeleted){
            return ResponseEntity.ok("done");
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
