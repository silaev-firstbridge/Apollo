/*
 * Copyright © 2018-2019 Apollo Foundation
 */
package com.apollocurrency.aplwallet.apl.exchange.model;

import com.apollocurrency.aplwallet.api.dto.DexOrderDto;
import com.apollocurrency.aplwallet.apl.core.app.Transaction;
import com.apollocurrency.aplwallet.apl.core.transaction.messages.DexOrderAttachment;
import com.apollocurrency.aplwallet.apl.core.transaction.messages.DexOrderAttachmentV2;
import com.apollocurrency.aplwallet.apl.core.transaction.messages.DexOrderAttachmentV3;
import com.apollocurrency.aplwallet.apl.eth.utils.EthUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;


@Data
@Builder
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class DexOrder {
    private Long dbId;
    private Long id;
    @NonNull
    private Long accountId;
    @NonNull
    private String fromAddress;
    @NonNull
    private String toAddress;
    @NonNull
    private OrderType type;
    @NonNull
    private OrderStatus status;
    @NonNull
    private DexCurrencies orderCurrency;
    @NonNull
    private Long orderAmount;
    @NonNull
    private DexCurrencies pairCurrency;
    @NonNull
    private BigDecimal pairRate;
    @NonNull
    private Integer finishTime;

    private String freezeTxId;
  
    public DexOrder(){
    };
    
    public DexOrder(Transaction transaction, DexOrderAttachment dexOrderAttachment) {
        this.id = transaction.getId();
        this.accountId = transaction.getSenderId();
        this.type = OrderType.getType(dexOrderAttachment.getType());
        this.orderCurrency = DexCurrencies.getType(dexOrderAttachment.getOrderCurrency());
        this.orderAmount = dexOrderAttachment.getOrderAmount();
        this.pairCurrency = DexCurrencies.getType(dexOrderAttachment.getPairCurrency());
        this.pairRate = EthUtil.gweiToEth(dexOrderAttachment.getPairRate());
        this.status = OrderStatus.getType(dexOrderAttachment.getStatus());
        this.finishTime = dexOrderAttachment.getFinishTime();
        if (dexOrderAttachment instanceof DexOrderAttachmentV2) {
            this.fromAddress = ((DexOrderAttachmentV2) dexOrderAttachment).getFromAddress();
            this.toAddress = ((DexOrderAttachmentV2) dexOrderAttachment).getToAddress();
        }
        if (dexOrderAttachment instanceof DexOrderAttachmentV3) {
            this.fromAddress = ((DexOrderAttachmentV3) dexOrderAttachment).getFromAddress();
            this.toAddress = ((DexOrderAttachmentV3) dexOrderAttachment).getToAddress();
            this.freezeTxId = ((DexOrderAttachmentV3) dexOrderAttachment).getFreezeTxId();
        }        
    }

    public DexOrderDto toDto() {
        DexOrderDto dexOrderDto = new DexOrderDto();

        dexOrderDto.id = Long.toUnsignedString(this.getId());
        dexOrderDto.accountId = Long.toUnsignedString(this.getAccountId());
        dexOrderDto.fromAddress = this.getFromAddress();
        dexOrderDto.toAddress = this.getToAddress();
        dexOrderDto.type = this.getType().ordinal();
        dexOrderDto.offerCurrency = this.getOrderCurrency().ordinal();
        //TODO make changes on UI. Send Apl as apl.
        dexOrderDto.offerAmount = EthUtil.atmToGwei(this.getOrderAmount());
        dexOrderDto.pairCurrency = this.getPairCurrency().ordinal();
        dexOrderDto.finishTime = this.getFinishTime();
        dexOrderDto.status = this.getStatus().ordinal();
        //TODO make changes on UI. Send BigDecimal.
        dexOrderDto.pairRate = EthUtil.ethToGwei(this.getPairRate());
        
        dexOrderDto.freezeTxId = this.freezeTxId;
        return dexOrderDto;
    }

}
