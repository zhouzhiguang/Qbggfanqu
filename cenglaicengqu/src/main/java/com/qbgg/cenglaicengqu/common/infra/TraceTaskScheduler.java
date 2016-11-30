package com.qbgg.cenglaicengqu.common.infra;

public class TraceTaskScheduler extends WrapTaskScheduler {
	public TraceTaskScheduler(com.qbgg.cenglaicengqu.common.infra.TaskScheduler wrap) {
		super(wrap);
	}

	@Override
	public void reschedule(com.qbgg.cenglaicengqu.common.infra.Task task) {
		trace("reschedule " + task.dump(true));
		
		super.reschedule(task);
	}
	
	private final void trace(String msg) {

	}
}
