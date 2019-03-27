package com.iprismtech.tahyba.pojo;

public class LoginPojo {

    /**
     * status : true
     * message : User Login Successfuly!
     * response : {"id":"18","autho_unic_id":"app190221180317","login_source":"app","full_name":"srikanth","email":"kallojisrikanth@gmail.com","mobile":"8886032595","password":"e10adc3949ba59abbe56e057f20f883e","image":"storage/default-no-image.png","dob":null,"created_on":"2019-02-21 18:03:17","status":"1","temp_pass":null}
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
         * id : 18
         * autho_unic_id : app190221180317
         * login_source : app
         * full_name : srikanth
         * email : kallojisrikanth@gmail.com
         * mobile : 8886032595
         * password : e10adc3949ba59abbe56e057f20f883e
         * image : storage/default-no-image.png
         * dob : null
         * created_on : 2019-02-21 18:03:17
         * status : 1
         * temp_pass : null
         */

        private String id;
        private String autho_unic_id;
        private String login_source;
        private String full_name;
        private String email;
        private String mobile;
        private String password;
        private String image;
        private Object dob;
        private String created_on;
        private String status;
        private Object temp_pass;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAutho_unic_id() {
            return autho_unic_id;
        }

        public void setAutho_unic_id(String autho_unic_id) {
            this.autho_unic_id = autho_unic_id;
        }

        public String getLogin_source() {
            return login_source;
        }

        public void setLogin_source(String login_source) {
            this.login_source = login_source;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Object getDob() {
            return dob;
        }

        public void setDob(Object dob) {
            this.dob = dob;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getTemp_pass() {
            return temp_pass;
        }

        public void setTemp_pass(Object temp_pass) {
            this.temp_pass = temp_pass;
        }
    }
}
