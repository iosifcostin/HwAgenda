import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/agenda")
public class Agenda  extends HttpServlet {

    AgendaDb agenda = new AgendaDb();
    @Override
    public void service(ServletRequest req, ServletResponse res) {

        String action = req.getParameter("action");
        String nume = req.getParameter("nume");
        String telefon = req.getParameter("telefon");

        if (action != null && action.equals("C")) {

            agenda.insert(nume, telefon);

        } else if (action != null && action.equals("R")){

            try {
                res.setContentType("text/html;charset=UTF-8");
                PrintWriter out = res.getWriter();

                List<Persoana> p = agenda.read();

                for (Persoana persoana : p){
                    out.print(persoana.getNume());
                    out.print(persoana.getTelefon());
                }
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}