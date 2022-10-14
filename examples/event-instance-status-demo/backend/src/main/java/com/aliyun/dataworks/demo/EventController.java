package com.aliyun.dataworks.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dataworks.config.Constants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author dataworks demo
 */
@RestController
@RequestMapping("/event")
public class EventController {

    /**
     * 接收eventBridge推送过来的消息
     * @param jsonParam
     */
    @PostMapping("/consumer")
    public void consumerEventBridge(@RequestBody String jsonParam){
        JSONObject jsonObj = JSON.parseObject(jsonParam);
        String eventCode = jsonObj.getString(Constants.EVENT_CODE_FILED);
        if(Constants.INSTANCE_STATUS_EVENT_CODE.equals(eventCode)){
            JSONObject dataParam = JSON.parseObject(jsonObj.getString("data"));
            //调度任务实例开始等时间的具体时间
            System.out.println("beginWaitTimeTime: "+ dataParam.getString("beginWaitTimeTime"));
            //DagId
            System.out.println("dagId: "+ dataParam.getString("dagId"));
            //Dag的类型，取值如下：
            //0：周期调度任务
            //1：手动任务
            //2：冒烟测试
            //3：补数据
            //4：手动业务流程
            //5：临时业务流程
            System.out.println("dagType: "+dataParam.getString("dagType"));
            //任务实例的调度类型，取值如下：
            //NORMAL(0)：正常调度任务。该任务被日常调度。
            //MANUAL(1)：手动任务。该任务不会被日常调度。
            //PAUSE(2)：冻结任务。该任务被日常调度，但启动调度时直接被置为失败状态。
            //SKIP(3)：空跑任务。该任务被日常调度，但启动调度时直接被置为成功状态。
            //SKIP_UNCHOOSE(4)：临时工作流中未选择的任务，仅存在于临时工作流中，启动调度时直接被置为成功状态。
            //SKIP_CYCLE(5)：未到运行周期的周或月任务。该任务被日常调度，但启动调度时直接被置为成功状态。
            //CONDITION_UNCHOOSE(6)：上游实例中有分支（IF）节点，但是该下游节点未被分支节点选中，直接置为空跑任务。
            //REALTIME_DEPRECATED(7)：实时生成的已经过期的周期实例，该类型的任务直接被置为成功状态。
            System.out.println("taskType: "+dataParam.getString("taskType"));
            //任务实例的修改时间
            System.out.println("modifyTime: "+dataParam.getString("modifyTime"));
            //任务实例的创建时间
            System.out.println("createTime: "+dataParam.getString("createTime"));
            //工作空间的ID。您可以调用ListProjects查看空间ID信息。
            System.out.println("appId: "+dataParam.getString("appId"));
            //调度任务实例所在工作空间的租户ID
            System.out.println("tenantId: "+dataParam.getString("tenantId"));
            //调度任务实例的操作码：该字段可忽略
            System.out.println("opCode: "+dataParam.getString("opCode"));
            //业务流程的ID，周期调度任务实例的业务流程默认为1，手动业务流程和内部工作流调度任务实例为实际的业务流程ID
            System.out.println("flowId: "+dataParam.getString("flowId"));
            //调度任务实例对应的节点ID
            System.out.println("nodeId:"+dataParam.getString("nodeId"));
            //调度任务实例开始等资源的具体时间
            System.out.println("beginWaitResTime: "+dataParam.getString("beginWaitResTime"));
            //调度任务实例ID
            System.out.println("taskId: "+dataParam.getString("taskId"));
            //任务的状态，取值如下：
            //0（未运行）
            //2（等待定时时间dueTime或cycleTime到来）
            //3（等待资源）
            //4（运行中）
            //7（下发给数据质量进行数据校检）
            //8（正在进行分支条件校检）
            //5（执行失败）
            //6（执行成功）
            System.out.println("status: "+dataParam.getString("status"));
        }else{
            System.out.println("未能过滤其他事件,请检查配置步骤");
        }
    }
}
