/*
 * Copyright (c) 2021 Qi Keira Zhang. All rights reserved.
 */

/**
 * Get a job build object with job name and build id
 * @param job_name
 * @param build_id
 * @return
 */
def call(String job_name=env.JOB_NAME, int build_id=env.BUILD_ID){
	def job = Jenkins.instance.getItem(job_name)
	println "DEBUG: job info: $job"

	def build = job.getBuildByNumber(build_id)
	println "DEBUG: build info: $build"

	return build
}
