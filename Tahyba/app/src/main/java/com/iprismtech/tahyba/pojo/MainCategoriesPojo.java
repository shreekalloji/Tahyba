package com.iprismtech.tahyba.pojo;

import java.util.List;

public class MainCategoriesPojo {


    /**
     * status : true
     * message : Categories found
     * response : [{"id":"1","name":"Resturant","meta_url":"Resturant","meta_image":"assets/global-logo.png","meta_keywords":null,"meta_description":"","image":"assets/website/core_img/icons/restaurant.png","xsmob_banner":"assets/app-images/category_banner/restaurent.png","smmob_banner":"assets/app-images/category_image/restaurent.png","mobicon":"assets/app-images/category_icon/restaurent.png","created_on":"2018-08-25 00:00:00"},{"id":"2","name":"Caterring","meta_url":"Caterring","meta_image":"assets/global-logo.png","meta_keywords":null,"meta_description":"","image":"assets/website/core_img/icons/catering.png","xsmob_banner":"assets/app-images/category_banner/catering.png","smmob_banner":"assets/app-images/category_image/catering.png","mobicon":"assets/app-images/category_icon/catering.png","created_on":"2018-08-25 00:00:00"},{"id":"3","name":"Hospitals","meta_url":"Hospitals","meta_image":"assets/global-logo.png","meta_keywords":null,"meta_description":"","image":"assets/website/core_img/icons/hospital.png","xsmob_banner":"assets/app-images/category_banner/hospital.png","smmob_banner":"assets/app-images/category_image/hospital.jpg","mobicon":"assets/app-images/category_icon/hospital.jpg","created_on":"2018-08-25 00:00:00"},{"id":"4","name":"Training institute","meta_url":"Training institute","meta_image":"assets/global-logo.png","meta_keywords":null,"meta_description":"","image":"assets/website/core_img/icons/training.png","xsmob_banner":"assets/app-images/category_banner/instituate.png","smmob_banner":"assets/app-images/category_image/instituate.png","mobicon":"assets/app-images/category_icon/college.png","created_on":"2018-08-25 00:00:00"},{"id":"5","name":"Schools","meta_url":"Schools","meta_image":"assets/global-logo.png","meta_keywords":null,"meta_description":"","image":"assets/website/core_img/icons/school.png","xsmob_banner":"assets/app-images/category_banner/college.png","smmob_banner":"assets/app-images/category_image/college.png","mobicon":"assets/app-images/category_icon/college.png","created_on":"2018-08-25 00:00:00"},{"id":"6","name":"webdesign","meta_url":"webdesign","meta_image":"assets/global-logo.png","meta_keywords":null,"meta_description":"","image":"assets/website/core_img/icons/webdesign.png","xsmob_banner":"assets/app-images/category_banner/restaurant.png","smmob_banner":"assets/app-images/category_image/catering.png","mobicon":"assets/app-images/category_icon/restaurent.png","created_on":"2018-08-25 00:00:00"}]
     */

    private boolean status;
    private String message;
    private List<ResponseBean> response;

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

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * id : 1
         * name : Resturant
         * meta_url : Resturant
         * meta_image : assets/global-logo.png
         * meta_keywords : null
         * meta_description :
         * image : assets/website/core_img/icons/restaurant.png
         * xsmob_banner : assets/app-images/category_banner/restaurent.png
         * smmob_banner : assets/app-images/category_image/restaurent.png
         * mobicon : assets/app-images/category_icon/restaurent.png
         * created_on : 2018-08-25 00:00:00
         */

        private String id;
        private String name;
        private String meta_url;
        private String meta_image;
        private Object meta_keywords;
        private String meta_description;
        private String image;
        private String xsmob_banner;
        private String smmob_banner;
        private String mobicon;
        private String created_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMeta_url() {
            return meta_url;
        }

        public void setMeta_url(String meta_url) {
            this.meta_url = meta_url;
        }

        public String getMeta_image() {
            return meta_image;
        }

        public void setMeta_image(String meta_image) {
            this.meta_image = meta_image;
        }

        public Object getMeta_keywords() {
            return meta_keywords;
        }

        public void setMeta_keywords(Object meta_keywords) {
            this.meta_keywords = meta_keywords;
        }

        public String getMeta_description() {
            return meta_description;
        }

        public void setMeta_description(String meta_description) {
            this.meta_description = meta_description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getXsmob_banner() {
            return xsmob_banner;
        }

        public void setXsmob_banner(String xsmob_banner) {
            this.xsmob_banner = xsmob_banner;
        }

        public String getSmmob_banner() {
            return smmob_banner;
        }

        public void setSmmob_banner(String smmob_banner) {
            this.smmob_banner = smmob_banner;
        }

        public String getMobicon() {
            return mobicon;
        }

        public void setMobicon(String mobicon) {
            this.mobicon = mobicon;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }
}
