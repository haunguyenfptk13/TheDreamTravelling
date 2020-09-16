/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.tour;

/**
 *
 * @author msi
 */
public class TourCreateError {
    private String tourIDLengthErr;
    private String tourIDIsExisted;
    private String tourNameLengthErr;
    private String fromDateLengthErr;
    private String toDateLengthErr;
    private String priceFormatErr;
    private String quotaFormatErr;

    public TourCreateError() {
    }

    /**
     * @return the tourIDLengthErr
     */
    public String getTourIDLengthErr() {
        return tourIDLengthErr;
    }

    /**
     * @param tourIDLengthErr the tourIDLengthErr to set
     */
    public void setTourIDLengthErr(String tourIDLengthErr) {
        this.tourIDLengthErr = tourIDLengthErr;
    }

    /**
     * @return the tourNameLengthErr
     */
    public String getTourNameLengthErr() {
        return tourNameLengthErr;
    }

    /**
     * @param tourNameLengthErr the tourNameLengthErr to set
     */
    public void setTourNameLengthErr(String tourNameLengthErr) {
        this.tourNameLengthErr = tourNameLengthErr;
    }

    /**
     * @return the fromDateLengthErr
     */
    public String getFromDateLengthErr() {
        return fromDateLengthErr;
    }

    /**
     * @param fromDateLengthErr the fromDateLengthErr to set
     */
    public void setFromDateLengthErr(String fromDateLengthErr) {
        this.fromDateLengthErr = fromDateLengthErr;
    }

    /**
     * @return the toDateLengthErr
     */
    public String getToDateLengthErr() {
        return toDateLengthErr;
    }

    /**
     * @param toDateLengthErr the toDateLengthErr to set
     */
    public void setToDateLengthErr(String toDateLengthErr) {
        this.toDateLengthErr = toDateLengthErr;
    }

    /**
     * @return the priceFormatErr
     */
    public String getPriceFormatErr() {
        return priceFormatErr;
    }

    /**
     * @param priceFormatErr the priceFormatErr to set
     */
    public void setPriceFormatErr(String priceFormatErr) {
        this.priceFormatErr = priceFormatErr;
    }

    /**
     * @return the quotaFormatErr
     */
    public String getQuotaFormatErr() {
        return quotaFormatErr;
    }

    /**
     * @param quotaFormatErr the quotaFormatErr to set
     */
    public void setQuotaFormatErr(String quotaFormatErr) {
        this.quotaFormatErr = quotaFormatErr;
    }

    /**
     * @return the tourIDIsExisted
     */
    public String getTourIDIsExisted() {
        return tourIDIsExisted;
    }

    /**
     * @param tourIDIsExisted the tourIDIsExisted to set
     */
    public void setTourIDIsExisted(String tourIDIsExisted) {
        this.tourIDIsExisted = tourIDIsExisted;
    }
    
}
