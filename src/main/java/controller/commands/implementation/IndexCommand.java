package controller.commands.implementation;

import controller.commands.Command;
import model.dto.IndexDto;
import model.service.IndexService;
import util.QueryManager;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class IndexCommand implements Command {

    private IndexService service = new IndexService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int limit = Integer.parseInt(QueryManager.getProperty("select.limit"));

        setTotalPageNumber(request, limit);
        handlePageNumber(request, limit);

        return "/WEB-INF/index.jsp";
    }

    private void handlePageNumber(HttpServletRequest request, int limit) {
        int page = Math.max(Integer.valueOf(Optional.ofNullable(request.getParameter("page")).orElse("1")), 1 );
        page = Math.min(page, (Integer) Optional.ofNullable(request.getAttribute("totalPages")).orElse(Integer.MAX_VALUE));
        int offset = (page - 1) * limit;
        try {
            List<IndexDto> assignments = service.getFutureAssignments(limit, offset);
            request.setAttribute("assignmentList", assignments);
        } catch (RuntimeException ex) {
            ResourceBundle errors = ResourceBundle.getBundle("errors", ThreadLocalWrapper.getLocale());
            request.setAttribute("selectError", errors.getString("index.select"));
        }
    }

    private void setTotalPageNumber(HttpServletRequest request, int limit) {
        try {
            request.setAttribute("totalPages", service.getTotalPagesNumber(limit));
        } catch (RuntimeException ex) {
            ResourceBundle errors = ResourceBundle.getBundle("errors", ThreadLocalWrapper.getLocale());
            request.setAttribute("pagesError", errors.getString("index.pages"));
        }
    }
}
