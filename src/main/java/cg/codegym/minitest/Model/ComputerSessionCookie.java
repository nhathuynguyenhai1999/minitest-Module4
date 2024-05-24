package cg.codegym.minitest.Model;

import java.util.HashMap;
import java.util.Map;

public class ComputerSessionCookie {
    //Session - cookie
    private Map<Computer,Integer> productTypes = new HashMap<>();
    public ComputerSessionCookie(Map<Computer,Integer> productTypes){
        this.productTypes = productTypes;
    }
    public Map<Computer,Integer> getProductTypes(){
        return productTypes;
    }
    private boolean checkItemCard(Computer computer){
        for (Map.Entry<Computer,Integer> entry: productTypes.entrySet()){
            if(entry.getKey().getId().equals(computer.getId())){
                return true;
            }
        }
        return false;
    }
    private Map.Entry<Computer, Integer> selectItemInCart(Computer computer){
        for(Map.Entry<Computer, Integer> entry : productTypes.entrySet()){
            if(entry.getKey().getId().equals(computer.getId())){
                return entry;
            }
        }
        return null;
    }
    public void addComputer(Computer computer) {
        if (!checkItemCard(computer)){
            productTypes.put(computer,1);
        }
        else {
            Map.Entry<Computer,Integer> itemEntry = selectItemInCart(computer);
            assert itemEntry != null;
            Integer newQuantity = itemEntry.getValue() + 1;
            productTypes.replace(itemEntry.getKey(), newQuantity);
        }
    }
    public Integer countListQuantity(){
        Integer productQuantity = 0;
        for (Map.Entry<Computer, Integer> entry : productTypes.entrySet()){
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }
    public Integer countItemQuantity(){
        Integer productQuantity = 0;
        for (Map.Entry<Computer,Integer> entry : productTypes.entrySet()){
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }
    public Integer countItemProductQuantity(){
        return productTypes.size();
    }
    public Float countTotalPayment(){
        float payment = 0;
        for (Map.Entry<Computer,Integer> entry : productTypes.entrySet()){
            payment += entry.getKey().getId() * (float) entry.getValue();
        }
        return payment;
    }
}
