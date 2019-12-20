import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HashMap<String, ArrayList<String>> spisok = new HashMap<>();

    public void init(ServletConfig config) {
    	File file = new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 9.0\\webapps\\laba16\\spisok.txt");
        try {
            BufferedReader r = new BufferedReader (new InputStreamReader(new FileInputStream(file)));
            String[] subStr;
            String tmp, delimeter = " ";
            while(r.ready()) {
                ArrayList<String> temp = new ArrayList<>();
                tmp = r.readLine();
                subStr = tmp.split(delimeter);
                for (int i = 1; i != subStr.length; i++) {
                    temp.add(subStr[i]);
                }
                spisok.put(subStr[0], temp);
            }
        }
        catch(IOException e) { 
            e.printStackTrace();
        }
    }


    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>LABA 16</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<script src=\" myScript.js \" defer></script>");
        int j = 1;
        Iterator it;
        for(Map.Entry<String, ArrayList<String>> item : spisok.entrySet()) {
            int m = 1;
            out.println("<h1>"+ j + ") " + item.getKey());
            ArrayList<String> temp = new ArrayList<>();
            temp = item.getValue();
            int size = temp.size();
            out.println("<a id = " + item.getKey().replace(" ","_") + " OnClick=\"on(this.id, " + size + ");\">[+]</a></h1>");
            for(int i = 0; i < temp.size(); i++) {
                out.println("<ul style = 'display:none;' id = \"" + item.getKey().replace(" ","_") + m +"\">" + "*" + temp.get(i) + "<br></ul>");
                m++;
            }
            j++;
        }
        out.println("</body>");
        out.println("</html>");
    }
}

