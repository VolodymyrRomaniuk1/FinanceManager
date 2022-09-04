package org.financemanager.dto;

import org.financemanager.entity.Category;

import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Objects;

public class ReportReqDto {
    private Date dateStart;
    private Date dateEnd;
    @Pattern(regexp = "Spending|Gain")
    private String operationType;
    private Category category;

    public ReportReqDto(Date dateStart, Date dateEnd, String operationType, Category category) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.operationType = operationType;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportReqDto that = (ReportReqDto) o;
        return Objects.equals(getDateStart(), that.getDateStart()) && Objects.equals(getDateEnd(), that.getDateEnd()) && Objects.equals(getOperationType(), that.getOperationType()) && Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateStart(), getDateEnd(), getOperationType(), getCategory());
    }

    @Override
    public String toString() {
        return "ReportReqDto{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", operationType='" + operationType + '\'' +
                ", category=" + category +
                '}';
    }
}
