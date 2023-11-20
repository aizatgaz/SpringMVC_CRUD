package com.aizatgaz.controller;

import com.aizatgaz.domain.entity.Task;
import com.aizatgaz.domain.entity.enums.Status;
import com.aizatgaz.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String tasks(Model model,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "count", required = false, defaultValue = "10") int count) {
        List<Task> tasks = taskService.getAllTasks((page - 1) * count, count);
        model.addAttribute("tasks", tasks);

        int totalPages = (int) Math.ceil(1.0 * taskService.count() / count);
        model.addAttribute("pageNumbers", totalPages);
        model.addAttribute("currentPage", page);

        return "tasks";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @RequestParam String description, @RequestParam String status, RedirectAttributes redirectAttributes) {
        if (isNull(id) || id <= 0) {
            throw new RuntimeException("Invalid id");
        }

        System.out.println(id + " " + description + " " + status);

        Task task = taskService.getById(id);
        task.setDescription(description);
        task.setStatus(Status.valueOf(status));

        taskService.edit(task);

        redirectAttributes.addFlashAttribute("message", "Task updated successfully");
        return "redirect:/";
    }

    @PostMapping("/")
    public String add(Model model,
                     @ModelAttribute TaskInfo info) {
        taskService.create(info.getDescription(), info.getStatus());
        return "redirect:/";
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String delete(Model model, @PathVariable Integer id) {
        if (isNull(id) || id <= 0) throw new RuntimeException("Invalid id");
        taskService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editEntity(Model model, @PathVariable Integer id) {
        System.out.println(id);
        if (isNull(id) || id <= 0) throw new RuntimeException("Invalid id");
        Task task = taskService.getById(id);
        model.addAttribute("task", task);
        System.out.println(task);
        return "edit";
    }

}
