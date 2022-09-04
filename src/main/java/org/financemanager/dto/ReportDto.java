package org.financemanager.dto;

import org.financemanager.entity.Transaction;

import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class ReportDto {

    private List<Transaction> transactionList;
    private Date dateStart;
    private Date dateEnd;
    @Pattern(regexp = "Spending|Gain")
    private String operationType;
    private double totalSum;

    public ReportDto(List<Transaction> transactionList, Date dateStart, Date dateEnd, String operationType) {
        this.transactionList = transactionList;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.operationType = operationType;
        this.totalSum = calculateTotalSum();
    }

    public ReportDto(){}

    public double calculateTotalSum(){
        double totalSum = 0;
            for (Transaction transaction: transactionList
                 ) {
                totalSum += transaction.getSum();
            }
        return totalSum;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        this.totalSum = calculateTotalSum();
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDto reportDto = (ReportDto) o;
        return Double.compare(reportDto.getTotalSum(), getTotalSum()) == 0 && Objects.equals(getTransactionList(), reportDto.getTransactionList()) && getDateStart().equals(reportDto.getDateStart()) && getDateEnd().equals(reportDto.getDateEnd()) && getOperationType().equals(reportDto.getOperationType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionList(), getDateStart(), getDateEnd(), getOperationType(), getTotalSum());
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "transactionList=" + transactionList +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", operationType='" + operationType + '\'' +
                ", totalSum=" + totalSum +
                '}';
    }
}
