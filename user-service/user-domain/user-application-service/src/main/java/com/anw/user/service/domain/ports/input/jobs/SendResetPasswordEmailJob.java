package com.anw.user.service.domain.ports.input.jobs;

import com.anw.user.service.domain.ports.input.jobs.handler.SendResetPasswordEmailJobHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendResetPasswordEmailJob implements JobRequest {

    private Long tokenId;

    @Override
    public Class<? extends JobRequestHandler> getJobRequestHandler() {
        return SendResetPasswordEmailJobHandler.class;
    }
}

