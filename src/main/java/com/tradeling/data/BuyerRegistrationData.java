package com.tradeling.data;

import com.tradeling.utilities.Utilities;

import java.util.Random;

public class BuyerRegistrationData {

    String userType;
    String companyName;
    String countryOfOperations;
    String email;
    String password;
    String firstName;
    String lastName;
    String department;
    String industry;
    String businessSize;
    String countryCode;
    String phone;
    String tradeLicenseExpiryMonth;
    String tradeLicenseExpiryDate;
    String tradeLicenseExpiryYear;

    String idExpiryMonth;
    String idExpiryDate;
    String idExpiryYear;

    String vatCertNumb;



    public BuyerRegistrationData(String userType, String countryOfOperations) {
        this.userType = userType;
        this.countryOfOperations = countryOfOperations;
        this.companyName = "Test auto company" + Utilities.createUniqueId(9999);
        this.email = "testauto" + Utilities.createUniqueId(9999) + "@test.com";
        this.password = "testpassword";
        this.firstName = "Test Auto F" + Utilities.createUniqueId(99);
        this.lastName = "Test Auto L" + Utilities.createUniqueId(99);
        this.department = "Administration";
        this.industry = "Automotive";
        this.businessSize = "5 - 10 People";
        this.countryCode = "";
        this.phone = "55555555";
        this.tradeLicenseExpiryMonth = Constants.MONTHS.get(new Random().nextInt(Constants.MONTHS.size()));
        this.tradeLicenseExpiryDate = Utilities.getRandomDate();
        this.idExpiryYear = this.tradeLicenseExpiryYear = Utilities.getYear(3);
        this.idExpiryMonth = Constants.MONTHS.get(new Random().nextInt(Constants.MONTHS.size()));
        this.idExpiryDate = Utilities.getRandomDate();
        this.vatCertNumb = "VATREFERENCE123";

    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountryOfOperations() {
        return countryOfOperations;
    }

    public void setCountryOfOperations(String countryOfOperations) {
        this.countryOfOperations = countryOfOperations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusinessSize() {
        return businessSize;
    }

    public void setBusinessSize(String businessSize) {
        this.businessSize = businessSize;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTradeLicenseExpiryMonth() {
        return tradeLicenseExpiryMonth;
    }

    public void setTradeLicenseExpiryMonth(String tradeLicenseExpiryMonth) {
        this.tradeLicenseExpiryMonth = tradeLicenseExpiryMonth;
    }

    public String getTradeLicenseExpiryDate() {
        return tradeLicenseExpiryDate;
    }

    public void setTradeLicenseExpiryDate(String tradeLicenseExpiryDate) {
        this.tradeLicenseExpiryDate = tradeLicenseExpiryDate;
    }

    public String getTradeLicenseExpiryYear() {
        return tradeLicenseExpiryYear;
    }

    public void setTradeLicenseExpiryYear(String tradeLicenseExpiryYear) {
        this.tradeLicenseExpiryYear = tradeLicenseExpiryYear;
    }

    public String getIdExpiryMonth() {
        return idExpiryMonth;
    }

    public void setIdExpiryMonth(String idExpiryMonth) {
        this.idExpiryMonth = idExpiryMonth;
    }

    public String getIdExpiryDate() {
        return idExpiryDate;
    }

    public void setIdExpiryDate(String idExpiryDate) {
        this.idExpiryDate = idExpiryDate;
    }

    public String getIdExpiryYear() {
        return idExpiryYear;
    }

    public void setIdExpiryYear(String idExpiryYear) {
        this.idExpiryYear = idExpiryYear;
    }

    public String getVatCertNumb() {
        return vatCertNumb;
    }

    public void setVatCertNumb(String vatCertNumb) {
        this.vatCertNumb = vatCertNumb;
    }
}
