package com.iprismtech.tahyba.pojo;

public class OTPgenerationPojo {

    /**
     * status : true
     * message : User existing!
     * response : 1
     * OTP : {"otp":"GOLD4416"}
     * user_data : {"id":"2","autho_unic_id":"app181228194241","login_source":"app","full_name":"pinku sahu","email":"taparanjansah703@gmail.com","mobile":"7793904866","password":"4297f44b13955235245b2497399d7a93","image":"storage/user_pf_doc/5c262f59acc58.png","dob":null,"created_on":"2018-12-28 19:42:41","status":"1","temp_pass":null}
     */

    private boolean status;
    private String message;
    private int response;
    private OTPBean OTP;
    private UserDataBean user_data;

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

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public OTPBean getOTP() {
        return OTP;
    }

    public void setOTP(OTPBean OTP) {
        this.OTP = OTP;
    }

    public UserDataBean getUser_data() {
        return user_data;
    }

    public void setUser_data(UserDataBean user_data) {
        this.user_data = user_data;
    }

    public static class OTPBean {
        /**
         * otp : GOLD4416
         */

        private int otp;

        public int getOtp() {
            return otp;
        }

        public void setOtp(int otp) {
            this.otp = otp;
        }
    }

    public static class UserDataBean {
        /**
         * id : 2
         * autho_unic_id : app181228194241
         * login_source : app
         * full_name : pinku sahu
         * email : taparanjansah703@gmail.com
         * mobile : 7793904866
         * password : 4297f44b13955235245b2497399d7a93
         * image : storage/user_pf_doc/5c262f59acc58.png
         * dob : null
         * created_on : 2018-12-28 19:42:41
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
