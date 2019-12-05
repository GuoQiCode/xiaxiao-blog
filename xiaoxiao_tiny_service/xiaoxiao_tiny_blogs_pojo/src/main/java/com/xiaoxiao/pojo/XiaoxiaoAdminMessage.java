package com.xiaoxiao.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XiaoxiaoAdminMessage {
  private String adminId;
  private String adminName;
  private String adminUrl;
  private String adminStatus;
}
