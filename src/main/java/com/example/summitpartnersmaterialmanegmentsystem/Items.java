package com.example.summitpartnersmaterialmanegmentsystem;

public class Items {
    private int id;
    private String itemCode;
    private String description;

    private String unitOfMeasure;
    private int quantity;

    private float unitPrice;
    private float totalValue;
    private String supplier;
    private int supplierInvoiceNo;

    public Items(int id, String itemCode, String description, String unitOfMeasure, int quantity, float unitPrice, float totalValue, String supplier, int supplierInvoiceNo) {
        this.id = id;
        this.itemCode = itemCode;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalValue = totalValue;
        this.supplier = supplier;
        this.supplierInvoiceNo = supplierInvoiceNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getSupplierInvoiceNo() {
        return supplierInvoiceNo;
    }

    public void setSupplierInvoiceNo(int supplierInvoiceNo) {
        this.supplierInvoiceNo = supplierInvoiceNo;
    }
}
