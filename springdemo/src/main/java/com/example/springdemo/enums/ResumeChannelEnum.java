package com.example.springdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResumeChannelEnum {
    RECRUITMENT_PORTAL_CHANNEL("2", "recruitment_portal", "招聘门户", false),
    HEADHUNT_SUPPLIER_CHANNEL("1", "headhunter_supplier", "猎头渠道", true),
    INTERNAL_CHANNEL("3", "internal_channel", "内推渠道", false),
    WEB_CHANNEL("4", "web_channel", "网聘渠道", false);
    private String channel;
    private String channelCode;
    private String desc;
    private Boolean huntChannel;

    public static ResumeChannelEnum getValue(String desc) {
        for (ResumeChannelEnum ele : values()) {
            if (ele.getDesc().equals(desc))
                return ele;
        }
        return null;
    }

    public static ResumeChannelEnum getDesc(String channel) {
        for (ResumeChannelEnum ele : values()) {
            if (ele.getChannel().equals(channel))
                return ele;
        }
        return null;
    }

}
