package com.apollocurrency.aplwallet.apl.util.env.config;

import com.apollocurrency.aplwallet.apl.util.env.dirprovider.DefaultConfigDirProvider;
import com.apollocurrency.aplwallet.apl.util.env.dirprovider.UnixConfigDirProvider;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConfigurationLoadServiceTest {
    private static UUID chainId = UUID.fromString("3fecf3bd-86a3-436b-a1d6-41eefc0bd1c6");
    public static final String APPLICATION_NAME = "test-app-blch";
    public static final String APPLICATION_ADMIN_NAME = "test-app-admin";
    private static final String CONFIG_NAME = "test-chains.json";

    private List<String> systemPropertiesNames = List.of("apl.key2");;
    private ConfigurationLoadService configurationLoadService;

    @Test
    void loadUnixConfig() {
        UnixConfigDirProvider unixConfigDirProvider = new UnixConfigDirProvider(APPLICATION_NAME, false,0);
        configurationLoadService = new ConfigurationLoadService(
            unixConfigDirProvider,
            false,
            unixConfigDirProvider.getConfigDirectory(),
            systemPropertiesNames,
            APPLICATION_NAME + ".properties", APPLICATION_ADMIN_NAME + ".properties", CONFIG_NAME
        );
        Properties props = configurationLoadService.propertiesLoad();
        assertNotNull(props);
        assertEquals(2, props.size());
        Map<UUID, Chain> chains = configurationLoadService.chainsLoad();
        assertNotNull(chains);
    }

    @Test
    void loadDefaultConfig() {
        DefaultConfigDirProvider defaultConfigDirProvider =  new DefaultConfigDirProvider(APPLICATION_NAME, true,0);
        configurationLoadService = new ConfigurationLoadService(
            defaultConfigDirProvider,
            false,
            defaultConfigDirProvider.getConfigDirectory(),
            systemPropertiesNames,
            APPLICATION_NAME + ".properties", APPLICATION_ADMIN_NAME + ".properties", CONFIG_NAME
        );
        Properties props = configurationLoadService.propertiesLoad();
        assertNotNull(props);
        assertEquals(2, props.size());
        Map<UUID, Chain> chains = configurationLoadService.chainsLoad();
        assertNotNull(chains);
    }
}