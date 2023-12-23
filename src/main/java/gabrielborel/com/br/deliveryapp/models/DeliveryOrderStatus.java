package gabrielborel.com.br.deliveryapp.models;

public enum DeliveryOrderStatus {
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    DELIVERED("Delivered"),
    CANCELED("Canceled");

    private final String formattedStatus;

    DeliveryOrderStatus(String formattedStatus) {
        this.formattedStatus = formattedStatus;
    }

    public String getStatus() {
        return formattedStatus;
    }
}
