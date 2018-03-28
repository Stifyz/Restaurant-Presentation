package com.example.stify.testdintegration;

public class MenuCell {

    final private String _name;
    final private String _category;
    final private double _price;
    final private String _describe;

    // Constructor for basics cells
    public MenuCell(String name, String category, double price, String describe) {
        _name = name;
        _category = category;
        _price = price;
        _describe = describe;
    }

    public String getName() {
        return _name;
    }

    public String getCategory() {
        return _category;
    }

    public Double getPrice() {
        return _price;
    }

    public String getDescribe() {
        return _describe;
    }
}
