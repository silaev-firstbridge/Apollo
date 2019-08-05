/*
 * Copyright © 2018-2019 Apollo Foundation.
 */
package com.apollocurrency.aplwallet.apl.core.account.dao;

import com.apollocurrency.aplwallet.apl.core.account.model.AccountInfo;
import com.apollocurrency.aplwallet.apl.core.db.DbClause;
import com.apollocurrency.aplwallet.apl.core.db.DbIterator;
import com.apollocurrency.aplwallet.apl.core.db.DbKey;
import com.apollocurrency.aplwallet.apl.core.db.DbUtils;
import com.apollocurrency.aplwallet.apl.core.db.LongKeyFactory;
import com.apollocurrency.aplwallet.apl.core.db.derived.VersionedDeletableEntityDbTable;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author al
 */
@Singleton
public class AccountInfoTable extends VersionedDeletableEntityDbTable<AccountInfo> {

    private static final LongKeyFactory<AccountInfo> accountInfoDbKeyFactory = new LongKeyFactory<AccountInfo>("account_id") {

        @Override
        public DbKey newKey(AccountInfo accountInfo) {
            return accountInfo.getDbKey() == null ? newKey(accountInfo.getAccountId()) : accountInfo.getDbKey();
        }

    };
       
    public static DbKey newKey(long id){
        return accountInfoDbKeyFactory.newKey(id);
    }

    public AccountInfoTable() {
        super("account_info",
            accountInfoDbKeyFactory, "name,description");
    }

    @Override
    public AccountInfo load(Connection con, ResultSet rs, DbKey dbKey) throws SQLException {
        return new AccountInfo(rs, dbKey);
    }

    @Override
    public void save(Connection con, AccountInfo accountInfo) throws SQLException {
        try (final PreparedStatement pstmt = con.prepareStatement("MERGE INTO account_info " + "(account_id, name, description, height, latest) " + "KEY (account_id, height) VALUES (?, ?, ?, ?, TRUE)")) {
            int i = 0;
            pstmt.setLong(++i, accountInfo.getAccountId());
            DbUtils.setString(pstmt, ++i, accountInfo.getName());
            DbUtils.setString(pstmt, ++i, accountInfo.getDescription());
            pstmt.setInt(++i, accountInfo.getHeight());
            pstmt.executeUpdate();
        }
    }

    public DbIterator<AccountInfo> searchAccounts(String query, int from, int to) {
        return search(query, DbClause.EMPTY_CLAUSE, from, to);
    }
}