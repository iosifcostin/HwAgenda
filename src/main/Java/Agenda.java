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
        String id = req.getParameter("id");

        res.setContentType("text/html;charset=UTF-8");

        if (action != null && action.equals("C")) {

            agenda.insert(nume, telefon);
            try {
                PrintWriter out = res.getWriter();
                out.print("Contact adaugat " + nume + " - "+ telefon );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action != null && action.equals("R")){

            try {
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
        }else if (action != null && action.equals("U")){

            agenda.update(nume,telefon,id);
            try {
                PrintWriter out = res.getWriter();
                out.print("Contact modificat cu succes !");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (action !=null && action.equals("D")){

            agenda.delete(id);
            try {
                PrintWriter out = res.getWriter();
                out.print("Contact sters !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}