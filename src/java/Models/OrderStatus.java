package Models;

public class OrderStatus {
    private int statusId;
    private String statusName;
    private int pendingCount;

    public OrderStatus() {
    }

    public OrderStatus(int statusId, String statusName, int pendingCount) {
        this.statusId = statusId;
        this.statusName = statusName;
        this.pendingCount = pendingCount;
    }

    public int getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
 
}
