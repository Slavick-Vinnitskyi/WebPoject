package controller.commands.implementation;

import controller.commands.Command;
import model.dto.IndexDto;
import model.entity.Assignment;
import model.service.IndexService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class IndexCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        IndexService indexService = new IndexService();
        List<IndexDto> assignmentList = indexService.getFutureAssignments();
        request.setAttribute("assignmentList", assignmentList);
        return "/WEB-INF/index.jsp";
    }
}
