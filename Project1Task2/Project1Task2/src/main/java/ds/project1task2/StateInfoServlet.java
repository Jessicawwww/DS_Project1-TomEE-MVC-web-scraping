package ds.project1task2;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "StateInfoServlet", urlPatterns = {"/getStateInfo"})
public class StateInfoServlet extends HttpServlet {
    StateInfoModel stateInfoModel = null;  // The implementation model for this servlet
    // Initiate this servlet
    @Override
    public void init() {
        stateInfoModel = new StateInfoModel();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("select");
        /*
        compatible to view on Android/iPhone
         */
        String ua = request.getHeader("User-Agent");
        boolean mobile;
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        String nextView;
        /*
         * Check if the search parameter is present.
         * If not, then give the user instructions and prompt for a search string.
         * If there is a search parameter, then do the search and return the result.
         */
        if (state != null) {
            String picSize = (mobile) ? "mobile" : "desktop";

            request.setAttribute("searchState", state);
            request.setAttribute("population", stateInfoModel.getStatePopulation(state));
            request.setAttribute("nickname", stateInfoModel.getStateNickname(state));
            request.setAttribute("capital", stateInfoModel.getStateCapital(state));
            request.setAttribute("song", stateInfoModel.getStateSong(state));
            request.setAttribute("imgFlower", stateInfoModel.getStateFlowerImage(state));
            request.setAttribute("imgFlag", stateInfoModel.getStateFlagImage(state));
            // Pass the user search string (pictureTag) also to the view.
            nextView = "result.jsp";
        } else {
            // no search parameter so choose the prompt view
            nextView = "index.jsp";
        }
        // Transfer control over the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

}