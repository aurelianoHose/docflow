package com.netradio.task;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

import org.apache.commons.configuration.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.support.CronTrigger;

import com.netradio.entity.Stream;
import com.netradio.service.StreamService;
import com.netradio.util.ActualThreadChecker;

public class AudioThreadsChekerTask implements Runnable {

    private StreamService srv;

    private TaskScheduler scheduler;

    private ThreadPoolTaskExecutor executor;

    private Configuration conf;

    private ScheduledFuture<Runnable> scheduledFuture;

    public void setExecutor(final ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    public void setScheduler(final TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setSrv(final StreamService srv) {
        this.srv = srv;
    }

    public void setConf(final Configuration conf) {
        this.conf = conf;
    }

    @SuppressWarnings("unchecked")
    public void replaceCronTrigger() {
        scheduledFuture.cancel(true);
        scheduledFuture = scheduler.schedule(this,
                new CronTrigger(conf.getString("crontab")));
    }

    @SuppressWarnings("unchecked")
    public void init() {
        scheduledFuture = scheduler.schedule(this,
                new CronTrigger(conf.getString("crontab")));
        executor.setCorePoolSize(10);
    }

    @Override
    public void run() {
        List<Stream> flows = srv.getStreams();
        executor.setMaxPoolSize(conf.getInt("poolSize"));
        for (Stream flow : flows) {
            executor.execute(new ActualThreadChecker(flow.getId(), flow
                    .getLink(), srv));
        }
    }
}
