package com.aliyun.dataworks.services;

import com.aliyun.dataworks.dto.CreateFolderDTO;
import com.aliyun.dataworks.dto.DeleteFolderDTO;
import com.aliyun.dataworks.dto.ListFoldersDTO;
import com.aliyun.dataworks.dto.UpdateFolderDTO;
import com.aliyuncs.dataworks_public.model.v20200518.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * the ide folders manager service
 *
 * @author dataworks demo
 */
@Service
public class FolderService {

    @Autowired
    private DataWorksOpenApiClient dataWorksOpenApiClient;


    /**
     * 查询文件夹，参考帮助文档：https://help.aliyun.com/document_detail/173955.html
     *
     * @param listFoldersDTO
     * @return
     */
    public List<ListFoldersResponse.Data.FoldersItem> listFolders(ListFoldersDTO listFoldersDTO) {
        try {
            ListFoldersRequest listFoldersRequest = new ListFoldersRequest();
            listFoldersRequest.setPageNumber(listFoldersDTO.getPageNumber() < 1 ? 1 : listFoldersDTO.getPageNumber());
            listFoldersRequest.setPageSize(listFoldersDTO.getPageSize() < 10 ? 10 : listFoldersDTO.getPageSize());
            listFoldersRequest.setParentFolderPath(listFoldersDTO.getParentFolderPath());
            listFoldersRequest.setProjectId(listFoldersDTO.getProjectId());
            ListFoldersResponse listFoldersResponse = dataWorksOpenApiClient.createClient().getAcsResponse(listFoldersRequest);
            System.out.println("the request of list folders : " + listFoldersResponse.getRequestId());
            ListFoldersResponse.Data response = listFoldersResponse.getData();
            System.out.println("total count :" + response.getTotalCount());
            if (!CollectionUtils.isEmpty(response.getFolders())) {
                for (ListFoldersResponse.Data.FoldersItem foldersItem : response.getFolders()) {
                    //作为创建File时的入参
                    System.out.println(foldersItem.getFolderId());
                    System.out.println(foldersItem.getFolderPath());
                }
            }
            return response.getFolders();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return null;
    }

    /**
     * 变更文件夹名称
     * 参考文档 https://help.aliyun.com/document_detail/173959.html
     *
     * @param updateFolderDTO
     * @return
     */
    public boolean updateFolder(UpdateFolderDTO updateFolderDTO) {
        UpdateFolderRequest updateFolderRequest = new UpdateFolderRequest();
        updateFolderRequest.setFolderId(updateFolderDTO.getFolderId());
        updateFolderRequest.setFolderName(updateFolderDTO.getFolderName());
        updateFolderRequest.setProjectId(updateFolderDTO.getProjectId());
        try {
            UpdateFolderResponse updateFolderResponse = dataWorksOpenApiClient.createClient().getAcsResponse(updateFolderRequest);
            System.out.println("the requestId of update folder:" + updateFolderResponse.getRequestId());
            System.out.println("update folder result:" + updateFolderResponse.getSuccess());
            return updateFolderResponse.getSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return false;
    }

    /**
     * @param createFolderDTO
     * @return Boolean
     */
    public boolean createFolder(CreateFolderDTO createFolderDTO) {
        try {
            CreateFolderRequest createFolderRequest = new CreateFolderRequest();
            createFolderRequest.setFolderPath(createFolderDTO.getFolderPath());
            createFolderRequest.setProjectId(createFolderDTO.getProjectId());
            CreateFolderResponse createFolderResponse = dataWorksOpenApiClient.createClient().getAcsResponse(createFolderRequest);
            System.out.println("the requestId of create folder:" + createFolderResponse.getRequestId());
            if (createFolderResponse.getSuccess()) {
                System.out.println("create the folder successful:" + createFolderResponse.getData());
            } else {
                throw new ClientException(createFolderResponse.getErrorCode(), createFolderResponse.getErrorMessage(), createFolderResponse.getRequestId());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return false;
    }

    /**
     *
     * @param deleteFolderDTO
     * @return
     */
    public boolean deleteFolder(DeleteFolderDTO deleteFolderDTO) {
        try {
            DeleteFolderRequest deleteFolderRequest = new DeleteFolderRequest();
            deleteFolderRequest.setFolderId(deleteFolderDTO.getFolderId());
            deleteFolderRequest.setProjectId(deleteFolderDTO.getProjectId());
            DeleteFolderResponse deleteFolderResponse = dataWorksOpenApiClient.createClient().getAcsResponse(deleteFolderRequest);
            System.out.println("the requestId of delete folder:" + deleteFolderResponse.getRequestId());
            System.out.println("delete folder:" + deleteFolderResponse.getSuccess());
            return deleteFolderResponse.getSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            // 请求ID
            System.out.println(e.getRequestId());
            // 错误码
            System.out.println(e.getErrCode());
            // 错误信息
            System.out.println(e.getErrMsg());
        }
        return false;
    }

}
