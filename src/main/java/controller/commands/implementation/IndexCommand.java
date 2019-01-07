package controller.commands.implementation;

import controller.commands.Command;
import model.dto.IndexDto;
import model.entity.Assignment;
import model.service.IndexService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class IndexCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        IndexService indexService = new IndexService();
        List<IndexDto> assignmentList = indexService.getFutureAssignments();
        setTotalPageNumber(request, assignmentList);
        handlePageNumber(request, assignmentList);
        return "/WEB-INF/index.jsp";
    }
    private void handlePageNumber(HttpServletRequest request, List<IndexDto> assignments) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page - 1) * OFFSET;
        int end = Math.min(start + OFFSET, assignments.size());
        request.setAttribute("assignmentList", assignments.subList(start, end));
    }

    private void setTotalPageNumber(HttpServletRequest request, List<IndexDto> assignments) {
        int totalPages = (int) Math.ceil((float)assignments.size()/OFFSET);
        request.setAttribute("totalPages", totalPages);
    }
}
