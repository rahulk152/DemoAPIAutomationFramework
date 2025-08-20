package com.api.models.request;

public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public ChangePasswordRequest(String currentPassword, String confirmPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.confirmPassword = confirmPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "ChangePasswordRequest{" +
                "currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }

    public static class Builder{
        private String currentPassword;
        private String newPassword;
        private String confirmPassword;

        public Builder currentPassword(String currentPassword){
            this.currentPassword = currentPassword;
            return this;
        }
        public Builder newPassword(String newPassword){
            this.newPassword = newPassword;
            return this;
        }
        public Builder confirmPassword(String confirmPassword){
            this.confirmPassword = confirmPassword;
            return this;
        }

        public ChangePasswordRequest Build(){
            ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(currentPassword, newPassword, confirmPassword);
            return changePasswordRequest;
        }
    }
}
