/*
 * Copyright © 2018 Apollo Foundation
 */

package com.apollocurrency.aplwallet.apl.util.env.dirprovider;

import lombok.ToString;

@ToString(callSuper = true)
public class UnixConfigDirProvider extends DefaultConfigDirProvider {

    public UnixConfigDirProvider(String applicationName, boolean isService,int netIdx) {
        super(applicationName, isService,netIdx);
    }

    @Override
    public String getSysConfigDirectory() {
        return "/etc/" + applicationName+"/"+getConfigDirectoryName();
    }
}
