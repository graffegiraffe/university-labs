package by.rublevskaya.banksystem.model.document;

public class Check {
    private String checkNumber;
    private String transactionDetails;

    public Check(String checkNumber, String transactionDetails) {
        if (checkNumber == null || checkNumber.isEmpty()) {
            throw new IllegalArgumentException("Check number cannot be null or empty");
        }
        if (transactionDetails == null || transactionDetails.isEmpty()) {
            throw new IllegalArgumentException("Transaction details cannot be null or empty");
        }
        this.checkNumber = checkNumber;
        this.transactionDetails = transactionDetails;
    }

    public void printCheckInfo() {
        System.out.println("Check Number: " + checkNumber);
        System.out.println("Transaction Details: " + transactionDetails);
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkNumber='" + checkNumber + '\'' +
                ", transactionDetails='" + transactionDetails + '\'' +
                '}';
    }

    public String getDescription() {
        return "Check Number: " + checkNumber + ", Transaction Details: " + transactionDetails;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        if (checkNumber == null || checkNumber.isEmpty()) {
            throw new IllegalArgumentException("Check number cannot be null or empty");
        }
        this.checkNumber = checkNumber;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        if (transactionDetails == null || transactionDetails.isEmpty()) {
            throw new IllegalArgumentException("Transaction details cannot be null or empty");
        }
        this.transactionDetails = transactionDetails;
    }

}