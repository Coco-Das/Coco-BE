package coco.ide.ideapp.projects;

import coco.ide.ideapp.projects.requestdto.CreateProjectForm;
import coco.ide.ideapp.projects.requestdto.UpdateProjectNameForm;
import coco.ide.ideapp.projects.responsedto.ProjectChildsDto;
import coco.ide.ideapp.projects.responsedto.ProjectDto;
import coco.ide.ideapp.projects.responsedto.ProjectListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("")
    public String createProject(@RequestBody CreateProjectForm form) {
        log.info("createProjectForm = {}", form);
        projectService.createProject(form);
        return "create project ok";
    }

    @DeleteMapping("/{projectId}")
    public String deleteProject(@PathVariable("projectId") Long projectId) {
        try {
            projectService.deleteProject(projectId);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        return "delete project ok";
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<ProjectDto> updateProjectName(@PathVariable("projectId") Long projectId, @RequestBody UpdateProjectNameForm form) {
        ProjectDto project = projectService.updateProjectName(projectId, form.getNewName());

        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<List<ProjectListDto>> findAllProjects() {
        List<ProjectListDto> allProjects = projectService.findAllProjects();
        return ResponseEntity.ok(allProjects);
    }

    //Todo : 프로젝트 로드 시 최상위 파일들도 불러와야 함. Dto 수정 필요 -> 수정 끝 확인필요
    // 최상위 폴더랑 최상위 파일만 가져오면 되는거 아닌가? 지금 폴더는 하위 폴더도 다 불러오는데 불러와야하나 싶네
    // 차라리 폴더 클릭하면 그 안에 있는 폴더랑 파일 다 불러오는 메소드를 만드는게 편할듯
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectChildsDto> findFolders(@PathVariable Long projectId) {
        ProjectChildsDto childs = projectService.findChilds(projectId);
        return ResponseEntity.ok(childs);
    }
}
