package com.byit.springemailthymeleaf.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author huangfu
 */
@Data
@Builder
public class EmailData {
    private String nodeName;
    private String startDate;
    private String endDate;
    private String consuming;
    private String runStatus;
    private List<String> logPaths;
}
