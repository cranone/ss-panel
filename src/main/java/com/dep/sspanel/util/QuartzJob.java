package com.dep.sspanel.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务
 * @author Maclaine
 *
 */
//@Component
public class QuartzJob implements Serializable {
	private static final long serialVersionUID = -7973412994975565416L;
	private static final Logger logger = LoggerFactory.getLogger(QuartzJob.class);

	public void work() {
		logger.info("quartz working");
	}

}
