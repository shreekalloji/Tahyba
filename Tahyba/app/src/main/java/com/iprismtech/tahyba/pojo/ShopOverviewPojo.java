package com.iprismtech.tahyba.pojo;

import java.util.List;

public class ShopOverviewPojo {


    /**
     * status : true
     * message : Got this shop information!
     * response : {"shop_banners":[{"id":"15","shop_id":"vw19014418","image":"storage/vender-shop-banner/1551423435_vw19014418165.jpeg","title":"","slider_no":"0","created_on":"2019-03-01 12:27:15","modified_on":"2019-03-01 06:57:15"},{"id":"16","shop_id":"vw19014418","image":"storage/vender-shop-banner/1551423445_vw1901441879.jpeg","title":"","slider_no":"0","created_on":"2019-03-01 12:27:25","modified_on":"2019-03-01 06:57:25"},{"id":"17","shop_id":"vw19014418","image":"storage/vender-shop-banner/1551424067_vw19014418117.jpg","title":"","slider_no":"0","created_on":"2019-03-01 12:37:47","modified_on":"2019-03-01 07:07:47"}],"shop_details":{"shop_id":"vw19014418","image":"","shop_name":"Iprism Test","address":"Test","landline":"040-22332233","mobile":"8886032595","other_no":"8886032596","location":"Hyderabad","sub_location":"Film Nagar","Onlydate":"01 Mar 2019","latitude":"17.4170016","longitude":"78.41215369999998","business_site":"www.google.com","business_information":"Best Restaurant in Hyderabad","avgRate":"0.0","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","YourFav":"NO"},"shop_hrs":[{"id":"22","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Sunday"},{"id":"23","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Monday"},{"id":"24","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Tuesday"},{"id":"25","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Wednesday"},{"id":"26","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Thursday"},{"id":"27","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Friday"},{"id":"28","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Saturday"}]}
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
        /**
         * shop_banners : [{"id":"15","shop_id":"vw19014418","image":"storage/vender-shop-banner/1551423435_vw19014418165.jpeg","title":"","slider_no":"0","created_on":"2019-03-01 12:27:15","modified_on":"2019-03-01 06:57:15"},{"id":"16","shop_id":"vw19014418","image":"storage/vender-shop-banner/1551423445_vw1901441879.jpeg","title":"","slider_no":"0","created_on":"2019-03-01 12:27:25","modified_on":"2019-03-01 06:57:25"},{"id":"17","shop_id":"vw19014418","image":"storage/vender-shop-banner/1551424067_vw19014418117.jpg","title":"","slider_no":"0","created_on":"2019-03-01 12:37:47","modified_on":"2019-03-01 07:07:47"}]
         * shop_details : {"shop_id":"vw19014418","image":"","shop_name":"Iprism Test","address":"Test","landline":"040-22332233","mobile":"8886032595","other_no":"8886032596","location":"Hyderabad","sub_location":"Film Nagar","Onlydate":"01 Mar 2019","latitude":"17.4170016","longitude":"78.41215369999998","business_site":"www.google.com","business_information":"Best Restaurant in Hyderabad","avgRate":"0.0","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","YourFav":"NO"}
         * shop_hrs : [{"id":"22","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Sunday"},{"id":"23","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Monday"},{"id":"24","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Tuesday"},{"id":"25","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Wednesday"},{"id":"26","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Thursday"},{"id":"27","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Friday"},{"id":"28","open_hour":"08:00 AM","close_hour":"09:00 PM","open_status":"1","Day_name":"Saturday"}]
         */

        private ShopDetailsBean shop_details;
        private List<ShopBannersBean> shop_banners;
        private List<ShopHrsBean> shop_hrs;

        public ShopDetailsBean getShop_details() {
            return shop_details;
        }

        public void setShop_details(ShopDetailsBean shop_details) {
            this.shop_details = shop_details;
        }

        public List<ShopBannersBean> getShop_banners() {
            return shop_banners;
        }

        public void setShop_banners(List<ShopBannersBean> shop_banners) {
            this.shop_banners = shop_banners;
        }

        public List<ShopHrsBean> getShop_hrs() {
            return shop_hrs;
        }

        public void setShop_hrs(List<ShopHrsBean> shop_hrs) {
            this.shop_hrs = shop_hrs;
        }

