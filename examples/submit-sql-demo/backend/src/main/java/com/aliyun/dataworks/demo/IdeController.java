package com.aliyun.dataworks.demo;

import com.aliyun.dataworks.dto.*;
import com.aliyun.dataworks.services.BusinessService;
import com.aliyun.dataworks.services.FileService;
import com.aliyun.dataworks.services.FolderService;
import com.aliyun.dataworks.services.ProjectService;
import com.aliyuncs.dataworks_public.model.v20200518.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dataworks demo
 */
@RestController
@RequestMapping("/ide")
public class IdeController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private ProjectService projectService;

    /**
     * for list those files
     *
     * @param listFilesDTO
     * @return ListFilesResponse.Data.File
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/listFiles")
    public List<ListFilesResponse.Data.File> listFiles(ListFilesDTO listFilesDTO) {
        return fileService.listFiles(listFilesDTO);
    }

    /**
     * for list those folders
     *
     * @param listFoldersDTO
     * @return ListFoldersResponse.Data.FoldersItem
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/listFolders")
    public List<ListFoldersResponse.Data.FoldersItem> listFolders(ListFoldersDTO listFoldersDTO) {
        return folderService.listFolders(listFoldersDTO);
    }

    /**
     * for create the folder
     *
     * @param createFolderDTO
     * @return boolean
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/createFolder")
    public boolean createFolder(@RequestBody CreateFolderDTO createFolderDTO) {
        return folderService.createFolder(createFolderDTO);
    }

    /**
     * for update the folder
     *
     * @param updateFolderDTO
     * @return boolean
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/updateFolder")
    public boolean updateFolder(@RequestBody UpdateFolderDTO updateFolderDTO) {
        return folderService.updateFolder(updateFolderDTO);
    }

    /**
     * for get the file
     *
     * @param getFileDTO
     * @return GetFileResponse.Data.File
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getFile")
    public GetFileResponse.Data.File getFile(GetFileDTO getFileDTO) {
        return fileService.getFile(getFileDTO);
    }

    /**
     * for create the file
     *
     * @param createFileDTO
     * @return fileId
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/createFile")
    public Long createFile(@RequestBody CreateFileDTO createFileDTO) {
        return fileService.createFile(createFileDTO);
    }

    /**
     * for update the file
     *
     * @param updateFileDTO
     * @return boolean
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/updateFile")
    public boolean updateFile(@RequestBody UpdateFileDTO updateFileDTO) {
        return fileService.updateFile(updateFileDTO);
    }

    /**
     * for deploy the file
     *
     * @param deployFileDTO
     * @return boolean
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/deployFile")
    public boolean deployFile(@RequestBody DeployFileDTO deployFileDTO) {
        try {
            return fileService.deployFile(deployFileDTO);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * for delete the file
     *
     * @param deleteFileDTO
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/deleteFile")
    public boolean deleteFile(DeleteFileDTO deleteFileDTO) {
        try {
            return fileService.deleteFile(deleteFileDTO);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * for delete the folder
     *
     * @param deleteFolderDTO
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/deleteFolder")
    public boolean deleteFolder(DeleteFolderDTO deleteFolderDTO) {
        return folderService.deleteFolder(deleteFolderDTO);
    }

    /**
     * list businesses
     *
     * @param listBusinessesDTO
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/listBusinesses")
    public List<ListBusinessResponse.Data.BusinessItem> listBusiness(ListBusinessesDTO listBusinessesDTO) {
        return businessService.listBusiness(listBusinessesDTO);
    }

    /**
     * create a business
     *
     * @param createBusinessDTO
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/createBusiness")
    public Long createBusiness(@RequestBody CreateBusinessDTO createBusinessDTO) {
        return businessService.createBusiness(createBusinessDTO);
    }

    /**
     * update a business
     *
     * @param updateBusinessDTO
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/updateBusiness")
    public boolean updateBusiness(@RequestBody UpdateBusinessDTO updateBusinessDTO) {
        return businessService.updateBusiness(updateBusinessDTO);
    }

    /**
     * delete a business
     *
     * @param deleteBusinessDTO
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/deleteBusiness")
    public boolean deleteBusiness(@RequestBody DeleteBusinessDTO deleteBusinessDTO) {
        return businessService.deleteBusiness(deleteBusinessDTO);
    }


    /**
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/listProjects")
    public ListProjectsResponse.PageResult listProjects(Integer pageNumber, Integer pageSize) {
        return projectService.listProjects(pageNumber, pageSize);
    }

    /**
     * @param runSmokeTestDTO
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/runSmokeTest")
    public List<ListInstancesResponse.Data.Instance> runSmokeTest(@RequestBody RunSmokeTestDTO runSmokeTestDTO) {
        return fileService.runSmokeTest(runSmokeTestDTO);
    }

    /**
     * @param instanceId
     * @param projectEnv
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getLog")
    public InstanceDetail getLog(@RequestParam Long instanceId, @RequestParam String projectEnv) {
        return fileService.getInstanceLog(instanceId, projectEnv);
    }

}
