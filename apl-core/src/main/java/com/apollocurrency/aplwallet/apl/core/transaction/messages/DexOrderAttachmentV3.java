package com.apollocurrency.aplwallet.apl.core.transaction.messages;

import com.apollocurrency.aplwallet.apl.crypto.Convert;
import com.apollocurrency.aplwallet.apl.crypto.NotValidException;
import com.apollocurrency.aplwallet.apl.exchange.model.DexOrder;
import com.apollocurrency.aplwallet.apl.util.AplException;
import com.apollocurrency.aplwallet.apl.util.Constants;
import org.json.simple.JSONObject;

import java.nio.ByteBuffer;

public class DexOrderAttachmentV3 extends DexOrderAttachmentV2 {

    private String freezeTxId;

    public DexOrderAttachmentV3(DexOrder order) {
        super(order);
        this.freezeTxId = order.getFreezeTxId();
    }

    public DexOrderAttachmentV3(ByteBuffer buffer) throws AplException.NotValidException {
        super(buffer);
        try{
            this.freezeTxId = Convert.readString(buffer, buffer.getShort(), Constants.MAX_ADDRESS_LENGTH);
        } catch (NotValidException ex) {
            throw new AplException.NotValidException(ex.getMessage());
        }
    }

    public DexOrderAttachmentV3(JSONObject attachmentData) {
        super(attachmentData);
        this.freezeTxId = String.valueOf(attachmentData.get("freezeTxId"));
    }

    @Override
    public int getMySize() {
        return super.getMySize() + Convert.toBytes(freezeTxId).length + 2;
    }

    @Override
    public void putMyBytes(ByteBuffer buffer) {
        super.putMyBytes(buffer);

        byte[] freezeTxId = Convert.toBytes(this.freezeTxId);
        buffer.putShort((short) freezeTxId.length);
        buffer.put(freezeTxId);

    }

    @Override
    public void putMyJSON(JSONObject json) {
        super.putMyJSON(json);
        json.put("freezeTxId", this.freezeTxId);
    }

    @Override
    public byte getVersion() {
        return 3;
    }

    @Override
    public String getAppendixName() {
        return "DexOrder_v3";
    }

    public String getFreezeTxId() {
        return freezeTxId;
    }
    
    public void setFreezeTxId(String freezeTxId) {
        this.freezeTxId = freezeTxId;
    }

  }
