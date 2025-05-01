import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voucher {
    private Long id;
    private Long shopId;
    private String title;
    private String subTitle;
    private String rules;
    private Integer payValue;
    private Integer actualValue;
    private Integer type;
    private Integer stock;
    private OffsetDateTime beginTime;
    private OffsetDateTime endTime;

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Integer getPayValue() {
        return payValue;
    }

    public void setPayValue(Integer payValue) {
        this.payValue = payValue;
    }

    public Integer getActualValue() {
        return actualValue;
    }

    public void setActualValue(Integer actualValue) {
        this.actualValue = actualValue;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public OffsetDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(OffsetDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voucher voucher = (Voucher) o;
        return Objects.equals(id, voucher.id) &&
                Objects.equals(shopId, voucher.shopId) &&
                Objects.equals(title, voucher.title) &&
                Objects.equals(subTitle, voucher.subTitle) &&
                Objects.equals(rules, voucher.rules) &&
                Objects.equals(payValue, voucher.payValue) &&
                Objects.equals(actualValue, voucher.actualValue) &&
                Objects.equals(type, voucher.type) &&
                Objects.equals(stock, voucher.stock) &&
                Objects.equals(beginTime, voucher.beginTime) &&
                Objects.equals(endTime, voucher.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shopId, title, subTitle, rules, payValue, actualValue, type, stock, beginTime, endTime);
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", rules='" + rules + '\'' +
                ", payValue=" + payValue +
                ", actualValue=" + actualValue +
                ", type=" + type +
                ", stock=" + stock +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}