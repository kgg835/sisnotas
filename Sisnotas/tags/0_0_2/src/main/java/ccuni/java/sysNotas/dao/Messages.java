package ccuni.java.sysNotas.dao;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

class Messages
{
    private static final String BUNDLE_NAME = "dao.messages";//$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Messages()
    {
    }

    public static String getString(String key)
    {
        // TODO Auto-generated method stub
        try
        {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key + '!';
        }
    }
}