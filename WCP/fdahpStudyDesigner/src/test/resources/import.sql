INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `email`, `password`, `role_id`, `created_by`, `modified_by`, `status`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`, `force_logout`) VALUES ('1', 'abc', 'xyx', 'ttt@gmail.com', '$2a$10$uSKnFqkar9ugqrdD1KElcOcPGEtdpuNMvwlHfRGwX4jovq.sH0e/q', '1', '1', '1', '1', '1', '1', '1', 'N');

INSERT INTO `studies` (`id`, `custom_study_id`, `name`, `full_name`, `type`, `category`, `research_sponsor`, `data_partner`, `tentative_duration`, `tentative_duration_weekmonth`, `irb_review`, `inbox_email_address`, `created_by`, `created_on`, `modified_by`, `modified_on`, `status`, `sequence_number`, `thumbnail_image`, `study_website`, `study_tagline`, `study_pre_active_flag`, `has_activity_draft`, `has_consent_draft`, `has_study_draft`, `is_live`, `version`, `has_activitetask_draft`, `has_questionnaire_draft`, `enrollmentdate_as_anchordate`, `app_id`) VALUES (678574, 'OpenStudy003', 'OpenStudy01', 'OpenStudy01', 'GT', '8', 'CDC', '11', 100, 'Days', '<p>Study for Covid-19</p>', 'test@gmail.com', 59, '2020-03-24 07:47:00', 59, '2020-03-24 07:47:00', 'Pre-launch', NULL, 'STUDY_OO_03242020074700.jpg', 'http://www.google.com', 'Study for Covid-19', 'N', 0, 0, 1, 1, 1, 0, 0, 'N', 'GCPMS001');
		