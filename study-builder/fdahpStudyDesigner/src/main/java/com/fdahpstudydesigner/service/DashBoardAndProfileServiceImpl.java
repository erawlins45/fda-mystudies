/*
 * Copyright 2020-2021 Google LLC
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package com.fdahpstudydesigner.service;

import static com.fdahpstudydesigner.common.StudyBuilderAuditEvent.USER_ACCOUNT_UPDATED;
import static com.fdahpstudydesigner.common.StudyBuilderAuditEvent.USER_ACCOUNT_UPDATED_FAILED;

import com.fdahpstudydesigner.bean.AuditLogEventRequest;
import com.fdahpstudydesigner.bo.MasterDataBO;
import com.fdahpstudydesigner.bo.UserBO;
import com.fdahpstudydesigner.common.StudyBuilderAuditEvent;
import com.fdahpstudydesigner.common.StudyBuilderAuditEventHelper;
import com.fdahpstudydesigner.dao.DashBoardAndProfileDAO;
import com.fdahpstudydesigner.mapper.AuditEventMapper;
import com.fdahpstudydesigner.util.FdahpStudyDesignerConstants;
import com.fdahpstudydesigner.util.SessionObject;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashBoardAndProfileServiceImpl implements DashBoardAndProfileService {

  private static XLogger logger =
      XLoggerFactory.getXLogger(DashBoardAndProfileServiceImpl.class.getName());

  @Autowired private DashBoardAndProfileDAO dashBoardAndProfiledao;

  @Autowired private StudyBuilderAuditEventHelper auditLogHelper;

  @Autowired private HttpServletRequest request;

  @Override
  public MasterDataBO getMasterData(String type) {
    logger.entry("begin getMasterData()");
    MasterDataBO masterDataBO = null;
    try {
      masterDataBO = dashBoardAndProfiledao.getMasterData(type);
    } catch (Exception e) {
      logger.error("DashBoardAndProfileServiceImpl - getMasterData() - ERROR", e);
    }
    logger.exit("getMasterData() - Ends");
    return masterDataBO;
  }

  @Override
  public String isEmailValid(String email) {
    return dashBoardAndProfiledao.isEmailValid(email);
  }

  @Override
  public String updateProfileDetails(UserBO userBO, String userId, SessionObject userSession) {
    logger.entry("begin updateProfileDetails()");
    String message = FdahpStudyDesignerConstants.FAILURE;
    StudyBuilderAuditEvent auditLogEvent = null;
    try {
      AuditLogEventRequest auditRequest = AuditEventMapper.fromHttpServletRequest(request);
      message = dashBoardAndProfiledao.updateProfileDetails(userBO, userId);
      if (message.equals(FdahpStudyDesignerConstants.SUCCESS)) {
        auditLogEvent = USER_ACCOUNT_UPDATED;
      } else {
        auditLogEvent = USER_ACCOUNT_UPDATED_FAILED;
      }
      auditLogHelper.logEvent(auditLogEvent, auditRequest);
    } catch (Exception e) {
      logger.error("DashBoardAndProfileServiceImpl - updateProfileDetails() - Error", e);
    }
    logger.exit("updateProfileDetails - Ends");
    return message;
  }
}
