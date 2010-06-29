package ccuni.java.sysNotas.logic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

public class CustomExceptionHandler extends ExceptionHandler
{
    Log log = LogFactory.getLog(CustomExceptionHandler.class);

    // commons logging reference
    /**
     * Handle the exception. Standard execute method with addition of logging
     * the stacktrace.
     */
    public ActionForward execute(Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm formInstance,
            HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
        logExceptionChain(ex);
        return super.execute(ex, ae, mapping, formInstance, request, response);
    }

    /**
     * logging exception stack trace, including chained exceptions modified from
     * version by Keld H. Hansen
     * http://javaboutique.internet.com/tutorials/Chained_Exceptions/
     * 
     * @param thr
     */
    private void logExceptionChain(Throwable thr)
    {
        StackTraceElement[] s;
        Throwable t = thr;
        StringBuffer errorMsg = new StringBuffer("\nException chain (top to bottom):\n");
        while (t != null)
        {
            errorMsg.append("-------------------------------\n");
            s = t.getStackTrace();
            StackTraceElement s0 = s[0];
            errorMsg.append(t.toString());
            errorMsg.append("  at " + s0.toString() + "\n");
            if (t.getCause() == null)
            {
                errorMsg.append("-------------------------------\n");
                errorMsg.append("Complete traceback (bottom to top):\n");
                for (int i = 0; i < s.length; i++)
                    errorMsg.append("  at " + s[i].toString() + "\n");
            }
            t = t.getCause();
        }
        log.error(errorMsg.toString());
    }
}
