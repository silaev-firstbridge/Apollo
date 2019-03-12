package com.apollocurrency.aplwallet.api.response;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;


@JsonInclude(JsonInclude.Include.NON_NULL)
//@ApiModel("List of scheduled transaction with optional attachment data")
public class GetAccountResponse extends ResponseBase {
    public String balanceATM;
    public String forgedBalanceATM;
    public String accountRS;
    public String publicKey;
    public String unconfirmedBalanceATM;
    public String account;
    public long numberOfBlocks;
    public long requestProcessingTime;
    public String name;
    public boolean is2FA;
    public BigInteger currentLessee;
    public String currentLesseeRS;
    public long currentLeasingHeightTo;
    public long currentLeasingHeightFrom;
}