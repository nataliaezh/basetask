package com.iteco.basetask;

import com.iteco.basetask.job.MailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(BasetaskApplication.class)
public class ScheduledIntegrationTask {

    @Autowired
    MailSender mailSender;

    @Test
    public void givenSleepBy100ms_whenGetInvocationCount_thenIsGreaterThanZero()
            throws InterruptedException {
        Thread.sleep(100L);
        assertThat(mailSender.getInvocationCount()).isGreaterThan(0);
    }
}