package com.iteco.basetask.job;

import com.iteco.basetask.entities.Address;
import com.iteco.basetask.services.AddressService;
import com.iteco.basetask.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailSender {

    private static final String CRON = "* * 17 * * MON-FRI";

    private final AddressService addressService;

    private final EmailService emailService;

    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
          List<Address> list = addressService.findAll();
        System.out.println(list);
        if (!list.isEmpty()) {
            list.forEach(addresses -> {
                try {
                    String message = "Пора пить чай!";
                    emailService.send(addresses.getEmail(), "Пора пить чай!", message);
                    log.info("Email have been sent.");
                } catch (Exception e) {
                    log.error("Email can't be sent", e);
                }
            });
        }
    }

    private AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 5)
    public void scheduled() {
        this.count.incrementAndGet();
    }

    public int getInvocationCount() {
        return this.count.get();
    }
}


