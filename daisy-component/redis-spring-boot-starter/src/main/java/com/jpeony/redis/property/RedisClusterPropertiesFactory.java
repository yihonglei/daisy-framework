package com.jpeony.redis.property;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.bind.*;
import org.springframework.boot.context.properties.bind.handler.IgnoreErrorsBindHandler;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @author yihonglei
 */
public class RedisClusterPropertiesFactory {

    private static final String DEFAULT_CLUSTER_BEAN_NAME = "redisSupport";

    private static ClusterProperties convert(RedisProperties redisProperties) {
        if (redisProperties == null) {
            return null;
        }
        if (StringUtils.isEmpty(redisProperties.getCluster()) && StringUtils.isEmpty(redisProperties.getCluster())) {
            throw new IllegalStateException("nodes or cluster must config");
        }
        String name = redisProperties.getName();
        if (StringUtils.isEmpty(name)) {
            name = DEFAULT_CLUSTER_BEAN_NAME;
        }
        redisProperties.setName(name);
        ClusterProperties properties = new ClusterProperties();
        properties.getClusters().add(redisProperties);
        return properties;
    }

    private static ClusterProperties getSingleClusterProperties(DefaultListableBeanFactory beanFactory, String prefix) {
        RedisProperties target = new RedisProperties();
        return convert(getTarget(beanFactory, prefix, target));
    }

    private static ClusterProperties getClusterProperties(DefaultListableBeanFactory beanFactory, String prefix) {
        ClusterProperties clusterProperties = getTarget(beanFactory, prefix, new ClusterProperties());
        if (clusterProperties != null && !CollectionUtils.isEmpty(clusterProperties.getClusters())) {
            AtomicInteger count = new AtomicInteger(0);
            clusterProperties.getClusters().forEach(r -> {
                if (StringUtils.isEmpty(r.getCluster()) && StringUtils.isEmpty(r.getCluster())) {
                    throw new IllegalStateException("nodes or cluster must config");
                }
                String name = r.getName();
                if (StringUtils.isEmpty(name)) {
                    name = DEFAULT_CLUSTER_BEAN_NAME + count.incrementAndGet();
                }
                r.setName(name);
            });
        }
        return clusterProperties;
    }

    public static ClusterProperties getProperties(DefaultListableBeanFactory beanFactory, String prefix) {
        ClusterProperties clusterProperties = getSingleClusterProperties(beanFactory, prefix);
        if (clusterProperties == null) {
            clusterProperties = getClusterProperties(beanFactory, prefix);
        }
        return clusterProperties;
    }

    private static <T> T getTarget(DefaultListableBeanFactory beanFactory, String prefix, T instance) {
        Bindable<?> target = Bindable.ofInstance(instance);
        PropertySources propertySources = getPropertySources(beanFactory);
        BindHandler bindHandler = getBindHandler();
        BindResult configBindResult = getBinder(propertySources, beanFactory).bind(prefix, target, bindHandler);
        return (T) configBindResult.orElse(null);
    }

    private static Binder getBinder(PropertySources propertySources, DefaultListableBeanFactory beanFactory) {
        return new Binder(getConfigurationPropertySources(propertySources),
                getPropertySourcesPlaceholdersResolver(propertySources), getConversionService(),
                getPropertyEditorInitializer(beanFactory));
    }

    private static Consumer<PropertyEditorRegistry> getPropertyEditorInitializer(DefaultListableBeanFactory beanFactory) {
        return beanFactory::copyRegisteredEditorsTo;
    }

    private static ConversionService getConversionService() {
        return new DefaultConversionService();
    }

    private static Iterable<ConfigurationPropertySource> getConfigurationPropertySources(PropertySources propertySources) {
        return ConfigurationPropertySources.from(propertySources);
    }

    private static PropertySourcesPlaceholdersResolver getPropertySourcesPlaceholdersResolver(PropertySources propertySources) {
        return new PropertySourcesPlaceholdersResolver(propertySources);
    }

    private static BindHandler getBindHandler() {
        BindHandler handler = new IgnoreErrorsBindHandler();
        return handler;
    }

    public static PropertySources getPropertySources(DefaultListableBeanFactory beanFactory) {
        PropertySourcesPlaceholderConfigurer configurer = getSinglePropertySourcesPlaceholderConfigurer(beanFactory);
        if (configurer != null) {
            return configurer.getAppliedPropertySources();
        }
        MutablePropertySources sources = extractEnvironmentPropertySources(beanFactory);
        if (sources != null) {
            return sources;
        }
        throw new IllegalStateException("Unable to obtain PropertySources from "
                + "PropertySourcesPlaceholderConfigurer or Environment");
    }

    private static MutablePropertySources extractEnvironmentPropertySources(DefaultListableBeanFactory beanFactory) {
        Environment environment = beanFactory.getBean(Environment.class);
        if (environment instanceof ConfigurableEnvironment) {
            return ((ConfigurableEnvironment) environment).getPropertySources();
        }
        return null;
    }

    private static PropertySourcesPlaceholderConfigurer getSinglePropertySourcesPlaceholderConfigurer(DefaultListableBeanFactory beanFactory) {
        // Take care not to cause early instantiation of all FactoryBeans
        Map<String, PropertySourcesPlaceholderConfigurer> beans = beanFactory.getBeansOfType(PropertySourcesPlaceholderConfigurer.class, false, false);
        if (beans.size() == 1) {
            return beans.values().iterator().next();
        }
        return null;
    }

}
