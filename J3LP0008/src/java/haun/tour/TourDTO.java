/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.tour;

import java.io.Serializable;

/**
 *
 * @author msi
 */
public class TourDTO implements Serializable {

    private String tourID;
    private String tourName;
    private float price;
    private String fromDate;
    private String toDate;
    private int fromPlaceID;
    private int toPlaceID;
    private String image;

    public TourDTO(String tourID, String tourName, float price, String fromDate, String toDate, int fromPlaceID, int toPlaceID, String image) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.price = price;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromPlaceID = fromPlaceID;
        this.toPlaceID = toPlaceID;
        this.image = image;
    }

    /**
     * @return the tourID
     */
    public String getTourID() {
        return tourID;
    }

    /**
     * @param tourID the tourID to set
     */
    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    /**
     * @return the tourName
     */
    public String getTourName() {
        return tourName;
    }

    /**
     * @param tourName the tourName to set
     */
    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the fromPlaceID
     */
    public int getFromPlaceID() {
        return fromPlaceID;
    }

    /**
     * @param fromPlaceID the fromPlaceID to set
     */
    public void setFromPlaceID(int fromPlaceID) {
        this.fromPlaceID = fromPlaceID;
    }

    /**
     * @return the toPlaceID
     */
    public int getToPlaceID() {
        return toPlaceID;
    }

    /**
     * @param toPlaceID the toPlaceID to set
     */
    public void setToPlaceID(int toPlaceID) {
        this.toPlaceID = toPlaceID;
    }

    /**
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

}
