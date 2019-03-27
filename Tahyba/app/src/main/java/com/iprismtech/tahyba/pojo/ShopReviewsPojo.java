package com.iprismtech.tahyba.pojo;

import java.util.List;

public class ShopReviewsPojo {

    /**
     * status : true
     * message : review found for you!
     * response : {"Reviews":[{"id":"5","shop_id":"vweb181019124212","user_id":"app181225154743","reviews":"A best shop ever seen","rating":"5","status":"0","created_on":"2018-12-31 16:49:23","full_name":"taparanjan sahu","image":"storage/user_pf_doc/1549539465_app181225154743269.png"},{"id":"2","shop_id":"vweb181019124212","user_id":"app181228194241","reviews":"my reviews","rating":"3","status":"0","created_on":"2018-11-29 12:21:42","full_name":"pinku sahu","image":"storage/user_pf_doc/5c262f59acc58.png"}],"Ratings":[{"RatedTime":"3","avgRate":"3.0","rate1":"1","rate2":"0","rate3":"1","rate4":"0","rate5":"1"}]}
     */

    private boolean status;
    private String message;
    private ResponseBean response;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        private List<ReviewsBean> Reviews;
        private List<RatingsBean> Ratings;

        public List<ReviewsBean> getReviews() {
            return Reviews;
        }

        public void setReviews(List<ReviewsBean> Reviews) {
            this.Reviews = Reviews;
        }

        public List<RatingsBean> getRatings() {
            return Ratings;
        }

        public void setRatings(List<RatingsBean> Ratings) {
            this.Ratings = Ratings;
        }

        public static class ReviewsBean {
            /**
             * id : 5
             * shop_id : vweb181019124212
             * user_id : app181225154743
             * reviews : A best shop ever seen
             * rating : 5
             * status : 0
             * created_on : 2018-12-31 16:49:23
             * full_name : taparanjan sahu
             * image : storage/user_pf_doc/1549539465_app181225154743269.png
             */

            private String id;
            private String shop_id;
            private String user_id;
            private String reviews;
            private String rating;
            private String status;
            private String created_on;
            private String full_name;
            private String image;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getReviews() {
                return reviews;
            }

            public void setReviews(String reviews) {
                this.reviews = reviews;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreated_on() {
                return created_on;
            }

            public void setCreated_on(String created_on) {
                this.created_on = created_on;
            }

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public static class RatingsBean {
            /**
             * RatedTime : 3
             * avgRate : 3.0
             * rate1 : 1
             * rate2 : 0
             * rate3 : 1
             * rate4 : 0
             * rate5 : 1
             */

            private String RatedTime;
            private String avgRate;
            private String rate1;
            private String rate2;
            private String rate3;
            private String rate4;
            private String rate5;

            public String getRatedTime() {
                return RatedTime;
            }

            public void setRatedTime(String RatedTime) {
                this.RatedTime = RatedTime;
            }

            public String getAvgRate() {
                return avgRate;
            }

            public void setAvgRate(String avgRate) {
                this.avgRate = avgRate;
            }

            public String getRate1() {
                return rate1;
            }

            public void setRate1(String rate1) {
                this.rate1 = rate1;
            }

            public String getRate2() {
                return rate2;
            }

            public void setRate2(String rate2) {
                this.rate2 = rate2;
            }

            public String getRate3() {
                return rate3;
            }

            public void setRate3(String rate3) {
                this.rate3 = rate3;
            }

            public String getRate4() {
                return rate4;
            }

            public void setRate4(String rate4) {
                this.rate4 = rate4;
            }

            public String getRate5() {
                return rate5;
            }

            public void setRate5(String rate5) {
                this.rate5 = rate5;
            }
        }
    }
}
