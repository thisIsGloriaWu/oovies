package oovies.servlet;

import oovies.dal.*;
import oovies.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;

@WebServlet("/toplovedmovies")
public class TopLovedMovies extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MovieDao movieDao;
	private PersonDao personDao ;

	@Override
	public void init() throws ServletException {
		movieDao = MovieDao.getInstance();
		personDao = PersonDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    List<Movie> topLovedMovies = new ArrayList<Movie>();
	   

	    try {
	    	topLovedMovies = movieDao.getTopLovedMovies();
	        req.setAttribute("topLovedMovies", topLovedMovies);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }

	    req.getRequestDispatcher("/TopLovedMovies.jsp").forward(req, resp);
	}

}