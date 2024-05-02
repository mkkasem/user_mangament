package com.dev.localization;

import jakarta.inject.Inject;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.context.Theme;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.SimpleTheme;

import java.util.Locale;

public class UserServicesResourceBundleSource implements ThemeSource, ResourceLoaderAware, MessageSource
{


    private MessageSource parentMessageSource;
    private int cacheSeconds;
    private ResourceLoader resourceLoader;
    private boolean fallbackToSystemLocale;
    private String defaultEncoding;
    private String basenamePrefix;
    private String basePrefix;
    private String sitePrefix;
    private String themePrefix;

    @Override public Theme getTheme(final String themeName)
    {
        return new SimpleTheme(themeName, createMessageSource("/WEB-INF/theme/theme"));
    }



    protected AbstractMessageSource createMessageSource(final String basename)
    {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(basename);
        messageSource.setCacheSeconds(getCacheSeconds());
        messageSource.setResourceLoader(getResourceLoader());
        messageSource.setFallbackToSystemLocale(fallbackToSystemLocale);
        messageSource.setDefaultEncoding(getDefaultEncoding());
        return messageSource;
    }

    @Override public String getMessage(final String code, final Object[] args, final String defaultMessage, final Locale locale)
    {
        final Theme theme = getTheme("theme");
        if (theme != null)
        {
            return theme.getMessageSource().getMessage(code, args, defaultMessage, locale);
        }
        return getParentMessageSource().getMessage(code, args, defaultMessage, locale);
    }

    @Override public String getMessage(final String code, final Object[] args, final Locale locale) throws NoSuchMessageException
    {
        return this.getMessage(code, args, null, locale);
    }

    @Override public String getMessage(final MessageSourceResolvable resolvable, final Locale locale) throws NoSuchMessageException
    {
        final Theme theme = getTheme("theme");
        if (theme != null)
        {
            return theme.getMessageSource().getMessage(resolvable, locale);
        }
        return getParentMessageSource().getMessage(resolvable, locale);
    }

    protected MessageSource getParentMessageSource()
    {
        return parentMessageSource;
    }

    @Inject
    public void setParentMessageSource(final MessageSource parentMessageSource)
    {
        this.parentMessageSource = parentMessageSource;
    }

    protected String getDefaultEncoding()
    {
        return defaultEncoding;
    }

    @Inject
    public void setDefaultEncoding(final String defaultEncoding)
    {
        this.defaultEncoding = defaultEncoding;
    }

    public int getCacheSeconds()
    {
        return cacheSeconds;
    }

    @Inject
    public void setCacheSeconds(final int cacheSeconds)
    {
        this.cacheSeconds = cacheSeconds;
    }

    public ResourceLoader getResourceLoader()
    {
        return resourceLoader;
    }

    public String getSitePrefix()
    {
        return sitePrefix;
    }

    @Inject
    public void setSitePrefix(final String sitePrefix)
    {
        this.sitePrefix = sitePrefix;
    }

    public String getThemePrefix()
    {
        return themePrefix;
    }

    @Inject
    public void setThemePrefix(final String themePrefix)
    {
        this.themePrefix = themePrefix;
    }

    @Override public void setResourceLoader(final ResourceLoader resourceLoader)
    {
        this.resourceLoader = resourceLoader;
    }

    /**
     * @param fallbackToSystemLocale the fallbackToSystemLocale to set
     */
    public void setFallbackToSystemLocale(final boolean fallbackToSystemLocale)
    {
        this.fallbackToSystemLocale = fallbackToSystemLocale;
    }

    public String getBasenamePrefix()
    {
        return basenamePrefix;
    }

    @Inject
    public void setBasenamePrefix(final String basenamePrefix)
    {
        this.basenamePrefix = basenamePrefix;
    }

    public String getBasePrefix()
    {
        return basePrefix;
    }

    @Inject
    public void setBasePrefix(final String basePrefix)
    {
        this.basePrefix = basePrefix;
    }



}