        public static class ShopDetailsBean {
            /**
             * shop_id : vw19014418
             * image :
             * shop_name : Iprism Test
             * address : Test
             * landline : 040-22332233
             * mobile : 8886032595
             * other_no : 8886032596
             * location : Hyderabad
             * sub_location : Film Nagar
             * Onlydate : 01 Mar 2019
             * latitude : 17.4170016
             * longitude : 78.41215369999998
             * business_site : www.google.com
             * business_information : Best Restaurant in Hyderabad
             * avgRate : 0.0
             * open_hour : 08:00 AM
             * close_hour : 09:00 PM
             * open_status : 1
             * YourFav : NO
             */

            private String shop_id;
            private String image;
            private String shop_name;
            private String address;
            private String landline;
            private String mobile;
            private String other_no;
            private String location;
            private String sub_location;
            private String Onlydate;
            private String latitude;
            private String longitude;
            private String business_site;
            private String business_information;
            private String avgRate;
            private String open_hour;
            private String close_hour;
            private String open_status;
            private String YourFav;

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLandline() {
                return landline;
            }

            public void setLandline(String landline) {
                this.landline = landline;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOther_no() {
                return other_no;
            }

            public void setOther_no(String other_no) {
                this.other_no = other_no;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getSub_location() {
                return sub_location;
            }

            public void setSub_location(String sub_location) {
                this.sub_location = sub_location;
            }

            public String getOnlydate() {
                return Onlydate;
            }

            public void setOnlydate(String Onlydate) {
                this.Onlydate = Onlydate;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getBusiness_site() {
                return business_site;
            }

            public void setBusiness_site(String business_site) {
                this.business_site = business_site;
            }

            public String getBusiness_information() {
                return business_information;
            }

            public void setBusiness_information(String business_information) {
                this.business_information = business_information;
            }

            public String getAvgRate() {
                return avgRate;
            }

            public void setAvgRate(String avgRate) {
                this.avgRate = avgRate;
            }

            public String getOpen_hour() {
                return open_hour;
            }

            public void setOpen_hour(String open_hour) {
                this.open_hour = open_hour;
            }

            public String getClose_hour() {
                return close_hour;
            }

            public void setClose_hour(String close_hour) {
                this.close_hour = close_hour;
            }

            public String getOpen_status() {
                return open_status;
            }

            public void setOpen_status(String open_status) {
                this.open_status = open_status;
            }

            public String getYourFav() {
                return YourFav;
            }

            public void setYourFav(String YourFav) {
                this.YourFav = YourFav;
            }
        }

        public static class ShopBannersBean {
            /**
             * id : 15
             * shop_id : vw19014418
             * image : storage/vender-shop-banner/1551423435_vw19014418165.jpeg
             * title :
             * slider_no : 0
             * created_on : 2019-03-01 12:27:15
             * modified_on : 2019-03-01 06:57:15
             */

            private String id;
            private String shop_id;
            private String image;
            private String title;
            private String slider_no;
            private String created_on;
            private String modified_on;

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSlider_no() {
                return slider_no;
            }

            public void setSlider_no(String slider_no) {
                this.slider_no = slider_no;
            }

            public String getCreated_on() {
                return created_on;
            }

            public void setCreated_on(String created_on) {
                this.created_on = created_on;
            }

            public String getModified_on() {
                return modified_on;
            }

            public void setModified_on(String modified_on) {
                this.modified_on = modified_on;
            }
        }

        public static class ShopHrsBean {
            /**
             * id : 22
             * open_hour : 08:00 AM
             * close_hour : 09:00 PM
             * open_status : 1
             * Day_name : Sunday
             */

            private String id;
            private String open_hour;
            private String close_hour;
            private String open_status;
            private String Day_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOpen_hour() {
                return open_hour;
            }

            public void setOpen_hour(String open_hour) {
                this.open_hour = open_hour;
            }

            public String getClose_hour() {
                return close_hour;
            }

            public void setClose_hour(String close_hour) {
                this.close_hour = close_hour;
            }

            public String getOpen_status() {
                return open_status;
            }

            public void setOpen_status(String open_status) {
                this.open_status = open_status;
            }

            public String getDay_name() {
                return Day_name;
            }

            public void setDay_name(String Day_name) {
                this.Day_name = Day_name;
            }
        }
    }
}
