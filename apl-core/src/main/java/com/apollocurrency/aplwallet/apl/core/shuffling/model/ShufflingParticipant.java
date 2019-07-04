/*
 *  Copyright © 2018-2019 Apollo Foundation
 */

package com.apollocurrency.aplwallet.apl.core.shuffling.model;

import com.apollocurrency.aplwallet.apl.core.app.ShufflingParticipantService;
import com.apollocurrency.aplwallet.apl.core.db.model.VersionedDerivedEntity;
import com.apollocurrency.aplwallet.apl.crypto.Convert;

import java.util.Arrays;
import java.util.Objects;

public class ShufflingParticipant extends VersionedDerivedEntity {
    private final long shufflingId;
    private final long accountId; // sender account
    private final int index;

    private long nextAccountId; // pointer to the next shuffling participant updated during registration
    private ShufflingParticipantService.State state; // tracks the state of the participant in the process
    private byte[][] blameData; // encrypted data saved as intermediate result in the shuffling process
    private byte[][] keySeeds; // to be revealed only if shuffle is being cancelled
    private byte[] dataTransactionFullHash;
    private byte[] dataHash; // hash of the processing data from ShufflingProcessingAttachment

    private ShufflingParticipant(long shufflingId, long accountId, int index, int height) {
        super(null, height);
        this.shufflingId = shufflingId;
        this.accountId = accountId;
        this.index = index;
        this.state = ShufflingParticipantService.State.REGISTERED;
        this.blameData = Convert.EMPTY_BYTES;
        this.keySeeds = Convert.EMPTY_BYTES;
    }

    public ShufflingParticipant(Long dbId, Integer height, long shufflingId, long accountId, int index, long nextAccountId, ShufflingParticipantService.State state, byte[][] blameData, byte[][] keySeeds, byte[] dataTransactionFullHash, byte[] dataHash) {
        super(dbId, height);
        this.shufflingId = shufflingId;
        this.accountId = accountId;
        this.index = index;
        this.nextAccountId = nextAccountId;
        this.state = state;
        this.blameData = blameData;
        this.keySeeds = keySeeds;
        this.dataTransactionFullHash = dataTransactionFullHash;
        this.dataHash = dataHash;
    }

    public long getShufflingId() {
        return shufflingId;
    }

    public long getAccountId() {
        return accountId;
    }

    public int getIndex() {
        return index;
    }

    public long getNextAccountId() {
        return nextAccountId;
    }

    public void setNextAccountId(long nextAccountId) {
        this.nextAccountId = nextAccountId;
    }

    public ShufflingParticipantService.State getState() {
        return state;
    }

    public void setState(ShufflingParticipantService.State state) {
        this.state = state;
    }

    public byte[][] getBlameData() {
        return blameData;
    }

    public void setBlameData(byte[][] blameData) {
        this.blameData = blameData;
    }

    public byte[][] getKeySeeds() {
        return keySeeds;
    }

    public void setKeySeeds(byte[][] keySeeds) {
        this.keySeeds = keySeeds;
    }

    public byte[] getDataTransactionFullHash() {
        return dataTransactionFullHash;
    }

    public void setDataTransactionFullHash(byte[] dataTransactionFullHash) {
        this.dataTransactionFullHash = dataTransactionFullHash;
    }

    public byte[] getDataHash() {
        return dataHash;
    }

    void setDataHash(byte[] dataHash) {
        if (this.dataHash != null) {
            throw new IllegalStateException("dataHash already set");
        }
        this.dataHash = dataHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShufflingParticipant)) return false;
        if (!super.equals(o)) return false;
        ShufflingParticipant that = (ShufflingParticipant) o;
        return shufflingId == that.shufflingId &&
                accountId == that.accountId &&
                index == that.index &&
                nextAccountId == that.nextAccountId &&
                state == that.state &&
                Arrays.equals(blameData, that.blameData) &&
                Arrays.equals(keySeeds, that.keySeeds) &&
                Arrays.equals(dataTransactionFullHash, that.dataTransactionFullHash) &&
                Arrays.equals(dataHash, that.dataHash);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), shufflingId, accountId, index, nextAccountId, state);
        result = 31 * result + Arrays.hashCode(blameData);
        result = 31 * result + Arrays.hashCode(keySeeds);
        result = 31 * result + Arrays.hashCode(dataTransactionFullHash);
        result = 31 * result + Arrays.hashCode(dataHash);
        return result;
    }
}