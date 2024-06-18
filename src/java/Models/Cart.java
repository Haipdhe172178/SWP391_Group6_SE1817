/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyca
 */
public class Cart {
    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    private int getQuantityById(int id) {
        Item item = getItemById(id);
        return item != null ? item.getQuantity() : 0;
    }

    private Item getItemById(int id) {
        for (Item i : items) {
            if (i.getProduct().getProductId() == id) {
                return i;
            }
        }
        return null;
    }

    public void addItem(Item t) {
        Item existingItem = getItemById(t.getProduct().getProductId());
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(int id) {
        Item item = getItemById(id);
        if (item != null) {
            items.remove(item);
        }
    }

    public double getTotalMoney() {
        double total = 0;
        for (Item i : items) {
            total += (i.getQuantity() * i.getPrice());
        }
        return total;
    }

    public Product getProductById(int id, List<Product> list) {
        for (Product i : list) {
            if (i.getProductId() == id) {
                return i;
            }
        }
        return null;
    }

    public Cart(String txt, List<Product> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) { // Giỏ hàng tồn tại
                String[] s = txt.split(",");
                for (String i : s) {
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    Product p = getProductById(id, list);
                    if (p != null) {
                        Item t = new Item(p, quantity, p.getPrice());
                        addItem(t);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Item item : items) {
        sb.append(item.getProduct().getProductId()).append(":").append(item.getQuantity()).append(",");
    }
    if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1); // Xóa dấu phẩy cuối cùng
    }
    return sb.toString();
}
}
