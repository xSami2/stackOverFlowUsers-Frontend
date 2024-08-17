package com.wedasoft.javafxspringbootmavenapp.feginClient;

import com.wedasoft.javafxspringbootmavenapp.model.ExportRequest;
import com.wedasoft.javafxspringbootmavenapp.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "stackOverFlowHttpClient" , url ="http://localhost:9091/sof")
public interface StackOverFlowUsersBackendClient {


    @GetMapping
    User[] getUsers();

    @GetMapping("/bookmarkedUsersIds")
    List<Long> getBookmarkedUsersIds();

    @PostMapping
    void exportStackOverFlowUsersFile(ExportRequest exportRequest);

    @PostMapping("/bookmarkUser")
    void saveBookmarkUser(Long userId);

    @DeleteMapping("/unmarkUser")
    void deleteBookmarkUser(Long userId);



}
