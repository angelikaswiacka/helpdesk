package com.pp.helpdesk.api;

import com.pp.helpdesk.model.comment.Comment;
import com.pp.helpdesk.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentApi {
    private final CommentService commentService;

    public CommentApi(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public Comment addComment(@RequestBody Comment comment, Long id) {
        return commentService.add(comment, id);
    }

    @GetMapping("/getByTicket/{id}")
    public List<Comment> getByTicket(@PathVariable Long id) {
        return commentService.getByTicket(id);
    }

}
