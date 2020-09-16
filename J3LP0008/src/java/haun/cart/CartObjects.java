/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.cart;

import haun.tour.TourDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author msi
 */
public class CartObjects implements Serializable {

    private Map<TourDTO, Integer> items;

    public Map<TourDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(TourDTO tourDto) {
        if (tourDto == null) {
            return;
        }

        if (this.items == null) {
            this.items = new HashMap<>();
        }
        int quantity = 1;
        
        for(TourDTO key : this.items.keySet()){
            if(key.getTourID().equalsIgnoreCase(tourDto.getTourID())){
                tourDto = key;
                quantity = this.items.get(tourDto) + 1;
                  
            }
        }
        
        this.items.put(tourDto, quantity);
    }
    
    public void removeSelectedItem(String tourID){
        if(this.items == null){
            return;
        }
        Iterator itr = this.items.keySet().iterator();
        while(itr.hasNext()){
            TourDTO dto = (TourDTO) itr.next();
            if(dto.getTourID().equalsIgnoreCase(tourID)){
                itr.remove();
                if(this.items.isEmpty()){
                    this.items = null;
                }
            }
        }
    }
    
    public void updateCart(String tourID, int amount){
        if(this.items == null){
            return;
        }
        
        for (TourDTO dto : this.items.keySet()) {
            if(dto.getTourID().equalsIgnoreCase(tourID)){
                this.items.put(dto, amount);
            }
        }
    }
    
    public float calculateTotalCarts(){
        float totalCarts = 0;
        if(this.items == null){
            return 0;
        }
        
        for(TourDTO dto : this.items.keySet()){
            float totalCart = dto.getPrice() * this.items.get(dto);
            totalCarts += totalCart;
        }
        return totalCarts;
    }
    
    public int totalAmountCart(){
        int totalAmountCart = 0;
        if(this.items == null){
            return 0;
        }
        for(TourDTO dto : this.items.keySet()){
            totalAmountCart += this.items.get(dto);
        }
        return totalAmountCart;        
    }
}
