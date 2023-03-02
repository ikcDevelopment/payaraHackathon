package com.estuardo.wyss.hospital.storage;

import java.net.URL;
import java.util.Optional;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

/**
 * @company kappa.computacion
 * @coder estuardo.wyss
 * @date
 */
public class DataBase {
    public static EmbeddedStorageManager storageManager;
    public final static DataRoot	root = new DataRoot();

    static
    {
        ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
        Optional<URL> resource = loader.getResource("microstream.xml");

        storageManager = EmbeddedStorageConfiguration.load(
                resource.get().getPath()).createEmbeddedStorageFoundation().createEmbeddedStorageManager(root).start();
    }
}
