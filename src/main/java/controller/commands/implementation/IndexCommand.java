package controller.commands.implementation;

import controller.commands.Command;
import model.dto.IndexDto;
import model.service.IndexService;
import util.QueryManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

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
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int offset = (page - 1) * limit;
        List<IndexDto> assignments = service.getFutureAssignments(limit, offset);
        request.setAttribute("assignmentList", assignments);
    }

    private void setTotalPageNumber(HttpServletRequest request, int limit) {
        request.setAttribute("totalPages", service.getTotalPagesNumber(limit));
    }
}
