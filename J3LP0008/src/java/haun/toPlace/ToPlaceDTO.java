/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.toPlace;

import java.io.Serializable;

/**
 *
 * @author msi
 */
public class ToPlaceDTO implements Serializable {

    private String toPlaceID;
    private String toPlaceName;

    public ToPlaceDTO(String toPlaceID, String toPlaceName) {
        this.toPlaceID = toPlaceID;
        this.toPlaceName = toPlaceName;
    }

    /**
     * @return the toPlaceName
     */
    public String getToPlaceName() {
        return toPlaceName;
    }

    /**
     * @param toPlaceName the toPlaceName to set
     */
    public void setToPlaceName(String toPlaceName) {
        this.toPlaceName = toPlaceName;
    }

    /**
     * @return the toPlaceID
     */
    public String getToPlaceID() {
        return toPlaceID;
    }

    /**
     * @param toPlaceID the toPlaceID to set
     */
    public void setToPlaceID(String toPlaceID) {
        this.toPlaceID = toPlaceID;
    }

}
