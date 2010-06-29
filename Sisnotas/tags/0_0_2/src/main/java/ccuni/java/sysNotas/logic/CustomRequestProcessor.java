package ccuni.java.sysNotas.logic;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.RequestProcessor;

import ccuni.java.sysNotas.domain.dto.ParametrosTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;

public class CustomRequestProcessor extends RequestProcessor
{
    protected boolean processPreprocess(HttpServletRequest request, HttpServletResponse response)
    {
        ServletContext sc = this.getServletContext();
        ParametrosTO parametrosTO = (ParametrosTO) sc.getAttribute("parametrosSistema");
        boolean continueProcessing = true;
        try
        {
            HttpSession session = null;
            if (request.isRequestedSessionIdValid())
                session = request.getSession();
            else
                response.sendRedirect("Welcome.jsp");
            String path = null;
            path = processPath(request, response);
            if ((!path.equals((String) "/login")) && (!path.equals((String) "/toLogin"))
                    && (!path.equals((String) "/consultar")) && (!path.equals((String) "/toConsulta")))
            {
                UsuarioTO user = (UsuarioTO) session.getAttribute("usuario");
                if (user == null)
                {
                    try
                    {
                        response.sendRedirect("toLogin.do");
                    }
                    catch (Exception e)
                    {
                    }
                    continueProcessing = false;
                }
                if (path.equals((String) "/ListarDocentes"))
                {
                    if (!user.getCodGrpUsr().equals("1"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/iniciarPeriodo"))
                {
                    if (!user.getCodGrpUsr().equals("1") || (parametrosTO.getInicio_periodo() == 1))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/toIniciarPeriodo"))
                {
                    if (!user.getCodGrpUsr().equals("1") || (parametrosTO.getInicio_periodo() == 1))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/toTerminarPeriodo"))
                {
                    if (!user.getCodGrpUsr().equals("1") || (parametrosTO.getInicio_periodo() == 0))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/registroAdmin"))
                {
                    if (!user.getCodGrpUsr().equals("1"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/toRegAdmin"))
                {
                    if (!user.getCodGrpUsr().equals("1"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/registrarDocentes"))
                {
                    if (!user.getCodGrpUsr().equals("1") && !user.getCodGrpUsr().equals("2"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/listarDocentes"))
                {
                    if (!user.getCodGrpUsr().equals("1") && !user.getCodGrpUsr().equals("2"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/listarDocentesNoRegistrados"))
                {
                    if (!user.getCodGrpUsr().equals("1") && !user.getCodGrpUsr().equals("2"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/migrarDocentes"))
                {
                    if (!user.getCodGrpUsr().equals("1") && !user.getCodGrpUsr().equals("2"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/consultaUsuarios"))
                {
                    if (!user.getCodGrpUsr().equals("1") && !user.getCodGrpUsr().equals("2"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/usuarioAction"))
                {
                    if (!user.getCodGrpUsr().equals("1") && !user.getCodGrpUsr().equals("2"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                /*
                 * if(path.equals((String)"/parametrosAction")){
                 * 
                 * if(!user.getCodGrpUsr().equals("1") &&
                 * !user.getCodGrpUsr().equals("2")){
                 * 
                 * try{ response.sendRedirect("Welcome.jsp"); } catch(Exception
                 * e){
                 * 
                 * }
                 * 
                 * continueProcessing = false;
                 * 
                 * } }
                 */
                /*
                 * if(path.equals((String)"/tipoPruebaCursoAction")){
                 * 
                 * if(!user.getCodGrpUsr().equals("1") &&
                 * !user.getCodGrpUsr().equals("2")){
                 * 
                 * try{ response.sendRedirect("Welcome.jsp"); } catch(Exception
                 * e){
                 * 
                 * }
                 * 
                 * continueProcessing = false;
                 * 
                 * } }
                 */
                if (path.equals((String) "/tipoPruebaAction"))
                {
                    if (!user.getCodGrpUsr().equals("1") && !user.getCodGrpUsr().equals("2"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/listaCursosParametros"))
                {
                    if (!user.getCodGrpUsr().equals("1") && !user.getCodGrpUsr().equals("2"))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
                if (path.equals((String) "/promedio"))
                {
                    if (!user.getCodGrpUsr().equals("1") || (parametrosTO.getInicio_periodo() == 0))
                    {
                        try
                        {
                            response.sendRedirect("Welcome.jsp");
                        }
                        catch (Exception e)
                        {
                        }
                        continueProcessing = false;
                    }
                }
            }
            if (path.equals((String) "/login"))
            {
                UsuarioTO user = (UsuarioTO) session.getAttribute("usuario");
                if (user != null)
                {
                    try
                    {
                        response.sendRedirect("Welcome.jsp");
                    }
                    catch (Exception e)
                    {
                    }
                    continueProcessing = false;
                }
            }
            if (path.equals((String) "/toLogin"))
            {
                UsuarioTO user = (UsuarioTO) session.getAttribute("usuario");
                if (user != null)
                {
                    try
                    {
                        response.sendRedirect("Welcome.jsp");
                    }
                    catch (Exception e)
                    {
                    }
                    continueProcessing = false;
                }
            }
        }
        catch (Exception e)
        {
        }
        return continueProcessing;
    }
}
