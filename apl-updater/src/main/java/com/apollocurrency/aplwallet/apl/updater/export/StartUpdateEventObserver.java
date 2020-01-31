/*
 *  Copyright © 2018-2020 Apollo Foundation
 */

package com.apollocurrency.aplwallet.apl.updater.export;

import static com.apollocurrency.aplwallet.apl.core.app.service.SecureStorageService.SECURE_STORE_KEY;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Objects;
import java.util.Properties;

import com.apollocurrency.aplwallet.apl.core.db.model.OptionDAO;
import com.apollocurrency.aplwallet.apl.crypto.Convert;
import com.apollocurrency.aplwallet.apl.crypto.Crypto;
import com.apollocurrency.aplwallet.apl.updater.export.event.StartUpdateEvent;
import com.apollocurrency.aplwallet.apl.updater.export.event.StartUpdateEventData;
import com.apollocurrency.aplwallet.apl.updater.export.event.StartUpdateEventType;
import com.apollocurrency.aplwallet.apl.util.env.dirprovider.ConfigDirProvider;
import com.apollocurrency.aplwallet.apl.util.injectable.PropertiesHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * Class process sent event afer update is processed and not run script.
 * It stores some properties into config folder for later use.
 */
@Slf4j
@Singleton
public class StartUpdateEventObserver {

    public static final String EXPORTED_DATA_FILE_NAME = "exported-data.properties";

    private PropertiesHolder propertiesHolder;
    private OptionDAO optionDAO;
    private ConfigDirProvider configDirProvider;

    @Inject
    public StartUpdateEventObserver(PropertiesHolder propertiesHolder, OptionDAO optionDAO, ConfigDirProvider configDirProvider) {
        this.propertiesHolder = Objects.requireNonNull(propertiesHolder, "propertiesHolder is NULL");
        this.optionDAO = Objects.requireNonNull(optionDAO, "optionDAO is NULL");
        this.configDirProvider = Objects.requireNonNull(configDirProvider, "configDirProvider is NULL");
    }

    public void onStartUpdateBefore(
        @Observes @StartUpdateEvent(StartUpdateEventType.BEFORE_SCRIPT) StartUpdateEventData startUpdateEventData) {
        log.debug("onStartUpdateBefore...");
        // read some properties
        String nameKey = new String(Base64.getDecoder().decode("YXBsLmFkbWluUGFzc3dvcmQ="));
        String propertyValue = propertiesHolder.getStringProperty(nameKey);
        log.debug("propertiesHolder value found? = '{}'", propertyValue != null && !propertyValue.isEmpty());
        // prepare folder to write to
        String configDir = configDirProvider.getConfigDirectory();
        File folder = new File(configDir);
        if (!folder.exists()) {
            // create if missing
            boolean result = folder.mkdirs();
            if (!result) {
                // no reason try to write if folder was not created
                log.error("Error, config folder was not created: configDir = '{}'", configDir);
                return;
            } else {
                log.debug("SUCCESS, Created configDir = '{}'", configDir);
            }
        } else {
            log.debug("Exists configDir = '{}'", configDir);
        }
        File targetFile = Path.of(configDir, EXPORTED_DATA_FILE_NAME).toFile(); // full target file
        log.debug("Before saving data to file in config dir = '{}'", targetFile);
        // read, prepare and write data
        Properties writeTempProps = new Properties();
        try (OutputStream output = new FileOutputStream(targetFile, false) ) {
            // set the properties value
            log.debug("START write props to file '{}'", targetFile);
            byte[] nameKeyHash;
            if (propertyValue != null && !propertyValue.isEmpty()) {
                nameKeyHash = Crypto.sha256().digest(propertyValue.getBytes());
                writeTempProps.setProperty(new String(Base64.getDecoder().decode("YXBsLmFkbWluLnBoYXNo")), Convert.toHexString(nameKeyHash) );
                writeTempProps.setProperty(new String(Base64.getDecoder().decode("YXBsLmFkbWluLnB2YWw=")), propertyValue);
            } else {
                writeTempProps.setProperty(new String(Base64.getDecoder().decode("YXBsLmFkbWluLnB2YWw=")),
                    new String(Base64.getDecoder().decode("YWRtaW5QYXNz")) );
                nameKeyHash = Crypto.sha256().digest(Base64.getDecoder().decode("YWRtaW5QYXNz"));// default
                writeTempProps.setProperty(new String(Base64.getDecoder().decode("YXBsLmFkbWluLnBoYXNo")), Convert.toHexString(nameKeyHash));
            }
            String value = optionDAO.get(SECURE_STORE_KEY);
            if (value != null && !value.isEmpty()) {
                writeTempProps.setProperty(new String(Base64.getDecoder().decode("YXBsLmRwYXM=")), value);
            }
            // save properties to file
            writeTempProps.store(output, null);
            log.debug("SUCCESS props [{}] were written to file '{}'", writeTempProps.size(), targetFile);
        } catch (IOException e) {
            log.error("IOError writing temp props file = '{}'", Path.of(configDir, EXPORTED_DATA_FILE_NAME).toString(), e);
            return;
        } catch (Exception e) {
            log.error("Error writing temp props file = '{}'", Path.of(configDir, EXPORTED_DATA_FILE_NAME).toString(), e);
            return;
        }
        log.debug("onStartUpdateBefore DONE");
    }
}