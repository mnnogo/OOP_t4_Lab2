import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/nav"})
@MultipartConfig
public class MainServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int pageNumber = Integer.parseInt(request.getParameter("page"));

        switchPageTo(pageNumber, request, response);
    }

    private void switchPageTo(int pageNumber, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String path = "";
        switch (pageNumber)
        {
            case 1 -> path = "index.html";
            case 2 -> path = "projects.html";
            case 3 -> path = "customers.html";
            case 4 -> path = "contacts.html";
            case 5 -> path = "offers.html";
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND, "Страница не найдена");
        }

        if (path.isEmpty())
            return;

        RequestDispatcher view = request.getRequestDispatcher(path);
        view.forward(request, response);
    }
}
