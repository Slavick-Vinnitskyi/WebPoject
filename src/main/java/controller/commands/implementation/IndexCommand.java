package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.service.IndexService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class IndexCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws Exception {

        IndexService indexService = new IndexService();
        List<Assignment> assignmentList = indexService.getFutureAssignments();
        request.setAttribute("assignmentList", assignmentList);
        return "/WEB-INF/index.jsp";
    }
}
