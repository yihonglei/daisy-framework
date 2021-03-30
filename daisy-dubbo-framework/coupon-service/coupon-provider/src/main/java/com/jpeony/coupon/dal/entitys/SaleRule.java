package com.jpeony.coupon.dal.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_sale_rule")
public class SaleRule implements Serializable {
    @Id
    private Long id;

    /**
     * 活动规则需要绑定 acti_id ，如果是优惠券规则， 此字段为null
     */
    @Column(name = "acti_id")
    private Long actiId;

    /**
     * 满足规则条件的商品id
     */
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "full_price")
    private BigDecimal fullPrice;

    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    @Column(name = "discount_rate")
    private BigDecimal discountRate;

    @Column(name = "gift_item_id")
    private Long giftItemId;

    /**
     * 是否可叠加规则
     */
    private Boolean overlap;

    private String desc;

    private Date created;

    private Date updated;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取活动规则需要绑定acti_id ，如果是优惠券规则， 此字段为null
     *
     * @return acti_id - 活动规则需要绑定acti_id ，如果是优惠券规则， 此字段为null
     */
    public Long getActiId() {
        return actiId;
    }

    /**
     * 设置活动规则需要绑定acti_id ，如果是优惠券规则， 此字段为null
     *
     * @param actiId 活动规则需要绑定acti_id ，如果是优惠券规则， 此字段为null
     */
    public void setActiId(Long actiId) {
        this.actiId = actiId;
    }

    /**
     * 获取满足规则条件的商品id
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 设置满足规则条件的商品id
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Long getGiftItemId() {
        return giftItemId;
    }

    public void setGiftItemId(Long giftItemId) {
        this.giftItemId = giftItemId;
    }

    /**
     * 获取是否可叠加规则
     */
    public Boolean getOverlap() {
        return overlap;
    }

    /**
     * 设置是否可叠加规则
     */
    public void setOverlap(Boolean overlap) {
        this.overlap = overlap;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}