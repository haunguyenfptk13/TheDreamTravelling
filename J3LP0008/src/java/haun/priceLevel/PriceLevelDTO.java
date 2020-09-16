/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.priceLevel;

import java.io.Serializable;

/**
 *
 * @author msi
 */
public class PriceLevelDTO implements Serializable{
    private int priceLevel;
    private String priceLevelName;
    private float priceFrom;
    private float priceTo;

    public PriceLevelDTO(int priceLevel, String priceLevelName, float priceFrom, float priceTo) {
        this.priceLevel = priceLevel;
        this.priceLevelName = priceLevelName;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    /**
     * @return the priceLevel
     */
    public int getPriceLevel() {
        return priceLevel;
    }

    /**
     * @param priceLevel the priceLevel to set
     */
    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    /**
     * @return the priceLevelName
     */
    public String getPriceLevelName() {
        return priceLevelName;
    }

    /**
     * @param priceLevelName the priceLevelName to set
     */
    public void setPriceLevelName(String priceLevelName) {
        this.priceLevelName = priceLevelName;
    }

    /**
     * @return the priceFrom
     */
    public float getPriceFrom() {
        return priceFrom;
    }

    /**
     * @param priceFrom the priceFrom to set
     */
    public void setPriceFrom(float priceFrom) {
        this.priceFrom = priceFrom;
    }

    /**
     * @return the priceTo
     */
    public float getPriceTo() {
        return priceTo;
    }

    /**
     * @param priceTo the priceTo to set
     */
    public void setPriceTo(float priceTo) {
        this.priceTo = priceTo;
    }
    
}
