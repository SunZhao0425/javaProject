package com.example.springdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum PsOfferStatusEnum {
    //3 代表offer 正在流程中 4:offer流程已完成 5:offer 对应的人员不在公司 可以发起流程
    PS_APPROVE_ING(3, "201", "审批中", "",""),
    PS_OFFER_CANCEL(5, "422", "offer已取消", "failed","recruiting_screening"),
    PS_OFFER_APPROVE_CANCEL(5, "403", "offer审批拒绝", "failed","recruiting_screening"),
    WAIT_ONBOARDING(4, "204", "待入职", "waiting_for_hired","recruiting_screening"),
    RECOVER_ONBOARDING(4, "304", "恢复入职", "recover_to_hired","in_service_staff"),
    ALREADY_EMPLOYED(4, "200", "已入职", "hired","in_service_staff"),
    RESIGNED(5, "404", "已离职", "resignation","resigned_staff"),
    CANCEL(5, "301", "取消入职", "hired_cancel","external_reserve");
    private Integer status;
    private String psStatus;
    private String desc;
    private String belloStatus;
    private String periodDivision;

    public static PsOfferStatusEnum getValue(String psStatus) {
        for (PsOfferStatusEnum ele : values()) {
            if (ele.getPsStatus().equals(psStatus))
                return ele;
        }
        return null;
    }

    public static List<String> getValidStatus() {

        return Arrays.asList("201", "204", "304", "200");
    }
}
