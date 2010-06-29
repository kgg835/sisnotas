package ccuni.java.sysNotas.action;

import java.util.Collections;
import java.util.Map;

import org.apache.struts.actions.LookupDispatchAction;

public class MyLookupAction extends LookupDispatchAction
{
    protected Map getKeyMethodMap()
    {
        return Collections.EMPTY_MAP;
    }
}
