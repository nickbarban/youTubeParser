package youTubeParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



public class ParserServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
	    PrintWriter out = response.getWriter(); 
	    out.write("<!DOCTYPE html>\n" +
	       "<head><title>A servlet for parsing youtube http link.</title></head>\n" +
	       "<body bgcolor=\"#fdf5e5\">\n" +
	       "<h1>List of top related links:</h1>\n" +
	       "<p>List</p>\n"); 
	    String strAmount = request.getParameter("amount");
	    String strNestingLevel= request.getParameter("nest");
	    String HTTPLink = request.getParameter("link");
	    if (strAmount != null && strNestingLevel != null ) {
	    	try {
				int amount = Integer.parseInt(strAmount);
				int nestingLevel = Integer.parseInt(strNestingLevel);
				YouTubeParser.parse(amount, nestingLevel, HTTPLink);
				out.write("<p>Amount: " + amount + "</p>\n");
				out.write("<p>NestingLevel: " + nestingLevel + "</p>\n");
				out.write("<p>HTTPL link: " + HTTPLink + "</p>\n");
				out.write("<p><b>Amount of links: " + YouTubeParser.parsedLinks.size() + "</p>\n");
			} catch (NumberFormatException e) {
				out.write("<p>WRONG INPUTED PARAMETER \"AMOUNT\"</p>\n");
			}
	    } // if
	    
	    out.write("</body></html>");
	} // doGet
	
	
}
