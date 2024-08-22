package com.sami.StackOverFlowFrontend.feginClient;

import com.sami.StackOverFlowFrontend.model.API_Responses;
import com.sami.StackOverFlowFrontend.model.ExportRequest;
import com.sami.StackOverFlowFrontend.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "stackOverFlowHttpClient" , url ="http://localhost:9091/${api.endpoint.url}")
public interface StackOverFlowUsersBackendClient {


    @GetMapping("/users")
    API_Responses<List<User>> getUsers();

    @GetMapping("/bookmark/bookmarkedUsersIds")
    API_Responses<List<Long>> getBookmarkedUsersIds();


    @PostMapping("/export")
    API_Responses<String> exportStackOverFlowUsersFile(ExportRequest exportRequest);

    @PostMapping("/bookmark/bookmarkUser")
    API_Responses<String> saveBookmarkUser(Long userId);

    @DeleteMapping("/bookmark/unmarkUser")
    API_Responses<String> deleteBookmarkUser(Long userId);



}
