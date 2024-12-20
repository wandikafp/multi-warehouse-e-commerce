package com.anw.user.service.domain.ports.input.jobs;

import com.anw.user.service.domain.ports.input.jobs.handler.SendWelcomeEmailJobHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendWelcomeEmailJob implements JobRequest {

    private UUID userId;

    @Override
    public Class<? extends JobRequestHandler> getJobRequestHandler() {
        return SendWelcomeEmailJobHandler.class;
    }
}
