package com.jobapp.jabapp.review.imp;


import com.jobapp.jabapp.company.Company;
import com.jobapp.jabapp.company.CompanyRepository;
import com.jobapp.jabapp.company.CompanyService;
import com.jobapp.jabapp.review.Review;
import com.jobapp.jabapp.review.ReviewRepository;
import com.jobapp.jabapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceimp implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceimp(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }




    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getcompanybyid(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review>reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(it->it.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatereview) {
        if(companyService.getcompanybyid(companyId)!=null){
            updatereview.setCompany(companyService.getcompanybyid(companyId));
            updatereview.setId(reviewId);
            reviewRepository.save(updatereview);
            return  true;

        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getcompanybyid(companyId)!=null && reviewRepository.existsById(reviewId)){
            Review reviews = reviewRepository.findById(reviewId).orElse(null);
            Company company = reviews.getCompany();
            company.getReviews().remove(reviews);
            reviews.setCompany(null);
            companyService.updatecompany(companyId,company);
            reviewRepository.deleteById(reviewId);
            return  true;
        }
        return false;
    }
}
