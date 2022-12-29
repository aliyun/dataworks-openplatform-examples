package com.aliyun.dataworks.services;

import com.aliyun.dataworks.dto.CreateBusinessDTO;
import com.aliyun.dataworks.dto.DeleteBusinessDTO;
import com.aliyun.dataworks.dto.ListBusinessesDTO;
import com.aliyun.dataworks.dto.UpdateBusinessDTO;
import com.aliyuncs.dataworks_public.model.v20200518.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author dataworks demo
 */
@Service
public class BusinessService {

    @Autowired
    private DataWorksOpenApiClient dataWorksOpenApiClient;

    /**
     * https://help.aliyun.com/document_detail/202122.html
     * create a business
     *
     * @param createBusinessDTO
     */
    public Long createBusiness(CreateBusinessDTO createBusinessDTO) {
        try {
            CreateBusinessRequest createBusinessRequest = new CreateBusinessRequest();
            //业务流程的名称
            createBusinessRequest.setBusinessName(createBusinessDTO.getBusinessName());
            createBusinessRequest.setDescription(createBusinessDTO.getDescription());
            createBusinessRequest.setOwner(createBusinessDTO.getOwner());
            createBusinessRequest.setProjectId(createBusinessDTO.getProjectId());
            //业务流程所属的功能模块 NORMAL（数据开发） MANUAL_BIZ（手动业务流程）
            createBusinessRequest.setUseType(createBusinessDTO.getUseType());
            CreateBusinessResponse createBusinessResponse = dataWorksOpenApiClient.createClient().getAcsResponse(createBusinessRequest);
            System.out.println("create business requestId:" + createBusinessResponse.getRequestId());
            System.out.println("create business successful,the businessId:" + createBusinessResponse.getBusinessId());
            return createBusinessResponse.getBusinessId();
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
     * https://help.aliyun.com/document_detail/173945.html
     *
     * @param listBusinessesDTO
     * @return
     */
    public List<ListBusinessResponse.Data.BusinessItem> listBusiness(ListBusinessesDTO listBusinessesDTO) {
        try {
            ListBusinessRequest listBusinessRequest = new ListBusinessRequest();
            listBusinessRequest.setKeyword(listBusinessesDTO.getKeyword());
            listBusinessRequest.setPageNumber(listBusinessesDTO.getPageNumber() < 1 ? 1 : listBusinessesDTO.getPageNumber());
            listBusinessRequest.setPageSize(listBusinessesDTO.getPageSize() < 10 ? 10 : listBusinessesDTO.getPageSize());
            listBusinessRequest.setProjectId(listBusinessesDTO.getProjectId());
            ListBusinessResponse listBusinessResponse = dataWorksOpenApiClient.createClient().getAcsResponse(listBusinessRequest);
            System.out.println("list business requestId:" + listBusinessResponse.getRequestId());
            ListBusinessResponse.Data data = listBusinessResponse.getData();
            System.out.println("total count:" + data.getTotalCount());
            if (!CollectionUtils.isEmpty(data.getBusiness())) {
                for (ListBusinessResponse.Data.BusinessItem businessItem : data.getBusiness()) {
                    System.out.println(businessItem.getBusinessId() + "," + businessItem.getBusinessName() + "," + businessItem.getUseType());
                }
            }
            return data.getBusiness();
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
     * update a business
     * https://help.aliyun.com/document_detail/173941.html
     *
     * @param updateBusinessDTO
     * @return
     */
    public Boolean updateBusiness(UpdateBusinessDTO updateBusinessDTO) {
        try {
            UpdateBusinessRequest updateBusinessRequest = new UpdateBusinessRequest();
            updateBusinessRequest.setBusinessId(updateBusinessDTO.getBusinessId());
            updateBusinessRequest.setBusinessName(updateBusinessDTO.getBusinessName());
            updateBusinessRequest.setDescription(updateBusinessDTO.getDescription());
            updateBusinessRequest.setOwner(updateBusinessDTO.getOwner());
            updateBusinessRequest.setProjectId(updateBusinessDTO.getProjectId());
            UpdateBusinessResponse updateBusinessResponse = dataWorksOpenApiClient.createClient().getAcsResponse(updateBusinessRequest);
            System.out.println(updateBusinessResponse.getRequestId());
            System.out.println(updateBusinessResponse.getSuccess());
            return updateBusinessResponse.getSuccess();
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
     * delete a business
     * https://help.aliyun.com/document_detail/173939.html
     *
     * @param deleteBusinessDTO
     */
    public boolean deleteBusiness(DeleteBusinessDTO deleteBusinessDTO) {
        try {
            DeleteBusinessRequest deleteBusinessRequest = new DeleteBusinessRequest();
            deleteBusinessRequest.setBusinessId(deleteBusinessDTO.getBusinessId());
            deleteBusinessRequest.setProjectId(deleteBusinessDTO.getProjectId());
            DeleteBusinessResponse deleteBusinessResponse = dataWorksOpenApiClient.createClient().getAcsResponse(deleteBusinessRequest);
            System.out.println("delete business:" + deleteBusinessResponse.getRequestId());
            System.out.println("delete business" + deleteBusinessResponse.getSuccess());
            return deleteBusinessResponse.getSuccess();
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
