package ds.project1task1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "ComputeHashes", value = "/getHashesForString")
public class ComputeHashes extends HttpServlet {
    /**
     * Get the string that user inputs and return its hash value(both hex and base64 expression) based on hashing method.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchWord = request.getParameter("searchWord");
        String hashMethod = request.getParameter("encrypt");
        String hex = "";
        String base64= "";
        if (hashMethod.equals("MD5")) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(searchWord.getBytes());
                // hex text
                hex = DatatypeConverter.printHexBinary(md.digest());
                // 64 base notation
                base64 = DatatypeConverter.printBase64Binary(md.digest());
            } catch (NoSuchAlgorithmException e) {
                System.err.println("oops,MD5 is not a valid message digest algorithm.");
            }
        } else if (hashMethod.equals("SHA-256")) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(searchWord.getBytes());
                // hex text
                hex = DatatypeConverter.printHexBinary(md.digest());
                // 64 base notation
                base64 = DatatypeConverter.printBase64Binary(md.digest());
            } catch (NoSuchAlgorithmException e) {
                System.err.println("oops,SHA-256 is not a valid message digest algorithm.");
            }
        }
        PrintWriter printWriter = response.getWriter();
        String returnString = "You input: "+searchWord+"\n"+hashMethod+" - "+"hexadecimal text: "+hex+"\n"+hashMethod+" - "+"base 64 notation: "+base64+"\n";
        printWriter.write(returnString);
    }
}