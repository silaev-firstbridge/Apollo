package com.apollocurrency.aplwallet.apl.util.env.config;

import javax.enterprise.inject.Vetoed;
import javax.inject.Singleton;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Stream;

import com.apollocurrency.aplwallet.apl.util.env.dirprovider.ConfigDirProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class ConfigurationLoadService {

    private PropertiesConfigLoader propertiesLoader;
    private ChainsConfigLoader chainsConfigLoader;

    public ConfigurationLoadService() { // needed for CDI
    }

    /**
     * Component's constructor for manual creation.
     *
     * @param configDirProvider
     * @param ignoreResources
     * @param configDir
     * @param systemPropertiesNames
     * @param resourcesName several 'properties' ot 'json' recource files can be mixed here
     */
    public ConfigurationLoadService(ConfigDirProvider configDirProvider, boolean ignoreResources, String configDir,
                                    List<String> systemPropertiesNames, String ...resourcesName) {
        log.debug("ConfigurationService init... :\n\tconfigDirProvider={}\n\tignoreResources={}\n\tconfigDir={}\n\tsystemPropertiesNames=[{}]\n\tresourcesName=[{}]",
            configDirProvider, ignoreResources, configDir, systemPropertiesNames != null ? systemPropertiesNames.size() : -1,
            resourcesName != null ? resourcesName.length : -1);
        // filter out resource files with 'properties' file extension only
        String[] propsResourcesArray = Stream.of(Objects.requireNonNull(resourcesName)).filter(item -> item.endsWith(".properties")).toArray(String[]::new);
        this.propertiesLoader = new PropertiesConfigLoader(
            configDirProvider,
            ignoreResources,
            configDir,
            systemPropertiesNames,
            propsResourcesArray
        );
        // filter out resource files with 'json' file extension only
        String[] chainsResourcesArray = Stream.of(Objects.requireNonNull(resourcesName)).filter(item -> item.endsWith(".json")).toArray(String[]::new);
        this.chainsConfigLoader = new ChainsConfigLoader(
            configDirProvider,
            configDir,
            ignoreResources,
            chainsResourcesArray
        );
        log.debug("ConfigurationService DONE");
    }

    public Properties propertiesLoad() {
        return propertiesLoader.load();
    }

    public Map<UUID, Chain> chainsLoad() {
        return chainsConfigLoader.load();
    }


    public PropertiesConfigLoader getPropertiesLoader() {
        return propertiesLoader;
    }

    public ChainsConfigLoader getChainsConfigLoader() {
        return chainsConfigLoader;
    }
}
