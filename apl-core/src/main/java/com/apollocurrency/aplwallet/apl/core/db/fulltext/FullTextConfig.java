/*
 *  Copyright © 2018-2019 Apollo Foundation
 */

package com.apollocurrency.aplwallet.apl.core.db.fulltext;

import com.apollocurrency.aplwallet.apl.util.StringValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Singleton;

@Singleton
public class FullTextConfig {

    private List<String> tableNames = new ArrayList<>();
    private String schema = "PUBLIC";

    public void registerTable(String tableName) {
        StringValidator.requireNonBlank(tableName, "Table name");
        tableNames.add(tableName);
    }

    public void setSchema(String schema) {
        StringValidator.requireNonBlank(schema, "Schema");
        this.schema = schema;
    }

    public FullTextConfig() {}

    public List<String> getTableNames() {
        return Collections.unmodifiableList(tableNames);
    }

    public String getSchema() {
        return schema;
    }
}
