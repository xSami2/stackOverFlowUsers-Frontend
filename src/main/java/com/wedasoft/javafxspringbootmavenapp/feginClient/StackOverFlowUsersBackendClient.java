package com.wedasoft.javafxspringbootmavenapp.feginClient;

import com.wedasoft.javafxspringbootmavenapp.model.ExportRequest;
import com.wedasoft.javafxspringbootmavenapp.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "stackOverFlowHttpClient" , url ="http://localhost:9091/${api.endpoint.url}")
public interface StackOverFlowUsersBackendClient {


    @GetMapping("/users")
    List<User> getUsers();

    @GetMapping("/bookmark/bookmarkedUsersIds")
    List<Long> getBookmarkedUsersIds();


    @PostMapping("/export")
    void exportStackOverFlowUsersFile(ExportRequest exportRequest);

    @PostMapping("/bookmark/bookmarkUser")
    void saveBookmarkUser(Long userId);

    @DeleteMapping("/bookmark/unmarkUser")
    void deleteBookmarkUser(Long userId);



}
