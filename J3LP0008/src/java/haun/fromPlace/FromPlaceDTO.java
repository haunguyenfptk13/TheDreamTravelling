/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.fromPlace;

import java.io.Serializable;

/**
 *
 * @author msi
 */
public class FromPlaceDTO implements Serializable{
    private String fromPlaceID;
    private String fromPlaceName;

    public FromPlaceDTO(String fromPlaceID, String fromPlaceName) {
        this.fromPlaceID = fromPlaceID;
        this.fromPlaceName = fromPlaceName;
    }


    /**
     * @return the fromPlaceName
     */
    public String getFromPlaceName() {
        return fromPlaceName;
    }

    /**
     * @param fromPlaceName the fromPlaceName to set
     */
    public void setFromPlaceName(String fromPlaceName) {
        this.fromPlaceName = fromPlaceName;
    }

    /**
     * @return the fromPlaceID
     */
    public String getFromPlaceID() {
        return fromPlaceID;
    }

    /**
     * @param fromPlaceID the fromPlaceID to set
     */
    public void setFromPlaceID(String fromPlaceID) {
        this.fromPlaceID = fromPlaceID;
    }

    
}
