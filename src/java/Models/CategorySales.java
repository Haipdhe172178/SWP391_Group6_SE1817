package Models;

public class CategorySales {
    private int categoryId;
    private String categoryName;
    private int totalQuantitySold;

    public CategorySales() {
    }

    public CategorySales(int categoryId, String categoryName, int totalQuantitySold) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.totalQuantitySold = totalQuantitySold;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(int totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
    }
}
