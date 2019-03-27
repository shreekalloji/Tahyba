package com.iprismtech.tahyba.pojo;

import java.util.List;

public class ShopsListPojo {

    /**
     * status : true
     * message : Shops found
     * response : [{"short_distance":"8.882","shop_id":"vw19225259","image":"storage/vender-shop-image/1548145489_vend4967389.jpg","shop_name":"Simple","address":"At/po :kharanipada, via:surangi,dist:ganjam,odisha,761037","landline":"0680-326236","mobile":"7077107010","other_no":"7793904868","location":"Ghaziabad","sub_location":"Chander Nagar","Onlydate":"22 Jan 2019","avgRate":"3.3","open_hour":"From","close_hour":"To","open_status":"0","YourFav":"NO"},{"short_distance":"1248.617","shop_id":"vweb1810191245","image":"storage/vender-shop-image/1548056781_vweb1810191245369.jpeg","shop_name":"Abhishek jewellers","address":"Shop no. 989 , Guru Ravidas Marg , Kalkaji , Govindpuri","landline":"0680-326236","mobile":"7077101010","other_no":"7793145545","location":"Delhi","sub_location":"Pitampura","Onlydate":"19 Oct 2018","avgRate":"0.0","open_hour":null,"close_hour":null,"open_status":null,"YourFav":"NO"}]
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
         * short_distance : 8.882
         * shop_id : vw19225259
         * image : storage/vender-shop-image/1548145489_vend4967389.jpg
         * shop_name : Simple
         * address : At/po :kharanipada, via:surangi,dist:ganjam,odisha,761037
         * landline : 0680-326236
         * mobile : 7077107010
         * other_no : 7793904868
         * location : Ghaziabad
         * sub_location : Chander Nagar
         * Onlydate : 22 Jan 2019
         * avgRate : 3.3
         * open_hour : From
         * close_hour : To
         * open_status : 0
         * YourFav : NO
         */

        private String short_distance;
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
        private String avgRate;
        private String open_hour;
        private String close_hour;
        private String open_status;
        private String YourFav;

        public String getShort_distance() {
            return short_distance;
        }

        public void setShort_distance(String short_distance) {
            this.short_distance = short_distance;
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
}
