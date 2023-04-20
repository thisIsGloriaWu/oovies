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

@WebServlet("/newinmovies")
public class NewInMovies extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MovieDao movieDao;

	@Override
	public void init() throws ServletException {
		movieDao = MovieDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    List<Movie> newInMovies = new ArrayList<Movie>();

	    try {
	    	newInMovies = movieDao.getNewlyReleasedMovies();
	        req.setAttribute("newInMovies", newInMovies);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }

	    req.getRequestDispatcher("/NewInMovies.jsp").forward(req, resp);
	}

}